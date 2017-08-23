/**
 * 初始化字典详情对话框
 */
var ChannelInfoDlg = {
    postData: undefined,
    validateFields: {
        value: {
            validators: {
                notEmpty: {
                    message: '渠道代码不能为空'
                }
            }
        },
        label: {
            validators: {
                notEmpty: {
                    message: '渠道名称不能为空'
                }
            }
        }
    }
};


/**
 * 关闭此对话框
 */
ChannelInfoDlg.close = function () {
    parent.layer.close(window.parent.Channel.layerIndex);
};

ChannelInfoDlg.validate = function () {
    $('#channelInfoForm').data("bootstrapValidator").resetForm();
    $('#channelInfoForm').bootstrapValidator('validate');
    return $("#channelInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 删除item
 */
ChannelInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj.parent().parent().remove();
};



/**
 * 收集添加字典的数据
 */
ChannelInfoDlg.collectData = function () {
    this.postData = {
        type: $("#type").val(),
        label: $("#label").val(),
        value: $("#value").val(),
        description: $("#description").val(),
        sort: $("#sort").val()
    };
};


/**
 * 提交添加字典
 */
ChannelInfoDlg.addSubmit = function () {
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/channel/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Channel.table.refresh();
        ChannelInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    }, "json");
    ajax.setData(this.postData);
    ajax.start();
};

/**
 * 提交修改
 */
ChannelInfoDlg.editSubmit = function () {
    this.collectData();
    if (!this.validate()) {
        return;
    }
    this.postData["id"] = $("#channelId").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/channel/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Channel.table.refresh();
        ChannelInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    }, "json");
    ajax.setData(this.postData);
    ajax.start();
};

$(function () {
    Feng.initValidator("channelInfoForm", ChannelInfoDlg.validateFields);
})