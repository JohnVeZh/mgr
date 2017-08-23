/**
 * 网课视频管理初始化
 */
var ProductNetworkCourseVideo = {
    id: "ProductNetworkCourseVideoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    selectionIds:[],  //保存选中ids
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ProductNetworkCourseVideo.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true,formatter:checkedFormatter},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '视频名称', field: 'name', align: 'center', valign: 'middle'},
        {title: '开始时间', field: 'startTime', align: 'center', valign: 'middle'},
        {title: '时长', field: 'duration', align: 'center', valign: 'middle'},
        {title: '授课老师', field: 'teacherName', align: 'center', valign: 'middle'},
        {title: '视频状态', field: 'videoStatus', align: 'center', valign: 'middle'},
        {title: '视频类型', field: 'type', align: 'center', valign: 'middle', formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label label-success">录播</span>';
                case 1:
                    return '<span class="label label-primary">直播</span>';
                default:
                    break;
            }
        }}
    ];
};

function checkedFormatter(value, row, index) {
    if (ProductNetworkCourseVideo.selectionIds.length > 0) {
        return {
            checked : $.inArray(row.id, ProductNetworkCourseVideo.selectionIds) != -1
        };
    }
}

/**
 * 检查是否选中
 */
ProductNetworkCourseVideo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ProductNetworkCourseVideo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加网课视频
 */
ProductNetworkCourseVideo.openAddProductNetworkCourseVideo = function () {
    var index = layer.open({
        type: 2,
        title: '添加网课视频',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/productNetworkCourseVideo/productNetworkCourseVideo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看网课视频详情
 */
ProductNetworkCourseVideo.openProductNetworkCourseVideoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '网课视频详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/productNetworkCourseVideo/productNetworkCourseVideo_update/' + ProductNetworkCourseVideo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除网课视频
 */
ProductNetworkCourseVideo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/productNetworkCourseVideo/delete", function (data) {
            Feng.success("删除成功!");
            ProductNetworkCourseVideo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("productNetworkCourseVideoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询网课视频列表
 */
ProductNetworkCourseVideo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ProductNetworkCourseVideo.table.refresh({query: queryData});
};

/**
 * 重置
 */
ProductNetworkCourseVideo.resetSearch = function () {
    $("#condition").val('');
    ProductNetworkCourseVideo.search();
};

/**
 * 选中事件操作数组
 */
ProductNetworkCourseVideo.union = function(array,ids){
    $.each(ids, function (i, id) {
        if($.inArray(id,array)==-1){
            array[array.length] = id;
            //添加课后练习
            ProductNetworkCourseVideo.addExercise(id);
        }
    });
    return array;
};

/**
 * 取消选中事件操作数组
 */
ProductNetworkCourseVideo.difference = function(array,ids){
    $.each(ids, function (i, id) {
        var index = $.inArray(id,array);
        if(index!=-1){
            array.splice(index, 1);
            //删除课后练习
            ProductNetworkCourseVideo.deleteExercise(id);
        }
    });
    return array;
};

/**
 * 表格处理
 */
ProductNetworkCourseVideo.tableResponseHandler = function (res) {
    $.each(res.rows, function (i, row) {
        row.selectItem = $.inArray(row.id, ProductNetworkCourseVideo.selectionIds) != -1;  //判断当前行的数据id是否存在与选中的数组，存在则将多选框状态变为true
    });
    return res;
}

/**
 * 获取课后作业
 */
ProductNetworkCourseVideo.exerciseList = function () {
    var paperId = window.parent.parent.parent.Paper.seItem.id;
    var recommendId = window.parent.GiftRecommendCourse.seItem.id;
    var structureId = window.parent.parent.PaperStructure.seItem.id;
    var ajax = new $ax(Feng.ctxPath + "/gift/exercise/list", function (data) {
        if (data != undefined) {
            $.each(data, function (i, exercise) {
                ProductNetworkCourseVideo.selectionIds[i] = exercise.networkCourseVideoId;
            });
        }
    }, function (data) {

    });
    ajax.set("paperId",paperId);
    ajax.set("recommendId",recommendId);
    ajax.set("structureId",structureId);
    ajax.start();
}

/**
 * 添加课后作业
 */
ProductNetworkCourseVideo.addExercise = function (videoId) {
    var paperId = window.parent.parent.parent.Paper.seItem.id;
    var recommendId = window.parent.GiftRecommendCourse.seItem.id;
    var structureId = window.parent.parent.PaperStructure.seItem.id;
    var sectionCode = window.parent.GiftRecommendCourse.seItem.sectionCode;
    var ajax = new $ax(Feng.ctxPath + "/gift/exercise/add", function (data) {
        Feng.success("绑定成功!");
    }, function (data) {
        Feng.error("绑定失败!");
    });
    ajax.set("paperId",paperId);
    ajax.set("recommendId",recommendId);
    ajax.set("networkCourseVideoId",videoId);
    ajax.set("structureId",structureId);
    ajax.set("sectionCode",sectionCode);
    ajax.start();
}

/**
 * 添加课后作业
 */
ProductNetworkCourseVideo.deleteExercise = function (videoId) {
    var paperId = window.parent.parent.parent.Paper.seItem.id;
    var recommendId = window.parent.GiftRecommendCourse.seItem.id;
    var structureId = window.parent.parent.PaperStructure.seItem.id;
    var ajax = new $ax(Feng.ctxPath + "/gift/exercise/del", function (data) {
        Feng.success("取消绑定成功!");
    }, function (data) {
        Feng.error("取消绑定失败!");
    });
    ajax.set("paperId",paperId);
    ajax.set("recommendId",recommendId);
    ajax.set("networkCourseVideoId",videoId);
    ajax.set("structureId",structureId);
    ajax.start();
}

$(function () {
    ProductNetworkCourseVideo.exerciseList();

    var defaultColunms = ProductNetworkCourseVideo.initColumn();
    var table = new BSTable(ProductNetworkCourseVideo.id, "/goods/video/queryList.do?networkCourseId=" + window.parent.GiftRecommendCourse.seItem.networkCourseId, defaultColunms);
    table.responseHandler = ProductNetworkCourseVideo.tableResponseHandler;
    ProductNetworkCourseVideo.table = table.init();

    var _ = {"union":ProductNetworkCourseVideo.union,"difference":ProductNetworkCourseVideo.difference};
    //绑定选中事件、取消事件、全部选中、全部取消
    ProductNetworkCourseVideo.table.btInstance.on('check.bs.table check-all.bs.table uncheck.bs.table uncheck-all.bs.table', function (e, rows) {
        var ids = $.map(!$.isArray(rows) ? [rows] : rows, function (row) {
            return row.id;
        });
        func = $.inArray(e.type, ['check', 'check-all']) > -1 ? 'union' : 'difference';
        ProductNetworkCourseVideo.selectionIds = _[func](ProductNetworkCourseVideo.selectionIds, ids);
    });
});
