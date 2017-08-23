package cn.sparke.modules.qrcode.qrCode.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.qrcode.qrCode.entity.QrCodeEntity;
import cn.sparke.modules.qrcode.qrCode.service.QrCodeService;
import cn.sparke.modules.qrcode.qrCode.wrapper.QrCodeWrapper;
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
 * 二维码控制器
 *
 * @author spark
 * @Date 2017-07-21 20:14:52
 */
@Controller
@RequestMapping("/qrCode")
public class QrCodeController extends BaseController {

    private String PREFIX = "/qrcode/qrCode/";

     @Autowired
     private QrCodeService qrCodeService;

    /**
     * 跳转到二维码首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrCode.html";
    }

    /**
     * 跳转到添加二维码
     */
    @RequestMapping("/qrCode_add")
    public String qrCodeAdd() {
        return PREFIX + "qrCode_add.html";
    }

    /**
     * 跳转到修改二维码
     */
    @RequestMapping("/qrCode_update/{qrCodeId}")
    public String qrCodeUpdate(@PathVariable String qrCodeId, Model model) {
       QrCodeEntity bean = qrCodeService.getById(qrCodeId);
        model.addAttribute("qrCode",bean);
        return PREFIX + "qrCode_edit.html";
    }

    /**
     * 获取二维码列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(QrCodeEntity entity) {
        Page page = qrCodeService.findList(entity);
        return new PageInfo<>((List<QrCodeEntity>) new QrCodeWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 新增二维码
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated QrCodeEntity entity) {
        qrCodeService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除二维码
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String qrCodeId) {
        qrCodeService.deleteById(qrCodeId);
        return SUCCESS_TIP;
    }


    /**
     * 修改二维码
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated QrCodeEntity entity) {
        qrCodeService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 二维码详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return qrCodeService.getById(id);
    }
}
