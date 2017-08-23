/**
 * 初始化激活码管理详情对话框
 */
var ActivationCodeInfoDlg = {
    activationCodeInfoData : {}
};

/**
 * 清除数据
 */
ActivationCodeInfoDlg.clearData = function() {
    this.activationCodeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivationCodeInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.activationCodeInfoData[key] = val;
    }else{
        this.activationCodeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivationCodeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ActivationCodeInfoDlg.close = function() {
    parent.layer.close(window.parent.ActivationCode.layerIndex);
}

/**
 * 收集数据
 */
ActivationCodeInfoDlg.collectData = function() {
    this.set('id');
}


/**
 * 激活码导入
 */
ActivationCodeInfoDlg.importSubmit = function () {
    var path = $("#txtFile").val();
    var extStart = path.lastIndexOf(".");
    var ext = path.substring(extStart, path.length).toUpperCase();
    if (ext.toLowerCase() != ".txt") {
        alert("请上传正确格式文件");
        return;
    }
    $("#activationCodeInfoForm").mask("文件正在提交...");
    $.ajaxFileUpload({
        url: Feng.ctxPath + '/gift/activationCode/importSubmit',
        secureuri: false,
        fileElementId:'txtFile', //文件选择框的id属性
        dataType: 'json',
        type: 'post',
        data: { qSectionCode: $("#qSectionCode").val() },
        cache:false,
        success: function (data, status) {
            if(data.code == 200) {
                Feng.success("导入成功！" + data.message);
                window.parent.ActivationCode.table.refresh();
                ActivationCodeInfoDlg.close();
            }else{
                Feng.error("导入失败！" + data.message + "!");
                ActivationCodeInfoDlg.close();
            }
        },
        error: function (data, status, e){
            alert(data.code + data.message);
            if(data.code == 200) {
                Feng.success("导入成功！" + data.message);
                window.parent.ActivationCode.table.refresh();
                ActivationCodeInfoDlg.close();
            }else{
                Feng.error("导入失败！" + data.message + "!");
                ActivationCodeInfoDlg.close();
            }
        }
    })
};



$(function() {

});
