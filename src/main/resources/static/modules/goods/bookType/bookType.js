/**
 * 图书类型管理初始化
 */
var BookType = {
    id: "BookTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BookType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true},
        {title: '创建人', field: 'createBy', align: 'center', valign: 'middle', sortable: true},
        {title: '修改时间', field: 'updateDate', align: 'center', valign: 'middle', sortable: true},
        {title: '修改人', field: 'updateBy', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remarks', align: 'center', valign: 'middle', sortable: true}

    ];
};

/**
 * 检查是否选中
 */
BookType.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BookType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加图书类型
 */
BookType.openAddBookType = function () {
    var index = layer.open({
        type: 2,
        title: '添加图书类型',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/bookType/bookType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看图书类型详情
 */
BookType.openBookTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '图书类型详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/bookType/bookType_update/' + BookType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除图书类型
 */
BookType.delete = function () {

    if (this.check()) {

            var operation = function () {
                var ajax = new $ax(Feng.ctxPath + "/goods/bookType/delete", function () {
                    Feng.success("删除成功!");
                    BookType.table.refresh();
                }, function (data) {
                    Feng.error("删除失败!" + data.responseJSON.message + "!");
                });
                ajax.set("bookTypeId", BookType.seItem.id);
                ajax.start();
            };
        Feng.confirm("是否刪除该图书类型?", operation);

    };

};

/**
 * 查询图书类型列表
 */
BookType.search = function () {
    var queryData = {};
    queryData['name'] = $("#condition").val();
    BookType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BookType.initColumn();
    // var table = new BSTable(BookType.id, "/goods/bookType/list", defaultColunms);
    // table.setPaginationType("client");
    var table = new BSTreeTable(BookType.id, "/goods/bookType/list", defaultColunms);
    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setRootCodeValue("FFFFFF");
    table.setExpandAll(true);
    table.init();
    BookType.table = table;
});
