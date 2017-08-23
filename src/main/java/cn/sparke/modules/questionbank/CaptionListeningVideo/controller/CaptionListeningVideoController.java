package cn.sparke.modules.questionbank.CaptionListeningVideo.controller;

import cn.sparke.modules.questionbank.CaptionListeningVideo.entity.CaptionListeningVideoEntity;
import cn.sparke.modules.questionbank.CaptionListeningVideo.service.CaptionListeningVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 字幕听力视频控制器
 *
 * @author spark
 * @Date 2017-07-26 22:58:39
 */
@Controller
@RequestMapping("/CaptionListeningVideo")
public class CaptionListeningVideoController extends BaseController {

    private String PREFIX = "/questionbank/CaptionListeningVideo/";

     @Autowired
     private CaptionListeningVideoService captionListeningVideoService;

    /**
     * 跳转到字幕听力视频首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "CaptionListeningVideo.html";
    }

    @RequestMapping("/captionListeningVideoManage")
    public String captionListeningManage(String paperId,String type ,Model model){
        model.addAttribute("paperId",paperId);
        model.addAttribute("type",type);
        return PREFIX+"CaptionListeningVideo.html";
    }

    /**
     * 跳转到添加字幕听力视频
     */
    @RequestMapping("/CaptionListeningVideo_add")
    public String CaptionListeningVideoAdd(Integer type,String paperId , Model model) {
        model.addAttribute("type",type);
        model.addAttribute("paperId",paperId);
        return PREFIX + "CaptionListeningVideo_add.html";
    }

    /**
     * 跳转到修改字幕听力视频
     */
    @RequestMapping("/CaptionListeningVideo_update/{CaptionListeningVideoId}")
    public String CaptionListeningVideoUpdate(@PathVariable String CaptionListeningVideoId, Model model) {
       CaptionListeningVideoEntity bean = captionListeningVideoService.getById(CaptionListeningVideoId);
        model.addAttribute("CaptionListeningVideo",bean);
        return PREFIX + "CaptionListeningVideo_edit.html";
    }

    /**
     * 获取字幕听力视频列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(CaptionListeningVideoEntity entity) {
        return captionListeningVideoService.findList(entity);
    }

    /**
     * 新增字幕听力视频
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated CaptionListeningVideoEntity entity) {
        captionListeningVideoService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除字幕听力视频
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String CaptionListeningVideoId) {
        captionListeningVideoService.deleteById(CaptionListeningVideoId);
        return SUCCESS_TIP;
    }


    /**
     * 修改字幕听力视频
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated CaptionListeningVideoEntity entity) {
        captionListeningVideoService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 字幕听力视频详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return captionListeningVideoService.getById(id);
    }
}
