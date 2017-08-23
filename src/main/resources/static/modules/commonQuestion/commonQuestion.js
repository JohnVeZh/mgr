/**
 * 常见问题管理初始化
 */
var CommonQuestion = {
    id: "CommonQuestionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 *  id, title, content, create_date, sort, create_by, update_date, update_by, del_flag
 */
CommonQuestion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
        {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'}

    ];
};

/**
 * 检查是否选中
 */
CommonQuestion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CommonQuestion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加常见问题
 */
CommonQuestion.openAddCommonQuestion = function () {
    var index = layer.open({
        type: 2,
        title: '添加常见问题',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/commonQuestion/commonQuestion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看常见问题详情
 */
CommonQuestion.openCommonQuestionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '常见问题详情',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/commonQuestion/commonQuestion_update/' + CommonQuestion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除常见问题
 */
CommonQuestion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/commonQuestion/delete", function (data) {
            Feng.success("删除成功!");
            CommonQuestion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("commonQuestionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询常见问题列表
 */
CommonQuestion.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    CommonQuestion.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CommonQuestion.initColumn();
    var table = new BSTable(CommonQuestion.id, "/commonQuestion/list", defaultColunms);
    table.setPaginationType("server");
    CommonQuestion.table = table.init();
});
