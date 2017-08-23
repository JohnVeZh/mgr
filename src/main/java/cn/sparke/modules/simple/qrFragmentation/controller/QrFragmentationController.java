package cn.sparke.modules.simple.qrFragmentation.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.qrcode.qrCode.entity.QrCodeEntity;
import cn.sparke.modules.simple.qrFragmentation.entity.QrFragmentationEntity;
import cn.sparke.modules.simple.qrFragmentation.service.QrFragmentationService;
import cn.sparke.modules.simple.qrNetworkVideo.wrapper.QrNetworkVideoWrapper;
import com.github.pagehelper.Page;
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
 * 简系列碎片化控制器
 *
 * @author spark
 * @Date 2017-08-09 17:07:15
 */
@Controller
@RequestMapping("/simple/qrFragmentation")
public class QrFragmentationController extends BaseController {

    private String PREFIX = "/simple/qrFragmentation/";

     @Autowired
     private QrFragmentationService qrFragmentationService;

    /**
     * 跳转到简系列碎片化首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrFragmentation.html";
    }

    /**
     * 跳转到添加简系列碎片化
     */
    @RequestMapping("/qrFragmentation_add")
    public String qrFragmentationAdd() {
        return PREFIX + "qrFragmentation_add.html";
    }

    /**
     * 跳转到修改简系列碎片化
     */
    @RequestMapping("/qrFragmentation_update/{qrFragmentationId}")
    public String qrFragmentationUpdate(@PathVariable String qrFragmentationId, Model model) {
       QrFragmentationEntity bean = qrFragmentationService.getById(qrFragmentationId);
        model.addAttribute("qrFragmentation",bean);
        return PREFIX + "qrFragmentation_edit.html";
    }

    /**
     * 获取简系列碎片化列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(QrFragmentationEntity entity) {
        entity.setName(entity.getCondition());
        Page page = qrFragmentationService.findList(entity);
        return new PageInfo<>((List<QrCodeEntity>) new QrNetworkVideoWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增简系列碎片化
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated QrFragmentationEntity entity) {
        qrFragmentationService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除简系列碎片化
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String qrFragmentationId) {
        qrFragmentationService.deleteById(qrFragmentationId);
        return SUCCESS_TIP;
    }


    /**
     * 修改简系列碎片化
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated QrFragmentationEntity entity) {
        qrFragmentationService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 简系列碎片化详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return qrFragmentationService.getById(id);
    }
}
