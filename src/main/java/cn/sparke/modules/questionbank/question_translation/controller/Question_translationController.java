package cn.sparke.modules.questionbank.question_translation.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_reading.entity.Question_readingEntity;
import cn.sparke.modules.questionbank.question_translation.entity.QuestionTranslationEntity;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.questionbank.question_translation.service.Question_translationService;
import cn.sparke.modules.questionbank.question_translation.entity.Question_translationEntity;

import java.util.Map;

/**
 * 翻译题控制器
 *
 * @author spark
 * @Date 2017-07-19 10:58:40
 */
@Controller
@RequestMapping("/question_translation")
public class Question_translationController extends BaseController {

    private String PREFIX = "/questionbank/question_translation/";

     @Autowired
     private Question_translationService question_translationService;
    @Autowired
    private PaperStructureMapper paperStructureMapper;
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 跳转到翻译题首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "question_translation.html";
    }

    @RequestMapping("/index2")
    public String index2(String id ,String name,Model model) {
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return PREFIX + "question_translation.html";
    }

    /**
     * 跳转到添加翻译题
     */
    @RequestMapping("/question_translation_add")
    public String question_translationAdd(String structureId ,Model model) {

        //通过结构id获取试卷id
        PaperStructureEntity paperStructureEntity=paperStructureMapper.getById(structureId);
        String paperId=paperStructureEntity.getPaperId();
        //通过实践i
        Map<String,Object> datamap=questionMapper.getSectionCodeByPaperId(paperId);
        model.addAttribute("structureId",structureId);
        model.addAttribute("paperId",paperId);
        model.addAttribute("sectionCode",datamap.get("sectionCode"));
        return PREFIX + "question_translation_add.html";
    }

    /**
     * 跳转到修改翻译题
     */
    @RequestMapping("/question_translation_update/{question_translationId}")
    public String question_translationUpdate(@PathVariable String question_translationId, Model model) {
       Question_translationEntity bean = question_translationService.getById(question_translationId);
        model.addAttribute("question_translation",bean);
        return PREFIX + "question_translation_edit.html";
    }

    /**
     * 获取翻译题列表
     */
   @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String id,String name,Question_translationEntity entity) {
        entity.setId(id);
        return question_translationService.findList(entity);
    }

    /**
     * 获取列表-分页
     */
    @RequestMapping(value = "/queryList")
    @ResponseBody
    public Object queryList(QuestionTranslationEntity entity) {
        Page page = question_translationService.queryList(entity);
        return new PageInfo<>(page);
    }

    /**
     * 新增翻译题
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated Question_translationEntity entity) {
        question_translationService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除翻译题
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        question_translationService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改翻译题
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated Question_translationEntity entity) {
        question_translationService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 翻译题详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return question_translationService.getById(id);
    }
}
