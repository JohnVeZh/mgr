/**
 * 简系列碎片化管理初始化
 */
var QrFragmentation = {
    id: "QrFragmentationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrFragmentation.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '二维码', field: 'qrCode', visible: true, align: 'center', valign: 'middle'},
        {title: '听力路径', field: 'hearUrl', visible: true, align: 'center', valign: 'middle', formatter:urlFormatter},
        {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle',formatter:typeFormatter},
        {title: '学段', field: 'sectionCodeName', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
        {title: '修改时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
        {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'}

    ];
};

/**
 * 检查是否选中
 */
QrFragmentation.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrFragmentation.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加简系列碎片化
 */
QrFragmentation.openAddQrFragmentation = function () {
    var index = layer.open({
        type: 2,
        title: '添加简系列碎片化',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/simple/qrFragmentation/qrFragmentation_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看简系列碎片化详情
 */
QrFragmentation.openQrFragmentationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '简系列碎片化详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/simple/qrFragmentation/qrFragmentation_update/' + QrFragmentation.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除简系列碎片化
 */
QrFragmentation.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/simple/qrFragmentation/delete", function (data) {
            Feng.success("删除成功!");
            QrFragmentation.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrFragmentationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询简系列碎片化列表
 */
QrFragmentation.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['type'] = $("#type").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    QrFragmentation.table.refresh({query: queryData});
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

    var defaultColunms = QrFragmentation.initColumn();
    var table = new BSTable(QrFragmentation.id, "/simple/qrFragmentation/list", defaultColunms);
    table.setPaginationType("server");
    QrFragmentation.table = table.init();
});

function typeFormatter(value,row,index) {
    if(value == 1){
        return '<span class="label label-info">碎片</span>';
    }else if(value == 2){
        return '<span class="label label-success">写作翻译</span>';
    }else{
        return '<span class="label label-danger">未识别类型</span>';
    }
}
function urlFormatter(value,rows,index) {
    if(value != ""){
        return '<a target="_blank" href="'+value+'">详情</a>';
    }
}