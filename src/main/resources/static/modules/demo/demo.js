/**
 * 例子管理初始化
 */
var Demo = {
    id: "DemoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Demo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Demo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Demo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加例子
 */
Demo.openAddDemo = function () {
    var index = layer.open({
        type: 2,
        title: '添加例子',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/demo/demo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看例子详情
 */
Demo.openDemoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '例子详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/demo/demo_update/' + Demo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除例子
 */
Demo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/demo/delete", function (data) {
            Feng.success("删除成功!");
            Demo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("demoId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询例子列表
 */
Demo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Demo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Demo.initColumn();
    var table = new BSTable(Demo.id, "/demo/list", defaultColunms);
    table.setPaginationType("client");
    Demo.table = table.init();
});
