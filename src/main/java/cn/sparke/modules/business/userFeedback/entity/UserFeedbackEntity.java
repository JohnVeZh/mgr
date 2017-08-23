package cn.sparke.modules.business.userFeedback.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 用户反馈Entity
 *
 * @author spark
 * @Date 2017-07-25 14:43:51
 */
public class UserFeedbackEntity extends BaseEntity{
    //用户ID
    private String userId;
    //邮箱
    private String email;
    //反馈内容
    private String content;
    //是否处理
    private Integer isHandle;
    //处理结果
    private String handleResults;


    //拓展字段
    //处理结果
    private String nickname;
    //处理结果
    private String realName;
    //处理结果
    private String phone;

    //开始时间
    private String btTime;
    //结束时间
    private String endTime;
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }

    public Integer getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(Integer isHandle) {
        this.isHandle = isHandle;
    }

    public String getHandleResults() {
        return handleResults;
    }

    public void setHandleResults(String handleResults) {
        this.handleResults = handleResults;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
