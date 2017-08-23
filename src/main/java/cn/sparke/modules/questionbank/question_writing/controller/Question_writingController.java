package cn.sparke.modules.questionbank.question_writing.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_translation.entity.Question_translationEntity;
import cn.sparke.modules.questionbank.question_writing.entity.QuestionWritingEntity;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.questionbank.question_writing.service.Question_writingService;
import cn.sparke.modules.questionbank.question_writing.entity.Question_writingEntity;

import java.util.Map;

/**
 * 写作题控制器
 *
 * @author spark
 * @Date 2017-07-19 10:59:38
 */
@Controller
@RequestMapping("/question_writing")
public class Question_writingController extends BaseController {

    private String PREFIX = "/questionbank/question_writing/";

     @Autowired
     private Question_writingService question_writingService;
    @Autowired
    private PaperStructureMapper paperStructureMapper;
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 跳转到写作题首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "question_writing.html";
    }

    @RequestMapping("/index2")
    public String index2(String id ,String name,Model model) {
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return PREFIX + "question_writing.html";
    }

    /**
     * 跳转到添加写作题
     */
    @RequestMapping("/question_writing_add")
    public String question_writingAdd(String structureId , Model model) {
        //通过结构id获取试卷id
        PaperStructureEntity paperStructureEntity=paperStructureMapper.getById(structureId);
        String paperId=paperStructureEntity.getPaperId();
        //通过实践i
        Map<String,Object> datamap=questionMapper.getSectionCodeByPaperId(paperId);
        model.addAttribute("structureId",structureId);
        model.addAttribute("paperId",paperId);
        model.addAttribute("sectionCode",datamap.get("sectionCode"));

        return PREFIX + "question_writing_add.html";
    }

    /**
     * 跳转到修改写作题
     */
    @RequestMapping("/question_writing_update/{question_writingId}")
    public String question_writingUpdate(@PathVariable String question_writingId, Model model) {
       Question_writingEntity bean = question_writingService.getById(question_writingId);
        model.addAttribute("question_writing",bean);
        return PREFIX + "question_writing_edit.html";
    }

    /**
     * 获取写作题列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String id,String name,Question_writingEntity entity) {
        entity.setId(id);
        return question_writingService.findList(entity);
    }

    /**
     * 获取列表-分页
     */
    @RequestMapping(value = "/queryList")
    @ResponseBody
    public Object queryList(QuestionWritingEntity entity) {
        Page page = question_writingService.queryList(entity);
        return new PageInfo<>(page);
    }

    /**
     * 新增写作题
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated Question_writingEntity entity) {
        question_writingService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除写作题
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        question_writingService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改写作题
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated Question_writingEntity entity) {
        question_writingService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 写作题详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return question_writingService.getById(id);
    }
}
