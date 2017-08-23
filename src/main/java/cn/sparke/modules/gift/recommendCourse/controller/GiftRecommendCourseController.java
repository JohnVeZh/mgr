package cn.sparke.modules.gift.recommendCourse.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.gift.recommendCourse.entity.GiftRecommendCourseEntity;
import cn.sparke.modules.gift.recommendCourse.service.GiftRecommendCourseService;
import cn.sparke.modules.gift.recommendCourse.wrapper.RecommendCourseWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 课程推荐控制器
 *
 * @author spark
 * @Date 2017-08-22 09:48:17
 */
@Controller
@RequestMapping("/gift/recommendCourse")
public class GiftRecommendCourseController extends BaseController {

    private String PREFIX = "/giftRecommendCourse/";

     @Autowired
     private GiftRecommendCourseService giftRecommendCourseService;

    /**
     * 跳转到课程推荐首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "giftRecommendCourse.html";
    }

    /**
     * 跳转到添加课程推荐
     */
    @RequestMapping("/giftRecommendCourse_add")
    public String giftRecommendCourseAdd() {
        return PREFIX + "giftRecommendCourse_add.html";
    }

    /**
     * 跳转到修改课程推荐
     */
    @RequestMapping("/giftRecommendCourse_update/{giftRecommendCourseId}")
    public String giftRecommendCourseUpdate(@PathVariable String giftRecommendCourseId, Model model) {
       GiftRecommendCourseEntity bean = giftRecommendCourseService.getById(giftRecommendCourseId);
        model.addAttribute("giftRecommendCourse",bean);
        return PREFIX + "giftRecommendCourse_edit.html";
    }

    /**
     * 获取课程推荐列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(GiftRecommendCourseEntity entity) {
        Page page = giftRecommendCourseService.findList(entity);
        return new PageInfo<>((List<GiftRecommendCourseEntity>) new RecommendCourseWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增课程推荐
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated GiftRecommendCourseEntity entity) {
        giftRecommendCourseService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除课程推荐
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String giftRecommendCourseId) {
        giftRecommendCourseService.deleteById(giftRecommendCourseId);
        return SUCCESS_TIP;
    }


    /**
     * 修改课程推荐
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated GiftRecommendCourseEntity entity) {
        giftRecommendCourseService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 课程推荐详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return giftRecommendCourseService.getById(id);
    }
}
