/**
 * 初始化简系列目录详情对话框
 */
var QrCatalogInfoDlg = {
    qrCatalogInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '目录名称不能为空'
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
                    message: '请选择类型'
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
 * 验证数据是否为空
 */
QrCatalogInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}

/**
 * 清除数据
 */
QrCatalogInfoDlg.clearData = function() {
    this.qrCatalogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrCatalogInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.qrCatalogInfoData[key] = val;
    }else{
        this.qrCatalogInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrCatalogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrCatalogInfoDlg.close = function() {
    parent.layer.close(window.parent.QrCatalog.layerIndex);
}

/**
 * 收集数据
 */
QrCatalogInfoDlg.collectData = function() {
    this.set('id');
    this.set('name');
    this.set('qrCode');
    this.set('type');
    this.set('sort');
}

/**
 * 提交添加
 */
QrCatalogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/simple/qrCatalog/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrCatalog.table.refresh();
        QrCatalogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrCatalogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrCatalogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/simple/qrCatalog/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrCatalog.table.refresh();
        QrCatalogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrCatalogInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("Form", QrCatalogInfoDlg.validateFields);

    $("#name").val($("#nameInit").val());
    $("#qrCode").val($("#qrCodeInit").val());
    $("#type").val($("#typeInit").val());
    $("#sort").val($("#sortInit").val());
});
