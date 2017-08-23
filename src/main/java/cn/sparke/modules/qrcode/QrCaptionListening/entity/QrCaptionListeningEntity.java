package cn.sparke.modules.qrcode.QrCaptionListening.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.questionbank.question.entity.QuestionContentType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.math.*;

/**
 * 二维码试卷字幕听力Entity
 *
 * @author spark
 * @Date 2017-07-26 23:27:40
 */
public class QrCaptionListeningEntity extends BaseEntity{
    //字幕听力id
    private String captionListeningId;
    //二维码
    @NotEmpty(message = "二维码不能为空")
    private String qrCode;

    //拓展项

    //试卷id
    private String paperId;
    //音频地址
    @NotEmpty(message = "音频地址不能为空")
    @Pattern(regexp="^http.*", message = "音频地址请以http或https开头")
    private String audioUrl;
    //字幕文件地址
    @NotEmpty(message = "字幕文件地址不能为空")
    @Pattern(regexp="^http.*", message = "字幕文件地址请以http或https开头")
    private String subtitleUrl;
    //音频文件大小,单位字节
    @NotNull(message = "音频文件大小不能为空")
    private Integer audioSize;
    //字幕文件大小,单位字节
    @NotNull(message = "字幕文件大小不能为空")
    private Integer subtitleSize ;
    //内容类型（0.不区分1.听力2.阅读3.翻译4.写作;默认0）
    private Integer contentType;
    //内容类型名称
    private String contentTypeName;

    //视频名称
    private  String name;

    //cc视频id
    private String ccId;

    public void setCaptionListeningId(String captionListeningId){
        this.captionListeningId = captionListeningId;
    }
    public String getCaptionListeningId(){
        return this.captionListeningId;
    }
    public void setQrCode(String qrCode){
        this.qrCode = qrCode;
    }
    public String getQrCode(){
        return this.qrCode;
    }

    public String getPaperId() {
        return paperId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public String getSubtitleUrl() {
        return subtitleUrl;
    }

    public Integer getAudioSize() {
        return audioSize;
    }

    public Integer getSubtitleSize() {
        return subtitleSize;
    }

    public String getName() {
        return name;
    }

    public String getCcId() {
        return ccId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public void setSubtitleUrl(String subtitleUrl) {
        this.subtitleUrl = subtitleUrl;
    }

    public void setAudioSize(Integer audioSize) {
        this.audioSize = audioSize;
    }

    public void setSubtitleSize(Integer subtitleSize) {
        this.subtitleSize = subtitleSize;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
        if(contentType != null && !contentType.equals("")){
            switch (contentType){
                case 1:
                    this.contentTypeName= QuestionContentType.CONTENT_LISTENING_NAME;
                    break;
                case 2:
                    this.contentTypeName= QuestionContentType.CONTENT_READING_NAME;
                    break;
                case 3:
                    this.contentTypeName= QuestionContentType.CONTENT_TRANSLATION_NAME;
                    break;
                case 4:
                    this.contentTypeName= QuestionContentType.CONTENT_WRITING_NAME;
                    break;
            }
        }
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public Integer getContentType() {

        return contentType;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }
}
