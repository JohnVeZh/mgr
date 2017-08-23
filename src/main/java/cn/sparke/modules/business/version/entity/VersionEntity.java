package cn.sparke.modules.business.version.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 版本Entity
 *
 * @author spark
 * @Date 2017-07-26 10:08:44
 */
public class VersionEntity extends BaseEntity{
    //标题
    private String title;
    //版本code，比较使用
    private Integer code;
    //版本号
    private String version;
    //简介
    private String summary;
    //下载地址
    private String downloadUrl;
    //版本类型(1.android  2.ios)
    private Integer type;
    //是否强制更新(0.否 1.是)
    private Integer isForce;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setCode(Integer code){
        this.code = code;
    }
    public Integer getCode(){
        return this.code;
    }
    public void setVersion(String version){
        this.version = version;
    }
    public String getVersion(){
        return this.version;
    }
    public void setSummary(String summary){
        this.summary = summary;
    }
    public String getSummary(){
        return this.summary;
    }
    public void setDownloadUrl(String downloadUrl){
        this.downloadUrl = downloadUrl;
    }
    public String getDownloadUrl(){
        return this.downloadUrl;
    }
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    public void setIsForce(Integer isForce){
        this.isForce = isForce;
    }
    public Integer getIsForce(){
        return this.isForce;
    }

}
