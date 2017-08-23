package cn.sparke.modules.simple.qrCaptionListening.controller;

import cn.sparke.core.common.constants.tips.ErrorTip;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;
import cn.sparke.modules.questionbank.CaptionListening.service.CaptionListeningService;
import cn.sparke.modules.simple.qrCaptionListening.entity.SimpleCaptionListeningEntity;
import cn.sparke.modules.simple.qrCaptionListening.service.SimpleCaptionListeningService;
import cn.sparke.modules.simple.qrCaptionListening.wrapper.SimpleCaptionListeningWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 简系列字幕听力控制器
 *
 * @author spark
 * @Date 2017-08-14 10:45:03
 */
@Validated
@Controller("simpleCaptionListeningController")
@RequestMapping("/simple/qrCaptionListening")
public class SimpleCaptionListeningController extends BaseController {

    private String PREFIX = "/simple/qrCaptionListening/";

     @Autowired
     private SimpleCaptionListeningService qrCaptionListeningService;


    /**
     * 跳转到简系列字幕听力首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrCaptionListening.html";
    }

    @RequestMapping("/to/upload")
    public String toUpload() {
        return PREFIX + "upload.html";
    }


    /**
     * 跳转到修改简系列字幕听力
     */
    @RequestMapping("/qrCaptionListening_update/{qrCaptionListeningId}")
    public String qrCaptionListeningUpdate(@PathVariable String qrCaptionListeningId, @RequestParam String qrCode , Model model) {
        QrCaptionListeningEntity qrEntity = qrCaptionListeningService.getQrListeningByQrcode(qrCode);
        model.addAttribute("qrEntity",qrEntity);
        return PREFIX + "qrCaptionListening_edit.html";
    }

    /**
     * 获取简系列字幕听力列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(SimpleCaptionListeningEntity entity) {
        Page page =  qrCaptionListeningService.findList(entity);
        return new PageInfo<>((List<SimpleCaptionListeningEntity>) new SimpleCaptionListeningWrapper(page.getResult()).warp(), page.getTotal());
    }


    /**
     * 删除简系列字幕听力
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String qrCode) {
        qrCaptionListeningService.deleteByQrCode(qrCode);
        return SUCCESS_TIP;
    }


    /**
     * 修改简系列字幕听力
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated QrCaptionListeningEntity entity) {
        qrCaptionListeningService.saveOrUpdate(entity);
        return super.SUCCESS_TIP;
    }

    //上传
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object upload( @RequestParam("file") MultipartFile file) {
        try {
            qrCaptionListeningService.upload( file);
        } catch (BussinessException e) {
            return new ErrorTip(e.getCode(),e.getMessage());
        } catch (Exception e) {
            return new ErrorTip(400, e.getMessage());
        }
        return super.SUCCESS_TIP;
    }



}
