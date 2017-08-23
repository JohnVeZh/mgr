package cn.sparke.modules.community.activityComment.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.community.comment.entity.po.UserCommentEntity;
import cn.sparke.modules.community.comment.entity.vo.UserCommentVoEntity;
import cn.sparke.modules.community.comment.service.UserCommentService;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 活动评论控制器
 *
 * @author spark
 * @Date 2017-07-21 10:18:26
 */
@Controller
@RequestMapping("/community/activity/comment")
public class ActivityCommentController extends BaseController {
    private String PREFIX = "/community/activityComment/";

    @Autowired
    private UserCommentService activityCommentService;

    /**
     * 跳转到活动评论首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "activityComment.html";
    }


    /**
     * 跳转到修改活动评论
     */
    @RequestMapping("/update/{activityCommentId}")
    public String activityCommentUpdate(@PathVariable String activityCommentId, Model model) {
        UserCommentVoEntity bean = activityCommentService.getVoById(activityCommentId);
        model.addAttribute("activityComment", bean);
        return PREFIX + "activityComment_edit.html";
    }

    /**
     * 获取活动评论列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(UserCommentEntity entity) {
        entity.setContentType(3);
        Page page = activityCommentService.findVoList(entity);
        return new PageInfo<>((List<UserCommentVoEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }


    /**
     * 删除活动评论
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String activityCommentId) {
        activityCommentService.deleteById(activityCommentId);
        return SUCCESS_TIP;
    }


    /**
     * 修改活动评论
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated UserCommentEntity entity) {
        activityCommentService.update(entity);
        return super.SUCCESS_TIP;
    }

}
