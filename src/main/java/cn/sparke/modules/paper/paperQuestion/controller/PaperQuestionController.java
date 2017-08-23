package cn.sparke.modules.paper.paperQuestion.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.service.PaperQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 试卷题目关系表控制器
 *
 * @author spark
 * @Date 2017-07-25 19:42:26
 */
@Controller
@RequestMapping("/paper/paperQuestion")
public class PaperQuestionController extends BaseController {

    private String PREFIX = "/paper/paperQuestion/";

     @Autowired
     private PaperQuestionService paperQuestionService;

    /**
     * 跳转到试卷题目关系表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paperQuestion.html";
    }

    /**
     * 跳转到添加试卷题目关系表
     */
    @RequestMapping("/paperQuestion_add")
    public String paperQuestionAdd() {
        return PREFIX + "paperQuestion_add.html";
    }

    /**
     * 跳转到修改试卷题目关系表
     */
    @RequestMapping("/paperQuestion_update/{paperQuestionId}")
    public String paperQuestionUpdate(@PathVariable String paperQuestionId, Model model) {
       PaperQuestionEntity bean = paperQuestionService.getById(paperQuestionId);
        model.addAttribute("paperQuestion",bean);
        return PREFIX + "paperQuestion_edit.html";
    }

    /**
     * 获取试卷题目关系表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PaperQuestionEntity entity) {
        return paperQuestionService.findList(entity);
    }

    /**
     * 新增试卷题目关系表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated PaperQuestionEntity entity) {
        paperQuestionService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除试卷题目关系表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String paperQuestionid) {
        paperQuestionService.deleteById(paperQuestionid);
        return SUCCESS_TIP;
    }


    /**
     * 修改试卷题目关系表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PaperQuestionEntity entity) {
        paperQuestionService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 试卷题目关系表详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return paperQuestionService.getById(id);
    }
}
