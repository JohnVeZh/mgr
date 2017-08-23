/**
 * 社区活动管理初始化
 */
var Activity = {
    id: "ActivityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 检查是否选中
 */
Activity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Activity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加社区活动
 */
Activity.openAddActivity = function () {
    var index = layer.open({
        type: 2,
        title: '添加社区活动',
        area: ['1000px', '700px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/community/activity/activity_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看社区活动详情
 */
Activity.openActivityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '社区活动详情',
            area: ['1000px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/community/activity/activity_update/' + Activity.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除社区活动
 */
Activity.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/community/activity/delete", function (data) {
            Feng.success("删除成功!");
            Activity.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};
//取消推荐
Activity.disRecommend = function (id) {
    //询问框
    var indexConfirm = layer.confirm('取消推荐？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        var ajax = new $ax(Feng.ctxPath + "/community/activity/disRecommend", function (data) {
            Feng.success("取消推荐成功!");
            Activity.table.refresh();
        }, function (data) {
            Feng.error("取消推荐失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", id);
        ajax.start();
        layer.close(indexConfirm);
    });

};
//推荐
Activity.recommend = function (id) {
    //询问框
    var indexConfirm = layer.confirm('推荐？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        var ajax = new $ax(Feng.ctxPath + "/community/activity/recommend", function (data) {
            Feng.success("推荐成功!");
            Activity.table.refresh();
        }, function (data) {
            Feng.error("推荐失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", id);
        ajax.start();
        layer.close(indexConfirm);
    });
};

//取消置顶
Activity.disTop = function (id) {
    //询问框
    var indexConfirm = layer.confirm('取消置顶？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        var ajax = new $ax(Feng.ctxPath + "/community/activity/disTop", function (data) {
            Feng.success("取消置顶成功!");
            Activity.table.refresh();
        }, function (data) {
            Feng.error("取消置顶失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",id);
        ajax.start();
        layer.close(indexConfirm);
    });
};
//置顶
Activity.top = function (id) {
//询问框
    var indexConfirm = layer.confirm('置顶？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        var ajax = new $ax(Feng.ctxPath + "/community/activity/top", function (data) {
            Feng.success("置顶成功!");
            Activity.table.refresh();
        }, function (data) {
            Feng.error("置顶失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",id);
        ajax.start();
        layer.close(indexConfirm);
    });
};

/**
 * 查询社区活动列表
 */
Activity.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['isShow'] = $("#isShow").val();
    queryData['isTop'] = $("#isTop").val();
    queryData['isRecommend'] = $("#isRecommend").val();
    Activity.table.refresh({query: queryData});
};

Activity.resetSearch = function () {
    $("#title").val('');
    $("#isShow").val('');
    $("#isTop").val('');
    $("#isRecommend").val('');
};

$(function () {
    var defaultColunms = Activity.initColumn();
    var table = new BSTable(Activity.id, "/community/activity/list", defaultColunms);
    table.setPaginationType("server");
    Activity.table = table.init();
});
