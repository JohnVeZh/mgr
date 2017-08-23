package cn.sparke.modules.qrcode.qrCode.service;

import cn.sparke.modules.qrcode.qrCode.entity.QrCodeEntity;
import cn.sparke.modules.qrcode.qrCode.mapper.QrCodeMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 二维码Dao
 *
 * @author spark
 * @Date 2017-07-21 20:14:52
 */
@Service
public class QrCodeService{
    @Autowired
    private QrCodeMapper qrCodeMapper;

    public void save(QrCodeEntity qrCode){
        qrCode.preInsert();
        qrCodeMapper.insert(qrCode);
    }

    public void update(QrCodeEntity qrCode){
        qrCode.preUpdate();
        qrCodeMapper.update(qrCode);
    }

    public QrCodeEntity get(QrCodeEntity qrCode){
        return qrCodeMapper.get(qrCode);
    }

    public QrCodeEntity getById(String id){
        return qrCodeMapper.getById(id);
    }
    public Page<QrCodeEntity> findList(QrCodeEntity qrCode){
       return qrCodeMapper.findList(qrCode);
    }

    public void deleteById(String id){
      qrCodeMapper.deleteById(id);
    }

    public QrCodeEntity get(String qrCode, int type) {
        QrCodeEntity condition = new QrCodeEntity();
        condition.setCode(qrCode);
        condition.setType(type);
        return qrCodeMapper.get(condition);
    }
}
