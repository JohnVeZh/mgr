package cn.sparke.modules.paper.paperCorrect.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.paper.paperCorrect.entity.PaperCorrectEntity;
import cn.sparke.modules.paper.paperCorrect.entity.QueryBean;
import cn.sparke.modules.paper.paperCorrect.entity.RuleBean;
import cn.sparke.modules.paper.paperCorrect.service.PaperCorrectService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * 大礼包试卷批改控制器
 *
 * @author spark
 * @Date 2017-08-21 09:39:15
 */
@Controller
@RequestMapping("/paper/paperCorrect")
public class PaperCorrectController extends BaseController {

    private String PREFIX = "/paper/paperCorrect/";

     @Autowired
     private PaperCorrectService paperCorrectService;

    /**
     * 跳转到大礼包试卷批改首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paperCorrect.html";
    }

    /**
     * 跳转到大礼包试卷批改详情
     */
    @RequestMapping("/paperCorrect_detail/{id}")
    public String paperCorrectDetail(@PathVariable String id,Model model) {
        model.addAttribute("answerId",id);
        return PREFIX + "paperCorrect_detail.html";
    }

    /**
     * 跳转到修改大礼包试卷批改
     */
    @RequestMapping("/paperCorrect_update/{id}")
    public String paperCorrectUpdate(@PathVariable String id, Model model) {
        model.addAttribute("answerId",id);
        return PREFIX + "paperCorrect_edit.html";
    }

    /**
     * 获取大礼包试卷批改列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PaperCorrectEntity entity) {
        Page<PaperCorrectEntity> list = paperCorrectService.findList(entity);
        return new PageInfo<>(list);
    }

    /**
     * 新增大礼包试卷批改
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated PaperCorrectEntity entity) {
        paperCorrectService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除大礼包试卷批改
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String paperCorrectId) {
        paperCorrectService.deleteById(paperCorrectId);
        return SUCCESS_TIP;
    }


    /**
     * 修改大礼包试卷批改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PaperCorrectEntity entity) {
        paperCorrectService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 大礼包试卷批改详情
     */
    @RequestMapping(value = "/detail/{answerId}")
    @ResponseBody
    public Object detail(@PathVariable String answerId) {
        PaperCorrectEntity correctEntity = paperCorrectService.getById(answerId);
        return correctEntity;
    }

    /**
     * 根据学段和作业类型获取规则
     */
    @RequestMapping(value = "/rules")
    @ResponseBody
    public List<RuleBean> getRules(QueryBean bean) {
        List<RuleBean> rules = paperCorrectService.getRules(bean.getSectionCode(), bean.getQuestionType());
        return rules;
    }


}
