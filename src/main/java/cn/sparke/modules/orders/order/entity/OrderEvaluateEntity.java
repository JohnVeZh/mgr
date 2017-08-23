package cn.sparke.modules.orders.order.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 订单评价Entity
 *
 * @author spark
 * @Date 2017-07-22 16:20:23
 */
public class OrderEvaluateEntity extends BaseEntity{
    //用户id
    private String userId;
    //
    private String orderDetailId;
    //订单id
    private String orderId;
    //订单商品
    private String productId;
    //评价内容
    private String content;
    //评价晒图,逗号分隔
    private String imgs;
    //讲课思路评分
    private Integer thinkingScore;
    //讲师风格评分
    private Integer styleScore;
    //课程板书评分
    private Integer curriculumScore;
    //平均分
    private Integer averageScore;

    /* 非数据库字段 */
    private String productName;
    private String productImg;
    private String nickname;
    private String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Integer getThinkingScore() {
        return thinkingScore;
    }

    public void setThinkingScore(Integer thinkingScore) {
        this.thinkingScore = thinkingScore;
    }

    public Integer getStyleScore() {
        return styleScore;
    }

    public void setStyleScore(Integer styleScore) {
        this.styleScore = styleScore;
    }

    public Integer getCurriculumScore() {
        return curriculumScore;
    }

    public void setCurriculumScore(Integer curriculumScore) {
        this.curriculumScore = curriculumScore;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
