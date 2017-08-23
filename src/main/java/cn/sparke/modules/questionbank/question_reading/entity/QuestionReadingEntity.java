package cn.sparke.modules.questionbank.question_reading.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.questionbank.question.entity.QuestionEntity;

import java.util.*;
import java.math.*;

/**
 * @author <a href="mailto:peng.huantao@qq.com">PengHuantao</a>
 * @version 1.0
 * @date 2017/8/11
 */
public class QuestionReadingEntity extends BaseEntity{
    //题目id
    private String questionId;
    //译文
    private String translation;

    private QuestionEntity questionEntity;

    //查询
    private String structureId;

    public void setQuestionId(String questionId){
        this.questionId = questionId;
    }
    public String getQuestionId(){
        return this.questionId;
    }
    public void setTranslation(String translation){
        this.translation = translation;
    }
    public String getTranslation(){
        return this.translation;
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
