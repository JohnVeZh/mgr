package cn.sparke.modules.questionbank.question_translation.service;

import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_translation.entity.QuestionTranslationEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.questionbank.question_translation.mapper.Question_translationMapper;
import cn.sparke.modules.questionbank.question_translation.entity.Question_translationEntity;

/**
 * 翻译题Dao
 *
 * @author spark
 * @Date 2017-07-19 10:58:40
 */
@Service
public class Question_translationService{
    @Autowired
    private Question_translationMapper question_translationMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public void save(Question_translationEntity question_translation){

        //创建题目对象
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.preInsert();
        String questionId=questionEntity.getId();
        questionEntity.setHasItem(0/*question_translation.getHasItem()*/);//翻译默认没有小题
        questionEntity.setAnalysis(question_translation.getAnalysis());
        questionEntity.setSectionCode(question_translation.getSectionCode());
        questionEntity.setType(question_translation.getType());
        questionEntity.setName(question_translation.getName());
        questionEntity.setContent(question_translation.getContent());
        questionEntity.setQuestionNum(question_translation.getQuestionNum());
        questionEntity.setRemarks(question_translation.getRemarks());

        //创建题目与关心对象
        PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
        paperQuestion.preInsert();
        paperQuestion.setSort(0);
        paperQuestion.setQuestionId(questionId);
        paperQuestion.setStructureId(question_translation.getStructureId());
        paperQuestion.setRemarks(question_translation.getRemarks());//remarks
        paperQuestion.setPaperId(question_translation.getPaperId());
        //翻译
        question_translation.preInsert();
        question_translation.setQuestionId(questionId);

        paperQuestionMapper.insert(paperQuestion);
        questionMapper.insert(questionEntity);
        question_translationMapper.insert(question_translation);
    }

    public void update(Question_translationEntity question_translation){

        //创建题目对象
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setRemarks(question_translation.getRemarks());
        questionEntity.setQuestionNum(question_translation.getQuestionNum());
        questionEntity.setContent(question_translation.getContent());
        questionEntity.setName(question_translation.getName());
        questionEntity.setType(question_translation.getType());
        questionEntity.setId(question_translation.getQuestionId());
        //questionEntity.setHasItem(question_translation.getHasItem());
        questionEntity.setAnalysis(question_translation.getAnalysis());
        questionEntity.preUpdate();
        questionMapper.update(questionEntity);
        question_translation.preUpdate();
        question_translationMapper.update(question_translation);
    }

    public Question_translationEntity get(Question_translationEntity question_translation){
        return question_translationMapper.get(question_translation);
    }

    public Question_translationEntity getById(String id){
        return question_translationMapper.getById(id);
    }
    public Page<Question_translationEntity> findList(Question_translationEntity question_translation){
       return question_translationMapper.findList(question_translation);
    }

    public Page<Question_translationEntity> queryList(QuestionTranslationEntity questionTranslationEntity){
        Page<Question_translationEntity> page = question_translationMapper.queryList(questionTranslationEntity);
        return page;
    }

    public void deleteById(String id){
      question_translationMapper.deleteById(id);
    }

}
