package cn.sparke.modules.paper.paperCatalog.service;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.paper.paper.mapper.PaperMapper;
import cn.sparke.modules.paper.paperCatalog.entity.CatalogTreeNode;
import cn.sparke.modules.paper.paperCatalog.entity.PaperCatalogEntity;
import cn.sparke.modules.paper.paperCatalog.mapper.PaperCatalogMapper;
import cn.sparke.modules.section.entity.SectionEntity;
import cn.sparke.modules.section.mapper.SectionMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 试卷目录Dao
 *
 * @author spark
 * @Date 2017-07-19 09:51:45
 */
@Service
public class PaperCatalogService{
    @Autowired
    private PaperCatalogMapper paperCatalogMapper;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private SectionMapper sectionMapper;

    public void save(PaperCatalogEntity paperCatalog){
        paperCatalog.preInsert();
        paperCatalogMapper.insert(paperCatalog);
    }

    public void update(PaperCatalogEntity paperCatalog){
        paperCatalog.preUpdate();
        paperCatalogMapper.update(paperCatalog);
    }

    public PaperCatalogEntity get(PaperCatalogEntity paperCatalog){
        return paperCatalogMapper.get(paperCatalog);
    }

    public PaperCatalogEntity getById(String id){
        PaperCatalogEntity entity = paperCatalogMapper.getById(id);
        if (ToolUtil.isNotEmpty(entity) && !entity.getParentId().equals("0")) {
            PaperCatalogEntity pEntity = paperCatalogMapper.getById(entity.getParentId());
            entity.setParentName(ToolUtil.isNotEmpty(pEntity)? pEntity.getName() : "");
        } else {
            entity.setParentName("顶级");
        }
        return entity;
    }
    public Page<PaperCatalogEntity> findList(PaperCatalogEntity paperCatalog){
       return paperCatalogMapper.findList(paperCatalog);
    }

    /**
     * 删除检查
     * @param id
     * @return
     */
    public String check(String id){
        //是否存在子级
        PaperCatalogEntity paperCatalog = new PaperCatalogEntity();
        paperCatalog.setParentId(id);
        List<PaperCatalogEntity> paperCatalogEntityList = paperCatalogMapper.findList(paperCatalog);
        if (ToolUtil.isNotEmpty(paperCatalogEntityList) && paperCatalogEntityList.size() > 0) return "parent";
        //是否存在引用
        PaperEntity paperEntity = new PaperEntity();
        paperEntity.setCatalogId(id);
        List<PaperEntity> paperEntityList = paperMapper.findList(paperEntity);
        if (ToolUtil.isNotEmpty(paperEntityList) && paperEntityList.size() > 0) return "paper";
        return "success";
    }

    public void deleteById(String id){
      paperCatalogMapper.deleteById(id);
    }

    /**
     * 获取目录树
     * @param list
     * @return
     */
    public List<ZTreeNode> sectionTreeList(List<String> list){
//        List<ZTreeNode> ZTreeNodelist = paperCatalogMapper.sectionTreeList(list);
//        ZTreeNodelist.add(ZTreeNode.createParent());
//        return ZTreeNodelist;
        List<ZTreeNode> ZTreeNodelist = new ArrayList<>();
        ZTreeNodelist.add(ZTreeNode.createParent());
        //所有学段
        List<SectionEntity> sectionList = sectionMapper.findList(null);
        //所有目录
        List<CatalogTreeNode> catalogTreeNodeList = paperCatalogMapper.catalogTreeList(list);
        if (ToolUtil.isNotEmpty(sectionList) && ToolUtil.isNotEmpty(catalogTreeNodeList)) {
            sectionList.stream().forEach(sectionEntity -> {
                ZTreeNode sectionTreeNode = new ZTreeNode();
                sectionTreeNode.setId(sectionEntity.getId());
                sectionTreeNode.setpId("0");
                sectionTreeNode.setName(sectionEntity.getName());
                sectionTreeNode.setIsOpen(false);
                sectionTreeNode.setChecked(false);
                ZTreeNodelist.add(sectionTreeNode);

                //1.全真考场 2.字幕听力 3.简系列 4.专项练习,5扫码字幕听力
                ZTreeNode treeNode1 = new ZTreeNode();
                treeNode1.setId(sectionEntity.getCode() + "-1");
                treeNode1.setpId(sectionTreeNode.getId());
                treeNode1.setName("全真考场");
                treeNode1.setIsOpen(false);
                treeNode1.setChecked(false);
                ZTreeNodelist.add(treeNode1);

                ZTreeNode treeNode2 = new ZTreeNode();
                treeNode2.setId(sectionEntity.getCode() + "-2");
                treeNode2.setpId(sectionTreeNode.getId());
                treeNode2.setName("字幕听力");
                treeNode2.setIsOpen(false);
                treeNode2.setChecked(false);
                ZTreeNodelist.add(treeNode2);

                ZTreeNode treeNode3 = new ZTreeNode();
                treeNode3.setId(sectionEntity.getCode() + "-3");
                treeNode3.setpId(sectionTreeNode.getId());
                treeNode3.setName("简系列");
                treeNode3.setIsOpen(false);
                treeNode3.setChecked(false);
                ZTreeNodelist.add(treeNode3);

                ZTreeNode treeNode4 = new ZTreeNode();
                treeNode4.setId(sectionEntity.getCode() + "-4");
                treeNode4.setpId(sectionTreeNode.getId());
                treeNode4.setName("专项练习");
                treeNode4.setIsOpen(false);
                treeNode4.setChecked(false);
                ZTreeNodelist.add(treeNode4);

                ZTreeNode treeNode5 = new ZTreeNode();
                treeNode5.setId(sectionEntity.getCode() + "-5");
                treeNode5.setpId(sectionTreeNode.getId());
                treeNode5.setName("扫码字幕听力");
                treeNode5.setIsOpen(false);
                treeNode5.setChecked(false);
                ZTreeNodelist.add(treeNode5);

                //父级目录处理
                catalogTreeNodeList.stream().filter(catalogTreeNode -> catalogTreeNode.getSectionCode() == sectionEntity.getCode()
                        && catalogTreeNode.getpId().equals("0")).forEach(catalogTreeNode -> {
                    switch (catalogTreeNode.getType()) {
                        case 1 : {
                            catalogTreeNode.setpId(treeNode1.getId());
                            break;
                        }
                        case 2 : {
                            catalogTreeNode.setpId(treeNode2.getId());
                            break;
                        }
                        case 3 : {
                            catalogTreeNode.setpId(treeNode3.getId());
                            break;
                        }
                        case 4 : {
                            catalogTreeNode.setpId(treeNode4.getId());
                            break;
                        }
                        case 5 : {
                            catalogTreeNode.setpId(treeNode5.getId());
                            break;
                        }
                    }
                });
            });
            //目录转换树model
            List<ZTreeNode> catalogZTreeNodelist = catalogTreeNodeList.stream().map(catalogTreeNode -> {
                return new ZTreeNode(catalogTreeNode.getId(),catalogTreeNode.getpId(),catalogTreeNode.getName(),catalogTreeNode.getOpen(),catalogTreeNode.getChecked());
            }).collect(Collectors.toList());
            ZTreeNodelist.addAll(catalogZTreeNodelist);
        }
        return ZTreeNodelist;
    }

    /**
     * 根据不同模块获取目录树
     * @param entity
     * @return
     */
    public List<ZTreeNode> catalogTree(PaperCatalogEntity entity){
        List<ZTreeNode> ZTreeNodelist = new ArrayList<>();
        ZTreeNodelist.add(ZTreeNode.createParent());
        //所有学段
        List<SectionEntity> sectionList = sectionMapper.findList(null);
        //所有目录
        List<CatalogTreeNode> catalogTreeNodeList = paperCatalogMapper.catalogTree(entity);

        if (ToolUtil.isNotEmpty(sectionList) && ToolUtil.isNotEmpty(catalogTreeNodeList)) {
            sectionList.stream().filter(sectionEntity -> sectionEntity.getCode() == 1 || sectionEntity.getCode() == 2)
                    .forEach(sectionEntity -> {
                ZTreeNode sectionTreeNode = new ZTreeNode();
                sectionTreeNode.setId(sectionEntity.getId());
                sectionTreeNode.setpId("0");
                sectionTreeNode.setName(sectionEntity.getName());
                sectionTreeNode.setIsOpen(true);
                sectionTreeNode.setChecked(false);
                ZTreeNodelist.add(sectionTreeNode);

                //父级目录处理
                catalogTreeNodeList.stream().filter(catalogTreeNode -> catalogTreeNode.getSectionCode() == sectionEntity.getCode()
                        && catalogTreeNode.getpId().equals("0")).forEach(catalogTreeNode -> {
                    catalogTreeNode.setpId(sectionTreeNode.getId());
                });
            });
            //目录转换树model
            List<ZTreeNode> catalogZTreeNodelist = catalogTreeNodeList.stream().map(catalogTreeNode -> {
                return new ZTreeNode(catalogTreeNode.getId(),catalogTreeNode.getpId(),catalogTreeNode.getName(),catalogTreeNode.getOpen(),catalogTreeNode.getChecked());
            }).collect(Collectors.toList());
            ZTreeNodelist.addAll(catalogZTreeNodelist);
        }

        return ZTreeNodelist;
    }

}
