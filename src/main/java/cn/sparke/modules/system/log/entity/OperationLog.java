package cn.sparke.modules.system.log.entity;

import cn.sparke.core.common.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author sparke
 * @since 2017-07-11
 */
public class OperationLog extends BaseEntity {
    private String beginTime;
    private String endTime;
    private String userName;
    /**
     * 日志类型
     */
    private String logtype;
    /**
     * 日志名称
     */
    private String logname;
    /**
     * 用户id
     */
    private String userid;
    /**
     * 类名称
     */
    private String classname;
    /**
     * 方法名称
     */
    private String method;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 是否成功
     */
    private String succeed;
    /**
     * 备注
     */
    private String message;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
}
