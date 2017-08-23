package cn.sparke.modules.startPage.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.startPage.mapper.StartPageMapper;
import cn.sparke.modules.startPage.entity.StartPageEntity;

/**
 * 启动页Dao
 *
 * @author spark
 * @Date 2017-08-02 21:27:28
 */
@Service
public class StartPageService{
    @Autowired
    private StartPageMapper startPageMapper;

    public void save(StartPageEntity startPage){
        startPage.preInsert();
        startPageMapper.insert(startPage);
    }

    public void update(StartPageEntity startPage){
        startPage.preUpdate();
        startPageMapper.update(startPage);
    }
    public void updateIsShow(StartPageEntity startPage){
        startPage.preUpdate();
        startPageMapper.updateIsShow(startPage);
    }
    public StartPageEntity get(StartPageEntity startPage){
        return startPageMapper.get(startPage);
    }

    public StartPageEntity getById(String id){
        return startPageMapper.getById(id);
    }
    public Page<StartPageEntity> findList(StartPageEntity startPage){
       return startPageMapper.findList(startPage);
    }

    public void deleteById(String id){
      startPageMapper.deleteById(id);
    }

}
