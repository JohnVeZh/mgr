/**
 * 学习方案绑定网课列表管理初始化
 */
var ShowNetworkCourse = {
    id: "NetworkCourseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ShowNetworkCourse.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '封面图片', field: 'coverImg', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '网课名称', field: 'name', width:'15%', align: 'center', valign: 'middle', sortable: true},
        {title: '学段类型', field: 'sectionType', align: 'center', valign: 'middle', sortable: true},
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
        {title: '操作', field: '', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            var htmls='<a href="javascript:void(0)" onclick="ShowNetworkCourse.getThis(\''+row.id+'\',\''+row.name+'\')">绑定该网课</a>';
            return htmls;
        }}
    ];
};

/**
 * 绑定网课
 * @param id
 * @param name
 */
ShowNetworkCourse.getThis = function (id, name) {
    $("#networkCourseId").val(id);
    $("#networkCourseName").val(name);
    // $("#networkCoursetable").css("display","none");
    $("#mymodal").modal("hide");
};

PlanNetworkCourseInfoDlg.test = function () {
    // alert("1234");
    // $("#networkCourseId").val("1234");
    $("#mymodal").modal("toggle");
}

/**
 * 检查是否选中
 */
ShowNetworkCourse.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ShowNetworkCourse.seItem = selected[0];
        return true;
    }
};

/**
 * 查询网课列表
 */
ShowNetworkCourse.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    ShowNetworkCourse.table.refresh({query: queryData});
};

/**
 * 清空查询条件
 */
ShowNetworkCourse.resetSearch = function () {
    $("#condition").val("");
    $("#sectionCode").val("");
    ShowNetworkCourse.search();
}

$(function () {
    var defaultColunms = ShowNetworkCourse.initColumn();
    var table = new BSTable(ShowNetworkCourse.id, "/goods/networkCourse/list", defaultColunms);
    table.setPaginationType("server");
    ShowNetworkCourse.table = table.init();
    ShowNetworkCourse.search();
});
