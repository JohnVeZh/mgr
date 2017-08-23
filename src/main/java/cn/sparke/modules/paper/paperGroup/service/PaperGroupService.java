package cn.sparke.modules.paper.paperGroup.service;

import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.paper.paperCatalog.entity.PaperCatalogEntity;
import cn.sparke.modules.paper.paperCatalog.mapper.PaperCatalogMapper;
import cn.sparke.modules.paper.paperGroup.entity.PaperGroupEntity;
import cn.sparke.modules.paper.paperGroup.mapper.PaperGroupMapper;
import cn.sparke.modules.paper.paperGroupRelation.entity.PaperGroupRelationEntity;
import cn.sparke.modules.paper.paperGroupRelation.mapper.PaperGroupRelationMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 试卷组Dao
 *
 * @author spark
 * @Date 2017-07-27 16:21:39
 */
@Service
public class PaperGroupService{
    @Autowired
    private PaperGroupMapper paperGroupMapper;
    @Autowired
    private PaperCatalogMapper paperCatalogMapper;
    @Autowired
    private PaperGroupRelationMapper paperGroupRelationMapper;

    /**
     * 保存
     * @param paperGroup
     */
    @Transactional
    public void save(PaperGroupEntity paperGroup){
        paperGroup.preInsert();
        paperGroupMapper.insert(paperGroup);
        List<String> paperIdList = Arrays.asList(paperGroup.getPaperIds().split(","));
        List<PaperGroupRelationEntity> paperGroupRelationList = paperIdList.stream().map(paperId -> {
            PaperGroupRelationEntity entity = new PaperGroupRelationEntity();
            entity.preInsert();
            entity.setPaperId(paperId);
            entity.setGroupId(paperGroup.getId());
            entity.setSort(0);
            return entity;
        }).collect(Collectors.toList());
        paperGroupRelationMapper.batchInsert(paperGroupRelationList);
    }

    /**
     * 修改
     * @param paperGroup
     */
    @Transactional
    public void update(PaperGroupEntity paperGroup){
        paperGroup.preUpdate();
        paperGroupMapper.update(paperGroup);
        paperGroupRelationMapper.deleteByGroupId(paperGroup.getId());
        List<String> paperIdList = Arrays.asList(paperGroup.getPaperIds().split(","));
        List<PaperGroupRelationEntity> paperGroupRelationList = paperIdList.stream().map(paperId -> {
            PaperGroupRelationEntity entity = new PaperGroupRelationEntity();
            entity.preInsert();
            entity.setPaperId(paperId);
            entity.setGroupId(paperGroup.getId());
            entity.setSort(0);
            return entity;
        }).collect(Collectors.toList());
        paperGroupRelationMapper.batchInsert(paperGroupRelationList);
    }

    public PaperGroupEntity get(PaperGroupEntity paperGroup){
        return paperGroupMapper.get(paperGroup);
    }

    public PaperGroupEntity getById(String id){
        PaperGroupEntity entity = paperGroupMapper.getById(id);
        if (ToolUtil.isNotEmpty(entity) && ToolUtil.isNotEmpty(entity.getCatalogId())) {
            PaperCatalogEntity paperCatalogEntity = paperCatalogMapper.getById(entity.getCatalogId());
            entity.setCatalogName(ToolUtil.isEmpty(paperCatalogEntity)? "" : paperCatalogEntity.getName());
        }
        PaperGroupRelationEntity paperGroupRelation = new PaperGroupRelationEntity();
        paperGroupRelation.setGroupId(id);
        List<PaperGroupRelationEntity> relationList = paperGroupRelationMapper.findList(paperGroupRelation);
        if (null != relationList && relationList.size() > 0) {
            String paperIds = relationList.stream().map(r -> r.getPaperId()).collect(Collectors.joining(","));
            entity.setPaperIds(paperIds);
        }
        return entity;
    }
    public Page<PaperGroupEntity> findList(PaperGroupEntity paperGroup){
        //根据目录类型查询
        if (ToolUtil.isNotEmpty(paperGroup) && ToolUtil.isNotEmpty(paperGroup.getCatalogType())) {
            PaperCatalogEntity pc = new PaperCatalogEntity();
            pc.setType(paperGroup.getCatalogType());
            List<PaperCatalogEntity> list = paperCatalogMapper.findList(pc);
            if (ToolUtil.isNotEmpty(list)) {
                List<String> catalogIdList = list.stream().map(paperCatalogEntity -> paperCatalogEntity.getId()).collect(Collectors.toList());
                paperGroup.setCatalogIdList(catalogIdList);
            }
        }
       return paperGroupMapper.findList(paperGroup);
    }

    @Transactional
    public void deleteById(String id){
        paperGroupMapper.deleteById(id);
        paperGroupRelationMapper.deleteByGroupId(id);
    }
}
