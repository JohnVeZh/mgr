package cn.sparke.modules.goods.CourseComment.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.goods.CourseComment.entity.CourseCommentEntity;
import cn.sparke.modules.goods.CourseComment.service.CourseCommentService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 网课评论控制器
 *
 * @author spark
 * @Date 2017-08-02 13:35:13
 */
@Controller
@RequestMapping("/goods/CourseComment")
public class CourseCommentController extends BaseController {

    private String PREFIX = "/goods/CourseComment/";

     @Autowired
     private CourseCommentService courseCommentService;

    /**
     * 跳转到网课评论首页
     */
    @RequestMapping("{networkCourseId}")
    public String index(@PathVariable String networkCourseId, Model model) {
        model.addAttribute("networkCourseId",networkCourseId);
        return PREFIX + "CourseComment.html";
    }


    /**
     * 跳转到修改网课评论
     */
    @RequestMapping("/CourseComment_update/{CourseCommentId}")
    public String CourseCommentUpdate(@PathVariable String CourseCommentId, Model model) {
       CourseCommentEntity bean = courseCommentService.getById(CourseCommentId);
        model.addAttribute("CourseComment",bean);
        return PREFIX + "CourseComment_edit.html";
    }

    /**
     * 获取网课评论列表
     */
    @RequestMapping(value = "/list/{networkCourseId}")
    @ResponseBody
    public Object list(@PathVariable String networkCourseId,CourseCommentEntity comment) {
        if(!StringUtils.isEmpty(networkCourseId)){
            comment.setContentId(networkCourseId);
        }
        Page<CourseCommentEntity> list = courseCommentService.findList(comment);
        return new PageInfo<>(list);
    }


    /**
     * 删除网课评论
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String CourseCommentId) {
        courseCommentService.deleteById(CourseCommentId);
        return SUCCESS_TIP;
    }


    /**
     * 修改网课评论点赞数
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated CourseCommentEntity entity) {
        courseCommentService.updatePraiseNum(entity);
        return super.SUCCESS_TIP;
    }

}
