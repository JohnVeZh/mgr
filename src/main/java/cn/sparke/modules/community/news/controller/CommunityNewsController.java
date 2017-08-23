package cn.sparke.modules.community.news.controller;

import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.community.activity.entity.ActivityEntity;
import cn.sparke.modules.community.news.entity.CommunityNewsEntity;
import cn.sparke.modules.community.news.service.CommunityNewsService;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;


/**
 * 社区资讯控制器
 *
 * @author spark
 * @Date 2017-07-24 16:51:33
 */
@Controller
@RequestMapping("/community/news")
public class CommunityNewsController extends BaseController {

    private String PREFIX = "/community/news/";

    @Autowired
    private CommunityNewsService communityNewsService;

    /**
     * 跳转到社区资讯首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "communityNews.html";
    }

    /**
     * 跳转到添加社区资讯
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String communityNewsAdd() {
        return PREFIX + "communityNews_add.html";
    }

    /**
     * 跳转到修改社区资讯
     */
    @RequestMapping("/update/{communityNewsId}")
    public String communityNewsUpdate(@PathVariable String communityNewsId, Model model) {
        CommunityNewsEntity bean = communityNewsService.getById(communityNewsId);
        model.addAttribute("communityNews", bean);
        return PREFIX + "communityNews_edit.html";
    }

    /**
     * 获取社区资讯列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(CommunityNewsEntity entity) {
        Page page = communityNewsService.findList(entity);
        return new PageInfo<>((List<CommunityNewsEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 获取社区资讯列表
     */
    @RequestMapping(value = "/list/without/content")
    @ResponseBody
    public Object listWithoutContent(CommunityNewsEntity entity) {
        Page page = communityNewsService.findListWithoutContent(entity);
        return new PageInfo<>((List<CommunityNewsEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增社区资讯
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody @Validated CommunityNewsEntity entity) {
        communityNewsService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除社区资讯
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String communityNewsId) {
        communityNewsService.deleteById(communityNewsId);
        return SUCCESS_TIP;
    }


    /**
     * 修改社区资讯
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody @Validated CommunityNewsEntity entity) {
        communityNewsService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 修改活动
     */
    @RequestMapping(value = "/disRecommend")
    @ResponseBody
    @BussinessLog(value = "资讯取消推荐")
    public Object disRecommend(@Validated CommunityNewsEntity entity) {
        entity.setIsRecommend(0);
        communityNewsService.update(entity);
        return super.SUCCESS_TIP;
    }
    /**
     * 修改活动
     */
    @RequestMapping(value = "/recommend")
    @ResponseBody
    @BussinessLog(value = "资讯推荐")
    public Object recommend(@Validated CommunityNewsEntity entity) {
        entity.setIsRecommend(1);
        entity.setRecommendTime(new Date());
        communityNewsService.update(entity);
        return super.SUCCESS_TIP;
    }
    /**
     * 修改活动
     */
    @RequestMapping(value = "/disTop")
    @ResponseBody
    @BussinessLog(value = "资讯取消置顶")
    public Object disTop(@Validated CommunityNewsEntity entity) {
        entity.setIsTop(0);
        communityNewsService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 修改活动
     */
    @RequestMapping(value = "/top")
    @ResponseBody
    @BussinessLog(value = "资讯置顶")
    public Object top(@Validated CommunityNewsEntity entity) {
        entity.setIsTop(1);
        entity.setTopTime(new Date());
        communityNewsService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 社区资讯详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return communityNewsService.getById(id);
    }
}
