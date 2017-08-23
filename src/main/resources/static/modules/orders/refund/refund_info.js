/**
 * 初始化退单详情对话框
 */
var RefundInfoDlg = {
    refundInfoData : {}
};

/**
 * 清除数据
 */
RefundInfoDlg.clearData = function() {
    this.refundInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RefundInfoDlg.set = function(key, val) {
    this.refundInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RefundInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RefundInfoDlg.close = function() {
    parent.layer.close(window.parent.Refund.layerIndex);
}

/**
 * 收集数据
 */
RefundInfoDlg.collectData = function() {
    this.set('id').set('orderCode').set('productName').set('productType').set('phone').set('status').set('userReceiveStatus').set('type').set('money').set('createDate');
}

/**
 * 提交添加
 */
RefundInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/orders/refund/add", function(data){
        Feng.success("添加成功!");
        window.parent.Refund.table.refresh();
        RefundInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.refundInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RefundInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/orders/refund/update", function(data){
        Feng.success("修改成功!");
        window.parent.Refund.table.refresh();
        RefundInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.refundInfoData);
    ajax.start();
}

$(function() {

});
