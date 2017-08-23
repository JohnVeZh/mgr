package cn.sparke.modules.simple.qrFragmentation.service;

import cn.sparke.modules.simple.qrFragmentation.entity.QrFragmentationEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.simple.qrFragmentation.mapper.QrFragmentationMapper;

/**
 * 简系列碎片化Dao
 *
 * @author spark
 * @Date 2017-08-09 17:07:15
 */
@Service
public class QrFragmentationService{
    @Autowired
    private QrFragmentationMapper qrFragmentationMapper;

    public void save(QrFragmentationEntity qrFragmentation){
        qrFragmentation.preInsert();
        qrFragmentationMapper.insert(qrFragmentation);
    }

    public void update(QrFragmentationEntity qrFragmentation){
        qrFragmentation.preUpdate();
        qrFragmentationMapper.update(qrFragmentation);
    }

    public QrFragmentationEntity get(QrFragmentationEntity qrFragmentation){
        return qrFragmentationMapper.get(qrFragmentation);
    }

    public QrFragmentationEntity getById(String id){
        return qrFragmentationMapper.getById(id);
    }
    public Page<QrFragmentationEntity> findList(QrFragmentationEntity qrFragmentation){
       return qrFragmentationMapper.findList(qrFragmentation);
    }

    public void deleteById(String id){
      qrFragmentationMapper.deleteById(id);
    }

}
