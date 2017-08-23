package cn.sparke.modules.words.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 词汇管理Entity
 *
 * @author spark
 * @Date 2017-07-25 20:49:20
 */
public class WordsEntity extends BaseEntity{
    //目录id
    private String catalogId;

    private String catalogName;
    //单词名称
    private String name;

    private String nameLike;
    //音标
    private String phonetic;
    //发音文件地址
    private String pronunciationUrl;
    //释义
    private String paraphrase;
    //例句
    private String sentence;
    //例句翻译
    private String reference;
    //例句发音
    private String referenceAudioUrl;

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public void setCatalogId(String catalogId){
        this.catalogId = catalogId;
    }
    public String getCatalogId(){
        return this.catalogId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPhonetic(String phonetic){
        this.phonetic = phonetic;
    }
    public String getPhonetic(){
        return this.phonetic;
    }
    public void setPronunciationUrl(String pronunciationUrl){
        this.pronunciationUrl = pronunciationUrl;
    }
    public String getPronunciationUrl(){
        return this.pronunciationUrl;
    }
    public void setParaphrase(String paraphrase){
        this.paraphrase = paraphrase;
    }
    public String getParaphrase(){
        return this.paraphrase;
    }
    public void setSentence(String sentence){
        this.sentence = sentence;
    }
    public String getSentence(){
        return this.sentence;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public String getReference(){
        return this.reference;
    }
    public void setReferenceAudioUrl(String referenceAudioUrl){
        this.referenceAudioUrl = referenceAudioUrl;
    }
    public String getReferenceAudioUrl(){
        return this.referenceAudioUrl;
    }

}
