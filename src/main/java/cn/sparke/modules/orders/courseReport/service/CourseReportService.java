package cn.sparke.modules.orders.courseReport.service;

import cn.sparke.modules.orders.courseReport.entity.OrderEntity;
import cn.sparke.modules.orders.courseReport.entity.OrderEvaluateEntity;
import cn.sparke.modules.orders.courseReport.mapper.CourseReportMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class CourseReportService {

    @Autowired
    private CourseReportMapper courseReportMapper;

    public Page<OrderEntity> findList(OrderEntity order) {
        Page<OrderEntity> orderList = courseReportMapper.findList(order);
        orderList.forEach(orderEntity -> {
            orderEntity.setUser(courseReportMapper.getUserByUserId(orderEntity.getUserId()));  // 查询用户信息
            orderEntity.setOrderLogistics(courseReportMapper.getOrderLogisticsByOrderId(orderEntity.getId()));
            orderEntity.setOrderDetailsList(courseReportMapper.getOrderDetailsListByOrderId(orderEntity.getId())); // 查询订单详情信息

        });
        return orderList;
    }

    public OrderEntity getOrderUpdateInfoById(String detailsId) {
        return courseReportMapper.getOrderUpdateInfoById(detailsId);
    }

    public int update(Map<String, Object> paramMap) {
        if (paramMap.get("olId") != null && StringUtils.isNotBlank(paramMap.get("olId").toString())) {
            return courseReportMapper.update(paramMap) + courseReportMapper.updateOrderLogistics(paramMap);
        } else {
            return courseReportMapper.update(paramMap);
        }
    }

    public OrderEntity getOrderDetailsInfoById(String orderId) {
        return courseReportMapper.getOrderDetailsInfoById(orderId);
    }

    public List<OrderEvaluateEntity> getOrderEvaluateList(String orderId) {
        return courseReportMapper.getOrderEvaluateList(orderId);
    }

    public int deleteOrderEvaluateById(String evaluateId) {
        return courseReportMapper.deleteOrderEvaluateById(evaluateId);
    }

    public OrderEntity getOrderStatusInfoById(String orderId) {
        return courseReportMapper.getOrderStatusInfoById(orderId);
    }

    public int updateOrderStatus(Map<String, Object> paramMap) {
        return courseReportMapper.updateOrderStatus(paramMap);
    }


}
