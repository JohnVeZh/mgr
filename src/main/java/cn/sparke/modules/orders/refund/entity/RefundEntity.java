package cn.sparke.modules.orders.refund.entity;

import cn.sparke.core.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;
import java.math.*;

/**
 * 退单Entity
 *
 * @author spark
 * @Date 2017-07-20 09:54:27
 */
public class RefundEntity extends BaseEntity {
    //订单编号
    private String orderCode;
    //退款原因
    private String reason;
    //退款金额
    private BigDecimal money;
    //退款类型
    private Integer type;
    //退款状态
    private Integer status;
    //用户收货状态
    private Integer userReceiveStatus;
    //上传图片
    private String imgs;
    //已看节数
    private Integer plauCount;
    //退款时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishDate;
    //备注
    private String remarks;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //批改人
    private String updateBy;
    //登录用户手机号
    private String phone;
    //商品名称
    private String productName;
    //商品类型
    private Integer productType;
    //物流单号
    private String logisticsCode;
    //收货人
    private String receiver;
    //收货地址
    private String receiveAddress;
    //收货人手机号
    private String receiveMobile;
    //收货时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;
    //物流公司
    private String expressName;



    //查询字段
    private String queryName;
    private String queryOrderCode;
    private String queryPhone;
    private String queryStartTime;
    private String queryEndTime;
    private String queryProductType;
    private String QueryStatus;

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryOrderCode() {
        return queryOrderCode;
    }

    public void setQueryOrderCode(String queryOrderCode) {
        this.queryOrderCode = queryOrderCode;
    }

    public String getQueryPhone() {
        return queryPhone;
    }

    public void setQueryPhone(String queryPhone) {
        this.queryPhone = queryPhone;
    }

    public String getQueryStartTime() {
        return queryStartTime;
    }

    public void setQueryStartTime(String queryStartTime) {
        this.queryStartTime = queryStartTime;
    }

    public String getQueryEndTime() {
        return queryEndTime;
    }

    public void setQueryEndTime(String queryEndTime) {
        this.queryEndTime = queryEndTime;
    }

    public String getQueryProductType() {
        return queryProductType;
    }

    public void setQueryProductType(String queryProductType) {
        this.queryProductType = queryProductType;
    }

    public String getQueryStatus() {
        return QueryStatus;
    }

    public void setQueryStatus(String queryStatus) {
        QueryStatus = queryStatus;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserReceiveStatus() {
        return userReceiveStatus;
    }

    public void setUserReceiveStatus(Integer userReceiveStatus) {
        this.userReceiveStatus = userReceiveStatus;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Integer getPlauCount() {
        return plauCount;
    }

    public void setPlauCount(Integer plauCount) {
        this.plauCount = plauCount;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }
}