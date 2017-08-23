package cn.sparke.modules.questionbank.question_reading.service;

import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_reading.entity.QuestionReadingEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.questionbank.question_reading.mapper.Question_readingMapper;
import cn.sparke.modules.questionbank.question_reading.entity.Question_readingEntity;

/**
 * 阅读题Dao
 *
 * @author spark
 * @Date 2017-07-19 10:55:02
 */
@Service
public class Question_readingService{
    @Autowired
    private Question_readingMapper question_readingMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public void save(Question_readingEntity question_reading){

        //创建题目对象
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.preInsert();
        String questionId=questionEntity.getId();
        questionEntity.setSort(question_reading.getSort());
        questionEntity.setHasItem(question_reading.getHasItem());
        questionEntity.setAnalysis(question_reading.getAnalysis());
        questionEntity.setSectionCode(question_reading.getSectionCode());
        questionEntity.setType(question_reading.getType());
        questionEntity.setName(question_reading.getName());
        questionEntity.setContent(question_reading.getContent());
        questionEntity.setQuestionNum(question_reading.getQuestionNum());
        questionEntity.setRemarks(question_reading.getRemarks());

        //创建题目与关心对象
        PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
        paperQuestion.preInsert();
        paperQuestion.setSort(0);
        paperQuestion.setQuestionId(questionId);
        paperQuestion.setStructureId(question_reading.getStructureId());
        paperQuestion.setRemarks(question_reading.getRemarks());//remarks
        paperQuestion.setPaperId(question_reading.getPaperId());
        //阅读
        question_reading.preInsert();
        question_reading.setQuestionId(questionId);

        paperQuestionMapper.insert(paperQuestion);
        questionMapper.insert(questionEntity);
        question_readingMapper.insert(question_reading);
    }

    public void update(Question_readingEntity question_reading){

        //创建题目对象
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setRemarks(question_reading.getRemarks());
        questionEntity.setQuestionNum(question_reading.getQuestionNum());
        questionEntity.setContent(question_reading.getContent());
        questionEntity.setName(question_reading.getName());
        questionEntity.setType(question_reading.getType());
        questionEntity.setId(question_reading.getQuestionId());
        questionEntity.setHasItem(question_reading.getHasItem());
        questionEntity.preUpdate();
        questionMapper.update(questionEntity);

        question_reading.preUpdate();
        question_readingMapper.update(question_reading);
    }

    public Question_readingEntity get(Question_readingEntity question_reading){
        return question_readingMapper.get(question_reading);
    }

    public Question_readingEntity getById(String id){
        return question_readingMapper.getById(id);
    }
    public Page<Question_readingEntity> findList(Question_readingEntity question_reading){
       return question_readingMapper.findList(question_reading);
    }

    public Page<Question_readingEntity> queryList(QuestionReadingEntity questionReadingEntity){
        Page<Question_readingEntity> page = question_readingMapper.queryList(questionReadingEntity);
        return page;
    }

    public void deleteById(String id){
      question_readingMapper.deleteById(id);
    }

}
