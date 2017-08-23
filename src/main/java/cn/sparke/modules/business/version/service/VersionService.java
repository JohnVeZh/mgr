package cn.sparke.modules.business.version.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.business.version.mapper.VersionMapper;
import cn.sparke.modules.business.version.entity.VersionEntity;

/**
 * 版本Dao
 *
 * @author spark
 * @Date 2017-07-26 10:08:44
 */
@Service
public class VersionService{
    @Autowired
    private VersionMapper versionMapper;

    public void save(VersionEntity version){
        version.preInsert();
        versionMapper.insert(version);
    }

    public void update(VersionEntity version){
        version.preUpdate();
        versionMapper.update(version);
    }

    public VersionEntity get(VersionEntity version){
        return versionMapper.get(version);
    }

    public VersionEntity getById(String id){
        return versionMapper.getById(id);
    }
    public Page<VersionEntity> findList(VersionEntity version){
       return versionMapper.findList(version);
    }

    public void deleteById(String id){
      versionMapper.deleteById(id);
    }

}
