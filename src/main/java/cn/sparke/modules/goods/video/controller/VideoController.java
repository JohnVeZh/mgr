package cn.sparke.modules.goods.video.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.goods.video.entity.VideoEntity;
import cn.sparke.modules.goods.video.service.VideoService;
import cn.sparke.modules.goods.video.utils.VideoNetworkCourseUtil;
import cn.sparke.modules.orders.utils.BizUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * 视频控制器
 *
 * @author spark
 * @Date 2017-07-24 13:10:16
 */
@Controller
@RequestMapping("/goods/video")
public class VideoController extends BaseController {

    private String PREFIX = "/goods/video/";

     @Autowired
     private VideoService videoService;

    /**
     * 跳转到视频首页
     */
    @RequestMapping("/{networkCourseId}")
    public String index(@PathVariable String networkCourseId , Model model) {
        VideoNetworkCourseUtil.handleNetworkCourseId(networkCourseId,model);
        return PREFIX + "video.html";
    }

    /**
     * 跳转到添加视频
     */
    @RequestMapping("/video_add/{networkCourseId}")
    public String videoAdd(@PathVariable String networkCourseId, Model model) {
        VideoNetworkCourseUtil.handleNetworkCourseId(networkCourseId,model);
        return PREFIX + "video_add.html";
    }

    /**
     * 跳转到修改视频
     */
    @RequestMapping("/video_update/{videoId}")
    public String videoUpdate(@PathVariable String videoId, Model model) {
       VideoEntity bean = videoService.getById(videoId);
        model.addAttribute("video",bean);
        return PREFIX + "video_edit.html";
    }

    /**
     * 获取视频列表
     */
    @RequestMapping(value = "/list/{networkCourseId}")
    @ResponseBody
    public Object list(@PathVariable String networkCourseId,VideoEntity entity) {
        VideoEntity video = VideoNetworkCourseUtil.handleNetworkCourseIdForEntity(networkCourseId, entity);
        Page<VideoEntity> list = videoService.findList(video);
        return new PageInfo<>(list);
    }

    /**
     * 获取视频列表
     */
    @RequestMapping(value = "/queryList")
    @ResponseBody
    public Object queryList(VideoEntity entity) {
        Page<VideoEntity> list = videoService.findList(entity);
        return new PageInfo<>(list);
    }

    /**
     * 新增视频
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated VideoEntity entity) {
        videoService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除视频
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String videoId) {
        videoService.deleteById(videoId);
        return SUCCESS_TIP;
    }


    /**
     * 修改视频
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated VideoEntity entity) {
        videoService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 视频详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return videoService.getById(id);
    }



}
