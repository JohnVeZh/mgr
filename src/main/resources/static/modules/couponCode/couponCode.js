/**
 * 优惠码管理初始化
 */
var CouponCode = {
    id: "CouponCodeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 检查是否选中
 */
CouponCode.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CouponCode.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加优惠码
 */
CouponCode.openAddCouponCode = function () {
    var index = layer.open({
        type: 2,
        title: '添加优惠码',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/couponCode/couponCode_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

CouponCode.batchAddCouponCode = function () {
    var index = layer.open({
        type: 2,
        title: '批量添加优惠码',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/couponCode/batch_add'
    });
    this.layerIndex = index;
    layer.full(index);
};
/**
 * 打开查看优惠码详情
 */
CouponCode.openCouponCodeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '优惠码详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/couponCode/couponCode_update/' + CouponCode.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除优惠码
 */
CouponCode.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/couponCode/delete", function (data) {
            Feng.success("删除成功!");
            CouponCode.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("couponCodeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询优惠码列表
 */
CouponCode.search = function () {
    var queryData = {};
    queryData['code'] = $("#code").val();
    queryData['status'] = $("#status").val();
    CouponCode.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CouponCode.initColumn();
    var table = new BSTable(CouponCode.id, "/couponCode/list", defaultColunms);
    CouponCode.table = table.init();
});
