package cn.sparke.modules.paper.paperGroupRelation.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.modules.paper.paperGroupRelation.entity.PaperGroupRelationEntity;
import cn.sparke.modules.paper.paperGroupRelation.service.PaperGroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 试卷组关系控制器
 *
 * @author spark
 * @Date 2017-07-27 16:23:44
 */
@Controller
@RequestMapping("/paperGroupRelation")
public class PaperGroupRelationController extends BaseController {

    private String PREFIX = "/paperGroupRelation/";

     @Autowired
     private PaperGroupRelationService paperGroupRelationService;

    /**
     * 跳转到试卷组关系首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paperGroupRelation.html";
    }

    /**
     * 跳转到添加试卷组关系
     */
    @RequestMapping("/paperGroupRelation_add")
    public String paperGroupRelationAdd() {
        return PREFIX + "paperGroupRelation_add.html";
    }

    /**
     * 跳转到修改试卷组关系
     */
    @RequestMapping("/paperGroupRelation_update/{paperGroupRelationId}")
    public String paperGroupRelationUpdate(@PathVariable String paperGroupRelationId, Model model) {
       PaperGroupRelationEntity bean = paperGroupRelationService.getById(paperGroupRelationId);
        model.addAttribute("paperGroupRelation",bean);
        return PREFIX + "paperGroupRelation_edit.html";
    }

    /**
     * 获取试卷组关系列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PaperGroupRelationEntity entity) {
        return paperGroupRelationService.findList(entity);
    }

    /**
     * 新增试卷组关系
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated PaperGroupRelationEntity entity) {
        paperGroupRelationService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除试卷组关系
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String paperGroupRelationId) {
        paperGroupRelationService.deleteById(paperGroupRelationId);
        return SUCCESS_TIP;
    }


    /**
     * 修改试卷组关系
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated PaperGroupRelationEntity entity) {
        paperGroupRelationService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 试卷组关系详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return paperGroupRelationService.getById(id);
    }
}
