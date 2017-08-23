/**
 * 初始化评论详情对话框
 */
var CourseCommentReplyInfoDlg = {
    courseCommentReplyInfoData : {}
};

/**
 * 清除数据
 */
CourseCommentReplyInfoDlg.clearData = function() {
    this.courseCommentReplyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CourseCommentReplyInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.courseCommentReplyInfoData[key] = val;
    }else{
        this.courseCommentReplyInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CourseCommentReplyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CourseCommentReplyInfoDlg.close = function() {
    parent.layer.close(window.parent.CourseComment.layerIndex);
}

/**
 * 收集数据
 */
CourseCommentReplyInfoDlg.collectData = function() {
    this.set('targetUserId');
    this.set('userId');
    this.set('commentId');
    this.set('content');
    this.set('id');
}

/**
 * 提交添加
 */
CourseCommentReplyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/courseCommentReply/add", function(data){
        Feng.success("添加成功!");
        window.parent.CourseComment.table.refresh();
        CourseCommentReplyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.courseCommentReplyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CourseCommentReplyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/courseCommentReply/update", function(data){
        Feng.success("修改成功!");
        window.parent.CourseComment.table.refresh();
        CourseCommentReplyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.courseCommentReplyInfoData);
    ajax.start();
}

$(function() {

});
