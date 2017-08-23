/**
 * 初始化二维码详情对话框
 */
var QrCodeInfoDlg = {
    qrCodeInfoData : {},
    validateFields: {
        audioUrl: {
            validators: {
                notEmpty: {
                    message: '音频地址不能为空'
                },
                regexp: {
                    regexp: /^http.*/,
                    message: '音频地址地址请以http或https开头'
                }
            }
        },
        subtitleUrl: {
            validators: {
                notEmpty: {
                    message: '字幕文件地址不能为空'
                },
                regexp: {
                    regexp: /^http.*/,
                    message: '字幕文件地址请以http或https开头'
                }
            }
        },
        audioSize: {
            validators: {
                notEmpty: {
                    message: '音频文件大小不能为空'
                },
                numeric:{
                    message: '内容为数值'
                }
            }
        },
        subtitleSize: {
            validators: {
                notEmpty: {
                    message: '字幕文件大小不能为空'
                },
                numeric:{
                    message: '内容为数值'
                }
            }
        },
    }
};

/**
 * 清除数据
 */
QrCodeInfoDlg.clearData = function() {
    this.qrCodeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrCodeInfoDlg.set = function(key, val) {
    this.qrCodeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrCodeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrCodeInfoDlg.close = function() {
    parent.layer.close(window.parent.QrCode.layerIndex);
}

/**
 * 收集数据
 */
QrCodeInfoDlg.collectData = function() {
    this.set('id').set('captionListeningId').set('qrCode').set('audioUrl').set('subtitleUrl')
        .set('audioSize').set('subtitleSize');
}

/**
 * 验证数据是否为空
 */
QrCodeInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}



/**
 * 提交修改
 */
QrCodeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/simple/qrCaptionListening/update", function(data){
        Feng.success("成功!");
        window.parent.QrCode.table.refresh();
        QrCodeInfoDlg.close();
    },function(data){
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrCodeInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("Form", QrCodeInfoDlg.validateFields);

    //初始化下修改
    if($("#id").val()!=''){
        $("#audioUrl").val($("#audioUrlInit").val())
        $("#subtitleUrl").val($("#subtitleUrlInit").val())
        $("#audioSize").val($("#audioSizeInit").val())
        $("#subtitleSize").val($("#subtitleSizeInit").val())
    }

});
