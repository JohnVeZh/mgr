package cn.sparke.modules.business.message.controller;

import cn.sparke.core.modules.mybatis.bean.PageInfo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.business.message.service.MessageUserService;
import cn.sparke.modules.business.message.entity.MessageUserEntity;

/**
 * 消息推送控制器
 *
 * @author spark
 * @Date 2017-07-24 14:20:12
 */
@Controller
@RequestMapping("/messageUser")
public class MessageUserController extends BaseController {

    private String PREFIX = "/business/message/messageUser/";

     @Autowired
     private MessageUserService messageUserService;

    /**
     * 跳转到消息推送首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "messageUser.html";
    }

    /**
     * 跳转到添加消息推送
     */
    @RequestMapping("/messageUser_add")
    public String messageUserAdd() {
        return PREFIX + "messageUser_add.html";
    }

    /**
     * 跳转到修改消息推送
     */
    @RequestMapping("/messageUser_update/{messageUserId}")
    public String messageUserUpdate(@PathVariable String messageUserId, Model model) {
       MessageUserEntity bean = messageUserService.getById(messageUserId);
        model.addAttribute("messageUser",bean);
        return PREFIX + "messageUser_edit.html";
    }

    /**
     * 获取消息推送列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(MessageUserEntity entity) {
        Page page = messageUserService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增消息推送
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated MessageUserEntity entity) {
        messageUserService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除消息推送
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String messageUserId) {
        messageUserService.deleteById(messageUserId);
        return SUCCESS_TIP;
    }


    /**
     * 修改消息推送
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated MessageUserEntity entity) {
        messageUserService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 消息推送详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return messageUserService.getById(id);
    }
}
