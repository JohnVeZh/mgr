package cn.sparke.modules.qrcode.QrCaptionListening.controller;

import cn.sparke.modules.qrcode.QrCaptionListening.entity.QrCaptionListeningEntity;
import cn.sparke.modules.qrcode.QrCaptionListening.service.QrCaptionListeningService;
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
 * 二维码试卷字幕听力控制器
 *
 * @author spark
 * @Date 2017-07-26 23:27:40
 */
@Controller
@RequestMapping("/QrCaptionListening")
public class QrCaptionListeningController extends BaseController {

    private String PREFIX = "/questionbank/QrCaptionListening/";

     @Autowired
     private QrCaptionListeningService qrCaptionListeningService;

    /**
     * 跳转到二维码试卷字幕听力首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "QrCaptionListening.html";
    }

    /**
     * 跳转到添加二维码试卷字幕听力
     */
    @RequestMapping("/QrCaptionListening_add")
    public String QrCaptionListeningAdd() {
        return PREFIX + "QrCaptionListening_add.html";
    }

    /**
     * 跳转到修改二维码试卷字幕听力
     */
    @RequestMapping("/QrCaptionListening_update")
    public String QrCaptionListeningUpdate(String QrCaptionListeningId, String questionId, String paperId,Model model) {
        Map<String ,Object> dataMap = new HashMap<String,Object>();
       QrCaptionListeningEntity bean = qrCaptionListeningService.getById(QrCaptionListeningId);
        model.addAttribute("QrCaptionListening",bean);
        return PREFIX + "QrCaptionListening_edit.html";
    }

    /**
     * 获取二维码试卷字幕听力列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(QrCaptionListeningEntity entity) {
        return qrCaptionListeningService.findList(entity);
    }

    /**
     * 新增二维码试卷字幕听力
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated QrCaptionListeningEntity entity) {
        qrCaptionListeningService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除二维码试卷字幕听力
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String QrCaptionListeningId) {
        qrCaptionListeningService.deleteById(QrCaptionListeningId);
        return SUCCESS_TIP;
    }


    /**
     * 修改二维码试卷字幕听力
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated QrCaptionListeningEntity entity) {
        qrCaptionListeningService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 二维码试卷字幕听力详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return qrCaptionListeningService.getById(id);
    }
}
