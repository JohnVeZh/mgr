/**
 * 二维码试卷字幕听力管理初始化
 */
var QrCaptionListening = {
    id: "QrCaptionListeningTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrCaptionListening.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
QrCaptionListening.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrCaptionListening.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加二维码试卷字幕听力
 */
QrCaptionListening.openAddQrCaptionListening = function () {
    var index = layer.open({
        type: 2,
        title: '添加二维码试卷字幕听力',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/QrCaptionListening/QrCaptionListening_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码试卷字幕听力详情
 */
QrCaptionListening.openQrCaptionListeningDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码试卷字幕听力详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/QrCaptionListening/QrCaptionListening_update/' + QrCaptionListening.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除二维码试卷字幕听力
 */
QrCaptionListening.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/QrCaptionListening/delete", function (data) {
            Feng.success("删除成功!");
            QrCaptionListening.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("QrCaptionListeningId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询二维码试卷字幕听力列表
 */
QrCaptionListening.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    QrCaptionListening.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = QrCaptionListening.initColumn();
    var table = new BSTable(QrCaptionListening.id, "/QrCaptionListening/list", defaultColunms);
    table.setPaginationType("client");
    QrCaptionListening.table = table.init();
});
