package cn.sparke.modules.orders.order.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;

/**
 * 订单物流Entity
 *
 * @author spark
 * @Date 2017-07-19 09:54:57
 */
public class OrderLogisticsEntity extends BaseEntity{
    //订单id
    private String orderId;
    //省id
    private String provinceId;
    //城市id
    private String cityId;
    //区id
    private String countyId;
    //省市区县全称
    private String provinceCityCn;
    //邮编
    private String zipcode;
    //收货人
    private String receiver;
    //收货人电话
    private String receiveMobile;
    //详细地址
    private String receiveAddress;
    //收货时间
    private Date receiveTime;
    //发货时间
    private Date deliveryTime;
    //物流公司code
    private String expressCode;
    //快递公司名称
    private String expressName;
    //物流单号
    private String logisticsCode;

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }
    public String getOrderId(){
        return this.orderId;
    }
    public void setProvinceId(String provinceId){
        this.provinceId = provinceId;
    }
    public String getProvinceId(){
        return this.provinceId;
    }
    public void setCityId(String cityId){
        this.cityId = cityId;
    }
    public String getCityId(){
        return this.cityId;
    }
    public void setCountyId(String countyId){
        this.countyId = countyId;
    }
    public String getCountyId(){
        return this.countyId;
    }
    public void setProvinceCityCn(String provinceCityCn){
        this.provinceCityCn = provinceCityCn;
    }
    public String getProvinceCityCn(){
        return this.provinceCityCn;
    }
    public void setZipcode(String zipcode){
        this.zipcode = zipcode;
    }
    public String getZipcode(){
        return this.zipcode;
    }
    public void setReceiver(String receiver){
        this.receiver = receiver;
    }
    public String getReceiver(){
        return this.receiver;
    }
    public void setReceiveMobile(String receiveMobile){
        this.receiveMobile = receiveMobile;
    }
    public String getReceiveMobile(){
        return this.receiveMobile;
    }
    public void setReceiveAddress(String receiveAddress){
        this.receiveAddress = receiveAddress;
    }
    public String getReceiveAddress(){
        return this.receiveAddress;
    }
    public void setReceiveTime(Date receiveTime){
        this.receiveTime = receiveTime;
    }
    public Date getReceiveTime(){
        return this.receiveTime;
    }
    public void setDeliveryTime(Date deliveryTime){
        this.deliveryTime = deliveryTime;
    }
    public Date getDeliveryTime(){
        return this.deliveryTime;
    }
    public void setExpressCode(String expressCode){
        this.expressCode = expressCode;
    }
    public String getExpressCode(){
        return this.expressCode;
    }
    public void setExpressName(String expressName){
        this.expressName = expressName;
    }
    public String getExpressName(){
        return this.expressName;
    }
    public void setLogisticsCode(String logisticsCode){
        this.logisticsCode = logisticsCode;
    }
    public String getLogisticsCode(){
        return this.logisticsCode;
    }

}
