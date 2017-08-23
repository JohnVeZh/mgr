/**
 * 词汇栏目管理管理初始化
 */
var WordsCatalog = {
    id: "WordsCatalogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */

WordsCatalog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        //{title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name',  align: 'center', valign: 'middle'},
        {title: '类型', field: 'type',  align: 'center', valign: 'middle',formatter : function (e,column,index) {
            var htmlElement = '';
            if(column.type == 1){
                htmlElement += '<span class="label label-success">常用词汇</span>&nbsp;&nbsp;';
            }else if(column.type == 2){
                htmlElement += '<span class="label label-info">专项练习</span>&nbsp;&nbsp;';
            }
            return htmlElement;
        }},

        {title: 'code', field: 'code',  align: 'center', valign: 'middle'},
        {title: '学段', field: 'sectionCode',  align: 'center', valign: 'middle',formatter : function (e,column,index) {
            var htmlElement = '';
            if(column.sectionCode == 1){
                htmlElement += '<span class="label">四级</span>&nbsp;&nbsp;';
            }else if(column.sectionCode == 2){
                htmlElement += '<span class="label label-primary">六级</span>&nbsp;&nbsp;';
            }else {
                htmlElement += '<span class="label label-success">其他</span>&nbsp;&nbsp;';
            }
            return htmlElement;
        }},
        {title: '节点', field: 'isLeaf',  align: 'center', valign: 'middle',formatter : function (e,column,index) {
            var htmlElement = '';
            if(column.isLeaf == 1){
                htmlElement += '<span class="label label-warning">叶子节点</span>&nbsp;&nbsp;';
            }else{

            }
            return htmlElement;
        }},
        {title: '单词总数', field: 'totalNum',  align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
WordsCatalog.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WordsCatalog.seItem = selected[0];
        return true;
    }
};
/**
 * 点击添加词汇栏目管理
 */
WordsCatalog.openAddRootWordsCatalog = function () {
    var index = layer.open({
        type: 2,
        title: '添加词汇栏目管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wordsCatalog/wordsCatalog_add_root'
    });
    this.layerIndex = index;
    layer.full(index);
};
/**
 * 点击添加词汇栏目管理
 */
WordsCatalog.openAddWordsCatalog = function () {
    var index = layer.open({
        type: 2,
        title: '添加词汇栏目管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wordsCatalog/wordsCatalog_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看词汇栏目管理详情
 */
WordsCatalog.openWordsCatalogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '词汇栏目管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wordsCatalog/wordsCatalog_update/' + WordsCatalog.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除词汇栏目管理
 */
WordsCatalog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wordsCatalog/delete", function (data) {
            Feng.success("删除成功!");
            WordsCatalog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wordsCatalogId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询词汇栏目管理列表
 */
WordsCatalog.search = function () {
    var queryData = {};
    queryData['type'] = $("#type").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    // queryData['isLeaf'] = $("#isLeaf").val();
    WordsCatalog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = WordsCatalog.initColumn();
    var table = new BSTreeTable(WordsCatalog.id, "/wordsCatalog/list", defaultColunms);
    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("parentId");
    table.setRootCodeValue("0");
    table.setExpandAll(false);
    table.init();
    WordsCatalog.table = table;
});
