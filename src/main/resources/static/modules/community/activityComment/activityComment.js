/**
 * 活动评论管理初始化
 */
var ActivityComment = {
    id: "ActivityCommentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ActivityComment.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ActivityComment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ActivityComment.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加活动评论
 */
ActivityComment.openAddActivityComment = function () {
    var index = layer.open({
        type: 2,
        title: '添加活动评论',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/community/activity/comment/add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看活动评论详情
 */
ActivityComment.openActivityCommentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '活动评论详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/community/activity/comment/update/' + ActivityComment.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

//置顶
ActivityComment.isTop = function (id,isTop) {
    var ajax = new $ax(Feng.ctxPath + "/community/activity/comment/update", function (data) {
        Feng.success("成功!");
        ActivityComment.table.refresh();
    }, function (data) {
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set("id",id);
    ajax.set("isTop",isTop);
    ajax.start();
};

//置顶
ActivityComment.viewReply = function (id) {
    var index = layer.open({
        type: 2,
        title: '活动评论回复详情',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/community/activity/comment/reply?activityCommentId=' + id
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 删除活动评论
 */
ActivityComment.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/community/activity/comment/delete", function (data) {
            Feng.success("删除成功!");
            ActivityComment.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("activityCommentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询活动评论列表
 */
ActivityComment.search = function () {
    var queryData = {};
    queryData['content'] = $("#content").val();
    queryData['userName'] = $("#userName").val();
    queryData['userPhone'] = $("#userPhone").val();
    ActivityComment.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ActivityComment.initColumn();
    var table = new BSTable(ActivityComment.id, "/community/activity/comment/list", defaultColunms);
    table.setPaginationType("server");
    ActivityComment.table = table.init();
});
