/**
 * 初始化订单详情对话框
 */
var CourseReportInfoDlg = {
    courseReportInfoData : {},
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
CourseReportInfoDlg.clearData = function() {
    this.courseReportInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CourseReportInfoDlg.set = function(key, val) {
    this.courseReportInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CourseReportInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CourseReportInfoDlg.close = function() {
    parent.layer.close(window.parent.CourseReport.layerIndex);
}

/**
 * 收集数据
 */
CourseReportInfoDlg.collectOrderUpdateData = function() {
    this.set('id').set("uId").set("olId").set("zipcode").set("receiver").set("receiveMobile").set("orderPrice").set("postagePrice").set("receiveAddress").set("remarks");
}

/**
 * 收集数据
 */
CourseReportInfoDlg.collectOrderStatusData = function() {
    this.set('id').set("orderStatus");
}

/**
 * 校验订单更新数据
 * @returns {*|jQuery}
 */
CourseReportInfoDlg.validateUpdate = function () {
    $('#courseReportUpdateInfoForm').data("bootstrapValidator").resetForm();
    $('#courseReportUpdateInfoForm').bootstrapValidator('validate');
    return $("#courseReportUpdateInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交修改
 */
CourseReportInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectOrderUpdateData();
    if (!this.validateUpdate()) {
        return;
    }
    // 提交订单编辑信息
    var ajax = new $ax(Feng.ctxPath + "/orders/courseReport/updateSave", function(data){
        Feng.success("修改成功!");
        window.parent.CourseReport.table.refresh();
        CourseReportInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.courseReportInfoData);
    ajax.start();
}


/**
 * 删除订单评价
 */
CourseReportInfoDlg.deleteOrderEvaluate = function (orderId, evaluateId) {
    var ajax = new $ax(Feng.ctxPath + "/orders/courseReport/deleteEvaluate", function (data) {
    }, function (data) {
    });
    ajax.set("orderId", orderId);
    ajax.set("evaluateId", evaluateId);
    ajax.start();
};

/**
 * 提交-更新订单状态信息
 */
CourseReportInfoDlg.updateOrderStatusSubmit = function() {
    this.clearData();
    this.collectOrderStatusData();
    /*if (!this.orderEditValidate()) {
     return;
     }*/
    // 提交订单状态信息
    var ajax = new $ax(Feng.ctxPath + "/orders/courseReport/updateStatusSave", function(data){
        Feng.success("修改成功!");
        window.parent.CourseReport.table.refresh();
        CourseReportInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });

    ajax.set(this.courseReportInfoData);
    ajax.start();
}


/**
 * 收集-订单发货数据
 */
CourseReportInfoDlg.collectOrderDeliverData = function() {
    this.set('id').set("olId").set("expressId").set("logisticsCode");
}

/**
 * 提交-订单发货信息
 */
CourseReportInfoDlg.orderDeliverSubmit = function () {
    this.clearData();
    this.collectOrderDeliverData();
    // 提交信息
    var ajax = new $ax(Feng.ctxPath + "/orders/courseReport/update", function(data){
        Feng.success("修改成功!");
        window.parent.CourseReport.table.refresh();
        CourseReportInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.courseReportInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("courseReportUpdateInfoForm", CourseReportInfoDlg.validateFields);
});
