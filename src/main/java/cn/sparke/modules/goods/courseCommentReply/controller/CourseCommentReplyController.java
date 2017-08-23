package cn.sparke.modules.goods.courseCommentReply.controller;

import cn.sparke.modules.goods.CourseComment.entity.CourseCommentEntity;
import cn.sparke.modules.goods.CourseComment.mapper.CourseCommentMapper;
import cn.sparke.modules.goods.CourseComment.service.CourseCommentService;
import cn.sparke.modules.goods.courseCommentReply.entity.CourseCommentReplyEntity;
import cn.sparke.modules.goods.courseCommentReply.service.CourseCommentReplyService;
import cn.sparke.modules.goods.networkCourse.entity.NetworkCourseEntity;
import cn.sparke.modules.goods.networkCourse.service.NetworkCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 回复控制器
 *
 * @author spark
 * @Date 2017-08-18 10:16:26
 */
@Controller
@RequestMapping("/goods/courseCommentReply")
public class CourseCommentReplyController extends BaseController {

    private String PREFIX = "/goods/courseCommentReply/";

    @Autowired
    private CourseCommentReplyService courseCommentReplyService;
    @Autowired
    private CourseCommentService courseCommentService;
    @Autowired
    private CourseCommentMapper courseCommentMapper;

    /**
     * 跳转到回复首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "courseCommentReply.html";
    }

    /**
     * 跳转到添加回复
     */
    @RequestMapping("/courseCommentReply_add")
    public String courseCommentReplyAdd() {
        return PREFIX + "courseCommentReply_add.html";
    }

    /**
     * 跳转到添加回复
     */
    @RequestMapping("/courseCommentReply_add/{CourseCommentId}")
    public String networkCourseUpdate(@PathVariable String CourseCommentId, Model model) {
        CourseCommentEntity bean = courseCommentMapper.getEntityById(CourseCommentId);
        model.addAttribute("CourseComment", bean);
        return PREFIX + "courseCommentReply_add.html";
    }

    /**
     * 跳转到修改回复
     */
    @RequestMapping("/courseCommentReply_update/{courseCommentId}")
    public String courseCommentReplyUpdate(@PathVariable String courseCommentId, Model model) {
        CourseCommentReplyEntity bean = courseCommentReplyService.getByCommentId(courseCommentId);
        CourseCommentEntity courseComment = courseCommentMapper.getEntityById(courseCommentId);
        model.addAttribute("CourseComment", courseComment);
        model.addAttribute("courseCommentReply", bean);
        return PREFIX + "courseCommentReply_edit.html";
    }

    /**
     * 获取评论回复
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(CourseCommentReplyEntity entity) {
        return courseCommentReplyService.findList(entity);
    }

    /**
     * 新增回复
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated CourseCommentReplyEntity entity) {
        courseCommentReplyService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除回复
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String courseCommentReplyId) {
        courseCommentReplyService.deleteById(courseCommentReplyId);
        return SUCCESS_TIP;
    }


    /**
     * 修改回复
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated CourseCommentReplyEntity entity) {
        courseCommentReplyService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 回复详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return courseCommentReplyService.getById(id);
    }
}
