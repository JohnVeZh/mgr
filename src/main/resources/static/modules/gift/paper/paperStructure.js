/**
 * 试卷树形结构管理初始化
 */
var PaperStructure = {
    id: "PaperStructureTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    paperId:null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PaperStructure.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '别名', field: 'alias', visible: true, align: 'center', valign: 'middle'},
        {title: '类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PaperStructure.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PaperStructure.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加试卷树形结构
 */
PaperStructure.openAddPaperStructure = function () {
    var index = layer.open({
        type: 2,
        title: '添加试卷树形结构',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/paper/paperStructure/goAdd.do?type=6'
    });
    this.layerIndex = index;
};

/**
 * 打开查看试卷树形结构详情
 */
PaperStructure.openPaperStructureDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '试卷树形结构详情',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/paper/paperStructure/goUpdate/' + PaperStructure.seItem.id +'.do?type=6'
        });
        this.layerIndex = index;
    }
};

/**
 * 删除试卷树形结构
 */
PaperStructure.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/paper/paperStructure/delete", function (data) {
            Feng.success("删除成功!");
            PaperStructure.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 网课视频绑定
 */
PaperStructure.recommendCourse = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '网课视频绑定',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/gift/exercise/goRecommendCourse'
        });
        this.layerIndex = index;
    }
};

/**
 * 查询试卷树形结构列表
 */
PaperStructure.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PaperStructure.table.refresh({query: queryData});
};

PaperStructure.resetSearch = function () {
    $("#condition").val("");
    PaperStructure.search();
}

$(function () {
    PaperStructure.paperId = $('#paperId').val();

    var defaultColunms = PaperStructure.initColumn();
    var table = new BSTable(PaperStructure.id, "/paper/paperStructure/queryList.do?paperId=" + PaperStructure.paperId, defaultColunms);
    PaperStructure.table = table.init();
});
