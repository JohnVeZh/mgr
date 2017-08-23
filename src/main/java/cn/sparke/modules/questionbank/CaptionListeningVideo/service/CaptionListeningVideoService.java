package cn.sparke.modules.questionbank.CaptionListeningVideo.service;

import cn.sparke.modules.questionbank.CaptionListeningVideo.entity.CaptionListeningVideoEntity;
import cn.sparke.modules.questionbank.CaptionListeningVideo.mapper.CaptionListeningVideoMapper;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 字幕听力视频Dao
 *
 * @author spark
 * @Date 2017-07-26 22:58:39
 */
@Service
public class CaptionListeningVideoService{
    @Autowired
    private CaptionListeningVideoMapper captionListeningVideoMapper;

    public void save(CaptionListeningVideoEntity captionListeningVideo){
        captionListeningVideo.preInsert();
        captionListeningVideoMapper.insert(captionListeningVideo);
    }

    public void update(CaptionListeningVideoEntity captionListeningVideo){
        captionListeningVideo.preUpdate();
        captionListeningVideoMapper.update(captionListeningVideo);
    }

    public CaptionListeningVideoEntity get(CaptionListeningVideoEntity captionListeningVideo){
        return captionListeningVideoMapper.get(captionListeningVideo);
    }

    public CaptionListeningVideoEntity getById(String id){
        return captionListeningVideoMapper.getById(id);
    }
    public Page<CaptionListeningVideoEntity> findList(CaptionListeningVideoEntity captionListeningVideo){
       return captionListeningVideoMapper.findList(captionListeningVideo);
    }

    public void deleteById(String id){
      captionListeningVideoMapper.deleteById(id);
    }

}
