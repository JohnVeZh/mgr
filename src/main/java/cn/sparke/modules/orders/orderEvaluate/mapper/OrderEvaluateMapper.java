package cn.sparke.modules.orders.orderEvaluate.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.orders.orderEvaluate.entity.OrderEvaluateEntity;
import com.github.pagehelper.Page;

/**
 * 订单评价管理Dao
 *
 * @author spark
 * @Date 2017-08-03 09:53:15
 */
public interface OrderEvaluateMapper extends BaseMapper<OrderEvaluateEntity>{

    Page<OrderEvaluateEntity> getOrderEvaluateList(OrderEvaluateEntity orderEvaluate);

}
