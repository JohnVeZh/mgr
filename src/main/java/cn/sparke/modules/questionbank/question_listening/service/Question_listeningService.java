package cn.sparke.modules.questionbank.question_listening.service;

import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_listening.entity.QuestionListeningEntity;
import cn.sparke.modules.questionbank.question_listening.entity.Question_listeningEntity;
import cn.sparke.modules.questionbank.question_listening.mapper.Question_listeningMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 听力题Dao
 *
 * @author spark
 * @Date 2017-07-19 10:53:23
 */
@Service
public class Question_listeningService{
    @Autowired
    private Question_listeningMapper question_listeningMapper;
    @Autowired
    private QuestionMapper questionMap;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;

    public void save(Question_listeningEntity question_listening){
        //创建题目对象
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.preInsert();
        String questionId=questionEntity.getId();
        questionEntity.setSort(question_listening.getSort());
        questionEntity.setHasItem(question_listening.getHasItem());
        questionEntity.setAnalysis(question_listening.getAnalysis());
        questionEntity.setSectionCode(question_listening.getSectionCode());
        questionEntity.setType(question_listening.getType());
        questionEntity.setName(question_listening.getName());
        questionEntity.setContent(question_listening.getContent());
        questionEntity.setQuestionNum(question_listening.getQuestionNum());
        questionEntity.setRemarks(question_listening.getRemarks());

        //创建题目与关心对象
        PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
        paperQuestion.preInsert();
        paperQuestion.setSort(0);
        paperQuestion.setQuestionId(questionId);
        paperQuestion.setStructureId(question_listening.getStructureId());
        paperQuestion.setRemarks(question_listening.getRemarks());//remarks
        paperQuestion.setPaperId(question_listening.getPaperId());

        //听力对象
        question_listening.setQuestionId(questionId);
        question_listening.preInsert();

        paperQuestionMapper.insert(paperQuestion);
        questionMap.insert(questionEntity);
        question_listeningMapper.insert(question_listening);
    }

    public void update(Question_listeningEntity question_listening){
        //创建题目对象
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setRemarks(question_listening.getRemarks());
        questionEntity.setQuestionNum(question_listening.getQuestionNum());
        questionEntity.setContent(question_listening.getContent());
        questionEntity.setName(question_listening.getName());
        questionEntity.setType(question_listening.getType());
        questionEntity.setId(question_listening.getQuestionId());
        questionEntity.setHasItem(question_listening.getHasItem());
        questionEntity.preUpdate();
        questionMap.update(questionEntity);

        question_listening.preUpdate();
        question_listeningMapper.update(question_listening);
    }

    public Question_listeningEntity get(Question_listeningEntity question_listening){
        return question_listeningMapper.get(question_listening);
    }

    public Question_listeningEntity getById(String id){
        return question_listeningMapper.getById(id);
    }

    public Page<Question_listeningEntity> findList(Question_listeningEntity question_listening){
        Page<Question_listeningEntity> page=question_listeningMapper.findList(question_listening);
       return page;
    }

    public Page<Question_listeningEntity> queryList(QuestionListeningEntity questionListening){
        Page<Question_listeningEntity> page = question_listeningMapper.queryList(questionListening);
        return page;
    }

    public void deleteById(String id){
      question_listeningMapper.deleteById(id);
    }

}
