package cn.sparke.modules.community.activity.entity;

import cn.sparke.core.common.entity.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.*;
import java.math.*;

/**
 * 活动Entity
 *
 * @author spark
 * @Date 2017-07-19 09:57:36
 */
public class ActivityEntity extends BaseEntity{
    //标题
    private String title;
    //简介
    private String brief;
    //多张图片，以，分割保存
    private String imgList;
    //内容
    private String content;
    //是否置顶  0:否 1;是
    private Integer isTop;
    //置顶时间
    private Date topTime;
    //是否推荐  0:否  1:是
    private Integer isRecommend;
    //推荐时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date recommendTime;
    //评论数量
    private Integer commentNum;
    //点赞数量
    private Integer likeNum;
    //查看数量
    private Integer readNum;
    //客服状态(0.隐藏 1.显示)
    private Integer serviceStatus;
    //关注数量
    private Integer attentionNum;

    private Integer isShow;

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setBrief(String brief){
        this.brief = brief;
    }
    public String getBrief(){
        return this.brief;
    }
    public void setImgList(String imgList){
        this.imgList = imgList;
    }
    public String getImgList(){
        return this.imgList;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setIsTop(Integer isTop){
        this.isTop = isTop;
    }
    public Integer getIsTop(){
        return this.isTop;
    }
    public void setTopTime(Date topTime){
        this.topTime = topTime;
    }
    public Date getTopTime(){
        return this.topTime;
    }
    public void setIsRecommend(Integer isRecommend){
        this.isRecommend = isRecommend;
    }
    public Integer getIsRecommend(){
        return this.isRecommend;
    }
    public void setRecommendTime(Date recommendTime){
        this.recommendTime = recommendTime;
    }
    public Date getRecommendTime(){
        return this.recommendTime;
    }
    public void setCommentNum(Integer commentNum){
        this.commentNum = commentNum;
    }
    public Integer getCommentNum(){
        return this.commentNum;
    }
    public void setLikeNum(Integer likeNum){
        this.likeNum = likeNum;
    }
    public Integer getLikeNum(){
        return this.likeNum;
    }
    public void setReadNum(Integer readNum){
        this.readNum = readNum;
    }
    public Integer getReadNum(){
        return this.readNum;
    }
    public void setServiceStatus(Integer serviceStatus){
        this.serviceStatus = serviceStatus;
    }
    public Integer getServiceStatus(){
        return this.serviceStatus;
    }
    public void setAttentionNum(Integer attentionNum){
        this.attentionNum = attentionNum;
    }
    public Integer getAttentionNum(){
        return this.attentionNum;
    }

}
