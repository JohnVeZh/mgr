/**
 * 分析建议管理初始化
 */
var SpecialSuggestion = {
    id: "SpecialSuggestionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SpecialSuggestion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '下限分数', field: 'start', visible: true, align: 'center', valign: 'middle'},
        {title: '上限分数', field: 'end', visible: true, align: 'center', valign: 'middle'},
        {title: '专项类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '学段', field: 'sectionCodeName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SpecialSuggestion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SpecialSuggestion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加分析建议
 */
SpecialSuggestion.openAddSpecialSuggestion = function () {
    var index = layer.open({
        type: 2,
        title: '添加分析建议',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/paper/specialSuggestion/specialSuggestion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看分析建议详情
 */
SpecialSuggestion.openSpecialSuggestionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '分析建议详情',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/paper/specialSuggestion/specialSuggestion_update/' + SpecialSuggestion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除分析建议
 */
SpecialSuggestion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/paper/specialSuggestion/delete", function (data) {
            Feng.success("删除成功!");
            SpecialSuggestion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("specialSuggestionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询分析建议列表
 */
SpecialSuggestion.search = function () {
    var queryData = {};
    queryData['type'] = $("#type").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    SpecialSuggestion.table.refresh({query: queryData});
};

/**
 * 重置
 */
SpecialSuggestion.resetSearch = function () {
    $('#type').val('');
    $('#sectionCode').val('');
    SpecialSuggestion.search();
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

    var defaultColunms = SpecialSuggestion.initColumn();
    var table = new BSTable(SpecialSuggestion.id, "/paper/specialSuggestion/list", defaultColunms);
    SpecialSuggestion.table = table.init();
});
