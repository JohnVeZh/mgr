/**
 * 启动页管理初始化
 */
var StartPage = {
    id: "StartPageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
StartPage.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '图片', field: 'img', visible: true, align: 'center', valign: 'middle',formatter:imgFormatter},
        {title: '跳转类型', field: 'jumpType', visible: true, align: 'center', valign: 'middle',formatter:jumpFormatter},
        {title: '跳转链接', field: 'url', visible: true, align: 'center', valign: 'middle',formatter:urlFormatter},
        {title: '跳转内容', field: 'contentTitle', visible: true, align: 'center', valign: 'middle'},
        {title: '是否展示', field: 'isShow', visible: true, align: 'center', valign: 'middle',formatter:showFormatter},
        {title: '富文本内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
        {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
        {title: '操作', field: 'edit', visible: true, align: 'center', valign: 'middle',formatter:editFormatter},
    ];
};

/**
 * 检查是否选中
 */
StartPage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        StartPage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加启动页
 */
StartPage.openAddStartPage = function () {
    var index = layer.open({
        type: 2,
        title: '添加启动页',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/startPage/startPage_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看启动页详情
 */
StartPage.openStartPageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '启动页详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/startPage/startPage_update/' + StartPage.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除启动页
 */
StartPage.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/startPage/delete", function (data) {
            Feng.success("删除成功!");
            StartPage.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("startPageId",this.seItem.id);
        ajax.start();
    }
};
/**
 *
 * @param id
 */
StartPage.updateIsShow = function (id,userStatus) {
    $.ajax({
        type: "post",
        url: "/startPage/updateIsShow",
        data: {id:id, isShow:userStatus},
        dataType: "json",
        success: function(data){
            StartPage.table.refresh();
        },
        error:function (data) {
            layer.msg("操作失败！请稍后再试！");
        }
    });
}
/**
 * 查询启动页列表
 */
StartPage.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    StartPage.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = StartPage.initColumn();
    var table = new BSTable(StartPage.id, "/startPage/list", defaultColunms);
    table.setPaginationType("server");
    StartPage.table = table.init();
});
function jumpFormatter(value,row,index) {
    var jump = "";
    switch(value)
    {
        case 0: jump = "无跳转"; break;
        case 1: jump = "富文本"; break;
        case 2: jump = "外部链接"; break;
        case 3: jump = "活动"; break;
        case 4: jump = "资讯"; break;
        case 5: jump = "图书"; break;
        case 6: jump = "网课"; break;
        default: jump = "";
    }
    return jump;
}

function editFormatter(value,row,index) {
    var menu = "";
    if($("#updatePermiss").val()) {
        if(row.isShow == 1){
            menu += ' <a onclick="StartPage.updateIsShow(\''+row.id+'\',\'0\')" title="隐藏"><span class="label label-default">隐藏</span></a>';
        }else if(row.isShow == 0){
            menu += ' <a onclick="StartPage.updateIsShow(\''+row.id+'\',\'1\')" title="展示"><span class="label label-success">展示</span></a>';
        }

    }
    return menu;
}

function imgFormatter(value,row,index) {
    var img = "";
    if(value != ""){
        var values = value.split(",");
        for(var i = 0;i<values.length;i++){
            var url = values[i];
            if(url.replace(/(^\s*)|(\s*$)/g, "")!= ""){
                img += "<img src='"+value+"' width='160px' height='80px'>";
            }
        }
        return img;
    }
}
function showFormatter(value,row,index) {
    if(value == 0){
        return "隐藏";
    }
    if(value == 1){
        return "展示";
    }
}
function urlFormatter(value,rows,index) {
    if(value != ""){
        return '<a target="_blank" href="'+value+'">'+value+'</a>';
    }
}