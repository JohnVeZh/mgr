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
        {title: '题目总数', field: 'questionNum', visible: true, align: 'center', valign: 'middle'}
        // {title: '操作',visible: true, align: 'center', valign: 'middle',formatter: function(value,row,index){
        //     return '<a href="javascript:;" onclick="Paper.specialExplain(\'' + row.id + '\');">专项讲解</a><br/>'
        //         + '<a href="javascript:;" onclick="Paper.specialSuggestion(\'' + row.id + '\');">分析建议</a><br/>';
        //         // + '<a href="javascript:;" onclick="Paper.paperStructure(\'' + row.id + '\');">试卷结构</a>';
        // }}
    ];
};

Paper.specialExplain = function (id) {
    var index = layer.open({
        type: 2,
        title: '专项讲解',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/paper/specialExplain/specialExplain_update/' + id
    });
    Paper.specialExplainLayerIndex = index;
}

Paper.specialSuggestion = function (id) {
    var index = layer.open({
        type: 2,
        title: '分析建议',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/paper/specialSuggestion/specialSuggestion_update/' + id
    });
    Paper.specialSuggestionLayerIndex = index;
}

Paper.paperStructure = function (id) {
    alert("试卷结构" + id);
}

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

/**
 * 点击导入
 */
Paper.openUpload = function () {
    var index = layer.open({
        type: 2,
        title: '导入试卷',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/paper/upload'
    });
    this.layerIndex = index;
};

/**
 * 点击添加试卷
 */
Paper.openAddPaper = function () {
    var index = layer.open({
        type: 2,
        title: '添加试卷',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/paper/paper_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看试卷详情
 */
Paper.openPaperDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '试卷详情',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/paper/paper_update/' + Paper.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除试卷
 */
Paper.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/paper/delete", function (data) {
            Feng.success("删除成功!");
            Paper.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", this.seItem.id);
        ajax.start();
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
    if (treeNode.level > 2) {
        Paper.catalogid = treeNode.id;
        Paper.search();
    }
};
/**
 * 试卷结构管理
 */
Paper.paperStructManage = function () {

    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/paper/paperType", function (data) {
            if (data == "1" || data == "3" || data == "4") {

                var index = layer.open({
                    type: 2,
                    title: '试卷结构管理',
                    area: ['100%', '100%'], //宽高
                    fix: true, //固定
                    content: Feng.ctxPath + '/paper/paperStructure/paperStructureList?paperId=' + Paper.seItem.id
                });
                this.layerIndex = index;
            }
        }, function (data) {

        });
        ajax.set("id",Paper.seItem.id);
        ajax.start();
    }


};
$(function () {
    var defaultColunms = Paper.initColumn();
    var table = new BSTable(Paper.id, "/paper/list", defaultColunms);
    table.setPaginationType("server");
    Paper.table = table.init();
    var ztree = new $ZTree("paperCatalogTree", "/paper/paperCatalog/sectionTreeList");
    ztree.bindOnClick(Paper.onClickCatalog);
    Paper.tree = ztree.init();
});
