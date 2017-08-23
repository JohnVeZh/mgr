/**
 * 用户反馈管理初始化
 */
var UserFeedback = {
    id: "UserFeedbackTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserFeedback.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
        {title: '真实姓名', field: 'realName', visible: false, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
        {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
        {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
        {title: '处理状态', field: 'isHandle', visible: true, align: 'center', valign: 'middle',formatter:handleFormatter},
        {title: '处理结果', field: 'handleResults', visible: false, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserFeedback.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserFeedback.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户反馈
 */
UserFeedback.openAddUserFeedback = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户反馈',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userFeedback/userFeedback_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户反馈详情
 */
UserFeedback.openUserFeedbackDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户反馈详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userFeedback/userFeedback_update/' + UserFeedback.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除用户反馈
 */
UserFeedback.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/userFeedback/delete", function (data) {
            Feng.success("删除成功!");
            UserFeedback.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userFeedbackId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户反馈列表
 */
UserFeedback.search = function () {
    var queryData = {};
    queryData['btTime'] = $("#btTime").val();
    queryData['endTime'] = $("#endTime").val();
    UserFeedback.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UserFeedback.initColumn();
    var table = new BSTable(UserFeedback.id, "/userFeedback/list", defaultColunms);
    table.setPaginationType("server");
    UserFeedback.table = table.init();
});


function handleFormatter(value,rows,index) {
    if(value == 0){
        return '<span class="label label-success">待处理</span>';
    }else if(value == 1){
        return '<span class="label label-primary">已处理</span>';
    }else{
        return '<span class="label label-danger">未识别状态</span>';
    }
}