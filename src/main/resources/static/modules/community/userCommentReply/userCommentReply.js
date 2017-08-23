/**
 * 用户评论回复管理初始化
 */
var UserCommentReply = {
    id: "UserCommentReplyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 检查是否选中
 */
UserCommentReply.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserCommentReply.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户评论回复
 */
UserCommentReply.openAddUserCommentReply = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户评论回复',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userCommentReply/userCommentReply_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户评论回复详情
 */
UserCommentReply.openUserCommentReplyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户评论回复详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/community/activity/comment/reply/update/' + UserCommentReply.seItem.id
        });
        this.layerIndex = index;
    }
};

UserCommentReply.isTop = function (id,isTop) {
    var ajax = new $ax(Feng.ctxPath + "/community/activity/comment/reply/update", function (data) {
        Feng.success("成功!");
        UserCommentReply.table.refresh();
    }, function (data) {
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set("id",id);
    ajax.set("isTop",isTop);
    ajax.start();
};

/**
 * 删除用户评论回复
 */
UserCommentReply.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/community/activity/comment/reply/delete", function (data) {
            Feng.success("删除成功!");
            UserCommentReply.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userCommentReplyId",this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = UserCommentReply.initColumn();
    var table = new BSTable(UserCommentReply.id, "/community/activity/comment/reply/list?activityCommentId="+UserCommentReply.activityCommentId, defaultColunms);
  //  table.setPaginationType("client");
    UserCommentReply.table = table.init();
});
