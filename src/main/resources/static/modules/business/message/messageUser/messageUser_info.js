/**
 * 初始化消息推送详情对话框
 */
var MessageUserInfoDlg = {
    messageUserInfoData : {}
};

/**
 * 清除数据
 */
MessageUserInfoDlg.clearData = function() {
    this.messageUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MessageUserInfoDlg.set = function(key, val) {
    this.messageUserInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MessageUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MessageUserInfoDlg.close = function() {
    parent.layer.close(window.parent.MessageUser.layerIndex);
}

/**
 * 收集数据
 */
MessageUserInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
MessageUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/messageUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.MessageUser.table.refresh();
        MessageUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.messageUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MessageUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/messageUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.MessageUser.table.refresh();
        MessageUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.messageUserInfoData);
    ajax.start();
}

$(function() {

});
