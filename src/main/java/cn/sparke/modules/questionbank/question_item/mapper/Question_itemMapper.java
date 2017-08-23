package cn.sparke.modules.questionbank.question_item.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.questionbank.question_item.entity.Question_itemEntity;

import java.util.List;

/**
 * 题目小题表Dao
 *
 * @author spark
 * @Date 2017-07-19 11:02:14
 */
public interface Question_itemMapper extends BaseMapper<Question_itemEntity>{

    int insertList(List<Question_itemEntity> list);
}
