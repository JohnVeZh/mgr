package cn.sparke.modules.system.code.template.config;

/**
 * Created by zhangbowen on 2017/7/18.
 */
public class MappingConfig {
    private ContextConfig contextConfig;

    private String xmlPathTemplate;
    private String packageName;
    private String entityPackageName;

    public void init() {
        this.xmlPathTemplate = "\\src\\main\\resources\\mybatis\\mapping\\" + contextConfig.getBizEnName() + "\\{}Mapper.xml";
        this.packageName = "cn.sparke.modules." + contextConfig.getBizEnName() + ".mapper";
        this.entityPackageName = "cn.sparke.modules." + contextConfig.getBizEnName() + ".entity";
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }

    public String getXmlPathTemplate() {
        return xmlPathTemplate;
    }

    public void setXmlPathTemplate(String xmlPathTemplate) {
        this.xmlPathTemplate = xmlPathTemplate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getEntityPackageName() {
        return entityPackageName;
    }

    public void setEntityPackageName(String entityPackageName) {
        this.entityPackageName = entityPackageName;
    }
}
