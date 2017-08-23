package cn.sparke.modules.paper.paperGroup.entity;

import cn.sparke.core.common.entity.BaseEntity;
import cn.sparke.core.common.utils.ToolUtil;

import java.util.*;
import java.math.*;

/**
 * 试卷组Entity
 *
 * @author spark
 * @Date 2017-07-27 17:02:14
 */
public class PaperGroupEntity extends BaseEntity{
    //目录id
    private String catalogId;
    private String catalogName;
    //试卷组名称
    private String name;
    //
    private String img;

    //查询
    private String paperIds;
    private String groupIds;
    private List<String> groupIdList;
    private List<String> catalogIdList;
    private Integer catalogType;

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
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }

    public String getPaperIds() {
        return paperIds;
    }

    public void setPaperIds(String paperIds) {
        this.paperIds = paperIds;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public List<String> getGroupIdList() {
        if (ToolUtil.isNotEmpty(this.groupIds)) {
            return Arrays.asList(this.groupIds.split(","));
        } else {
            return null;
        }
    }

    public void setGroupIdList(List<String> groupIdList) {
        this.groupIdList = groupIdList;
    }

    public Integer getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(Integer catalogType) {
        this.catalogType = catalogType;
    }

    public List<String> getCatalogIdList() {
        return catalogIdList;
    }

    public void setCatalogIdList(List<String> catalogIdList) {
        this.catalogIdList = catalogIdList;
    }
}
