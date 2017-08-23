package cn.sparke.modules.startPage.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.startPage.service.StartPageService;
import cn.sparke.modules.startPage.entity.StartPageEntity;

/**
 * 启动页控制器
 *
 * @author spark
 * @Date 2017-08-02 21:27:28
 */
@Controller
@RequestMapping("/startPage")
public class StartPageController extends BaseController {

    private String PREFIX = "/startPage/";

     @Autowired
     private StartPageService startPageService;

    /**
     * 跳转到启动页首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "startPage.html";
    }

    /**
     * 跳转到添加启动页
     */
    @RequestMapping("/startPage_add")
    public String startPageAdd() {
        return PREFIX + "startPage_add.html";
    }

    /**
     * 跳转到修改启动页
     */
    @RequestMapping("/startPage_update/{startPageId}")
    public String startPageUpdate(@PathVariable String startPageId, Model model) {
       StartPageEntity bean = startPageService.getById(startPageId);
        model.addAttribute("startPage",bean);
        return PREFIX + "startPage_edit.html";
    }

    /**
     * 获取启动页列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(StartPageEntity entity) {
        Page page = startPageService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增启动页
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated StartPageEntity entity) {
        startPageService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除启动页
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String startPageId) {
        startPageService.deleteById(startPageId);
        return SUCCESS_TIP;
    }


    /**
     * 修改启动页
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated StartPageEntity entity) {
        startPageService.update(entity);
        return super.SUCCESS_TIP;
    }
    /**
     * 修改展示隐藏
     */
    @RequestMapping(value = "/updateIsShow")
    @ResponseBody
    public Object updateIsShow(StartPageEntity entity) {
        startPageService.updateIsShow(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 启动页详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return startPageService.getById(id);
    }
}
