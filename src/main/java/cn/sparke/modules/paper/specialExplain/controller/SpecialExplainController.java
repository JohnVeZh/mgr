package cn.sparke.modules.paper.specialExplain.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.specialExplain.entity.SpecialExplainEntity;
import cn.sparke.modules.paper.specialExplain.service.SpecialExplainService;
import cn.sparke.modules.paper.specialExplain.wrapper.SpecialExplainWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 专项讲解控制器
 *
 * @author spark
 * @Date 2017-07-22 11:02:09
 */
@Controller
@RequestMapping("/paper/specialExplain")
public class SpecialExplainController extends BaseController {

    private String PREFIX = "/paper/specialExplain/";

     @Autowired
     private SpecialExplainService specialExplainService;

    /**
     * 跳转到专项讲解首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "specialExplain.html";
    }

    /**
     * 跳转到添加专项讲解
     */
    @RequestMapping("/specialExplain_add")
    public String specialExplainAdd() {
        return PREFIX + "specialExplain_add.html";
    }

    /**
     * 跳转到修改专项讲解
     */
    @RequestMapping("/specialExplain_update/{specialExplainId}")
    public String specialExplainUpdate(@PathVariable String specialExplainId, Model model) {
       SpecialExplainEntity bean = specialExplainService.getById(specialExplainId);
        model.addAttribute("specialExplain",bean);
        return PREFIX + "specialExplain_edit.html";
    }

    /**
     * 获取专项讲解列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(SpecialExplainEntity entity) {
        Page page = specialExplainService.findList(entity);
        return new PageInfo<>((List<SpecialExplainEntity>) new SpecialExplainWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增专项讲解
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated SpecialExplainEntity entity) {
        specialExplainService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除专项讲解
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String specialExplainId) {
        specialExplainService.deleteById(specialExplainId);
        return SUCCESS_TIP;
    }


    /**
     * 修改专项讲解
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated SpecialExplainEntity entity) {
        specialExplainService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 专项讲解详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return specialExplainService.getById(id);
    }
}
