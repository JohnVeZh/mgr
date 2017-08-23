package cn.sparke.modules.questionbank.question_writing.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.questionbank.question_writing.entity.Question_writingEntity;

import java.util.List;

/**
 * 写作题Dao
 *
 * @author spark
 * @Date 2017-07-19 10:59:38
 */
public interface Question_writingMapper extends BaseMapper<Question_writingEntity>{

    int insertList(List<Question_writingEntity> list);
}
