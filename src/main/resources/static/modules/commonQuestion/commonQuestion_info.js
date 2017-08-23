/**
 * 初始化常见问题详情对话框
 */
var CommonQuestionInfoDlg = {
    commonQuestionInfoData : {}
};

/**
 * 清除数据
 */
CommonQuestionInfoDlg.clearData = function() {
    this.commonQuestionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommonQuestionInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.commonQuestionInfoData[key] = val;
    }else{
        this.commonQuestionInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommonQuestionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CommonQuestionInfoDlg.close = function() {
    parent.layer.close(window.parent.CommonQuestion.layerIndex);
}

/**
 * 收集数据
 */
CommonQuestionInfoDlg.collectData = function() {
    this.set('id');
    this.set('title');
    this.set('content');
    this.set('sort');
}

/**
 * 提交添加
 */
CommonQuestionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/commonQuestion/add", function(data){
        Feng.success("添加成功!");
        window.parent.CommonQuestion.table.refresh();
        CommonQuestionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.commonQuestionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CommonQuestionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/commonQuestion/update", function(data){
        Feng.success("修改成功!");
        window.parent.CommonQuestion.table.refresh();
        CommonQuestionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.commonQuestionInfoData);
    ajax.start();
}

$(function() {
    var editor = new $wangEditor("content");
    editor.init();
});
