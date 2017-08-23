package cn.sparke.modules.community.comment.entity.po;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 活动评论Entity
 *
 * @author spark
 * @Date 2017-07-21 10:18:26
 */
public class UserCommentEntity extends BaseEntity{

    private String userName;
    private String userPhone;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setContentId(String contentId){
        this.contentId = contentId;
    }
    public String getContentId(){
        return this.contentId;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
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

}
