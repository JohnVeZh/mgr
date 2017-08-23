/**
 * 初始化社区资讯详情对话框
 */
var CommunityNewsInfoDlg = {
    communityNewsInfoData : {},
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
                }
            }
        },
        subtitle: {
            validators: {
                notEmpty: {
                    message: '副标题不能为空'
                }
            }
        },
        author: {
            validators: {
                notEmpty: {
                    message: '作者不能为空'
                }
            }
        },
        publishTime: {
            validators: {
                notEmpty: {
                    message: '发布时间不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
CommunityNewsInfoDlg.clearData = function() {
    this.communityNewsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommunityNewsInfoDlg.set = function(key, value) {
    this.communityNewsInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommunityNewsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CommunityNewsInfoDlg.close = function() {
    parent.layer.close(window.parent.CommunityNews.layerIndex);
}

/**
 * 收集数据
 */
CommunityNewsInfoDlg.collectData = function() {
    this.set('id');
    this.set('title');
    this.set('subtitle');
    this.set('author');
    this.set('publishTime');
    CommunityNewsInfoDlg.uploadMultipleImg.setUploadUrls;
    this.set('listImg');
    CommunityNewsInfoDlg.communityNewsInfoData.isShow = $("input:checkbox[name=isShow]:checked").val() ? $("input:checkbox[name=isShow]:checked").val() : 0,
    CommunityNewsInfoDlg.communityNewsInfoData.content=this.editor.$txt.html()
}

/**
 * 提交添加
 */
CommunityNewsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    var listImg = $("#listImg").val();
    if(listImg === null || listImg === undefined || listImg === ''){
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
    var ajax = new $ax(Feng.ctxPath + "/community/news/add", function(data){
        Feng.success("添加成功!");
        window.parent.CommunityNews.table.refresh();
        CommunityNewsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    },'json');
    ajax.setData(this.communityNewsInfoData);
    ajax.start();
}

CommunityNewsInfoDlg.validate = function () {
    $('#communityNewsForm').data("bootstrapValidator").resetForm();
    $('#communityNewsForm').bootstrapValidator('validate');
    return $("#communityNewsForm").data('bootstrapValidator').isValid();
};
/**
 * 提交修改
 */
CommunityNewsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    var listImg = $("#listImg").val();
    if(listImg === null || listImg === undefined || listImg === ''){
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
    var ajax = new $ax(Feng.ctxPath + "/community/news/update", function(data){
        Feng.success("修改成功!");
        window.parent.CommunityNews.table.refresh();
        CommunityNewsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    },'json');
    ajax.setData(this.communityNewsInfoData);
    ajax.start();
}

$(function() {
    // 初始化图片上传
    // var avatarUp = new $WebUpload("listImg");
    // avatarUp.init();
    Feng.initValidator("communityNewsForm", CommunityNewsInfoDlg.validateFields);
    CommunityNewsInfoDlg.editor = new $wangEditor("content");
    CommunityNewsInfoDlg.editor = CommunityNewsInfoDlg.editor.init();
});
