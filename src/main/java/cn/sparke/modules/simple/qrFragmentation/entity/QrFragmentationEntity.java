package cn.sparke.modules.simple.qrFragmentation.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 简系列碎片化Entity
 *
 * @author spark
 * @Date 2017-08-09 17:07:15
 */
public class QrFragmentationEntity extends BaseEntity{
    //二维码
    private String qrCode;
    //听力路径
    private String hearUrl;
    //名称
    private String name;
    //类型(1.碎片 2.写作翻译)
    private Integer type;
    //学段(写作翻译时用到)
    private Integer sectionCode;
    //目录id
    private String catalogId;

    public void setQrCode(String qrCode){
        this.qrCode = qrCode;
    }
    public String getQrCode(){
        return this.qrCode;
    }
    public void setHearUrl(String hearUrl){
        this.hearUrl = hearUrl;
    }
    public String getHearUrl(){
        return this.hearUrl;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setCatalogId(String catalogId){
        this.catalogId = catalogId;
    }
    public String getCatalogId(){
        return this.catalogId;
    }

}
