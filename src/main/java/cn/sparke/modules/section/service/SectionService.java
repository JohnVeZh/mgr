package cn.sparke.modules.section.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.section.mapper.SectionMapper;
import cn.sparke.modules.section.entity.SectionEntity;

/**
 * 学段Dao
 *
 * @author spark
 * @Date 2017-07-19 16:33:08
 */
@Service
public class SectionService{
    @Autowired
    private SectionMapper sectionMapper;

    public void save(SectionEntity section){
        section.preInsert();
        sectionMapper.insert(section);
    }

    public void update(SectionEntity section){
        section.preUpdate();
        sectionMapper.update(section);
    }

    public SectionEntity get(SectionEntity section){
        return sectionMapper.get(section);
    }

    public SectionEntity getById(String id){
        return sectionMapper.getById(id);
    }
    public Page<SectionEntity> findList(SectionEntity section){
       return sectionMapper.findList(section);
    }

    public void deleteById(String id){
      sectionMapper.deleteById(id);
    }

}
