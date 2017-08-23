/**
 * 初始化字幕听力音频详情对话框
 */
var CaptionListeningInfoDlg = {
    CaptionListeningInfoData : {},
    validateFields: {
        audioUrl: {
            validators: {
                notEmpty: {
                    message: '音频地址不能为空',
                    verify:false

                }
            }
        },
        subtitleUrl :{
            validators: {
                numeric:{
                    message: '字幕文件地址不能为空'

                }
            }
        },
        qrCode :{
            validators: {
                numeric:{
                    message: '二维码不能为空'

                }
            }
        },
        sort: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '请输入正整数'
                }
            }
        }
    }
};

//验证
CaptionListeningInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
CaptionListeningInfoDlg.clearData = function() {
    this.CaptionListeningInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaptionListeningInfoDlg.set = function(key, val) {

        if(val!=undefined){
            this.CaptionListeningInfoData[key] = val;
        }else{
            this.CaptionListeningInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
        }

    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaptionListeningInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CaptionListeningInfoDlg.close = function() {
    parent.layer.close(window.parent.QrCaptionListening.layerIndex);
}

/**
 * 收集数据
 */
CaptionListeningInfoDlg.collectData = function() {
    this.set('id').set('type').set('paperId').set('name').set('sort').set("audioUrl").set('subtitleUrl').set('qrCode')
        .set('captionListeningId');
}

/**
 * 提交添加
 */
CaptionListeningInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/CaptionListening/addQr", function(data){
        Feng.success("添加成功!");
        window.parent.QrCaptionListening.table.refresh();
        CaptionListeningInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.CaptionListeningInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CaptionListeningInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/CaptionListening/updateQr", function(data){
        Feng.success("修改成功!");
        window.parent.QrCaptionListening.table.refresh();
        CaptionListeningInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.CaptionListeningInfoData);
    ajax.start();
}

$(function() {

    Feng.initValidator("Form", CaptionListeningInfoDlg.validateFields);
    //初始化editor
    var bookEditor = new $wangEditor("content");
    CaptionListeningInfoDlg.editor =bookEditor.init();
    //初始化editor
    var bookEditor2 = new $wangEditor("analysis");
    CaptionListeningInfoDlg.editor2 =bookEditor2.init()

});
