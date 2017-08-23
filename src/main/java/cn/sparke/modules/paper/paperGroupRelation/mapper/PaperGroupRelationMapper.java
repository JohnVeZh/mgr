package cn.sparke.modules.paper.paperGroupRelation.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.paper.paperGroupRelation.entity.PaperGroupRelationEntity;

import java.util.List;

/**
 * 试卷组关系Dao
 *
 * @author spark
 * @Date 2017-07-27 16:23:44
 */
public interface PaperGroupRelationMapper extends BaseMapper<PaperGroupRelationEntity>{

    void batchInsert(List<PaperGroupRelationEntity> list);

    Integer deleteByGroupId(String id);

    Integer deleteByPaperId(String id);

}
