package cn.sparke.modules.paper.paperStructure.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 试卷树形结构Entity
 *
 * @author spark
 * @Date 2017-07-27 10:36:08
 */
public class PaperStructureEntity extends BaseEntity {
    //试卷id
    private String paperId;
    //名称(part 1 section one)
    private String name;
    //描述
    private String content;
    //题目类型(1.听力 2.阅读 3.翻译 4.写作)
    private Integer contentType;
    //别名(写作 阅读 section one)
    private String alias;
    //父id
    private String parentId;
    private String parentName;

    //
    private String parentIds;
    //级别
    private Integer level;
    //是否叶子叶点(1.否 1.是)(叶子节点才会存在题目)
    private Integer isLeaf;

    public void setPaperId(String paperId){
        this.paperId = paperId;
    }
    public String getPaperId(){
        return this.paperId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setContentType(Integer contentType){
        this.contentType = contentType;
    }
    public Integer getContentType(){
        return this.contentType;
    }
    public void setAlias(String alias){
        this.alias = alias;
    }
    public String getAlias(){
        return this.alias;
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
    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
