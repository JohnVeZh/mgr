package cn.sparke.modules.questionbank.CaptionListening.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.questionbank.question.entity.QuestionContentType;
import cn.sparke.modules.questionbank.question.entity.SectionCodeType;

import java.util.*;
import java.math.*;

/**
 * 字幕听力音频Entity
 *
 * @author spark
 * @Date 2017-07-26 23:01:00
 */
public class CaptionListeningEntity extends BaseEntity{
    //试卷id
    private String paperId;
    //音频地址
    private String audioUrl;
    //字幕文件地址
    private String subtitleUrl;
    //音频文件大小,单位字节
    private Integer audioSize;
    //字幕文件大小,单位字节
    private Integer subtitleSize ;


    //学段code
    private Integer sectionCode;
    //学段名称
    private String sectionCodeName;
    //content_type内容类型（0.不区分1.听力2.阅读3.翻译4.写作;默认0）
    private Integer contentType;

    //content_type内容类型名称（0.不区分1.听力2.阅读3.翻译4.写作;默认0）
    private String contentTypeName;

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public String getContentTypeName() {

        return contentTypeName;
    }

    //视频名称
    private  String name;

    //cc视频id
    private String ccId;

    public void setPaperId(String paperId){
        this.paperId = paperId;
    }
    public String getPaperId(){
        return this.paperId;
    }
    public void setAudioUrl(String audioUrl){
        this.audioUrl = audioUrl;
    }
    public String getAudioUrl(){
        return this.audioUrl;
    }
    public void setSubtitleUrl(String subtitleUrl){
        this.subtitleUrl = subtitleUrl;
    }
    public String getSubtitleUrl(){
        return this.subtitleUrl;
    }

    public Integer getAudioSize() {
        return audioSize;
    }

    public Integer getSubtitleSize() {
        return subtitleSize;
    }

    public void setAudioSize(Integer audioSize) {
        this.audioSize = audioSize;
    }

    public void setSubtitleSize(Integer subtitleSize) {
        this.subtitleSize = subtitleSize;
    }

    public String getName() {
        return name;
    }

    public Integer getSectionCode() {
        return sectionCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSectionCode(Integer sectionCode) {
        this.sectionCode = sectionCode;
        if(sectionCode != null && !sectionCode.equals("")){
            switch (sectionCode){
                case 1:
                    this.sectionCodeName= SectionCodeType.SECTION_CODE_CET4_ENGLISH_NAME;
                    break;
                case 2:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_CET6_ENGLISH_NAME;
                    break;
                case 3:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_GRADUATE_ENGLISH_NAME;
                    break;
                case 4:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_MAJOR_ENGLISH_NAME;
                    break;
                case 5:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_SENIOR_ENGLISH_NAME;
                    break;
                case 6:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_JUNIOR_ENGLISH_NAME;
                    break;
                case 7:
                    this.sectionCodeName=SectionCodeType.SECTION_CODE_PRIMARY_ENGLISH_NAME;
                    break;

            }
        }
    }
    public void setSectionCodeName(String sectionCodeName) {
        this.sectionCodeName = sectionCodeName;
    }

    public String getSectionCodeName() {

        return sectionCodeName;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public String getCcId() {

        return ccId;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
        if(contentType != null && !contentType.equals("")){
            switch (contentType){
                case 1:
                    this.contentTypeName=QuestionContentType.CONTENT_LISTENING_NAME;
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

    public Integer getContentType() {

        return contentType;
    }
}
