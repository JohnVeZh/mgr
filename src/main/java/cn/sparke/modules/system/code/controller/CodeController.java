package cn.sparke.modules.system.code.controller;

import cn.sparke.core.common.constants.Const;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.core.modules.shiro.annotation.Permission;
import cn.sparke.modules.system.code.template.config.ContextConfig;
import cn.sparke.modules.system.code.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代码生成控制器
 *
 * @author spark
 * @Date 2017-05-23 18:52:34
 */
@Controller
@RequestMapping("/code")
public class CodeController extends BaseController {
    @Autowired
    private CodeService codeService;

    private String PREFIX = "/system/code/";

    /**
     * 跳转到代码生成首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "code.html";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    @Permission(Const.ADMIN_NAME)
    public Object add(String bizChName, String bizEnName, String path, String tableName) {
        if (ToolUtil.isOneEmpty(bizChName, bizEnName)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        ContextConfig contextConfig = new ContextConfig();
        contextConfig.setBizChName(bizChName);
        contextConfig.setBizEnName(bizEnName);
        contextConfig.setTableName(tableName);
        if (ToolUtil.isNotEmpty(path)) {
            contextConfig.setProjectPath(path);
        }
        codeService.generate(contextConfig);


        return super.SUCCESS_TIP;
    }
}
