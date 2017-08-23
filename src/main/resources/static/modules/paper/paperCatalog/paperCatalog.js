/**
 * 试卷目录管理初始化
 */
var PaperCatalog = {
    id: "PaperCatalogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PaperCatalog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '目录名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '类型', visible: true, align: 'center', valign: 'middle',formatter: function(value,row,index){
            return row.typeName;
        }},
        {title: '学段', visible: true, align: 'center',valign: 'middle',formatter: function(value,row,index){
            return row.sectionCodeName;
        }},
        {title: '二维码', field: 'qrCode', visible: true, align: 'center', valign: 'middle'},
        // {title: '级别', field: 'level', visible: true, align: 'center', valign: 'middle'},
        // {title: '父id', field: 'parentId', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PaperCatalog.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PaperCatalog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加试卷目录
 */
PaperCatalog.openAddPaperCatalog = function () {
    var index = layer.open({
        type: 2,
        title: '添加试卷目录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/paper/paperCatalog/paperCatalog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看试卷目录详情
 */
PaperCatalog.openPaperCatalogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '试卷目录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/paper/paperCatalog/paperCatalog_update/' + PaperCatalog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除试卷目录
 */
PaperCatalog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/paper/paperCatalog/check", function (data) {
            if (data == 'parent') Feng.error("请先删除子级目录!");
            if (data == 'paper') Feng.error("请先删除试卷依赖!");
            if (data == 'success') {
                var ajax = new $ax(Feng.ctxPath + "/paper/paperCatalog/delete", function (data1) {
                    Feng.success("删除成功!");
                    PaperCatalog.table.refresh();
                }, function (data1) {
                    Feng.error("删除失败!" + data1.responseJSON.message + "!");
                });
                ajax.set("id",PaperCatalog.seItem.id);
                ajax.start();
            }
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",PaperCatalog.seItem.id);
        ajax.start();
    }
};

/**
 * 查询试卷目录列表
 */
PaperCatalog.search = function () {
    var queryData = {};
    queryData['name'] = $("#condition").val();
    queryData['type'] = $("#type").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    PaperCatalog.table.refresh({query: queryData});
};

/**
 * 重置
 */
PaperCatalog.resetSearch = function () {
    $('#condition').val('');
    $('#type').val('');
    $('#sectionCode').val('');
    PaperCatalog.search();
};

$(function () {
    //初始化学段
    var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
        $.each(data,function(i,item){
            $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
        });
    }, function (data) {
        Feng.error("获取学段失败!" + data.responseJSON.message + "!");
    });
    ajax.start();

    var defaultColunms = PaperCatalog.initColumn();
    // var table = new BSTable(PaperCatalog.id, "/paper/paperCatalog/list", defaultColunms);
    // table.setPaginationType("client");
    // PaperCatalog.table = table.init();
    var table = new BSTreeTable(PaperCatalog.id, "/paper/paperCatalog/list", defaultColunms);
    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    table.init();
    PaperCatalog.table = table;
});
