/**
 * 初始化二维码详情对话框
 */
var QrCodeInfoDlg = {
    qrCodeInfoData : {},
    validateFields: {
        code: {
            validators: {
                notEmpty: {
                    message: '二维码内容不能为空'
                }
            }
        },
        sort: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                },
                numeric:{
                    message: '内容为数值'
                }
            }
        }
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
    this.set('id').set('code').set('sectionCode').set('targetType').set('useScene')
        .set('type').set('sort');
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
 * 提交添加
 */
QrCodeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrCode/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrCode.table.refresh();
        QrCodeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrCodeInfoData);
    ajax.start();
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
    var ajax = new $ax(Feng.ctxPath + "/qrCode/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrCode.table.refresh();
        QrCodeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrCodeInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("Form", QrCodeInfoDlg.validateFields);

    //初始化学段
    var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
        $.each(data,function(i,item){
            $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
        });
    }, function (data) {
        Feng.error("获取学段失败!" + data.responseJSON.message + "!");
    });
    ajax.start();

    //初始化下拉框
    if ($("#sectionCodeValue").val() != undefined) {
        $("#sectionCode").val($("#sectionCodeValue").val());
    }
    if ($("#targetTypeValue").val() != undefined) {
        $("#targetType").val($("#targetTypeValue").val());
    }
    if ($("#useSceneValue").val() != undefined) {
        $("#useScene").val($("#useSceneValue").val());
    }
    if ($("#typeValue").val() != undefined) {
        $("#type").val($("#typeValue").val());
    }
});
