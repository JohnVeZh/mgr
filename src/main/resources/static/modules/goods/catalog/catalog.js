/**
 * 课程目录管理初始化
 */
var Catalog = {
    id: "CatalogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Catalog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '目录名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true},
        {title: '创建人', field: 'createBy', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remarks', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Catalog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Catalog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加课程目录
 */
Catalog.openAddCatalog = function () {
    var index = layer.open({
        type: 2,
        title: '添加课程目录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/catalog/catalog_add/'+$("#networkCourseId").val()
    });
    this.layerIndex = index;
};

/**
 * 打开查看课程目录详情
 */
Catalog.openCatalogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '课程目录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/catalog/catalog_update/' + Catalog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看视频管理目录
 */
Catalog.openVideoList = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '视频管理目录',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/video/' +$("#networkCourseId").val()+','+ Catalog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除课程目录
 */
Catalog.delete = function () {
    if (this.check()) {
        var operation=function () {
            var ajax = new $ax(Feng.ctxPath + "/goods/catalog/delete", function (data) {
                Feng.success("删除成功!");
                Catalog.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("catalogId",Catalog.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否刪除该目录?", operation);
    }
};

/**
 * 查询课程目录列表
 */
Catalog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Catalog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Catalog.initColumn();
    var table = new BSTable(Catalog.id, "/goods/catalog/list/"+$("#networkCourseId").val(), defaultColunms);
    table.setPaginationType("server");
    Catalog.table = table.init();
    // var query= {};
    // query['networkCourseId'] = $("#networkCourseId").val();
    // Catalog.table.refresh({query: query});
});
