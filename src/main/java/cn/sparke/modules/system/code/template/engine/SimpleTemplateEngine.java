package cn.sparke.modules.system.code.template.engine;


import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.system.code.template.engine.base.SparkTemplateEngine;

/**
 * 通用的模板生成引擎
 *
 * @author spark
 * @date 2017-05-09 20:32
 */
public class SimpleTemplateEngine extends SparkTemplateEngine {

    @Override
    protected void generatePageEditHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageEditPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("template/page_edit.html.btl", path);
        System.out.println("生成编辑页面成功!");
    }

    @Override
    protected void generatePageAddHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageAddPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("template/page_add.html.btl", path);
        System.out.println("生成添加页面成功!");
    }

    @Override
    protected void generatePageInfoJs() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageInfoJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("template/page_info.js.btl", path);
        System.out.println("生成页面详情js成功!");
    }

    @Override
    protected void generatePageJs() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("template/page.js.btl", path);
        System.out.println("生成页面js成功!");
    }

    @Override
    protected void generatePageHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPagePathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("template/page.html.btl", path);
        System.out.println("生成页面成功!");
    }

    @Override
    protected void generateController() {
        String controllerPath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getControllerConfig().getControllerPathTemplate(),
                ToolUtil.firstLetterToUpper(super.getContextConfig().getBizEnName()));
        generateFile("template/Controller.java.btl", controllerPath);
        System.out.println("生成控制器成功!");
    }

    @Override
    protected void generateMapper() {
        String daoPath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getMapperConfig().getDaoPathTemplate(),
                ToolUtil.firstLetterToUpper(super.getContextConfig().getBizEnName()));
        generateFile("template/Mapper.java.btl", daoPath);
        System.out.println("生成Mapper成功!");

    }

    @Override
    protected void generateService() {
        String servicePath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getServiceConfig().getServicePathTemplate(),
                ToolUtil.firstLetterToUpper(super.getContextConfig().getBizEnName()));
        generateFile("template/Service.java.btl", servicePath);
        System.out.println("生成Service成功!");
    }

    @Override
    protected void generateEntity() {
        String daoPath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getEntityConfig().getEntityPathTemplate(),
                ToolUtil.firstLetterToUpper(super.getContextConfig().getBizEnName()));
        generateFile("template/Entity.java.btl", daoPath);
        System.out.println("生成Entity成功!");
    }

    @Override
    protected void generateMapping() {
        String mappingPath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getMappingConfig().getXmlPathTemplate(),
                ToolUtil.firstLetterToUpper(super.getContextConfig().getBizEnName()));
        generateFile("template/Mapping.xml.btl", mappingPath);
        System.out.println("生成Mapping xml成功!");
    }
}
