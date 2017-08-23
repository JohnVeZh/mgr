/**
 * 初始化简系列碎片化详情对话框
 */
var QrFragmentationInfoDlg = {
    qrFragmentationInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
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
        hearUrl: {
            validators: {
                notEmpty: {
                    message: '听力路径不能为空'
                },
                regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                    regexp: /^http.*/,
                    message: '请填写正确链接.'
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
        },
        sectionCode: {
            validators: {
                notEmpty: {
                    message: '请选择学段'
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: '请选择类型'
                }
            }
        }
    }
};
/**
 * 验证数据是否为空
 */
QrFragmentationInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}

/**
 * 清除数据
 */
QrFragmentationInfoDlg.clearData = function() {
    this.qrFragmentationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrFragmentationInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.qrFragmentationInfoData[key] = val;
    }else{
        this.qrFragmentationInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrFragmentationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrFragmentationInfoDlg.close = function() {
    parent.layer.close(window.parent.QrFragmentation.layerIndex);
}

/**
 * 收集数据
 */
QrFragmentationInfoDlg.collectData = function() {
    this.set('id');
    this.set('name');
    this.set('qrCode');
    this.set('hearUrl');
    this.set('type');
    this.set('sectionCode');
    this.set('remarks');
    this.set('sort');
}

/**
 * 提交添加
 */
QrFragmentationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/simple/qrFragmentation/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrFragmentation.table.refresh();
        QrFragmentationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrFragmentationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrFragmentationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/simple/qrFragmentation/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrFragmentation.table.refresh();
        QrFragmentationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrFragmentationInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("Form", QrFragmentationInfoDlg.validateFields);
//初始化学段
    var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
        $.each(data,function(i,item){
            $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
        });
    }, function (data) {
        Feng.error("获取学段失败!" + data.responseJSON.message + "!");
    });
    ajax.start();

    //初始化修改数据
    if ($("#nameInit")){
        $("#name").val($("#nameInit").val());
    }
    if ($("#qrCodeInit")){
        $("#qrCode").val($("#qrCodeInit").val());
    }
    if ($("#hearUrlInit")){
        $("#hearUrl").val($("#hearUrlInit").val());
    }
    if ($("#typeInit")){
        $("#type").val($("#typeInit").val());
    }

    if ($("#sectionCodeInit")){
        $("#sectionCode").val($("#sectionCodeInit").val());
    }

    if ($("#remarksInit")){
        $("#remarks").val($("#remarksInit").val());
    }
    if ($("#sortInit")){
        $("#sort").val($("#sortInit").val());
    }

});
