/**
 * 消息推送管理初始化
 */
var Message = {
    id: "MessageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Message.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '简介', field: 'intro', visible: true, align: 'center', valign: 'middle'},
        {title: '内容', field: 'content', visible: false, align: 'center', valign: 'middle'},
        {title: '图片', field: 'img', visible: true, align: 'center', valign: 'middle',formatter:imgFormatter},
        {title: '推送状态', field: 'pushStatus', visible: true, align: 'center', valign: 'middle',formatter:statusFormatter},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
        {title: '推送时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle',formatter:dateFormatter},
        {title: '操作', field: 'caozuo', visible: true, align: 'center', valign: 'middle',formatter:editFormatter}
    ];
};

/**
 * 检查是否选中
 */
Message.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Message.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加消息
 */
Message.openAddMessage = function () {
    var index = layer.open({
        type: 2,
        title: '添加消息推送',
        // area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/message/message_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看消息详情
 */
Message.openMessageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '消息推送详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/message/message_update/' + Message.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 推送
 */
Message.push = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/message/push", function (data) {
            Feng.success("推送成功!");
            Message.table.refresh();
        }, function (data) {
            Feng.error("推送失败!" + data.responseJSON.message + "!");
        });
        ajax.set("messageId",this.seItem.id);
        ajax.start();
    }
};


/**
 * 删除消息
 */
Message.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/message/delete", function (data) {
            Feng.success("删除成功!");
            Message.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("messageId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询消息推送列表
 */
Message.search = function () {
    var queryData = {};
    queryData['btTime'] = $("#btTime").val();
    queryData['endTime'] = $("#endTime").val();
    Message.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Message.initColumn();
    var table = new BSTable(Message.id, "/message/list", defaultColunms);
    table.setPaginationType("server");//client
    Message.table = table.init();
});



function imgFormatter(value,row,index) {
    if(value != ""){
        return "<img src='"+value+"' width='50px' height='50px'>"
    }
}
function statusFormatter(value,row,index) {
    if(value == 0){
        return '<span class="label label-success">待推送</span>';
    }else if(value == 1){
        return '<span class="label label-primary">已推送</span>';
    }else{
        return '<span class="label label-danger">未识别状态</span>';
    }

}
function dateFormatter(value,row,index) {
    if(value != row.createDate && row.pushStatus == 1){
        return value;
    }else{
        return "";
    }
}

function editFormatter(value,row,index) {
    var menu = "";
    if($("#pushPermiss").val()){
        menu += ' <a onclick="Message.push(\''+row.id+'\')" title="推送"><span class="label label-default">推送</span></a>';
    }
    return menu;
}