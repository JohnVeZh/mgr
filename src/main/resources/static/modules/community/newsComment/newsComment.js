/**
 * 资讯评论管理初始化
 */
var NewsComment = {
    id: "NewsCommentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};



/**
 * 检查是否选中
 */
NewsComment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        NewsComment.seItem = selected[0];
        return true;
    }
};

NewsComment.isTop = function (id,isTop) {
    var ajax = new $ax(Feng.ctxPath + "/community/news/comment/update", function (data) {
        Feng.success("成功!");
        NewsComment.table.refresh();
    }, function (data) {
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set("id",id);
    ajax.set("isTop",isTop);
    ajax.start();
};

/**
 * 点击添加资讯评论
 */
NewsComment.openAddNewsComment = function () {
    var index = layer.open({
        type: 2,
        title: '添加资讯评论',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/community/news/comment/add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看资讯评论详情
 */
NewsComment.openNewsCommentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '资讯评论详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/community/news/comment/update/' + NewsComment.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除资讯评论
 */
NewsComment.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/community/news/comment/delete", function (data) {
            Feng.success("删除成功!");
            NewsComment.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("newsCommentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询资讯评论列表
 */
NewsComment.search = function () {
    var queryData = {};
    queryData['content'] = $("#content").val();
    queryData['isTop'] = $("#isTop").val();
    queryData['userName'] = $("#userName").val();
    queryData['userPhone'] = $("#userPhone").val();
    NewsComment.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = NewsComment.initColumn();
    var table = new BSTable(NewsComment.id, "/community/news/comment/list", defaultColunms);
   // table.setPaginationType("client");
    NewsComment.table = table.init();
});
