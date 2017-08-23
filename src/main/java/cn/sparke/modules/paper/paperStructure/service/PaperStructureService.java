package cn.sparke.modules.paper.paperStructure.service;

import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.entity.StructureTreeNode;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 试卷树形结构Dao
 *
 * @author spark
 * @Date 2017-07-19 09:52:16
 */
@Service
public class PaperStructureService {
    @Autowired
    private PaperStructureMapper paperStructureMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;

    public void save(PaperStructureEntity paperStructure) {
        paperStructure.preInsert();

        PaperStructureEntity parent = paperStructureMapper.getParentStructure(paperStructure.getParentId());
        //未删除
        paperStructure.setDelFlag(0);

        paperStructure.setIsLeaf(0);

        StringBuilder parentIds = new StringBuilder();

        PaperStructureEntity parentNode;

        String tempParentId = paperStructure.getParentId();

        //父级结构存在
        if (ToolUtil.isNotEmpty(parent)) {
            if (ToolUtil.isEmpty(parent.getParentId()) || parent != null && parent.getParentId().equals("0")) {
                paperStructure.setLevel(1);
            } else {
                int level = parent.getLevel();
                paperStructure.setLevel(level + 1);
            }
            //获取所有父结构
            while (true) {
                parentNode = paperStructureMapper.getParentStructure(tempParentId);
                if (parentNode != null && parentNode.getParentId() != null && !parentNode.getParentId().equals("0")) {
                    parentIds.append(parentNode.getParentId()).append(",");
                    tempParentId = parentNode.getParentId();
                } else {
                    break;
                }
            }
            String ids = parentIds.toString();
            if (ids.length() > 0) {
                paperStructure.setParentIds(ids.substring(0, ids.length() - 1));
            } else {
                paperStructure.setParentIds("");
            }

            //父结构不存在
        } else {
            paperStructure.setParentIds("");
            paperStructure.setLevel(1);
            paperStructure.setParentId("0");
        }


        paperStructureMapper.insert(paperStructure);

        //检查并修改父节点是否为叶节点

        if (parent != null) {
            if (parent.getIsLeaf() == 0) {
                parent.setIsLeaf(1);
                paperStructureMapper.update(parent);
            }

        }
    }


    public void insert(PaperStructureEntity paperStructure) {
        paperStructure.preInsert();
        paperStructureMapper.insert(paperStructure);
    }

    public void update(PaperStructureEntity paperStructure) {
        paperStructure.preUpdate();
        paperStructureMapper.update(paperStructure);
    }

    public PaperStructureEntity get(PaperStructureEntity paperStructure) {
        return paperStructureMapper.get(paperStructure);
    }

    public PaperStructureEntity getById(String id) {

        PaperStructureEntity entity = paperStructureMapper.getById(id);

        if (ToolUtil.isNotEmpty(entity) && ToolUtil.isNotEmpty(entity.getParentId())) {
            PaperStructureEntity parentEntity = paperStructureMapper.getById(entity.getParentId());
            entity.setParentName(ToolUtil.isNotEmpty(parentEntity) ? parentEntity.getName() : "");
        }
        return entity;
    }

    public Page<PaperStructureEntity> findList(PaperStructureEntity paperStructure) {
        return paperStructureMapper.findList(paperStructure);
    }

    /**
     * 获取结构树
     * @param paperStructure
     * @return
     */
    public List<ZTreeNode> structureTree(PaperStructureEntity paperStructure) {
        List<ZTreeNode> zTreeNodeList = new ArrayList<>();
        List<StructureTreeNode> structureTreeNodeList = paperStructureMapper.structureTreeList(paperStructure);

        //听力
        ZTreeNode listening = new ZTreeNode("1","0","听力",true,false);
        //阅读
        ZTreeNode reading = new ZTreeNode("2","0","阅读",true,false);
        //翻译
        ZTreeNode translation = new ZTreeNode("3","0","翻译",true,false);
        //写作
        ZTreeNode writing = new ZTreeNode("4","0","写作",true,false);

        if (paperStructure.getContentType() == 0 || paperStructure.getContentType() == 1)
            zTreeNodeList.add(listening);
        if (paperStructure.getContentType() == 0 || paperStructure.getContentType() == 2)
            zTreeNodeList.add(reading);
        if (paperStructure.getContentType() == 0 || paperStructure.getContentType() == 3)
            zTreeNodeList.add(translation);
        if (paperStructure.getContentType() == 0 || paperStructure.getContentType() == 4)
            zTreeNodeList.add(writing);

        if (ToolUtil.isNotEmpty(structureTreeNodeList)) {
            structureTreeNodeList.stream().filter(structureTreeNode -> structureTreeNode.getpId().equals("0")).forEach(structureTreeNode -> {
                switch (structureTreeNode.getContentType()) {
                    case 1 : {
                        structureTreeNode.setpId(listening.getId());
                        break;
                    }
                    case 2 : {
                        structureTreeNode.setpId(reading.getId());
                        break;
                    }
                    case 3 : {
                        structureTreeNode.setpId(translation.getId());
                        break;
                    }
                    case 4 : {
                        structureTreeNode.setpId(writing.getId());
                        break;
                    }
                }
            });
            //转换树model
            List<ZTreeNode> structureZTreeNodelist = structureTreeNodeList.stream().map(structureTreeNode -> {
                return new ZTreeNode(structureTreeNode.getId(),structureTreeNode.getpId(),structureTreeNode.getName(),structureTreeNode.getOpen(),structureTreeNode.getChecked());
            }).collect(Collectors.toList());
            zTreeNodeList.addAll(structureZTreeNodelist);
        }

        return zTreeNodeList;
    }

    public void deleteById(String id) {
        paperStructureMapper.deleteById(id);
    }

    public String check(String id) {

        List<String> childList = paperStructureMapper.getChildStructure(id);
        if (ToolUtil.isNotEmpty(childList)) {
            return "childStruture";
        }

        PaperQuestionEntity paperQuestionEntity = new PaperQuestionEntity();
        paperQuestionEntity.setStructureId(id);

        List<PaperQuestionEntity> entityList = paperQuestionMapper.findList(paperQuestionEntity);
        if (ToolUtil.isNotEmpty(entityList)) {
            return "paper";
        }

        return "success";
    }

}
