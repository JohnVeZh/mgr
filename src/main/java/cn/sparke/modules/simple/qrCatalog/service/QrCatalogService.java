package cn.sparke.modules.simple.qrCatalog.service;

import cn.sparke.modules.simple.qrCatalog.entity.QrCatalogEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.simple.qrCatalog.mapper.QrCatalogMapper;

/**
 * 简系列目录Dao
 *
 * @author spark
 * @Date 2017-08-09 10:23:51
 */
@Service
public class QrCatalogService{
    @Autowired
    private QrCatalogMapper qrCatalogMapper;

    public void save(QrCatalogEntity qrCatalog){
        qrCatalog.preInsert();
        qrCatalogMapper.insert(qrCatalog);
    }

    public void update(QrCatalogEntity qrCatalog){
        qrCatalog.preUpdate();
        qrCatalogMapper.update(qrCatalog);
    }

    public QrCatalogEntity get(QrCatalogEntity qrCatalog){
        return qrCatalogMapper.get(qrCatalog);
    }

    public QrCatalogEntity getById(String id){
        return qrCatalogMapper.getById(id);
    }
    public Page<QrCatalogEntity> findList(QrCatalogEntity qrCatalog){
       return qrCatalogMapper.findList(qrCatalog);
    }

    public void deleteById(String id){
      qrCatalogMapper.deleteById(id);
    }

}
