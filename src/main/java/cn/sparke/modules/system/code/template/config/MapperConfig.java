package cn.sparke.modules.system.code.template.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao模板生成的配置
 *
 * @author spark
 * @date 2017-05-07 22:12
 */
public class MapperConfig {

    private ContextConfig contextConfig;

    private String daoPathTemplate;
    private List<String> imports;//所引入的包
    private String packageName;

    public void init() {
        ArrayList<String> imports = new ArrayList<>();

        imports.add("cn.sparke.core.modules.mybatis.mapper.BaseMapper");
        imports.add("cn.sparke.modules." + contextConfig.getBizEnName() + ".entity." + contextConfig.getBizEnBigName() + "Entity");
        this.imports = imports;

        this.daoPathTemplate = "\\src\\main\\java\\cn\\sparke\\modules\\" + contextConfig.getBizEnName() + "\\mapper\\{}Mapper.java";
        this.packageName = "cn.sparke.modules." + contextConfig.getBizEnName() + ".mapper";
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDaoPathTemplate() {
        return daoPathTemplate;
    }

    public void setDaoPathTemplate(String daoPathTemplate) {
        this.daoPathTemplate = daoPathTemplate;
    }


    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }
}
