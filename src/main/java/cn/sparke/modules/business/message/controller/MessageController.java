package cn.sparke.modules.business.message.controller;

import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
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
import cn.sparke.modules.business.message.service.MessageService;
import cn.sparke.modules.business.message.entity.MessageEntity;

/**
 * 消息推送控制器
 *
 * @author spark
 * @Date 2017-07-24 14:17:38
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {


    private String PREFIX = "/business/message/message/";

     @Autowired
     private MessageService messageService;

    /**
     * 跳转到消息推送首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "message.html";
    }

    /**
     * 跳转到添加消息推送
     */
    @RequestMapping("/message_add")
    public String messageAdd() {
        return PREFIX + "message_add.html";
    }

    /**
     * 跳转到修改消息推送
     */
    @RequestMapping("/message_update/{messageId}")
    public String messageUpdate(@PathVariable String messageId, Model model) {
       MessageEntity bean = messageService.getById(messageId);
        model.addAttribute("message",bean);
        return PREFIX + "message_edit.html";
    }

    /**
     * 获取消息推送列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(MessageEntity entity) {
        entity.setTargetType(2);
        Page page = messageService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增消息推送
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated MessageEntity entity) {
        messageService.save(entity);
        return super.SUCCESS_TIP;
    }

    @RequestMapping(value = "/push")
    @ResponseBody
    public Object push(@RequestParam("messageId") String id) {
        MessageEntity entity = messageService.getById(id);
        if(entity == null){
            throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
        }
        if(entity.getTargetType() == 1){//暂时没有单推业务

        }else if(entity.getTargetType() == 2){
            //自定义推送demo
            messageService.sendNotificationAndMessage(entity);
            //官方SDK方法推送
//            messageService.sendNotificationAll(entity);
        }
        entity = new MessageEntity();
        entity.setId(id);
        entity.setPushStatus(1);
        messageService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除消息推送
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String messageId) {
        messageService.deleteById(messageId);
        return SUCCESS_TIP;
    }


    /**
     * 修改消息推送
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated MessageEntity entity) {
        messageService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 消息推送详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return messageService.getById(id);
    }
}
