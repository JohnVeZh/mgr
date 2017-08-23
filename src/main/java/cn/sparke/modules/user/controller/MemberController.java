package cn.sparke.modules.user.controller;

import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.core.modules.shiro.ShiroKit;
import cn.sparke.modules.system.log.entity.OperationLog;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
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
import cn.sparke.modules.user.service.MemberService;
import cn.sparke.modules.user.entity.UserEntity;

import java.util.List;

/**
 * 用户控制器
 * 由于与系统用户管理UserController冲突，controller、service、mapper 都统一改名member
 * @author spark
 * @Date 2017-07-20 10:35:28
 */
@Controller
@RequestMapping("/user")
public class MemberController extends BaseController{

    private String PREFIX = "/user/user/";

     @Autowired
     private MemberService userService;

    /**
     * 跳转到用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * 跳转到添加用户
     */
    @RequestMapping("/user_add")
    public String userAdd() {
        return PREFIX + "user_add.html";
    }

    /**
     * 跳转到修改用户
     */
    @RequestMapping("/user_update/{userId}")
    public String userUpdate(@PathVariable String userId, Model model) {
       UserEntity bean = userService.getById(userId);
        model.addAttribute("user",bean);
        return PREFIX + "user_edit.html";
    }

    /**
     * 获取用户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(UserEntity entity) {
        Page page = userService.findList(entity);
        return new PageInfo<>(page, page.getTotal());
    }

    /**
     * 新增用户
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Validated UserEntity entity) {
        UserEntity searchUser = new UserEntity();
        searchUser.setPhone(entity.getPhone());
        UserEntity userEntity = userService.get(searchUser);
        if(userEntity != null){
            throw new BussinessException(BizExceptionEnum.PHONE_ALREADY_REG);
        }
        entity.setIsPush(1);
        entity.setRegType(6);
        entity.setIsAutoRemove(0);
        entity.setPassword("96e79218965eb72c92a549dd5a330112");//密码111111
        userService.save(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String userId) {
        userService.deleteById(userId);
        return SUCCESS_TIP;
    }


    /**
     * 修改用户
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Object update(@RequestParam("id")String id,@RequestParam("userStatus") int userStatus) {
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setUserStatus(userStatus);
        userService.update(entity);
        return super.SUCCESS_TIP;
    }
    /**
     * 修改用户
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Validated UserEntity entity) {
        userService.update(entity);
        return super.SUCCESS_TIP;
    }

    /**
     * 用户详情
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return userService.getById(id);
    }
}
