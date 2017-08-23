/**
 * 订单管理初始化
 */
var Order = {
    id: "OrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Order.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '订单号', field: 'orderCode', align: 'left', valign: 'middle', sortable: true, width: '160px'},
        {title: '用户昵称', field: 'user.nickname', align: 'left', valign: 'middle', sortable: true},
        {title: '用户帐号', field: 'user.phone', align: 'left', valign: 'middle', sortable: true, width: '100px'},
        {title: '收货人', field: 'orderLogistics.receiver', align: 'left', valign: 'middle', sortable: true},
        // {title: '收货电话', field: 'orderLogistics.receiveMobile', align: 'left', valign: 'middle', sortable: true, width: '100px'},
        {title: '交易号', field: 'orderTradeNo', align: 'left', valign: 'middle', sortable: true, width: '160px', formatter: function (value, row, index) {
            return '<font color="#FF0000">'+value+'</font>'
        }},
        {title: '订单状态', field: 'orderStatus', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
            switch(value) {
                case 1:
                    return '<span class="label badge-warning">待支付</span>';
                case 2:
                    return '<span class="label label-primary">待发货</span>';
                case 3:
                    return '<span class="label label-success">待收货</span>';
                case 4:
                    return '<span class="label label-info">待评论</span>';
                case 5:
                    return '<span class="label label-white">已完成</span>';
                case 6:
                    return '<span class="label label-default">已取消</span>';
                default:
                    break;
            }
        }},
        {title: '订单金额', field: 'orderPrice', align: 'right', valign: 'middle', sortable: true, width: '80px'},
        {title: '订单来源', field: 'fromType', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<font color="#000000" style="font-weight:bold;">老数据</font>';
                case 1:
                    return '<font color="#009900" style="font-weight:bold;">Android</font>';
                case 2:
                    return '<font color="#FF0000" style="font-weight:bold;">iOS</font>';
                case 3:
                    return '<font color="#006699" style="font-weight:bold;">M站</font>';
                case 4:
                    return '<font color="#0000FF" style="font-weight:bold;">PC站</font>';
                case 5:
                    return '<font color="#CC99CC" style="font-weight:bold;">PC站</font>';
                default:
                    break;
            }
        }},
        {title: '实付金额', field: 'payPrice', align: 'right', valign: 'middle', sortable: true, width: '80px'},
        {title: '支付方式', field: 'payType', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
            switch(value) {
                case 1:
                    return '<font color="#0066CC" style="font-weight:bold;font-style:italic;">支付宝</font>';
                case 2:
                    return '<font color="#009900" style="font-weight:bold;font-style:italic;">微信</font>';
                case 3:
                    return '<font color="#990033" style="font-weight:bold;font-style:italic;">兑换码</font>';
                default:
                    break;
            }
        }},
        {title: '商品数', field: 'productNum', align: 'center', valign: 'middle', sortable: true, width: '50px'},
        {title: '下单时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true, width: '140px'},
        {title: '支付时间', field: 'payTime', align: 'center', valign: 'middle', sortable: true, width: '140px'}
    ];
};


/**
 * 检查是否选中
 */
Order.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Order.seItem = selected[0];
        return true;
    }
};

/**
 * 检查是否选中（发货状态）
 */
Order.checkDeliver = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Order.seItem = selected[0];
        if(Order.seItem.orderStatus == "2") {
            return true;
        }
        Feng.info("此订单不是待发货状态！");
        return false;
    }
};
/**
 * 检查是否选中（评价状态）
 */
Order.checkEvaluate = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Order.seItem = selected[0];
        if(Order.seItem.isEvaluate == "1") {
            return true;
        }
        Feng.info("此订单用户未发表评价！");
        return false;
    }
};

/**
 * 订单更新
 */
Order.openOrderUpdate = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2, title: '订单更新', area: ['1024px', '450px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/orders/order/update/' + Order.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 订单详情
 */
Order.openOrderDetails = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2, title: '订单详情', area: ['1024px', '750px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/orders/order/details/' + Order.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 订单评价
 */
Order.openOrderEvaluate = function () {
    if (this.checkEvaluate()) {
        var index = layer.open({
            type: 2, title: '订单评价管理', area: ['1024px', '600px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/orders/orderEvaluate/' + Order.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 订单发货
 */
Order.openOrderDeliver = function () {
    if (this.checkDeliver()) {
        var index = layer.open({
            type: 2, title: '订单发货', area: ['1024px', '450px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/orders/order/deliver/' + Order.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 检查是否选中（是否已取消）
 */
Order.checkCancel = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Order.seItem = selected[0];
        if(Order.seItem.orderStatus != "6") {
            return true;
        }
        Feng.info("此订单已是取消状态！");
        return false;
    }
};

/**
 * 取消订单
 */
Order.openOrderCancel = function () {
    if (this.checkCancel()) {
        if(!confirm("确定要取消此订单？")) return false;
        var ajax = new $ax(Feng.ctxPath + "/orders/order/cancel", function (data) {
            Feng.success("订单取消成功!");
            Order.table.refresh();
        }, function (data) {
            Feng.error("订单取消失败!" + data.responseJSON.message + "!");
        });
        ajax.set("orderId", Order.seItem.id);
        ajax.start();
    }
};
/**
 * 修改订单状态
 */
Order.openOrderUpdateStatus = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2, title: '修改订单状态', area: ['800px', '240px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/orders/order/updateStatus/' + Order.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 导出订单Excel
 */
Order.openExportExcel = function () {
    $("#eOrderCode").val($("#qOrderCode").val());
    $("#eStartTime").val($("#qStartTime").val());
    $("#eEndTime").val($("#qEndTime").val());
    $("#eOrderStatus").val($("#qOrderStatus").val());
    $("#eFromType").val($("#qFromType").val());
    $("#eReceiver").val($("#qReceiver").val());
    excelForm.action = Feng.ctxPath + '/orders/order/exportExcel';
    excelForm.submit();
};
/**
 * 导出网课Excel订单
 */
Order.openExportCourse = function () {
    var index = layer.open({
        type: 2, title: '导出网课订单', area: ['800px', '360px'], fix: false, maxmin: true,
        content: Feng.ctxPath + '/orders/order/exportCourse'
    });
    this.layerIndex = index;
}
/**
 * 导出图书Excel订单
 */
Order.openExportBook = function () {
    var index = layer.open({
        type: 2, title: '导出图书订单', area: ['800px', '360px'], fix: false, maxmin: true,
        content: Feng.ctxPath + '/orders/order/exportBook'
    });
    this.layerIndex = index;
}

/**
 * 导入订单物流单号
 */
Order.openImportLogistics = function () {
    var index = layer.open({
        type: 2, title: '物流单号导入', area: ['600px', '250px'], fix: false, maxmin: true,
        content: Feng.ctxPath + '/orders/order/importLogistics'
    });
    this.layerIndex = index;
}

/**
 * 查询订单列表
 */
Order.search = function () {
    var queryData = {};
    queryData['qOrderCode'] = $("#qOrderCode").val();
    queryData['qStartTime'] = $("#qStartTime").val();
    queryData['qEndTime'] = $("#qEndTime").val();
    queryData['qOrderStatus'] = $("#qOrderStatus").val();
    queryData['qFromType'] = $("#qFromType").val();
    queryData['qReceiver'] = $("#qReceiver").val();
    Order.table.refresh({query: queryData});
}

Order.resetSearch = function () {
    $("#qOrderCode").val("");
    $("#qStartTime").val("");
    $("#qEndTime").val("");
    $("#qOrderStatus").val("");
    $("#qFromType").val("");
    $("#qReceiver").val("");
    Order.search();
}

$(function () {
    var defaultColunms = Order.initColumn();
    var table = new BSTable(Order.id, "/orders/order/list", defaultColunms);
    table.setPaginationType("server");
    Order.table = table.init();
});
