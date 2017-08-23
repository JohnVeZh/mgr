package cn.sparke.modules.gift.exercise.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 课后作业Entity
 *
 * @author spark
 * @Date 2017-08-21 15:23:45
 */
public class GiftExerciseEntity extends BaseEntity{
    //学段
    private Integer sectionCode;
    //类型 1听力，2阅读，3翻译，4写作
    private Integer questionType;
    //课程推荐id
    private String recommendId;
    //视频id(不是网课id)
    private String networkCourseVideoId;
    //试卷id
    private String paperId;
    //结构id
    private String structureId;

    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setQuestionType(Integer questionType){
        this.questionType = questionType;
    }
    public Integer getQuestionType(){
        return this.questionType;
    }
    public void setRecommendId(String recommendId){
        this.recommendId = recommendId;
    }
    public String getRecommendId(){
        return this.recommendId;
    }
    public void setNetworkCourseVideoId(String networkCourseVideoId){
        this.networkCourseVideoId = networkCourseVideoId;
    }
    public String getNetworkCourseVideoId(){
        return this.networkCourseVideoId;
    }
    public void setPaperId(String paperId){
        this.paperId = paperId;
    }
    public String getPaperId(){
        return this.paperId;
    }
    public void setStructureId(String structureId){
        this.structureId = structureId;
    }
    public String getStructureId(){
        return this.structureId;
    }

}
