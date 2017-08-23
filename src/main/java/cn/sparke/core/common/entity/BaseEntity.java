package cn.sparke.core.common.entity;

import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.core.modules.shiro.ShiroKit;
import cn.sparke.core.modules.shiro.ShiroUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;

import java.util.Date;

/**
 * Created by zhangbowen on 2017/7/16.
 */
public abstract class BaseEntity {
    private String id;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //创建人
    private String createBy;
    //修改人
    private String updateBy;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    //删除标识(0:未删除1：删除)
    private Integer delFlag = 0;
    private String remarks;
    private Integer sort;
    private String condition;
    private Integer pageNum;
    private Integer pageSize;
    private String sortName;
    private String sortOrder;

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert() {
        ShiroUser user = ShiroKit.getUser();
        if (user != null) {
            this.updateBy = user.getId();
            this.createBy = user.getId();
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
        this.id = ToolUtil.uuid();
    }

    public void preUpdate() {
        ShiroUser user = ShiroKit.getUser();
        if (user != null) {
            this.updateBy = user.getId() + "";
        }
        this.updateDate = new Date();
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    private static final long serialVersionUID = 1L;

}
