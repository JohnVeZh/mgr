package cn.sparke.modules.goods.networkCourse.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.modules.goods.product.entity.ProductEntity;

import java.util.*;
import java.math.*;

/**
 * 网课Entity
 *
 * @author spark
 * @Date 2017-07-22 16:13:33
 */
public class NetworkCourseEntity extends ProductEntity{
    //商品ID
    private String productId;
    //课时数
    private Integer catalogNumber;
    //老师名称，多个空格拼接
    private String teacherNames;
    //1系统课，2公开课 3.微课
    private Integer courseType;
    //0.录播 1.直播
    private Integer isLive;
    //是否免费，0否，1是
    private Integer isFree;
    //是否限时免费，0否，1是
    private Integer isLimitFree;
    //是否是名师视频，0否，1是
    private Integer isFamousTeacher;
    //是否存在目录(0.否 1.是)
    private Integer hasCatalog;
    // 0: 待发布, 1: 发布中, 2: 预约中, 3:开售中, 4: 已停售, 5: 已下架
    private Integer status;
    //开课时间(直播课使用)
    private String startTime;
    //结课时间(直播课使用)
    private String endTime;
    //开始预约时间（直播课使用）
    private String reserveStartTime;
    //已预约人数(直播课使用)
    private Integer reserveNumber;
    //已领取人数（免费课程使用）
    private Integer receiveNumber;
    //开始放购时间
    private String saleStartTime;
    //结束放购时间
    private String saleEndTime;
    //上架时间
    private String shelfOnTime;
    //下架时间
    private String shelfOffTime;
    //限购数
    private Integer limitNumber;
    //QQ群号码
    private String qqGroupNo;
    //限时开始时间（限时免费时使用）
    private String limitStartTime;
    //限时结束时间（限时免费时使用）
    private String limitEndTime;
    //网课视频预告ccid
    private String previewCcId;
    //网课cc视频直播间id
    private String liveRoomId;
    //评论数量
    private Integer commentNum;

    //非数据库字段
    private String networkCourseId;
    private String sectionType;


    public String getNetworkCourseId() {
        return networkCourseId;
    }

    public void setNetworkCourseId(String networkCourseId) {
        this.networkCourseId = networkCourseId;
    }

    public String getSectionType() {
        this.sectionType=this.changeSectionCode(this.getSectionCode())+"-"+this.changeCourseType(this.getCourseType());
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public void setProductId(String productId){
        this.productId = productId;
    }
    public String getProductId(){
        return this.productId;
    }
    public void setCatalogNumber(Integer catalogNumber){
        this.catalogNumber = catalogNumber;
    }
    public Integer getCatalogNumber(){
        return this.catalogNumber;
    }
    public void setTeacherNames(String teacherNames){
        this.teacherNames = teacherNames;
    }
    public String getTeacherNames(){
        return this.teacherNames;
    }
    public void setCourseType(Integer courseType){
        this.courseType = courseType;
    }
    public Integer getCourseType(){
        return this.courseType;
    }
    public void setIsLive(Integer isLive){
        this.isLive = isLive;
    }
    public Integer getIsLive(){
        return this.isLive;
    }
    public void setIsFree(Integer isFree){
        this.isFree = isFree;
    }
    public Integer getIsFree(){
        return this.isFree;
    }
    public void setIsLimitFree(Integer isLimitFree){
        this.isLimitFree = isLimitFree;
    }
    public Integer getIsLimitFree(){
        return this.isLimitFree;
    }
    public void setIsFamousTeacher(Integer isFamousTeacher){
        this.isFamousTeacher = isFamousTeacher;
    }
    public Integer getIsFamousTeacher(){
        return this.isFamousTeacher;
    }
    public void setHasCatalog(Integer hasCatalog){
        this.hasCatalog = hasCatalog;
    }
    public Integer getHasCatalog(){
        return this.hasCatalog;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public Integer getStatus(){
        return this.status;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public void setReserveStartTime(String reserveStartTime){
        this.reserveStartTime = reserveStartTime;
    }
    public String getReserveStartTime(){
        return this.reserveStartTime;
    }
    public void setReserveNumber(Integer reserveNumber){
        this.reserveNumber = reserveNumber;
    }
    public Integer getReserveNumber(){
        return this.reserveNumber;
    }
    public void setReceiveNumber(Integer receiveNumber){
        this.receiveNumber = receiveNumber;
    }
    public Integer getReceiveNumber(){
        return this.receiveNumber;
    }
    public void setSaleStartTime(String saleStartTime){
        this.saleStartTime = saleStartTime;
    }
    public String getSaleStartTime(){
        return this.saleStartTime;
    }
    public void setSaleEndTime(String saleEndTime){
        this.saleEndTime = saleEndTime;
    }
    public String getSaleEndTime(){
        return this.saleEndTime;
    }
    public void setShelfOnTime(String shelfOnTime){
        this.shelfOnTime = shelfOnTime;
    }
    public String getShelfOnTime(){
        return this.shelfOnTime;
    }
    public void setShelfOffTime(String shelfOffTime){
        this.shelfOffTime = shelfOffTime;
    }
    public String getShelfOffTime(){
        return this.shelfOffTime;
    }
    public void setLimitNumber(Integer limitNumber){
        this.limitNumber = limitNumber;
    }
    public Integer getLimitNumber(){
        return this.limitNumber;
    }
    public void setQqGroupNo(String qqGroupNo){
        this.qqGroupNo = qqGroupNo;
    }
    public String getQqGroupNo(){
        return this.qqGroupNo;
    }
    public void setLimitStartTime(String limitStartTime){
        this.limitStartTime = limitStartTime;
    }
    public String getLimitStartTime(){
        return this.limitStartTime;
    }
    public void setLimitEndTime(String limitEndTime){
        this.limitEndTime = limitEndTime;
    }
    public String getLimitEndTime(){
        return this.limitEndTime;
    }
    public void setPreviewCcId(String previewCcId){
        this.previewCcId = previewCcId;
    }
    public String getPreviewCcId(){
        return this.previewCcId;
    }
    public void setLiveRoomId(String liveRoomId){
        this.liveRoomId = liveRoomId;
    }
    public String getLiveRoomId(){
        return this.liveRoomId;
    }
    public void setCommentNum(Integer commentNum){
        this.commentNum = commentNum;
    }
    public Integer getCommentNum(){
        return this.commentNum;
    }

    private String changeSectionCode(Integer sectionCode){
        switch(sectionCode) {
            case 1:
                return "4级";
            case 2:
                return "6级";
            case 3:
                return "考研";
            case 4:
                return "英专";
            case 5:
                return "高中英语";
            case 6:
                return "初中英语";
            case 7:
                return "小学英语";
            default:
                break;
        }
        return "";
    }

    private String changeCourseType(Integer courseType){
        switch(courseType) {
            case 1:
                return "系统课";
            case 2:
                return "公开课";
            case 3:
                return "微课";
            default:
                break;
        }
        return "";
    }

}
