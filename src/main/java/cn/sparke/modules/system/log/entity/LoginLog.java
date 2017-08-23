package cn.sparke.modules.system.log.entity;

import cn.sparke.core.common.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 登录记录
 * </p>
 *
 * @author sparke
 * @since 2017-07-11
 */
public class LoginLog extends BaseEntity {

    private String beginTime;
    private String endTime;
    /**
     * 日志名称
     */
    private String logname;
    /**
     * 管理员id
     */
    private String userid;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 是否执行成功
     */
    private String succeed;
    /**
     * 具体消息
     */
    private String message;
    /**
     * 登录ip
     */
    private String ip;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
