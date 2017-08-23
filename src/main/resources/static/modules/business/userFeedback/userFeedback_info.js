/**
 * 初始化用户反馈详情对话框
 */
var UserFeedbackInfoDlg = {
    userFeedbackInfoData : {}
};

/**
 * 清除数据
 */
UserFeedbackInfoDlg.clearData = function() {
    this.userFeedbackInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserFeedbackInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.userFeedbackInfoData[key] = val;
    }else{
        this.userFeedbackInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserFeedbackInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserFeedbackInfoDlg.close = function() {
    parent.layer.close(window.parent.UserFeedback.layerIndex);
}

/**
 * 收集数据
 */
UserFeedbackInfoDlg.collectData = function() {
    this.set('id');
    var result = $("#handleResults").val();
    result = result.replace(/(^\s*)|(\s*$)/g, "");
    if(result == undefined){

    }else if(result == ""){
        result = "已处理";
        this.set('handleResults',result);
        this.set('isHandle',1);
    }else{
        this.set('handleResults',result);
        this.set('isHandle',1);
    }
    this.set('id');
}

/**
 * 提交添加
 */
UserFeedbackInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userFeedback/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserFeedback.table.refresh();
        UserFeedbackInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });

    ajax.set(this.userFeedbackInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserFeedbackInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if($("#isHandle").html() == "已处理"){
        layer.msg("已处理，无需再次提交！");
        return "";
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userFeedback/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserFeedback.table.refresh();
        UserFeedbackInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userFeedbackInfoData);
    ajax.start();
}

$(function() {
    var handleStatus = $("#handleStatus").val();
    if(handleStatus != undefined){
        if(handleStatus == 0){
            $("#isHandle").html("待处理");
        }else if(handleStatus == 1){
            $("#isHandle").html("已处理");
            $("#handleResults").attr("readonly",true);
        }
    }
});
