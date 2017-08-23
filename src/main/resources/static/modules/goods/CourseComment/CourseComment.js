/**
 * 网课评论管理初始化
 */
var CourseComment = {
    id: "CourseCommentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CourseComment.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '评论内容', field: 'content', align: 'center', valign: 'middle', sortable: true},
        {title: '网课名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '点赞数', field: 'praiseNum', align: 'center', valign: 'middle', sortable: true},
        {title: '评论用户', field: 'phone', align: 'center', valign: 'middle', sortable: true},
        {title: '评论时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true},
        {
            title: '回复操作',
            field: 'count',
            align: 'center',
            valign: 'middle',
            sortable: false,
            formatter: function (value, row, index) {
                switch (value) {
                    case 0:
                        return '<span class="label label-danger" onclick="CourseComment.openCourseCommentReply(\'' + row.id + '\')">回复</span>';
                    case 1:
                        return '<span class="label label-primary" onclick="CourseComment.openCourseCommentReplyEdit(\'' + row.id + '\')">查看</span>';
                    default:
                        break;
                }
            }
        }
    ];
};

/**
 * 检查是否选中
 */
CourseComment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        CourseComment.seItem = selected[0];
        return true;
    }
};


/**
 * 打开查看网课评论点赞
 */
CourseComment.openCourseCommentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '网课评论点赞数',
            area: ['320px', '220px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/CourseComment/CourseComment_update/' + CourseComment.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除网课评论
 */
CourseComment.delete = function () {
    if (this.check()) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/goods/CourseComment/delete", function (data) {
                Feng.success("删除成功!");
                CourseComment.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("CourseCommentId", CourseComment.seItem.id);
            ajax.start();
        }
        Feng.confirm("您确定要删除这条评论吗?", operation);
    }
};

/**
 * 查询网课评论列表
 */
CourseComment.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['startDate'] = $("#startDate").val();
    queryData['endDate'] = $("#endDate").val();
    CourseComment.table.refresh({query: queryData});
};

/**
 * 重置搜索条件
 */
CourseComment.resetSearch = function () {
    $("#condition").val("");
    $("#startDate").val("");
    $("#endDate").val("");
    CourseComment.search();
};

/**
 * 打开查看网课评论并回复
 */
CourseComment.openCourseCommentReply = function (id) {
    var index = layer.open({
        type: 2,
        title: '网课评论回复',
        area: ['1130px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/courseCommentReply/courseCommentReply_add/' + id
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 查看修改回复
 */
CourseComment.openCourseCommentReplyEdit = function (id) {
    var index = layer.open({
        type: 2,
        title: '修改网课评论回复',
        area: ['1130px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/courseCommentReply/courseCommentReply_update/' + id
    });
    this.layerIndex = index;
    layer.full(index);
};

$(function () {
    var defaultColunms = CourseComment.initColumn();
    var table = new BSTable(CourseComment.id, "/goods/CourseComment/list/" + $("#networkCourseId").val(), defaultColunms);
    table.setPaginationType("server");
    CourseComment.table = table.init();
});
