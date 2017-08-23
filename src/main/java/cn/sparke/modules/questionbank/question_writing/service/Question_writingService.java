package cn.sparke.modules.questionbank.question_writing.service;

import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_writing.entity.QuestionWritingEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.questionbank.question_writing.mapper.Question_writingMapper;
import cn.sparke.modules.questionbank.question_writing.entity.Question_writingEntity;

/**
 * 写作题Dao
 *
 * @author spark
 * @Date 2017-07-19 10:59:38
 */
@Service
public class Question_writingService{
    @Autowired
    private Question_writingMapper question_writingMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public void save(Question_writingEntity question_writing){
        //创建题目对象
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.preInsert();
        String questionId=questionEntity.getId();
        questionEntity.setSort(question_writing.getSort());
        questionEntity.setHasItem(0);//没有小题
        questionEntity.setAnalysis(question_writing.getAnalysis());
        questionEntity.setSectionCode(question_writing.getSectionCode());
        questionEntity.setType(question_writing.getType());
        questionEntity.setName(question_writing.getName());
        questionEntity.setContent(question_writing.getContent());
        questionEntity.setQuestionNum(question_writing.getQuestionNum());
        questionEntity.setRemarks(question_writing.getRemarks());

        //创建题目与关心对象
        PaperQuestionEntity paperQuestion = new PaperQuestionEntity();
        paperQuestion.preInsert();
        paperQuestion.setSort(0);
        paperQuestion.setQuestionId(questionId);
        paperQuestion.setStructureId(question_writing.getStructureId());
        paperQuestion.setRemarks(question_writing.getRemarks());//remarks
        paperQuestion.setPaperId(question_writing.getPaperId());
        //写作
        question_writing.preInsert();
        question_writing.setQuestionId(questionId);

        paperQuestionMapper.insert(paperQuestion);
        questionMapper.insert(questionEntity);
        question_writingMapper.insert(question_writing);
    }

    public void update(Question_writingEntity question_writing){

        //创建题目对象
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setRemarks(question_writing.getRemarks());
        questionEntity.setQuestionNum(question_writing.getQuestionNum());
        questionEntity.setContent(question_writing.getContent());
        questionEntity.setName(question_writing.getName());
        questionEntity.setType(question_writing.getType());
        questionEntity.setId(question_writing.getQuestionId());
        //questionEntity.setHasItem(question_writing.getHasItem());
        questionEntity.setAnalysis(question_writing.getAnalysis());
        questionEntity.preUpdate();
        questionMapper.update(questionEntity);

        question_writing.preUpdate();
        question_writingMapper.update(question_writing);
    }

    public Question_writingEntity get(Question_writingEntity question_writing){
        return question_writingMapper.get(question_writing);
    }

    public Question_writingEntity getById(String id){
        return question_writingMapper.getById(id);
    }
    public Page<Question_writingEntity> findList(Question_writingEntity question_writing){
       return question_writingMapper.findList(question_writing);
    }

    public Page<Question_writingEntity> queryList(QuestionWritingEntity questionWritingEntity){
        Page<Question_writingEntity> page = question_writingMapper.queryList(questionWritingEntity);
        return page;
    }

    public void deleteById(String id){
      question_writingMapper.deleteById(id);
    }

}
