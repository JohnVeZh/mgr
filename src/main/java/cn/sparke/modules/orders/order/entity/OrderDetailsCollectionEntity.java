package cn.sparke.modules.orders.order.entity;

import cn.sparke.core.common.entity.BaseEntity;

import java.math.*;

/**
 * 订单详情套餐Entity
 *
 * @author spark
 * @Date 2017-07-19 10:07:51
 */
public class OrderDetailsCollectionEntity extends BaseEntity{
    //订单详情id
    private String orderDetailId;
    //搭配商品id
    private String productId;
    //搭配商品价格
    private BigDecimal productPrice;
    //搭配商品名称
    private String productName;
    //搭配商品图片
    private String productImg;
    //搭配类型(1: 网课; 2: 图书 3.其他.)
    private Integer type;
    //赠送数量
    private Integer num;

    public void setOrderDetailId(String orderDetailId){
        this.orderDetailId = orderDetailId;
    }
    public String getOrderDetailId(){
        return this.orderDetailId;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }
    public String getProductId(){
        return this.productId;
    }
    public void setProductPrice(BigDecimal productPrice){
        this.productPrice = productPrice;
    }
    public BigDecimal getProductPrice(){
        return this.productPrice;
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
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    public void setNum(Integer num){
        this.num = num;
    }
    public Integer getNum(){
        return this.num;
    }

}
