/**
 * 初始化社区活动详情对话框
 */
var ActivityInfoDlg = {
    editor:{},
    activityInfoData : {},
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
                }
            }
        },
        brief: {
            validators: {
                notEmpty: {
                    message: '简介不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ActivityInfoDlg.clearData = function() {
    this.activityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivityInfoDlg.set = function(key, val) {
    this.activityInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ActivityInfoDlg.close = function() {
    parent.layer.close(window.parent.Activity.layerIndex);
}

/**
 * 收集数据
 */
ActivityInfoDlg.collectData = function() {
    //this.set('id');
    this.activityInfoData = {
        id:$("#id").val(),
        title: $("#title").val(),
        brief: $("#brief").val(),
        imgList:$("#imgList").val(),
        // isTop:$("input:checkbox[name=isTop]:checked").val() ? $("input:checkbox[name=isTop]:checked").val() : 0 ,
        // isRecommend:$("input:checkbox[name=isRecommend]:checked").val() ? $("input:checkbox[name=isRecommend]:checked").val() : 0,
        isShow:$("input:checkbox[name=isShow]:checked").val() ? $("input:checkbox[name=isShow]:checked").val() : 0,
        serviceStatus:$("input:checkbox[name=serviceStatus]:checked").val() ? $("input:checkbox[name=serviceStatus]:checked").val() : 0,
        content: this.editor.$txt.html()
    };
};


ActivityInfoDlg.validate = function () {
    $('#activityInfoForm').data("bootstrapValidator").resetForm();
    $('#activityInfoForm').bootstrapValidator('validate');
    return $("#activityInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 提交添加
 */
ActivityInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    var imgList = $("#imgList").val();
    if(imgList === null || imgList === undefined || imgList === ''){
        //提示层
        layer.msg('请上传图片');
        return;
    }
    var content = this.editor.$txt.html();
    if(content === null || content === undefined || content === ''){
        //提示层
        layer.msg('请填写内容');
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/community/activity/add", function(data){
        Feng.success("添加成功!");
        window.parent.Activity.table.refresh();
        ActivityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.activityInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
ActivityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    var imgList = $("#imgList").val();
    if(imgList === null || imgList === undefined || imgList === ''){
        //提示层
        layer.msg('请上传图片');
        return;
    }
    var content = this.editor.$txt.html();
    if(content === null || content === undefined || content === ''){
        //提示层
        layer.msg('请填写内容');
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/community/activity/update", function(data){
        Feng.success("修改成功!");
        window.parent.Activity.table.refresh();
        ActivityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.activityInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("activityInfoForm", ActivityInfoDlg.validateFields);
        // 初始化图片上传
        var avatarUp = new $WebUpload("imgList");
        avatarUp.init();
    ActivityInfoDlg.editor = new $wangEditor("content");
    ActivityInfoDlg.editor = ActivityInfoDlg.editor.init();
});
