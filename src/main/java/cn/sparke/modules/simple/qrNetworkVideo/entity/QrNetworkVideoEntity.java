package cn.sparke.modules.simple.qrNetworkVideo.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 简系列视频Entity
 *
 * @author spark
 * @Date 2017-08-09 14:26:29
 */
public class QrNetworkVideoEntity extends BaseEntity{
    //学段
    private String sectionCode;
    //目录id
    private String catalogId;
    //视频名称
    private String name;
    //二维码
    private String qrCode;
    //视频ccid
    private String ccId;
    //图片路径
    private String img;

    public void setSectionCode(String sectionCode){
        this.sectionCode = sectionCode;
    }
    public String getSectionCode(){
        return this.sectionCode;
    }
    public void setCatalogId(String catalogId){
        this.catalogId = catalogId;
    }
    public String getCatalogId(){
        return this.catalogId;
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
    public void setCcId(String ccId){
        this.ccId = ccId;
    }
    public String getCcId(){
        return this.ccId;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }

}
