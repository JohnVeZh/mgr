package cn.sparke.modules.system.dept.entity;


import cn.sparke.core.common.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author sparke
 * @since 2017-07-11
 */
public class Dept extends BaseEntity {

    /**
     * 排序
     */
    private Integer num;
    private String pName;
    /**
     * 父部门id
     */
    private String pid;
    /**
     * 父级ids
     */
    private String pids;
    /**
     * 简称
     */
    private String simplename;
    /**
     * 全称
     */
    @NotBlank
    private String fullname;
    /**
     * 提示
     */
    private String tips;
    /**
     * 版本（乐观锁保留字段）
     */
    private Integer version;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public String getSimplename() {
        return simplename;
    }

    public void setSimplename(String simplename) {
        this.simplename = simplename;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
