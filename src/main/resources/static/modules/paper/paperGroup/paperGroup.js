/**
 * 试卷组管理初始化
 */
var PaperGroup = {
    id: "PaperGroupTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PaperGroup.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '组图片', field: 'img', visible: true, align: 'center', valign: 'middle',width:'20%',formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '组名称', field: 'name', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PaperGroup.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PaperGroup.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加试卷组
 */
PaperGroup.openAddPaperGroup = function () {
    var index = layer.open({
        type: 2,
        title: '添加试卷组',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/paper/paperGroup/paperGroup_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看试卷组详情
 */
PaperGroup.openPaperGroupDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '试卷组详情',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/paper/paperGroup/paperGroup_update/' + PaperGroup.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除试卷组
 */
PaperGroup.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/paper/paperGroup/delete", function (data) {
            Feng.success("删除成功!");
            PaperGroup.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("paperGroupId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询试卷组列表
 */
PaperGroup.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PaperGroup.table.refresh({query: queryData});
};

/**
 * 查询试卷组列表
 */
PaperGroup.resetSearch = function () {
    $("#condition").val('');
    PaperGroup.search();
};

$(function () {
    var defaultColunms = PaperGroup.initColumn();
    var table = new BSTable(PaperGroup.id, "/paper/paperGroup/list", defaultColunms);
    PaperGroup.table = table.init();
});
