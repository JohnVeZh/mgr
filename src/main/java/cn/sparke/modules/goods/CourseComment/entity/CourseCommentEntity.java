package cn.sparke.modules.goods.CourseComment.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 网课评论Entity
 *
 * @author spark
 * @Date 2017-08-02 13:35:13
 */
public class CourseCommentEntity extends BaseEntity{
    //评论用户id
    private String userId;
    //内容类型(1.网课 2.资讯 3.活动 )
    private Integer contentType;
    //内容id（1.网课id 2.资讯id  3.活动id ）
    private String contentId;
    //点赞数量
    private Integer praiseNum;
    //内容
    private String content;
    //图片地址
    private String imgs;
    //是否置顶(0.否 1.是)
    private Integer isTop;
    //回复数量
    private Integer replyNum;


    //非数据库字段
    private String name; //网课名称
    private String phone; //用户手机
    private String startDate;
    private String endDate;
    private Integer count;//管理员是否回复，0未回复

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setContentType(Integer contentType){
        this.contentType = contentType;
    }
    public Integer getContentType(){
        return this.contentType;
    }
    public void setContentId(String contentId){
        this.contentId = contentId;
    }
    public String getContentId(){
        return this.contentId;
    }
    public void setPraiseNum(Integer praiseNum){
        this.praiseNum = praiseNum;
    }
    public Integer getPraiseNum(){
        return this.praiseNum;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setImgs(String imgs){
        this.imgs = imgs;
    }
    public String getImgs(){
        return this.imgs;
    }
    public void setIsTop(Integer isTop){
        this.isTop = isTop;
    }
    public Integer getIsTop(){
        return this.isTop;
    }
    public void setReplyNum(Integer replyNum){
        this.replyNum = replyNum;
    }
    public Integer getReplyNum(){
        return this.replyNum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
