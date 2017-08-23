package cn.sparke.modules.paper.paperCatalog.mapper;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.paper.paperCatalog.entity.CatalogTreeNode;
import cn.sparke.modules.paper.paperCatalog.entity.PaperCatalogEntity;

import java.util.List;

/**
 * 试卷目录Dao
 *
 * @author spark
 * @Date 2017-07-19 09:51:45
 */
public interface PaperCatalogMapper extends BaseMapper<PaperCatalogEntity>{

    List<ZTreeNode> treeList(List<String> list);

    List<ZTreeNode> sectionTreeList(List<String> list);

    List<CatalogTreeNode> catalogTreeList(List<String> list);

    List<CatalogTreeNode> catalogTree(PaperCatalogEntity entity);

}
