package cn.sparke.modules.questionbank.CaptionListeningVideo.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.questionbank.question.entity.QuestionContentType;

import java.util.*;
import java.math.*;

/**
 * 字幕听力视频Entity
 *
 * @author spark
 * @Date 2017-07-26 22:58:39
 */
public class CaptionListeningVideoEntity extends BaseEntity{
    //试卷id
    private String paperId;
    //视频名称
    private String name;
    //cc视频id
    private String ccId;
    //content_type 内容类型（0.不区分1.听力2.阅读3.翻译4.写作;默认0）
    private Integer contentType ;
    //内容类型名称
    private String contentTypeName;

    public void setPaperId(String paperId){
        this.paperId = paperId;
    }
    public String getPaperId(){
        return this.paperId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCcId(String ccId){
        this.ccId = ccId;
    }
    public String getCcId(){
        return this.ccId;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
        if(contentType != null && !contentType.equals("")){
            switch (contentType){
                case 1:
                    this.contentTypeName= QuestionContentType.CONTENT_LISTENING_NAME;
                    break;
                case 2:
                    this.contentTypeName=QuestionContentType.CONTENT_READING_NAME;
                    break;
                case 3:
                    this.contentTypeName=QuestionContentType.CONTENT_TRANSLATION_NAME;
                    break;
                case 4:
                    this.contentTypeName=QuestionContentType.CONTENT_WRITING_NAME;
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
