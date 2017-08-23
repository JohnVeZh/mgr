/**
 * 初始化资讯评论详情对话框
 */
var NewsCommentInfoDlg = {
    newsCommentInfoData : {}
};

/**
 * 清除数据
 */
NewsCommentInfoDlg.clearData = function() {
    this.newsCommentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewsCommentInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.newsCommentInfoData[key] = val;
    }else{
        this.newsCommentInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewsCommentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NewsCommentInfoDlg.close = function() {
    parent.layer.close(window.parent.NewsComment.layerIndex);
}

/**
 * 收集数据
 */
NewsCommentInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
NewsCommentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/newsComment/add", function(data){
        Feng.success("添加成功!");
        window.parent.NewsComment.table.refresh();
        NewsCommentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newsCommentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NewsCommentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/newsComment/update", function(data){
        Feng.success("修改成功!");
        window.parent.NewsComment.table.refresh();
        NewsCommentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newsCommentInfoData);
    ajax.start();
}

$(function() {

});
