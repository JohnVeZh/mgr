package cn.sparke.modules.paper.paperCatalog.entity;

import cn.sparke.core.common.entity.BaseEntity;

import java.util.List;

/**
 * 试卷目录Entity
 *
 * @author spark
 * @Date 2017-07-19 09:51:45
 */
public class PaperCatalogEntity extends BaseEntity{
    //目录名称
    private String name;
    //类型(1.全真考场 2.字幕听力 3.简系列 4.专项练习,5扫码字幕听力)
    private Integer type;
    //学段code
    private Integer sectionCode;
    //二维码
    private String qrCode;
    //父id
    private String parentId;
    private String parentName;
    //级别
    private Integer level;

    //查询
    private List<String> typeList;

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
    public void setQrCode(String qrCode){
        this.qrCode = qrCode;
    }
    public String getQrCode(){
        return this.qrCode;
    }
    public void setParentId(String parentId){
        this.parentId = parentId;
    }
    public String getParentId(){
        return this.parentId;
    }
    public void setLevel(Integer level){
        this.level = level;
    }
    public Integer getLevel(){
        return this.level;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }
}
