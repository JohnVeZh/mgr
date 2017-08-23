/**
 * 流量统计管理初始化
 */
var TrafficStatistic = {
    id: "TrafficStatisticTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TrafficStatistic.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TrafficStatistic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TrafficStatistic.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加流量统计
 */
TrafficStatistic.openAddTrafficStatistic = function () {
    var index = layer.open({
        type: 2,
        title: '添加流量统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/trafficStatistic/trafficStatistic_add'
    });
    this.layerIndex = index;
};
/**
 * 点击添加流量统计
 */
TrafficStatistic.openChannel = function () {
    var index = layer.open({
        type: 2,
        title: '添加流量统计',
        area: ['1000px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/channel/'
    });
    this.layerIndex = index;
};
/**
 * 打开查看流量统计详情
 */
TrafficStatistic.openTrafficStatisticDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '流量统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/trafficStatistic/trafficStatistic_update/' + TrafficStatistic.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除流量统计
 */
TrafficStatistic.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/trafficStatistic/delete", function (data) {
            Feng.success("删除成功!");
            TrafficStatistic.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("trafficStatisticId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询流量统计列表
 */
TrafficStatistic.search = function () {
    $.ajax({
        type: "GET",
        url: "/trafficStatistic/table",
        data: {btTime:$("#btTime").val(), endTime:$("#endTime").val()},
        dataType: "json",
        success: function(data){
            $('#TrafficStatisticTable').empty();   //清空resText里面的所有内容
            $('#TrafficStatisticTable').html(data);
        }
    });
};

$(function () {
    var date = new Date();
    var time = date.getFullYear() +"-"+ (date.getMonth()+1)+"-"+date.getDate();
    $.ajax({
        type: "GET",
        url: "/trafficStatistic/table",
        data: {btTime:time, endTime:time},
        dataType: "json",
        success: function(data){
            $('#TrafficStatisticTable').empty();   //清空resText里面的所有内容
            $('#TrafficStatisticTable').html(data);
        }
    });
});
