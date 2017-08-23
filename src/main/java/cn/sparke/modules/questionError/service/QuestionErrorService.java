package cn.sparke.modules.questionError.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.questionError.mapper.QuestionErrorMapper;
import cn.sparke.modules.questionError.entity.QuestionErrorEntity;

/**
 * 题目报错Dao
 *
 * @author spark
 * @Date 2017-07-31 10:58:47
 */
@Service
public class QuestionErrorService{
    @Autowired
    private QuestionErrorMapper questionErrorMapper;

    public void save(QuestionErrorEntity questionError){
        questionError.preInsert();
        questionErrorMapper.insert(questionError);
    }

    public void update(QuestionErrorEntity questionError){
        questionError.preUpdate();
        questionErrorMapper.update(questionError);
    }

    public QuestionErrorEntity get(QuestionErrorEntity questionError){
        return questionErrorMapper.get(questionError);
    }

    public QuestionErrorEntity getById(String id){
        return questionErrorMapper.getById(id);
    }
    public Page<QuestionErrorEntity> findList(QuestionErrorEntity questionError){
       return questionErrorMapper.findList(questionError);
    }

    public void deleteById(String id){
      questionErrorMapper.deleteById(id);
    }

}
