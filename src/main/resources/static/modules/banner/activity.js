/**
 * 社区活动管理初始化
 */
var Activity = {
    id: "ActivityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
Activity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title',  align: 'center', valign: 'middle'},
        // {title: '客服展示', field: 'serviceStatus',  align: 'center', valign: 'middle',formatter : function (e,column,index) {
        //     if(column.serviceStatus == 1){
        //         return '<span class="label label-primary">显示</span>';
        //     }else{
        //         return '<span class="label label-default">隐藏</span>';
        //     }
        // }},
        {title: '是否展示', field: 'serviceStatus',  align: 'center', valign: 'middle',formatter : function (e,column,index) {
            if(column.isShow == 1){
                return '<span class="label label-primary">显示</span>';
            }else{
                return '<span class="label label-default">草稿箱</span>';
            }
        }},
        {title: '点击数量', width:10 ,field: 'readNum',  align: 'center', valign: 'middle'},
        {title: '评论数量', width:10 ,field: 'commentNum',  align: 'center', valign: 'middle'},
        {title: '点赞数量', width:10 ,field: 'likeNum',  align: 'center', valign: 'middle'},
        {title: '关注数量', width:10 ,field: 'attentionNum',  align: 'center', valign: 'middle'},
        {title: '标识', field: 'show', width: 180, align: 'center', valign: 'middle',formatter : function (e,column,index) {
            var htmlElement = '';
            if(column.isTop == 1){
                htmlElement += '<span class="label label-primary">置顶</span>&nbsp;&nbsp;';
            }
            if(column.isRecommend == 1){
                htmlElement += '<span class="label label-success">推荐</span>&nbsp;&nbsp;';
            }
            return htmlElement;
        }},
        {title: '操作', field: '',  width: 150, align: 'center', valign: 'middle',formatter : editFormatter}
    ];
};

/**
 * 检查是否选中
 */
Activity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Activity.seItem = selected[0];
        return true;
    }
};

/**
 * 查询社区活动列表
 */
Activity.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Activity.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Activity.initColumn();
    var table = new BSTable(Activity.id, "/banner/activity/list", defaultColunms);
    table.setPaginationType("server");
    Activity.table = table.init();
});
function editFormatter(value,row,index) {
    var menu = ' <a onclick="addBook(\''+row.id+'\',\''+row.title+'\')" title="添加"><span class="label label-default">添加</span></a>';
    return menu;
}
function addBook(id,name) {
    $("#contentTitle", parent.document).val(name);
    $("#contentId", parent.document).val(id);
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}