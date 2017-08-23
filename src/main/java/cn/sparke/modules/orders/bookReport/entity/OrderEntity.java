package cn.sparke.modules.orders.bookReport.entity;

import cn.sparke.core.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单Entity
 *
 * @author spark
 * @Date 2017-07-19 09:47:28
 */
public class OrderEntity extends BaseEntity{
    //用户id
    private String userId;
    //订单类型（1. 网课  2.图书 ）
    private Integer type;
    //1. 待支付 2.待发货 3.待收货 4.待评论 5.已完成 6.已取消
    private Integer orderStatus;
    //订单号
    private String orderCode;
    //订单交易号
    private String orderTradeNo;
    //支付方式（1.支付宝 2.微信 3.兑换码）
    private Integer payType;
    //实付金额（等于使用支付方式付款 等于订单金额 ）
    private BigDecimal payPrice;
    //订单金额（等于应付金额等于商品总金额+邮费-优惠券金额）
    private BigDecimal orderPrice;
    //商品总金额
    private BigDecimal totalProductPrice;
    //优惠金额
    private BigDecimal discountPrice;
    //邮费金额
    private BigDecimal postagePrice;
    //商品总数(展示使用)
    private Integer productNum;
    //支付时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    //交易号
    private String tradeNo;
    //订单来源（ 0.老数据 1: android 2.iOS; 3: M站; 4: pc; 5: 知米;）
    private Integer fromType;
    //是否需要邮寄（0.否 1.是）
    private Integer isSend;
    //使用的优惠券的id
    private String couponId;
    //兑换码id
    private String redeemId;
    //是否管理员删除（0: 否; 1: 是）-
    private Integer isAdminDel;
    //是否用户删除（0: 否; 1: 是）-
    private Integer isUserDel;

    private Integer isEvaluate;

    /* 非数据库字段 */
    private List<OrderDetailsEntity> orderDetailsList;  // 订单详情列表
    private UserEntity  user;                           // 用户信息
    private OrderLogisticsEntity orderLogistics;        // 订单物流信息

    // 查询字段
    private String qOrderCode;
    private String qPhone;
    private String qReceiver;
    private Integer qOrderStatus;
    private Integer qRefundStatus;
    private String qStartTime;
    private String qEndTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderTradeNo() {
        return orderTradeNo;
    }

    public void setOrderTradeNo(String orderTradeNo) {
        this.orderTradeNo = orderTradeNo;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(BigDecimal totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getPostagePrice() {
        return postagePrice;
    }

    public void setPostagePrice(BigDecimal postagePrice) {
        this.postagePrice = postagePrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getRedeemId() {
        return redeemId;
    }

    public void setRedeemId(String redeemId) {
        this.redeemId = redeemId;
    }

    public Integer getIsAdminDel() {
        return isAdminDel;
    }

    public void setIsAdminDel(Integer isAdminDel) {
        this.isAdminDel = isAdminDel;
    }

    public Integer getIsUserDel() {
        return isUserDel;
    }

    public void setIsUserDel(Integer isUserDel) {
        this.isUserDel = isUserDel;
    }

    public Integer getIsEvaluate() {
        return isEvaluate;
    }

    public void setIsEvaluate(Integer isEvaluate) {
        this.isEvaluate = isEvaluate;
    }

    public List<OrderDetailsEntity> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetailsEntity> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public OrderLogisticsEntity getOrderLogistics() {
        return orderLogistics;
    }

    public void setOrderLogistics(OrderLogisticsEntity orderLogistics) {
        this.orderLogistics = orderLogistics;
    }

    public String getqOrderCode() {
        return qOrderCode;
    }

    public void setqOrderCode(String qOrderCode) {
        this.qOrderCode = qOrderCode;
    }

    public String getqPhone() {
        return qPhone;
    }

    public void setqPhone(String qPhone) {
        this.qPhone = qPhone;
    }

    public String getqReceiver() {
        return qReceiver;
    }

    public void setqReceiver(String qReceiver) {
        this.qReceiver = qReceiver;
    }

    public Integer getqOrderStatus() {
        return qOrderStatus;
    }

    public void setqOrderStatus(Integer qOrderStatus) {
        this.qOrderStatus = qOrderStatus;
    }

    public Integer getqRefundStatus() {
        return qRefundStatus;
    }

    public void setqRefundStatus(Integer qRefundStatus) {
        this.qRefundStatus = qRefundStatus;
    }

    public String getqStartTime() {
        return qStartTime;
    }

    public void setqStartTime(String qStartTime) {
        this.qStartTime = qStartTime;
    }

    public String getqEndTime() {
        return qEndTime;
    }

    public void setqEndTime(String qEndTime) {
        this.qEndTime = qEndTime;
    }
}
