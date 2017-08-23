package cn.sparke.modules.questionbank.question_option.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.questionbank.question_option.entity.Question_optionEntity;

import java.util.List;

/**
 * 题目选项Dao
 *
 * @author spark
 * @Date 2017-07-19 11:23:46
 */
public interface Question_optionMapper extends BaseMapper<Question_optionEntity>{

    int insertList(List<Question_optionEntity> list);

}
