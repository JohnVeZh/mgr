package cn.sparke.modules.paper.paperStructure.mapper;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.entity.StructureTreeNode;

import java.util.List;

/**
 * 试卷树形结构Dao
 *
 * @author spark
 * @Date 2017-07-19 09:52:16
 */
public interface PaperStructureMapper extends BaseMapper<PaperStructureEntity> {

    void batchInsert(List<PaperStructureEntity> list);

    List<ZTreeNode> treeList(String paperId);

    List<StructureTreeNode> structureTreeList(PaperStructureEntity paperStructure);

    PaperStructureEntity getParentStructure(String parentId);

    List<String> getChildStructure(String parentId);
}
