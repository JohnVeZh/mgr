/**
 * 初始化图书详情对话框
 */
var BookInfoDlg = {
    bookInfoData : {},

};

/**
 * 清除数据
 */
BookInfoDlg.clearData = function() {
    this.bookInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookInfoDlg.set = function(key, val) {
    this.bookInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BookInfoDlg.close = function() {
    parent.layer.close(window.parent.Book.layerIndex);
}



/**
 * 收集数据
 */
BookInfoDlg.collectData = function() {
    // this.set('id');
    this.bookInfoData = {
        id:$("#id").val(),
        isShow:$("#isShow").val()

    };
}







/**
 * 提交修改
 */
BookInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/book/update", function(data){
        Feng.success("修改成功!");
        window.parent.Book.table.refresh();
        BookInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookInfoData);
    ajax.start();
}




$(function() {

    //初始化下拉选项
    if($("#id").val()!=''){
        $("#isShow").val($("#isShowValue").val());
    }

});
