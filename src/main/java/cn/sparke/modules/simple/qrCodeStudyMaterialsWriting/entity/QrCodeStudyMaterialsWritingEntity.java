package cn.sparke.modules.simple.qrCodeStudyMaterialsWriting.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 简系列写作翻译阅读Entity
 *
 * @author spark
 * @Date 2017-08-10 10:58:45
 */
public class QrCodeStudyMaterialsWritingEntity extends BaseEntity{
    //
    private String title;
    //
    private String content;
    //
    private String qrCode;
    //0：写作 1：翻译 2：阅读
    private Integer type;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
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
