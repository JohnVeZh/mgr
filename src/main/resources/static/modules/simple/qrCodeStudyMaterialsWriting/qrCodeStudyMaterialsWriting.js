/**
 * 简系列写作翻译阅读管理初始化
 */
var QrCodeStudyMaterialsWriting = {
    id: "QrCodeStudyMaterialsWritingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrCodeStudyMaterialsWriting.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
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
QrCodeStudyMaterialsWriting.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrCodeStudyMaterialsWriting.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加简系列写作翻译阅读
 */
QrCodeStudyMaterialsWriting.openAddQrCodeStudyMaterialsWriting = function () {
    var index = layer.open({
        type: 2,
        title: '添加简系列写作翻译阅读',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/simple/qrCodeStudyMaterialsWriting/qrCodeStudyMaterialsWriting_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看简系列写作翻译阅读详情
 */
QrCodeStudyMaterialsWriting.openQrCodeStudyMaterialsWritingDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '简系列写作翻译阅读详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/simple/qrCodeStudyMaterialsWriting/qrCodeStudyMaterialsWriting_update/' + QrCodeStudyMaterialsWriting.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除简系列写作翻译阅读
 */
QrCodeStudyMaterialsWriting.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/simple/qrCodeStudyMaterialsWriting/delete", function (data) {
            Feng.success("删除成功!");
            QrCodeStudyMaterialsWriting.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrCodeStudyMaterialsWritingId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询简系列写作翻译阅读列表
 */
QrCodeStudyMaterialsWriting.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['type'] = $("#type").val();
    QrCodeStudyMaterialsWriting.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = QrCodeStudyMaterialsWriting.initColumn();
    var table = new BSTable(QrCodeStudyMaterialsWriting.id, "/simple/qrCodeStudyMaterialsWriting/list", defaultColunms);
    table.setPaginationType("server");
    QrCodeStudyMaterialsWriting.table = table.init();
});
function typeFormatter(value,row,index) {
    if(value == 0){
        return '<span class="label label-info">写作</span>';
    }else if(value == 1){
        return '<span class="label label-info">翻译</span>';
    }else if(value == 2){
        return '<span class="label label-info">阅读</span>';
    }else{
        return '<span class="label label-danger">未识别类别</span>';
    }
}