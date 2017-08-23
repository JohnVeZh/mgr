/**
 * 课程推荐管理初始化
 */
var GiftRecommendCourse = {
    id: "GiftRecommendCourseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GiftRecommendCourse.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '学段', field: 'sectionCodeName', visible: true, align: 'center', valign: 'middle'},
        {title: '网课名称', field: 'networkCourseName', visible: true, align: 'center', valign: 'middle'}
        // ,
        // {title: '网课视频', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {
        //     return '<a href="javascript:;" onclick="GiftRecommendCourse.networkCourseVideo(\'' + row.networkCourseId + '\');">网课视频</a><br/>';
        // }}
    ];
};

/**
 * 网课视频
 */
GiftRecommendCourse.networkCourseVideo = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '网课视频',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/gift/exercise/goNetworkCourseVideoList'
        });
        this.layerIndex = index;
    }
};

/**
 * 检查是否选中
 */
GiftRecommendCourse.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        GiftRecommendCourse.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加课程推荐
 */
GiftRecommendCourse.openAddGiftRecommendCourse = function () {
    var index = layer.open({
        type: 2,
        title: '添加课程推荐',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/giftRecommendCourse/giftRecommendCourse_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看课程推荐详情
 */
GiftRecommendCourse.openGiftRecommendCourseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '课程推荐详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/giftRecommendCourse/giftRecommendCourse_update/' + GiftRecommendCourse.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除课程推荐
 */
GiftRecommendCourse.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/giftRecommendCourse/delete", function (data) {
            Feng.success("删除成功!");
            GiftRecommendCourse.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("giftRecommendCourseId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询课程推荐列表
 */
GiftRecommendCourse.search = function () {
    var queryData = {};
    queryData['networkCourseName'] = $("#condition").val();
    GiftRecommendCourse.table.refresh({query: queryData});
};

/**
 * 重置
 */
GiftRecommendCourse.resetSearch = function () {
    $("#condition").val('');
    GiftRecommendCourse.search();
};

$(function () {
    var ajax = new $ax(Feng.ctxPath + "/paper/paperCatalog/detail/" + window.parent.parent.Paper.seItem.catalogId, function (data) {
        var defaultColunms = GiftRecommendCourse.initColumn();
        var table = new BSTable(GiftRecommendCourse.id, "/gift/recommendCourse/list.do?sectionCode=" + data.sectionCode, defaultColunms);
        GiftRecommendCourse.table = table.init();
    }, function (data) {

    });
    ajax.start();

});
