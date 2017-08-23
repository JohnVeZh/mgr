/**
 * 初始化弹窗详情对话框
 */
var PopUpInfoDlg = {
    popUpInfoData: {},
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '弹窗主题不能为空'
                }
            }
        },
        startTime: {
            validators: {
                notEmpty: {
                    message: '开始时间不能为空'
                }
            }
        },
        endTime: {
            validators: {
                notEmpty: {
                    message: '结束时间不能为空'
                }
            }
        },
        sort: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                },
            }
        }
    }

};

/**
 * 清除数据
 */
PopUpInfoDlg.clearData = function () {
    this.popUpInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PopUpInfoDlg.set = function (key, val) {
    if (val != undefined) {
        this.popUpInfoData[key] = val;
    } else {
        this.popUpInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PopUpInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PopUpInfoDlg.close = function () {
    parent.layer.close(window.parent.PopUp.layerIndex);
}

/**
 * 收集数据
 */
PopUpInfoDlg.collectData = function () {
    this.set('id');
    this.set('title');
    this.set('showModule');
    this.set('url');
    this.set('sort');
    this.set('startTime');
    this.set('endTime');
    this.set('jumpType');
    this.set('img');
    this.set('status');
}

PopUpInfoDlg.validate = function () {
    $('#popUpInfoForm').data("bootstrapValidator").resetForm();
    $('#popUpInfoForm').bootstrapValidator('validate');
    return $("#popUpInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
PopUpInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/popUp/add", function (data) {
        Feng.success(data.message);
        window.parent.PopUp.table.refresh();
        PopUpInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.popUpInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PopUpInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/popUp/update", function (data) {
        Feng.success("修改成功!");
        window.parent.PopUp.table.refresh();
        PopUpInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.popUpInfoData);
    ajax.start();
}

$(function () {
    // 初始化图片上传
    var avatarUp = new $WebUpload("img");
    avatarUp.init();

    $("#jumpType").change(function () {
        var value = $("#jumpType").val();
        if (value != 2) {
            $("#url").attr("readonly", "readonly");
        }else {
            $("#url").removeAttr("readonly");
        }
    });
    intUpdate();

});
function intUpdate() {
    if($("#jumpTypeInit").val()!= undefined ){
        $("#jumpType").val($("#jumpTypeInit").val());
    }
    if($("#showModuleInit").val()!= undefined ){
        $("#showModule").val($("#showModuleInit").val());
    }
}

