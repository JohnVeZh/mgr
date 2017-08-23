package cn.sparke.modules.community.activity.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.community.activity.entity.ActivityEntity;
import cn.sparke.modules.community.activity.service.IActivityService;
import cn.sparke.modules.system.log.entity.OperationLog;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

/**
 * 社区活动控制器
 *
 * @author spark
 * @Date 2017-07-18 19:25:30
 */
@Controller
@RequestMapping("/community/activity")
public class ActivityController extends BaseController {

    private String PREFIX = "/community/activity/";

    @Autowired
    private IActivityService activityService;

    /**
     * 跳转到活动首页
     */
    @RequestMapping("")
    public String index(@RequestParam(value = "isSelect", defaultValue = "false") boolean isSelect, Model model) {
        model.addAttribute("isSelect", isSelect);
        return PREFIX + "activity.html";
    }

    /**
     * 跳转到添加活动
     */
    @RequestMapping("/activity_add")
    public String activityAdd() {
        return PREFIX + "activity_add.html";
    }

    /**
     * 跳转到修改活动
     */
    @RequestMapping("/activity_update/{activityId}")
    public String activityUpdate(@PathVariable String activityId, Model model) {
        ActivityEntity bean = activityService.getById(activityId);
        model.addAttribute("activity", bean);
        return PREFIX + "activity_edit.html";
    }

    /**
     * 获取活动列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(ActivityEntity entity) {
        Page page = activityService.findList(entity);
        return new PageInfo<>((List<ActivityEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    @RequestMapping(value = "/list/without/content")
    @ResponseBody
    public Object listWithoutContent(ActivityEntity entity) {
        Page page = activityService.findListWithoutContent(entity);
        return new PageInfo<>((List<ActivityEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增活动
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated ActivityEntity entity) {
        activityService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除活动
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        activityService.deleteById(id);
        return SUCCESS_TIP;
    }


    /**
     * 修改活动
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated ActivityEntity entity) {
        activityService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 活动详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return activityService.getById(id);
    }

    /**
     * 修改活动
     */
    @RequestMapping(value = "/disRecommend")
    @ResponseBody
    @BussinessLog(value = "活动取消推荐")
    public Object disRecommend(@Validated ActivityEntity entity) {
        entity.setIsRecommend(0);
        activityService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 修改活动
     */
    @RequestMapping(value = "/recommend")
    @ResponseBody
    @BussinessLog(value = "活动推荐")
    public Object recommend(@Validated ActivityEntity entity) {
        entity.setIsRecommend(1);
        entity.setRecommendTime(new Date());
        activityService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 修改活动
     */
    @RequestMapping(value = "/disTop")
    @ResponseBody
    @BussinessLog(value = "活动取消置顶")
    public Object disTop(@Validated ActivityEntity entity) {
        entity.setIsTop(0);
        activityService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 修改活动
     */
    @RequestMapping(value = "/top")
    @ResponseBody
    @BussinessLog(value = "活动置顶")
    public Object top(@Validated ActivityEntity entity) {
        entity.setIsTop(1);
        entity.setTopTime(new Date());
        activityService.update(entity);
        return super.SUCCESS_TIP;
    }

}
