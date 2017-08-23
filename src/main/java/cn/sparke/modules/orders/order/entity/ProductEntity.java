package cn.sparke.modules.orders.order.entity;

import cn.sparke.core.common.entity.BaseEntity;

/**
 * 订单Entity
 *
 * @author spark
 * @Date 2017-08-05 09:47:28
 */
public class ProductEntity extends BaseEntity{
    //商品名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
