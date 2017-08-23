package cn.sparke.modules.orders.order.entity;

import cn.sparke.modules.orders.utils.annotation.ExcelField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

/**
 * 订单Entity
 *
 * @author spark
 * @Date 2017-07-19 09:47:28
 */
public class ExcelOrderCourseEntity {

    private String id;
    @ExcelField(title = "交易号", order = 1)
    private String orderTradeNo;
    @ExcelField(title = "商品名", order = 2)
    private String productName;
    @ExcelField(title = "ERP编码", order = 3)
    private String erpCode;
    @ExcelField(title = "数量", order = 4)
    private Integer productNum;
    @ExcelField(title = "支付方式", order = 5)
    private String payType = "";
    @ExcelField(title = "订单金额", order = 6)
    private BigDecimal orderPrice;
    @ExcelField(title = "实付金额", order = 7)
    private BigDecimal payPrice;
    @ExcelField(title = "订单总额", order = 8)
    private BigDecimal totalProductPrice;
    @ExcelField(title = "买家昵称", order = 9)
    private String nickname;
    @ExcelField(title = "收货人", order = 10)
    private String receiver;
    @ExcelField(title = "收货电话", order = 11)
    private String receiveMobile;
    @ExcelField(title = "用户帐号", order = 12)
    private String phone;
    @ExcelField(title = "收货邮编", order = 13)
    private String zipcode;
    @ExcelField(title = "省", order = 14)
    private String provinceName;
    @ExcelField(title = "市", order = 15)
    private String cityName;
    @ExcelField(title = "区", order = 16)
    private String countyName;
    @ExcelField(title = "详细地址", order = 17)
    private String receiveAddress;
    @ExcelField(title = "买家留言", order = 18)
    private String clientRemarks = "未留言";
    @ExcelField(title = "商家留言", order = 19)
    private String shopRemarks;
    @ExcelField(title = "订单运费", order = 20)
    private BigDecimal postagePrice;
    @ExcelField(title = "下单时间", order = 21)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createDate;
    @ExcelField(title = "订单状态", order = 22)
    private String orderStatus;
    @ExcelField(title = "订单来源", order = 23)
    private String fromType = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderTradeNo() {
        return orderTradeNo;
    }

    public void setOrderTradeNo(String orderTradeNo) {
        this.orderTradeNo = orderTradeNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public BigDecimal getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(BigDecimal totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getClientRemarks() {
        return clientRemarks;
    }

    public void setClientRemarks(String clientRemarks) {
        this.clientRemarks = clientRemarks;
    }

    public String getShopRemarks() {
        return shopRemarks;
    }

    public void setShopRemarks(String shopRemarks) {
        this.shopRemarks = shopRemarks;
    }

    public BigDecimal getPostagePrice() {
        return postagePrice;
    }

    public void setPostagePrice(BigDecimal postagePrice) {
        this.postagePrice = postagePrice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }
}
