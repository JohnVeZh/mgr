/**
 * 视频管理初始化
 */
var Video = {
    id: "VideoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Video.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '视频名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '开始时间', field: 'startTime', align: 'center', valign: 'middle', sortable: true},
        {title: '时长', field: 'duration', align: 'center', valign: 'middle', sortable: true},
        {title: '授课老师', field: 'teacherName', align: 'center', valign: 'middle', sortable: true},
        {title: '视频状态', field: 'videoStatus', align: 'center', valign: 'middle', sortable: true},
        {title: '视频类型', field: 'type', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
            $("#videoType").val(value);
            switch(value) {
                case 0:
                    return '<span class="label label-success">录播</span>';
                case 1:
                    return '<span class="label label-primary">直播</span>';
                default:
                    break;
            }
        }},
        {title: '预告视频', field: 'previewCcId', align: 'center', valign: 'middle', sortable: true}

    ];
};

/**
 * 检查是否选中
 */
Video.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Video.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加视频
 */
Video.openAddVideo = function () {
    var index = layer.open({
        type: 2,
        title: '添加视频',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/video/video_add/'+$("#networkCourseId").val()+','+$("#catalogId").val()
    });
    this.layerIndex = index;
};


/**
 * 打开查看视频详情
 */
Video.openVideoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '视频详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/video/video_update/' + Video.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除视频
 */
Video.delete = function () {
    if (this.check()) {
        var operation=function () {
            var ajax = new $ax(Feng.ctxPath + "/goods/video/delete", function (data) {
                Feng.success("删除成功!");
                Video.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("videoId",Video.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否刪除该视频?", operation);
    }
};

/**
 * 查询视频列表
 */
Video.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Video.table.refresh({query: queryData});
};



$(function () {

    var defaultColunms = Video.initColumn();
    var table = new BSTable(Video.id, "/goods/video/list/"+$("#networkCourseId").val()+','+$("#catalogId").val(), defaultColunms);
    table.setPaginationType("server");
    Video.table = table.init();
    // var query= {};
    // query['networkCourseId'] = $("#networkCourseId").val();
    // Video.table.refresh({query: query});

});
