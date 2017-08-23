/**
 * 初始化简系列视频详情对话框
 */
var QrNetworkVideoInfoDlg = {
    qrNetworkVideoInfoData : {},
    validateFields: {
        catalogId: {
            validators: {
                notEmpty: {
                    message: '目录不能为空'
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: '视频名称不能为空'
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
        ccId: {
            validators: {
                notEmpty: {
                    message: '视频ccid不能为空'
                }
            }
        },
        img: {
            validators: {
                notEmpty: {
                    message: '图片不能为空'
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
QrNetworkVideoInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}

/**
 * 清除数据
 */
QrNetworkVideoInfoDlg.clearData = function() {
    this.qrNetworkVideoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrNetworkVideoInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.qrNetworkVideoInfoData[key] = val;
    }else{
        this.qrNetworkVideoInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrNetworkVideoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrNetworkVideoInfoDlg.close = function() {
    parent.layer.close(window.parent.QrNetworkVideo.layerIndex);
}

/**
 * 收集数据
 */
QrNetworkVideoInfoDlg.collectData = function() {
    this.set('id');
    this.set('sectionCode');
    this.set('catalogId');
    this.set('name');
    this.set('qrCode');
    this.set('ccId');
    this.set('img');
    this.set('sort');

}

/**
 * 提交添加
 */
QrNetworkVideoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    if (!($("#img").val())){
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/simple/qrNetworkVideo/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrNetworkVideo.table.refresh();
        QrNetworkVideoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrNetworkVideoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrNetworkVideoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    if (!($("#img").val())){
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/simple/qrNetworkVideo/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrNetworkVideo.table.refresh();
        QrNetworkVideoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrNetworkVideoInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("Form", QrNetworkVideoInfoDlg.validateFields);
//初始化学段
    var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
        $.each(data,function(i,item){
            $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
        });
    }, function (data) {
        Feng.error("获取学段失败!" + data.responseJSON.message + "!");
    });
    ajax.start();

    //初始化目录
    var catalogAjax = new $ax(Feng.ctxPath + "/simple/qrCatalog/list", function (data) {
        $.each(data.rows,function(i,item){
            $("#catalogId").append("<option value='" + item.id + "'>" + item.name + "</option>");
        });
    }, function (data) {
        Feng.error("获取目录失败!" + data.responseJSON.message + "!");
    });
    catalogAjax.start();


    // 初始化图片上传
    var avatarUp = new $WebUpload("img");
    avatarUp.init();

    //初始化修改数据
    if ($("#sectionCodeInit")){
        $("#sectionCode").val($("#sectionCodeInit").val());
    }
    if ($("#catalogIdInit")){
        $("#catalogId").val($("#catalogIdInit").val());
    }
    if ($("#nameInit")){
        $("#name").val($("#nameInit").val());
    }
    if ($("#qrCodeInit")){
        $("#qrCode").val($("#qrCodeInit").val());
    }

    if ($("#imgInit")){
        $("img").attr("src",$("#imgInit").val());
    }
    if ($("#sortInit")){
        $("#sort").val($("#sortInit").val());
    }
    if ($("#ccIdInit")){
        $("#ccId").val($("#ccIdInit").val());
    }

});
