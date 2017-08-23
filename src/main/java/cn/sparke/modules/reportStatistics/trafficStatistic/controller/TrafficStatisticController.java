package cn.sparke.modules.reportStatistics.trafficStatistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import cn.sparke.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import cn.sparke.modules.reportStatistics.trafficStatistic.service.TrafficStatisticService;
import cn.sparke.modules.reportStatistics.trafficStatistic.entity.TrafficStatisticEntity;

/**
 * 流量统计控制器
 *
 * @author spark
 * @Date 2017-08-01 10:03:56
 */
@Controller
@RequestMapping("/trafficStatistic")
public class TrafficStatisticController extends BaseController {

    private String PREFIX = "/reportStatistics/trafficStatistic/";

     @Autowired
     private TrafficStatisticService trafficStatisticService;

    /**
     * 跳转到流量统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "trafficStatistic.html";
    }

    /**
     * 获取流量统计列表
     */
    @RequestMapping(value = "/table")
    @ResponseBody
    public String list(TrafficStatisticEntity entity) {
        return trafficStatisticService.getTableHtml(entity);
    }


}
