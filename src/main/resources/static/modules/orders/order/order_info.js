/**
 * 初始化订单详情对话框
 */
var OrderInfoDlg = {
    orderInfoData : {},
    orderUpdateValidateFields: {
        orderPrice: {
            validators: {
                notEmpty: {message: '订单金额不能为空'},
                numeric: {message: '订单金额只能输入数字'},
                regexp: {
                    regexp: /^\d+(\.\d{2})?$/,
                    message: '请输入正确订单金额'
                }

            }
        }
    },
    orderDeliverValidateFields: {
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
OrderInfoDlg.clearData = function() {
    this.orderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderInfoDlg.set = function(key, val) {
    this.orderInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrderInfoDlg.close = function() {
    parent.layer.close(window.parent.Order.layerIndex);
}

/**
 * 收集数据
 */
OrderInfoDlg.collectData = function() {
    this.set('id').set("uId").set("olId").set("zipcode").set("receiver").set("receiveMobile").set("orderPrice").set("postagePrice").set("receiveAddress").set("remarks");
}

OrderInfoDlg.orderEditValidate = function () {
    $('#orderEditInfoForm').data("bootstrapValidator").resetForm();
    $('#orderEditInfoForm').bootstrapValidator('validate');
    return $("#orderEditInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 提交修改
 */
OrderInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    if (!this.orderEditValidate()) {
        return;
    }
    // 提交订单编辑信息
    var ajax = new $ax(Feng.ctxPath + "/orders/order/updateSave", function(data){
        Feng.success("修改成功!");
        window.parent.Order.table.refresh();
        OrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderInfoData);
    ajax.start();
}


/**
 * 删除订单评价
 */
OrderInfoDlg.deleteOrderEvaluate = function (orderId, evaluateId) {
    var ajax = new $ax(
        Feng.ctxPath + "/orders/order/order_evaluate_delete", function (data) {
        }, function (data) {
    });
    ajax.set("orderId", orderId);
    ajax.set("evaluateId", evaluateId);
    ajax.start();
};

/**
 * 收集-订单发货数据
 */
OrderInfoDlg.collectOrderDeliverData = function() {
    this.set('id').set("olId").set("expressCode").set("logisticsCode");
}
/**
 * 验证
 * @returns {*|jQuery}
 */
OrderInfoDlg.orderDeliverValidate = function () {
    $('#orderDeliverInfoForm').data("bootstrapValidator").resetForm();
    $('#orderDeliverInfoForm').bootstrapValidator('validate');
    return $("#orderDeliverInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 提交-订单发货信息
 */
OrderInfoDlg.orderDeliverSubmit = function () {
    this.clearData();
    this.collectOrderDeliverData();
    if (!this.orderDeliverValidate()) {
        return;
    }
    // 提交信息
    var ajax = new $ax(Feng.ctxPath + "/orders/order/deliverUpdate", function(data){
        Feng.success("发货成功!");
        window.parent.Order.table.refresh();
        OrderInfoDlg.close();
    },function(data){
        Feng.error("发货失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderInfoData);
    ajax.start();
};

/**
 * 收集数据
 */
OrderInfoDlg.collectOrderStatusData = function() {
    this.set('id').set("orderStatus");
}

/**
 * 提交更新订单状态信息
 */
OrderInfoDlg.updateOrderStatusSubmit = function() {
    this.clearData();
    this.collectOrderStatusData();
    /*if (!this.orderEditValidate()) {
        return;
    }*/
    // 提交订单状态信息
    var ajax = new $ax(Feng.ctxPath + "/orders/order/updateStatusSave", function(data){
        Feng.success("修改成功!");
        window.parent.Order.table.refresh();
        OrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderInfoData);
    ajax.start();
}
/**
 * 导出订单Excel
 */
OrderInfoDlg.exportOrderExcel = function () {
    excelForm.action = Feng.ctxPath + '/orders/order/exportOrderExcel';
    excelForm.submit();
};
/**
 * 导出网课Excel订单
 */
OrderInfoDlg.exportCourseOrder = function () {
    $("#eProductType").val(1);
    OrderInfoDlg.exportOrderExcel();
}
/**
 * 导出图书Excel订单
 */
OrderInfoDlg.exportBookOrder = function () {
    $("#eProductType").val(2);
    OrderInfoDlg.exportOrderExcel();
}


/**
 * 导入订单Excel
 */
OrderInfoDlg.importLogisticsSubmit = function () {
    var path = $("#excelFile").val();
    var extStart = path.lastIndexOf(".");
    var ext = path.substring(extStart, path.length).toUpperCase();
    if (ext.toLowerCase() != ".xlsx" && ext.toLowerCase() != ".xls") {
        alert("请上传正确格式文件");
        return;
    }
    $("#logisticsInfoForm").mask("文件正在提交...");
    $.ajaxFileUpload({
        url: Feng.ctxPath + '/orders/order/importLogisticsSubmit',
        secureuri: false,
        fileElementId:'excelFile', //文件选择框的id属性
        dataType: 'json',
        cache:false,
        success: function (data, status) {
            // data = eval(data);
            if(data.code == 200) {
                Feng.success("导入成功！" + data.message);
                window.parent.Order.table.refresh();
                OrderInfoDlg.close();
            }else{
                Feng.error("导入失败！" + data.message + "!");
                OrderInfoDlg.close();
            }
        },
        error: function (data, status, e){
            alert(data.code + data.message);
            if(data.code == 200) {
                Feng.success("导入成功！" + data.message);
                window.parent.Order.table.refresh();
                OrderInfoDlg.close();
            }else{
                Feng.error("导入失败！" + data.message + "!");
                OrderInfoDlg.close();
            }
        }
    })
};


$(function() {
    Feng.initValidator("orderEditInfoForm", OrderInfoDlg.orderUpdateValidateFields);
    Feng.initValidator("orderDeliverInfoForm", OrderInfoDlg.orderDeliverValidateFields);

});
