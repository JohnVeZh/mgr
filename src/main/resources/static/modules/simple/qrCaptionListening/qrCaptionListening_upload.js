
/**
 * 初始化
 */
var QrCaptionListeningDllg = {
    paperUploadData : {}
};

/**
 * 关闭此对话框
 */
QrCaptionListeningDllg.close = function() {
    parent.layer.close(window.parent.Paper.layerIndex);
}

/**
 * 提交上传
 */
QrCaptionListeningDllg.upload = function() {
    var formData = new FormData($("#uploadForm")[0]);
    // var defaultContentType = "application/x-www-form-urlencoded;charset=UTF-8";
    // //提交信息
    // var ajax = new $ax(Feng.ctxPath + "/paper/upload/df87b9efd3ed11e68bac00163e0c8sd4", function (data) {
    //     Feng.success("导入成功!");
    //     QrCaptionListeningDllg.close();
    // }, function (data) {
    //     Feng.error("导入失败!" + data.responseJSON.message + "!");
    // },defaultContentType);
    // ajax.setData(formData);
    // ajax.start();
    $.ajax({
        url: Feng.ctxPath + "/simple/qrCaptionListening/upload",
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            if(data.code==200){
                Feng.success("导入成功!");
                QrCaptionListeningDllg.close();
            }else{
                Feng.error(data.message);
            }

        },
        error: function (returndata) {
            Feng.error("导入失败!" + returndata.message + "!");
        }
    });
}
/**
 * 提交上传
 */
QrCaptionListeningDllg.template = function(type) {
    // //提交信息
    // var ajax = new $ax(Feng.ctxPath + "/paper/template", function (data) {
    //     // QrCaptionListeningDllg.close();
    // }, function (data) {
    //     alert("error");
    // });
    // alert(type);
    // ajax.set("type",type);
    // ajax.start();
    window.location.href="/paper/template?type=" + type;
}

$(function() {

});
