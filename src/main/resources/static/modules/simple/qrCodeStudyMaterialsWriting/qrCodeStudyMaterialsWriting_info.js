/**
 * 初始化简系列写作翻译阅读详情对话框
 */
var QrCodeStudyMaterialsWritingInfoDlg = {
    editor:{},
    qrCodeStudyMaterialsWritingInfoData : {},
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
                }
            }
        },
        qrCode: {
            validators: {
                notEmpty: {
                    message: '二维码不能为空'
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: '类型不能为空'
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
QrCodeStudyMaterialsWritingInfoDlg.validate = function () {
    $('#QrCodeStudyMaterialsWritingInfoForm').data("bootstrapValidator").resetForm();
    $('#QrCodeStudyMaterialsWritingInfoForm').bootstrapValidator('validate');
    return $("#QrCodeStudyMaterialsWritingInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
QrCodeStudyMaterialsWritingInfoDlg.clearData = function() {
    this.qrCodeStudyMaterialsWritingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrCodeStudyMaterialsWritingInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.qrCodeStudyMaterialsWritingInfoData[key] = val;
    }else{
        this.qrCodeStudyMaterialsWritingInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrCodeStudyMaterialsWritingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrCodeStudyMaterialsWritingInfoDlg.close = function() {
    parent.layer.close(window.parent.QrCodeStudyMaterialsWriting.layerIndex);
}

/**
 * 收集数据
 */
QrCodeStudyMaterialsWritingInfoDlg.collectData = function() {
    this.qrCodeStudyMaterialsWritingInfoData = {
        id:$("#id").val(),
        title:$("#title").val(),
        qrCode:$("#qrCode").val(),
        type:$("#type").val(),
        sort:$("#sort").val(),
        content: this.editor.$txt.html()
    };
}

/**
 * 提交添加
 */
QrCodeStudyMaterialsWritingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/simple/qrCodeStudyMaterialsWriting/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrCodeStudyMaterialsWriting.table.refresh();
        QrCodeStudyMaterialsWritingInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrCodeStudyMaterialsWritingInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrCodeStudyMaterialsWritingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/simple/qrCodeStudyMaterialsWriting/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrCodeStudyMaterialsWriting.table.refresh();
        QrCodeStudyMaterialsWritingInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrCodeStudyMaterialsWritingInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("QrCodeStudyMaterialsWritingInfoForm", QrCodeStudyMaterialsWritingInfoDlg.validateFields);
    if($("#id").val()!='') {
        $("#title").val($("#titleInit").val());
        $("#qrCode").val($("#qrCodeInit").val());
        $("#type").val($("#typeInit").val());
        $("#sort").val($("#sortInit").val());
        //$("#isPromotion").val($("#isPromotionValue").val());
    }
    //初始化editor
    var bookEditor = new $wangEditor("content");
    QrCodeStudyMaterialsWritingInfoDlg.editor =bookEditor.init();
    this.editor.$txt.html();

    });
