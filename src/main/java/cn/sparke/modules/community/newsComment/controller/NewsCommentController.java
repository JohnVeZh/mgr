package cn.sparke.modules.community.newsComment.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 活动评论控制器
 *
 * @author spark
 * @Date 2017-07-21 10:18:26
 */
@Controller
@RequestMapping("/community/news/comment")
public class NewsCommentController extends BaseController {
    private String PREFIX = "/community/newsComment/";

    @Autowired
    private UserCommentService newsCommentService;

    /**
     * 跳转到资讯评论首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "newsComment.html";
    }

    /**
     * 跳转到添加资讯评论
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String newsCommentAdd() {
        return PREFIX + "newsComment_add.html";
    }

    /**
     * 跳转到修改资讯评论
     */
    @RequestMapping("/update/{newsCommentId}")
    public String newsCommentUpdate(@PathVariable String newsCommentId, Model model) {
        UserCommentVoEntity bean = newsCommentService.getVoById(newsCommentId);
        model.addAttribute("newsComment",bean);
        return PREFIX + "newsComment_edit.html";
    }

    /**
     * 获取资讯评论列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(UserCommentEntity entity) {
        entity.setContentType(2);
        Page page = newsCommentService.findVoList(entity);
        return new PageInfo<>((List<UserCommentVoEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增资讯评论
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(@Validated UserCommentEntity entity) {
        newsCommentService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除资讯评论
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String newsCommentId) {
        newsCommentService.deleteById(newsCommentId);
        return SUCCESS_TIP;
    }


    /**
     * 修改资讯评论
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated UserCommentEntity entity) {
        newsCommentService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 资讯评论详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return newsCommentService.getById(id);
    }
}
