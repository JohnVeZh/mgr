/**
 * 字幕听力视频管理初始化
 */
var CaptionListeningVideo = {
    id: "CaptionListeningVideoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CaptionListeningVideo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '视频名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: 'cc视频id', field: 'ccId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CaptionListeningVideo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CaptionListeningVideo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加字幕听力视频
 */
CaptionListeningVideo.openAddCaptionListeningVideo = function () {
    var paperId=$("#paperId").val();
    var type = $("#type").val();
    var index = layer.open({
        type: 2,
        title: '添加字幕听力视频',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/CaptionListeningVideo/CaptionListeningVideo_add?paperId='+paperId+"&type="+type
    });
    this.layerIndex = index;
};

/**
 * 打开查看字幕听力视频详情
 */
CaptionListeningVideo.openCaptionListeningVideoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '字幕听力视频详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/CaptionListeningVideo/CaptionListeningVideo_update/' + CaptionListeningVideo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除字幕听力视频
 */
CaptionListeningVideo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/CaptionListeningVideo/delete", function (data) {
            Feng.success("删除成功!");
            CaptionListeningVideo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("CaptionListeningVideoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询字幕听力视频列表
 */
CaptionListeningVideo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CaptionListeningVideo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CaptionListeningVideo.initColumn();
    var paperId = $("#paperId").val();
    var table = new BSTable(CaptionListeningVideo.id, "/CaptionListeningVideo/list?paperId="+paperId, defaultColunms);
    table.setPaginationType("client");
    CaptionListeningVideo.table = table.init();
});
