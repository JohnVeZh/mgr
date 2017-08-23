package cn.sparke.modules.business.message.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 消息推送Entity
 *
 * @author spark
 * @Date 2017-07-24 14:17:38
 */
public class MessageEntity extends BaseEntity{
    //标题
    private String title;
    //富文本
    private String content;
    //简介
    private String intro;
    //目标类型(1.个人 2.全体)
    private Integer targetType;
    //图片
    private String img;
    //跳转类型(1.富文本 2.外部链接 3.活动 4.资讯 5.图书 6.网课 )
    private Integer jumpType;
    //内容id(活动详情)
    private String contentId;
    //
    private String url;
    //推送状态（0.未推送 1.已推送）
    private Integer pushStatus;

    //开始时间
    private String btTime;
    //结束时间
    private String endTime;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setIntro(String intro){
        this.intro = intro;
    }
    public String getIntro(){
        return this.intro;
    }
    public void setTargetType(Integer targetType){
        this.targetType = targetType;
    }
    public Integer getTargetType(){
        return this.targetType;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }
    public void setJumpType(Integer jumpType){
        this.jumpType = jumpType;
    }
    public Integer getJumpType(){
        return this.jumpType;
    }
    public void setContentId(String contentId){
        this.contentId = contentId;
    }
    public String getContentId(){
        return this.contentId;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setPushStatus(Integer pushStatus){
        this.pushStatus = pushStatus;
    }
    public Integer getPushStatus(){
        return this.pushStatus;
    }
    public String getBtTime() {
        return btTime;
    }

    public void setBtTime(String btTime) {
        if(!btTime.equals("")&& btTime != null){
            btTime = btTime + " 00:00:00";
        }
        this.btTime = btTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        if(!endTime.equals("")&& endTime != null){
            endTime = endTime + " 23:59:59";
        }
        this.endTime = endTime;
    }
}
