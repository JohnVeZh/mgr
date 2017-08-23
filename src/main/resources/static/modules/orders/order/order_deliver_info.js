/**
 * 初始化订单发货信息对话框
 */
var OrderDeliverInfoDlg = {
    orderDeliverInfoData : {},
    validateFields: {
        logisticsCode: {
            validators: {
                notEmpty: {
                    message: '物流单号不能为空'
                }
            }
        }
    }

};

/**
 * 清除数据
 */
OrderDeliverInfoDlg.clearData = function () {
    this.orderDeliverInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderDeliverInfoDlg.set = function(key, val) {
    this.orderDeliverInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderDeliverInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrderDeliverInfoDlg.close = function() {
    parent.layer.close(window.parent.Order.layerIndex);
}

/**
 * 收集-订单发货数据
 */
OrderDeliverInfoDlg.collectOrderDeliverData = function() {
    this.set('oId').set("olId").set("expressId").set("logisticsCode");
}


OrderDeliverInfoDlg.validate = function () {
    $('#orderDeliverInfoForm').data("bootstrapValidator").resetForm();
    $('#orderDeliverInfoForm').bootstrapValidator('validate');
    return $("#orderDeliverInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交-订单发货信息
 */
OrderDeliverInfoDlg.orderDeliverSubmit = function () {
    this.clearData();
    this.collectOrderDeliverData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/orders/order/order_deliver_update", function(data){
        Feng.success("修改成功!");
        window.parent.Order.table.refresh();
        OrderDeliverInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderDeliverInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("orderDeliverInfoForm", OrderDeliverInfoDlg.validateFields);
});
