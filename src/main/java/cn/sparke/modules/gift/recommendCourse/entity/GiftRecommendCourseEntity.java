package cn.sparke.modules.gift.recommendCourse.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 课程推荐Entity
 *
 * @author spark
 * @Date 2017-08-22 09:48:17
 */
public class GiftRecommendCourseEntity extends BaseEntity{
    //
    private Integer sectionCode;
    //
    private String networkCourseId;
    private String networkCourseName;

    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setNetworkCourseId(String networkCourseId){
        this.networkCourseId = networkCourseId;
    }
    public String getNetworkCourseId(){
        return this.networkCourseId;
    }

    public String getNetworkCourseName() {
        return networkCourseName;
    }

    public void setNetworkCourseName(String networkCourseName) {
        this.networkCourseName = networkCourseName;
    }
}
