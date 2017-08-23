package cn.sparke.modules.questionbank.question_translation.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.questionbank.question_translation.entity.Question_translationEntity;

import java.util.List;

/**
 * 翻译题Dao
 *
 * @author spark
 * @Date 2017-07-19 10:58:40
 */
public interface Question_translationMapper extends BaseMapper<Question_translationEntity>{

    int insertList(List<Question_translationEntity> list);
}
