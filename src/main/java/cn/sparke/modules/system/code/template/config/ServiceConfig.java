package cn.sparke.modules.system.code.template.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Service模板生成的配置
 *
 * @author spark
 * @date 2017-05-07 22:12
 */
public class ServiceConfig {

    private ContextConfig contextConfig;

    private String servicePathTemplate;

    private String packageName;

    private String mapperPackageName;

    private List<String> serviceImports;

    public void init() {
        ArrayList<String> imports = new ArrayList<>();
        imports.add("org.springframework.stereotype.Service");
        imports.add("org.springframework.beans.factory.annotation.Autowired");
        imports.add("com.github.pagehelper.Page");
        imports.add("cn.sparke.modules." + contextConfig.getBizEnName() + ".mapper." + contextConfig.getBizEnBigName() + "Mapper");
        imports.add("cn.sparke.modules." + contextConfig.getBizEnName() + ".entity." + contextConfig.getBizEnBigName() + "Entity");
        this.serviceImports = imports;
        this.servicePathTemplate = "\\src\\main\\java\\cn\\sparke\\modules\\" + contextConfig.getBizEnName() + "\\service\\{}Service.java";
        this.packageName = "cn.sparke.modules." + contextConfig.getBizEnName() + ".service";
    }

    public String getMapperPackageName() {
        return mapperPackageName;
    }

    public void setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }

    public String getServicePathTemplate() {
        return servicePathTemplate;
    }

    public void setServicePathTemplate(String servicePathTemplate) {
        this.servicePathTemplate = servicePathTemplate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getServiceImports() {
        return serviceImports;
    }

    public void setServiceImports(List<String> serviceImports) {
        this.serviceImports = serviceImports;
    }
}
