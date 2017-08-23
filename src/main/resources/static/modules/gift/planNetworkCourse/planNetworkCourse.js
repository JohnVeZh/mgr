/**
 * 学习方案绑定网课列表管理初始化
 */
var PlanNetworkCourse = {
    id: "PlanNetworkCourseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PlanNetworkCourse.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'planId', field: 'planId', visible: false, align: 'center', valign: 'middle'},
        {title: '展示名称', field: 'title', align: 'center', valign: 'middle'},
        {title: '封面图片', field: 'coverImg', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '网课名称', field: 'name', width:'15%', align: 'center', valign: 'middle', sortable: true},
        {title: '学段类型形式', field: 'mold', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'sort', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
PlanNetworkCourse.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PlanNetworkCourse.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加学习方案绑定网课列表
 */
PlanNetworkCourse.openAddPlanNetworkCourse = function () {
    var index = layer.open({
        type: 2,
        title: '添加学习方案绑定网课列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/gift/planNetworkCourse/planNetworkCourse_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看学习方案绑定网课列表详情
 */
PlanNetworkCourse.openPlanNetworkCourseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '学习方案绑定网课列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/gift/planNetworkCourse/planNetworkCourse_update/' + PlanNetworkCourse.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除学习方案绑定网课列表
 */
PlanNetworkCourse.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/gift/planNetworkCourse/delete", function (data) {
            Feng.success("删除成功!");
            PlanNetworkCourse.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("planNetworkCourseId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询学习方案绑定网课列表列表
 */
PlanNetworkCourse.search = function () {
    var queryData = {};
    queryData['ncName'] = $("#ncName").val();
    PlanNetworkCourse.table.refresh({query: queryData});
};

/**
 * 清空查询条件
 */
PlanNetworkCourse.resetSearch = function () {
    $("#ncName").val("");
    PlanNetworkCourse.search();
}

$(function () {
    var defaultColunms = PlanNetworkCourse.initColumn();
    var table = new BSTable(PlanNetworkCourse.id, "/gift/planNetworkCourse/list", defaultColunms);
    table.setPaginationType("server");
    PlanNetworkCourse.table = table.init();
});
