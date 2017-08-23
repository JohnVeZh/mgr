package cn.sparke.modules.orders.orderEvaluate.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.orders.orderEvaluate.mapper.OrderEvaluateMapper;
import cn.sparke.modules.orders.orderEvaluate.entity.OrderEvaluateEntity;

/**
 * 订单评价管理Dao
 *
 * @author spark
 * @Date 2017-08-03 09:53:15
 */
@Service
public class OrderEvaluateService{
    @Autowired
    private OrderEvaluateMapper orderEvaluateMapper;


    public OrderEvaluateEntity get(OrderEvaluateEntity orderEvaluate){
        return orderEvaluateMapper.get(orderEvaluate);
    }

    public OrderEvaluateEntity getById(String id){
        return orderEvaluateMapper.getById(id);
    }

    public Page<OrderEvaluateEntity> findList(OrderEvaluateEntity orderEvaluate){
       return orderEvaluateMapper.findList(orderEvaluate);
    }

    public Page<OrderEvaluateEntity> getOrderEvaluateList(OrderEvaluateEntity orderEvaluate) {
        return orderEvaluateMapper.getOrderEvaluateList(orderEvaluate);
    }

    public void deleteById(String id){
      orderEvaluateMapper.deleteById(id);
    }

}
