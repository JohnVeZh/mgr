package cn.sparke.modules.questionbank.question_reading.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.questionbank.question_reading.entity.Question_readingEntity;

import java.util.List;

/**
 * 阅读题Dao
 *
 * @author spark
 * @Date 2017-07-19 10:55:02
 */
public interface Question_readingMapper extends BaseMapper<Question_readingEntity>{

    int insertList(List<Question_readingEntity> list);
}
