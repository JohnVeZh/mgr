/**
 * 网课管理初始化
 */
var NetworkCourse = {
    id: "NetworkCourseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
NetworkCourse.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '封面图片', field: 'coverImg', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '网课名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '网课级别类型', field: 'sectionType', align: 'center', valign: 'middle', sortable: true},
        // {title: '现价', field: 'presentPrice', align: 'center', valign: 'middle', sortable: true},
        // {title: '实际销量', field: 'saleNum', align: 'center', valign: 'middle', sortable: true},
        {title: '网课形式', field: 'isLive', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label label-success">录播</span>';
                case 1:
                    return '<span class="label label-primary">直播</span>';
                default:
                    break;
            }
        }},
        {title: '网课状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label badge-default">待发布</span>';
                case 1:
                    return '<span class="label label-info">发布中</span>';
                case 2:
                    return '<span class="label label-success">预约中</span>';
                case 3:
                    return '<span class="label label-primary">开售中</span>';
                case 4:
                    return '<span class="label label-warning">已停售</span>';
                case 5:
                    return '<span class="label label-danger">已下架</span>';
                default:
                    break;
            }
        }},
        // {title: '目录', field: 'hasCatalog', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
        //     switch(value) {
        //         case 0:
        //             return '<span class="label label-danger">不存在</span>';
        //         case 1:
        //             return '<span class="label label-primary">存在</span>';
        //         default:
        //             break;
        //     }
        // }},
        {title: '开始时间', field: 'startTime', align: 'center', valign: 'middle', sortable: true},
        {title: '结束时间', field: 'endTime', align: 'center', valign: 'middle', sortable: true},
        {title: '操作', field: 'edit', align: 'center', valign: 'middle', sortable: true,formatter:editFormatter}
    ];
};


/*
function rowActionFormatter(value, row, index) {
    html = [];
    if(row['hasCatalog'].toString() == "1") {
        html.push('<a title="添加目录" href="javascript:;"  onclick="openCatalogLayer(\'' + row.id + '\')"><i class="glyphicon glyphicon-th-list"></i>&nbsp;</a>&nbsp;');
    }
    return html.join('&nbsp;&nbsp;');
}
 */

/**
 * 检查是否选中
 */
NetworkCourse.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        NetworkCourse.seItem = selected[0];
        return true;
    }
};

/**
 * 查询网课列表
 */
NetworkCourse.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    queryData['courseType'] = $("#courseType").val();
    queryData['isLive'] = $("#isLive").val();
    NetworkCourse.table.refresh({query: queryData});
};


/**
 * 重置搜索条件
 */
NetworkCourse.resetSearch = function () {
    $("#condition").val("");
    $("#sectionCode").val("");
    $("#courseType").val("");
    $("#isLive").val("");
    NetworkCourse.search();
};

$(function () {
    var defaultColunms = NetworkCourse.initColumn();
    var table = new BSTable(NetworkCourse.id, "/banner/network/list", defaultColunms);
    table.setPaginationType("server");
    NetworkCourse.table = table.init();


});
function editFormatter(value,row,index) {
    var menu = ' <a onclick="addBook(\''+row.id+'\',\''+row.name+'\')" title="添加"><span class="label label-default">添加</span></a>';
    return menu;
}
function addBook(id,name) {
    $("#contentTitle", parent.document).val(name);
    $("#contentId", parent.document).val(id);
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}