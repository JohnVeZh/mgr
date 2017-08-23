package cn.sparke.modules.questionbank.question_listening.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;

import java.util.*;
import java.math.*;

/**
 * @author <a href="mailto:peng.huantao@qq.com">PengHuantao</a>
 * @version 1.0
 * @date 2017/8/11
 */
public class QuestionListeningEntity extends BaseEntity{
    //题目id
    private String questionId;
    //音频地址
    private String audioUrl;
    //听力原文
    private String tapescripts;
    //译文
    private String translation;
    //字幕文件地址
    private String subtitleUrl;
    //音频文件大小,单位字节
    private Integer audioSize;
    //字幕文件大小,单位字节
    private Integer subtitleSize;

    private QuestionEntity questionEntity;

    //查询
    private String structureId;

    public void setQuestionId(String questionId){
        this.questionId = questionId;
    }
    public String getQuestionId(){
        return this.questionId;
    }
    public void setAudioUrl(String audioUrl){
        this.audioUrl = audioUrl;
    }
    public String getAudioUrl(){
        return this.audioUrl;
    }
    public void setTapescripts(String tapescripts){
        this.tapescripts = tapescripts;
    }
    public String getTapescripts(){
        return this.tapescripts;
    }
    public void setTranslation(String translation){
        this.translation = translation;
    }
    public String getTranslation(){
        return this.translation;
    }
    public void setSubtitleUrl(String subtitleUrl){
        this.subtitleUrl = subtitleUrl;
    }
    public String getSubtitleUrl(){
        return this.subtitleUrl;
    }
    public void setAudioSize(Integer audioSize){
        this.audioSize = audioSize;
    }
    public Integer getAudioSize(){
        return this.audioSize;
    }
    public void setSubtitleSize(Integer subtitleSize){
        this.subtitleSize = subtitleSize;
    }
    public Integer getSubtitleSize(){
        return this.subtitleSize;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }
}
