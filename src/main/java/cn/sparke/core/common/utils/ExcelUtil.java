package cn.sparke.core.common.utils;

import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;

public class ExcelUtil {

    public static String getStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        String value = null;
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case BLANK :
                value = null;
                break;
            case STRING : {
                value = cell.getStringCellValue();
                break;
            }
            case NUMERIC : {
                DecimalFormat decimalFormat = new DecimalFormat("0");

                Double a = cell.getNumericCellValue();
                if (a != null) {
                    value = decimalFormat.format(a);
                }
                break;
            }
            default :
                throw new BussinessException(BizExceptionEnum.VALUE_TYPE_NOT_RIGHT);
        }
        if (!StringUtils.isEmpty(value)) {
            value = value.trim();
        }
        return value;
    }

    public static Integer getIntegerValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        Integer value = null;
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case BLANK :
                value = null;
                break;
            case STRING : {
                value = Integer.parseInt(cell.getStringCellValue());
                break;
            }
            case NUMERIC : {
                Double a = cell.getNumericCellValue();
                value = a.intValue();
                break;
            }
            default :
                throw new BussinessException(BizExceptionEnum.VALUE_TYPE_NOT_RIGHT);
        }
        return value;
    }

    public static boolean isRowEmpty(Row row) {

        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {

            Cell cell = row.getCell(c);

            if (cell != null && !cell.getCellTypeEnum().equals(CellType.BLANK)) {
                return false;
            }


        }

        return true;

    }

}
