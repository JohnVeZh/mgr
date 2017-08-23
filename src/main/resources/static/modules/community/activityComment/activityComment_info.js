/**
 * 初始化活动评论详情对话框
 */
var ActivityCommentInfoDlg = {
    uploadMultipleImg:{},
    ActivityCommentInfoData : {},
    validateFields: {
        replyContent: {
            validators: {
                notEmpty: {
                    message: '回复内容不能为空'
                }
            }
        },
        userId: {
            validators: {
                notEmpty: {
                    message: '回复账号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ActivityCommentInfoDlg.clearData = function() {
    this.ActivityCommentInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivityCommentInfoDlg.set = function(key, val) {
    this.ActivityCommentInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivityCommentInfoDlg.get = function(key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
ActivityCommentInfoDlg.close = function() {
    parent.layer.close(window.parent.ActivityComment.layerIndex);
};

/**
 * 收集数据
 */
ActivityCommentInfoDlg.collectData = function() {
    this.set('id');
};



ActivityCommentInfoDlg.replyContent  = function() {

    if (!this.validate()) {
        return;
    }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/community/activity/comment/reply/add", function(data){
        Feng.success("回复成功!");
        window.parent.ActivityComment.table.refresh();
        ActivityCommentInfoDlg.close();
    },function(data){
        Feng.error("回复失败!" + data.responseJSON.message + "!");
    });
    ajax.set("commentId",this.ActivityCommentInfoData.id);
    ajax.set("content",$("#replyContent").val());
    ajax.set("targetUserId",$("#activityCommentuserId").val());
    ajax.set("userId",$("#userId").val());
    ajax.start();
};

ActivityCommentInfoDlg.validate = function () {
    $('#activityCommentForm').data("bootstrapValidator").resetForm();
    $('#activityCommentForm').bootstrapValidator('validate');
    return $("#activityCommentForm").data('bootstrapValidator').isValid();
};

/**
 * 提交修改
 */
ActivityCommentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/community/activity/comment/update", function(data){
        Feng.success("修改成功!");
        window.parent.ActivityComment.table.refresh();
        ActivityCommentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ActivityCommentInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("activityCommentForm", ActivityCommentInfoDlg.validateFields);
});
