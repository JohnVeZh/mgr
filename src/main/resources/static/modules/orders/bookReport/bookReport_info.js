/**
 * 初始化订单详情对话框
 */
var BookReportInfoDlg = {
    bookReportInfoData : {},
    validateFields: {
        orderPrice: {
            validators: {
                notEmpty: {message: '订单金额不能为空'},
                numeric: {message: '订单金额只能输入数字'},
                regexp: {
                    regexp: /^\d+(\.\d{2})?$/,
                    message: '请输入正确订单金额'
                }

            }
        },
        postagePrice: {
            validators: {
                // notEmpty: {message: '订单金额不能为空'},
                numeric: {message: '邮费金额只能输入数字'},
                regexp: {
                    regexp: /^\d+(\.\d{2})?$/,
                    message: '请输入正确邮费金额'
                }

            }
        }
    }
};

/**
 * 清除数据
 */
BookReportInfoDlg.clearData = function() {
    this.bookReportInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookReportInfoDlg.set = function(key, val) {
    this.bookReportInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookReportInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BookReportInfoDlg.close = function() {
    parent.layer.close(window.parent.BookReport.layerIndex);
}

/**
 * 收集数据
 */
BookReportInfoDlg.collectOrderUpdateData = function() {
    this.set('id').set("uId").set("olId").set("zipcode").set("receiver").set("receiveMobile").set("orderPrice").set("postagePrice").set("receiveAddress").set("remarks");
}

/**
 * 收集数据
 */
BookReportInfoDlg.collectOrderStatusData = function() {
    this.set('id').set("orderStatus");
}

/**
 * 校验订单更新数据
 * @returns {*|jQuery}
 */
BookReportInfoDlg.validateUpdate = function () {
    $('#bookReportUpdateInfoForm').data("bootstrapValidator").resetForm();
    $('#bookReportUpdateInfoForm').bootstrapValidator('validate');
    return $("#bookReportUpdateInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交修改
 */
BookReportInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectOrderUpdateData();
    if (!this.validateUpdate()) {
        return;
    }
    // 提交订单编辑信息
    var ajax = new $ax(Feng.ctxPath + "/orders/bookReport/updateSave", function(data){
        Feng.success("修改成功!");
        window.parent.BookReport.table.refresh();
        BookReportInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookReportInfoData);
    ajax.start();
}


/**
 * 删除订单评价
 */
BookReportInfoDlg.deleteEvaluate = function (orderId, evaluateId) {
    var ajax = new $ax(Feng.ctxPath + "/orders/orderEvaluate/delete", function (data) {
    }, function (data) {
    });
    ajax.set("orderId", orderId);
    ajax.set("evaluateId", evaluateId);
    ajax.start();
};

/**
 * 提交-更新订单状态信息
 */
BookReportInfoDlg.updateOrderStatusSubmit = function() {
    this.clearData();
    this.collectOrderStatusData();
    /*if (!this.orderEditValidate()) {
     return;
     }*/
    // 提交订单状态信息
    var ajax = new $ax(Feng.ctxPath + "/orders/bookReport/updateStatusSave", function(data){
        Feng.success("修改成功!");
        window.parent.BookReport.table.refresh();
        BookReportInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });

    ajax.set(this.bookReportInfoData);
    ajax.start();
}


/**
 * 收集-订单发货数据
 */
BookReportInfoDlg.collectOrderDeliverData = function() {
    this.set('id').set("olId").set("expressId").set("logisticsCode");
}

/**
 * 提交-订单发货信息
 */
BookReportInfoDlg.orderDeliverSubmit = function () {
    this.clearData();
    this.collectOrderDeliverData();
    // 提交信息
    var ajax = new $ax(Feng.ctxPath + "/orders/bookReport/update", function(data){
        Feng.success("修改成功!");
        window.parent.BookReport.table.refresh();
        BookReportInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookReportInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("bookReportUpdateInfoForm", BookReportInfoDlg.validateFields);
});
