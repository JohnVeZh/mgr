package cn.sparke.modules.orders.order.controller;

import cn.sparke.core.common.constants.tips.FailureTip;
import cn.sparke.core.common.constants.tips.SuccessTip;
import cn.sparke.core.common.controller.BaseController;
import cn.sparke.core.modules.mybatis.bean.PageInfo;
import cn.sparke.modules.orders.order.constants.ProductTypeConstants;
import cn.sparke.modules.orders.order.entity.ExcelOrderLogisticsEntity;
import cn.sparke.modules.orders.order.entity.OrderEntity;
import cn.sparke.modules.orders.order.entity.OrderEvaluateEntity;
import cn.sparke.modules.orders.order.entity.ProductEntity;
import cn.sparke.modules.orders.order.service.OrderService;
import cn.sparke.modules.orders.utils.BizUtil;
import cn.sparke.modules.orders.utils.ExcelUtils;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 订单控制器
 *
 * @author spark
 * @Date 2017-07-18 19:32:38
 */
@Controller
@RequestMapping("/orders/order")
public class OrderController extends BaseController {

    private String PREFIX = "/orders/order/";

    @Autowired
    private OrderService orderService;

    /**
     * 跳转到订单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "order.html";
    }

    /**
     * 获取订单列表，分页
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(OrderEntity orderEntity) {
        Page orderList = orderService.findList(orderEntity);
        return new PageInfo<>(orderList);
    }

    /**
     * 跳转-订单修改
     */
    @RequestMapping("/update/{orderId}")
    public String orderUpdate(@PathVariable String orderId, Model model) {
        OrderEntity orderEntity = orderService.getOrderUpdateInfoById(orderId);
        model.addAttribute("order", orderEntity);
        return PREFIX + "order_update.html";
    }

    /**
     * 保存-修改订单
     */
    @RequestMapping("/updateSave")
    @ResponseBody
    public Object update(HttpServletRequest request) {
        if (orderService.update(BizUtil.getParameterMap(request)) > 0) {
            return super.SUCCESS_TIP;
        }
        return new FailureTip();
    }

    /**
     * 跳转-订单详情
     */
    @RequestMapping("/details/{orderId}")
    public String orderDetails(@PathVariable String orderId, Model model) {
        OrderEntity orderEntity = orderService.getOrderDetailsInfoById(orderId);
        model.addAttribute("order", orderEntity);
        return PREFIX + "order_details.html";
    }

    /**
     * 跳转-订单评价
     */
    @RequestMapping("/evaluate/{orderId}")
    public String orderEvaluate(@PathVariable String orderId, Model model) {
        List<OrderEvaluateEntity> orderEvaluateList = orderService.getOrderEvaluateList(orderId);
        model.addAttribute("orderEvaluateList", orderEvaluateList);
        return PREFIX + "order_evaluate.html";
    }

    /**
     * 删除-订单评价
     */
    @RequestMapping("/order_evaluate_delete")
    public String orderEvaluateDelete(@RequestParam("orderId") String orderId, @RequestParam("evaluateId") String evaluateId, Model model) {
        orderService.deleteOrderEvaluateById(evaluateId);
        List<OrderEvaluateEntity> orderEvaluateList = orderService.getOrderEvaluateList(orderId);
        model.addAttribute("orderEvaluateList", orderEvaluateList);
        return PREFIX + "order_evaluate.html";
    }

    /**
     * 跳转-订单发货
     */
    @RequestMapping("/deliver/{orderId}")
    public String orderDeliver(@PathVariable String orderId, Model model) {
        OrderEntity orderEntity = orderService.getOrderLogisticsById(orderId);
        model.addAttribute("order", orderEntity);
        model.addAttribute("expressList", orderService.getExpressList());
        return PREFIX + "order_deliver.html";
    }

    /**
     * 订单发货更新
     */
    @RequestMapping("/deliverUpdate")
    @ResponseBody
    public Object orderDeliverUpdate(HttpServletRequest request) {
        Map<String, Object> paramMap = BizUtil.getParameterMap(request);
        if (paramMap.get("expressCode") != null && orderService.updateOrderDeliver(paramMap) > 0) {
            return super.SUCCESS_TIP;
        }
        return new FailureTip();
    }

    /**
     * 取消订单
     */
    @RequestMapping(value = "/cancel")
    @ResponseBody
    public Object orderCancel(@RequestParam("orderId") String orderId) {
        if (StringUtils.isNotBlank(orderId) && orderService.cancelOrder(orderId) > 0) {
            return super.SUCCESS_TIP;
        }
        return new FailureTip();
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping("/updateStatus/{orderId}")
    public String updateOrderStatus(@PathVariable String orderId, Model model) {
        OrderEntity orderEntity = orderService.getOrderStatusInfoById(orderId);
        model.addAttribute("order", orderEntity);
        return PREFIX + "order_updateStatus.html";
    }

    /**
     * 修改订单状态-保存
     */
    @RequestMapping("/updateStatusSave")
    @ResponseBody
    public Object updateOrderStatusSave(HttpServletRequest request) {
        Map<String, Object> paramMap = BizUtil.getParameterMap(request);
        if (paramMap.get("orderStatus") != null && orderService.updateOrderStatus(paramMap) > 0) {
            return super.SUCCESS_TIP;
        }
        return new FailureTip();
    }

    /**
     * 跳转-导出图书订单Excel
     */
    @RequestMapping("/exportBook")
    public String exportBook(Model model) {
        model.addAttribute("productList", orderService.getProductListByType(ProductTypeConstants.BOOK));
        return PREFIX + "order_exportBook.html";
    }

    /**
     * 跳转-导出网课订单Excel
     */
    @RequestMapping("/exportCourse")
    public String exportCourse(Model model) {
        model.addAttribute("productList", orderService.getProductListByType(ProductTypeConstants.NETWORK_COURSE));
        return PREFIX + "order_exportCourse.html";
    }

    /**
     * 导出订单Excel
     */
    @RequestMapping("/exportOrderExcel")
    @ResponseBody
    public String exportOrderExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> paramMap = BizUtil.getParameterMap(request);
        if (paramMap.get("eProductType") != null) {
            orderService.exportOrderExcel(BizUtil.getParameterMap(request), response);
        }
        return null;
    }

    /**
     * 导入物流单号
     * @return
     */
    @RequestMapping("/importLogistics")
    public String importLogistics() {
        return PREFIX + "order_importLogistics.html";
    }

    @RequestMapping("/importLogisticsSubmit")
    @ResponseBody
    public Object expressImport(@RequestParam(value = "excelFile") MultipartFile excelFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (excelFile.isEmpty()) {  // 这里的file就是前台页面的name
            return new FailureTip("请选择需要导入文件！");
        }
        int successCount = 0;
        InputStream inputStream = excelFile.getInputStream();
        List<ExcelOrderLogisticsEntity> logisticsList = ExcelUtils.getInstance().readExcel2Objects(inputStream, ExcelOrderLogisticsEntity.class, 0, Integer.MAX_VALUE, 0);
        for (ExcelOrderLogisticsEntity logistics : logisticsList) {
            successCount += orderService.importOrderLogistic(logistics);
        }
        if (logisticsList == null || logisticsList.size() == 0 || successCount == 0) {
            return new FailureTip("未能导入物流单号！");
        }
        return new SuccessTip("成功导入：" + successCount + " 条物流单号！");
    }

}
