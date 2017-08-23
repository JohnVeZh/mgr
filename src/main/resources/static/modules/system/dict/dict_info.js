/**
 * 初始化字典详情对话框
 */
var DictInfoDlg = {
    postData: undefined,
    itemTemplate: $("#itemTemplate").html(),
    validateFields: {
        type: {
            validators: {
                notEmpty: {
                    message: '类型不能为空'
                }
            }
        },
        label: {
            validators: {
                notEmpty: {
                    message: '标签不能为空'
                }
            }
        }
    }
};


/**
 * 关闭此对话框
 */
DictInfoDlg.close = function () {
    parent.layer.close(window.parent.Dict.layerIndex);
};

/**
 * 添加条目
 */
DictInfoDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
};
DictInfoDlg.validate = function () {
    $('#dictInfoForm').data("bootstrapValidator").resetForm();
    $('#dictInfoForm').bootstrapValidator('validate');
    return $("#dictInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 删除item
 */
DictInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
DictInfoDlg.clearNullDom = function () {
    $("[name='dictItem']").each(function () {
        var value = $(this).find("[name='value']").val();
        var label = $(this).find("[name='label']").val();
        if (value == '' || label == '') {
            $(this).remove();
        }
    });
};

/**
 * 收集添加字典的数据
 */
DictInfoDlg.collectData = function () {
    this.clearNullDom();
    this.postData = {
        type: $("#type").val(),
        label: $("#label").val(),
        value: $("#value").val(),
        description: $("#description").val(),
        sort: $("#sort").val()

    };
    var children = [];
    $("[name='dictItem']").each(function () {
        var label = $(this).find("[name='label']").val();
        var value = $(this).find("[name='value']").val();
        var description = $(this).find("[name='description']").val();
        if (label && value) {
            children.push({
                label: label,
                value: value,
                description: description
            });
        }
    });
    this.postData["children"] = children;
};


/**
 * 提交添加字典
 */
DictInfoDlg.addSubmit = function () {
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/system/dict/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Dict.table.refresh();
        DictInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    }, "json");
    ajax.setData(this.postData);
    ajax.start();
};

/**
 * 提交修改
 */
DictInfoDlg.editSubmit = function () {
    this.collectData();
    if (!this.validate()) {
        return;
    }
    this.postData["id"] = $("#dictId").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/system/dict/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Dict.table.refresh();
        DictInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    }, "json");
    ajax.setData(this.postData);
    ajax.start();
};

$(function () {
    Feng.initValidator("dictInfoForm", DictInfoDlg.validateFields);
    // 初始化图片上传
    if($("#start").val() == 1){
        var avatarUp = new $WebUpload("value");
        avatarUp.init();
    }
})