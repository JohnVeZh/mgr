package cn.sparke.modules.orders.order.entity;

import cn.sparke.modules.orders.utils.annotation.ExcelField;

/**
 * 订单物流单号导入实体
 *
 * @author spark
 * @Date 2017-07-19 09:54:57
 */
public class ExcelOrderLogisticsEntity {

    @ExcelField(title = "交易号", order = 1)
    private String orderTradeNo;
    @ExcelField(title = "快递公司", order = 2)
    private String expressName;
    @ExcelField(title = "快递单号", order = 3)
    private String logisticsCode;

    private int expressCount;

    public String getOrderTradeNo() {
        return orderTradeNo;
    }

    public void setOrderTradeNo(String orderTradeNo) {
        this.orderTradeNo = orderTradeNo;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public int getExpressCount() {
        return expressCount;
    }

    public void setExpressCount(int expressCount) {
        this.expressCount = expressCount;
    }
}
