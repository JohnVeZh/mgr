/**
 * 教师管理初始化
 */
var Teacher = {
    id: "TeacherTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Teacher.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '头像', field: 'headImg', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '名字', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '性别', field: 'gender', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span >男</span>';
                case 1:
                    return '<span >女</span>';
                default:
                    break;
            }
        }},
        {title: '电话', field: 'moblie', align: 'center', valign: 'middle', sortable: true},
        {title: '简介', field: 'introduce', width:'35%', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true},
    ];
};

/**
 * 检查是否选中
 */
Teacher.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Teacher.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加教师
 */
Teacher.openAddTeacher = function () {
    var index = layer.open({
        type: 2,
        title: '添加教师',
        area: ['1130px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/teacher/teacher_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看教师详情
 */
Teacher.openTeacherDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '教师详情',
            area: ['1130px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/teacher/teacher_update/' + Teacher.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除教师
 */
Teacher.delete = function () {
    if (this.check()) {
        var operation=function () {
            var ajax = new $ax(Feng.ctxPath + "/goods/teacher/delete", function (data) {
                Feng.success("删除成功!");
                Teacher.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("teacherId",Teacher.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否刪除该老师?", operation);
    }
};

/**
 * 重置搜索条件
 */
Teacher.resetSearch = function () {
    $("#condition").val("");
    Teacher.search();
};

/**
 * 查询教师列表
 */
Teacher.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Teacher.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Teacher.initColumn();
    var table = new BSTable(Teacher.id, "/goods/teacher/list", defaultColunms);
    table.setPaginationType("server");
    Teacher.table = table.init();


});
