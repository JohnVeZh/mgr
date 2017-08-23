package cn.sparke.modules.questionbank.question.service;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import cn.sparke.modules.questionbank.question.entity.PaperSubtitleEntity;
import cn.sparke.modules.questionbank.question.entity.QuestionTitleTypes;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目Dao
 *
 * @author spark
 * @Date 2017-07-19 12:30:16
 */
@Service
public class QuestionService{
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private PaperStructureMapper paperStructureMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;

    public void save(QuestionEntity question){
        //创建关系对象
        PaperStructureEntity paperStructure= new PaperStructureEntity();
        paperStructure.preInsert();
        String structureId = paperStructure.getId();
        paperStructure.setParentIds("0");
        paperStructure.setParentId("0");
        paperStructure.setIsLeaf(1);
        paperStructure.setAlias(question.getAlias());
        paperStructure.setPaperId(question.getPaperId());
        paperStructure.setName(question.getName());
        paperStructure.setContentType(question.getType());
        paperStructure.setLevel(0);
        paperStructure.setRemarks(question.getRemarks());
        paperStructure.setSort(question.getSort());

        //创建题目对象
        question.preInsert();


        //创建试卷题目关系对象
        PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
        paperQuestion.preInsert();
        paperQuestion.setPaperId(question.getPaperId());
        paperQuestion.setRemarks(question.getRemarks());
        paperQuestion.setStructureId(structureId);
        paperQuestion.setQuestionId(question.getId());
        paperQuestion.setSort(question.getSort());

        paperStructureMapper.insert(paperStructure);
        paperQuestionMapper.insert(paperQuestion);
        questionMapper.insert(question);
    }

    public void update(QuestionEntity question){
        question.preUpdate();
        questionMapper.update(question);
    }

    public QuestionEntity get(QuestionEntity question){
        return questionMapper.get(question);
    }

    public QuestionEntity getById(String id){
        return questionMapper.getById(id);
    }

    public Page<QuestionEntity> findList(QuestionEntity question){
       return questionMapper.findList(question);
    }

    public void deleteById(String id){
      questionMapper.deleteById(id);
    }



    public List<ZTreeNode> sectionTreeList(Map<String,Object> dataMap) {
        List<ZTreeNode> tree=questionMapper.sectionTreeList(dataMap) ;
        return  tree;
    }


    public List<PaperEntity> findPaperList(QuestionEntity entity) {

        List<PaperEntity> page=questionMapper.findPaperList(entity);
        return  page;
    }

    public List<PaperSubtitleEntity> findPaperQrList(QuestionEntity entity) {

        List<PaperSubtitleEntity> page=questionMapper.findPaperQrList(entity);
        return  page;
    }

    public List<ZTreeNode> paperStructureTree(Map<String,Object> papramMap) {;

        Integer type=(Integer) papramMap.get("type");
        Integer contentType = (Integer) papramMap.get("contentType");
        List<ZTreeNode> tree=questionMapper.paperStructureTree(papramMap);
        //的到根节点
        //听力
        if(contentType==0 || contentType==1) {
            ZTreeNode listenNoe= new ZTreeNode();
            listenNoe.setId(QuestionTitleTypes.QUESTIONTYPES_LISTENING);
            listenNoe.setName(QuestionTitleTypes.QUESTIONTYPES_LISTENING_NAME);
            listenNoe.setpId("0");
            listenNoe.setIsOpen(true);
            tree.add(listenNoe);
        }
        if(type != 2 && type != 5){
        if(contentType==0 || contentType==2){
            //阅读
            ZTreeNode readingNoe= new ZTreeNode();
            readingNoe.setId(QuestionTitleTypes.QUESTIONTYPES_READING);
            readingNoe.setName(QuestionTitleTypes.QUESTIONTYPES_READING_NAME);
            readingNoe.setpId("0");
            readingNoe.setIsOpen(true);
            tree.add(readingNoe);
        }
        if(contentType==0 || contentType==3){
            //翻译
            ZTreeNode translationNoe= new ZTreeNode();
            translationNoe.setId(QuestionTitleTypes.QUESTIONTYPES_TRANSLATION);
            translationNoe.setName(QuestionTitleTypes.QUESTIONTYPES_TRANSLATION_NAME);
            translationNoe.setpId("0");
            translationNoe.setIsOpen(true);
            tree.add(translationNoe);
        }
        if(contentType==0 || contentType==4){
            //写作
            ZTreeNode writeNoe= new ZTreeNode();
            writeNoe.setId(QuestionTitleTypes.QUESTIONTYPES_WRITING);
            writeNoe.setName(QuestionTitleTypes.QUESTIONTYPES_WRITING_NAME);
            writeNoe.setpId("0");
            writeNoe.setIsOpen(true);
            tree.add(writeNoe);
        }
        }
        return  tree;
    }

    public Map<String,Object> getType(String id) {
        Map<String,Object> map = questionMapper.getType(id);
        return map;
    }

    public PaperStructureEntity getPaperStructureById(String id) {
       return paperStructureMapper.getById(id);
    }
}
