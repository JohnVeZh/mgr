package cn.sparke.modules.reportStatistics.trafficStatistic.entity;

import cn.sparke.core.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 流量统计Entity
 *
 * @author spark
 * @Date 2017-08-01 10:03:56
 */
public class TrafficStatisticEntity {

    private String id;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //创建人
    private String createBy;
    //修改人
    private String updateBy;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    //删除标识(0:未删除1：删除)
    private Integer delFlag = 0;
    private String remarks;
    private Integer sort;
    //渠道代码(字典表value值)
    private String channelCode;
    //操作类型(1、打开2、下载)
    private Integer operationType;
    //操作平台（1、andriod 2、ios 3、pc 4、其他）
    private Integer fromType;
    //来源ip
    private String ip;
    //来源所属省
    private String province;
    //来源市
    private String city;
    //开始时间
    private String btTime;
    //结束时间
    private String endTime;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public void setOperationType(Integer operationType){
        this.operationType = operationType;
    }
    public Integer getOperationType(){
        return this.operationType;
    }
    public void setFromType(Integer fromType){
        this.fromType = fromType;
    }
    public Integer getFromType(){
        return this.fromType;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
    public String getIp(){
        return this.ip;
    }
    public void setProvince(String province){
        this.province = province;
    }
    public String getProvince(){
        return this.province;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
