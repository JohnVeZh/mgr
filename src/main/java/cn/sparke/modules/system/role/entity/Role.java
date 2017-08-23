package cn.sparke.modules.system.role.entity;

import cn.sparke.core.common.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zhangbowen on 2017/7/17.
 */
public class Role extends BaseEntity {
    /**
     * 父角色id
     */
    @NotBlank
    private String pid;
    /**
     * 角色名称
     */
    @NotBlank
    private String name;
    /**
     * 部门名称
     */
    @NotBlank
    private String deptid;
    /**
     * 提示
     */
    private String tips;
    private String pName;
    private String deptName;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

}
