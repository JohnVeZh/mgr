package cn.sparke.modules.simple.qrNetworkVideo.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.qrcode.qrCode.entity.QrCodeEntity;
import cn.sparke.modules.simple.qrNetworkVideo.entity.QrNetworkVideoEntity;
import cn.sparke.modules.simple.qrNetworkVideo.service.QrNetworkVideoService;
import cn.sparke.modules.simple.qrNetworkVideo.wrapper.QrNetworkVideoWrapper;
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
 * 简系列视频控制器
 *
 * @author spark
 * @Date 2017-08-09 14:26:29
 */
@Controller
@RequestMapping("/simple/qrNetworkVideo")
public class QrNetworkVideoController extends BaseController {

    private String PREFIX = "/simple/qrNetworkVideo/";

     @Autowired
     private QrNetworkVideoService qrNetworkVideoService;

    /**
     * 跳转到简系列视频首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrNetworkVideo.html";
    }

    /**
     * 跳转到添加简系列视频
     */
    @RequestMapping("/qrNetworkVideo_add")
    public String qrNetworkVideoAdd() {
        return PREFIX + "qrNetworkVideo_add.html";
    }

    /**
     * 跳转到修改简系列视频
     */
    @RequestMapping("/qrNetworkVideo_update/{qrNetworkVideoId}")
    public String qrNetworkVideoUpdate(@PathVariable String qrNetworkVideoId, Model model) {
       QrNetworkVideoEntity bean = qrNetworkVideoService.getById(qrNetworkVideoId);
        model.addAttribute("qrNetworkVideo",bean);
        return PREFIX + "qrNetworkVideo_edit.html";
    }

    /**
     * 获取简系列视频列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(QrNetworkVideoEntity entity) {
        entity.setName(entity.getCondition());
        Page page = qrNetworkVideoService.findList(entity);
        return new PageInfo<>((List<QrCodeEntity>) new QrNetworkVideoWrapper(page.getResult()).warp(), page.getTotal());

    }

    /**
     * 新增简系列视频
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated QrNetworkVideoEntity entity) {
        qrNetworkVideoService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除简系列视频
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String qrNetworkVideoId) {
        qrNetworkVideoService.deleteById(qrNetworkVideoId);
        return SUCCESS_TIP;
    }


    /**
     * 修改简系列视频
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated QrNetworkVideoEntity entity) {
        qrNetworkVideoService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 简系列视频详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return qrNetworkVideoService.getById(id);
    }
}
