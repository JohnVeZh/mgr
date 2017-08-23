package cn.sparke.modules.community.news.entity;

import cn.sparke.core.common.entity.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.*;
import java.math.*;

/**
 * 社区资讯Entity
 *
 * @author spark
 * @Date 2017-07-24 16:51:33
 */
public class CommunityNewsEntity extends BaseEntity{
    //标题
    private String title;
    //副标题
    private String subtitle;
    //发布时间，当发布时间小于当前时间，is_show 为1时，展示资讯
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    //作者
    private String author;
    //列表图片
    private String listImg;
    //内容
    private String content;
    //是否发布展示，0为不展示或者翻译为草稿箱，1为展示
    private Integer isShow;
    //阅读数
    private Integer readNum;
    //是否置顶 0：否 1：是
    private Integer isTop;
    //置顶时间
    private Date topTime;
    //是否推荐 0：否 1：是
    private Integer isRecommend;
    //推荐时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date recommendTime;
    //评论数量
    private Integer commentNum;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setSubtitle(String subtitle){
        this.subtitle = subtitle;
    }
    public String getSubtitle(){
        return this.subtitle;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setListImg(String listImg){
        this.listImg = listImg;
    }
    public String getListImg(){
        return this.listImg;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setIsShow(Integer isShow){
        this.isShow = isShow;
    }
    public Integer getIsShow(){
        return this.isShow;
    }
    public void setReadNum(Integer readNum){
        this.readNum = readNum;
    }
    public Integer getReadNum(){
        return this.readNum;
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

}
