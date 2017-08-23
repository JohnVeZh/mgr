package cn.sparke.modules.gift.planNetworkCourse.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 学习方案绑定网课列表Entity
 *
 * @author spark
 * @Date 2017-08-21 14:57:58
 */
public class PlanNetworkCourseEntity extends BaseEntity{
    //计划id
    private String planId;
    //网课id
    private String networkCourseId;

    public void setPlanId(String planId){
        this.planId = planId;
    }
    public String getPlanId(){
        return this.planId;
    }
    public void setNetworkCourseId(String networkCourseId){
        this.networkCourseId = networkCourseId;
    }
    public String getNetworkCourseId(){
        return this.networkCourseId;
    }

    //非数据库字段
    private String coverImg; //列表小图
    private String name;  //商品名称
    private String mold;
    private String title; //配置名称

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCoverImg() {
        return coverImg;
    }
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMold() {
        return mold;
    }
    public void setMold(String mold) {
        this.mold = mold;
    }

    //查询字段
    private String ncName;
    private Integer sectionCode;

    public String getNcName() {
        return ncName;
    }
    public void setNcName(String ncName) {
        this.ncName = ncName;
    }
    public Integer getSectionCode() {
        return sectionCode;
    }
    public void setSectionCode(Integer sectionCode) {
        this.sectionCode = sectionCode;
    }
}
