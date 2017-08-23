/**
 * 渠道管理初始化
 */
var Channel = {
    id: "ChannelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Channel.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        // {title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true},
        {title: '渠道名称', field: 'label', align: 'center', valign: 'middle', sortable: true},
        // {title: '描述', field: 'description', align: 'center', valign: 'middle', sortable: true},
        {title: '渠道代码', field: 'value', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'sort', align: 'center', visible: true, valign: 'middle', sortable: true}
    ]
        ;
};

/**
 * 检查是否选中
 */
Channel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length === 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Channel.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加渠道
 */
Channel.openAddChannel = function () {
    var index = layer.open({
        type: 2,
        title: '添加渠道',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/channel/channel_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看渠道详情
 */
Channel.openChannelDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '渠道详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/channel/channel_edit/' + Channel.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除渠道
 */
Channel.delete = function () {
    if (this.check()) {

        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/channel/delete", function (data) {
                Feng.success("删除成功!");
                Channel.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("channelId", Channel.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除渠道 " + Channel.seItem.type + "?", operation);
    }
};

/**
 * 查询渠道列表
 */
Channel.search = function () {
    var queryData = {};
    queryData['label'] = $("#label").val();
    Channel.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Channel.initColumn();
    var table = new BSTable(Channel.id, "/channel/list", defaultColunms);
    table.setPaginationType("server");
    table.setHeight(600);
    Channel.table = table.init();
});
