/**
 * 社区资讯管理初始化
 */
var CommunityNews = {
    id: "CommunityNewsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

//列表图片
//private String listImg;
/**
 * 初始化表格的列
 */
CommunityNews.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title',  align: 'center', valign: 'middle'},
        {title: '副标题', field: 'subtitle', align: 'center', valign: 'middle'},
        {title: '作者', field: 'author',  align: 'center', valign: 'middle'},
        {title: '阅读数', field: 'readNum',  align: 'center', valign: 'middle'},
        {title: '发布', field: 'isShow',  align: 'center', valign: 'middle' , formatter : function (e,column,index) {
            var htmlElement = '';
            if(column.isShow == 1){
                htmlElement += '<span class="label label-primary">已发布</span>&nbsp;&nbsp;';
            }else{
                htmlElement += '<span class="label label-default">草稿箱</span>';
            }
            return htmlElement;
        }},
        {title: '标识', field: 'show',  align: 'center', valign: 'middle',formatter : function (e,column,index) {
            var htmlElement = '';
            if(column.isTop == 1){
                htmlElement += '<span class="label label-primary">置顶</span>&nbsp;&nbsp;';
            }
            if(column.isRecommend == 1){
                htmlElement += '<span class="label label-success">推荐</span>&nbsp;&nbsp;';
            }
            return htmlElement;
        }},
        {title: '操作', field: 'option', align: 'center', valign: 'middle',formatter : editFormatter}
    ];
};

/**
 * 检查是否选中
 */
CommunityNews.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CommunityNews.seItem = selected[0];
        return true;
    }
};



/**
 * 查询社区资讯列表
 */
CommunityNews.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CommunityNews.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CommunityNews.initColumn();
    var table = new BSTable(CommunityNews.id, "/banner/new/list", defaultColunms);
    table.setPaginationType("server");
    CommunityNews.table = table.init();
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