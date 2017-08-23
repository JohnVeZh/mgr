/**
 * 消息推送管理初始化
 */
var MessageUser = {
    id: "MessageUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MessageUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MessageUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MessageUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加消息推送
 */
MessageUser.openAddMessageUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加消息推送',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/messageUser/messageUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看消息推送详情
 */
MessageUser.openMessageUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '消息推送详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/messageUser/messageUser_update/' + MessageUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除消息推送
 */
MessageUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/messageUser/delete", function (data) {
            Feng.success("删除成功!");
            MessageUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("messageUserId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询消息推送列表
 */
MessageUser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MessageUser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MessageUser.initColumn();
    var table = new BSTable(MessageUser.id, "/messageUser/list", defaultColunms);
    table.setPaginationType("server");
    MessageUser.table = table.init();
});
