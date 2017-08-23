/**
 * 专项讲解管理初始化
 */
var SpecialExplain = {
    id: "SpecialExplainTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SpecialExplain.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '专项类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '学段', field: 'sectionCodeName', visible: true, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '访问次数', field: 'visitNum', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SpecialExplain.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SpecialExplain.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加专项讲解
 */
SpecialExplain.openAddSpecialExplain = function () {
    var index = layer.open({
        type: 2,
        title: '添加专项讲解',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/paper/specialExplain/specialExplain_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看专项讲解详情
 */
SpecialExplain.openSpecialExplainDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '专项讲解详情',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/paper/specialExplain/specialExplain_update/' + SpecialExplain.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除专项讲解
 */
SpecialExplain.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/paper/specialExplain/delete", function (data) {
            Feng.success("删除成功!");
            SpecialExplain.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("specialExplainId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询专项讲解列表
 */
SpecialExplain.search = function () {
    var queryData = {};
    queryData['queryTitle'] = $("#condition").val();
    queryData['type'] = $("#type").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    SpecialExplain.table.refresh({query: queryData});
};

/**
 * 重置
 */
SpecialExplain.resetSearch = function () {
    $('#condition').val('');
    $('#type').val('');
    $('#sectionCode').val('');
    SpecialExplain.search();
};

$(function () {
    // //初始化学段
    // var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
    //     $.each(data,function(i,item){
    //         $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
    //     });
    // }, function (data) {
    //     Feng.error("获取学段失败!" + data.responseJSON.message + "!");
    // });
    // ajax.start();

    var defaultColunms = SpecialExplain.initColumn();
    var table = new BSTable(SpecialExplain.id, "/paper/specialExplain/list", defaultColunms);
    SpecialExplain.table = table.init();
});
