package cn.sparke.modules.qrcode.qrCode.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 二维码Entity
 *
 * @author spark
 * @Date 2017-07-21 20:14:52
 */
public class QrCodeEntity extends BaseEntity{
    //使用场景1简系列,2配套专区
    private Integer useScene;
    //二维码内容
    private String code;
    //学段(配套专区会用到)
    private Integer sectionCode;
    //二维码类型（1.简系列考场原音 2.简系列碎片 3.简系列学习视频 4.简系列简写作 5.简系列词汇 6.简系列字幕听力 7.简系列写作翻译,8:配套专区全真考场,9:配套专区名师视频,10:配套专区常用词汇,11配套专区字幕听力)
    private Integer type;
    //目标类型 1列表 2详情
    private Integer targetType;

    public void setUseScene(Integer useScene){
        this.useScene = useScene;
    }
    public Integer getUseScene(){
        return this.useScene;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    public void setTargetType(Integer targetType){
        this.targetType = targetType;
    }
    public Integer getTargetType(){
        return this.targetType;
    }

}
