package cn.sparke.core.modules.jiguang.service;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.connection.HttpProxy;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.modules.jiguang.bean.JpushBean;
import cn.sparke.core.modules.jiguang.bean.JpushOptionsBean;
import cn.sparke.core.modules.jiguang.constants.AudienceTypeConstants;
import cn.sparke.core.modules.jiguang.constants.JpushProperties;
import cn.sparke.core.modules.jiguang.constants.PlatformConstants;
import cn.sparke.modules.business.message.entity.MessageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/7/23.
 */
@Service
public class JpushService {
    @Autowired
    JpushProperties properties;

    public JPushClient getJpushClient(){
        JPushClient jpushClient = new JPushClient(properties.getMasterSecret(), properties.getAppKey(), null, ClientConfig.getInstance());
        return jpushClient;
    }

    /**
     * 自定义消息与通知推送
     * @param jpushBean 消息实体不为空，发送消息；通知实体不为空，发送通知；两者都不为空，两者同时发送
     * @return
     */
    public boolean sendNotificationAndMessage(JpushBean jpushBean) {
        final Logger LOG = LoggerFactory.getLogger(HttpProxy.class);
        boolean status = false;
        JPushClient jpushClient = new JPushClient(properties.getMasterSecret(), properties.getAppKey(), null, ClientConfig.getInstance());
        PushPayload payload = getPushPayload(jpushBean);
        try {
            PushResult result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            LOG.error("极光API请求网络错误！");
            throw new BussinessException(BizExceptionEnum.JPUSH_APICONNECTION_ERROR);
        } catch (APIRequestException e) {
            LOG.error("极光请求错误！");
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            throw new BussinessException(BizExceptionEnum.JPUSH_REQUEST_ERROR);
        }
        return status;
    }

    public PushPayload getPushPayload(JpushBean jpushBean) {
        PushPayload push = null;
        boolean isPush = false;
        PushPayload.Builder builder = this.setPlatform(push,jpushBean.getPlatform());
        builder = this.setAudience(builder,jpushBean.getAudience());
        if(jpushBean.getNotification() != null) {
            isPush = true;
            builder = this.setNotificationAll(builder, jpushBean.getNotification());
        }
        if(jpushBean.getMessage() != null){
            isPush = true;
            builder = this.setMessage(builder,jpushBean.getMessage());
        }
        if(!isPush){
            throw new BussinessException(BizExceptionEnum.JPUSH_BEAN_ERROR);
        }
        if(jpushBean.getOptions() != null) {
            builder = this.setOption(builder, jpushBean.getOptions());
        }
        builder.build();
        return builder.build();
    }

    public PushPayload.Builder setPlatform(PushPayload push, int platform) {
        PushPayload.Builder bulder = null;
        if (PlatformConstants.ALL.equals(platform)) {
            bulder = push.newBuilder().setPlatform(Platform.all());
        } else if (PlatformConstants.Android.equals(platform)) {
            bulder = push.newBuilder().setPlatform(Platform.android());
        } else if (PlatformConstants.IOS.equals(platform)) {
            bulder = push.newBuilder().setPlatform(Platform.ios());
        } else if (PlatformConstants.AndroidAndIOS.equals(platform)) {
            bulder =  push.newBuilder().setPlatform(Platform.android_ios());
        } else if (PlatformConstants.AndroidAndWinPhone.equals(platform)) {
            bulder = push.newBuilder().setPlatform(Platform.android_winphone());
        } else if (PlatformConstants.IOSAndWinPhone.equals(platform)) {
            bulder = push.newBuilder().setPlatform(Platform.ios_winphone());
        }
        return bulder;
    }

    public PushPayload.Builder setAudience(PushPayload.Builder builder, List<Map<Integer,List<String>>> audience) {
        Audience.Builder audienceBuilder = Audience.newBuilder();
        for (int i = 0; i < audience.size(); i++) {
            for (Integer key : audience.get(i).keySet()) {
                if (key.equals(AudienceTypeConstants.all) ) {
                    builder.setAudience(Audience.all());
                } else if (key.equals(AudienceTypeConstants.tag)) {
                    List<String> tags = audience.get(i).get(key);
                    builder.setAudience(Audience.tag(tags));
                }else if(key.equals(AudienceTypeConstants.alias)){
                    List<String> alias = audience.get(i).get(key);
                    builder.setAudience(Audience.alias(alias));
                }else if(key.equals(AudienceTypeConstants.tag_and)){
                    List<String> tag_and = audience.get(i).get(key);
                    builder.setAudience(Audience.tag_and(tag_and));
                }else if(key.equals(AudienceTypeConstants.registrationId)){
                    List<String> registrationId = audience.get(i).get(key);
                    builder.setAudience(Audience.registrationId(registrationId));
                }else if(key.equals(AudienceTypeConstants.segment)){
                    List<String> segment = audience.get(i).get(key);
                    builder.setAudience(Audience.segment(segment));
                }
            }
        }
        return builder;
    }
    public PushPayload.Builder setNotificationAll(PushPayload.Builder builder, String notification) {
        builder.setNotification(Notification.alert(notification));
        return builder;
    }
    /**
     *  应用内消息。或者称作：自定义消息，透传消息。
     此部分内容不会展示到通知栏上，JPush SDK 收到消息内容后透传给 App。需要 App 自行处理。
     iOS 平台上，此部分内容在推送应用内消息通道（非APNS）获取。Windows Phone 暂时不支持应用内消息。
     */

    public PushPayload.Builder setMessage(PushPayload.Builder builder, MessageEntity message) {
        Message.Builder messageBuilder = Message.newBuilder();
        if(message.getTitle() != null){
            messageBuilder.setTitle(message.getTitle());
        }
        if(message.getContent() != null) {
            messageBuilder.setMsgContent(message.getContent());
        }
        builder.setMessage(messageBuilder.build());
        return builder;
    }
    /**
     *
     */
    public PushPayload.Builder setOption(PushPayload.Builder builder, JpushOptionsBean optionsBean) {
        Options.Builder optionsBuilder = Options.newBuilder();
        if(optionsBean.getApnsCollapseId() != null) {
            optionsBuilder.setApnsProduction(optionsBean.isApnsProduction());
        }
        if(optionsBean.getBigPushDuration() != null) {
            optionsBuilder.setBigPushDuration(optionsBean.getBigPushDuration());
        }
        if(optionsBean.getOverrideMsgId() != null) {
            optionsBuilder.setOverrideMsgId(optionsBean.getOverrideMsgId());
        }
        if(optionsBean.getSendno() != null) {
            optionsBuilder.setSendno(optionsBean.getSendno());
        }
        if(optionsBean.getTimeToLive() != null) {
            optionsBuilder.setTimeToLive(optionsBean.getTimeToLive());
        }
        builder.setOptions(optionsBuilder.build());
        return builder;
    }
}