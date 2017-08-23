/**
 * 字幕听力音频管理初始化
 */
var CaptionListening = {
    id: "CaptionListeningTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CaptionListening.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '音频', field: 'audioUrl', visible: true, align: 'center', valign: 'middle',formatter: function(value,row,index){
            return '<audio style="width: 80px" controls="controls"><source src="'+value+'"></audio>';
        }},
        {title: '字幕文件地址', field: 'subtitleUrl', visible: false, align: 'center', valign: 'middle'},
        {title: '音频文件大小', field: 'audioSize', visible: false, align: 'center', valign: 'middle'},
        {title: '字幕文件大小', field: 'subtitleSize', visible: false, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CaptionListening.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CaptionListening.seItem = selected[0];
        return true;
    }
};

/**
 * 查询字幕听力音频列表
 */
CaptionListening.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CaptionListening.table.refresh({query: queryData});
};

$(function () {
    var paperId= $("#paperId").val();
    var defaultColunms = CaptionListening.initColumn();
    var table = new BSTable(CaptionListening.id, "/CaptionListening/list?paperId="+paperId, defaultColunms);
    table.setPaginationType("client");
    CaptionListening.table = table.init();
});

/**
 * 打开查看题目详情
 */
CaptionListening.openCaptionListeningDetail = function () {
    if (this.check()) {
        var id=CaptionListening.seItem.id;
        var questionId=CaptionListening.seItem.questionId;
        var index = layer.open({
            type: 2,
            title: '字幕听力详情',
            area: ['96%', '96%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/CaptionListening/CaptionListening_update?CaptionListeningId='+id+"&questionId="+questionId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除听力题
 */
CaptionListening.delete = function () {
    if (this.check()) {
        var type=$("#type").val();
        if(type=='2'){//字幕听力
            var ajax = new $ax(Feng.ctxPath + "/CaptionListening/delete", function (data) {
                Feng.success("删除成功!");
                CaptionListening.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("CaptionListeningId",this.seItem.id);
            ajax.start();
        }else{//扫描字幕听力
            var ajax = new $ax(Feng.ctxPath + "/CaptionListening/delete", function (data) {
                Feng.success("删除成功!");
                CaptionListening.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("CaptionListeningId",this.seItem.id);
            ajax.start();
        }


    }
};

/**
 * 点击添加字幕听力题目
 */
CaptionListening.openAddCaptionListening = function () {
    var type=$("#type").val();
    var paperId=$("#paperId").val();
    var index = layer.open({
        type: 2,
        title: '添加字幕听力题目',
        area: ['96%', '96%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/CaptionListening/CaptionListening_add?type='+type+"&paperId="+paperId
    });
    this.layerIndex = index;
};

