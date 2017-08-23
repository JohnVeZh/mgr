package cn.sparke.modules.questionbank.question.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * Created by douht on 2017/8/1.
 */
public class PaperSubtitleEntity extends BaseEntity {

    //目录id
    private String catalogId;
    private String catalogName;
    //二维码代码
    private String qrCode;
    //名称
    private String name;
    //内容类型（0.不区分1.听力2.阅读3.翻译4.写作;默认0）
    private Integer contentType;
    //内容类型名称
    private String contentTypeName;
    //code
    private String code;
    //试卷图片
    private String img;
    //试卷内题目总数
    private Integer questionNum;

    //音频地址
    private String audioUrl;
    //字幕文件地址
    private String subtitleUrl;

    //字幕听力id
    private String captionListeningId;

    //视频名称
    private String listeningVideoName;

    //

    private String sectionCode;
    //学段名称
    private String sectionCodeName;

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public String getContentTypeName() {

        return contentTypeName;
    }

    public String getSectionCodeName() {
        return sectionCodeName;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
        if(sectionCode != null && !sectionCode.equals("")){
            Integer sectionCod=Integer.valueOf(sectionCode);
            switch (sectionCod){
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

    public String getSectionCode() {

        return sectionCode;
    }

    //cc视频id
    private String ccId;

    public void setCatalogId(String catalogId){
        this.catalogId = catalogId;
    }
    public String getCatalogId(){
        return this.catalogId;
    }
    public void setQrCode(String qrCode){
        this.qrCode = qrCode;
    }
    public String getQrCode(){
        return this.qrCode;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setContentType(Integer contentType){
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
    public Integer getContentType(){
        return this.contentType;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }

    public void setQuestionNum(Integer questionNum){
        this.questionNum = questionNum;
    }
    public Integer getQuestionNum(){
        return this.questionNum;
    }

    public String getCatalogName() {
        return catalogName;
    }
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
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

    public void setCaptionListeningId(String captionListeningId){
        this.captionListeningId = captionListeningId;
    }
    public String getCaptionListeningId(){
        return this.captionListeningId;
    }

    public void setListeningVideoName(String listeningVideoName){
        this.listeningVideoName = listeningVideoName;
    }
    public String getListeningVideoName(){
        return this.listeningVideoName;
    }

    public void setCcId(String ccId){
        this.ccId = ccId;
    }
    public String getCcId(){
        return this.ccId;
    }

}
