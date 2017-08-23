package cn.sparke.modules.goods.catalog.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.goods.catalog.entity.CatalogEntity;
import cn.sparke.modules.goods.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 课程目录控制器
 *
 * @author spark
 * @Date 2017-07-24 18:29:41
 */
@Controller
@RequestMapping("/goods/catalog")
public class CatalogController extends BaseController {

    private String PREFIX = "/goods/catalog/";

     @Autowired
     private CatalogService catalogService;

    /**
     * 跳转到课程目录首页
     */
    @RequestMapping("{networkCourseId}")
    public String index(@PathVariable String networkCourseId, Model model) {
        model.addAttribute("networkCourseId",networkCourseId);
        return PREFIX + "catalog.html";
    }

    /**
     * 跳转到添加课程目录
     */
    @RequestMapping("/catalog_add/{networkCourseId}")
    public String catalogAdd(@PathVariable String networkCourseId, Model model) {
        model.addAttribute("networkCourseId",networkCourseId);
        return PREFIX + "catalog_add.html";
    }

    /**
     * 跳转到修改课程目录
     */
    @RequestMapping("/catalog_update/{catalogId}")
    public String catalogUpdate(@PathVariable String catalogId, Model model) {
       CatalogEntity bean = catalogService.getById(catalogId);
        model.addAttribute("catalog",bean);
        return PREFIX + "catalog_edit.html";
    }

    /**
     * 获取课程目录列表
     */
    @RequestMapping(value = "/list/{networkCourseId}")
    @ResponseBody
    public Object list(@PathVariable String networkCourseId,CatalogEntity entity) {
        if(!StringUtils.isEmpty(networkCourseId)){
            entity.setNetworkCourseId(networkCourseId);
        }
        return new PageInfo<>(catalogService.findList(entity));
    }

    /**
     * 新增课程目录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated CatalogEntity entity) {
        catalogService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除课程目录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String catalogId) {
        catalogService.deleteById(catalogId);
        return SUCCESS_TIP;
    }


    /**
     * 修改课程目录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated CatalogEntity entity) {
        catalogService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 课程目录详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return catalogService.getById(id);
    }
}
