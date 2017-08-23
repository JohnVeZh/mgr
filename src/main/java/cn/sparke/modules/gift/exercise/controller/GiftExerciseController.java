package cn.sparke.modules.gift.exercise.controller;

import cn.sparke.modules.gift.exercise.entity.GiftExerciseEntity;
import cn.sparke.modules.gift.exercise.service.GiftExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 课后作业控制器
 *
 * @author spark
 * @Date 2017-08-21 15:23:45
 */
@Controller
@RequestMapping("/gift/exercise")
public class GiftExerciseController extends BaseController {

    private String PREFIX = "/gift/";

     @Autowired
     private GiftExerciseService giftExerciseService;

    /**
     * 跳转到课后作业首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "giftExercise.html";
    }

    /**
     * 跳转到网课视频列表
     */
    @RequestMapping("/goNetworkCourseVideoList")
    public String goNetworkCourseVideoList() {
        return PREFIX + "paper/networkCourseVideo.html";
    }

    /**
     * 跳转到网课视频绑定
     */
    @RequestMapping("/goRecommendCourse")
    public String goRecommendCourse() {
        return PREFIX + "paper/recommendCourse.html";
    }

    /**
     * 跳转到添加课后作业
     */
    @RequestMapping("/giftExercise_add")
    public String giftExerciseAdd() {
        return PREFIX + "giftExercise_add.html";
    }

    /**
     * 跳转到修改课后作业
     */
    @RequestMapping("/giftExercise_update/{giftExerciseId}")
    public String giftExerciseUpdate(@PathVariable String giftExerciseId, Model model) {
       GiftExerciseEntity bean = giftExerciseService.getById(giftExerciseId);
        model.addAttribute("giftExercise",bean);
        return PREFIX + "giftExercise_edit.html";
    }

    /**
     * 获取课后作业列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(GiftExerciseEntity entity) {
        return giftExerciseService.findList(entity);
    }

    /**
     * 新增课后作业
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated GiftExerciseEntity entity) {
        giftExerciseService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除课后作业
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String giftExerciseId) {
        giftExerciseService.deleteById(giftExerciseId);
        return SUCCESS_TIP;
    }

    /**
     * 删除课后作业
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(GiftExerciseEntity entity) {
        giftExerciseService.delete(entity);
        return SUCCESS_TIP;
    }

    /**
     * 修改课后作业
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated GiftExerciseEntity entity) {
        giftExerciseService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 课后作业详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return giftExerciseService.getById(id);
    }
}
