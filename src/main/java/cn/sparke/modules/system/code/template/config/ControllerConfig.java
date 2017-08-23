package cn.sparke.modules.system.code.template.config;

import cn.sparke.core.common.utils.ToolUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制器模板生成的配置
 *
 * @author spark
 * @date 2017-05-07 22:12
 */
public class ControllerConfig {

    private ContextConfig contextConfig;

    private String controllerPathTemplate;
    private String packageName;//包名称
    private List<String> imports;//所引入的包
    private String serviceName;

    public void init() {
        ArrayList<String> imports = new ArrayList<>();

        imports.add("org.springframework.beans.factory.annotation.Autowired");
        imports.add("org.springframework.validation.annotation.Validated");
        imports.add("cn.sparke.core.common.controller.BaseController");
        imports.add("org.springframework.stereotype.Controller");
        imports.add("org.springframework.web.bind.annotation.RequestMapping");
        imports.add("org.springframework.web.bind.annotation.ResponseBody");
        imports.add("org.springframework.ui.Model");
        imports.add("org.springframework.web.bind.annotation.PathVariable");
        imports.add("cn.sparke.modules." + contextConfig.getBizEnName() + ".service." + contextConfig.getBizEnBigName() + "Service");
        imports.add("cn.sparke.modules." + contextConfig.getBizEnName() + ".entity." + contextConfig.getBizEnBigName() + "Entity");
        this.imports = imports;
        this.packageName = "cn.sparke.modules." + contextConfig.getBizEnName() + ".controller";
        this.controllerPathTemplate = "\\src\\main\\java\\cn\\sparke\\modules\\" + contextConfig.getBizEnName() + "\\controller\\{}Controller.java";
        this.serviceName = ToolUtil.firstLetterToLower(contextConfig.getBizEnName()) + "Service";
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getControllerPathTemplate() {
        return controllerPathTemplate;
    }

    public void setControllerPathTemplate(String controllerPathTemplate) {
        this.controllerPathTemplate = controllerPathTemplate;
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }
}
