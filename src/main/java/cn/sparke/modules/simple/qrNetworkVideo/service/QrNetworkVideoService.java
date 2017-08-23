package cn.sparke.modules.simple.qrNetworkVideo.service;

import cn.sparke.modules.simple.qrNetworkVideo.entity.QrNetworkVideoEntity;
import cn.sparke.modules.simple.qrNetworkVideo.mapper.QrNetworkVideoMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

/**
 * 简系列视频Dao
 *
 * @author spark
 * @Date 2017-08-09 14:26:29
 */
@Service
public class QrNetworkVideoService{
    @Autowired
    private QrNetworkVideoMapper qrNetworkVideoMapper;

    public void save(QrNetworkVideoEntity qrNetworkVideo){
        qrNetworkVideo.preInsert();
        qrNetworkVideoMapper.insert(qrNetworkVideo);
    }

    public void update(QrNetworkVideoEntity qrNetworkVideo){
        qrNetworkVideo.preUpdate();
        qrNetworkVideoMapper.update(qrNetworkVideo);
    }

    public QrNetworkVideoEntity get(QrNetworkVideoEntity qrNetworkVideo){
        return qrNetworkVideoMapper.get(qrNetworkVideo);
    }

    public QrNetworkVideoEntity getById(String id){
        return qrNetworkVideoMapper.getById(id);
    }
    public Page<QrNetworkVideoEntity> findList(QrNetworkVideoEntity qrNetworkVideo){
       return qrNetworkVideoMapper.findList(qrNetworkVideo);
    }

    public void deleteById(String id){
      qrNetworkVideoMapper.deleteById(id);
    }

}
