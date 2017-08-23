package cn.sparke.modules.simple.qrCaptionListening.entity;

import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;
import cn.sparke.modules.qrcode.qrCode.entity.QrCodeEntity;
import cn.sparke.modules.questionbank.CaptionListening.entity.CaptionListeningEntity;
import org.springframework.validation.annotation.Validated;
@Validated
public class SimpleCaptionListeningEntity extends QrCodeEntity {
    private QrCaptionListeningEntity qrCaptionListeningEntity;
    private CaptionListeningEntity captionListeningEntity;


    public QrCaptionListeningEntity getQrCaptionListeningEntity() {
        return qrCaptionListeningEntity;
    }

    public void setQrCaptionListeningEntity(QrCaptionListeningEntity qrCaptionListeningEntity) {
        this.qrCaptionListeningEntity = qrCaptionListeningEntity;
    }

    public CaptionListeningEntity getCaptionListeningEntity() {
        return captionListeningEntity;
    }

    public void setCaptionListeningEntity(CaptionListeningEntity captionListeningEntity) {
        this.captionListeningEntity = captionListeningEntity;
    }
}
