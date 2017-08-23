package cn.sparke.modules.orders.bookReport.service;

import cn.sparke.modules.orders.bookReport.entity.OrderEntity;
import cn.sparke.modules.orders.bookReport.entity.OrderEvaluateEntity;
import cn.sparke.modules.orders.bookReport.mapper.BookReportMapper;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 订单Dao
 *
 * @author spark
 * @Date 2017-07-18 19:32:38
 */
@Service
public class BookReportService {

    @Autowired
    private BookReportMapper bookReportMapper;

    public Page<OrderEntity> findList(OrderEntity orderEntity) {
        Page<OrderEntity> orderList = bookReportMapper.findList(orderEntity);
        orderList.forEach(order -> {
            order.setUser(bookReportMapper.getUserByUserId(order.getUserId()));
            order.setOrderLogistics(bookReportMapper.getOrderLogisticsByOrderId(order.getId()));
            order.setOrderDetailsList(bookReportMapper.getOrderDetailsListByOrderId(order.getId()));
        });
        return orderList;
    }

    public OrderEntity getOrderUpdateInfoById(String detailsId) {
        return bookReportMapper.getOrderUpdateInfoById(detailsId);
    }

    public int update(Map<String, Object> paramMap) {
        if (paramMap.get("olId") != null && StringUtils.isNotBlank(paramMap.get("olId").toString())) {
            return bookReportMapper.update(paramMap) + bookReportMapper.updateOrderLogistics(paramMap);
        } else {
            return bookReportMapper.update(paramMap);
        }
    }

    public OrderEntity getOrderDetailsInfoById(String orderId) {
        return bookReportMapper.getOrderDetailsInfoById(orderId);
    }

    public OrderEntity getOrderStatusInfoById(String orderId) {
        return bookReportMapper.getOrderStatusInfoById(orderId);
    }

    public int updateOrderStatus(Map<String, Object> paramMap) {
        return bookReportMapper.updateOrderStatus(paramMap);
    }


}
