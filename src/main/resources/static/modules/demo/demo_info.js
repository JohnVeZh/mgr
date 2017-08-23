/**
 * 初始化例子详情对话框
 */
var DemoInfoDlg = {
    demoInfoData : {}
};

/**
 * 清除数据
 */
DemoInfoDlg.clearData = function() {
    this.demoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DemoInfoDlg.set = function(key, val) {
    this.demoInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DemoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DemoInfoDlg.close = function() {
    parent.layer.close(window.parent.Demo.layerIndex);
}

/**
 * 收集数据
 */
DemoInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
DemoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/demo/add", function(data){
        Feng.success("添加成功!");
        window.parent.Demo.table.refresh();
        DemoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.demoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DemoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/demo/update", function(data){
        Feng.success("修改成功!");
        window.parent.Demo.table.refresh();
        DemoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.demoInfoData);
    ajax.start();
}

$(function() {
    // 初始化图片上传
    var avatarUp = new $WebUpload("productImg");
    avatarUp.init();
    var editor = new $wangEditor("demo");
    editor.init();
});
