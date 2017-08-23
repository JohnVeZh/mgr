package cn.sparke.modules.community.userCommentReply.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.community.comment.entity.vo.UserCommentVoEntity;
import cn.sparke.modules.community.userCommentReply.entity.UserCommentReplyEntity;
import cn.sparke.modules.community.userCommentReply.service.UserCommentReplyService;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 用户评论回复控制器
 *
 * @author spark
 * @Date 2017-07-22 11:27:00
 */
@Controller
@RequestMapping("/community/activity/comment/reply")
public class UserCommentReplyController extends BaseController {

    private String PREFIX = "/community/userCommentReply/";

    @Autowired
    private UserCommentReplyService userCommentReplyService;

    /**
     * 跳转到用户评论回复首页
     */
    @RequestMapping("")
    public String index(@RequestParam("activityCommentId") String activityCommentId, Model model) {
        model.addAttribute("activityCommentId", activityCommentId);
        return PREFIX + "userCommentReply.html";
    }

    /**
     * 跳转到添加用户评论回复
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String userCommentReplyAdd() {
        return PREFIX + "userCommentReply_add.html";
    }

    /**
     * 跳转到修改用户评论回复
     */
    @RequestMapping("/update/{userCommentReplyId}")
    public String userCommentReplyUpdate(@PathVariable String userCommentReplyId, Model model) {
        UserCommentReplyEntity bean = userCommentReplyService.getById(userCommentReplyId);
        model.addAttribute("userCommentReply", bean);
        return PREFIX + "userCommentReply_edit.html";
    }

    /**
     * 获取用户评论回复列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam("activityCommentId") String activityCommentId, UserCommentReplyEntity entity) {
        entity.setCommentId(activityCommentId);
        Page page = userCommentReplyService.findList(entity);
        return new PageInfo<>((List<UserCommentReplyEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增用户评论回复
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@Validated UserCommentReplyEntity entity) {
        userCommentReplyService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除用户评论回复
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String userCommentReplyId) {
        userCommentReplyService.deleteById(userCommentReplyId);
        return SUCCESS_TIP;
    }


    /**
     * 修改用户评论回复
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated UserCommentReplyEntity entity) {
        userCommentReplyService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 用户评论回复详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return userCommentReplyService.getById(id);
    }
}
