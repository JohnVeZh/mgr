/**
 * 初始化用户评论回复详情对话框
 */
var UserCommentReplyInfoDlg = {
    userCommentReplyInfoData : {}
};

/**
 * 清除数据
 */
UserCommentReplyInfoDlg.clearData = function() {
    this.userCommentReplyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserCommentReplyInfoDlg.set = function(key, val) {
    this.userCommentReplyInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserCommentReplyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserCommentReplyInfoDlg.close = function() {
    parent.layer.close(window.parent.UserCommentReply.layerIndex);
}

/**
 * 收集数据
 */
UserCommentReplyInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
UserCommentReplyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userCommentReply/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserCommentReply.table.refresh();
        UserCommentReplyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userCommentReplyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserCommentReplyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userCommentReply/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserCommentReply.table.refresh();
        UserCommentReplyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userCommentReplyInfoData);
    ajax.start();
}

$(function() {

});
