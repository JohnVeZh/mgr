package cn.sparke.modules.orders.refund.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户Entity
 *
 * @author spark
 * @Date 2017-07-19 09:50:06
 */
public class RefundDetailsEntity {
    private String id;
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
    //批改日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    //登录用户手机号
    private String phone;
    //商品名称
    private String productName;
    //商品类型
    private Integer productType;
    //购买人真实姓名
    private String realName;
    //购买人昵称
    private String nickName;
    //收货人省市县
    private String ProvinceCityCn;
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
    //发货时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryTime;
    //物流公司
    private String expressName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProvinceCityCn() {
        return ProvinceCityCn;
    }

    public void setProvinceCityCn(String provinceCityCn) {
        ProvinceCityCn = provinceCityCn;
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

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }
}
