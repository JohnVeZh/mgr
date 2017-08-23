package cn.sparke.modules.orders.order.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 快递Entity
 *
 * @author spark
 * @Date 2017-07-23 11:05:39
 */
public class ExpressEntity extends BaseEntity{
    //公司名称
    private String name;
    //状态(0.禁用 1.启用)
    private Integer state;
    //快递公司编号
    private String code;
    //首字母
    private String letter;
    //1常用2不常用
    private Integer order;
    //公司网址
    private String url;
    //是否支持服务站配送0否1是
    private Integer hasStation;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setState(Integer state){
        this.state = state;
    }
    public Integer getState(){
        return this.state;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setLetter(String letter){
        this.letter = letter;
    }
    public String getLetter(){
        return this.letter;
    }
    public void setOrder(Integer order){
        this.order = order;
    }
    public Integer getOrder(){
        return this.order;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setHasStation(Integer hasStation){
        this.hasStation = hasStation;
    }
    public Integer getHasStation(){
        return this.hasStation;
    }

}
