package cn.sparke.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.user.service.UserAddressService;
import cn.sparke.modules.user.entity.UserAddressEntity;

/**
 * 收货地址控制器
 *
 * @author spark
 * @Date 2017-07-22 10:49:10
 */
@Controller
@RequestMapping("/userAddress")
public class UserAddressController extends BaseController {

    private String PREFIX = "/user/userAddress/";

     @Autowired
     private UserAddressService userAddressService;

    /**
     * 跳转到收货地址首页
     */
    @RequestMapping("")
    public String index(@RequestParam("userId") String userId,Model model) {
        model.addAttribute("userId",userId);
        return PREFIX + "userAddress.html";
    }

    /**
     * 跳转到添加收货地址
     */
    @RequestMapping("/userAddress_add")
    public String userAddressAdd(@RequestParam("userId") String userId,Model model) {
        model.addAttribute("userId",userId);
        return PREFIX + "userAddress_add.html";
    }

    /**
     * 跳转到修改收货地址
     */
    @RequestMapping("/userAddress_update/{userAddressId}")
    public String userAddressUpdate(@PathVariable String userAddressId, Model model) {
       UserAddressEntity bean = userAddressService.getById(userAddressId);
        model.addAttribute("userAddress",bean);
        return PREFIX + "userAddress_edit.html";
    }

    /**
     * 获取收货地址列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(UserAddressEntity entity) {
        return userAddressService.findList(entity);
    }

    /**
     * 新增收货地址
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated UserAddressEntity entity) {
        userAddressService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除收货地址
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String userAddressId) {
        userAddressService.deleteById(userAddressId);
        return SUCCESS_TIP;
    }


    /**
     * 修改收货地址
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated UserAddressEntity entity) {
        userAddressService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 收货地址详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return userAddressService.getById(id);
    }
}
