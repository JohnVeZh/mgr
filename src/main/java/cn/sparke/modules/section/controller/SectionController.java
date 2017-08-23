package cn.sparke.modules.section.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.section.service.SectionService;
import cn.sparke.modules.section.entity.SectionEntity;

/**
 * 学段控制器
 *
 * @author spark
 * @Date 2017-07-19 16:33:08
 */
@Controller
@RequestMapping("/section")
public class SectionController extends BaseController {

    private String PREFIX = "/section/";

     @Autowired
     private SectionService sectionService;

    /**
     * 跳转到学段首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "section.html";
    }

    /**
     * 跳转到添加学段
     */
    @RequestMapping("/section_add")
    public String sectionAdd() {
        return PREFIX + "section_add.html";
    }

    /**
     * 跳转到修改学段
     */
    @RequestMapping("/section_update/{sectionId}")
    public String sectionUpdate(@PathVariable String sectionId, Model model) {
       SectionEntity bean = sectionService.getById(sectionId);
        model.addAttribute(bean);
        return PREFIX + "section_edit.html";
    }

    /**
     * 获取学段列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(SectionEntity entity) {
        return sectionService.findList(entity);
    }

    /**
     * 新增学段
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated SectionEntity entity) {
        sectionService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除学段
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        sectionService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改学段
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated SectionEntity entity) {
        sectionService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 学段详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return sectionService.getById(id);
    }
}
