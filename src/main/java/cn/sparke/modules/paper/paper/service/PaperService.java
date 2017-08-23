package cn.sparke.modules.paper.paper.service;

import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.common.utils.Convert;
import cn.sparke.core.common.utils.ExcelUtil;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.paper.paper.mapper.PaperMapper;
import cn.sparke.modules.paper.paperCatalog.entity.PaperCatalogEntity;
import cn.sparke.modules.paper.paperCatalog.mapper.PaperCatalogMapper;
import cn.sparke.modules.paper.paperGroup.entity.PaperGroupEntity;
import cn.sparke.modules.paper.paperGroup.mapper.PaperGroupMapper;
import cn.sparke.modules.paper.paperGroupRelation.entity.PaperGroupRelationEntity;
import cn.sparke.modules.paper.paperGroupRelation.mapper.PaperGroupRelationMapper;
import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
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
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 试卷Dao
 *
 * @author spark
 * @Date 2017-07-19 09:50:52
 */
@Service
public class PaperService{
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private PaperCatalogMapper paperCatalogMapper;
    @Autowired
    private PaperStructureMapper paperStructureMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private Question_itemMapper questionItemMapper;
    @Autowired
    private Question_optionMapper questionOptionMapper;
    @Autowired
    private Question_listeningMapper questionListeningMapper;
    @Autowired
    private Question_readingMapper questionReadingMapper;
    @Autowired
    private Question_translationMapper questionTranslationMapper;
    @Autowired
    private Question_writingMapper questionWritingMapper;
    @Autowired
    private PaperGroupRelationMapper paperGroupRelationMapper;
    @Autowired
    private PaperGroupMapper paperGroupMapper;

    /**
     * 新增试卷
     * @param paper
     */
    @Transactional
    public void save(PaperEntity paper){
        paper.preInsert();
        //关联试卷组
        if (ToolUtil.isNotEmpty(paper.getGroupIds())) {
            List<String> groupIdList = Arrays.asList(paper.getGroupIds().split(","));
            List<PaperGroupRelationEntity> grList = groupIdList.stream().map(groupId -> {
                PaperGroupRelationEntity gr = new PaperGroupRelationEntity();
                gr.setPaperId(paper.getId());
                gr.setSort(0);
                gr.setGroupId(groupId);
                gr.preInsert();
                return gr;
            }).collect(Collectors.toList());
            paperGroupRelationMapper.batchInsert(grList);
            paper.setCatalogId("FFFFFF");
        }
        paperMapper.insert(paper);
    }

    /**
     * 修改试卷
     * @param paper
     */
    @Transactional
    public void update(PaperEntity paper){
        paper.preUpdate();
        //关联试卷组
        if (ToolUtil.isNotEmpty(paper.getGroupIds())) {
            //删除试卷组关联
            paperGroupRelationMapper.deleteByPaperId(paper.getId());

            List<String> groupIdList = Arrays.asList(paper.getGroupIds().split(","));
            List<PaperGroupRelationEntity> grList = groupIdList.stream().map(groupId -> {
                PaperGroupRelationEntity gr = new PaperGroupRelationEntity();
                gr.setPaperId(paper.getId());
                gr.setSort(0);
                gr.setGroupId(groupId);
                gr.preInsert();
                return gr;
            }).collect(Collectors.toList());
            paperGroupRelationMapper.batchInsert(grList);
            paper.setCatalogId("FFFFFF");
        }
        paperMapper.update(paper);
    }

    public PaperEntity get(PaperEntity paper){
        return paperMapper.get(paper);
    }

    /**
     * 获取试卷
     * @param id
     * @return
     */
    public PaperEntity getById(String id){
        PaperEntity entity = paperMapper.getById(id);
        if (ToolUtil.isNotEmpty(entity)) {
            PaperCatalogEntity paperCatalog = paperCatalogMapper.getById(entity.getCatalogId());
            String groupIds = StringUtils.EMPTY;
            if (ToolUtil.isEmpty(paperCatalog)) {//字幕听力、扫码字幕听力
                //获取所有试卷组
                PaperGroupRelationEntity pgr = new PaperGroupRelationEntity();
                pgr.setPaperId(entity.getId());
                List<PaperGroupRelationEntity> list = paperGroupRelationMapper.findList(pgr);
                if (ToolUtil.isNotEmpty(list)) {
                    groupIds = list.stream().map(paperGroupRelationEntity -> paperGroupRelationEntity.getGroupId()).collect(Collectors.joining(","));
                    entity.setGroupIds(groupIds);
                    PaperGroupEntity paperGroup = paperGroupMapper.getById(list.get(0).getGroupId());
                    paperCatalog = paperCatalogMapper.getById(paperGroup.getCatalogId());
                }
            } else {
                entity.setCatalogName(paperCatalog.getName());
            }
            entity.setGroupIds(groupIds);
            entity.setCatalogType(ToolUtil.isEmpty(paperCatalog)? 2 : paperCatalog.getType());
        }
        return entity;
    }

    /**
     * 试卷列表
     * @param paper
     * @return
     */
    public Page<PaperEntity> findList(PaperEntity paper){
        //查询所有字幕听力和扫码字幕听力指定目录下试卷
        if (ToolUtil.isNotEmpty(paper.getCatalogId())) {
            //获取目录
            PaperCatalogEntity paperCatalog = paperCatalogMapper.getById(paper.getCatalogId());
            if (ToolUtil.isNotEmpty(paperCatalog)) {
                if (paperCatalog.getType() == 2 || paperCatalog.getType() == 5) {
                    return paperMapper.list(paper);
                }
            }
        }
        //查询所有字幕听力和扫码字幕听力试卷
        if (ToolUtil.isNotEmpty(paper.getCatalogType()) && 25 == paper.getCatalogType()) {
//            List<String> typeList = new ArrayList<>();
//            typeList.add("2");
//            typeList.add("5");
//            PaperCatalogEntity catalog = new PaperCatalogEntity();
//            catalog.setTypeList(typeList);
//            List<PaperCatalogEntity> catalogList = paperCatalogMapper.findList(catalog);
//            if (ToolUtil.isNotEmpty(catalogList)) {
//                List<String> catalogIdList = catalogList.stream().map(paperCatalogEntity -> paperCatalogEntity.getId())
//                        .collect(Collectors.toList());
//                PaperEntity qPaper = new PaperEntity();
//                qPaper.setCatalogIdList(catalogIdList);
//                return paperMapper.findList(qPaper);
//            }
            paper.setCatalogId("FFFFFF");
            return paperMapper.findList(paper);
        }

        if (ToolUtil.isNotEmpty(paper.getCatalogType()) && 6 == paper.getCatalogType()) {
            return paperMapper.queryList(paper);
        }
       return paperMapper.findList(paper);
    }

    public void deleteById(String id){
        paperMapper.deleteById(id);
    }

    /**
     * 模板下载
     * @param request
     * @param response
     */
    public void template(String type,HttpServletRequest request, HttpServletResponse response){
        HSSFWorkbook wb = null;
        try {
            ServletOutputStream out = response.getOutputStream();
            String fileName = StringUtils.EMPTY;
            String filePath = StringUtils.EMPTY;
            switch (type){
                case "1" : {
                    fileName = "专项练习-听力模板.xls";
                    filePath = "classpath:template/paperExcel/special_practice_listening.xls";
                    break;
                }
                case "2" : {
                    fileName = "专项练习-阅读模板.xls";
                    filePath = "classpath:template/paperExcel/special_practice_reading.xls";
                    break;
                }
                case "3" : {
                    fileName = "专项练习-写作模板.xls";
                    filePath = "classpath:template/paperExcel/special_practice_writing.xls";
                    break;
                }
                case "4" : {
                    fileName = "专项练习-翻译模板.xls";
                    filePath = "classpath:template/paperExcel/special_practice_translation.xls";
                    break;
                }
                case "5" : {
                    fileName = "简系列-听力模板.xls";
                    filePath = "classpath:template/paperExcel/easy_listening.xls";
                    break;
                }
            }
            String userAgent = request.getHeader("User-Agent");

            response.setContentType("multipart/form-data");
            byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题
            fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));

            File file = ResourceUtils.getFile(filePath);
            wb = new HSSFWorkbook(new FileInputStream(file));
            wb.write(out);

        } catch (Exception e) {
            throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_TEMPLATE_ERROR);
        } finally {
            try {
                if (wb != null) {
                    wb.close();
                }
            } catch (IOException e) {
                throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_TEMPLATE_ERROR);
            }
        }
    }

    /**
     * 模板导入
     * @param paperId
     * @param file
     */
    @Transactional
    public void upload(String paperId,MultipartFile file){
        //文件校验
        if (file.isEmpty())
            throw new BussinessException(BizExceptionEnum.FILE_NOT_FOUND);
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (!"xls".equals(suffix) && !"XLS".equals(suffix))
            throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_VERSION_ERROR);

        try {
            //获取试卷
            PaperEntity paper = paperMapper.getById(paperId);
            if (null == paper)
                throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_PAPER_NOT_FOUND);
            //获取目录
            PaperCatalogEntity paperCatalog = paperCatalogMapper.getById(paper.getCatalogId());

            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            Row headRow = sheet.getRow(1);
            //数据开始行
            int dataStartRow = 2;
            //获取总行数
            int rowCount = sheet.getLastRowNum();

            if (rowCount < dataStartRow) //无数据
                throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_EMPTY);

            if (paperCatalog.getType() == 4) {//专项练习
                //表头校验
                int cellNum = checkSpecialHead(headRow,paper.getContentType());

                //区分1.听力2.阅读3.翻译4.写作
                switch (paper.getContentType()) {
                    case 1: {//听力
                        uploadSpecialListening(paper, paperCatalog, sheet, dataStartRow, rowCount);
                        break;
                    }
                    case 2: {//阅读
                        uploadSpecialReading(paper, paperCatalog, sheet, dataStartRow, rowCount);
                        break;
                    }
                    case 3: {//翻译
                        uploadSpecialTranslation(paper, paperCatalog, sheet, dataStartRow, rowCount);
                        break;
                    }
                    case 4: {//写作
                        uploadSpecialWriting(paper, paperCatalog, sheet, dataStartRow, rowCount);
                        break;
                    }
                }

                //刷新题目数量
                refSpeciaQuestionCountById(paper.getId());
            } else if (paperCatalog.getType() == 3) {//简系列
                //表头校验
                int cellNum = checkEasyHead(headRow,paper.getContentType());

                if (paper.getContentType() == 1) {//听力
                    uploadEasyListening(paper, paperCatalog, sheet, dataStartRow, rowCount);
                } else {
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_EASY_TYPE);
                }
            }
        } catch (BussinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_UPLOAD_ERROR);
        }
    }

    /**
     * 专项练习-听力上传解析
     * @param paper
     * @param paperCatalog
     * @param sheet
     * @param dataStartRow
     * @param rowCount
     */
    private void uploadSpecialListening(PaperEntity paper,PaperCatalogEntity paperCatalog,Sheet sheet,int dataStartRow,int rowCount) {
        //实体模型
        PaperStructureEntity paperStructure = null;
        QuestionEntity question = null;
        Question_itemEntity questionItem = null;
        Question_optionEntity questionOption = null;
        //数据集合
        List<PaperStructureEntity> paperStructureList = new ArrayList<>();
        List<PaperQuestionEntity> paperQuestionList = new ArrayList<>();
        List<QuestionEntity> questionList = new ArrayList<>();
        List<Question_itemEntity> questionItemList = new ArrayList<>();
        List<Question_optionEntity> questionOptionList = new ArrayList<>();
        List<Question_listeningEntity> questionListeningList = new ArrayList<>();

        for (int i = dataStartRow; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (!ExcelUtil.isRowEmpty(row)) {

                //校验学段
                String sectionCode = ExcelUtil.getStringValue(row.getCell(0));
                if (ToolUtil.isNotEmpty(sectionCode) && !sectionCode.equals(paperCatalog.getSectionCode().toString()))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_SECTION_CODE_ERROR);

                //目录结构
                String structureName = ExcelUtil.getStringValue(row.getCell(1));
                if (ToolUtil.isNotEmpty(structureName)) {
                    PaperStructureEntity entity = new PaperStructureEntity();
                    entity.setName(structureName);
                    entity.setPaperId(paper.getId());
                    entity = paperStructureMapper.get(entity);
                    if (null != entity)
                        paperStructure = entity;
                    else {
                        paperStructure = new PaperStructureEntity();
                        paperStructure.preInsert();
                        paperStructure.setPaperId(paper.getId());
                        paperStructure.setName(structureName);
                        paperStructure.setContentType(paper.getContentType());
                        paperStructure.setAlias(structureName);
                        paperStructure.setParentId("0");
                        paperStructure.setParentIds("0");
                        paperStructure.setLevel(1);
                        paperStructure.setIsLeaf(1);
                        Integer sort = ExcelUtil.getIntegerValue(row.getCell(2));
                        paperStructure.setSort(null == sort ? 0 : sort);
                        paperStructureList.add(paperStructure);
                    }
                }
                //题目
                String questionName = ExcelUtil.getStringValue(row.getCell(3));
                if (ToolUtil.isNotEmpty(questionName)) {
                    question = new QuestionEntity();
                    question.preInsert();
                    question.setName(questionName);
                    question.setContent(ExcelUtil.getStringValue(row.getCell(4)));
                    question.setType(paper.getContentType());
                    Integer questionNum = ExcelUtil.getIntegerValue(row.getCell(8));
                    if (null == questionNum || questionNum == 0)
                        throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_ITEM_NOT_FOUND);
                    question.setHasItem(null == questionNum ? 0:1);
                    question.setQuestionNum(null == questionNum ? 0:questionNum);
                    question.setSectionCode(paperCatalog.getSectionCode());
                    questionList.add(question);
                    //听力题
                    Question_listeningEntity questionListening = new Question_listeningEntity();
                    questionListening.preInsert();
                    questionListening.setQuestionId(question.getId());
                    questionListening.setAudioUrl(ExcelUtil.getStringValue(row.getCell(5)));
                    questionListening.setTapescripts(ExcelUtil.getStringValue(row.getCell(6)));
                    questionListening.setTranslation(ExcelUtil.getStringValue(row.getCell(7)));
                    questionListening.setSubtitleUrl("");
                    questionListeningList.add(questionListening);
                    //试卷题目关系
                    PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                    paperQuestion.preInsert();
                    paperQuestion.setPaperId(paper.getId());
                    paperQuestion.setStructureId(paperStructure.getId());
                    paperQuestion.setQuestionId(question.getId());
                    paperQuestion.setSort(0);
                    paperQuestionList.add(paperQuestion);
                }
                //小题
                String itemName = ExcelUtil.getStringValue(row.getCell(9));
                if (ToolUtil.isNotEmpty(itemName)) {
                    questionItem = new Question_itemEntity();
                    questionItem.preInsert();
                    questionItem.setQuestionId(question.getId());
                    questionItem.setContent(itemName);
                    questionItem.setAnalysis(ExcelUtil.getStringValue(row.getCell(10)));
                    Integer sort = ExcelUtil.getIntegerValue(row.getCell(11));
                    questionItem.setSort(null == sort ? 0 : sort);
                    questionItemList.add(questionItem);
                }
                //选项
                String optionName = ExcelUtil.getStringValue(row.getCell(12));
                if (ToolUtil.isNotEmpty(optionName)) {
                    questionOption = new Question_optionEntity();
                    questionOption.preInsert();
                    questionOption.setItemId(questionItem.getId());
                    questionOption.setName(optionName);
                    questionOption.setContent(ExcelUtil.getStringValue(row.getCell(13)));
                    Integer isAnswer = ExcelUtil.getIntegerValue(row.getCell(14));
                    questionOption.setIsAnswer(null == isAnswer ? 0 : isAnswer);
                    questionOption.setSort(0);
                    questionOptionList.add(questionOption);
                }
            }
        }
        //数据获取End,持久化
        if (ToolUtil.isNotEmpty(paperStructureList))
            paperStructureMapper.batchInsert(paperStructureList);
        if (ToolUtil.isNotEmpty(paperQuestionList))
            paperQuestionMapper.batchInsert(paperQuestionList);
        if (ToolUtil.isNotEmpty(questionList))
            questionMapper.insertList(questionList);
        if (ToolUtil.isNotEmpty(questionItemList))
            questionItemMapper.insertList(questionItemList);
        if (ToolUtil.isNotEmpty(questionOptionList))
            questionOptionMapper.insertList(questionOptionList);
        if (ToolUtil.isNotEmpty(questionListeningList))
            questionListeningMapper.insertList(questionListeningList);

    }

    /**
     * 专项练习-阅读上传解析
     * @param paper
     * @param paperCatalog
     * @param sheet
     * @param dataStartRow
     * @param rowCount
     */
    private void uploadSpecialReading(PaperEntity paper,PaperCatalogEntity paperCatalog,Sheet sheet,int dataStartRow,int rowCount) {
        //数据实体
        PaperStructureEntity paperStructure = null;
        QuestionEntity question = null;
        Question_itemEntity questionItem = null;
        Question_optionEntity questionOption = null;
        //数据集合
        List<PaperStructureEntity> paperStructureList = new ArrayList<>();
        List<PaperQuestionEntity> paperQuestionList = new ArrayList<>();
        List<QuestionEntity> questionList = new ArrayList<>();
        List<Question_itemEntity> questionItemList = new ArrayList<>();
        List<Question_optionEntity> questionOptionList = new ArrayList<>();
        List<Question_readingEntity> questionReadingList = new ArrayList<>();

        for (int i = dataStartRow; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (!ExcelUtil.isRowEmpty(row)) {

                //校验学段
                String sectionCode = ExcelUtil.getStringValue(row.getCell(0));
                if (ToolUtil.isNotEmpty(sectionCode) && !sectionCode.equals(paperCatalog.getSectionCode().toString()))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_SECTION_CODE_ERROR);

                //目录结构
                String structureName = ExcelUtil.getStringValue(row.getCell(1));
                if (ToolUtil.isNotEmpty(structureName)) {
                    PaperStructureEntity entity = new PaperStructureEntity();
                    entity.setName(structureName);
                    entity.setPaperId(paper.getId());
                    entity = paperStructureMapper.get(entity);
                    if (null != entity)
                        paperStructure = entity;
                    else {
                        paperStructure = new PaperStructureEntity();
                        paperStructure.preInsert();
                        paperStructure.setPaperId(paper.getId());
                        paperStructure.setName(structureName);
                        paperStructure.setContentType(paper.getContentType());
                        paperStructure.setAlias(structureName);
                        paperStructure.setParentId("0");
                        paperStructure.setParentIds("0");
                        paperStructure.setLevel(1);
                        paperStructure.setIsLeaf(1);
                        Integer sort = ExcelUtil.getIntegerValue(row.getCell(2));
                        paperStructure.setSort(null == sort ? 0 : sort);
                        paperStructureList.add(paperStructure);
                    }
                }
                //题目
                String questionName = ExcelUtil.getStringValue(row.getCell(3));
                if (ToolUtil.isNotEmpty(questionName)) {
                    question = new QuestionEntity();
                    question.preInsert();
                    question.setName(questionName);
                    question.setContent(ExcelUtil.getStringValue(row.getCell(4)));
                    question.setType(paper.getContentType());
                    Integer questionNum = ExcelUtil.getIntegerValue(row.getCell(6));
                    if (null == questionNum || questionNum == 0)
                        throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_ITEM_NOT_FOUND);
                    question.setHasItem(null == questionNum ? 0:1);
                    question.setQuestionNum(null == questionNum ? 0:questionNum);
                    question.setSectionCode(paperCatalog.getSectionCode());
                    questionList.add(question);
                    //阅读题
                    Question_readingEntity questionReading = new Question_readingEntity();
                    questionReading.preInsert();
                    questionReading.setQuestionId(question.getId());
                    questionReading.setTranslation(ExcelUtil.getStringValue(row.getCell(5)));
                    questionReadingList.add(questionReading);
                    //试卷题目关系
                    PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                    paperQuestion.preInsert();
                    paperQuestion.setPaperId(paper.getId());
                    paperQuestion.setStructureId(paperStructure.getId());
                    paperQuestion.setQuestionId(question.getId());
                    paperQuestion.setSort(0);
                    paperQuestionList.add(paperQuestion);
                }
                //小题
                String itemName = ExcelUtil.getStringValue(row.getCell(7));
                if (ToolUtil.isNotEmpty(itemName)) {
                    questionItem = new Question_itemEntity();
                    questionItem.preInsert();
                    questionItem.setQuestionId(question.getId());
                    questionItem.setContent(itemName);
                    questionItem.setAnalysis(ExcelUtil.getStringValue(row.getCell(8)));
                    Integer sort = ExcelUtil.getIntegerValue(row.getCell(9));
                    questionItem.setSort(null == sort ? 0 : sort);
                    questionItemList.add(questionItem);
                }
                //选项
                String optionName = ExcelUtil.getStringValue(row.getCell(10));
                if (ToolUtil.isNotEmpty(optionName)) {
                    questionOption = new Question_optionEntity();
                    questionOption.preInsert();
                    questionOption.setItemId(questionItem.getId());
                    questionOption.setName(optionName);
                    questionOption.setContent(ExcelUtil.getStringValue(row.getCell(11)));
                    Integer isAnswer = ExcelUtil.getIntegerValue(row.getCell(12));
                    questionOption.setIsAnswer(null == isAnswer ? 0 : isAnswer);
                    questionOption.setSort(0);
                    questionOptionList.add(questionOption);
                }
            }
        }

        //数据获取End,持久化
        if (ToolUtil.isNotEmpty(paperStructureList))
            paperStructureMapper.batchInsert(paperStructureList);
        if (ToolUtil.isNotEmpty(paperQuestionList))
            paperQuestionMapper.batchInsert(paperQuestionList);
        if (ToolUtil.isNotEmpty(questionList))
            questionMapper.insertList(questionList);
        if (ToolUtil.isNotEmpty(questionItemList))
            questionItemMapper.insertList(questionItemList);
        if (ToolUtil.isNotEmpty(questionOptionList))
            questionOptionMapper.insertList(questionOptionList);
        if (ToolUtil.isNotEmpty(questionReadingList))
            questionReadingMapper.insertList(questionReadingList);

    }

    /**
     * 专项练习-翻译上传解析
     * @param paper
     * @param paperCatalog
     * @param sheet
     * @param dataStartRow
     * @param rowCount
     */
    private void uploadSpecialTranslation(PaperEntity paper,PaperCatalogEntity paperCatalog,Sheet sheet,int dataStartRow,int rowCount) {
        //数据实体
        QuestionEntity question = null;
        //数据集合
        List<PaperQuestionEntity> paperQuestionList = new ArrayList<>();
        List<QuestionEntity> questionList = new ArrayList<>();
        List<Question_translationEntity> questionTranslationList = new ArrayList<>();

        for (int i = dataStartRow; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (!ExcelUtil.isRowEmpty(row)) {

                //校验学段
                String sectionCode = ExcelUtil.getStringValue(row.getCell(0));
                if (ToolUtil.isNotEmpty(sectionCode) && !sectionCode.equals(paperCatalog.getSectionCode().toString()))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_SECTION_CODE_ERROR);

                //题目
                String questionName = ExcelUtil.getStringValue(row.getCell(1));
                if (ToolUtil.isNotEmpty(questionName)) {
                    question = new QuestionEntity();
                    question.preInsert();
                    question.setName(questionName);
                    question.setContent(ExcelUtil.getStringValue(row.getCell(2)));
                    question.setType(paper.getContentType());
                    question.setHasItem(0);
                    question.setQuestionNum(0);
                    question.setSectionCode(paperCatalog.getSectionCode());
                    question.setAnalysis(ExcelUtil.getStringValue(row.getCell(3)));
                    questionList.add(question);
                    //翻译题
                    Question_translationEntity questionTranslation = new Question_translationEntity();
                    questionTranslation.preInsert();
                    questionTranslation.setQuestionId(question.getId());
                    questionTranslation.setReference(ExcelUtil.getStringValue(row.getCell(4)));
                    questionTranslation.setAnalysisCcId(ExcelUtil.getStringValue(row.getCell(5)));
                    questionTranslationList.add(questionTranslation);
                    //试卷题目关系
                    PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                    paperQuestion.preInsert();
                    paperQuestion.setPaperId(paper.getId());
                    paperQuestion.setQuestionId(question.getId());
                    paperQuestion.setSort(0);
                    paperQuestionList.add(paperQuestion);
                }

            }
        }

        //数据获取End,持久化
        if (ToolUtil.isNotEmpty(paperQuestionList))
            paperQuestionMapper.batchInsert(paperQuestionList);
        if (ToolUtil.isNotEmpty(questionList))
            questionMapper.insertList(questionList);
        if (ToolUtil.isNotEmpty(questionTranslationList))
            questionTranslationMapper.insertList(questionTranslationList);
    }

    /**
     * 专项练习-写作上传解析
     * @param paper
     * @param paperCatalog
     * @param sheet
     * @param dataStartRow
     * @param rowCount
     */
    private void uploadSpecialWriting(PaperEntity paper,PaperCatalogEntity paperCatalog,Sheet sheet,int dataStartRow,int rowCount) {
        //数据实体
        QuestionEntity question = null;
        //数据集合
        List<PaperQuestionEntity> paperQuestionList = new ArrayList<>();
        List<QuestionEntity> questionList = new ArrayList<>();
        List<Question_writingEntity> questionWritingList = new ArrayList<>();

        for (int i = dataStartRow; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (!ExcelUtil.isRowEmpty(row)) {

                //校验学段
                String sectionCode = ExcelUtil.getStringValue(row.getCell(0));
                if (ToolUtil.isNotEmpty(sectionCode) && !sectionCode.equals(paperCatalog.getSectionCode().toString()))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_SECTION_CODE_ERROR);

                //题目
                String questionName = ExcelUtil.getStringValue(row.getCell(1));
                if (ToolUtil.isNotEmpty(questionName)) {
                    question = new QuestionEntity();
                    question.preInsert();
                    question.setName(questionName);
                    question.setContent(ExcelUtil.getStringValue(row.getCell(2)));
                    question.setType(paper.getContentType());
                    question.setHasItem(0);
                    question.setQuestionNum(0);
                    question.setSectionCode(paperCatalog.getSectionCode());
                    question.setAnalysis(ExcelUtil.getStringValue(row.getCell(3)));
                    questionList.add(question);
                    //写作题
                    Question_writingEntity questionWriting = new Question_writingEntity();
                    questionWriting.preInsert();
                    questionWriting.setQuestionId(question.getId());
                    questionWriting.setReference(ExcelUtil.getStringValue(row.getCell(4)));
                    questionWriting.setAnalysisCcId(ExcelUtil.getStringValue(row.getCell(5)));
                    questionWritingList.add(questionWriting);
                    //试卷题目关系
                    PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                    paperQuestion.preInsert();
                    paperQuestion.setPaperId(paper.getId());
                    paperQuestion.setQuestionId(question.getId());
                    paperQuestion.setSort(0);
                    paperQuestionList.add(paperQuestion);
                }

            }
        }

        //数据获取End,持久化
        if (ToolUtil.isNotEmpty(paperQuestionList))
            paperQuestionMapper.batchInsert(paperQuestionList);
        if (ToolUtil.isNotEmpty(questionList))
            questionMapper.insertList(questionList);
        if (ToolUtil.isNotEmpty(questionWritingList))
            questionWritingMapper.insertList(questionWritingList);

    }

    /**
     * 简系列-听力上传解析
     * @param paper
     * @param paperCatalog
     * @param sheet
     * @param dataStartRow
     * @param rowCount
     */
    private void uploadEasyListening(PaperEntity paper,PaperCatalogEntity paperCatalog,Sheet sheet,int dataStartRow,int rowCount) {
        //实体模型
        PaperStructureEntity paperStructure1 = null;
        PaperStructureEntity paperStructure2 = null;
        PaperStructureEntity paperStructure3 = null;
        QuestionEntity question = null;
        Question_itemEntity questionItem = null;
        Question_optionEntity questionOption = null;
        //数据集合
        List<PaperStructureEntity> paperStructureList = new ArrayList<>();
        List<PaperQuestionEntity> paperQuestionList = new ArrayList<>();
        List<QuestionEntity> questionList = new ArrayList<>();
        List<Question_itemEntity> questionItemList = new ArrayList<>();
        List<Question_optionEntity> questionOptionList = new ArrayList<>();
        List<Question_listeningEntity> questionListeningList = new ArrayList<>();

        for (int i = dataStartRow; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (!ExcelUtil.isRowEmpty(row)) {

                //校验学段
                String sectionCode = ExcelUtil.getStringValue(row.getCell(0));
                if (ToolUtil.isNotEmpty(sectionCode) && !sectionCode.equals(paperCatalog.getSectionCode().toString()))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_SECTION_CODE_ERROR);

                //目录结构
                String structure1Name = ExcelUtil.getStringValue(row.getCell(1));
                if (ToolUtil.isNotEmpty(structure1Name)) {
                    PaperStructureEntity entity = new PaperStructureEntity();
                    entity.setName(structure1Name);
                    entity.setPaperId(paper.getId());
                    entity.setPaperId("0");
                    entity = paperStructureMapper.get(entity);
                    if (null != entity)
                        paperStructure1 = entity;
                    else {
                        paperStructure1 = new PaperStructureEntity();
                        paperStructure1.preInsert();
                        paperStructure1.setPaperId(paper.getId());
                        paperStructure1.setName(structure1Name);
                        paperStructure1.setContentType(paper.getContentType());
                        paperStructure1.setAlias(structure1Name);
                        paperStructure1.setParentId("0");
                        paperStructure1.setParentIds("0");
                        paperStructure1.setLevel(1);
                        paperStructure1.setIsLeaf(0);
                        paperStructure1.setSort(0);
                        paperStructureList.add(paperStructure1);
                    }
                }
                //目录结构2
                String structure2Name = ExcelUtil.getStringValue(row.getCell(2));
                if (ToolUtil.isNotEmpty(structure2Name)) {
                    PaperStructureEntity entity = new PaperStructureEntity();
                    entity.setName(structure2Name);
                    entity.setPaperId(paper.getId());
                    entity.setPaperId(paperStructure1.getId());
                    entity = paperStructureMapper.get(entity);
                    if (null != entity)
                        paperStructure2 = entity;
                    else {
                        paperStructure2 = new PaperStructureEntity();
                        paperStructure2.preInsert();
                        paperStructure2.setPaperId(paper.getId());
                        paperStructure2.setName(structure2Name);
                        paperStructure2.setContent(ExcelUtil.getStringValue(row.getCell(3)));
                        paperStructure2.setContentType(paper.getContentType());
                        paperStructure2.setAlias(structure2Name);
                        paperStructure2.setParentId(paperStructure1.getId());
                        paperStructure2.setParentIds("0," + paperStructure1.getId());
                        paperStructure2.setLevel(2);
                        paperStructure2.setIsLeaf(0);
                        paperStructure2.setSort(0);
                        paperStructureList.add(paperStructure2);
                    }
                }
                //目录结构3
                String structure3Name = ExcelUtil.getStringValue(row.getCell(4));
                if (ToolUtil.isNotEmpty(structure3Name)) {
                    PaperStructureEntity entity = new PaperStructureEntity();
                    entity.setName(structure3Name);
                    entity.setPaperId(paper.getId());
                    entity.setPaperId(paperStructure2.getId());
                    entity = paperStructureMapper.get(entity);
                    if (null != entity)
                        paperStructure3 = entity;
                    else {
                        paperStructure3 = new PaperStructureEntity();
                        paperStructure3.preInsert();
                        paperStructure3.setPaperId(paper.getId());
                        paperStructure3.setName(structure3Name);
                        paperStructure3.setContentType(paper.getContentType());
                        paperStructure3.setAlias(structure3Name);
                        paperStructure3.setParentId(paperStructure2.getId());
                        paperStructure3.setParentIds(paperStructure2.getParentIds() + "," + paperStructure2.getId());
                        paperStructure3.setLevel(1);
                        paperStructure3.setIsLeaf(1);
                        paperStructure3.setSort(0);
                        paperStructureList.add(paperStructure3);
                    }
                }
                //题目
                String questionName = ExcelUtil.getStringValue(row.getCell(5));
                if (ToolUtil.isNotEmpty(questionName)) {
                    question = new QuestionEntity();
                    question.preInsert();
                    question.setName(questionName);
                    question.setType(paper.getContentType());
                    question.setAnalysis(ExcelUtil.getStringValue(row.getCell(6)));
                    String item = ExcelUtil.getStringValue(row.getCell(9));
                    if (null == item)
                        throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_ITEM_NOT_FOUND);
                    question.setHasItem(ToolUtil.isEmpty(item) ? 0:1);
                    question.setSectionCode(paperCatalog.getSectionCode());
                    questionList.add(question);
                    //听力题
                    Question_listeningEntity questionListening = new Question_listeningEntity();
                    questionListening.preInsert();
                    questionListening.setQuestionId(question.getId());
                    questionListening.setAudioUrl(ExcelUtil.getStringValue(row.getCell(7)));
                    questionListening.setTapescripts(ExcelUtil.getStringValue(row.getCell(8)));
                    questionListening.setSubtitleUrl("");
                    questionListeningList.add(questionListening);
                    //试卷题目关系
                    PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                    paperQuestion.preInsert();
                    paperQuestion.setPaperId(paper.getId());
                    paperQuestion.setStructureId(paperStructure3.getId());
                    paperQuestion.setQuestionId(question.getId());
                    paperQuestion.setSort(0);
                    paperQuestionList.add(paperQuestion);
                }
                //小题
                String itemName = ExcelUtil.getStringValue(row.getCell(9));
                if (ToolUtil.isNotEmpty(itemName)) {
                    questionItem = new Question_itemEntity();
                    questionItem.preInsert();
                    questionItem.setQuestionId(question.getId());
                    questionItem.setContent(itemName);
                    questionItem.setSort(0);
                    questionItemList.add(questionItem);
                }
                //选项
                String optionName = ExcelUtil.getStringValue(row.getCell(10));
                if (ToolUtil.isNotEmpty(optionName)) {
                    questionOption = new Question_optionEntity();
                    questionOption.preInsert();
                    questionOption.setItemId(questionItem.getId());
                    questionOption.setName(optionName);
                    questionOption.setContent(ExcelUtil.getStringValue(row.getCell(11)));
                    Integer isAnswer = ExcelUtil.getIntegerValue(row.getCell(12));
                    questionOption.setIsAnswer(null == isAnswer ? 0 : isAnswer);
                    questionOption.setSort(0);
                    questionOptionList.add(questionOption);
                }
            }
        }

        //数据获取End,持久化
        if (ToolUtil.isNotEmpty(paperStructureList))
            paperStructureMapper.batchInsert(paperStructureList);
        if (ToolUtil.isNotEmpty(paperQuestionList))
            paperQuestionMapper.batchInsert(paperQuestionList);
        if (ToolUtil.isNotEmpty(questionList))
            questionMapper.insertList(questionList);
        if (ToolUtil.isNotEmpty(questionItemList))
            questionItemMapper.insertList(questionItemList);
        if (ToolUtil.isNotEmpty(questionOptionList))
            questionOptionMapper.insertList(questionOptionList);
        if (ToolUtil.isNotEmpty(questionListeningList))
            questionListeningMapper.insertList(questionListeningList);
    }

    /**
     * Excel表头校验-专项练习
     * @param headRow
     */
    private int checkSpecialHead(Row headRow,Integer contentType){
        int cellNum = 0;
        switch (contentType){
            case 1 : {//听力
                int headCellIndex = 0;
                if (!ExcelUtil.getStringValue(headRow.getCell(headCellIndex)).equals("学段(1-CET4;2-CET6;3-考研英语;4-英语专业;5-高中英语;6-初中英语;7-小学英语;)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("类型\n(短篇新闻;长对话;短文理解;讲座讲话)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("类型排序")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题目名称")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题干")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("音频地址")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("听力原文")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("译文")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("小题数量")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("小题题干")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("小题解析")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("小题排序")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("选项\n(ABCD..)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("选项内容")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("是否答案\n(0否1是)"))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_VERSION_ERROR);
                cellNum = 15;
                break;
            }
            case 2 : {//阅读
                int headCellIndex = 0;
                if (!ExcelUtil.getStringValue(headRow.getCell(headCellIndex)).equals("学段(1-CET4;2-CET6;3-考研英语;4-英语专业;5-高中英语;6-初中英语;7-小学英语;)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("类型\n(篇章词汇;信息匹配;篇章阅读)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("类型排序")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题目名称")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题干")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("译文")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("小题数量")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("小题题干")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("小题解析")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("小题排序")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("选项\n(ABCD..)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("选项内容")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("是否答案\n(0否1是)"))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_VERSION_ERROR);
                cellNum = 13;
                break;
            }
            case 3 : {//翻译
                int headCellIndex = 0;
                if (!ExcelUtil.getStringValue(headRow.getCell(headCellIndex)).equals("学段(1-CET4;2-CET6;3-考研英语;4-英语专业;5-高中英语;6-初中英语;7-小学英语;)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题目名称")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题干")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题目解析")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("参考范文")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("视频讲解"))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_VERSION_ERROR);
                cellNum = 6;
                break;
            }
            case 4 : {//写作
                int headCellIndex = 0;
                if (!ExcelUtil.getStringValue(headRow.getCell(headCellIndex)).equals("学段(1-CET4;2-CET6;3-考研英语;4-英语专业;5-高中英语;6-初中英语;7-小学英语;)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题目名称")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题干")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题目解析")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("参考范文")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("视频讲解"))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_VERSION_ERROR);
                cellNum = 6;
                break;
            }
        }
        return cellNum;
    }

    /**
     * Excel表头校验-简系列
     * @param headRow
     */
    private int checkEasyHead(Row headRow,Integer contentType){
        int cellNum = 0;
        switch (contentType){
            case 1 : {//听力
                int headCellIndex = 0;
                if (!ExcelUtil.getStringValue(headRow.getCell(headCellIndex)).equals("学段(1-CET4;2-CET6;3-考研英语;4-英语专业;5-高中英语;6-初中英语;7-小学英语;)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("类型（在线做题;赠品600)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("节名称（Section A，Section B等等 ）")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("节描述（Directions:等等）")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("Conversation（Conversation One,Conversation Two等)")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题目名称")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("解析")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("音频地址")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("听力原文")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("题号")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("选项")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("选项内容")
                        || !ExcelUtil.getStringValue(headRow.getCell(++headCellIndex)).equals("是否正确答案（0否 1是）"))
                    throw new BussinessException(BizExceptionEnum.PAPER_EXCEL_VERSION_ERROR);
                cellNum = 13;
            }
        }
        return cellNum;
    }

    /**
     * 统计专项练习试卷题目数量
     */
    @Transactional
    public void refSpeciaQuestionCount(){
        PaperCatalogEntity catalog = new PaperCatalogEntity();
        catalog.setType(4);//专项练习
        List<PaperCatalogEntity> catalogList = paperCatalogMapper.findList(catalog);
        if (ToolUtil.isNotEmpty(catalogList)) {
            List<String> catalogIdList = catalogList.stream().map(paperCatalogEntity -> paperCatalogEntity.getId())
                    .collect(Collectors.toList());
            PaperEntity paper = new PaperEntity();
            paper.setCatalogIdList(catalogIdList);
            List<PaperEntity> paperList = paperMapper.findList(paper);
            if (ToolUtil.isNotEmpty(paperList)) {
                List<String> paperIdList = paperList.stream().map(paperEntity -> paperEntity.getId())
                        .collect(Collectors.toList());
                PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                paperQuestion.setPaperIdList(paperIdList);
                List<PaperQuestionEntity> paperQuestionList = paperQuestionMapper.findList(paperQuestion);
                //计算所有试卷题目数量
                if (ToolUtil.isNotEmpty(paperQuestionList)) {
                    paperList.stream().forEach(paperEntity -> {
                        long count = paperQuestionList.stream().filter(paperQuestionEntity -> paperQuestionEntity.getPaperId().equals(paperEntity.getId())).count();
                        paperEntity.setQuestionNum(Convert.toInt(count,0));
                    });
                    //修改题目数量
                    for (PaperEntity pEntity : paperList) {
                        paperMapper.update(pEntity);
                    }
                }
            }
        }
    }

    /**
     * 统计试卷小题数量
     * @param paperId
     */
    @Transactional
    public void refSpeciaQuestionCountById(String paperId){
        PaperEntity paper = paperMapper.getById(paperId);
        if (ToolUtil.isNotEmpty(paper)) {
            PaperCatalogEntity paperCatalog = paperCatalogMapper.getById(paper.getCatalogId());
            if (ToolUtil.isNotEmpty(paperCatalog)
                    && paperCatalog.getType() == 4) {
                int count = 0;

                PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
                paperQuestion.setPaperId(paper.getId());
                List<PaperQuestionEntity> paperQuestionList = paperQuestionMapper.findList(paperQuestion);

                if (ToolUtil.isNotEmpty(paperQuestionList)) {
                    //计算小题
                    if (paper.getContentType() == 1 || paper.getContentType() == 2) {
                        List<String> questionIdList = paperQuestionList.stream().map(paperQuestionEntity -> paperQuestionEntity.getQuestionId())
                                .collect(Collectors.toList());
                        Question_itemEntity questionItem = new Question_itemEntity();
                        questionItem.setQuestionIdList(questionIdList);
                        List<Question_itemEntity> questionItemList = questionItemMapper.findList(questionItem);
                        if (ToolUtil.isNotEmpty(questionItemList))
                            count = questionItemList.size();
                    }
                    //计算题目
                    if (paper.getContentType() == 3 || paper.getContentType() == 4) {
                        //计算所有试卷题目数量
                        count = paperQuestionList.size();
                    }
                }
                paper.setQuestionNum(count);
                paperMapper.update(paper);
            }
        }
    }

}
