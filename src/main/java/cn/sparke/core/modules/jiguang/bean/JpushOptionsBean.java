package cn.sparke.core.modules.jiguang.bean;

/**
 * Created by Administrator on 2017/7/24.
 */
public class JpushOptionsBean {
    private Integer sendno;//	可选	推送序号	纯粹用来作为 API 调用标识，API 返回时被原样返回，以方便 API 调用方匹配请求与返回。
    private Integer timeToLive;//	可选	离线消息保留时长(秒)	推送当前用户不在线时，为该用户保留多长时间的离线消息，以便其上线时再次推送。默认 86400 （1 天），最长 10 天。设置为 0 表示不保留离线消息，只有推送当前在线的用户可以收到。
    private Long overrideMsgId;//	可选	要覆盖的消息ID	如果当前的推送要覆盖之前的一条推送，这里填写前一条推送的 msg_id 就会产生覆盖效果，即：1）该 msg_id 离线收到的消息是覆盖后的内容；2）即使该 msg_id Android 端用户已经收到，如果通知栏还未清除，则新的消息内容会覆盖之前这条通知；覆盖功能起作用的时限是：1 天。如果在覆盖指定时限内该 msg_id 不存在，则返回 1003 错误，提示不是一次有效的消息覆盖操作，当前的消息不会被推送。
    private boolean apnsProduction;//boolean	可选	APNs是否生产环境	True 表示推送生产环境，False 表示要推送开发环境；如果不指定则为推送生产环境。JPush 官方 API LIbrary (SDK) 默认设置为推送 “开发环境”。
    private String apnsCollapseId;//string	可选	更新 iOS 通知的标识符	APNs 新通知如果匹配到当前通知中心有相同 apns-collapse-id 字段的通知，则会用新通知内容来更新它，并使其置于通知中心首位。collapse id 长度不可超过 64 bytes。
    private Integer bigPushDuration;//	可选	定速推送时长(分钟)	又名缓慢推送，把原本尽可能快的推送速度，降低下来，给定的n分钟内，均匀地向这次推送的目标用户推送。最大值为1400.未设置则不是定速推送。

    public Integer getSendno() {
        return sendno;
    }

    public void setSendno(Integer sendno) {
        this.sendno = sendno;
    }

    public Integer getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Integer timeToLive) {
        this.timeToLive = timeToLive;
    }

    public Long getOverrideMsgId() {
        return overrideMsgId;
    }

    public void setOverrideMsgId(Long overrideMsgId) {
        this.overrideMsgId = overrideMsgId;
    }

    public boolean isApnsProduction() {
        return apnsProduction;
    }

    public void setApnsProduction(boolean apnsProduction) {
        this.apnsProduction = apnsProduction;
    }

    public String getApnsCollapseId() {
        return apnsCollapseId;
    }

    public void setApnsCollapseId(String apnsCollapseId) {
        this.apnsCollapseId = apnsCollapseId;
    }

    public Integer getBigPushDuration() {
        return bigPushDuration;
    }

    public void setBigPushDuration(Integer bigPushDuration) {
        this.bigPushDuration = bigPushDuration;
    }
}
