/**
 * 初始化教师详情对话框
 */
var TeacherInfoDlg = {
    // editor:{},
    teacherInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        moblie: {
            validators: {
                notEmpty: {
                    message: '手机不能为空'
                },
                stringLength: {
                    min: 11,
                    max: 11,
                    message: '手机号长度必须是11位'
                },
                regexp: {
                    regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                    message: '请输入正确的手机号码'
                }
            }
        },
        email: {
            validators: {
                notEmpty: {
                    message: '邮箱不能为空'
                },
                emailAddress: {
                    message: '请输入正确的邮件地址如：123456789@qq.com'
                }
            }
        },
        contenteditor: {
            validators: {
                notEmpty: {
                    message: '简介不能为空'
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

/**
 * 清除数据
 */
TeacherInfoDlg.clearData = function() {
    this.teacherInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacherInfoDlg.set = function(key, val) {
    this.teacherInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacherInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TeacherInfoDlg.close = function() {
    parent.layer.close(window.parent.Teacher.layerIndex);
}

/**
 * 收集数据
 */
TeacherInfoDlg.collectData = function() {
    // this.set('id');
    this.teacherInfoData = {
        id:$("#id").val(),
        name: $("#name").val(),
        moblie: $("#moblie").val(),
        sort: $("#sort").val(),
        headImg:$("#headImg").val(),
        email:$("#email").val(),
        tags:$("#tags").val(),
        gender:$("input:radio[name=gender]:checked").val(),
        introduce: $("#introduce").val(),
    };
}

//验证表单
TeacherInfoDlg.validate = function () {
    $('#teacherInfoForm').data("bootstrapValidator").resetForm();
    $('#teacherInfoForm').bootstrapValidator('validate');
    return $("#teacherInfoForm").data('bootstrapValidator').isValid();
};




/**
 * 提交添加
 */
TeacherInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/teacher/add", function(data){
        Feng.success("添加成功!");
        window.parent.Teacher.table.refresh();
        TeacherInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacherInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TeacherInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/teacher/update", function(data){
        Feng.success("修改成功!");
        window.parent.Teacher.table.refresh();
        TeacherInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacherInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("teacherInfoForm", TeacherInfoDlg.validateFields);

    // 初始化图片上传
    var avatarUp = new $WebUpload("headImg");
    avatarUp.init();
    // TeacherInfoDlg.editor = new $wangEditor("introduce");
    // TeacherInfoDlg.editor = TeacherInfoDlg.editor.init();

});
