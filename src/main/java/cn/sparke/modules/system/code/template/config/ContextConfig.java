package cn.sparke.modules.system.code.template.config;


import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.system.code.bean.TableColumnBean;

import java.util.List;

/**
 * 全局配置
 *
 * @author spark
 * @date 2017-05-08 20:21
 */
public class ContextConfig {

    private String projectPath = "D:\\ideaSpace\\spark";//模板输出的项目目录
    private String bizChName;   //业务名称
    private String bizEnName;   //业务英文名称
    private String bizEnBigName;//业务英文名称(大写)
    private Boolean controllerSwitch = true;    //是否生成控制器代码开关
    private Boolean indexPageSwitch = true;     //主页
    private Boolean addPageSwitch = true;       //添加页面
    private Boolean editPageSwitch = true;      //编辑页面
    private Boolean jsSwitch = true;            //js
    private Boolean infoJsSwitch = true;        //详情页面js
    private Boolean daoSwitch = true;           //是否生成dao
    private Boolean serviceSwitch = true;       //service
    private Boolean entitySwitch = true;        //是否生成实体
    private Boolean mapperSwitch = true;        //是否生成Mapper
    private String tableName;
    private List<TableColumnBean> tablePropertyList;//要生成的表格属性列表
    private Boolean hasProperties = false;
    private List<TableColumnBean> entityPropertyList;//要生成的实体属性列表

    public List<TableColumnBean> getEntityPropertyList() {
        return entityPropertyList;
    }

    public void setEntityPropertyList(List<TableColumnBean> entityPropertyList) {
        this.entityPropertyList = entityPropertyList;
    }

    public Boolean getHasProperties() {
        return hasProperties;
    }

    public void setHasProperties(Boolean hasProperties) {
        this.hasProperties = hasProperties;
    }

    public List<TableColumnBean> getTablePropertyList() {
        return tablePropertyList;
    }

    public void setTablePropertyList(List<TableColumnBean> tablePropertyList) {
        this.tablePropertyList = tablePropertyList;
    }

    public Boolean getMapperSwitch() {
        return mapperSwitch;
    }

    public void setMapperSwitch(Boolean mapperSwitch) {
        this.mapperSwitch = mapperSwitch;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getEntitySwitch() {
        return entitySwitch;
    }

    public void setEntitySwitch(Boolean entitySwitch) {
        this.entitySwitch = entitySwitch;
    }

    public String getBizEnBigName() {
        return bizEnBigName;
    }

    public void setBizEnBigName(String bizEnBigName) {
        this.bizEnBigName = bizEnBigName;
    }

    public String getBizChName() {
        return bizChName;
    }

    public void setBizChName(String bizChName) {
        this.bizChName = bizChName;
    }

    public String getBizEnName() {
        return bizEnName;
    }

    public void setBizEnName(String bizEnName) {
        this.bizEnName = bizEnName;
        this.bizEnBigName = ToolUtil.firstLetterToUpper(this.bizEnName);
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public Boolean getControllerSwitch() {
        return controllerSwitch;
    }

    public void setControllerSwitch(Boolean controllerSwitch) {
        this.controllerSwitch = controllerSwitch;
    }

    public Boolean getIndexPageSwitch() {
        return indexPageSwitch;
    }

    public void setIndexPageSwitch(Boolean indexPageSwitch) {
        this.indexPageSwitch = indexPageSwitch;
    }

    public Boolean getAddPageSwitch() {
        return addPageSwitch;
    }

    public void setAddPageSwitch(Boolean addPageSwitch) {
        this.addPageSwitch = addPageSwitch;
    }

    public Boolean getEditPageSwitch() {
        return editPageSwitch;
    }

    public void setEditPageSwitch(Boolean editPageSwitch) {
        this.editPageSwitch = editPageSwitch;
    }

    public Boolean getJsSwitch() {
        return jsSwitch;
    }

    public void setJsSwitch(Boolean jsSwitch) {
        this.jsSwitch = jsSwitch;
    }

    public Boolean getInfoJsSwitch() {
        return infoJsSwitch;
    }

    public void setInfoJsSwitch(Boolean infoJsSwitch) {
        this.infoJsSwitch = infoJsSwitch;
    }

    public Boolean getDaoSwitch() {
        return daoSwitch;
    }

    public void setDaoSwitch(Boolean daoSwitch) {
        this.daoSwitch = daoSwitch;
    }

    public Boolean getServiceSwitch() {
        return serviceSwitch;
    }

    public void setServiceSwitch(Boolean serviceSwitch) {
        this.serviceSwitch = serviceSwitch;
    }
}
