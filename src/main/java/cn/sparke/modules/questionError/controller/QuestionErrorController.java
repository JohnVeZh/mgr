package cn.sparke.modules.questionError.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.questionError.service.QuestionErrorService;
import cn.sparke.modules.questionError.entity.QuestionErrorEntity;

/**
 * 题目报错控制器
 *
 * @author spark
 * @Date 2017-07-31 10:58:47
 */
@Controller
@RequestMapping("/questionError")
public class QuestionErrorController extends BaseController {

    private String PREFIX = "/questionError/";

     @Autowired
     private QuestionErrorService questionErrorService;

    /**
     * 跳转到题目报错首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "questionError.html";
    }

    /**
     * 跳转到添加题目报错
     */
    @RequestMapping("/questionError_add")
    public String questionErrorAdd() {
        return PREFIX + "questionError_add.html";
    }

    /**
     * 跳转到修改题目报错
     */
    @RequestMapping("/questionError_update/{questionErrorId}")
    public String questionErrorUpdate(@PathVariable String questionErrorId, Model model) {
       QuestionErrorEntity bean = questionErrorService.getById(questionErrorId);
        model.addAttribute("questionError",bean);
        return PREFIX + "questionError_edit.html";
    }

    /**
     * 获取题目报错列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(QuestionErrorEntity entity) {
        return questionErrorService.findList(entity);
    }

    /**
     * 新增题目报错
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated QuestionErrorEntity entity) {
        questionErrorService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除题目报错
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String questionErrorId) {
        questionErrorService.deleteById(questionErrorId);
        return SUCCESS_TIP;
    }


    /**
     * 修改题目报错
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(QuestionErrorEntity entity) {
        questionErrorService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 题目报错详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return questionErrorService.getById(id);
    }
}
