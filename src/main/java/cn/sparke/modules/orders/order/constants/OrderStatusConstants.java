package cn.sparke.modules.orders.order.constants;

/**
 * Created by yangye on 2017-07-27.
 */
public interface OrderStatusConstants {
    // 1. 待支付 2.待发货 3.待收货 4.待评论 5.已完成 6.已取消
    Integer NOT_PAY = 1;
    Integer NOT_DELIVER = 2;
    Integer NOT_RECEIVED = 3;
    Integer NOT_COMMENT = 4;
    Integer FINISHED = 5;
    Integer CANCELED = 6;
}
