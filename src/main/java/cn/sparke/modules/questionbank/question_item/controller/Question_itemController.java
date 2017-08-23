package cn.sparke.modules.questionbank.question_item.controller;

import cn.sparke.modules.paper.paper.service.PaperService;
import cn.sparke.modules.paper.paperQuestion.entity.PaperQuestionEntity;
import cn.sparke.modules.paper.paperQuestion.mapper.PaperQuestionMapper;
import cn.sparke.modules.questionbank.question_listening.entity.Question_listeningEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.questionbank.question_item.service.Question_itemService;
import cn.sparke.modules.questionbank.question_item.entity.Question_itemEntity;

/**
 * 题目小题表控制器
 *
 * @author spark
 * @Date 2017-07-19 11:02:14
 */
@Controller
@RequestMapping("/question_item")
public class Question_itemController extends BaseController {

    private String PREFIX = "/questionbank/question_item/";

     @Autowired
     private Question_itemService question_itemService;
     @Autowired
     private PaperQuestionMapper paperQuestionMapper;
     @Autowired
     private PaperService paperService;

    /**
     * 跳转到题目小题表首页
     */
    @RequestMapping("")
    public String index(String questionId,Model model) {

        model.addAttribute("questionId",questionId);
        return PREFIX + "question_item.html";
    }

    /**
     * 跳转到添加题目小题表
     */
    @RequestMapping("/question_item_add")
    public String question_itemAdd(String questionId,Model model) {
        model.addAttribute("questionId",questionId);
        return PREFIX + "question_item_add.html";
    }

    /**
     * 跳转到修改题目小题表
     */
    @RequestMapping("/question_item_update/{question_itemId}")
    public String question_itemUpdate(@PathVariable String question_itemId, Model model) {
       Question_itemEntity bean = question_itemService.getById(question_itemId);
        model.addAttribute("question_item",bean);
        return PREFIX + "question_item_edit.html";
    }

    /**
     * 获取题目小题表列表
     */
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    public Object list(Question_itemEntity entity) {
//        return question_itemService.findList(entity);
//    }
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String questionId,Question_itemEntity entity) {
        entity.setQuestionId(questionId);
        return question_itemService.findList(entity);
    }

    /**
     * 新增题目小题表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated Question_itemEntity entity) {
        question_itemService.save(entity);
        //根据questionId获取paperId
        PaperQuestionEntity paperQuestionEntity = new PaperQuestionEntity();
        paperQuestionEntity.setQuestionId(entity.getQuestionId());
        paperQuestionEntity= paperQuestionMapper.get(paperQuestionEntity);
       String paperId = paperQuestionEntity.getPaperId();
        //调用更新题目的接口
        paperService.refSpeciaQuestionCountById(paperId);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除题目小题表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id,String questionId) {
        question_itemService.deleteById(id);
        //根据questionId获取paperId
        PaperQuestionEntity paperQuestionEntity = new PaperQuestionEntity();
        paperQuestionEntity.setQuestionId(questionId);
        paperQuestionEntity= paperQuestionMapper.get(paperQuestionEntity);
        String paperId = paperQuestionEntity.getPaperId();
        //调用更新题目的接口
        paperService.refSpeciaQuestionCountById(paperId);
        return SUCCESS_TIP;
    }


    /**
     * 修改题目小题表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated Question_itemEntity entity) {
        question_itemService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 题目小题表详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return question_itemService.getById(id);
    }
}
