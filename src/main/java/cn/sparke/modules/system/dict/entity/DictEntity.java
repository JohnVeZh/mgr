package cn.sparke.modules.system.dict.entity;

import cn.sparke.core.common.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created by zhangbowen on 2017/5/5.
 */
public class DictEntity extends BaseEntity {
    private String value;
    @NotBlank
    private String label;
    @NotBlank
    private String type;
    @NotBlank
    private String description;
    private String parentId;
    private List<DictEntity> children;

    public List<DictEntity> getChildren() {
        return children;
    }

    public void setChildren(List<DictEntity> children) {
        this.children = children;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
