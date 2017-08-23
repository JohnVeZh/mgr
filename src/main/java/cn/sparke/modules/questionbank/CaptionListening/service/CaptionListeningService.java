package cn.sparke.modules.questionbank.CaptionListening.service;

import cn.sparke.modules.paper.paper.entity.PaperEntity;
import cn.sparke.modules.paper.paper.mapper.PaperMapper;
import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;
import cn.sparke.modules.qrcode.QrCaptionListening.mapper.QrCaptionListeningMapper;
import cn.sparke.modules.questionbank.CaptionListening.entity.CaptionListeningEntity;
import cn.sparke.modules.questionbank.CaptionListening.mapper.CaptionListeningMapper;
import cn.sparke.modules.questionbank.CaptionListeningVideo.entity.CaptionListeningVideoEntity;
import cn.sparke.modules.questionbank.CaptionListeningVideo.mapper.CaptionListeningVideoMapper;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * 字幕听力音频Dao
 *
 * @author spark
 * @Date 2017-07-26 23:01:00
 */
@Service
public class CaptionListeningService{
    @Autowired
    private CaptionListeningMapper captionListeningMapper;
    @Autowired
    private CaptionListeningVideoMapper captionListeningVideoMapper;
    @Autowired
    private QrCaptionListeningMapper qrCaptionListeningMapper;
    @Autowired
    private PaperMapper paperMapper;

    public void save(CaptionListeningEntity captionListening){

        captionListening.preInsert();
        captionListening.setAudioSize(0);
        captionListening.setSubtitleSize(0);
        //创建字幕听力
        captionListeningMapper.insert(captionListening);
    }

    public void saveQr(QrCaptionListeningEntity qrCaptionListeningEntity){

        //创建字幕听力
        CaptionListeningEntity captionListeningEntity = new CaptionListeningEntity();
        captionListeningEntity.preInsert();
        String captionListeningId = captionListeningEntity.getId();
        captionListeningEntity.setPaperId(qrCaptionListeningEntity.getPaperId());
        captionListeningEntity.setAudioUrl(qrCaptionListeningEntity.getAudioUrl());
        captionListeningEntity.setSubtitleUrl(qrCaptionListeningEntity.getSubtitleUrl());
        captionListeningEntity.setAudioSize(0);
        captionListeningEntity.setSubtitleSize(0);
        captionListeningEntity.preUpdate();

        //创建试卷对象
        PaperEntity paperEntity = new PaperEntity();
        paperEntity.setId(qrCaptionListeningEntity.getPaperId());
        paperEntity.setQrCode(qrCaptionListeningEntity.getQrCode());
        paperEntity.preUpdate();

        qrCaptionListeningEntity.setCaptionListeningId(captionListeningId);
        qrCaptionListeningEntity.preInsert();
        //captionListeningEntity.setSubtitleSize(0);
        qrCaptionListeningMapper.insert(qrCaptionListeningEntity);
        captionListeningMapper.insert(captionListeningEntity);
        paperMapper.update(paperEntity);
    }


    public void update(CaptionListeningEntity captionListening){
        captionListening.preUpdate();
        captionListeningMapper.update(captionListening);
    }

    public void updateQr(QrCaptionListeningEntity qrCaptionListeningEntity){

        qrCaptionListeningEntity.preUpdate();
        //创建字幕听力对象
        CaptionListeningEntity captionListeningEntity = new CaptionListeningEntity();
        captionListeningEntity.setId(qrCaptionListeningEntity.getCaptionListeningId());
        captionListeningEntity.setAudioUrl(qrCaptionListeningEntity.getAudioUrl());
        captionListeningEntity.setSubtitleUrl(qrCaptionListeningEntity.getSubtitleUrl());
        captionListeningEntity.preUpdate();
        //试卷对象
        PaperEntity paperEntity = new PaperEntity();
        paperEntity.setId(qrCaptionListeningEntity.getPaperId());
        paperEntity.setQrCode(qrCaptionListeningEntity.getQrCode());
        paperEntity.preUpdate();
        //captionListeningEntity.setAudioSize();
        //判断二维码对象是否存在
        String qrPaperCaptionListeningId=qrCaptionListeningEntity.getId();
        if(qrPaperCaptionListeningId != null && !qrPaperCaptionListeningId.equals("")){//存在直接更新
            qrCaptionListeningMapper.update(qrCaptionListeningEntity);
        }else {//不存在直接插入
            qrCaptionListeningEntity.preInsert();
            qrCaptionListeningMapper.insert(qrCaptionListeningEntity);
        }
        captionListeningMapper.update(captionListeningEntity);

        paperMapper.update(paperEntity);



    }

    public CaptionListeningEntity get(CaptionListeningEntity captionListening){
        return captionListeningMapper.get(captionListening);
    }

    public CaptionListeningEntity getById(String id){
        return captionListeningMapper.getById(id);
    }
    //getByIdQr
    public QrCaptionListeningEntity getByIdQr(String id){
        return qrCaptionListeningMapper.getById(id);
    }
//    public CaptionListeningEntity getByIds(Map<String,Object> dataMap){
//        return captionListeningMapper.getByIds(dataMap);
//    }
//    public CaptionListeningEntity getQrByIds(Map<String,Object> dataMap){
//        return captionListeningMapper.getQrByIds(dataMap);
//    }
    public Page<CaptionListeningEntity> findList(CaptionListeningEntity captionListening){
       return captionListeningMapper.findList(captionListening);
    }

//    public Page<CaptionListeningEntity> findQrList(CaptionListeningEntity captionListening){
//        return captionListeningMapper.findQrList(captionListening);
//    }

    public void deleteById(String id){
      captionListeningMapper.deleteById(id);
    }

    public void deleteQrById(String id){
        qrCaptionListeningMapper.deleteById(id);
    }

}
