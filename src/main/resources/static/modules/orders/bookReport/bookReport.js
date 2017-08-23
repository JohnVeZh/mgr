/**
 * 订单管理初始化
 */
var BookReport = {
    id: "BookReportTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BookReport.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '图书图片-图书名称', field: 'orderDetailsList', align: 'left', valign: 'middle', sortable: true, width: '230px', height: '86px', formatter: function(value, row, index) {
            if(value != null && value !='undefined') {
                var html = '';
                html += '<table border="0">';
                value.map(function(i) {
                    html += '<tr>';
                    html += '<td style="width: 40%;"><div style="width: 85px; height: 85px; clear: left; float: left;"><img src="' + i['productImg'] + '" width="82px" height="82px" style="border:1px solid #ccc;padding:3px;"></div></td>';
                    html += '<td><font color="#000000" style="font-weight:bold; padding-top: 5px;">' + i['productName'] + '</font></td>';
                    html += '</tr>';
                })
                html += '</table>';
                return html;
            }else{
                return '暂无图片';
            }
        }},
        {title: '订单号', field: 'orderCode', align: 'left', valign: 'middle', sortable: true, width: '160px'},
        {title: '数量', field: 'productNum', align: 'center', valign: 'middle', sortable: true},
        {title: '交易号', field: 'orderTradeNo', align: 'left', valign: 'middle', sortable: true, width: '160px', formatter: function (value, row, index) {
            return '<font color="#FF0000">'+value+'</font>';
        }},
        {title: '订单状态', field: 'orderStatus', align: 'center', valign: 'middle', sortable: true, width: '50px', formatter: function(value, row, index){
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
        {title: '商品价格', field: 'productPrice', align: 'right', valign: 'middle', sortable: true, width: '60px'},
        {title: '订单总额', field: 'orderPrice', align: 'right', valign: 'middle', sortable: true, width: '60px'},
        {title: '实付总额', field: 'payPrice', align: 'right', valign: 'middle', sortable: true, width: '60px'},
        {title: '用户昵称', field: 'user.nickname', align: 'left', valign: 'middle', sortable: true, width: '60px'},
        {title: '收货人', field: 'orderLogistics.receiver', align: 'left', valign: 'middle', sortable: true, width: '60px',},
        {title: '收货电话', field: 'orderLogistics.receiveMobile', align: 'left', valign: 'middle', sortable: true, width: '60px',},
        /*{title: '省-市-区/县', field: 'orderLogistics.provinceCityCn', align: 'left', valign: 'middle', sortable: true, width: '100px', formatter: function(value, row, index) {
            return '<span title="' + value + '">' + (value.length > 15 ? value.substring(0, 15) + '...' : value) + '</span>';
        }},*/
        {title: '详细地址', field: 'orderLogistics.receiveAddress', align: 'left', valign: 'middle', sortable: true},
        {title: '下单时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true, width: '140px'},
        {title: '支付时间', field: 'payTime', align: 'center', valign: 'middle', sortable: true, width: '140px'}
        // {title: '操作', align: 'left', valign: 'middle', formatter: rowActionFormatter, width: '160px'}
    ];
};

/**
 * 检查是否选中
 */
BookReport.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BookReport.seItem = selected[0];
        return true;
    }
};

/**
 * 检查是否选中，评价
 */
BookReport.checkEvaluate = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BookReport.seItem = selected[0];
        if(BookReport.seItem.isEvaluate == "1") {
            return true;
        }
        Feng.info("此订单用户未发表评价！");
        return false;
    }
};

/**
 * 订单编辑
 */
BookReport.update = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2, title: '订单编辑', area: ['1024px', '500px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/orders/bookReport/update/' + BookReport.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 订单详情
 */
BookReport.details = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2, title: '订单详情', area: ['1024px', '600px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/orders/bookReport/details/' + BookReport.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 订单评价
 */
BookReport.evaluate = function () {
    if (this.checkEvaluate()) {
        var index = layer.open({
            type: 2, title: '订单评价管理', area: ['1024px', '600px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/orders/orderEvaluate/' + BookReport.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 修改订单状态
 */
BookReport.updateStatus = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2, title: '修改订单状态', area: ['800px', '320px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/orders/bookReport/updateStatus/' + BookReport.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 设置查询条件
 */
BookReport.search = function () {
    var queryData = {};
    queryData['qOrderCode'] = $("#qOrderCode").val();
    queryData['qPhone'] = $("#qPhone").val();
    queryData['qReceiver'] = $("#qReceiver").val();
    queryData['qOrderStatus'] = $("#qOrderStatus").val();
    queryData['qRefundStatus'] = $("#qRefundStatus").val();
    queryData['qStartTime'] = $("#qStartTime").val();
    queryData['qEndTime'] = $("#qEndTime").val();
    BookReport.table.refresh({query: queryData});
}

BookReport.resetSearch = function () {
    $("#qOrderCode").val("");
    $("#qPhone").val("");
    $("#qReceiver").val("");
    $("#qOrderStatus").val("");
    $("#qRefundStatus").val("");
    $("#qStartTime").val("");
    $("#qEndTime").val("");
    BookReport.search();
}

$(function () {
    var defaultColunms = BookReport.initColumn();
    var table = new BSTable(BookReport.id, "/orders/bookReport/list", defaultColunms);
    table.setPaginationType("server");
    BookReport.table = table.init();
});
