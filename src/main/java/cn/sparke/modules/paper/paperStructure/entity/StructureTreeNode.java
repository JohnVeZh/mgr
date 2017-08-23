package cn.sparke.modules.paper.paperStructure.entity;

/**
 * @author <a href="mailto:peng.huantao@qq.com">PengHuantao</a>
 * @version 1.0
 * @date 2017/8/11
 */
public class StructureTreeNode {

    private String id;	//节点id

    private String pId;//父节点id

    private String name;//节点名称

    private Boolean open;//是否打开节点

    private Boolean checked;//是否被选中

    private Integer contentType;//题目类型

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}
