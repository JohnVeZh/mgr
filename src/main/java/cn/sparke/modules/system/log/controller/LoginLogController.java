package cn.sparke.modules.system.log.controller;

import com.github.pagehelper.Page;
import cn.sparke.core.common.constants.Const;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.core.modules.shiro.annotation.Permission;
import cn.sparke.modules.system.log.entity.LoginLog;
import cn.sparke.modules.system.log.mapper.LoginLogMapper;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 日志管理的控制器
 *
 * @author spark
 * @Date 2017年4月5日 19:45:36
 */
@Controller
@RequestMapping("/system/loginLog")
public class LoginLogController extends BaseController {

    private static String PREFIX = "/system/log/";

    @Autowired
    private LoginLogMapper loginLogMapper;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "login_log.html";
    }

    /**
     * 查询登录日志列表
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(LoginLog loginLog) {
        Page page = loginLogMapper.findList(loginLog);
        return new PageInfo<>((List<LoginLog>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 清空日志
     */
    @BussinessLog("清空登录日志")
    @RequestMapping("/delLoginLog")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object delLog() {
        return super.SUCCESS_TIP;
    }
}
