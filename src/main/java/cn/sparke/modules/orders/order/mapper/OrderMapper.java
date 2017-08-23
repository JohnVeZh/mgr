package cn.sparke.modules.orders.order.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.orders.order.entity.*;
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
public interface OrderMapper extends BaseMapper{

    Page<OrderEntity> findList(OrderEntity orderEntity);

    UserEntity getUserByUserId(@Param("userId") String userId);

    OrderLogisticsEntity getOrderLogisticsByOrderId(@Param("orderId") String orderId);

    OrderEntity getOrderUpdateInfoById(@Param("orderId") String orderId);


    int updateOrderLogistics(Map<String, Object> paramMap);

    int cancelOrder(@Param("orderId") String orderId);

    OrderEntity getOrderDetailsInfoById(@Param("orderId") String orderId);

    List<OrderEvaluateEntity> getOrderEvaluateList(@Param("orderId") String orderId);

    int deleteOrderEvaluateById(@Param("evaluateId") String evaluateId);

    List<ExpressEntity> getExpressList();

    OrderEntity getOrderLogisticsById(@Param("orderId") String orderId);

    int updateOrderDeliverInfo(Map<String, Object> paramMap);

    int updateOrderLogisticsInfo(Map<String, Object> paramMap);

    OrderEntity getOrderStatusInfoById(@Param("orderId") String orderId);

    int updateOrderStatus(Map<String, Object> paramMap);

    List<ProductEntity> getProductListByType(@Param("type") Integer type);

    List<ExcelOrderBookEntity> getExcelOrderBookList(Map<String, Object> paramMap);

    List<ExcelOrderCourseEntity> getExcelOrderCourseList(Map<String, Object> paramMap);

    int updateOrderDeliverStatus(@Param("orderTradeNo") String orderTradeNo,@Param("orderStatus") Integer orderStatus);

    int importOrderLogistic(ExcelOrderLogisticsEntity orderLogistic);
}
