package cn.sparke.modules.questionbank.question_listening.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 听力题Entity
 *
 * @author spark
 * @Date 2017-07-19 10:53:23
 */
public class Question_listeningEntity extends BaseEntity{
    //题目id
    private String questionId;

    private String contentTypeName;
    //音频地址
    private String audioUrl;
    //听力原文
    private String tapescripts;
    //译文
    private String translation;
    //字幕文件地址
    private String subtitleUrl;

    //是否是小题
    private Integer hasItem;
    //题目名称
    private String name;
    //题目类型(1.听力 2.阅读 3.翻译 4.写作)'
    private Integer type;
    //题干
    private  String content;
    //题目数量
    private Integer questionNum;
    //学段code
    private  Integer sectionCode;
    //解析
    private String analysis;

    //试卷id
    private String paperId;
    //结构id
    private  String structureId;


    //音频文件大小,单位字节
    private int audioSize;

    //字幕文件大小,单位字节
    private int subtitleSize;

    public void setAudioSize(int audioSize) {
        this.audioSize = audioSize;
    }

    public void setSubtitleSize(int subtitleSize) {
        this.subtitleSize = subtitleSize;
    }

    public Integer getAudioSize() {

        return audioSize;
    }

    public Integer getSubtitleSize() {
        return subtitleSize;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public String getStructureId() {

        return structureId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperId() {

        return paperId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public void setSectionCode(Integer sectionCode) {
        this.sectionCode = sectionCode;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }


    public String getContent() {

        return content;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public Integer getSectionCode() {
        return sectionCode;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public void setTapescripts(String tapescripts) {
        this.tapescripts = tapescripts;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setSubtitleUrl(String subtitleUrl) {
        this.subtitleUrl = subtitleUrl;
    }

    public void setHasItem(Integer hasItem) {
        this.hasItem = hasItem;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public String getTapescripts() {
        return tapescripts;
    }

    public String getTranslation() {
        return translation;
    }

    public String getSubtitleUrl() {
        return subtitleUrl;
    }

    public Integer getHasItem() {
        return hasItem;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }


}
