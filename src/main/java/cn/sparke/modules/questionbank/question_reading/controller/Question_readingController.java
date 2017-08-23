package cn.sparke.modules.questionbank.question_reading.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_option.entity.Question_optionEntity;
import cn.sparke.modules.questionbank.question_reading.entity.QuestionReadingEntity;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.questionbank.question_reading.service.Question_readingService;
import cn.sparke.modules.questionbank.question_reading.entity.Question_readingEntity;

import java.util.Map;

/**
 * 阅读题控制器
 *
 * @author spark
 * @Date 2017-07-19 10:55:02
 */
@Controller
@RequestMapping("/question_reading")
public class Question_readingController extends BaseController {

    private String PREFIX = "/questionbank/question_reading/";

     @Autowired
     private Question_readingService question_readingService;
    @Autowired
    private PaperStructureMapper paperStructureMapper;
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 跳转到阅读题首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "question_reading.html";
    }

    @RequestMapping("/index2")
    public String index2(String id ,String name,Model model) {
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return PREFIX + "question_reading.html";
    }

    /**
     * 跳转到添加阅读题
     */
    @RequestMapping("/question_reading_add")
    public String question_readingAdd(String structureId ,Model model) {
        //通过结构id获取试卷id
        PaperStructureEntity paperStructureEntity=paperStructureMapper.getById(structureId);
        String paperId=paperStructureEntity.getPaperId();
        //通过实践i
        Map<String,Object> datamap=questionMapper.getSectionCodeByPaperId(paperId);
        model.addAttribute("structureId",structureId);
        model.addAttribute("paperId",paperId);
        model.addAttribute("sectionCode",datamap.get("sectionCode"));

        return PREFIX + "question_reading_add.html";
    }

    /**
     * 跳转到修改阅读题
     */
    @RequestMapping("/question_reading_update/{question_readingId}")
    public String question_readingUpdate(@PathVariable String question_readingId, Model model) {
       Question_readingEntity bean = question_readingService.getById(question_readingId);
        model.addAttribute("question_reading",bean);
        return PREFIX + "question_reading_edit.html";
    }

    /**
     * 获取阅读题列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String id,String name,Question_readingEntity entity) {
        entity.setId(id);
        return question_readingService.findList(entity);
    }

    /**
     * 获取列表-分页
     */
    @RequestMapping(value = "/queryList")
    @ResponseBody
    public Object queryList(QuestionReadingEntity entity) {
        Page page = question_readingService.queryList(entity);
        return new PageInfo<>(page);
    }

    /**
     * 新增阅读题
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated Question_readingEntity entity) {
        question_readingService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除阅读题
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        question_readingService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改阅读题
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated Question_readingEntity entity) {
        question_readingService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 阅读题详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return question_readingService.getById(id);
    }
}
