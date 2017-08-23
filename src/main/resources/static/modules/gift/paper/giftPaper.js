/**
 * 试卷管理初始化
 */
var Paper = {
    id: "PaperTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    catalogid: 0,
    tree: null,
    specialExplainLayerIndex: 0,
    specialSuggestionLayerIndex: 0
};

/**
 * 初始化表格的列
 */
Paper.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {
            title: '试卷图片',
            field: 'img',
            visible: true,
            align: 'center',
            valign: 'middle',
            width: '20%',
            formatter: function (value, row, index) {
                return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
            }
        },
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: 'contentType', field: 'contentType', visible: false, align: 'center', valign: 'middle'},
        {title: '题目总数', field: 'questionNum', visible: true, align: 'center', valign: 'middle'}

    ];
};

/**
 * 检查是否选中
 */
Paper.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Paper.seItem = selected[0];
        return true;
    }
};

Paper.resetSearch = function () {
    $("#condition").val("");
    Paper.catalogid = null;
    Paper.search();
}

/**
 * 查询试卷列表
 */
Paper.search = function () {
    var queryData = {};
    queryData['queryName'] = $("#condition").val();
    if (Paper.catalogid != '0') {
        queryData['catalogId'] = Paper.catalogid;
    }
    // alert(queryData.name);
    Paper.table.refresh({query: queryData});
};

Paper.onClickCatalog = function (e, treeId, treeNode) {
    if (treeNode.level > 1) {
        Paper.catalogid = treeNode.id;
        Paper.search();
    }
};

/**
 * 试卷结构管理
 */
Paper.paperStructManage = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '试卷结构管理',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/paper/paperStructure/goList?type=6&paperId=' + Paper.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 试卷题目管理
 */
Paper.paperQuestionManage = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '试卷题目管理',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/question/goList?type=6&paperId=' + Paper.seItem.id
        });
        this.layerIndex = index;
    }
};

$(function () {
    var defaultColunms = Paper.initColumn();
    var table = new BSTable(Paper.id, "/paper/list.do?catalogType=6", defaultColunms);
    Paper.table = table.init();
    var ztree = new $ZTree("paperCatalogTree", "/paper/paperCatalog/catalogTree.do?type=6");
    ztree.bindOnClick(Paper.onClickCatalog);
    Paper.tree = ztree.init();
});
