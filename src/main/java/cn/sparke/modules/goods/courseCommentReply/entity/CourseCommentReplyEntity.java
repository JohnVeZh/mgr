package cn.sparke.modules.goods.courseCommentReply.entity;

import cn.sparke.core.common.entity.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 评论Entity
 *
 * @author spark
 * @Date 2017-08-18 10:16:26
 */
public class CourseCommentReplyEntity extends BaseEntity{
    //评论id
    private String commentId;
    //回复人id
    private String userId;
    //回复目标用户id
    private String targetUserId;
    //回复内容
    @NotNull
    private String content;

    public void setCommentId(String commentId){
        this.commentId = commentId;
    }
    public String getCommentId(){
        return this.commentId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setTargetUserId(String targetUserId){
        this.targetUserId = targetUserId;
    }
    public String getTargetUserId(){
        return this.targetUserId;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }

}
