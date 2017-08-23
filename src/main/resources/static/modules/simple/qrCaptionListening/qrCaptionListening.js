/**
 * 二维码管理初始化
 */
var QrCode = {
    id: "QrCodeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrCode.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '二维码内容', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: '使用场景', field: 'useSceneName', visible: true, align: 'center', valign: 'middle'},
        {title: '学段', field: 'sectionCodeName', visible: true, align: 'center', valign: 'middle'},
        {title: '二维码类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '目标类型', field: 'targetTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '音频地址', field: 'captionListeningEntity.audioUrl', visible: true, align: 'center', valign: 'middle', formatter:audioUrlFormatter},
        {title: '音频大小', field: 'captionListeningEntity.audioSize', visible: true, align: 'center', valign: 'middle', formatter:fileSizeFormatter},
        {title: '字幕文件地址', field: 'captionListeningEntity.subtitleUrl', visible: true, align: 'center', valign: 'middle', formatter:urlFormatter},
        {title: '字幕文件大小', field: 'captionListeningEntity.subtitleSize', visible: true, align: 'center', valign: 'middle', formatter:fileSizeFormatter}
    ];
};

/**
 * 检查是否选中
 */
QrCode.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrCode.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加二维码
 */
QrCode.openAddQrCode = function () {
    var index = layer.open({
        type: 2,
        title: '添加二维码',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/simple/qrCaptionListening/qrCaptionListening_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码详情
 */
QrCode.openQrCodeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑简系列字幕听力',
            area: ['900px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/simple/qrCaptionListening/qrCaptionListening_update/' + QrCode.seItem.id+'?qrCode='+QrCode.seItem.code
        });
        this.layerIndex = index;
    }
};

/**
 * 删除二维码
 */
QrCode.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/simple/qrCaptionListening/delete", function (data) {
            Feng.success("删除成功!");
            QrCode.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrCode",this.seItem.code);
        ajax.start();
    }
};

/**
 * 查询二维码列表
 */
QrCode.search = function () {
    var queryData = {};
    queryData['code'] = $("#code").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    queryData['targetType'] = $("#targetType").val();
    queryData['useScene'] = $("#useScene").val();
    queryData['type'] = $("#type").val();
    QrCode.table.refresh({query: queryData});
};

/**
 * 重置
 */
QrCode.resetSearch = function () {
    $('#code').val('');
    $('#sectionCode').val('');
    $('#targetType').val('');
    $('#useScene').val('');
    $('#type').val('');
    QrCode.search();
};

$(function () {
    //初始化学段
    var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
        $.each(data,function(i,item){
            $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
        });
    }, function (data) {
        Feng.error("获取学段失败!" + data.responseJSON.message + "!");
    });
    ajax.start();

    var buttonListen ='<button type="button" class="btn btn-primary" onclick="QrCode.downloadListening()" id="">'
        +'<i class="fa fa-download"></i>&nbsp;听力模板</button> &nbsp;&nbsp;';

    var buttonUpload ='<button type="button" class="btn btn-primary" onclick="QrCode.Upload()" id="">'
        +'<i class="fa fa-upload"></i>&nbsp;上传Excel</button>';

    $("#QrCodeTableToolbar").append(buttonListen+buttonUpload);


    var defaultColunms = QrCode.initColumn();
    var table = new BSTable(QrCode.id, "/simple/qrCaptionListening/list", defaultColunms);
    QrCode.table = table.init();




});


function urlFormatter(value,rows,index) {
    if(value ){
        return '<a target="_blank" href="'+value+'" title="'+value+'">点击查看</a>';
    }
    return '-';
}

function audioUrlFormatter(value,row,index){
    if(value ) {
        return '<audio style="width: 80px" controls="controls"><source src="' + value + '"></audio>';
    }
    return '-';
}

function fileSizeFormatter(value,rows,index) {
    if(value){
        return value+' Byte';
    }
    return '-';
}
QrCode.downloadListening=function(){

        window.location.href = Feng.ctxPath + "/FileUoload/downloadFile?type=" + "6";
}

QrCode.Upload=function(){

        var index = layer.open({
            type: 2,
            title: '导入简系列字幕听力',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/simple/qrCaptionListening/to/upload'
        });
        this.layerIndex = index;
}