package cn.sparke.modules.questionbank.question_listening.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.questionbank.question_listening.entity.Question_listeningEntity;

import java.util.List;

/**
 * 听力题Dao
 *
 * @author spark
 * @Date 2017-07-19 10:53:23
 */
public interface Question_listeningMapper extends BaseMapper<Question_listeningEntity>{

    int insertList(List<Question_listeningEntity> list);
}
