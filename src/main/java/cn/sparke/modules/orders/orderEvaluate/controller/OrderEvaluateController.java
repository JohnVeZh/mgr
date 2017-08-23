package cn.sparke.modules.orders.orderEvaluate.controller;

import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.orders.orderEvaluate.entity.OrderEvaluateEntity;
import cn.sparke.modules.orders.orderEvaluate.service.OrderEvaluateService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单评价管理控制器
 *
 * @author spark
 * @Date 2017-08-03 09:53:15
 */
@Controller
@RequestMapping("/orders/orderEvaluate")
public class OrderEvaluateController extends BaseController {

    private String PREFIX = "/orders/orderEvaluate/";

     @Autowired
     private OrderEvaluateService orderEvaluateService;

    /**
     * 跳转到订单评价管理首页
     */
    @RequestMapping("{orderId}")
    public String index(@PathVariable("orderId") String orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return PREFIX + "orderEvaluate.html";
    }

    @RequestMapping("/list/{orderId}")
    @ResponseBody
    public Object list(@PathVariable String orderId, OrderEvaluateEntity orderEvaluate) {
        if (StringUtils.isNotBlank(orderId)) {
            orderEvaluate.setOrderId(orderId);
        }
        return new PageInfo<>(orderEvaluateService.getOrderEvaluateList(orderEvaluate));
    }


    /**
     * 删除订单评价管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String orderEvaluateId) {
        orderEvaluateService.deleteById(orderEvaluateId);
        return SUCCESS_TIP;
    }

}
