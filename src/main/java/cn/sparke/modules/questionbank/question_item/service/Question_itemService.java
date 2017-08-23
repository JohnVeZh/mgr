package cn.sparke.modules.questionbank.question_item.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.questionbank.question_item.mapper.Question_itemMapper;
import cn.sparke.modules.questionbank.question_item.entity.Question_itemEntity;

/**
 * 题目小题表Dao
 *
 * @author spark
 * @Date 2017-07-19 11:02:14
 */
@Service
public class Question_itemService{
    @Autowired
    private Question_itemMapper question_itemMapper;

    public void save(Question_itemEntity question_item){
        question_item.preInsert();
        question_itemMapper.insert(question_item);
    }

    public void update(Question_itemEntity question_item){
        question_item.preUpdate();
        question_itemMapper.update(question_item);
    }

    public Question_itemEntity get(Question_itemEntity question_item){
        return question_itemMapper.get(question_item);
    }

    public Question_itemEntity getById(String id){
        return question_itemMapper.getById(id);
    }
    public Page<Question_itemEntity> findList(Question_itemEntity question_item){
       return question_itemMapper.findList(question_item);
    }

    public void deleteById(String id){
      question_itemMapper.deleteById(id);
    }

}
