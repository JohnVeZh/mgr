package cn.sparke.core.modules.jiguang.bean;

import cn.sparke.modules.business.message.entity.MessageEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/23.
 */
public class JpushBean {
    public Integer platform;//推送平台
    public List<Map<Integer,List<String>>> audience;//推送设备
    public String notification;//通知内容体；notification与message必存一
    public MessageEntity message;//消息内容体；notification与message必存一
    public JpushOptionsBean options;//可选参数

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public List<Map<Integer, List<String>>> getAudience() {
        return audience;
    }

    public void setAudience(List<Map<Integer, List<String>>> audience) {
        this.audience = audience;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public MessageEntity getMessage() {
        return message;
    }

    public void setMessage(MessageEntity message) {
        this.message = message;
    }

    public JpushOptionsBean getOptions() {
        return options;
    }

    public void setOptions(JpushOptionsBean options) {
        this.options = options;
    }
}
