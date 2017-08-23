package cn.sparke.modules.simple.qrCatalog.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 简系列目录Entity
 *
 * @author spark
 * @Date 2017-08-09 10:23:51
 */
public class QrCatalogEntity extends BaseEntity{
    //目录名称
    private String name;
    //二维码
    private String qrCode;
    //类型(1.视频 2.写作)
    private Integer type;
    private  String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setQrCode(String qrCode){
        this.qrCode = qrCode;
    }
    public String getQrCode(){
        return this.qrCode;
    }
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }

}
