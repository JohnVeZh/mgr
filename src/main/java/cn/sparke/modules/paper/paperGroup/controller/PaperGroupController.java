package cn.sparke.modules.paper.paperGroup.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.paperGroup.entity.PaperGroupEntity;
import cn.sparke.modules.paper.paperGroup.service.PaperGroupService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 试卷组控制器
 *
 * @author spark
 * @Date 2017-07-27 16:21:39
 */
@Controller
@RequestMapping("/paper/paperGroup")
public class PaperGroupController extends BaseController {

    private String PREFIX = "/paper/paperGroup/";

     @Autowired
     private PaperGroupService paperGroupService;

    /**
     * 跳转到试卷组首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paperGroup.html";
    }

    /**
     * 跳转到添加试卷组
     */
    @RequestMapping("/paperGroup_add")
    public String paperGroupAdd() {
        return PREFIX + "paperGroup_add.html";
    }

    /**
     * 跳转到修改试卷组
     */
    @RequestMapping("/paperGroup_update/{paperGroupId}")
    public String paperGroupUpdate(@PathVariable String paperGroupId, Model model) {
       PaperGroupEntity bean = paperGroupService.getById(paperGroupId);
        model.addAttribute("paperGroup",bean);
        return PREFIX + "paperGroup_edit.html";
    }

    /**
     * 获取试卷组列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PaperGroupEntity entity) {
        Page page = paperGroupService.findList(entity);
        return new PageInfo<>(page);
    }

    /**
     * 新增试卷组
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated PaperGroupEntity entity) {
        paperGroupService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除试卷组
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String paperGroupId) {
        paperGroupService.deleteById(paperGroupId);
        return SUCCESS_TIP;
    }


    /**
     * 修改试卷组
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PaperGroupEntity entity) {
        paperGroupService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 试卷组详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return paperGroupService.getById(id);
    }
}
