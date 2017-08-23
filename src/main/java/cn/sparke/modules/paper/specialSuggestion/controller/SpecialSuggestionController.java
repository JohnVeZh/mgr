package cn.sparke.modules.paper.specialSuggestion.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.specialSuggestion.entity.SpecialSuggestionEntity;
import cn.sparke.modules.paper.specialSuggestion.service.SpecialSuggestionService;
import cn.sparke.modules.paper.specialSuggestion.wrapper.SpecialSuggestionWrapper;
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
 * 分析建议控制器
 *
 * @author spark
 * @Date 2017-07-22 10:14:36
 */
@Controller
@RequestMapping("/paper/specialSuggestion")
public class SpecialSuggestionController extends BaseController {

    private String PREFIX = "/paper/specialSuggestion/";

     @Autowired
     private SpecialSuggestionService specialSuggestionService;

    /**
     * 跳转到分析建议首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "specialSuggestion.html";
    }

    /**
     * 跳转到添加分析建议
     */
    @RequestMapping("/specialSuggestion_add")
    public String specialSuggestionAdd() {
        return PREFIX + "specialSuggestion_add.html";
    }

    /**
     * 跳转到修改分析建议
     */
    @RequestMapping("/specialSuggestion_update/{specialSuggestionId}")
    public String specialSuggestionUpdate(@PathVariable String specialSuggestionId, Model model) {
       SpecialSuggestionEntity bean = specialSuggestionService.getById(specialSuggestionId);
        model.addAttribute("specialSuggestion",bean);
        return PREFIX + "specialSuggestion_edit.html";
    }

    /**
     * 获取分析建议列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(SpecialSuggestionEntity entity) {
        Page page = specialSuggestionService.findList(entity);
        return new PageInfo<>((List<SpecialSuggestionEntity>) new SpecialSuggestionWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增分析建议
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated SpecialSuggestionEntity entity) {
        specialSuggestionService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除分析建议
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String specialSuggestionId) {
        specialSuggestionService.deleteById(specialSuggestionId);
        return SUCCESS_TIP;
    }


    /**
     * 修改分析建议
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated SpecialSuggestionEntity entity) {
        specialSuggestionService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 分析建议详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return specialSuggestionService.getById(id);
    }
}
