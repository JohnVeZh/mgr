package cn.sparke.modules.paper.specialSuggestion.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 分析建议Entity
 *
 * @author spark
 * @Date 2017-07-22 10:14:36
 */
public class SpecialSuggestionEntity extends BaseEntity{
    //专项类型：1.听力2,阅读3.翻译4.写作5.词汇
    private Integer type;
    //下限分数
    private float start;
    //上限分数
    private float end;
    //学段
    private Integer sectionCode;
    //分析
    private String analysis;
    //建议
    private String suggestion;

    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    public void setStart(float start){
        this.start = start;
    }
    public float getStart(){
        return this.start;
    }
    public void setEnd(float end){
        this.end = end;
    }
    public float getEnd(){
        return this.end;
    }
    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setAnalysis(String analysis){
        this.analysis = analysis;
    }
    public String getAnalysis(){
        return this.analysis;
    }
    public void setSuggestion(String suggestion){
        this.suggestion = suggestion;
    }
    public String getSuggestion(){
        return this.suggestion;
    }

}
