package cn.sparke.core.common.utils;

import cn.sparke.core.common.exception.BussinessException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ExcelToObject{

    public static <T> List<T> convert(Workbook workbook, String templateName, T targetObje) throws Exception {
        List<T> result = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);
        ExcelTemplateInfo templateInfo  = loadTemplate(templateName);
        //数据开始行
        int dataStartRow = templateInfo.getDataRow();
        //获取总行数
        int rowCount = sheet.getLastRowNum();
        if (rowCount < dataStartRow) { //无数据
            return new ArrayList<>();
        }
        for (int i = dataStartRow; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (ExcelUtil.isRowEmpty(row)) {
                break;
            }
            JSONObject json = new JSONObject();
            for (int j=0; j<templateInfo.getColumnKeys().length; j++){
                json.put(templateInfo.getColumnKeys()[j], ExcelUtil.getStringValue(row.getCell(j)));
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try{
                T obj = (T)mapper.readValue(json.toString(),targetObje.getClass());
                result.add(obj);
            }catch (Exception e){
                throw new Exception("第"+(i+1)+"行数据格式有误请修改后重新提交");
            }

        }
        return result;
    }

    private static ExcelTemplateInfo loadTemplate(String fileName) throws Exception {
        ExcelTemplateInfo template = new ExcelTemplateInfo();
        String path="classpath:template/paperExcel/";
        Workbook workbook = WorkbookFactory.create(ResourceUtils.getFile(path+fileName));
        Sheet sheet = workbook.getSheetAt(1);
        try {
            Row headRow = sheet.getRow(1);
            //数据开始行

            template.setDataRow(ExcelUtil.getIntegerValue(headRow.getCell(0)));
            template.setColumnCount(ExcelUtil.getIntegerValue(headRow.getCell(1)));
        }catch (Exception e){
            throw new Exception("模板定义错误");
        }

        sheet = workbook.getSheetAt(0);
        Row dataRow =  sheet.getRow(template.getDataRow());
        String[] columnKeys = new String[template.getColumnCount()];
        for (int i=0;i< columnKeys.length;i++){
            String key = ExcelUtil.getStringValue(dataRow.getCell(i));
            if(StringUtils.isEmpty(key)){
                break;
            }
            columnKeys[i] = key;
        }
        template.setColumnKeys(columnKeys);

        return template;
    }

    public static class ExcelTemplateInfo{
        private int dataRow = 2;
        private int columnCount ;
        private String[] columnKeys;

        public int getColumnCount() {
            return columnCount;
        }

        public void setColumnCount(int columnCount) {
            this.columnCount = columnCount;
        }

        public int getDataRow() {
            return dataRow;
        }

        public void setDataRow(int dataRow) {
            this.dataRow = dataRow;
        }

        public String[] getColumnKeys() {
            return columnKeys;
        }

        public void setColumnKeys(String[] columnKeys) {
            this.columnKeys = columnKeys;
        }
    }


}
