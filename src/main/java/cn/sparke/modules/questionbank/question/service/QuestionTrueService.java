package cn.sparke.modules.questionbank.question.service;

import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import cn.sparke.modules.questionbank.question.entity.QuestionContentType;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_item.entity.Question_itemEntity;
import cn.sparke.modules.questionbank.question_item.mapper.Question_itemMapper;
import cn.sparke.modules.questionbank.question_listening.entity.Question_listeningEntity;
import cn.sparke.modules.questionbank.question_listening.mapper.Question_listeningMapper;
import cn.sparke.modules.questionbank.question_option.entity.Question_optionEntity;
import cn.sparke.modules.questionbank.question_option.mapper.Question_optionMapper;
import cn.sparke.modules.questionbank.question_reading.entity.Question_readingEntity;
import cn.sparke.modules.questionbank.question_reading.mapper.Question_readingMapper;
import cn.sparke.modules.questionbank.question_translation.entity.Question_translationEntity;
import cn.sparke.modules.questionbank.question_translation.mapper.Question_translationMapper;
import cn.sparke.modules.questionbank.question_writing.entity.Question_writingEntity;
import cn.sparke.modules.questionbank.question_writing.mapper.Question_writingMapper;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by douht on 2017/7/26.
 */
@Service

public class QuestionTrueService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private Question_listeningMapper questionListeningMapper;
    @Autowired
    private Question_readingMapper questionReadingMapper;
    @Autowired
    private Question_translationMapper quesionTranslationMapper;
    @Autowired
    private Question_writingMapper questionWritingMapper;
    @Autowired
    private Question_itemMapper questionItemMapper;
    @Autowired
    private Question_optionMapper questoinOptionMapper;
    @Autowired
    private PaperStructureMapper paperStrucMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;
    @Transactional
    public Map<String,String> insertExcel(HSSFWorkbook book, Map<String, String> paramMap){
        String message="";
        String code="";
        Map<String,String> dataMap = new HashMap<String,String>();
        if(paramMap != null && !paramMap.isEmpty()){
            if(paramMap.containsKey("type") && paramMap.get("type").equals("1")){
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
                        HSSFCell cell=sheet.getRow(startRow).getCell(1);//cellNum,0
                        String sectionCode = (String) verifyData(cell0,startRow,1,0);//学段 sectionCode
                        String papername = (String) verifyData(cell,startRow,2,0);////试卷名称
                        if(sectionCode != null &&  !sectionCode.equals("")){
                            if(sectionCode.equals(paramMap.get("sectionCode"))){
                                if(papername != null &&  !papername.equals("")){
                                    if(papername.equals(paramMap.get("paperName").toString())){
                                        Integer  contentType=Integer.valueOf(paramMap.get("contentType"));
                                        switch (contentType){
                                            case 1:
                                                dataMap=insertListenExcel(book,paramMap);
                                                break;
                                            case 2:
                                                dataMap=insertReadExcel(book,paramMap);
                                                break;
                                            case 3:
                                                dataMap=insertTranslationExcel(book,paramMap);
                                                break;
                                            case 4:
                                                dataMap =insertWriteExcel(book,paramMap);
                                                break;
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
               message="试卷类型不正确(改试卷类型不是全真考场)，请核对" ;//1.全真考场 2.字幕听力 3.简系列 4.专项练习,5.扫码字幕听力
            }

        }else {
            code="902";
            message="试卷信息不能为空";
        }
        dataMap.put("code",code);
        dataMap.put("message",message);
        return dataMap;
    }
    //transaction
    @Transactional
    public Map<String ,String> insertListenExcel(HSSFWorkbook book, Map<String, String> paramMap) {
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
        if (excelList != null && !excelList.isEmpty()) {
                //得到题目信息，并保存到 QuestionEntity（题目）对象
                List<QuestionEntity> questionList = new ArrayList<QuestionEntity>();
                List<PaperQuestionEntity> paperQuestionList = new ArrayList<PaperQuestionEntity>();
                List<PaperStructureEntity> papeStrucureList = new ArrayList<PaperStructureEntity>();
                List<Question_listeningEntity> questionListrnList = new ArrayList<Question_listeningEntity>();
                List<Question_itemEntity> questionItemList = new ArrayList<Question_itemEntity>();
                List<Question_optionEntity> questionOptionList = new ArrayList<Question_optionEntity>();
                for (Map<String,Object> excelMap : excelList) {
                    if (excelMap.containsKey("question")) {
                        questionList.add((QuestionEntity) excelMap.get("question"));
                    }
                    if (excelMap.containsKey("paperQuestion")) {
                        paperQuestionList.add((PaperQuestionEntity) excelMap.get("paperQuestion"));
                    }
                    if (excelMap.containsKey("paperStructureNode1")) {
                        papeStrucureList.add((PaperStructureEntity) excelMap.get("paperStructureNode1"));
                    }
                    if (excelMap.containsKey("paperStructureNode2")) {
                        papeStrucureList.add((PaperStructureEntity) excelMap.get("paperStructureNode2"));
                    }
                    if (excelMap.containsKey("paperStructureNode3")) {
                        papeStrucureList.add((PaperStructureEntity) excelMap.get("paperStructureNode3"));
                    }
                    if (excelMap.containsKey("questionListen")) {
                        questionListrnList.add((Question_listeningEntity) excelMap.get("questionListen"));
                    }
                    if (excelMap.containsKey("questionItem")) {
                        questionItemList.add((Question_itemEntity) excelMap.get("questionItem"));
                    }
                    if (excelMap.containsKey("questionOption")) {
                        questionOptionList.add((Question_optionEntity) excelMap.get("questionOption"));
                    }
                }

                try{
                    //保存题目相关对象
                    questoinOptionMapper.insertList(questionOptionList);
                    questionMapper.insertList(questionList);
                    questionListeningMapper.insertList(questionListrnList);
                    questionItemMapper.insertList(questionItemList);
                    //保存试卷相关对象信息
                    paperStrucMapper.batchInsert(papeStrucureList);
                    paperQuestionMapper.batchInsert(paperQuestionList);
                    returnMap.put("code","0");
                    returnMap.put("message","导入成功");
                }catch (Exception e){
                    e.printStackTrace();
                    returnMap.put("code","902");
                    returnMap.put("message","数据库存储数据失败");
                }
            } else {
                returnMap.put("code","902");
                returnMap.put("message","Excel的数据为空");
            }
        return returnMap;
    }

    /**
     * 解析真题听力Excel（吧Excel转化成List）
     */
    private Map<String, Object> readListenExcel(HSSFWorkbook book, Map<String, String> paramMap) {

        Map<String,Object> returnMap =new HashMap<String,Object>();
        String message="导入成功";
        String code="0";//0表示导入成功，902表示导入数据失败
        Integer sectionCode = 0;
        String questionId = "";
        String itemId = "";
        String Node1Id="";
        String Node1Id2="";
        String Node1Id3="";
        Integer sort=0;
        Integer option=0;
        Integer startRow=Integer.valueOf(paramMap.get("startRow"));
        //得到试卷信息并保持到PaperEntity（试卷）对象中
        List<Map<String, Object>> excelList = new ArrayList<Map<String, Object>>();
        int sheetNumb = book.getNumberOfSheets();
        for (int i = 0; i < sheetNumb; i++) {
            String name = book.getSheetName(i);
            HSSFSheet sheet = book.getSheet(name);
            int rowNum = sheet.getLastRowNum() + 1;
            for (int k = startRow; k < rowNum; k++) {
                Map<String, Object> excelMap = new HashMap<String, Object>();
                HSSFRow row = sheet.getRow(k);
                int cellNum = 1;
                HSSFCell cell0 = row.getCell(0);
                try{
                    if (cell0 !=null &&cell0.getCellType() != HSSFCell.CELL_TYPE_BLANK) {
                        sectionCode = (Integer) verifyData(cell0, k,cellNum,1);//获取学段
                    }
                    cellNum++;
                    HSSFCell cell1 = row.getCell(1);
                    String papername = (String) verifyData(cell1, k,cellNum,0);////试卷名称
                    cellNum++;

                    HSSFCell cell2 = row.getCell(2);
                    String structureNameNode1 = (String) verifyData(cell2, k,cellNum,0);//试卷树形结构一级节点名称（PartI）
                    cellNum++;

                    HSSFCell cell3 = row.getCell(3);
                    String structureNameNode2 = (String) verifyData(cell3, k,cellNum,0); //试卷树形结构二级节点名称（Section A，Section B）
                    cellNum++;

                    HSSFCell cell4 = row.getCell(4);
                    String Node1Alias = (String) verifyData(cell4, k,cellNum,0);//试卷树形结构二级节点描述
                    cellNum++;

                    HSSFCell cell5 = row.getCell(5);
                    String structureNameNodeA3 = (String) verifyData(cell5, k,cellNum,0);//试卷树形结构三级节点的名称（(News Report One··）新闻报道类
                    cellNum++;

                    HSSFCell cell6 = row.getCell(6);
                    String structureNameNodeB3 = (String) verifyData(cell6, k,cellNum,0);//试卷树形结构三级节点的名称（Conversation one··） 交流类
                    cellNum++;

                    HSSFCell cell7 = row.getCell(7);
                    String structureNameNodeC3 = (String) verifyData(cell7, k,cellNum,0); //试卷树形结构三级节点的名称（Passage One）
                    cellNum++;

                    HSSFCell cell8 = row.getCell(8);
                    String questionName = (String) verifyData(cell8, k,cellNum,0); //题目名称
                    cellNum++;


                    HSSFCell cell9 = row.getCell(9);
                    String questioncontent = (String) verifyData(cell9, k,cellNum,0);
                    cellNum++;

                    HSSFCell cell10 = row.getCell(10);
                    String itemAnalysis = (String) verifyData(cell10, k,cellNum,0);//解析（对小题的解析）
                    cellNum++;

                    HSSFCell cell11 = row.getCell(11);
                    String audioUrl = (String) verifyData(cell11, k,cellNum,0);//音频地址
                    cellNum++;

                    HSSFCell cell12 = row.getCell(12);
                    String tapescripts = (String) verifyData(cell12, k,cellNum,0);//听力原文
                    cellNum++;

                    HSSFCell cell13 = row.getCell(13);
                    String itemContent = (String) verifyData(cell13, k,cellNum,0);//小题内容
                    cellNum++;

                    HSSFCell cell14 = row.getCell(14);
                    Integer itemsort = (Integer) verifyData(cell14, k,cellNum,1);//题号
                    cellNum++;

                    HSSFCell cell15 = row.getCell(15);
                    String optionName = (String) verifyData(cell15, k,cellNum,0);//选项名称(A B C D)
                    cellNum++;

                    HSSFCell cell16 = row.getCell(16);
                    String optionContent = (String) verifyData(cell16, k,cellNum,0);//选项内容
                    cellNum++;

                    HSSFCell cell17 = row.getCell(17);
                    Integer isAnswer = (Integer) verifyData(cell17, k,cellNum,1);//是否正确答案（0否 1是）
                    try{
                        //二级节点Section A, Section B, Section C
                        PaperStructureEntity paperStructureNode2 = new PaperStructureEntity();
                        //创建试卷树形结构对象(父节点)
                        if (structureNameNode1 != null && !structureNameNode1.equals("")) {
                            //一级节点 PartI
                            PaperStructureEntity paperStructureNode1 = new PaperStructureEntity();
                            paperStructureNode1.preInsert();
                            Node1Id = paperStructureNode1.getId();
                            paperStructureNode1.setPaperId(paramMap.get("paperId"));
                            paperStructureNode1.setName(structureNameNode1);
                            paperStructureNode1.setContentType(Integer.valueOf(QuestionContentType.CONTENT_LISTENING));//题目类型（听力）
                            // paperStructureNode1.setContent(Node1Alias);//节点描述
                            paperStructureNode1.setAlias(structureNameNode1);//节点别名
                            paperStructureNode1.setParentId("0");//父类id
                            paperStructureNode1.setParentIds("0");//父类的所有id
                            paperStructureNode1.setLevel(1);//级别
                            paperStructureNode1.setIsLeaf(0);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                            paperStructureNode1.setSort(1);
                            paperStructureNode1.setRemarks(structureNameNode1);
                            excelMap.put("paperStructureNode1", paperStructureNode1);

                        }
                        if (structureNameNode2 != null && !structureNameNode2.equals("")) {
                            //二级节点Section A, Section B, Section C
                            paperStructureNode2.preInsert();
                            Node1Id2=paperStructureNode2.getId();
                            paperStructureNode2.setPaperId(paramMap.get("paperId"));
                            paperStructureNode2.setName(structureNameNode2);
                            paperStructureNode2.setContentType(Integer.valueOf(QuestionContentType.CONTENT_LISTENING));//题目类型（听力）
                            paperStructureNode2.setContent(Node1Alias);//节点描述
                            paperStructureNode2.setAlias(structureNameNode2);//节点别名
                            paperStructureNode2.setParentId(Node1Id);//父类id
                            paperStructureNode2.setParentIds("0,"+structureNameNode2);//父类的所有id
                            paperStructureNode2.setLevel(2);//级别
                            paperStructureNode2.setSort(1);
                            paperStructureNode2.setRemarks(structureNameNode2);
                            excelMap.put("paperStructureNode2", paperStructureNode2);

                        }
                        if(structureNameNodeA3 != null && !structureNameNodeA3.equals("")){
                            //三级节点News Report
                            PaperStructureEntity paperStructureNode3 = new PaperStructureEntity();
                            paperStructureNode3.preInsert();
                            Node1Id3=paperStructureNode3.getId();
                            paperStructureNode3.setPaperId(paramMap.get("paperId"));
                            paperStructureNode3.setName(structureNameNodeA3);
                            paperStructureNode3.setContentType(Integer.valueOf(QuestionContentType.CONTENT_LISTENING));//题目类型（听力）
                            paperStructureNode3.setContent(structureNameNodeA3);//节点描述
                            paperStructureNode3.setAlias(structureNameNodeA3);//节点别名
                            paperStructureNode3.setParentId(Node1Id2);//父类id
                            paperStructureNode3.setParentIds("0,"+structureNameNode2+","+Node1Id2);//父类的所有id
                            paperStructureNode3.setLevel(3);//级别
                            paperStructureNode3.setIsLeaf(1);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                            paperStructureNode3.setSort(1);
                            paperStructureNode3.setRemarks(structureNameNodeA3);
                            //二级节点不是叶子节点
                            paperStructureNode2.setIsLeaf(0);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）

                            excelMap.put("paperStructureNode3", paperStructureNode3);

                        }else if(structureNameNodeB3 != null && !structureNameNodeB3.equals("")){
                            //三级节点Conversation
                            PaperStructureEntity paperStructureNode3 = new PaperStructureEntity();
                            paperStructureNode3.preInsert();
                            Node1Id3=paperStructureNode3.getId();
                            paperStructureNode3.setPaperId(paramMap.get("paperId"));
                            paperStructureNode3.setName(structureNameNodeB3);
                            paperStructureNode3.setContentType(Integer.valueOf(QuestionContentType.CONTENT_LISTENING));//题目类型（听力）
                            paperStructureNode3.setContent(structureNameNodeB3);//节点描述
                            paperStructureNode3.setAlias(structureNameNodeB3);//节点别名
                            paperStructureNode3.setParentId(Node1Id2);//父类id
                            paperStructureNode3.setParentIds("0,"+structureNameNode2+","+Node1Id2);//父类的所有id
                            paperStructureNode3.setLevel(3);//级别
                            paperStructureNode3.setIsLeaf(1);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                            paperStructureNode3.setSort(2);
                            paperStructureNode3.setRemarks(structureNameNodeB3);

                            //二级节点不是叶子节点
                            paperStructureNode2.setIsLeaf(0);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）

                            excelMap.put("paperStructureNode3", paperStructureNode3);

                        }else if(structureNameNodeC3 != null && !structureNameNodeC3.equals("")){
                            //三级节点Conversation
                            PaperStructureEntity paperStructureNode3 = new PaperStructureEntity();
                            paperStructureNode3.preInsert();
                            Node1Id3=paperStructureNode3.getId();
                            paperStructureNode3.setPaperId(paramMap.get("paperId"));
                            paperStructureNode3.setName(structureNameNodeC3);
                            paperStructureNode3.setContentType(Integer.valueOf(QuestionContentType.CONTENT_LISTENING));//题目类型（听力）
                            paperStructureNode3.setContent(structureNameNodeC3);//节点描述
                            paperStructureNode3.setAlias(structureNameNodeC3);//节点别名
                            paperStructureNode3.setParentId(Node1Id2);//父类id
                            paperStructureNode3.setParentIds("0,"+structureNameNode2+","+Node1Id2);//父类的所有id
                            paperStructureNode3.setLevel(3);//级别
                            paperStructureNode3.setIsLeaf(1);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                            paperStructureNode3.setSort(3);
                            paperStructureNode3.setRemarks(structureNameNodeC3);

                            //二级节点不是叶子节点
                            paperStructureNode2.setIsLeaf(0);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                            excelMap.put("paperStructureNode3", paperStructureNode3);


                        }else {
                            //二级节点是叶子节点
                            paperStructureNode2.setIsLeaf(1);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                        }
                        //给bean对象赋值
                        if (questionName != null && !questionName.equals("")) {

                            //创建题目对象
                            QuestionEntity question = new QuestionEntity();
                            question.preInsert();
                            questionId = question.getId();
                            question.setName(questionName);//题目名称
                            question.setType(Integer.valueOf(QuestionContentType.CONTENT_LISTENING));//听力
                            question.setContent(questioncontent);//题干
                            question.setSectionCode(sectionCode);//学段
                            question.setAnalysis(itemAnalysis);
                            //判断是否存在小题
                            if (itemContent != null && !itemContent.equals("")) {
                                question.setHasItem(1);//存在小提
                            } else {
                                question.setHasItem(0); //不存在
                            }

                            //创建试卷题目关系对象
                            PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                            paperQuestion.preInsert();
                            paperQuestion.setQuestionId(questionId);//添加题目id
                            paperQuestion.setPaperId(paramMap.get("paperId"));//添加试卷id
                            paperQuestion.setStructureId(Node1Id3);//试卷树形结构id
                            paperQuestion.setSort(sort);
                            excelMap.put("question", question);
                            excelMap.put("paperQuestion", paperQuestion);
                            sort++;

                        }

                        if (audioUrl != null && !audioUrl.equals("")) {
                            //听力列表
                            Question_listeningEntity questionListen = new Question_listeningEntity();
                            questionListen.preInsert();//添加听力id
                            questionListen.setQuestionId(questionId);//添加题目id
                            questionListen.setAudioUrl(audioUrl);//音频URL
                            questionListen.setTapescripts(tapescripts);
                            questionListen.setTranslation(itemContent);
                            questionListen.setAudioSize(0);
                            //questionListen.setSubtitleSize(0);
                            excelMap.put("questionListen", questionListen);
                        }
                        if (itemContent != null && !itemContent.equals("")) {//小题
                            Question_itemEntity questionItem = new Question_itemEntity();
                            questionItem.preInsert();
                            itemId = questionItem.getId();
                            questionItem.setContent(itemContent);
                            questionItem.setQuestionId(questionId);

                            questionItem.setSort(itemsort);
                            excelMap.put("questionItem", questionItem);
                        }
                        if (optionName != null && !optionName.equals("")) {
                            Question_optionEntity questionOption = new Question_optionEntity();
                            questionOption.preInsert();
                            questionOption.setItemId(itemId);//问题id
                            questionOption.setContent(optionContent);//选项内容
                            questionOption.setIsAnswer(isAnswer);//是否为正确答案
                            questionOption.setName(optionName);
                            questionOption.setSort(option);
                            option++;
                            excelMap.put("questionOption", questionOption);
                        }
                        if(excelMap != null && !excelMap .isEmpty()){
                            excelList.add(excelMap);
                        }

                    }catch (Exception e){
                        code="902";
                        message="数据插入数据库错误";
                        returnMap.put("code",code);
                        returnMap.put("message",message);
                        return returnMap;
                    }

                }catch (Exception e){
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

    public Object verifyData(HSSFCell cell, int row,int column,Integer bz) {
        Object obj = null;
        switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        //  如果是date类型则 ，获取该cell的date值
                        obj = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
                    } else {
                        obj = (int) cell.getNumericCellValue();
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    System.out.print(cell.getStringCellValue() + "   ");
                    if (bz == 1) {
                        obj = Integer.valueOf(cell.getStringCellValue());
                    } else {
                        obj = cell.getStringCellValue();
                    }
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    obj = cell.getBooleanCellValue();
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    obj = cell.getCellFormula();
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    obj = null;
                    break;
                default:
                    System.out.print("未知类型   ");
                    obj = null;
                    break;
            }

        return obj;
    }

    public Map<String, String> insertReadExcel(HSSFWorkbook book, Map<String, String> paramMap) {
        Map<String,String> returnMap = new HashMap<String,String>();
        Map<String, Object> dataMap = readReadExcel(book, paramMap);
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
        if (excelList != null) {
            //得到题目信息，并保存到 QuestionEntity（题目）对象
            List<QuestionEntity> questionList = new ArrayList<QuestionEntity>();
            List<PaperQuestionEntity> paperQuestionList = new ArrayList<PaperQuestionEntity>();
            List<PaperStructureEntity> papeStrucureList = new ArrayList<PaperStructureEntity>();
            List<Question_readingEntity> questionReadingList = new ArrayList<Question_readingEntity>();
            List<Question_itemEntity> questionItemList = new ArrayList<Question_itemEntity>();
            List<Question_optionEntity> questionOptionList = new ArrayList<Question_optionEntity>();
            for (int i = 0; i < excelList.size(); i++) {
                Map<String, Object> excelMap = new HashMap<String, Object>();
                excelMap = excelList.get(i);
                if (excelMap.containsKey("question")) {
                    questionList.add((QuestionEntity) excelMap.get("question"));
                }
                if (excelMap.containsKey("paperQuestion")) {
                    paperQuestionList.add((PaperQuestionEntity) excelMap.get("paperQuestion"));
                }
                if (excelMap.containsKey("paperStructureNode1")) {
                    papeStrucureList.add((PaperStructureEntity) excelMap.get("paperStructureNode1"));
                }
                if(excelMap.containsKey("paperStructureNode2")){
                    papeStrucureList.add((PaperStructureEntity) excelMap.get("paperStructureNode2"));
                }
                if(excelMap.containsKey("paperStructureNode3")){
                    papeStrucureList.add((PaperStructureEntity) excelMap.get("paperStructureNode3"));
                }
                if (excelMap.containsKey("questionRead")) {
                    questionReadingList.add((Question_readingEntity) excelMap.get("questionRead"));
                }
                if (excelMap.containsKey("questionItem")) {
                    questionItemList.add((Question_itemEntity) excelMap.get("questionItem"));
                }
                if (excelMap.containsKey("questionOption")) {
                    questionOptionList.add((Question_optionEntity) excelMap.get("questionOption"));
                }
            }
            try{
                //保存试卷相关对象信息
                paperStrucMapper.batchInsert(papeStrucureList);
                paperQuestionMapper.batchInsert(paperQuestionList);

                //保存题目相关对象
                questionMapper.insertList(questionList);
                questionReadingMapper.insertList(questionReadingList);
                questionItemMapper.insertList(questionItemList);
                questoinOptionMapper.insertList(questionOptionList);
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

    //解析真题阅读
    //读取Excel的内容
    private Map<String, Object> readReadExcel( HSSFWorkbook book, Map<String, String> paramMap) {

        String code="0";
        String message="导入成功";
        Integer sectionCode = 0;
        String questionId = "";
        String itemId = "";
        String Node1Id="";
        String Node1Id2="";
        String Node1Id3="";
        Integer sort=0;
        Integer ptionSort=0;
        Integer startRow=Integer.valueOf(paramMap.get("startRow"));
        //得到试卷信息并保持到PaperEntity（试卷）对象中
        List<Map<String, Object>> excelList = new ArrayList<Map<String, Object>>();
        Map<String,Object> returnMap = new HashMap<String,Object>();
        int sheetNumb = book.getNumberOfSheets();
        for (int i = 0; i < sheetNumb; i++) {
            String name= book.getSheetName(i);
            HSSFSheet sheet = book.getSheet(name);
            int rowNum = sheet.getLastRowNum() + 1;
            for (int k = startRow; k < rowNum; k++) {
                Map<String, Object> excelMap1 = new HashMap<String, Object>();
                Map<String, Object> excelMap = new HashMap<String, Object>();
                HSSFRow row = sheet.getRow(k);
                int cellNum = 1;
                HSSFCell cell0 = row.getCell(0);
                try{
                    if (cell0 !=null &&cell0.getCellType() != HSSFCell.CELL_TYPE_BLANK) {
                        sectionCode = (Integer) verifyData(cell0, k,cellNum,1);//获取学段
                    }

                    cellNum++;
                    HSSFCell cell1 = row.getCell(1);
                    String papername = (String) verifyData(cell1, k,cellNum,0);////试卷名称

                    cellNum++;
                    HSSFCell cell2 = row.getCell(2);
                    String structureNameNode1 = (String) verifyData(cell2, k,cellNum,0);//试卷树形结构一级节点名称（PartIII）

                    cellNum++;
                    HSSFCell cell3 = row.getCell(3);
                    String structureNameNode2 = (String) verifyData(cell3, k,cellNum,0); //试卷树形结构节点名称（二级）（Section A，Section B）

                    cellNum++;
                    HSSFCell cell4 = row.getCell(4);
                    String Node12Alias = (String) verifyData(cell3, k,cellNum,0); //试卷树形结构二级节点描述

                    cellNum++;
                    HSSFCell cell5 = row.getCell(5);
                    String structureNameNode3 = (String) verifyData(cell5, k,cellNum,0);//试卷树形结构三级节点的名称（Passage One、Passage Two）

                    cellNum++;
                    HSSFCell cell6 = row.getCell(6);
                    String questionName = (String) verifyData(cell6, k,cellNum,0); //题目名称

                    cellNum++;
                    HSSFCell cell7 = row.getCell(7);
                    String questioncontent = (String) verifyData(cell7, k,cellNum,0); //题干

                    cellNum++;
                    HSSFCell cell8 = row.getCell(8);
                    String itemAnalysis = (String) verifyData(cell8, k,cellNum,0);//解析（对小题的解析）

                    cellNum++;
                    HSSFCell cell9 = row.getCell(9);
                    String readingTranslation = (String) verifyData(cell9, k,cellNum,0);//译文

                    cellNum++;
                    HSSFCell cell10 = row.getCell(10);
                    String itemContent = (String) verifyData(cell10, k,cellNum,0);//小题内容

                    cellNum++;
                    HSSFCell cell11 = row.getCell(11);
                    Integer Optionsort = (Integer) verifyData(cell11, k,cellNum,1);//题号

                    cellNum++;
                    HSSFCell cell12 = row.getCell(12);
                    String optionName = (String) verifyData(cell12, k,cellNum,0);//选项名称(A B C D)

                    cellNum++;
                    HSSFCell cell13 = row.getCell(13);
                    String optionContent = (String) verifyData(cell13, k,cellNum,0);//选项内容

                    cellNum++;
                    HSSFCell cell14 = row.getCell(14);
                    Integer isAnswer = (Integer) verifyData(cell14, k,cellNum,1);//是否正确答案（0否 1是）

                    try{
                        //创建试卷树形结构对象(父节点)
                        if (structureNameNode1 != null && !structureNameNode1.equals("")) {
                            //一级目录
                            PaperStructureEntity paperStructureNode1 = new PaperStructureEntity();
                            paperStructureNode1.preInsert();
                            Node1Id = paperStructureNode1.getId();
                            paperStructureNode1.setPaperId(paramMap.get("paperId"));
                            paperStructureNode1.setName(structureNameNode1);
                            paperStructureNode1.setContentType(Integer.valueOf(QuestionContentType.CONTENT_READING));//题目类型（阅读）
                            // paperStructureNode1.setContent(Node1Alias);//节点描述
                            paperStructureNode1.setAlias(structureNameNode1);//节点别名
                            paperStructureNode1.setParentId("0");//父类id
                            paperStructureNode1.setParentIds("0");//父类的所有id
                            paperStructureNode1.setLevel(1);//级别
                            paperStructureNode1.setIsLeaf(0);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                            paperStructureNode1.setSort(1);
                            paperStructureNode1.setRemarks(structureNameNode1);
                            excelMap.put("paperStructureNode1", paperStructureNode1);

                        }
                        if (structureNameNode2 != null && !structureNameNode2.equals("")) {
                            //二级节点
                            PaperStructureEntity paperStructureNode2 = new PaperStructureEntity();
                            paperStructureNode2.preInsert();
                            Node1Id2 = paperStructureNode2.getId();//试卷树形结构id
                            paperStructureNode2.setPaperId(paramMap.get("paperId"));//试卷id
                            paperStructureNode2.setName(structureNameNode2);//试卷树形结构名称
                            paperStructureNode2.setContent(Node12Alias);//描述
                            paperStructureNode2.setContentType(Integer.valueOf(QuestionContentType.CONTENT_READING));//题目类型（阅读）
                            paperStructureNode2.setAlias(structureNameNode2);//别名
                            paperStructureNode2.setParentId(Node1Id);//父亲id。set
                            paperStructureNode2.setParentIds("0,"+Node1Id);//所有父类id
                            paperStructureNode2.setLevel(2);//级别
                            paperStructureNode2.setSort(2);
                            if(structureNameNode3 != null && !structureNameNode3.equals("") ){
                                paperStructureNode2.setIsLeaf(0);//不是叶子节点
                            }else {
                                paperStructureNode2.setIsLeaf(1);//是叶子节点
                            }
                            excelMap.put("paperStructureNode2", paperStructureNode2);

                        }
                        if(structureNameNode3 != null && !structureNameNode3.equals("") ){
                            //三级目录
                            PaperStructureEntity paperStructureNode3 = new PaperStructureEntity();
                            paperStructureNode3.preInsert();
                            Node1Id3=paperStructureNode3.getId();
                            paperStructureNode3.setPaperId(paramMap.get("paperId"));
                            paperStructureNode3.setName(structureNameNode3);
                            paperStructureNode3.setContentType(Integer.valueOf(QuestionContentType.CONTENT_READING));//阅读
                            paperStructureNode3.setAlias(structureNameNode3);
                            paperStructureNode3.setParentId(Node1Id2);
                            paperStructureNode3.setParentIds("0,"+Node1Id+","+Node1Id2);
                            paperStructureNode3.setLevel(3);
                            paperStructureNode3.setIsLeaf(1);
                            paperStructureNode3.setSort(3);
                            // paperStructureNode3.setContent(structureNameNode3);
                            excelMap.put("paperStructureNode3", paperStructureNode3);
                        }else{
                            Node1Id3=Node1Id2;
                        }

                        //给bean对象赋值
                        if (questionName != null && !questionName.equals("")) {
                            QuestionEntity question = new QuestionEntity();
                            question.preInsert();
                            questionId = question.getId();
                            question.setName(questionName);//题目名称
                            question.setType(Integer.valueOf(QuestionContentType.CONTENT_READING));//阅读
                            question.setContent(questioncontent);//题干
                            question.setSectionCode(sectionCode);//学段
                            question.setAnalysis(itemAnalysis);//解析
                            //判断是否存在小题
                            if (itemContent != null && !itemContent.equals("")) {
                                question.setHasItem(1);//存在小提
                            } else {
                                question.setHasItem(0); //不存在
                            }

                            //创建试卷题目关系对象
                            PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                            paperQuestion.preInsert();
                            paperQuestion.setQuestionId(questionId);//添加题目id
                            paperQuestion.setPaperId(paramMap.get("paperId"));//添加试卷id
                            paperQuestion.setStructureId(Node1Id3);
                            paperQuestion.setSort(sort);
                            excelMap.put("question", question);
                            excelMap.put("paperQuestion", paperQuestion);
                            sort++;

                        }
                        //创建阅读对象
                        if(readingTranslation !=null && !readingTranslation.equals("")){
                            Question_readingEntity questionRead =new Question_readingEntity();//readingTranslation
                            questionRead.preInsert();
                            questionRead.setQuestionId(questionId);
                            questionRead.setTranslation(readingTranslation);
                            excelMap.put("questionRead", questionRead);
                        }
                        //小题
                        if (itemContent != null && !itemContent.equals("")) {
                            Question_itemEntity questionItem = new Question_itemEntity();
                            questionItem.preInsert();
                            itemId = questionItem.getId();
                            questionItem.setContent(itemContent);
                            questionItem.setSort(Optionsort);
                            questionItem.setQuestionId(questionId);
                            //questiomitem.setAnalysis();
                            excelMap.put("questionItem", questionItem);
                        }
                        if (isAnswer != null && !isAnswer.equals("")) {
                            Question_optionEntity questionOption = new Question_optionEntity();
                            questionOption.preInsert();
                            questionOption.setItemId(itemId);//问题id
                            questionOption.setContent(optionContent);//选项内容
                            questionOption.setName(optionName);
                            questionOption.setIsAnswer(isAnswer);//是否为正确答案
                            questionOption.setSort(ptionSort);
                            excelMap.put("questionOption", questionOption);
                        }
                        if(excelMap !=null && !excelMap.equals(""))
                            excelList.add(excelMap);

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

    public Map<String,String> insertWriteExcel(HSSFWorkbook book, Map<String, String> paramMap) {
        String code="";
        String message="";
        Map<String,String> returnMap = new HashMap<String,String>();
        Map<String, Object> dataMap = readWritingExcel(book, paramMap);
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
        List<Map<String,Object>> excelList = (List<Map<String,Object>>) dataMap.get("excelList");
        if (excelList != null) {
            //得到题目信息，并保存到 QuestionEntity（题目）对象
            List<QuestionEntity> questionList = new ArrayList<QuestionEntity>();
            List<PaperQuestionEntity> paperQuestionList = new ArrayList<PaperQuestionEntity>();
            List<PaperStructureEntity> papeStrucureList = new ArrayList<PaperStructureEntity>();
            List<Question_writingEntity> questionWritingList = new ArrayList<Question_writingEntity>();
            for (int i = 0; i < excelList.size(); i++) {
                Map<String, Object> excelMap = new HashMap<String, Object>();
                excelMap = excelList.get(i);
                if (excelMap.containsKey("question")) {
                    questionList.add((QuestionEntity) excelMap.get("question"));
                }
                if (excelMap.containsKey("paperQuestion")) {
                    paperQuestionList.add((PaperQuestionEntity) excelMap.get("paperQuestion"));
                }
                if (excelMap.containsKey("paperStructureNode1")) {
                    papeStrucureList.add((PaperStructureEntity) excelMap.get("paperStructureNode1"));
                }
                if (excelMap.containsKey("questionWriting")) {
                    questionWritingList.add((Question_writingEntity) excelMap.get("questionWriting"));
                }
            }
            try{
                //保存试卷相关对象信息
                paperStrucMapper.batchInsert(papeStrucureList);
                paperQuestionMapper.batchInsert(paperQuestionList);

                //保存题目相关对象
                questionMapper.insertList(questionList);
                questionWritingMapper.insertList(questionWritingList);
                returnMap.put("code","0");
                returnMap.put("message","导入成功");
            }catch (Exception e){
                e.printStackTrace();
                returnMap.put("code","902");
                returnMap.put("message","数据库存储数据失败");
                return returnMap;
            }
        }else {
            returnMap.put("code","902");
            returnMap.put("message","Excel的数据为空");

        }
        return returnMap;
    }

    //解析写作
    private Map<String,Object> readWritingExcel (HSSFWorkbook book, Map<String, String> paramMap) {
        Integer sectionCode = 0;
        String questionId = "";
        String Node1Id="";
        Integer sort=0;
        String message="导入成功";
        String code="0";
        Integer startRow=Integer.valueOf(paramMap.get("startRow"));
        //得到试卷信息并保持到PaperEntity（试卷）对象中
        List<Map<String, Object>> excelList = new ArrayList<Map<String, Object>>();
        Map<String,Object> returnMap =new HashMap<String,Object>();
        int sheetNumb = book.getNumberOfSheets();
        for(int i=0;i<sheetNumb ;i++){
            String name= book.getSheetName(i);
            HSSFSheet sheet = book.getSheet(name);
            int rowNum = sheet.getLastRowNum() + 1;
            for (int k = startRow; k < rowNum; k++) {
                Map<String, Object> excelMap = new HashMap<String, Object>();
                HSSFRow row = sheet.getRow(k);
                int cellNum = 1;
                HSSFCell cell0 = row.getCell(0);
                try {
                    if (cell0 !=null && cell0.getCellType() != HSSFCell.CELL_TYPE_BLANK) {
                        sectionCode = (Integer) verifyData(cell0, k,cellNum,1);//获取学段
                    }

                    cellNum++;
                    HSSFCell cell1 = row.getCell(1);
                    String papername = (String) verifyData(cell1, k,cellNum,0);////试卷名称

                    cellNum++;
                    HSSFCell cell2 = row.getCell(2);
                    String structureNameNode1 = (String) verifyData(cell2, k,cellNum,0);//内容类型（4.写作）(一级)

                    cellNum++;
                    HSSFCell cell3 = row.getCell(3);
                    String questioncontent = (String) verifyData(cell3, k,cellNum,0); //节描述

                    cellNum++;
                    HSSFCell cell4 = row.getCell(4);
                    String reference = (String) verifyData(cell4, k,cellNum,0); //参考范文

                    cellNum++;
                    HSSFCell cell5 = row.getCell(5);
                    String analysisCcId = (String) verifyData(cell5, k,cellNum,0); //视频讲解ccid

                    cellNum++;
                    HSSFCell cell6 = row.getCell(6);
                    String questionanalysis = (String) verifyData(cell6, k,cellNum,0); // 解析

                    try{
                        //建立父节点关系对象
                        if(structureNameNode1 !=null && !structureNameNode1.equals("") ){
                            //一级目录
                            PaperStructureEntity paperStructureNode1 = new PaperStructureEntity();
                            paperStructureNode1.preInsert();
                            Node1Id = paperStructureNode1.getId();
                            paperStructureNode1.setPaperId(paramMap.get("paperId"));
                            paperStructureNode1.setName(structureNameNode1);
                            paperStructureNode1.setContentType(Integer.valueOf(QuestionContentType.CONTENT_WRITING));//题目类型（听力）
                            paperStructureNode1.setAlias(structureNameNode1);//节点别名
                            paperStructureNode1.setParentId("0");//父类id
                            paperStructureNode1.setParentIds("0");//父类的所有id
                            paperStructureNode1.setLevel(1);//级别
                            paperStructureNode1.setIsLeaf(1);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                            paperStructureNode1.setSort(1);
                            paperStructureNode1.setRemarks(structureNameNode1);
                            excelMap.put("paperStructureNode1", paperStructureNode1);
                        }
                        if(questioncontent != null && !questioncontent.equals("")){
                            //创建题目对象
                            QuestionEntity question = new QuestionEntity();
                            question.preInsert();
                            question.setHasItem(0);//
                            questionId = question.getId();
                            question.setName("写作");//题目名称
                            question.setType(Integer.valueOf(QuestionContentType.CONTENT_WRITING));//写作
                            question.setContent(questioncontent);//题干
                            question.setSectionCode(sectionCode);//学段
                            question.setAnalysis(questionanalysis);//解析

                            //创建试卷题目关系对象
                            PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                            paperQuestion.preInsert();
                            paperQuestion.setQuestionId(questionId);//添加题目id
                            paperQuestion.setPaperId(paramMap.get("paperId"));//添加试卷id
                            paperQuestion.setStructureId(Node1Id);
                            paperQuestion.setSort(sort);
                            sort++;
                            excelMap.put("question", question);
                            excelMap.put("paperQuestion", paperQuestion);
                        }
                        if((reference !=null && !reference.equals(""))||( analysisCcId != null &&  !analysisCcId.equals(""))){
                            //创建写作信息对象
                            Question_writingEntity questionWriting =new Question_writingEntity();
                            questionWriting.preInsert();
                            questionWriting.setQuestionId(questionId);
                            questionWriting.setReference(reference);
                            questionWriting.setAnalysisCcId(analysisCcId);
                            excelMap.put("questionWriting",questionWriting);
                        }
                        if(excelMap !=null && !excelMap.equals(""))
                            excelList.add(excelMap);
                    }catch (Exception e){
                        code="902";
                        message="数据创建对象出现错误";
                        returnMap.put("code",code);
                        returnMap.put("message",message);
                        return returnMap;
                    }
                }catch (Exception e){
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

    public Map<String,String> insertTranslationExcel(HSSFWorkbook book, Map<String, String> paramMap) {

        Map<String, Object> dataMap = readTranslationExcel(book, paramMap);
        Map<String,String> returnMap = new HashMap<String,String>();
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
        List<Map<String,Object>> excelList =(List<Map<String,Object>>) dataMap.get("excelList");
        if (excelList != null) {
            //得到题目信息，并保存到 QuestionEntity（题目）对象
            List<QuestionEntity> questionList = new ArrayList<QuestionEntity>();
            List<PaperQuestionEntity> paperQuestionList = new ArrayList<PaperQuestionEntity>();
            List<PaperStructureEntity> papeStrucureList = new ArrayList<PaperStructureEntity>();
            List<Question_translationEntity> questiontranslationList = new ArrayList<Question_translationEntity>();
            for (int i = 0; i < excelList.size(); i++) {
                Map<String, Object> excelMap = new HashMap<String, Object>();
                excelMap = excelList.get(i);
                if (excelMap.containsKey("question")) {
                    questionList.add((QuestionEntity) excelMap.get("question"));
                }
                if (excelMap.containsKey("paperQuestion")) {
                    paperQuestionList.add((PaperQuestionEntity) excelMap.get("paperQuestion"));
                }
                if (excelMap.containsKey("paperStructureNode1")) {
                    papeStrucureList.add((PaperStructureEntity) excelMap.get("paperStructureNode1"));
                }
                if (excelMap.containsKey("questionTranslation")) {
                    questiontranslationList.add((Question_translationEntity) excelMap.get("questionTranslation"));
                }
            }
            try{
                //保存试卷相关对象信息
                paperStrucMapper.batchInsert(papeStrucureList);
                paperQuestionMapper.batchInsert(paperQuestionList);

                //保存题目相关对象
                questionMapper.insertList(questionList);
                quesionTranslationMapper.insertList(questiontranslationList);
                returnMap.put("code","0");
                returnMap.put("message","导入成功");
            }catch (Exception e){
                e.printStackTrace();
                returnMap.put("code","902");
                returnMap.put("message","数据库存储数据失败");
                return returnMap;
            }

        }else {
            returnMap.put("code","902");
            returnMap.put("message","Excel的数据为空");
        }
        return returnMap;
    }



    //解析翻译Excel
    private Map<String,Object> readTranslationExcel (HSSFWorkbook book, Map<String, String> paramMap){
        Integer sectionCode = 0;
        String questionId = "";
        String Node1Id="";
        Integer sort=0;
        String code="0";
        String message="导入成功";
        Integer startRow=Integer.valueOf(paramMap.get("startRow"));
        //得到试卷信息并保持到PaperEntity（试卷）对象中
        Map<String,Object> returnMap =new HashMap<String,Object>();
        List<Map<String, Object>> excelList = new ArrayList<Map<String, Object>>();
        int sheetNumb = book.getNumberOfSheets();
        for(int i=0; i<sheetNumb;i++){
            String name=book.getSheetName(i);
            HSSFSheet sheet = book.getSheet(name);
            int rowNum = sheet.getLastRowNum() + 1;
            for(int k=startRow; k<rowNum ; k++){
                Map<String, Object> excelMap = new HashMap<String, Object>();
                HSSFRow row = sheet.getRow(k);
                int cellNum = 1;
                HSSFCell cell0 = row.getCell(0);
                try {
                    if (cell0 !=null && cell0.getCellType() != HSSFCell.CELL_TYPE_BLANK) {
                        sectionCode = (Integer) verifyData(cell0, k,cellNum,1);//获取学段
                    }
                    cellNum++;
                    HSSFCell cell1 = row.getCell(1);
                    String papername = (String) verifyData(cell1, k,cellNum,0);////试卷名称

                    cellNum++;
                    HSSFCell cell2 = row.getCell(2);
                    String structureNameNode1 = (String) verifyData(cell2, k,cellNum,0);//内容类型（0.不区分1.听力2.阅读3.翻译4.写作;默认0）(一级)

                    cellNum++;
                    HSSFCell cell3 = row.getCell(3);
                    String questioncontent = (String) verifyData(cell3, k,cellNum,0); //节描述

                    cellNum++;
                    HSSFCell cell4 = row.getCell(4);
                    String reference = (String) verifyData(cell4, k,cellNum,0); //参考范文

                    cellNum++;
                    HSSFCell cell5 = row.getCell(5);
                    String analysisCcId = (String) verifyData(cell5, k,cellNum,0); //视频讲解ccid

                    cellNum++;
                    HSSFCell cell6 = row.getCell(6);
                    String questionanalysis = (String) verifyData(cell6, k,cellNum,0); // 解析
                    try{

                        //建立父节点关系对象
                        if(structureNameNode1 !=null && !structureNameNode1.equals("") ){

                            //一级目录
                            PaperStructureEntity paperStructureNode1 = new PaperStructureEntity();
                            paperStructureNode1.preInsert();
                            Node1Id = paperStructureNode1.getId();
                            paperStructureNode1.setPaperId(paramMap.get("paperId"));
                            paperStructureNode1.setName(structureNameNode1);
                            paperStructureNode1.setContentType(Integer.valueOf(QuestionContentType.CONTENT_TRANSLATION));//题目类型（翻译）
                            paperStructureNode1.setAlias(structureNameNode1);//节点别名
                            paperStructureNode1.setParentId("0");//父类id
                            paperStructureNode1.setParentIds("0");//父类的所有id
                            paperStructureNode1.setLevel(1);//级别
                            paperStructureNode1.setIsLeaf(1);//是否是叶子节点（0.否 1.是)(叶子节点才会存在题目）
                            paperStructureNode1.setSort(1);
                            paperStructureNode1.setRemarks(structureNameNode1);
                            excelMap.put("paperStructureNode1", paperStructureNode1);


                        }
                        if(questioncontent != null && !questioncontent.equals("")){
                            //创建题目对象
                            QuestionEntity question = new QuestionEntity();
                            question.preInsert();
                            question.setHasItem(0);//
                            questionId = question.getId();
                            question.setName("写作");//题目名称
                            question.setType(Integer.valueOf(QuestionContentType.CONTENT_TRANSLATION));//阅读
                            question.setContent(questioncontent);//题干
                            question.setSectionCode(sectionCode);//学段
                            question.setAnalysis(questionanalysis);//解析

                            //创建试卷题目关系对象
                            PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                            paperQuestion.preInsert();
                            paperQuestion.setQuestionId(questionId);//添加题目id
                            paperQuestion.setPaperId(paramMap.get("paperId"));//添加试卷id
                            paperQuestion.setStructureId(Node1Id);
                            paperQuestion.setSort(sort);
                            excelMap.put("paperQuestion",paperQuestion);
                            excelMap.put("question",question);
                        }
                        if((reference !=null && !reference.equals("") )|| (analysisCcId != null && !analysisCcId.equals(""))){
                            Question_translationEntity questionTranslation =new Question_translationEntity();
                            questionTranslation.preInsert();
                            questionTranslation.setQuestionId(questionId);
                            questionTranslation.setReference(reference);
                            questionTranslation.setAnalysisCcId(questionanalysis);
                            excelMap.put("questionTranslation",questionTranslation);

                        }
                        if(excelMap !=null && !excelMap.equals(""))
                            excelList.add(excelMap);

                    }catch (Exception e){
                        e.printStackTrace();
                        code="902";
                        message="数据创建对象出现错误";
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
}
