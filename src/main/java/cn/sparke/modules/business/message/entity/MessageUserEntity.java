package cn.sparke.modules.business.message.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 消息推送Entity
 *
 * @author spark
 * @Date 2017-07-24 14:20:12
 */
public class MessageUserEntity extends BaseEntity{
    //用户id
    private String userId;
    //消息id
    private String msgId;

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setMsgId(String msgId){
        this.msgId = msgId;
    }
    public String getMsgId(){
        return this.msgId;
    }

}
