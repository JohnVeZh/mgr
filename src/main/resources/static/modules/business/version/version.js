/**
 * 版本管理初始化
 */
var Version = {
    id: "VersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Version.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '版本code(比较用)', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: '版本(展示用)', field: 'version', visible: true, align: 'center', valign: 'middle'},
        {title: '更新内容', field: 'summary', visible: false, align: 'center', valign: 'middle'},
        {title: '下载链接', field: 'downloadUrl', visible: false, align: 'center', valign: 'middle',formatter:urlFormatter},
        {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle',formatter:typeFormatter},
        {title: '是否强制更新', field: 'isForce', visible: true, align: 'center', valign: 'middle',formatter:forceFormatter},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
        {title: '修改时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle'}

    ];
};

/**
 * 检查是否选中
 */
Version.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Version.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加版本
 */
Version.openAddVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加版本',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/version/version_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看版本详情
 */
Version.openVersionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '版本详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/version/version_update/' + Version.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除版本
 */
Version.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/version/delete", function (data) {
            Feng.success("删除成功!");
            Version.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("versionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询版本列表
 */
Version.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Version.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Version.initColumn();
    var table = new BSTable(Version.id, "/version/list", defaultColunms);
    table.setPaginationType("server");
    Version.table = table.init();
});


function typeFormatter(value,rows,index) {
    if(value == 1){
        return '<font color="#006400" style="font-weight:bold;">Android</font>';
    }else if(value == 2){
        return  '<font color="#FF0000" style="font-weight:bold;">IOS</font>'
    }
}
function forceFormatter(value,rows,index) {
    if(value == 0){
        return '<font color="#2E8B57" style="font-weight:bold;">否</font>';
    }else if(value == 1){
        return  '<font color="#FF0000" style="font-weight:bold;">是</font>'
    }
}

function urlFormatter(value,rows,index) {
    if(value != ""){
        return '<a target="_blank" href="'+value+'">'+value+'</a>';
    }
}