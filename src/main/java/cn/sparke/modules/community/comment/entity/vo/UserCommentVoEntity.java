package cn.sparke.modules.community.comment.entity.vo;

import cn.sparke.modules.community.comment.entity.po.UserCommentEntity;

/**
 * @author ToQuery
 * @version V1.0
 * @date 2017/7/21 11:36
 */
public class UserCommentVoEntity extends UserCommentEntity {

    private String userName;
    private String title;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
