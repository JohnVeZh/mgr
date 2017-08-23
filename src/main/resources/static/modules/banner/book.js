/**
 * 图书管理初始化
 */
var Book = {
    id: "BookTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Book.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '图书名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '列表图片', field: 'listImg', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '封面图片', field: 'coverImg', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '原价', field: 'originalPrice', align: 'center', valign: 'middle', sortable: true},
        {title: '现价', field: 'presentPrice', align: 'center', valign: 'middle', sortable: true},
        {title: '邮费', field: 'postage', align: 'center', valign: 'middle', sortable: true},
        {title: '类型', field: 'bookTypeName', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true},
        {title: '创建人', field: 'createBy', align: 'center', valign: 'middle', sortable: true},
        {title: '操作', field: 'edit', align: 'center', valign: 'middle', sortable: true,formatter:editFormatter}
    ];
};

/**
 * 检查是否选中
 */
Book.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Book.seItem = selected[0];
        return true;
    }
};

/**
 * 重置搜索条件
 */
Book.resetSearch = function () {
    $("#condition").val("");
    $("#endDate").val("");
    $("#startDate").val("");
    Book.search();
};

/**
 * 查询图书列表
 */
Book.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['endDate'] = $("#endDate").val();
    queryData['startDate'] = $("#startDate").val();
    Book.table.refresh({query: queryData});
};


$(function () {
    var defaultColunms = Book.initColumn();
    var table = new BSTable(Book.id, "/banner/book/list", defaultColunms);
    table.setPaginationType("server");
    Book.table = table.init();
});

function editFormatter(value,row,index) {
    var menu = ' <a onclick="addBook(\''+row.id+'\',\''+row.name+'\')" title="添加"><span class="label label-default">添加</span></a>';
    return menu;
}
function addBook(id,name) {
    $("#contentTitle", parent.document).val(name);
    $("#contentId", parent.document).val(id);
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}