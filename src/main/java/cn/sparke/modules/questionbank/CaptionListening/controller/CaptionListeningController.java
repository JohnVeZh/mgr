package cn.sparke.modules.questionbank.CaptionListening.controller;

import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;
import cn.sparke.modules.questionbank.CaptionListening.entity.CaptionListeningEntity;
import cn.sparke.modules.questionbank.CaptionListening.service.CaptionListeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;


/**
 * 字幕听力音频控制器
 *
 * @author spark
 * @Date 2017-07-26 23:01:00
 */
@Controller
@RequestMapping("/CaptionListening")
public class CaptionListeningController extends BaseController {

    private String PREFIX = "/questionbank/CaptionListening/";

     @Autowired
     private CaptionListeningService captionListeningService;

    /**
     * 跳转到字幕听力音频首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "CaptionListening.html";
    }

    @RequestMapping("/captionListeningManage")
    public String captionListeningManage(String paperId,Integer type ,Model model){
        model.addAttribute("paperId",paperId);
        model.addAttribute("type",type);
        if(type==5){//扫描
            return PREFIX+"QrCaptionListening.html";
        }else {
            return PREFIX+"CaptionListening.html";
        }

    }

    /**
     * 跳转到添加字幕听力音频
     */
    @RequestMapping("/CaptionListening_add")
    public String CaptionListeningAdd(String paperId,String type ,String sectionCode ,String contentType,Model model) {
        model.addAttribute("paperId",paperId);
        model.addAttribute("type",type);
        return PREFIX + "CaptionListening_add.html";
    }

    @RequestMapping("/CaptionListening_addQr")
    public String CaptionListeningAddQr(String paperId,String type ,Model model) {
        model.addAttribute("paperId",paperId);
        model.addAttribute("type",type);
        return PREFIX + "QrCaptionListening_add.html";
    }

    /**
     * 跳转到修改字幕听力音频
     */
    @RequestMapping("/CaptionListening_update")
    public String CaptionListeningUpdate(String CaptionListeningId, Model model) {
       CaptionListeningEntity bean = captionListeningService.getById(CaptionListeningId);
        model.addAttribute("CaptionListening",bean);
        return PREFIX + "CaptionListening_edit.html";
    }

    @RequestMapping("/CaptionListening_updateQr")
    public String CaptionListening_updateQr(String id, Model model) {
        QrCaptionListeningEntity bean = captionListeningService.getByIdQr(id);
        model.addAttribute("QrCaptionListening",bean);
        return PREFIX + "QrCaptionListening_edit.html";
    }

    /**
     * 获取字幕听力音频列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(CaptionListeningEntity entity) {
        //entity.setPaperId(paperId);
        return captionListeningService.findList(entity);
    }

    /**
     * 新增字幕听力音频
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated CaptionListeningEntity entity) {
        captionListeningService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 新增字幕听力音频
     */
    @RequestMapping(value = "/addQr")
    @ResponseBody
    public Object addQr(@Validated QrCaptionListeningEntity entity) {
        captionListeningService.saveQr(entity);
        return super.SUCCESS_TIP;
    }


    /**
     * 删除字幕听力音频
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String CaptionListeningId) {
        captionListeningService.deleteById(CaptionListeningId);
        return SUCCESS_TIP;
    }

    /**
     * 删除字幕听力音频
     */
    @RequestMapping(value = "/deleteQr")
    @ResponseBody
    public Object deleteQr(String qrCaptionListeningId) {
        captionListeningService.deleteQrById(qrCaptionListeningId);
        return SUCCESS_TIP;
    }

    /**
     * 修改字幕听力音频
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated CaptionListeningEntity entity) {
        captionListeningService.update(entity);
        return super.SUCCESS_TIP;
    }

    @RequestMapping(value = "/updateQr")
    @ResponseBody
    public Object updateQr(@Validated QrCaptionListeningEntity entity) {
        captionListeningService.updateQr(entity);
        return super.SUCCESS_TIP;
    }


    /**
     * 字幕听力音频详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return captionListeningService.getById(id);
    }
}
