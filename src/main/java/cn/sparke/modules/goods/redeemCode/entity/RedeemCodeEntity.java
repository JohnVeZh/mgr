package cn.sparke.modules.goods.redeemCode.entity;

import cn.sparke.core.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;
import java.math.*;

/**
 * 网课兑换码Entity
 *
 * @author spark
 * @Date 2017-07-21 17:17:55
 */
public class RedeemCodeEntity extends BaseEntity{
    //使用用户id
    private String userId;
    //网课id
    private String networkCourseId;
    //兑换码
    private String code;
    //生效时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    //失效时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    //是否导出 0: 未导出, 1: 已导出
    private Integer isExport;
    //是否可用 0: 禁用, 1: 可用
    private Integer isEnable;
    //状态(0.未使用 1.已使用)
    private Integer status;

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setNetworkCourseId(String networkCourseId){
        this.networkCourseId = networkCourseId;
    }
    public String getNetworkCourseId(){
        return this.networkCourseId;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }
    public Date getStartTime(){
        return this.startTime;
    }
    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }
    public Date getEndTime(){
        return this.endTime;
    }
    public void setIsExport(Integer isExport){
        this.isExport = isExport;
    }
    public Integer getIsExport(){
        return this.isExport;
    }
    public void setIsEnable(Integer isEnable){
        this.isEnable = isEnable;
    }
    public Integer getIsEnable(){
        return this.isEnable;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public Integer getStatus(){
        return this.status;
    }

}
