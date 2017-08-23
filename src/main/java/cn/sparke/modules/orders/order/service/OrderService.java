package cn.sparke.modules.orders.order.service;

import cn.sparke.modules.orders.order.constants.OrderStatusConstants;
import cn.sparke.modules.orders.order.constants.ProductTypeConstants;
import cn.sparke.modules.orders.order.entity.*;
import cn.sparke.modules.orders.order.mapper.OrderMapper;
import cn.sparke.modules.orders.utils.BizUtil;
import cn.sparke.modules.orders.utils.DateUtils;
import cn.sparke.modules.orders.utils.ExcelUtils;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 订单Dao
 *
 * @author spark
 * @Date 2017-07-18 19:32:38
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public Page<OrderEntity> findList(OrderEntity orderEntity) {
        Page<OrderEntity> orderList = orderMapper.findList(orderEntity);
        orderList.forEach(order -> {
            order.setUser(orderMapper.getUserByUserId(order.getUserId()));
            order.setOrderLogistics(orderMapper.getOrderLogisticsByOrderId(order.getId()));
            System.out.println("userId: " + order.getUserId() + ", orderId: " + order.getId());
        });
        return orderList;
    }


    public OrderEntity getOrderUpdateInfoById(String orderId) {
        return orderMapper.getOrderUpdateInfoById(orderId);
    }

    public Integer update(Map<String, Object> paramMap) {
        if (paramMap.get("olId") != null && StringUtils.isNotBlank(paramMap.get("olId").toString())) {
            return orderMapper.update(paramMap) + orderMapper.updateOrderLogistics(paramMap);
        } else {
            return orderMapper.update(paramMap);
        }
    }

    public int cancelOrder(String orderId) {
        return orderMapper.cancelOrder(orderId);
    }

    public OrderEntity getOrderDetailsInfoById(String orderId) {
        return orderMapper.getOrderDetailsInfoById(orderId);
    }

    public List<OrderEvaluateEntity> getOrderEvaluateList(String orderId) {
        return orderMapper.getOrderEvaluateList(orderId);
    }

    public int deleteOrderEvaluateById(String evaluateId) {
        return orderMapper.deleteOrderEvaluateById(evaluateId);
    }


    public List<ExpressEntity> getExpressList() {
        return orderMapper.getExpressList();
    }

    public OrderEntity getOrderLogisticsById(String orderId) {
        return orderMapper.getOrderLogisticsById(orderId);
    }

    public Integer updateOrderDeliver(Map<String, Object> paramMap) {
        // 1、更新订单发货状态；2、更新物流发货信息；
        return orderMapper.updateOrderDeliverInfo(paramMap) + orderMapper.updateOrderLogisticsInfo(paramMap);
    }

    public OrderEntity getOrderStatusInfoById(String orderId) {
        return orderMapper.getOrderStatusInfoById(orderId);
    }

    public Integer updateOrderStatus(Map<String, Object> paramMap) {
        return orderMapper.updateOrderStatus(paramMap);
    }

    public List<ProductEntity> getProductListByType(Integer type) {
        return orderMapper.getProductListByType(type);
    }

    public void exportOrderExcel(Map<String, Object> paramMap, HttpServletResponse response) throws IOException {
        Integer eProductType = Integer.parseInt(paramMap.get("eProductType").toString());
        String eProductTypeStr = (eProductType == ProductTypeConstants.NETWORK_COURSE ? "网课订单" : "图书订单");
        String fileName = new String((eProductTypeStr).getBytes("UTF-8"), "iso8859-1") +  "-" + DateUtils.getCurrentTimestampStr("yyyyMMddHHmm") + ".xlsx";
        if (StringUtils.isBlank(paramMap.get("eProductId").toString()) && StringUtils.isBlank(paramMap.get("eStartTime").toString()) && StringUtils.isBlank(paramMap.get("eOrderStatus").toString()) && StringUtils.isBlank(paramMap.get("eSectionCode").toString()) && StringUtils.isBlank(paramMap.get("ePayType").toString()) && StringUtils.isBlank(paramMap.get("eFromType").toString())) {
            paramMap.put("eStartTime", DateUtils.getRecentMonthDateStr() + " 00:00:01");
        }
        System.out.println("fileName: " + fileName + ", eProductTypeStr: " + eProductTypeStr);

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setCharacterEncoding("utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            if(eProductType == ProductTypeConstants.NETWORK_COURSE) { // 网课订单
                List<ExcelOrderCourseEntity> courseOrderList = orderMapper.getExcelOrderCourseList(paramMap);
                ExcelUtils.getInstance().exportObjects2Excel(courseOrderList, ExcelOrderBookEntity.class, true, eProductTypeStr, true, outputStream);
            } else if (eProductType == ProductTypeConstants.BOOK) { // 图书订单
                List<ExcelOrderBookEntity> bookOrderList = orderMapper.getExcelOrderBookList(paramMap);
                ExcelUtils.getInstance().exportObjects2Excel(bookOrderList, ExcelOrderBookEntity.class, true, eProductTypeStr, true, outputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int importOrderLogistic(ExcelOrderLogisticsEntity orderLogistics) {
        // todo 导入物流信息，是不是只有订单状态为待发货时才允许导入物流订单？
        // 更新订单表订单发货状态
        orderMapper.updateOrderDeliverStatus(orderLogistics.getOrderTradeNo(), OrderStatusConstants.NOT_RECEIVED);
        // 更新物流表中物流信息
        return orderMapper.importOrderLogistic(orderLogistics);
    }

}
