/**
 * 弹窗管理初始化
 */
var PopUp = {
    id: "PopUpTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PopUp.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '弹窗主题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '弹窗图片', field: 'img', visible: true, align: 'center', valign: 'middle', formatter: imgFormatter},
        {
            title: '展示频道',
            field: 'showModule',
            visible: true,
            align: 'center',
            valign: 'middle',
            formatter: moduleFormatter
        },
        {
            title: '跳转类型',
            field: 'jumpType',
            visible: true,
            align: 'center',
            valign: 'middle',
            formatter: jumpTypeFormatter
        },
        {title: '外部地址', field: 'url', visible: true, align: 'center', valign: 'middle',formatter:urlFormatter},
        {title: '添加时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
        {title: '开始时间', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
        {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
        {title: '操作人', field: 'operationName', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle', formatter: statusFormatter}
    ];
};

/**
 * 检查是否选中
 */
PopUp.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        PopUp.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加弹窗
 */
PopUp.openAddPopUp = function () {
    var index = layer.open({
        type: 2,
        title: '添加弹窗',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/popUp/popUp_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看弹窗详情
 */
PopUp.openPopUpDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '弹窗详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/popUp/popUp_update/' + PopUp.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除弹窗
 */
PopUp.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/popUp/delete", function (data) {
            Feng.success("删除成功!");
            PopUp.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("popUpId", this.seItem.id);
        ajax.start();
    }
};


/**
 * 查询弹窗列表
 */
PopUp.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['showModule'] = $("#showModule").val();
    queryData['status'] = $("#status").val();
    PopUp.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PopUp.initColumn();
    var table = new BSTable(PopUp.id, "/popUp/list", defaultColunms);
    table.setPaginationType("server");
    PopUp.table = table.init();
});
function moduleFormatter(value, row, index) {
    if (value == 1) {
        return '<span class="label label-primary">首页</span>'
    } else if (value == 2) {
        return '<span class="label label-primary">社区</span>'
    } else if (value == 3) {
        return '<span class="label label-primary">商城</span>'
    } else if (value == 4) {
        return '<span class="label label-primary">我的</span>'
    } else if (value == 5) {
        return '<span class="label label-primary">网课</span>'
    }
}
function jumpTypeFormatter(value, row, index) {
    if (value == 10) {
        return '<font color="#2E8B57" class="label label-danger" style="font-weight:bold;">我的</font>'
    } else if (value == 2) {
        return '<font color="#2E8B57" class="label label-danger" style="font-weight:bold;">外部链接</font>'
    } else if (value == 3) {
        return '<font color="#2E8B57" class="label label-danger" style="font-weight:bold;">首页</font>'
    } else if (value == 4) {
        return '<font color="#2E8B57" class="label label-danger" style="font-weight:bold;">网课-四级</font>'
    } else if (value == 5) {
        return '<font color="#2E8B57" class="label label-danger" style="font-weight:bold;">网课-六级</font>'
    } else if (value == 6) {
        return '<font color="#2E8B57" class="label label-danger" style="font-weight:bold;">网课-考研</font>'
    } else if (value == 7) {
        return '<font color="#2E8B57" class="label label-danger" style="font-weight:bold;">社区-资讯</font>'
    } else if (value == 8) {
        return '<font color="#2E8B57" class="label label-danger" style="font-weight:bold;">社区-活动</font>'
    } else if (value == 9) {
        return '<font color="#2E8B57" class="label label-danger" style="font-weight:bold;">图书</font>'
    }
}
function imgFormatter(value, row, index) {
    return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">' +
        '<br>'+'<br>' + '<a style="border: 8px" class="label label-warning" href="javascript:openImg(\'' + value + '\')">查看大图</a>';
}
function statusFormatter(value, row, index) {
    if (value == 1) {
        return '<font color="#2E8B57" style="font-weight:bold;">有效</font>';
    } else if (value == 2) {
        return '<font color="#A9A9A9" style="font-weight:bold;">无效</font>';
    }
}
function urlFormatter(value, row, index) {
        return '<a href="'+value+'">'+value+'</a>';
}


function openImg(img) {
    var index = layer.open({
        type: 2,
        title: '主题图片',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/popUp/img?imgSrc=' + img
    });
    this.layerIndex = index;
}