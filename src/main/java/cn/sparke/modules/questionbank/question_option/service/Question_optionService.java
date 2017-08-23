package cn.sparke.modules.questionbank.question_option.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.questionbank.question_option.mapper.Question_optionMapper;
import cn.sparke.modules.questionbank.question_option.entity.Question_optionEntity;

/**
 * 题目选项Dao
 *
 * @author spark
 * @Date 2017-07-19 11:23:46
 */
@Service
public class Question_optionService{
    @Autowired
    private Question_optionMapper question_optionMapper;

    public void save(Question_optionEntity question_option){
        question_option.preInsert();
        question_optionMapper.insert(question_option);
    }

    public void update(Question_optionEntity question_option){
        question_option.preUpdate();
        question_optionMapper.update(question_option);
    }

    public Question_optionEntity get(Question_optionEntity question_option){
        return question_optionMapper.get(question_option);
    }

    public Question_optionEntity getById(String id){
        return question_optionMapper.getById(id);
    }
    public Page<Question_optionEntity> findList(Question_optionEntity question_option){
       return question_optionMapper.findList(question_option);
    }

    public void deleteById(String id){
      question_optionMapper.deleteById(id);
    }

}
