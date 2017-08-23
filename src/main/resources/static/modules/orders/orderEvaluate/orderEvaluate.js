/**
 * 订单评价管理管理初始化
 */
var OrderEvaluate = {
    id: "OrderEvaluateTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrderEvaluate.initColumn = function () {
    return [
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '商品图片', field: 'productImg', visible: true, align: 'center', valign: 'middle', formatter: function (value, row, index) {
            if(value != null && value !='undefined') {
                return '<div style="width: 85px; height: 85px; clear: left; float: left;"><img src="' + row['productImg'] + '" width="82px" height="82px" style="border:1px solid #ccc;padding:3px;"></div>'
            }
        }},
        {title: '商品名称', field: 'productName', visible: true, align: 'center', valign: 'middle'},
        {title: '讲课思路/讲师风格/课程板书', field: 'thinkingScore', visible: true, align: 'center', valign: 'middle', formatter: function(value, row, index){
            if(value != null && value !='undefined') {
                var html = '';
                html += '&nbsp;<span class="label label-info">' + row['thinkingScore'] + '</span>&nbsp;&nbsp;&nbsp;';
                html += '&nbsp;<span class="label label-primary">' + row['styleScore'] + '</span>&nbsp;&nbsp;&nbsp;';
                html += '&nbsp;<span class="label label-success">' + row['curriculumScore'] + '</span>&nbsp;&nbsp;&nbsp;';
                return html;
            }
        }},
        {title: '平均分', field: 'averageScore', visible: true, align: 'center', valign: 'middle', formatter: function(value, row, index){
            if(value != null && value !='undefined') {
                var html = '';
                html += '<span class="label badge-warning">' + value + '</span>';
                return html;
            }
        }},
        {title: '用户帐号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
        {title: '用户昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
        {title: '操作', field: 'mgr', visible: true, align: 'center', valign: 'middle',formatter: function(value,row,index) {
            return ' <a onclick="OrderEvaluate.deleteEvaluate(\''+row.id+'\')" title="删除"><span class="label label-default">删除</span></a>';
        }}
    ];
};

/**
 * 关闭此对话框
 */
OrderEvaluate.close = function() {
    parent.layer.close(window.parent.OrderEvaluate.layerIndex);
}

/**
 * 检查是否选中
 */
OrderEvaluate.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OrderEvaluate.seItem = selected[0];
        return true;
    }
};


/**
 * 删除订单评价管理
 */
OrderEvaluate.deleteEvaluate = function (evaluateId) {
    var ajax = new $ax(Feng.ctxPath + "/orders/orderEvaluate/delete", function (data) {
        Feng.success("删除成功!");
        OrderEvaluate.table.refresh();
    }, function (data) {
        Feng.error("删除失败!" + data.responseJSON.message + "!");
    });
    ajax.set("orderEvaluateId", evaluateId);
    ajax.start();
};

/**
 * 查询订单评价管理列表
 */
OrderEvaluate.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    OrderEvaluate.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OrderEvaluate.initColumn();
    var table = new BSTable(OrderEvaluate.id, "/orders/orderEvaluate/list/" + $("#orderId").val(), defaultColunms);
    table.setPaginationType("server");
    OrderEvaluate.table = table.init();
});
