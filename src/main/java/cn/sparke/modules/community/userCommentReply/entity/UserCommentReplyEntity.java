package cn.sparke.modules.community.userCommentReply.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 用户评论回复Entity
 *
 * @author spark
 * @Date 2017-07-22 11:27:00
 */
public class UserCommentReplyEntity extends BaseEntity{
    //评论id
    private String commentId;
    //回复人id
    private String userId;
    private String userName;
    //回复目标用户id
    private String targetUserId;
    //回复内容
    private String content;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
