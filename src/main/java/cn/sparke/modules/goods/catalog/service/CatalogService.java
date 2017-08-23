package cn.sparke.modules.goods.catalog.service;

import cn.sparke.modules.goods.catalog.entity.CatalogEntity;
import cn.sparke.modules.goods.catalog.mapper.CatalogMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

/**
 * 课程目录Dao
 *
 * @author spark
 * @Date 2017-07-24 18:29:41
 */
@Service
public class CatalogService{
    @Autowired
    private CatalogMapper catalogMapper;

    public void save(CatalogEntity catalog){
        catalog.preInsert();
        catalogMapper.insert(catalog);
    }

    public void update(CatalogEntity catalog){
        catalog.preUpdate();
        catalogMapper.update(catalog);
    }

    public CatalogEntity get(CatalogEntity catalog){
        return catalogMapper.get(catalog);
    }

    public CatalogEntity getById(String id){
        return catalogMapper.getById(id);
    }
    public Page<CatalogEntity> findList(CatalogEntity catalog){
       return catalogMapper.findList(catalog);
    }

    public void deleteById(String id){
      catalogMapper.deleteById(id);
    }

}
