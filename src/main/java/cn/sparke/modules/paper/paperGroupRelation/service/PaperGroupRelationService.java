package cn.sparke.modules.paper.paperGroupRelation.service;

import cn.sparke.modules.paper.paperGroupRelation.entity.PaperGroupRelationEntity;
import cn.sparke.modules.paper.paperGroupRelation.mapper.PaperGroupRelationMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 试卷组关系Dao
 *
 * @author spark
 * @Date 2017-07-27 16:23:44
 */
@Service
public class PaperGroupRelationService{
    @Autowired
    private PaperGroupRelationMapper paperGroupRelationMapper;

    public void save(PaperGroupRelationEntity paperGroupRelation){
        paperGroupRelation.preInsert();
        paperGroupRelationMapper.insert(paperGroupRelation);
    }

    public void update(PaperGroupRelationEntity paperGroupRelation){
        paperGroupRelation.preUpdate();
        paperGroupRelationMapper.update(paperGroupRelation);
    }

    public PaperGroupRelationEntity get(PaperGroupRelationEntity paperGroupRelation){
        return paperGroupRelationMapper.get(paperGroupRelation);
    }

    public PaperGroupRelationEntity getById(String id){
        return paperGroupRelationMapper.getById(id);
    }
    public Page<PaperGroupRelationEntity> findList(PaperGroupRelationEntity paperGroupRelation){
       return paperGroupRelationMapper.findList(paperGroupRelation);
    }

    public void deleteById(String id){
      paperGroupRelationMapper.deleteById(id);
    }

}
