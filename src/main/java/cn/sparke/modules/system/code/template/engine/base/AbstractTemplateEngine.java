package cn.sparke.modules.system.code.template.engine.base;


import cn.sparke.modules.system.code.template.config.*;

/**
 * 模板生成父类
 *
 * @author spark
 * @date 2017-05-08 20:17
 */
public class AbstractTemplateEngine {

    protected ContextConfig contextConfig;                //全局配置
    protected ControllerConfig controllerConfig;          //控制器的配置
    protected PageConfig pageConfig;                      //页面的控制器
    protected MapperConfig mapperConfig;                        //Dao配置
    protected ServiceConfig serviceConfig;                //Service配置
    protected EntityConfig entityConfig;                    //实体配置
    private MappingConfig mappingConfig;                      //mapping配置

    public void initConfig() {
        if (this.contextConfig == null) {
            this.contextConfig = new ContextConfig();
        }
        if (this.controllerConfig == null) {
            this.controllerConfig = new ControllerConfig();
        }
        if (this.pageConfig == null) {
            this.pageConfig = new PageConfig();
        }
        if (this.mapperConfig == null) {
            this.mapperConfig = new MapperConfig();
        }
        if (this.serviceConfig == null) {
            this.serviceConfig = new ServiceConfig();
        }
        if (this.entityConfig == null) {
            this.entityConfig = new EntityConfig();
        }
        if (this.mappingConfig == null) {
            this.mappingConfig = new MappingConfig();
        }

        this.controllerConfig.setContextConfig(this.contextConfig);
        this.controllerConfig.init();

        this.serviceConfig.setContextConfig(this.contextConfig);
        this.serviceConfig.init();

        this.mapperConfig.setContextConfig(this.contextConfig);
        this.mapperConfig.init();

        this.pageConfig.setContextConfig(this.contextConfig);
        this.pageConfig.init();

        this.entityConfig.setContextConfig(this.contextConfig);
        this.entityConfig.init();


        this.mappingConfig.setContextConfig(this.contextConfig);
        this.mappingConfig.init();
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }

    public ControllerConfig getControllerConfig() {
        return controllerConfig;
    }

    public void setControllerConfig(ControllerConfig controllerConfig) {
        this.controllerConfig = controllerConfig;
    }

    public PageConfig getPageConfig() {
        return pageConfig;
    }

    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    public MapperConfig getMapperConfig() {
        return mapperConfig;
    }

    public void setMapperConfig(MapperConfig mapperConfig) {
        this.mapperConfig = mapperConfig;
    }

    public ServiceConfig getServiceConfig() {
        return serviceConfig;
    }

    public void setServiceConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    public EntityConfig getEntityConfig() {
        return entityConfig;
    }

    public void setEntityConfig(EntityConfig entityConfig) {
        this.entityConfig = entityConfig;
    }

    public MappingConfig getMappingConfig() {
        return mappingConfig;
    }

    public void setMappingConfig(MappingConfig mappingConfig) {
        this.mappingConfig = mappingConfig;
    }
}

