package cn.sparke.modules.questionbank.question.service;

import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.paper.paper.mapper.PaperMapper;
import cn.sparke.modules.paper.paperCatalog.entity.PaperCatalogEntity;
import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;
import cn.sparke.modules.qrcode.QrCaptionListening.mapper.QrCaptionListeningMapper;
import cn.sparke.modules.questionbank.CaptionListening.entity.CaptionListeningEntity;
import cn.sparke.modules.questionbank.CaptionListening.mapper.CaptionListeningMapper;
import cn.sparke.modules.questionbank.CaptionListeningVideo.entity.CaptionListeningVideoEntity;
import cn.sparke.modules.questionbank.CaptionListeningVideo.mapper.CaptionListeningVideoMapper;
import cn.sparke.modules.questionbank.question.entity.QuestionContentType;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_listening.entity.Question_listeningEntity;
import cn.sparke.modules.questionbank.question_listening.mapper.Question_listeningMapper;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by douht on 2017/7/26.
 */
@Service
public class QuestionSubtitleListService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private Question_listeningMapper questionListeningMapper;
    @Autowired
    private PaperStructureMapper paperStrucMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;
    @Autowired
    private CaptionListeningMapper captionListeningMapper;
    @Autowired
    private CaptionListeningVideoMapper  captionListeningVideoMapper;
    @Autowired
    private QrCaptionListeningMapper  qrCaptionListeningMapper;

    @Autowired
    private PaperMapper paperMapper;

    public  Map<String,String> insertExcel(HSSFWorkbook book, Map<String, String> paramMap){
        String message="";
        String code="";
        Map<String,String> dataMap = new HashMap<String,String>();
        if(paramMap != null && !paramMap.isEmpty()){
            if(paramMap.containsKey("type") && paramMap.get("type").equals("2")){
                if(paramMap.containsKey("paperId")){
                    if(paramMap.containsKey("contentType")){
                        //得到导入书卷的名称
                        String name = book.getSheetName(0);
                        HSSFSheet sheet = book.getSheet(name);
                        Integer startRow= 2;//默认从第二行开始解析
                        if(paramMap.containsKey("startRow")){
                            startRow = Integer.valueOf(paramMap.get("startRow").toString());
                        }else {
                            paramMap.put("startRow",startRow.toString());
                        }
                        HSSFCell cell0=sheet.getRow(startRow).getCell(0);
                        HSSFCell cell=sheet.getRow(startRow).getCell(1);
                        String sectionCode = (String) verifyData(cell0,0);//学段 sectionCode
                        String papername = (String) verifyData(cell,0);////试卷名称
                        if(sectionCode != null &&  !sectionCode.equals("")){
                            if(sectionCode.equals(paramMap.get("sectionCode"))){
                                if(papername != null &&  !papername.equals("")){
                                    if(papername.equals(paramMap.get("paperName"))){
                                        Integer  contentType=Integer.valueOf(paramMap.get("contentType"));
                                        switch (contentType){
                                            case 1:
                                                dataMap=insertListenExcel(book,paramMap);
                                                break;
//                                      case 2:
//                                              dataMap=insertReadExcel(book,paramMap);
//                                              break;
//                                      case 3:
//                                              dataMap=insertTranslationExcel(book,paramMap);
//                                              break;
//                                      case 4:
//                                              dataMap =insertWriteExcel(book,paramMap);
//                                              break;
                                            default:
                                                code="902";
                                                message="试卷内容类型不正确";
                                                dataMap.put("code",code);
                                                dataMap.put("message",message);
                                                break;
                                        }
                                        code = dataMap.get("code");
                                        message = dataMap.get("message");
                                    }else{
                                        code="902";
                                        message="导入名为：\""+papername+"\"的试卷,与选中试卷的名称不符，请核对试卷信息！";
                                    }

                                }else {
                                    code="902";
                                    message="第"+startRow+"行第二列的值不能为空";
                                }
                            }else {
                                code="902";
                                message="导入的试卷,与选中试卷的学段不符，请核对试卷信息！";
                            }
                        }else {
                            code="902";
                            message="第"+startRow+"行第一列的值不能为空";
                        }
                    }else{
                        code="902";
                        message="试卷内容类型不能为空";
                    }
                }else {
                    code="902";
                    message="试卷ID不能为空";
                }
            }else {
                code="902";
                message="试卷类型不正确(改试卷类型不是字幕听力)，请核对" ;//1.全真考场 2.字幕听力 3.简系列 4.专项练习,5.扫码字幕听力
            }
        }else {
            code="902";
            message="试卷信息不能为空";
        }
        dataMap.put("code",code);
        dataMap.put("message",message);
        return dataMap;
    }

    public Map<String,String> insertListenExcel (HSSFWorkbook book,Map<String,String> paramMap){

        String code="0";
        String massege="导入成功";
        Map<String,String> returnMap = new HashMap<String,String>();
        Map<String, Object> dataMap = readListenExcel(book, paramMap);
        if(dataMap !=null){
            if(!(dataMap.get("code").toString()).equals("0")){
                returnMap.put("code","902");
                returnMap.put("message",dataMap.get("message").toString());
                return returnMap;
            }
        }else {
            returnMap.put("code","902");
            returnMap.put("message","数据为空！");
            return returnMap;
        }
        List<Map<String,Object>> excelList = (List<Map<String,Object>>)dataMap.get("excelList");
        if(excelList !=null && !excelList.isEmpty()){
            //Integer startRow=2;//从第几行开始解析(默认为第二行开始)
            List<PaperStructureEntity> paperStructureList = new ArrayList<PaperStructureEntity>();
            List<QuestionEntity> questionList = new ArrayList<QuestionEntity>();
            List<PaperQuestionEntity> paperQuestionList = new ArrayList<PaperQuestionEntity>();
            List<Question_listeningEntity> questionListeningList = new ArrayList<Question_listeningEntity>();
            List<CaptionListeningEntity> captionListeningList = new ArrayList<CaptionListeningEntity>();
            List<CaptionListeningVideoEntity> captionListeningVideoList = new ArrayList<CaptionListeningVideoEntity>();
            List<QrCaptionListeningEntity> qrCaptionListeningList = new ArrayList<QrCaptionListeningEntity>();
            for(Map<String,Object> map:excelList){
                if(map.containsKey("paperStructureNode1")){
                    paperStructureList.add((PaperStructureEntity)map.get("paperStructureNode1"));
                }
                if(map.containsKey("question")){
                    questionList.add((QuestionEntity)map.get("question"));
                }
                if(map.containsKey("paperQuestion")){
                    paperQuestionList.add((PaperQuestionEntity) map.get("paperQuestion"));
                }
                if(map.containsKey("questionListening")){
                    questionListeningList.add((Question_listeningEntity) map.get("questionListening"));
                }
                if(map.containsKey("captionListening")){
                    captionListeningList.add((CaptionListeningEntity) map.get("captionListening"));
                }
                if(map.containsKey("captionListeningVideo")){
                    captionListeningVideoList.add((CaptionListeningVideoEntity) map.get("captionListeningVideo"));
                }
                if(map.containsKey("qrCaptionListening")){
                    qrCaptionListeningList.add((QrCaptionListeningEntity) map.get("qrCaptionListening"));
                }
            }
            try{
                //插入数据
                paperStrucMapper.batchInsert(paperStructureList);
                questionMapper.insertList(questionList);
                paperQuestionMapper.batchInsert(paperQuestionList);
                questionListeningMapper.insertList(questionListeningList);
                captionListeningMapper.insertList(captionListeningList);
                captionListeningVideoMapper.insertList(captionListeningVideoList);
                qrCaptionListeningMapper.insertList(qrCaptionListeningList);
                returnMap.put("code","0");
                returnMap.put("message","导入成功");
            }catch (Exception e){
                e.printStackTrace();
                returnMap.put("code","902");
                returnMap.put("message","数据库存储数据失败");
            }


        }else {
            returnMap.put("code","902");
            returnMap.put("message","Excel的数据为空");
        }
        return returnMap;

    }

    //解析字幕听力
    private Map<String,Object> readListenExcel( HSSFWorkbook book,Map<String, String> paramMap){

        Map<String,Object> returnMap =new HashMap<String,Object>();
        String message="导入成功";
        String code="0";//0表示导入成功，902表示导入数据失败
        Integer sectionCode = 0;
        String questionId = "";
        String captionListeningId="";
        String struParentId = "";
        String Node1Id="";
        Integer sort=0;
        Integer startRow=Integer.valueOf(paramMap.get("startRow"));
        List<Map<String,Object>> excelList=  new ArrayList<Map<String,Object>>();
        int sheetNumb = book.getNumberOfSheets();
        for(int i=0; i<sheetNumb ;i++) {
            String name = book.getSheetName(i);
            HSSFSheet sheet = book.getSheet(name);
            int rowNumber = sheet.getLastRowNum();
            for (int k = startRow ; k < rowNumber; k++) {
                Map<String, Object> excelMap = new HashMap<String, Object>();
                HSSFRow row = sheet.getRow(k);
                int cellNum = row.getLastCellNum();
                PaperCatalogEntity paperCatalogEntity = new PaperCatalogEntity();
                try{
                    HSSFCell cell0 = row.getCell(0);
                    if (cell0 != null && cell0.getCellType() != HSSFCell.CELL_TYPE_BLANK) {
                        sectionCode = (Integer) verifyData(cell0, 1);//获取学段
                    }
                    HSSFCell cell1 = row.getCell(1);
                    String papername = (String) verifyData(cell1, 0);////试卷名称

                    HSSFCell cell2 = row.getCell(2);
                    String qrCode = (String) verifyData(cell2, 0);//二维码

                    HSSFCell cell3 = row.getCell(3);
                    String audioUrl = (String) verifyData(cell3, 0);////音频地址
                    HSSFCell cell4 = row.getCell(4);
                    String subtitleUrl = (String) verifyData(cell4, 0);//字幕文件地址

                    HSSFCell cell5 = row.getCell(5);
                    String ccId = (String) verifyData(cell5, 0);//视频ID

                    HSSFCell cell6 = row.getCell(6);
                    String videoName = (String) verifyData(cell6, 0);//视频名称

                    try{
                        //创建试卷树形结构对象(父节点)
                        if (audioUrl != null && !audioUrl.equals("")) { //
                            //一级目录
                            PaperStructureEntity paperStructureNode1 = new PaperStructureEntity();
                            paperStructureNode1.preInsert();
                            Node1Id = paperStructureNode1.getId();
                            paperStructureNode1.setPaperId(paramMap.get("paperId"));
                            paperStructureNode1.setName("字幕听力");
                            paperStructureNode1.setContentType(Integer.valueOf(QuestionContentType.CONTENT_LISTENING));//题目类型（翻译）
                            paperStructureNode1.setAlias("字幕听力");//节点别名
                            paperStructureNode1.setParentId(struParentId);//父类id
                            paperStructureNode1.setParentIds(struParentId);//父类的所有id
                            paperStructureNode1.setLevel(1);//级别
                            paperStructureNode1.setIsLeaf(1);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                            paperStructureNode1.setSort(1);
                            paperStructureNode1.setRemarks("字幕听力");
                            excelMap.put("paperStructureNode1", paperStructureNode1);
                        }
                        //给bean对象赋值
                        if (audioUrl != null && !audioUrl.equals("")) {

                            //创建题目对象
                            QuestionEntity question = new QuestionEntity();
                            question.preInsert();
                            questionId = question.getId();
                            question.setName("字幕听力");//题目名称
                            question.setType(Integer.valueOf(QuestionContentType.CONTENT_LISTENING));//听力
                            question.setContent("");//题干
                            question.setSectionCode(sectionCode);//学段
//                    question.setAnalysis(itemAnalysis);
                            //判断是否存在小题
//                    if (itemContent != null && !itemContent.equals("")) {
//                        question.setHasItem(1);//存在小提
//                    } else {
                            question.setHasItem(0); //不存在
//                    }

                            //创建试卷题目关系对象
                            PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                            paperQuestion.preInsert();
                            paperQuestion.setQuestionId(questionId);//添加题目id
                            paperQuestion.setPaperId(paramMap.get("paperId"));//添加试卷id
                            paperQuestion.setStructureId(Node1Id);
                            paperQuestion.setSort(0);


                            //创建听力对象
                            Question_listeningEntity questionListening =new Question_listeningEntity();
                            questionListening.preInsert();
                            questionListening.setAudioUrl(audioUrl);
                            questionListening.setSubtitleUrl(subtitleUrl);
                            questionListening.setQuestionId(questionId);
                            questionListening.setAudioSize(0);
                            questionListening.setSubtitleSize(0);

                            //创建字幕听力对象
                            CaptionListeningEntity captionListening =new CaptionListeningEntity();
                            captionListening.preInsert();
                            captionListening.setPaperId(paramMap.get("paperId"));
                            captionListening.setAudioUrl(audioUrl);
                            captionListening.setSubtitleUrl(subtitleUrl);
                            captionListening.setAudioSize(0);
                            captionListening.setSubtitleSize(0);

                            excelMap.put("question", question);
                            excelMap.put("paperQuestion", paperQuestion);
                            excelMap.put("questionListening", questionListening);
                            excelMap.put("captionListening", captionListening);

                        }
                        if(ccId != null && !ccId.equals("")){
                            //创建字幕听力视频对象
                            CaptionListeningVideoEntity captionListeningVideo = new CaptionListeningVideoEntity();
                            captionListeningVideo.preInsert();
                            captionListeningId=captionListeningVideo.getId();
                            captionListeningVideo.setPaperId(paramMap.get("paperId"));
                            captionListeningVideo.setCcId(ccId);
                            captionListeningVideo.setName(videoName);
                            excelMap.put("captionListeningVideo", captionListeningVideo);
                        }
                        if(qrCode != null && !qrCode.equals("")){
                            //更新试卷中的二维码
                            PaperEntity paperEntity =new PaperEntity();
                            paperEntity.setId(paramMap.get("paperId"));
                            paperEntity.setQrCode(qrCode);
                            paperMapper.update(paperEntity);
                            //创建二维码对象
                            QrCaptionListeningEntity qrCaptionListening = new QrCaptionListeningEntity();
                            qrCaptionListening.preInsert();
                            qrCaptionListening.setCaptionListeningId(captionListeningId);
                            qrCaptionListening.setQrCode(qrCode);
                            qrCaptionListening.setSort(sort);
                            sort++;
                            excelMap.put("qrCaptionListening", qrCaptionListening);
                        }
                        if(excelMap != null && !excelMap.isEmpty()){
                            excelList.add(excelMap);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        code="902";
                        message="数据建立对象出现错误";
                        returnMap.put("code",code);
                        returnMap.put("message",message);
                        return returnMap;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    code="902";
                    message="第"+(k+1)+"行第"+cellNum+"列的数据类型不正确，请核对";
                    returnMap.put("code",code);
                    returnMap.put("message",message);
                    return returnMap;
                }
            }
        }
        returnMap.put("code",code);
        returnMap.put("message",message);
        returnMap.put("excelList",excelList);
        return returnMap;

    }

    public Object verifyData(HSSFCell cell, Integer bz) {
        Object obj = null;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                System.out.print(cell.getStringCellValue() + "   ");
                if (bz == 1) {
                    obj = Integer.valueOf(cell.getStringCellValue());
                } else {
                    obj = cell.getStringCellValue();
                }
                break;
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //  如果是date类型则 ，获取该cell的date值
                    obj = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
                } else {
                    obj = (int) cell.getNumericCellValue();

                }
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                System.out.println(cell.getBooleanCellValue() + "   ");
                obj = cell.getBooleanCellValue();
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                System.out.print(cell.getCellFormula() + "   ");
                obj = cell.getCellFormula();
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                System.out.println(" ");
                obj = null;
                break;
            default:
                System.out.print("未知类型   ");
                obj = null;
                break;
        }
        return obj;
    }
}
