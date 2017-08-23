package cn.sparke.modules.questionbank.question_listening.controller;

import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.paperStructure.entity.PaperStructureEntity;
import cn.sparke.modules.paper.paperStructure.mapper.PaperStructureMapper;
import cn.sparke.modules.questionbank.question.mapper.QuestionMapper;
import cn.sparke.modules.questionbank.question_listening.entity.QuestionListeningEntity;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.questionbank.question_listening.service.Question_listeningService;
import cn.sparke.modules.questionbank.question_listening.entity.Question_listeningEntity;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * 听力题控制器
 *
 * @author spark
 * @Date 2017-07-19 10:53:23
 */
@Controller
@RequestMapping("/question_listening")
public class Question_listeningController extends BaseController {

    private String PREFIX = "/questionbank/question_listening/";

     @Autowired
     private Question_listeningService question_listeningService;
     @Autowired
     private PaperStructureMapper paperStructureMapper;
     @Autowired
    private QuestionMapper questionMapper;

    /**
     * 跳转到听力题首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "question_listening.html";
    }

    /**
     * 跳转到听力题首页
     */
    @RequestMapping("/index2")
    public String index2(String id ,String name,Model model) {
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return PREFIX + "question_listening.html";
    }

    /**
     * 跳转到添加听力题
     */
    @RequestMapping("/question_listening_add")
    public String question_listeningAdd(String structureId ,Model model) {

        //通过结构id获取试卷id
        PaperStructureEntity paperStructureEntity=paperStructureMapper.getById(structureId);
        String paperId=paperStructureEntity.getPaperId();
        //通过实践i
        Map<String,Object> datamap=questionMapper.getSectionCodeByPaperId(paperId);
        model.addAttribute("structureId",structureId);
        model.addAttribute("paperId",paperId);
        model.addAttribute("sectionCode",datamap.get("sectionCode"));
        return PREFIX + "question_listening_add.html";
    }

    /**
     * 跳转到修改听力题
     */
    @RequestMapping("/question_listening_update/{question_listeningId}")
    public String question_listeningUpdate(@PathVariable String question_listeningId, Model model) {
       Question_listeningEntity bean = question_listeningService.getById(question_listeningId);
        model.addAttribute("question_listening",bean);
        return PREFIX + "question_listening_edit.html";
    }

    /**
     * 获取听力题列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String id,String name,Question_listeningEntity entity) {
        entity.setId(id);
        return question_listeningService.findList(entity);
    }

    /**
     * 获取列表-分页
     */
    @RequestMapping(value = "/queryList")
    @ResponseBody
    public Object queryList(QuestionListeningEntity entity) {
        Page page = question_listeningService.queryList(entity);
        return new PageInfo<>(page);
    }

    /**
     * 新增听力题
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated Question_listeningEntity entity) {
        question_listeningService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除听力题
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        question_listeningService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改听力题
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated Question_listeningEntity entity) {
        question_listeningService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 听力题详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return question_listeningService.getById(id);
    }
}
