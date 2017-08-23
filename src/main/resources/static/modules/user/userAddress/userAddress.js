/**
 * 收货地址管理初始化
 */
var UserAddress = {
    id: "UserAddressTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserAddress.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '收货人', field: 'receiver', visible: true, align: 'center', valign: 'middle'},
        {title: '电话', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserAddress.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserAddress.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加收货地址
 */
UserAddress.openAddUserAddress = function () {
    var index = layer.open({
        type: 2,
        title: '添加收货地址',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userAddress/userAddress_add?userId='+$("#userId").val()
    });
    this.layerIndex = index;
};

/**
 * 打开查看收货地址详情
 */
UserAddress.openUserAddressDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '收货地址详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userAddress/userAddress_update/' + UserAddress.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除收货地址
 */
UserAddress.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/userAddress/delete", function (data) {
            Feng.success("删除成功!");
            UserAddress.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userAddressId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询收货地址列表
 */
UserAddress.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    UserAddress.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UserAddress.initColumn();
    var table = new BSTable(UserAddress.id, "/userAddress/list?userId="+$("#userId").val(), defaultColunms);
    table.setPaginationType("client");
    // table.setHeight(600);
    UserAddress.table = table.init();
});
