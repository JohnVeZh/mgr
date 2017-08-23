/**
 * 初始化网课评论详情对话框
 */
var CourseCommentInfoDlg = {
    CourseCommentInfoData : {},
    validateFields:{
        praiseNum: {
            validators: {
                notEmpty: {
                    message: '点赞数量不能为空'
                }
            }
        }

    }
};

/**
 * 清除数据
 */
CourseCommentInfoDlg.clearData = function() {
    this.CourseCommentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CourseCommentInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.CourseCommentInfoData[key] = val;
    }else{
        this.CourseCommentInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CourseCommentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CourseCommentInfoDlg.close = function() {
    parent.layer.close(window.parent.CourseComment.layerIndex);
}

/**
 * 收集数据
 */
CourseCommentInfoDlg.collectData = function() {
    this.set('id').set("praiseNum");
}


//验证
CourseCommentInfoDlg.validate = function () {
    $('#courseCommentInfoForm').data("bootstrapValidator").resetForm();
    $('#courseCommentInfoForm').bootstrapValidator('validate');
    return $("#courseCommentInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 提交添加
 */
CourseCommentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/CourseComment/add", function(data){
        Feng.success("添加成功!");
        window.parent.CourseComment.table.refresh();
        CourseCommentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.CourseCommentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CourseCommentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/CourseComment/update", function(data){
        Feng.success("修改成功!");
        window.parent.CourseComment.table.refresh();
        CourseCommentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.CourseCommentInfoData);
    ajax.start();
}

$(function() {

    //验证初始化c
    Feng.initValidator("courseCommentInfoForm", CourseCommentInfoDlg.validateFields);

});
