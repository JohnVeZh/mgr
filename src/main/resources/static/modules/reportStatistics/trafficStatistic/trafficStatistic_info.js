/**
 * 初始化流量统计详情对话框
 */
var TrafficStatisticInfoDlg = {
    trafficStatisticInfoData : {}
};

/**
 * 清除数据
 */
TrafficStatisticInfoDlg.clearData = function() {
    this.trafficStatisticInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TrafficStatisticInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.trafficStatisticInfoData[key] = val;
    }else{
        this.trafficStatisticInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TrafficStatisticInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TrafficStatisticInfoDlg.close = function() {
    parent.layer.close(window.parent.TrafficStatistic.layerIndex);
}

/**
 * 收集数据
 */
TrafficStatisticInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
TrafficStatisticInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/trafficStatistic/add", function(data){
        Feng.success("添加成功!");
        window.parent.TrafficStatistic.table.refresh();
        TrafficStatisticInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.trafficStatisticInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TrafficStatisticInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/trafficStatistic/update", function(data){
        Feng.success("修改成功!");
        window.parent.TrafficStatistic.table.refresh();
        TrafficStatisticInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.trafficStatisticInfoData);
    ajax.start();
}

$(function() {

});
