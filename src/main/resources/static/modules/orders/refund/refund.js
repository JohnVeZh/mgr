/**
 * 退单管理初始化
 */
var Refund = {
    id: "RefundTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Refund.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '订单号', field: 'orderCode', align: 'center', valign: 'middle', sortable: true, width: '160px'},
        {title: '课程名称', field: 'productName', align: 'center', valign: 'middle', sortable: true, width: '250px'},
        {title: '订单类型', field: 'productType', align: 'center', valign: 'middle', sortable: true, width: '80px',formatter: function(value, row, index){
            switch(value) {
                case 1:
                    return '<span>网课</span>';
                case 2:
                    return '<span>图书</span>';
                default:
                    break;
            }
        }},
        {title: '购买人手机号', field: 'refundUser.phone', align: 'center', valign: 'middle', sortable: true, width: '160px'},
        {title: '订单状态', field: 'status', align: 'center', valign: 'middle', sortable: true, width: '80px',formatter: function(value, row, index){
            switch(value) {
                case 1:
                    return '<span class="label badge-warning">退款审核中</span>';
                case 2:
                    return '<span class="label label-primary">审核通过</span>';
                case 3:
                    return '<span class="label label-primary">拒绝退款</span>';
                case 4:
                    return '<span class="label label-primary">已完成退款</span>';
                default:
                    break;
            }
        }},
        {title: '收货状态', field: 'userReceiveStatus', align: 'center', valign: 'middle', sortable: true, width: '80px',formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label badge-warning">未收到货</span>';
                case 1:
                    return '<span class="label label-primary">已收到货</span>';
                default:
                    break;
            }
        }}, {title: '退款类型', field: 'type', align: 'center', valign: 'middle', sortable: true, width: '80px',formatter: function(value, row, index){
            switch(value) {
                case 1:
                    return '<span class="label badge-warning">仅退款</span>';
                case 2:
                    return '<span class="label label-primary">退款退货</span>';
                default:
                    break;
            }
        }},
        {title: '退款金额', field: 'money', align: 'center', valign: 'middle', sortable: true, width: '80px'},
        {title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true, width: '160px'},
        {title: '操作', align: 'center', valign: 'middle', formatter: rowActionFormatter}
    ];
};

function rowActionFormatter(value, row, index) {
    return [
        '<a class="vive" href="javascript:;" onclick="openEditRefund(\'' + row.id + '\')">退单信息</a>',
        '<a class="edit" href="javascript:;" onclick="openEditRefundStatus(\'' + row.id + '\')">退单审核</a>',
    ].join('&nbsp;&nbsp;');
}

/**
 * 检查是否选中
 */
Refund.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Refund.seItem = selected[0];
        return true;
    }
};


/**
 * 打开查看退单详情
 */
 function openEditRefund(id) {
    this.layerIndex = layer.open({
        type: 2, title: '退单详情', area: ['1024px', '700px'], fix: false, maxmin: true,
        content: Feng.ctxPath + '/orders/refund/refund_update/' + id
    });
};

/**
 * 删除退单
 */
Refund.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/orders/refund/delete", function (data) {
            Feng.success("删除成功!");
            Refund.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("refundId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询退单列表
 */
Refund.search = function () {
    var queryData = {};
    queryData['queryName'] = $("#queryName").val();
    queryData['queryOrderCode'] = $("#queryOrderCode").val();
    queryData['queryPhone'] = $("#queryPhone").val();
    queryData['queryStartTime'] = $("#queryStartTime").val();
    queryData['queryEndTime'] = $("#queryEndTime").val();
    queryData['QueryStatus'] = $("#QueryStatus").val();
    queryData['queryProductType'] = $("#queryProductType").val();
    Refund.table.refresh({query: queryData});
};

Refund.resetSearch = function () {
    $("#queryName").val("");
    $("#queryOrderCode").val("");
    $("#queryPhone").val("");
    $("#queryStartTime").val("");
    $("#queryEndTime").val("");
    $("#QueryStatus").val("");
    $("#queryProductType").val("");
    Refund.search();
}

$(function () {
    var defaultColunms = Refund.initColumn();
    var table = new BSTable(Refund.id, "/orders/refund/list", defaultColunms);
    table.setPaginationType("server");
    Refund.table = table.init();
});
