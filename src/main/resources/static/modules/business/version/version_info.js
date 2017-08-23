/**
 * 初始化版本详情对话框
 */
var VersionInfoDlg = {
    versionInfoData : {}
};

/**
 * 清除数据
 */
VersionInfoDlg.clearData = function() {
    this.versionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VersionInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.versionInfoData[key] = val;
    }else{
        this.versionInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VersionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VersionInfoDlg.close = function() {
    parent.layer.close(window.parent.Version.layerIndex);
}

/**
 * 收集数据
 */
VersionInfoDlg.collectData = function() {
    var isForce =  $("#isForce").prop("checked");
    if(isForce == true){
        this.set('isForce',1);
    }else{
        this.set('isForce',0);
    }
    this.set('id');
    this.set('code');
    this.set('title');
    this.set('version');
    this.set('type');
    this.set('downloadUrl');
    this.set('summary');
}

/**
 * 提交添加
 */
VersionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/version/add", function(data){
        Feng.success("添加成功!");
        window.parent.Version.table.refresh();
        VersionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.versionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VersionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/version/update", function(data){
        Feng.success("修改成功!");
        window.parent.Version.table.refresh();
        VersionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.versionInfoData);
    ajax.start();
}

$(function() {
    var type = $("#type").val();
    var isForceValue= $("#isForceValue").val();
    if(type != undefined){
        if(type == 1){
            $("#versionType").val("Android");
        }
        if(type == 2){
            $("#versionType").val("IOS");
        }
    }
    if(isForceValue != undefined){
        if(isForceValue == 0){
            $("#isForce").prop("checked",false);
        }
        if(isForceValue == 1){
            $("#isForce").prop("checked",true);
        }
    }

});
