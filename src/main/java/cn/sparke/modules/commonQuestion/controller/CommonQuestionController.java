package cn.sparke.modules.commonQuestion.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.commonQuestion.service.CommonQuestionService;
import cn.sparke.modules.commonQuestion.entity.CommonQuestionEntity;

/**
 * 常见问题控制器
 *
 * @author spark
 * @Date 2017-07-31 09:52:24
 */
@Controller
@RequestMapping("/commonQuestion")
public class CommonQuestionController extends BaseController {

    private String PREFIX = "/commonQuestion/";

     @Autowired
     private CommonQuestionService commonQuestionService;

    /**
     * 跳转到常见问题首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "commonQuestion.html";
    }

    /**
     * 跳转到添加常见问题
     */
    @RequestMapping("/commonQuestion_add")
    public String commonQuestionAdd() {
        return PREFIX + "commonQuestion_add.html";
    }

    /**
     * 跳转到修改常见问题
     */
    @RequestMapping("/commonQuestion_update/{commonQuestionId}")
    public String commonQuestionUpdate(@PathVariable String commonQuestionId, Model model) {
       CommonQuestionEntity bean = commonQuestionService.getById(commonQuestionId);
        model.addAttribute("commonQuestion",bean);
        return PREFIX + "commonQuestion_edit.html";
    }

    /**
     * 获取常见问题列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(CommonQuestionEntity entity) {
        Page page = commonQuestionService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增常见问题
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated CommonQuestionEntity entity) {
        commonQuestionService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除常见问题
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String commonQuestionId) {
        commonQuestionService.deleteById(commonQuestionId);
        return SUCCESS_TIP;
    }


    /**
     * 修改常见问题
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated CommonQuestionEntity entity) {
        commonQuestionService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 常见问题详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return commonQuestionService.getById(id);
    }
}
