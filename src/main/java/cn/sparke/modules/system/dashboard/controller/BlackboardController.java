package cn.sparke.modules.system.dashboard.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.log.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 总览信息
 *
 * @author spark
 * @Date 2017年3月4日23:05:54
 */
@Controller
@RequestMapping("/blackboard")
public class BlackboardController extends BaseController {

    private static Logger logger = Logger.getLogger(LogManager.class);
    /**
     * 跳转到黑板
     */
    @RequestMapping("")
    public String blackboard(Model model) {
        return "/blackboard.html";
    }
}
