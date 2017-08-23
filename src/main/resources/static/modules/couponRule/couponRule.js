/**
 * 优惠劵规则管理初始化
 */
var CouponRule = {
    id: "CouponRuleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 检查是否选中
 */
CouponRule.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CouponRule.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加优惠劵规则
 */
CouponRule.openAddCouponRule = function () {
    var index = layer.open({
        type: 2,
        title: '添加优惠劵规则',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/couponRule/couponRule_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看优惠劵规则详情
 */
CouponRule.openCouponRuleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '优惠劵规则详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/couponRule/couponRule_update/' + CouponRule.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除优惠劵规则
 */
CouponRule.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/couponRule/delete", function (data) {
            Feng.success("删除成功!");
            CouponRule.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("couponRuleId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询优惠劵规则列表
 */
CouponRule.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['status'] = $("#status").val();
    CouponRule.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CouponRule.initColumn();
    var table = new BSTable(CouponRule.id, "/couponRule/list", defaultColunms);
    CouponRule.table = table.init();
});
