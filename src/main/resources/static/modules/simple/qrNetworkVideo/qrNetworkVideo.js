/**
 * 简系列视频管理初始化
 */
var QrNetworkVideo = {
    id: "QrNetworkVideoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrNetworkVideo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '学段', field: 'sectionCodeName', visible: true, align: 'center', valign: 'middle'},
        {title: '视频名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '二维码', field: 'qrCode', visible: true, align: 'center', valign: 'middle'},
        {title: '视频ccid', field: 'ccId', visible: true, align: 'center', valign: 'middle'},
        {title: '图片', field: 'img', visible: true, align: 'center', valign: 'middle',formatter:imgFormatter},
        {title: '修改时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle'},
        {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
QrNetworkVideo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrNetworkVideo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加简系列视频
 */
QrNetworkVideo.openAddQrNetworkVideo = function () {
    var index = layer.open({
        type: 2,
        title: '添加简系列视频',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/simple/qrNetworkVideo/qrNetworkVideo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看简系列视频详情
 */
QrNetworkVideo.openQrNetworkVideoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '简系列视频详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/simple/qrNetworkVideo/qrNetworkVideo_update/' + QrNetworkVideo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除简系列视频
 */
QrNetworkVideo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/simple/qrNetworkVideo/delete", function (data) {
            Feng.success("删除成功!");
            QrNetworkVideo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrNetworkVideoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询简系列视频列表
 */
QrNetworkVideo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#name").val();
    QrNetworkVideo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = QrNetworkVideo.initColumn();
    var table = new BSTable(QrNetworkVideo.id, "/simple/qrNetworkVideo/list", defaultColunms);
    table.setPaginationType("server");
    QrNetworkVideo.table = table.init();


});
function imgFormatter(value,row,index) {
    if (value.indexOf("http")!=0){
        value = ""+value;
    }

    return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
}