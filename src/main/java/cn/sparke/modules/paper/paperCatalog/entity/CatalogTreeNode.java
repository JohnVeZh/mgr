package cn.sparke.modules.paper.paperCatalog.entity;

/**
 * @author <a href="mailto:peng.huantao@qq.com">PengHuantao</a>
 * @version 1.0
 * @date 2017/8/11
 */
public class CatalogTreeNode {

    private String id;	//节点id

    private String pId;//父节点id

    private String name;//节点名称

    private Boolean open;//是否打开节点

    private Boolean checked;//是否被选中

    private Integer type;//目录类型

    private Integer sectionCode;//学段

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(Integer sectionCode) {
        this.sectionCode = sectionCode;
    }
}
