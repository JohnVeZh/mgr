package cn.sparke.modules.orders.order.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 订单详情Entity
 *
 * @author spark
 * @Date 2017-07-19 10:00:33
 */
public class OrderDetailsEntity extends BaseEntity{
    //订单id
    private String orderId;
    //商品id
    private String productId;
    //购买数量
    private Integer num;
    //商品类型（1:  网课; 2:图书; ）
    private Integer productType;
    //商品名
    private String productName;
    //商品图片
    private String productImg;
    //商品价格
    private BigDecimal productPrice;
    //应付金额（商品价格*数量-优惠金额）
    private BigDecimal dealPrice;
    //优惠金额(所有商品的优惠金额)
    private BigDecimal discountPrice;
    //是否有套餐搭配(0.否 1.是)
    private Integer hasCollection;
    //退款状态（0.未退款 1.退款中 2.拒绝退款 3.退款完成）
    private Integer refundStatus;
    //是否评价（0.否 1是）
    private Integer isComment;

    private List<OrderDetailsCollectionEntity> orderDetailsCollection;

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }
    public String getOrderId(){
        return this.orderId;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }
    public String getProductId(){
        return this.productId;
    }
    public void setNum(Integer num){
        this.num = num;
    }
    public Integer getNum(){
        return this.num;
    }
    public void setProductType(Integer productType){
        this.productType = productType;
    }
    public Integer getProductType(){
        return this.productType;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public String getProductName(){
        return this.productName;
    }
    public void setProductImg(String productImg){
        this.productImg = productImg;
    }
    public String getProductImg(){
        return this.productImg;
    }
    public void setProductPrice(BigDecimal productPrice){
        this.productPrice = productPrice;
    }
    public BigDecimal getProductPrice(){
        return this.productPrice;
    }
    public void setDealPrice(BigDecimal dealPrice){
        this.dealPrice = dealPrice;
    }
    public BigDecimal getDealPrice(){
        return this.dealPrice;
    }
    public void setDiscountPrice(BigDecimal discountPrice){
        this.discountPrice = discountPrice;
    }
    public BigDecimal getDiscountPrice(){
        return this.discountPrice;
    }
    public void setHasCollection(Integer hasCollection){
        this.hasCollection = hasCollection;
    }
    public Integer getHasCollection(){
        return this.hasCollection;
    }
    public void setRefundStatus(Integer refundStatus){
        this.refundStatus = refundStatus;
    }
    public Integer getRefundStatus(){
        return this.refundStatus;
    }
    public void setIsComment(Integer isComment){
        this.isComment = isComment;
    }
    public Integer getIsComment(){
        return this.isComment;
    }

    public List<OrderDetailsCollectionEntity> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(List<OrderDetailsCollectionEntity> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }
}
