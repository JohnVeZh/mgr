/**
 * 大礼包试卷批改管理初始化
 */
var PaperCorrect = {
    id: "PaperCorrectTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PaperCorrect.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'phone',width:'20%', align: 'center', valign: 'middle', sortable: true},
        {title: '试卷类型', field: 'period', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            switch(value) {
                case 1:
                    return '<span>学前测试</span>';
                case 2:
                    return '<span>学中测试</span>';
                case 3:
                    return '<span>学末测试</span>';
                default:
                    break;
            }
        }},
        {title: '作业类型', field: 'questionType', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            switch(value) {
                case 3:
                    return '<span>翻译</span>';
                case 4:
                    return '<span>写作</span>';
                default:
                    break;
            }
        }},
        {title: '四六级', field: 'sectionCode', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            switch(value) {
                case 1:
                    return '<span>四级</span>';
                case 2:
                    return '<span>六级</span>';
                default:
                    break;
            }
        }},
        {title: '提交时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true},
        {title: '批改时间', field: 'replyDate', align: 'center', valign: 'middle', sortable: true},
        {title: '批改人', field: 'teacherName', align: 'center', valign: 'middle', sortable: true},
        {title: '操作', field: '', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            if(row.replyStatus==0){
                return '<a href="javascript:void(0)" onclick="PaperCorrect.correctPaper(\''+row.id+'\')">批改</a>';
            }
            return '<a href="javascript:void(0)" onclick="PaperCorrect.selectPaper(\''+row.id+'\')">查看</a>';
        }}
    ];
};


/**
 * 查看试卷
 */
PaperCorrect.selectPaper = function (id) {
    var index = layer.open({
        type: 2,
        title: '查看试卷',
        area: ['1130px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/paper/paperCorrect/paperCorrect_detail/'+id
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 批改试卷
 */
PaperCorrect.correctPaper = function (id) {
    var index = layer.open({
        type: 2,
        title: '批改试卷',
        area: ['1130px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/paper/paperCorrect/paperCorrect_update/'+id
    });
    this.layerIndex = index;
    layer.full(index);
};


/**
 * 检查是否选中
 */
PaperCorrect.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PaperCorrect.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加大礼包试卷批改
 */
PaperCorrect.openAddPaperCorrect = function () {
    var index = layer.open({
        type: 2,
        title: '添加大礼包试卷批改',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/paper/paperCorrect/paperCorrect_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看大礼包试卷批改详情
 */
PaperCorrect.openPaperCorrectDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '大礼包试卷批改详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/paper/paperCorrect/paperCorrect_update/' + PaperCorrect.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除大礼包试卷批改
 */
PaperCorrect.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/paper/paperCorrect/delete", function (data) {
            Feng.success("删除成功!");
            PaperCorrect.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("paperCorrectId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 重置搜索条件
 */
PaperCorrect.resetSearch = function () {
    $("#phone").val("");
    $("#period").val("");
    $("#questionType").val("");
    $("#replyStatus").val("");
    $("#sectionCode").val("");
    $("#commitTimeStart").val("");
    $("#commitTimeEnd").val("");
    $("#correctTimeStart").val("");
    $("#correctTimeEnd").val("");
    PaperCorrect.search();
};


/**
 * 查询大礼包试卷批改列表
 */
PaperCorrect.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    queryData['period'] = $("#period").val();
    queryData['questionType'] = $("#questionType").val();
    queryData['replyStatus'] = $("#replyStatus").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    queryData['commitTimeStart'] = $("#commitTimeStart").val();
    queryData['commitTimeEnd'] = $("#commitTimeEnd").val();
    queryData['correctTimeStart'] = $("#correctTimeStart").val();
    queryData['correctTimeEnd'] = $("#correctTimeEnd").val();

    PaperCorrect.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PaperCorrect.initColumn();
    var table = new BSTable(PaperCorrect.id, "/paper/paperCorrect/list", defaultColunms);
    table.setPaginationType("server");
    PaperCorrect.table = table.init();
});
