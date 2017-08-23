package cn.sparke.modules.wordsCatalog.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 词汇栏目管理Entity
 *
 * @author spark
 * @Date 2017-07-25 19:51:11
 */
public class WordsCatalogEntity extends BaseEntity{
    //名称
    private String name;
    //code
    private String code;
    //父id
    private String parentId;

    private String parentCatalogName;
    //父id集合，以英文逗号,分隔
    private String parentIds;
    //
    private Integer sectionCode;
    //级别
    private Integer level;
    //是否是叶子节点(0.否 1.是)
    private Integer isLeaf;
    //类型（1.常用词汇 2.专项练习 ）
    private Integer type;
    //单词总数
    private Integer totalNum;
    //icon路径地址
    private String iconUrl;

    public String getParentCatalogName() {
        return parentCatalogName;
    }

    public void setParentCatalogName(String parentCatalogName) {
        this.parentCatalogName = parentCatalogName;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setParentId(String parentId){
        this.parentId = parentId;
    }
    public String getParentId(){
        return this.parentId;
    }
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
    public String getParentIds(){
        return this.parentIds;
    }
    public void setSectionCode(Integer sectionCode){
        this.sectionCode = sectionCode;
    }
    public Integer getSectionCode(){
        return this.sectionCode;
    }
    public void setLevel(Integer level){
        this.level = level;
    }
    public Integer getLevel(){
        return this.level;
    }
    public void setIsLeaf(Integer isLeaf){
        this.isLeaf = isLeaf;
    }
    public Integer getIsLeaf(){
        return this.isLeaf;
    }
    public void setType(Integer type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    public void setTotalNum(Integer totalNum){
        this.totalNum = totalNum;
    }
    public Integer getTotalNum(){
        return this.totalNum;
    }
    public void setIconUrl(String iconUrl){
        this.iconUrl = iconUrl;
    }
    public String getIconUrl(){
        return this.iconUrl;
    }

}
