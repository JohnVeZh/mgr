package cn.sparke.modules.orders.bookReport.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.orders.bookReport.entity.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 订单Dao
 *
 * @author spark
 * @Date 2017-07-18 19:32:38
 */
public interface BookReportMapper extends BaseMapper{

    Page<OrderEntity> findList(OrderEntity order);

    UserEntity getUserByUserId(@Param("userId") String userId);

    OrderLogisticsEntity getOrderLogisticsByOrderId(@Param("orderId") String orderId);

    List<OrderDetailsEntity> getOrderDetailsListByOrderId(@Param("orderId") String orderId);

    OrderEntity getOrderUpdateInfoById(@Param("orderId") String orderId);

    int update(Map<String, Object> paramMap);

    int updateOrderLogistics(Map<String, Object> paramMap);

    OrderEntity getOrderDetailsInfoById(@Param("orderId") String orderId);

    OrderEntity getOrderStatusInfoById(@Param("orderId") String orderId);

    int updateOrderStatus(Map<String, Object> paramMap);
}
