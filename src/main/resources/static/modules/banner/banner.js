/**
 * banner管理初始化
 */
var Banner = {
    id: "BannerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Banner.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '图片', field: 'img', visible: true, align: 'center', valign: 'middle',formatter:imgFormatter},
        {title: '终端类型', field: 'terminalType', visible: true, align: 'center', valign: 'middle',formatter:terminalFormatter},
        {title: '模块类型', field: 'moduleType', visible: true, align: 'center', valign: 'middle',formatter:moduleFormatter},
        {title: '学段', field: 'sectionCode', visible: true, align: 'center', valign: 'middle',formatter:sectionFormatter},
        {title: '跳转类型', field: 'jumpType', visible: true, align: 'center', valign: 'middle',formatter:jumpFormatter},
        {title: '跳转链接', field: 'url', visible: true, align: 'center', valign: 'middle',formatter:urlFormatter},
        {title: '跳转内容', field: 'contentTitle', visible: true, align: 'center', valign: 'middle'},
        {title: '富文本内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
        {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'}
    ];
};
Banner.selectItem = function () {
    if (Banner.check()){
        var selectItem = $('#' + Banner.id).bootstrapTable('getSelections');
        try{
            window.parent.$("#contentId").val(Banner.seItem.id);
            window.parent.$("#contentName").val(Banner.seItem.title);
            parent.layer.close(window.parent.CouponGainSceneInfoDlg.selectLayerIndex);
        }catch (error){
            //
        }

    }
};
/**
 * 检查是否选中
 */
Banner.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Banner.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加banner
 */
Banner.openAddBanner = function () {
    var index = layer.open({
        type: 2,
        title: '添加banner',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/banner/banner_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看banner详情
 */
Banner.openBannerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'banner详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/banner/banner_update/' + Banner.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除banner
 */
Banner.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/banner/delete", function (data) {
            Feng.success("删除成功!");
            Banner.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bannerId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询banner列表
 */
Banner.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['jumpType'] = $("#jumpType").val();
    queryData['moduleType'] = $("#moduleType").val();
    queryData['terminalType'] = $("#terminalType").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    Banner.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Banner.initColumn();
    var table = new BSTable(Banner.id, "/banner/list", defaultColunms);
    table.setPaginationType("server");
    Banner.table = table.init();
});
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
function terminalFormatter(value,row,index) {
    if(value == 1){
        return '<span class="label label-info">APP</span>';
    }else if(value == 2){
        return '<span class="label label-success">PC</span>';
    }else{
        return '<span class="label label-danger">未识别状态</span>';
    }
}
function moduleFormatter(value,row,index) {
    if(value == 1){
        return '<span class="label label-warning">首页</span>';
    }else if(value == 2){
        return '<span class="label label-primary">资讯</span>';
    }else if(value == 3){
        return '<span class="label label-danger">网课</span>';
    }else if(value==4){
        return '<span class="label label-danger">大礼包</span>';
    }
}


function sectionFormatter(value,row,index) {
    var section = "";
    switch(value)
    {
        case 1: section = "CET4"; break;
        case 2: section = "CET6"; break;
        case 3: section = "考研英语"; break;
        case 4: section = "英语专业"; break;
        case 5: section = "高中英语"; break;
        case 6: section = "初中英语"; break;
        case 7: section = "小学英语"; break;
        default: section = "";
    }
    return section;
}
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
    if($("#pushPermiss").val()){
        menu += ' <a onclick="Message.push(\''+row.id+'\')" title="推送"><span class="label label-default">推送</span></a>';
    }
    return menu;
}
function urlFormatter(value,rows,index) {
    if(value != ""){
        return '<a target="_blank" href="'+value+'">'+value+'</a>';
    }
}