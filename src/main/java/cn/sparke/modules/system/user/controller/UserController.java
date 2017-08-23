package cn.sparke.modules.system.user.controller;

import cn.sparke.core.common.constants.Const;
import cn.sparke.core.common.constants.dictmap.UserDict;
import cn.sparke.core.common.constants.factory.ConstantFactory;
import cn.sparke.core.common.constants.tips.Tip;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.core.modules.log.LogObjectHolder;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.shiro.ShiroKit;
import cn.sparke.core.modules.shiro.ShiroUser;
import cn.sparke.core.modules.shiro.annotation.Permission;
import cn.sparke.core.modules.shiro.constants.ManagerStatus;
import cn.sparke.modules.system.user.entity.User;
import cn.sparke.modules.system.user.mapper.UserMapper;
import cn.sparke.modules.system.user.wrapper.UserListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.UUID;

/**
 * 系统管理员控制器
 *
 * @author spark
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    private static String PREFIX = "/system/user/";

    @Autowired
    private UserMapper userMapper;


    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * 查询管理员列表
     */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Object list(User user) {
        List<User> users = userMapper.findList(user);
        return new UserListWrapper(users).warp();
    }

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add.html";
    }

    /**
     * 跳转到角色分配页面
     */
    @Permission
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable String userId, Model model) {
        User user = userMapper.getById(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_roleassign.html";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @Permission
    @RequestMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable String userId, Model model) {
        User user = this.userMapper.getById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return PREFIX + "user_edit.html";
    }

    /**
     * 跳转到查看用户详情页面
     */
    @RequestMapping("/user_info")
    public String userInfo(Model model) {
        String userId = ShiroKit.getUser().getId();
        User user = this.userMapper.getById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return PREFIX + "user_view.html";
    }

    /**
     * 跳转到修改密码界面
     */
    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return PREFIX + "user_chpwd.html";
    }

    /**
     * 修改当前用户的密码
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
        if (!newPwd.equals(rePwd)) {
            throw new BussinessException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        String userId = ShiroKit.getUser().getId();
        User user = userMapper.getById(userId);
        String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
        if (user.getPassword().equals(oldMd5)) {
            String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
            user.setPassword(newMd5);
            user.preUpdate();
            userMapper.update(user);
            return SUCCESS_TIP;
        } else {
            throw new BussinessException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }


    /**
     * 添加管理员
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Validated User user) {
        // 判断账号是否重复
        User theUser = userMapper.getByAccount(user.getAccount());
        if (theUser != null) {
            throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
        }
        // 完善账号信息
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setStatus(ManagerStatus.OK.getCode());
        user.preInsert();
        this.userMapper.insert(user);
        return SUCCESS_TIP;
    }

    /**
     * 修改管理员
     *
     * @throws NoPermissionException
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
    @ResponseBody
    public Tip edit(@Validated User user) throws NoPermissionException {
        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
            user.preUpdate();
            this.userMapper.update(user);
            return SUCCESS_TIP;
        } else {
            ShiroUser shiroUser = ShiroKit.getUser();
            if (shiroUser.getId().equals(user.getId())) {
                user.preUpdate();
                userMapper.update(user);
                return SUCCESS_TIP;
            } else {
                throw new BussinessException(BizExceptionEnum.NO_PERMITION);
            }
        }
    }

    /**
     * 删除管理员（逻辑删除）
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip delete(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
        if (userId.equals(String.valueOf(Const.ADMIN_ID))) {
            throw new BussinessException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        this.userMapper.deleteById(userId);
        return SUCCESS_TIP;
    }

    /**
     * 查看管理员详情
     */
    @RequestMapping("/view/{userId}")
    @ResponseBody
    public User view(@PathVariable String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        return this.userMapper.getById(userId);
    }

    /**
     * 重置管理员的密码
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip reset(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userMapper.getById(userId);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
        user.preUpdate();
        this.userMapper.update(user);
        return SUCCESS_TIP;
    }

    /**
     * 冻结用户
     */
    @RequestMapping("/freeze")
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip freeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能冻结超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new BussinessException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        this.userMapper.setStatus(userId, ManagerStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结用户
     */
    @RequestMapping("/unfreeze")
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip unfreeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userMapper.setStatus(userId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 分配角色
     */
    @RequestMapping("/setRole")
    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip setRole(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new BussinessException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        this.userMapper.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }


    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {
        String pictureName = UUID.randomUUID().toString() + ".jpg";
//        try {
//            String fileSavePath = dbProperties.getFileUploadPath();
//            picture.transferTo(new File(fileSavePath + pictureName));
//        } catch (Exception e) {
//            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
//        }
        return pictureName;
    }
}
