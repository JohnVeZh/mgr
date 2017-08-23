package cn.sparke.modules.banner.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.community.activity.entity.ActivityEntity;
import cn.sparke.modules.community.activity.service.IActivityService;
import cn.sparke.modules.community.news.entity.CommunityNewsEntity;
import cn.sparke.modules.community.news.service.CommunityNewsService;
import cn.sparke.modules.goods.book.entity.BookEntity;
import cn.sparke.modules.goods.book.service.BookService;
import cn.sparke.modules.goods.networkCourse.entity.NetworkCourseEntity;
import cn.sparke.modules.goods.networkCourse.service.NetworkCourseService;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.banner.service.BannerService;
import cn.sparke.modules.banner.entity.BannerEntity;

import java.util.List;

/**
 * banner控制器
 *
 * @author spark
 * @Date 2017-07-27 11:04:48
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends BaseController {

    private String PREFIX = "/banner/";

     @Autowired
     private BannerService bannerService;
     @Autowired
     private BookService bookService;
    @Autowired
    private NetworkCourseService networkCourseService;
    @Autowired
    private IActivityService activityService;
    @Autowired
    private CommunityNewsService communityNewsService;

    /**
     * 跳转到banner首页
     */
    @RequestMapping("")
    public String index(@RequestParam(value = "isSelect", defaultValue = "false") boolean isSelect, Model model) {
        model.addAttribute("isSelect", isSelect);
        return PREFIX + "banner.html";
    }

    /**
     * 跳转到添加banner
     */
    @RequestMapping("/banner_add")
    public String bannerAdd() {
        return PREFIX + "banner_add.html";
    }

    /**
     * 跳转到修改banner
     */
    @RequestMapping("/banner_update/{bannerId}")
    public String bannerUpdate(@PathVariable String bannerId, Model model) {
       BannerEntity bean = bannerService.getById(bannerId);
        model.addAttribute("banner",bean);
        return PREFIX + "banner_edit.html";
    }

    /**
     * 获取banner列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(BannerEntity entity) {
        Page page = bannerService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增banner
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated BannerEntity entity) {
        bannerService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除banner
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String bannerId) {
        bannerService.deleteById(bannerId);
        return SUCCESS_TIP;
    }


    /**
     * 修改banner
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated BannerEntity entity) {
        bannerService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * banner详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return bannerService.getById(id);
    }



    /**
     * 跳转到图书首页
     */
    @RequestMapping("book")
    public String bookIndex() {
        return PREFIX + "book.html";
    }

    /**
     * 获取图书列表
     */
    @RequestMapping(value = "/book/list")
    @ResponseBody
    public Object bookList(BookEntity entity) {
        Page<BookEntity> bookEntities = bookService.queryAllList(entity);
        return new PageInfo<>(bookEntities);
    }

    /**
     * 跳转到网课首页
     */
    @RequestMapping("network")
    public String networkIndex() {
        return PREFIX + "networkCourse.html";
    }

    /**
     * 获取网课列表
     */
    @RequestMapping(value = "/network/list")
    @ResponseBody
    public Object list(NetworkCourseEntity entity) {
        Page<NetworkCourseEntity> list = networkCourseService.queryAllList(entity);
        return new PageInfo<>(list);
    }

    /**
     * 跳转到活动首页
     */
    @RequestMapping("activity")
    public String activityIndex() {
        return PREFIX + "activity.html";
    }

    /**
     * 获取活动列表
     */
    @RequestMapping(value = "/activity/list")
    @ResponseBody
    public Object list(ActivityEntity entity) {
        Page page = activityService.findList(entity);
        return new PageInfo<>((List<ActivityEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 跳转到社区资讯首页
     */
    @RequestMapping("new")
    public String newIndex() {
        return PREFIX + "communityNews.html";
    }

    /**
     * 获取社区资讯列表
     */
    @RequestMapping(value = "/new/list")
    @ResponseBody
    public Object list(CommunityNewsEntity entity) {
        Page page = communityNewsService.findList(entity);
        return new PageInfo<>((List<CommunityNewsEntity>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }
}
