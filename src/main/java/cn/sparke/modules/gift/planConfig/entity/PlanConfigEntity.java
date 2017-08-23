package cn.sparke.modules.gift.planConfig.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 学习方案Entity
 *
 * @author spark
 * @Date 2017-08-19 10:33:52
 */
public class PlanConfigEntity extends BaseEntity{
    //学段
    private Integer sectionCode;
    //阶段，1学前，2学中，3学末
    private Integer period;
    //标题
    private String title;
    //分数起始值
    private Integer scoreStart;
    //分数结束值
    private Integer scoreEnd;
    //评语
    private String comment;
    //内容
    private String content;

    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setPeriod(Integer period){
        this.period = period;
    }
    public Integer getPeriod(){
        return this.period;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setScoreStart(Integer scoreStart){
        this.scoreStart = scoreStart;
    }
    public Integer getScoreStart(){
        return this.scoreStart;
    }
    public void setScoreEnd(Integer scoreEnd){
        this.scoreEnd = scoreEnd;
    }
    public Integer getScoreEnd(){
        return this.scoreEnd;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return this.comment;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }

}
