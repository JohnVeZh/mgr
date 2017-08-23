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
        {title: 'productId', field: 'productId', visible: false, align: 'center', valign: 'middle'},
        {title: '封面图片', field: 'coverImg', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '网课名称', field: 'name', width:'15%', align: 'center', valign: 'middle', sortable: true},
        {title: '网课学段类型', field: 'sectionType', align: 'center', valign: 'middle', sortable: true},
        {title: '现价', field: 'presentPrice', align: 'center', valign: 'middle', sortable: true},
        {title: '实际销量', field: 'saleNum', align: 'center', valign: 'middle', sortable: true},
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
        {title: '目录', field: 'hasCatalog', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label label-danger">不存在</span>';
                case 1:
                    return '<span class="label label-primary">存在</span>';
                default:
                    break;
            }
        }},
        {title: '上架', field: 'isShow', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label label-danger">否</span>';
                case 1:
                    return '<span class="label label-primary">是</span>';
                default:
                    break;
            }
        }},
        {title: '操作', field: '', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            var htmls='<a href="javascript:void(0)" onclick="NetworkCourse.updateIsShow(\''+row.id+'\')">上架状态</a>&nbsp;&nbsp;' +
                      '<a href="javascript:void(0)" onclick="NetworkCourse.openNetworkCourseStatus(\''+row.id+'\')">网课状态</a>';
            return htmls;
        }}

        // {title: '操作', align: 'middle', valign: 'middle', formatter: rowActionFormatter, width: '60px'}
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
 * 去往修改上架状态页面
 */
NetworkCourse.updateIsShow = function (id) {
    var index = layer.open({
        type: 2,
        title: '修改上架状态',
        area: ['380px', '260px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/networkCourse/networkCourse_isShow/'+id
    });
    this.layerIndex = index;
};



/**
 * 打开目录管理界面
 */
NetworkCourse.catalogOpen = function () {
    if (this.checkHasCatalog()) {
        this.layerIndex = layer.open({
            type: 2, title: '课程目录', area: ['1024px', '450px'], fix: false, maxmin: true,
            content: Feng.ctxPath + '/goods/catalog/' + NetworkCourse.seItem.id
        });
    }
}

/**
 * 检查是否存在目录
 * @returns {boolean}
 */
NetworkCourse.checkHasCatalog=function(){
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        NetworkCourse.seItem = selected[0];
        if(NetworkCourse.seItem.hasCatalog){
            return true;
        }
        Feng.info("本网课不存在目录！");
        return false;
    }
}

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
 * 点击添加网课
 */
NetworkCourse.openAddNetworkCourse = function () {
    var index = layer.open({
        type: 2,
        title: '添加网课',
        area: ['1130px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/networkCourse/networkCourse_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看网课详情
 */
NetworkCourse.openNetworkCourseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '网课详情',
            area: ['1130px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/networkCourse/networkCourse_update/' + NetworkCourse.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看网课状态
 */
NetworkCourse.openNetworkCourseStatus = function (id) {

        var index = layer.open({
            type: 2,
            title: '修改网课状态',
            area: ['500px', '300px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/networkCourse/networkCourse_status/' + id
        });
        this.layerIndex = index;

};

/**
 * 打开查看视频管理
 */
NetworkCourse.openVideo = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '视频管理',
            area: ['1130px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/video/' + NetworkCourse.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看评论管理
 */
NetworkCourse.courseCommentOpen = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '网课评论管理',
            area: ['1130px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/CourseComment/' + NetworkCourse.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除网课
 */
NetworkCourse.delete = function () {
    if (this.check()) {
        var operation=function () {
            var ajax = new $ax(Feng.ctxPath + "/goods/networkCourse/delete", function (data) {
                Feng.success("删除成功!");
                NetworkCourse.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("networkCourseId",NetworkCourse.seItem.productId);
            ajax.start();
        };
        Feng.confirm("是否刪除该网课?", operation);
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
    var table = new BSTable(NetworkCourse.id, "/goods/networkCourse/list", defaultColunms);
    table.setPaginationType("server");
    NetworkCourse.table = table.init();


});
