package cn.sparke.modules.simple.qrCodeStudyMaterialsWriting.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.simple.qrCodeStudyMaterialsWriting.service.QrCodeStudyMaterialsWritingService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.simple.qrCodeStudyMaterialsWriting.entity.QrCodeStudyMaterialsWritingEntity;

/**
 * 简系列写作翻译阅读控制器
 *
 * @author spark
 * @Date 2017-08-10 10:58:45
 */
@Controller
@RequestMapping("/simple/qrCodeStudyMaterialsWriting")
public class QrCodeStudyMaterialsWritingController extends BaseController {

    private String PREFIX = "/simple/qrCodeStudyMaterialsWriting/";

     @Autowired
     private QrCodeStudyMaterialsWritingService qrCodeStudyMaterialsWritingService;

    /**
     * 跳转到简系列写作翻译阅读首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrCodeStudyMaterialsWriting.html";
    }

    /**
     * 跳转到添加简系列写作翻译阅读
     */
    @RequestMapping("/qrCodeStudyMaterialsWriting_add")
    public String qrCodeStudyMaterialsWritingAdd() {
        return PREFIX + "qrCodeStudyMaterialsWriting_add.html";
    }

    /**
     * 跳转到修改简系列写作翻译阅读
     */
    @RequestMapping("/qrCodeStudyMaterialsWriting_update/{qrCodeStudyMaterialsWritingId}")
    public String qrCodeStudyMaterialsWritingUpdate(@PathVariable String qrCodeStudyMaterialsWritingId, Model model) {
       QrCodeStudyMaterialsWritingEntity bean = qrCodeStudyMaterialsWritingService.getById(qrCodeStudyMaterialsWritingId);
        model.addAttribute("qrCodeStudyMaterialsWriting",bean);
        return PREFIX + "qrCodeStudyMaterialsWriting_edit.html";
    }

    /**
     * 获取简系列写作翻译阅读列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(QrCodeStudyMaterialsWritingEntity entity) {
        Page page =  qrCodeStudyMaterialsWritingService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增简系列写作翻译阅读
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated QrCodeStudyMaterialsWritingEntity entity) {
        qrCodeStudyMaterialsWritingService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除简系列写作翻译阅读
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String qrCodeStudyMaterialsWritingId) {
        qrCodeStudyMaterialsWritingService.deleteById(qrCodeStudyMaterialsWritingId);
        return SUCCESS_TIP;
    }


    /**
     * 修改简系列写作翻译阅读
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated QrCodeStudyMaterialsWritingEntity entity) {
        qrCodeStudyMaterialsWritingService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 简系列写作翻译阅读详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return qrCodeStudyMaterialsWritingService.getById(id);
    }
}
