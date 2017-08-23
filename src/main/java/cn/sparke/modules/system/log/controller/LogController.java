package cn.sparke.modules.system.log.controller;

import com.github.pagehelper.Page;
import cn.sparke.core.common.constants.Const;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.core.modules.shiro.annotation.Permission;
import cn.sparke.modules.system.log.entity.OperationLog;
import cn.sparke.modules.system.log.mapper.OperationLogMapper;
import cn.sparke.modules.system.log.wrapper.LogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/system/log")
public class LogController extends BaseController {

    private static String PREFIX = "/system/log/";

    @Autowired
    private OperationLogMapper operationLogMapper;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "log.html";
    }

    /**
     * 查询操作日志列表
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(OperationLog operationLog) {
        Page page = operationLogMapper.findList(operationLog);
        return new PageInfo<>((List<OperationLog>) new LogWrapper(page.getResult()).warp(), page.getTotal());
    }

    /**
     * 查询操作日志详情
     */
    @RequestMapping("/detail/{id}")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object detail(@PathVariable String id) {
        return new LogWrapper(operationLogMapper.getById(id)).warp();
    }

    /**
     * 清空日志
     */
    @BussinessLog(value = "清空业务日志")
    @RequestMapping("/delLog")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object delLog() {
        return super.SUCCESS_TIP;
    }
}
