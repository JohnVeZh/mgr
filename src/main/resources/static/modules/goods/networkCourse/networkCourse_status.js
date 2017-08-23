/**
 * 初始化网课详情对话框
 */
var NetworkCourseInfoDlg = {
    networkCourseInfoData : {}

};

/**
 * 清除数据
 */
NetworkCourseInfoDlg.clearData = function() {
    this.networkCourseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NetworkCourseInfoDlg.set = function(key, val) {
    this.networkCourseInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NetworkCourseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NetworkCourseInfoDlg.close = function() {
    parent.layer.close(window.parent.NetworkCourse.layerIndex);
}



/**
 * 收集状态数据
 */
NetworkCourseInfoDlg.collectStatusData = function() {
    // this.set('id');

    this.networkCourseInfoData = {
        id:$("#id").val(),
        status:$("#CourseStatus").val(),
    };
}


/**
 * 修改网课状态
 */
NetworkCourseInfoDlg.editStatusSubmit = function() {

    this.clearData();
    this.collectStatusData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/networkCourse/updateStatus", function(data){
        Feng.success("修改成功!");
        window.parent.NetworkCourse.table.refresh();
        NetworkCourseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.networkCourseInfoData);
    ajax.start();
}

$(function() {


    //网课状态回显
    $("#CourseStatus").val($("#CourseStatusValue").val());


});
