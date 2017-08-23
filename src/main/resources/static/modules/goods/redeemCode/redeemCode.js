/**
 * 网课兑换码管理初始化
 */
var RedeemCode = {
    id: "RedeemCodeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RedeemCode.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '兑换码', field: 'code', align: 'center', valign: 'middle',sortable:true},
        {title: '网课名称', field: 'ncName', align: 'center', valign: 'middle',sortable:true},
        {title: '用户', field: 'phone', align: 'center', valign: 'middle',sortable:true},
        {title: '网课类型', field: 'mold', align: 'center', valign: 'middle',sortable:true},
        {title: '兑换码状态', field: 'status', align: 'center', valign: 'middle',sortable:true,formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label label-primary">未使用</span>';
                case 1:
                    return '<span class="label label-danger">已使用</span>';
                default:
                    break;
            }
        }},
        {title: '是否可用', field: 'isEnable', align: 'center', valign: 'middle',sortable:true,formatter: function(value, row, index){
            if(row.status=='0'){
                switch(value) {
                    case 0:
                        return '<span class="label label-danger">否</span>';
                    case 1:
                        return '<span class="label label-primary">是</span>';
                    default:
                        break;
                }
            }
            return '';
        }},
        {title: '操作', field: '', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            if(row.status=='0') {
                return '<a href="javascript:void(0)" onclick="RedeemCode.updateIsEnable(\'' + row.id + '\')">可用状态</a>';
            }
            return '';
        }}
    ];
};

RedeemCode.updateIsEnable= function (id) {
    var index = layer.open({
        type: 2,
        title: '兑换码可用状态',
        area: ['380px', '260px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/redeemCode/redeemCode_update/'+id
    });
    this.layerIndex = index;
};


/*window.enableEvents = {
    'click .RoleOfA':function (e,value,row,index) {
        if(value == '1'){
            var ajax = new $ax(Feng.ctxPath + "/goods/redeemCode/enableOrNot/"+row.id, function (data) {
                Feng.success("禁用成功!");
                RedeemCode.table.refresh();
            },function (data) {
                Feng.error("失败!" + data.responseJSON.message + "!");
            });
            ajax.set("redeemCodeId",row.id);
            ajax.start();
        }
        if(value == '0'){
            var ajax = new $ax(Feng.ctxPath + "/goods/redeemCode/enableOrNot/"+row.id, function (data) {
                Feng.success("可用成功!");
                RedeemCode.table.refresh();
            },function (data) {
                Feng.error("失败!" + data.responseJSON.message + "!");
            });
            ajax.set("redeemCodeId",row.id);
            ajax.start();
        }
    }
}*/

function enableFormatter(value,row,index) {
    if(!row["status"]){
        switch (value) {
            case 0:
                return '<button id="btn_detail" type="button" class="RoleOfA btn-default bt-select">禁用</button>';
            case 1:
                return '<button id="btn_detail" type="button" class="RoleOfA btn-success bt-select">可用</button>';
            default:
                break;
        }
    }
    return "";
}

/**
 * 检查是否选中
 */
RedeemCode.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        RedeemCode.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加网课兑换码
 */
RedeemCode.openAddRedeemCode = function () {
    var index = layer.open({
        type: 2,
        title: '添加网课兑换码',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/redeemCode/redeemCode_add'
    });
    this.layerIndex = index;
};

/**
 * 重置搜索条件
 */
RedeemCode.resetSearch = function () {
    $("#code").val("");
    $("#ncName").val("");
    $("#status").val("");
    Book.search();
};

/**
 * 查询网课兑换码列表
 */
RedeemCode.search = function () {
    var queryData = {};
    queryData['code'] = $("#code").val();
    queryData['ncName'] = $("#ncName").val();
    queryData['status'] = $("#status").val();
    RedeemCode.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RedeemCode.initColumn();
    var table = new BSTable(RedeemCode.id, "/goods/redeemCode/list", defaultColunms);
    table.setPaginationType("server");
    RedeemCode.table = table.init();
});
