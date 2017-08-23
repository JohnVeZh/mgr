package cn.sparke.modules.goods.teacher.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.goods.teacher.entity.TeacherEntity;
import cn.sparke.modules.goods.teacher.mapper.TeacherMapper;
import cn.sparke.modules.goods.teacher.service.TeacherService;
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
 * 教师控制器
 *
 * @author spark
 * @Date 2017-07-21 11:06:39
 */
@Controller
@RequestMapping("/goods/teacher")
public class TeacherController extends BaseController {

    private String PREFIX = "/goods/teacher/";

     @Autowired
     private TeacherService teacherService;

     @Autowired
     private TeacherMapper teacherMapper;

    /**
     * 跳转到教师首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "teacher.html";
    }

    /**
     * 跳转到添加教师
     */
    @RequestMapping("/teacher_add")
    public String teacherAdd() {
        return PREFIX + "teacher_add.html";
    }

    /**
     * 跳转到修改教师
     */
    @RequestMapping("/teacher_update/{teacherId}")
    public String teacherUpdate(@PathVariable String teacherId, Model model) {
       TeacherEntity bean = teacherService.getById(teacherId);
        model.addAttribute("teacher",bean);
        return PREFIX + "teacher_edit.html";
    }

    /**
     * 获取教师列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(TeacherEntity entity) {
        return new PageInfo<>(teacherService.findList(entity));
    }

    @RequestMapping(value = "/course/list")
    @ResponseBody
    public List<TeacherEntity> listForCourse() {
        return teacherMapper.quertTeachers();
    }

    /**
     * 新增教师
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated TeacherEntity entity) {
        teacherService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除教师
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String teacherId) {
        teacherService.deleteById(teacherId);
        return SUCCESS_TIP;
    }


    /**
     * 修改教师
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated TeacherEntity entity) {
        teacherService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 教师详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return teacherService.getById(id);
    }
}
