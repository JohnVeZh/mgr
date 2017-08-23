package cn.sparke.modules.questionbank.question_option.controller;

import cn.sparke.modules.questionbank.question_item.entity.Question_itemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.questionbank.question_option.service.Question_optionService;
import cn.sparke.modules.questionbank.question_option.entity.Question_optionEntity;

import javax.websocket.server.PathParam;

/**
 * 题目选项控制器
 *
 * @author spark
 * @Date 2017-07-19 11:23:46
 */
@Controller
@RequestMapping("/question_option")
public class Question_optionController extends BaseController {

    private String PREFIX = "/questionbank/question_option/";

     @Autowired
     private Question_optionService question_optionService;

    /**
     * 跳转到题目选项首页
     */
    @RequestMapping("")
    public String index(String itemId, Model model) {

        model.addAttribute("itemId",itemId);
        return PREFIX + "question_option.html";
    }

    @RequestMapping("/index2")
    public String index2(String id ,String name,Model model) {
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return PREFIX + "question_option.html";
    }

    /**
     * 跳转到添加题目选项
     */
    @RequestMapping("/question_option_add")
    public String question_optionAdd(String itemId ,Model model) {
        model.addAttribute("itemId",itemId);
        return PREFIX + "question_option_add.html";
    }

    /**
     * 跳转到修改题目选项
     */
    @RequestMapping("/question_option_update")
    public String question_optionUpdate(@PathParam("question_optionId") String question_optionId, Model model) {
       Question_optionEntity bean = question_optionService.getById(question_optionId);
        model.addAttribute("question_option",bean);
        return PREFIX + "question_option_edit.html";
    }

    /**
     * 获取题目选项列表
     */
   @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String itemId,Question_optionEntity entity) {
        entity.setItemId(itemId);
        return question_optionService.findList(entity);
    }
    /**
     * 新增题目选项
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated Question_optionEntity entity) {
        question_optionService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除题目选项
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        question_optionService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改题目选项
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated Question_optionEntity entity) {
        question_optionService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 题目选项详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return question_optionService.getById(id);
    }
}
