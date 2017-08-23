package cn.sparke.modules.paper.paperQuestion.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;

import java.util.List;

/**
 * 试卷题目关系表Dao
 *
 * @author spark
 * @Date 2017-07-25 19:42:26
 */
public interface PaperQuestionMapper extends BaseMapper<PaperQuestionEntity>{

    void batchInsert(List<PaperQuestionEntity> list);

}
