package cn.sparke.modules.qrcode.QrCaptionListening.service;

import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;
import cn.sparke.modules.qrcode.QrCaptionListening.mapper.QrCaptionListeningMapper;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 二维码试卷字幕听力Dao
 *
 * @author spark
 * @Date 2017-07-26 23:27:40
 */
@Service
public class QrCaptionListeningService{
    @Autowired
    private QrCaptionListeningMapper qrCaptionListeningMapper;

    public void save(QrCaptionListeningEntity qrCaptionListening){

        String captionListeningId ="";
        //二维码试卷字幕听力
        QrCaptionListeningEntity qrCaptionListeningEntity = new QrCaptionListeningEntity();
        qrCaptionListeningEntity.preInsert();
        qrCaptionListeningEntity.setSort(0);
        qrCaptionListeningEntity.setCaptionListeningId(captionListeningId);
        qrCaptionListeningEntity.setQrCode(qrCaptionListening.getQrCode());
        qrCaptionListening.preInsert();

        //

        qrCaptionListeningMapper.insert(qrCaptionListening);
    }

    public void update(QrCaptionListeningEntity qrCaptionListening){
        qrCaptionListening.preUpdate();
        qrCaptionListeningMapper.update(qrCaptionListening);
    }

    public QrCaptionListeningEntity get(QrCaptionListeningEntity qrCaptionListening){
        return qrCaptionListeningMapper.get(qrCaptionListening);
    }

    public QrCaptionListeningEntity getById(String id){
        return qrCaptionListeningMapper.getById(id);
    }
    public Page<QrCaptionListeningEntity> findList(QrCaptionListeningEntity qrCaptionListening){
       return qrCaptionListeningMapper.findList(qrCaptionListening);
    }

    public void deleteById(String id){
      qrCaptionListeningMapper.deleteById(id);
    }

    public QrCaptionListeningEntity get(String qrCode){
        QrCaptionListeningEntity qrCaptionListening = new QrCaptionListeningEntity();
        qrCaptionListening.setQrCode(qrCode);
        return qrCaptionListeningMapper.get(qrCaptionListening);
    }

}
