/**
 * 字幕听力音频管理初始化
 */
var QrCaptionListening = {
    id: "CaptionListeningTable",	//表格id
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
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '字幕听力id', field: 'captionListeningId', visible: false, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '音频', field: 'audioUrl', visible: true, align: 'center', valign: 'middle',formatter: function(value,row,index){
            return '<audio style="width: 80px" controls="controls"><source src="'+value+'"></audio>';
        }},
        {title: '二维码', field: 'qrCode', visible: true, align: 'center', valign: 'middle'},
        {title: '字幕文件地址', field: 'subtitleUrl', visible: false, align: 'center', valign: 'middle'},
        {title: '音频文件大小', field: 'audioSize', visible: false, align: 'center', valign: 'middle'},
        {title: '字幕文件大小', field: 'subtitleSize', visible: false, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'}
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
 * 查询字幕听力音频列表
 */
QrCaptionListening.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    QrCaptionListening.table.refresh({query: queryData});
};

$(function () {
    var paperId= $("#paperId").val();
    var defaultColunms = QrCaptionListening.initColumn();
    var table = new BSTable(QrCaptionListening.id, "/QrCaptionListening/list?paperId="+paperId, defaultColunms);
    table.setPaginationType("client");
    QrCaptionListening.table = table.init();
});

/**
 * 打开查看题目详情
 */
QrCaptionListening.openCaptionListeningDetail = function () {
    if (this.check()) {
        var paperId= $("#paperId").val();//试卷id
        var id=QrCaptionListening.seItem.id;//二维码字幕听力id
        var captionListeningId =QrCaptionListening.seItem.captionListeningId;
        var index = layer.open({
            type: 2,
            title: '扫描字幕听力详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/CaptionListening/CaptionListening_updateQr?id='+id+'&paperId='+paperId+'&captionListeningId='+captionListeningId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除听力题
 */
QrCaptionListening.delete = function () {
    if (this.check()) {
            var ajax = new $ax(Feng.ctxPath + "/CaptionListening/deleteQr", function (data) {
                Feng.success("删除成功!");
                QrCaptionListening.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("qrCaptionListeningId",this.seItem.id);
            //ajax.set("questionId",this.seItem.questionId);
            ajax.start();
        }

};

/**
 * 点击添加字幕听力题目
 */
QrCaptionListening.openAddCaptionListening = function () {
    var type=$("#type").val();
    var paperId=$("#paperId").val();
    var sectionCode=$("#sectionCode").val();
    var contentType = $("#contentType").val();
    var index = layer.open({
        type: 2,
        title: '添加扫码字幕听力题目',
        area: ['96%', '96%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/CaptionListening/CaptionListening_addQr?type='+type+"&paperId="+paperId+"&contentType="+contentType+
        "&sectionCode="+sectionCode
    });
    this.layerIndex = index;
};

