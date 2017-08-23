/**
 * 优惠劵业务场景管理初始化
 */
var CouponGainScene = {
    id: "CouponGainSceneTable",	//表格id
    couponRuleId:null,
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
CouponGainScene.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '规则id', field: 'ruleId', visible: false, align: 'center', valign: 'middle'},
        {title: '规则名称', field: 'ruleName', align: 'center', valign: 'middle'},
        {title: '场景类型', field: 'type', align: 'center', valign: 'middle',formatter : function (e,column,index) {
            if(column.type == 1){
                return '<span class="label label-primary">商品</span>';
            }else if(column.type == 2){
                return '<span class="label label-success">优惠码</span>';
            }else if(column.type == 3){
                return '<span class="label label-info">活动</span>';
            }else if(column.type == 4){
                return '<span class="label label-warning">首页banner</span>';
            }
        }},
        {title: '场景id', field: 'contentId',  visible: false, align: 'center', valign: 'middle'},
        {title: '关联标题或名称', field: 'contentName',  align: 'center', valign: 'middle'},
        {title: '商品id', field: 'productId', visible: false,align: 'center', valign: 'middle'},
        {title: '商品名称', field: 'productName',  align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CouponGainScene.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CouponGainScene.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加优惠劵业务场景
 */
CouponGainScene.openAddCouponGainScene = function () {
    var index = layer.open({
        type: 2,
        title: '添加优惠劵业务场景',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/couponGainScene/'+CouponGainScene.couponRuleId+'/add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看优惠劵业务场景详情
 */
CouponGainScene.openCouponGainSceneDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '优惠劵业务场景详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/couponGainScene/couponGainScene_update/' + CouponGainScene.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除优惠劵业务场景
 */
CouponGainScene.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/couponGainScene/delete", function (data) {
            Feng.success("删除成功!");
            CouponGainScene.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("couponGainSceneId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询优惠劵业务场景列表
 */
CouponGainScene.search = function () {
    var queryData = {};
    queryData['type'] = $("#type").val();
    CouponGainScene.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CouponGainScene.initColumn();
    var table = new BSTable(CouponGainScene.id, "/couponGainScene/"+CouponGainScene.couponRuleId+"/list", defaultColunms);
    CouponGainScene.table = table.init();
});
