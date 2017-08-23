/**
 * 初始化二维码试卷字幕听力详情对话框
 */
var QrCaptionListeningInfoDlg = {
    QrCaptionListeningInfoData : {}
};

/**
 * 清除数据
 */
QrCaptionListeningInfoDlg.clearData = function() {
    this.QrCaptionListeningInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrCaptionListeningInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.QrCaptionListeningInfoData[key] = val;
    }else{
        this.QrCaptionListeningInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrCaptionListeningInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrCaptionListeningInfoDlg.close = function() {
    parent.layer.close(window.parent.QrCaptionListening.layerIndex);
}

/**
 * 收集数据
 */
QrCaptionListeningInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
QrCaptionListeningInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/QrCaptionListening/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrCaptionListening.table.refresh();
        QrCaptionListeningInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.QrCaptionListeningInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrCaptionListeningInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/QrCaptionListening/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrCaptionListening.table.refresh();
        QrCaptionListeningInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.QrCaptionListeningInfoData);
    ajax.start();
}

$(function() {

});
