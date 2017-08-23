/**
 * 初始化用户详情对话框
 */
var UserInfoDlg = {
    userInfoData : {},
    validateFields: {
        nickname: {
            validators: {
                notEmpty: {
                    message: '昵称不能为空'
                }
            }
        },
        sex: {
            validators: {
                notEmpty: {
                    message: '性别不能为空'
                }
            }
        },
        userStatus: {
            validators: {
                notEmpty: {
                    message: '用户状态不能为空'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '手机不能为空'
                },
                regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                    regexp: /^1[345789]\d{9}$/,
                    message: '请填写正确手机号.'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
UserInfoDlg.clearData = function() {
    this.userInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function(key, val) {
    this.userInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function() {
    parent.layer.close(window.parent.User.layerIndex);
}

/**
 * 验证数据是否为空
 */
UserInfoDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 收集数据
 */
UserInfoDlg.collectData = function() {
    this.set('nickname');
    this.set('sex');
    this.set('enrollYear');
    this.set('realName');
    this.set('birthday');
    this.set('userStatus');
    this.set('phone');
}

/**
 * 提交添加
 */
UserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/user/add", function(data){
        Feng.success("添加成功!");
        window.parent.User.table.refresh();
        UserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/user/update", function(data){
        Feng.success("修改成功!");
        window.parent.User.table.refresh();
        UserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);
    //初始化性别选项
    if($("#sexValue").val()!= undefined ){
        $("#sex").val($("#sexValue").val());
    }
    //初始化用户状态
    if($("#userStatusValue").val()!= undefined){
        $("#userStatus").val($("#userStatusValue").val());
    }

});
