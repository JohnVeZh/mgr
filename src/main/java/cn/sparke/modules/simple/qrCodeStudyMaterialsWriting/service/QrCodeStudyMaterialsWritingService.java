package cn.sparke.modules.simple.qrCodeStudyMaterialsWriting.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.simple.qrCodeStudyMaterialsWriting.mapper.QrCodeStudyMaterialsWritingMapper;
import cn.sparke.modules.simple.qrCodeStudyMaterialsWriting.entity.QrCodeStudyMaterialsWritingEntity;

/**
 * 简系列写作翻译阅读Dao
 *
 * @author spark
 * @Date 2017-08-10 10:58:45
 */
@Service
public class QrCodeStudyMaterialsWritingService{
    @Autowired
    private QrCodeStudyMaterialsWritingMapper qrCodeStudyMaterialsWritingMapper;

    public void save(QrCodeStudyMaterialsWritingEntity qrCodeStudyMaterialsWriting){
        qrCodeStudyMaterialsWriting.preInsert();
        qrCodeStudyMaterialsWritingMapper.insert(qrCodeStudyMaterialsWriting);
    }

    public void update(QrCodeStudyMaterialsWritingEntity qrCodeStudyMaterialsWriting){
        qrCodeStudyMaterialsWriting.preUpdate();
        qrCodeStudyMaterialsWritingMapper.update(qrCodeStudyMaterialsWriting);
    }

    public QrCodeStudyMaterialsWritingEntity get(QrCodeStudyMaterialsWritingEntity qrCodeStudyMaterialsWriting){
        return qrCodeStudyMaterialsWritingMapper.get(qrCodeStudyMaterialsWriting);
    }

    public QrCodeStudyMaterialsWritingEntity getById(String id){
        return qrCodeStudyMaterialsWritingMapper.getById(id);
    }
    public Page<QrCodeStudyMaterialsWritingEntity> findList(QrCodeStudyMaterialsWritingEntity qrCodeStudyMaterialsWriting){
       return qrCodeStudyMaterialsWritingMapper.findList(qrCodeStudyMaterialsWriting);
    }

    public void deleteById(String id){
      qrCodeStudyMaterialsWritingMapper.deleteById(id);
    }

}
