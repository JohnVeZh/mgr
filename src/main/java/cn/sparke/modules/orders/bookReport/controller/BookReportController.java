package cn.sparke.modules.orders.bookReport.controller;

import cn.sparke.core.common.constants.tips.FailureTip;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.orders.bookReport.entity.OrderEntity;
import cn.sparke.modules.orders.bookReport.entity.OrderEvaluateEntity;
import cn.sparke.modules.orders.bookReport.service.BookReportService;
import cn.sparke.modules.orders.utils.BizUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 图书订单报表控制器
 *
 * @author spark
 * @Date 2017-07-18 19:32:38
 */
@Controller
@RequestMapping("/orders/bookReport")
public class BookReportController extends BaseController {

    private String PREFIX = "/orders/bookReport/";

    @Autowired
    private BookReportService bookReportService;

    /**
     * 跳转-图书订单报表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bookReport.html";
    }

    /**
     * 获取图书订单报表列表，分页
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(OrderEntity order) {
        Page orderList = this.bookReportService.findList(order);
        return new PageInfo<>(orderList);
    }

    /**
     * 跳转-图报表修改
     */
    @RequestMapping("/update/{orderId}")
    public String update(@PathVariable String orderId, Model model) {
        OrderEntity order = this.bookReportService.getOrderUpdateInfoById(orderId);
        model.addAttribute("order", order);
        return PREFIX + "bookReport_update.html";
    }

    /**
     * 保存-修改订单
     */
    @RequestMapping("/updateSave")
    @ResponseBody
    public Object updateSave(HttpServletRequest request) {
        if (bookReportService.update(BizUtil.getParameterMap(request)) > 0) {
            return super.SUCCESS_TIP;
        }
        return new FailureTip();
    }

    /**
     * 订单详情
     */
    @RequestMapping("/details/{orderId}")
    public String details(@PathVariable String orderId, Model model) {
        OrderEntity order = bookReportService.getOrderDetailsInfoById(orderId);
        model.addAttribute("order", order);
        return PREFIX + "bookReport_details.html";
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping("/updateStatus/{orderId}")
    public String updateOrderStatus(@PathVariable String orderId, Model model) {
        OrderEntity orderEntity = bookReportService.getOrderStatusInfoById(orderId);
        model.addAttribute("order", orderEntity);
        return PREFIX + "bookReport_updateStatus.html";
    }

    /**
     * 修改订单状态-保存
     */
    @RequestMapping("/updateStatusSave")
    @ResponseBody
    public Object updateOrderStatusSave(HttpServletRequest request) {
        Map<String, Object> paramMap = BizUtil.getParameterMap(request);
        if (paramMap.get("orderStatus") != null && bookReportService.updateOrderStatus(paramMap) > 0) {
            return super.SUCCESS_TIP;
        }
        return new FailureTip();
    }



}
