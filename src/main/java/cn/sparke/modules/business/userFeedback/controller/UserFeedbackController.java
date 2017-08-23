package cn.sparke.modules.business.userFeedback.controller;

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
import cn.sparke.modules.business.userFeedback.service.UserFeedbackService;
import cn.sparke.modules.business.userFeedback.entity.UserFeedbackEntity;

/**
 * 用户反馈控制器
 *
 * @author spark
 * @Date 2017-07-25 14:43:51
 */
@Controller
@RequestMapping("/userFeedback")
public class UserFeedbackController extends BaseController {

    private String PREFIX = "/business/userFeedback/";

     @Autowired
     private UserFeedbackService userFeedbackService;

    /**
     * 跳转到用户反馈首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userFeedback.html";
    }

    /**
     * 跳转到添加用户反馈
     */
    @RequestMapping("/userFeedback_add")
    public String userFeedbackAdd() {
        return PREFIX + "userFeedback_add.html";
    }

    /**
     * 跳转到修改用户反馈
     */
    @RequestMapping("/userFeedback_update/{userFeedbackId}")
    public String userFeedbackUpdate(@PathVariable String userFeedbackId, Model model) {
       UserFeedbackEntity bean = userFeedbackService.getById(userFeedbackId);
        model.addAttribute("userFeedback",bean);
        return PREFIX + "userFeedback_edit.html";
    }

    /**
     * 获取用户反馈列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(UserFeedbackEntity entity) {
        Page page = userFeedbackService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增用户反馈
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated UserFeedbackEntity entity) {
        userFeedbackService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除用户反馈
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String userFeedbackId) {
        userFeedbackService.deleteById(userFeedbackId);
        return SUCCESS_TIP;
    }


    /**
     * 修改用户反馈
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated UserFeedbackEntity entity) {
        userFeedbackService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 用户反馈详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return userFeedbackService.getById(id);
    }
}
