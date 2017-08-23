package cn.sparke.modules.system.code.template.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbowen on 2017/7/18.
 */
public class EntityConfig {
    private ContextConfig contextConfig;

    private String entityPathTemplate;
    private String packageName;//包名称
    private List<String> imports;//所引入的包

    public void init() {
        ArrayList<String> imports = new ArrayList<>();
        imports.add("cn.sparke.core.common.entity.BaseEntity");
        imports.add("java.util.*");
        imports.add("java.math.*");
        this.imports = imports;
        this.packageName = "cn.sparke.modules." + contextConfig.getBizEnName() + ".entity";
        this.entityPathTemplate = "\\src\\main\\java\\cn\\sparke\\modules\\" + contextConfig.getBizEnName() + "\\entity\\{}Entity.java";
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getEntityPathTemplate() {
        return entityPathTemplate;
    }

    public void setEntityPathTemplate(String entityPathTemplate) {
        this.entityPathTemplate = entityPathTemplate;
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }
}
