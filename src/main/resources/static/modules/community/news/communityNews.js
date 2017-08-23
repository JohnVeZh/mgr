/**
 * 社区资讯管理初始化
 */
var CommunityNews = {
    id: "CommunityNewsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 检查是否选中
 */
CommunityNews.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CommunityNews.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加社区资讯
 */
CommunityNews.openAddCommunityNews = function () {
    var index = layer.open({
        type: 2,
        title: '添加社区资讯',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/community/news/add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看社区资讯详情
 */
CommunityNews.openCommunityNewsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '社区资讯详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/community/news/update/' + CommunityNews.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除社区资讯
 */
CommunityNews.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/community/news/delete", function (data) {
            Feng.success("删除成功!");
            CommunityNews.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("communityNewsId",this.seItem.id);
        ajax.start();
    }
};
//取消推荐
CommunityNews.disRecommend = function (id) {
    var ajax = new $ax(Feng.ctxPath + "/community/news/disRecommend", function (data) {
        Feng.success("取消推荐成功!");
        CommunityNews.table.refresh();
    }, function (data) {
        Feng.error("取消推荐失败!" + data.responseJSON.message + "!");
    });
    ajax.set("id", id);
    ajax.start();
};
//推荐
CommunityNews.recommend = function (id) {
    var ajax = new $ax(Feng.ctxPath + "/community/news/recommend", function (data) {
        Feng.success("推荐成功!");
        CommunityNews.table.refresh();
    }, function (data) {
        Feng.error("推荐失败!" + data.responseJSON.message + "!");
    });
    ajax.set("id", id);
    ajax.start();
};

//取消置顶
CommunityNews.disTop = function (id) {
    var ajax = new $ax(Feng.ctxPath + "/community/news/disTop", function (data) {
        Feng.success("取消置顶成功!");
        CommunityNews.table.refresh();
    }, function (data) {
        Feng.error("取消置顶失败!" + data.responseJSON.message + "!");
    });
    ajax.set("id",id);
    ajax.start();
};
//置顶
CommunityNews.top = function (id) {

    var ajax = new $ax(Feng.ctxPath + "/community/news/top", function (data) {
        Feng.success("置顶成功!");
        CommunityNews.table.refresh();
    }, function (data) {
        Feng.error("置顶失败!" + data.responseJSON.message + "!");
    });
    ajax.set("id",id);
    ajax.start();
};

/**
 * 查询社区资讯列表
 */
CommunityNews.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['isShow'] = $("#isShow").val();
    queryData['isTop'] = $("#isTop").val();
    queryData['isRecommend'] = $("#isRecommend").val();
    queryData['author'] = $("#author").val();
    CommunityNews.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CommunityNews.initColumn();
    var table = new BSTable(CommunityNews.id, "/community/news/list", defaultColunms);
    CommunityNews.table = table.init();
});
