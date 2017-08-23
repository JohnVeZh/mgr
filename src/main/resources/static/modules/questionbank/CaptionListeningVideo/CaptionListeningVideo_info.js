/**
 * 初始化字幕听力视频详情对话框
 */
var CaptionListeningVideoInfoDlg = {
    CaptionListeningVideoInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '视频名称不能为空',
                    verify:false

                }
            }
        },
        ccId :{
            validators: {
                numeric:{
                    message: 'cc视频id不能为空'

                }
            }
        }
    }
};

/**
 * 清除数据
 */
CaptionListeningVideoInfoDlg.clearData = function() {
    this.CaptionListeningVideoInfoData = {};
}

//验证
CaptionListeningVideoInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaptionListeningVideoInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.CaptionListeningVideoInfoData[key] = val;
    }else{
        this.CaptionListeningVideoInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaptionListeningVideoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CaptionListeningVideoInfoDlg.close = function() {
    parent.layer.close(window.parent.CaptionListeningVideo.layerIndex);
}

/**
 * 收集数据
 */
CaptionListeningVideoInfoDlg.collectData = function() {
    this.set('id').set('paperId').set('name').set('ccId');
}

/**
 * 提交添加
 */
CaptionListeningVideoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/CaptionListeningVideo/add", function(data){
        Feng.success("添加成功!");
        window.parent.CaptionListeningVideo.table.refresh();
        CaptionListeningVideoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.CaptionListeningVideoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CaptionListeningVideoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/CaptionListeningVideo/update", function(data){
        Feng.success("修改成功!");
        window.parent.CaptionListeningVideo.table.refresh();
        CaptionListeningVideoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.CaptionListeningVideoInfoData);
    ajax.start();
}

$(function() {

});
