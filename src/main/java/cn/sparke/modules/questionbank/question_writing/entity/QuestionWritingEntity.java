package cn.sparke.modules.questionbank.question_writing.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;

import java.util.*;
import java.math.*;

/**
 * @author <a href="mailto:peng.huantao@qq.com">PengHuantao</a>
 * @version 1.0
 * @date 2017/8/11
 */
public class QuestionWritingEntity extends BaseEntity{
    //题目id
    private String questionId;
    //参考范文
    private String reference;
    //视频讲解ccid
    private String analysisCcId;

    private QuestionEntity questionEntity;

    //查询
    private String structureId;

    public void setQuestionId(String questionId){
        this.questionId = questionId;
    }
    public String getQuestionId(){
        return this.questionId;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public String getReference(){
        return this.reference;
    }
    public void setAnalysisCcId(String analysisCcId){
        this.analysisCcId = analysisCcId;
    }
    public String getAnalysisCcId(){
        return this.analysisCcId;
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
