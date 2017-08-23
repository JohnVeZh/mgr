package cn.sparke.modules.demo.controller;

import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 例子控制器
 *
 * @author spark
 * @Date 2017-07-18 14:55:09
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

    private String PREFIX = "/demo/";

    /**
     * 跳转到例子首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "demo.html";
    }

    /**
     * 跳转到添加例子
     */
    @RequestMapping("/demo_add")
    public String demoAdd() {
        return PREFIX + "demo_add.html";
    }

    /**
     * 跳转到修改例子
     */
    @RequestMapping("/demo_update/{demoId}")
    public String demoUpdate(@PathVariable Integer demoId, Model model) {
        return PREFIX + "demo_edit.html";
    }

    /**
     * 获取例子列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增例子
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除例子
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改例子
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 例子详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
