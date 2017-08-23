/**
 * 简系列目录管理初始化
 */
var QrCatalog = {
    id: "QrCatalogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrCatalog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '目录名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '二维码', field: 'qrCode', visible: true, align: 'center', valign: 'middle'},
        {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle',formatter:typeFormatter},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
        {title: '修改时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle'},
        {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
QrCatalog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrCatalog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加简系列目录
 */
QrCatalog.openAddQrCatalog = function () {
    var index = layer.open({
        type: 2,
        title: '添加简系列目录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/simple/qrCatalog/qrCatalog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看简系列目录详情
 */
QrCatalog.openQrCatalogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '简系列目录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/simple/qrCatalog/qrCatalog_update/' + QrCatalog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除简系列目录
 */
QrCatalog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/simple/qrCatalog/delete", function (data) {
            Feng.success("删除成功!");
            QrCatalog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrCatalogId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询简系列目录列表
 */
QrCatalog.search = function () {
    var queryData = {};
    queryData['title'] = $("#name").val();
    queryData['type'] = $("#type").val();
    QrCatalog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = QrCatalog.initColumn();
    var table = new BSTable(QrCatalog.id, "/simple/qrCatalog/list", defaultColunms);
    table.setPaginationType("server");
    QrCatalog.table = table.init();
});
function typeFormatter(value,row,index) {
    if(value == 1){
        return '<span class="label label-info">视频</span>';
    }else if(value == 2){
        return '<span class="label label-success">写作</span>';
    }else{
        return '<span class="label label-danger">未识别状态</span>';
    }
}