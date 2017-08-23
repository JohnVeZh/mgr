/**
 * 初始化订单评价管理详情对话框
 */
var OrderEvaluateInfoDlg = {
    orderEvaluateInfoData : {}
};

/**
 * 清除数据
 */
OrderEvaluateInfoDlg.clearData = function() {
    this.orderEvaluateInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderEvaluateInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.orderEvaluateInfoData[key] = val;
    }else{
        this.orderEvaluateInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderEvaluateInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrderEvaluateInfoDlg.close = function() {
    parent.layer.close(window.parent.OrderEvaluate.layerIndex);
}

/**
 * 收集数据
 */
OrderEvaluateInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
OrderEvaluateInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/orders/orderEvaluate/add", function(data){
        Feng.success("添加成功!");
        window.parent.OrderEvaluate.table.refresh();
        OrderEvaluateInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderEvaluateInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OrderEvaluateInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/orders/orderEvaluate/update", function(data){
        Feng.success("修改成功!");
        window.parent.OrderEvaluate.table.refresh();
        OrderEvaluateInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderEvaluateInfoData);
    ajax.start();
}

$(function() {

});
