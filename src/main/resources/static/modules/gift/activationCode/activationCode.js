/**
 * 激活码管理管理初始化
 */
var ActivationCode = {
    id: "ActivationCodeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ActivationCode.initColumn = function () {
    return [
        [
            {field: 'selectItem', radio: true, rowspan: 2, colspan: 1},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', rowspan: 2, colspan: 1},
            {title: '激活码', field: 'code', align: 'left', valign: 'middle', sortable: true, width: '100px', rowspan: 2, colspan: 1},
            {title: '学段', field: 'sectionCode', align: 'center', valign: 'middle', sortable: true, width: '80px', rowspan: 2, colspan: 1, formatter: function(value, row, index){
            switch(value) {
                case 1:
                    return '<span class="label badge-warning">四级</span>';
                case 2:
                    return '<span class="label label-primary">六级</span>';
                case 3:
                    return '<span class="label label-success">考研</span>';
                case 4:
                    return '<span class="label label-info">英专</span>';
                case 5:
                    return '<span class="label label-white">高中英语</span>';
                case 6:
                    return '<span class="label label-default">初中英语</span>';
                case 7:
                    return '<span class="label label-default">小学英语</span>';
                default:
                    break;
                }
            }},
            {title: '导入时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true, width: '140px', rowspan: 2, colspan: 1},
            {title: '激活次序', field: 'sort', align: 'center', valign: 'middle', sortable: true, width: '40px', rowspan: 2, colspan: 1},
            {title: '操作人', field: 'updateBy', align: 'center', valign: 'middle', sortable: true, width: '90px', rowspan: 2, colspan: 1, formatter: function(value, row, index) {
                return "超级管理员";
            }},
            {title: '激活时间', field: 'activateTime', align: 'center', valign: 'middle', sortable: true, width: '140px', rowspan: 2, colspan: 1},
            {title: '使用地区', field: 'address', align: 'left', valign: 'middle', sortable: true, rowspan: 2, colspan: 1},
            {title: '用户帐号', field: 'phone', align: 'left', valign: 'middle', sortable: true, width: '100px', rowspan: 2, colspan: 1},
            {title: '翻译批改', align: 'center', valign: 'middle', sortable: true, width: '250px', rowspan: 1, colspan: 3},
            {title: '写作批改', align: 'center', valign: 'middle', sortable: true, width: '250px', rowspan: 1, colspan: 3}
        ], [
            {title: '学前', field: 'subjectiveSubmitRecord', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(value, row, index){
                if(value['preTranslateTotal'] != null && value['preTranslateTotal'] != "undefined" && value['preTranslateUse'] != null && value['preTranslateUse'] != "undefined" ) {
                    return '<span class="label badge-primary">'+value['preTranslateTotal']+'</span>&nbsp;/&nbsp;<span class="label label-warning">'+value['preTranslateUse']+'</span>';
                } else {
                    return '-';
                }
            }},
            {title: '学中', field: 'subjectiveSubmitRecord', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(value, row, index){
                if(value['midTranslateTotal'] != null && value['midTranslateTotal'] != "undefined" && value['midTranslateUse'] != null && value['midTranslateUse'] != "undefined") {
                    return '<span class="label badge-primary">'+value['midTranslateTotal']+'</span>&nbsp;/&nbsp;<span class="label label-warning">'+value['midTranslateUse']+'</span>';
                } else {
                    return '-';
                }
            }},
            {title: '学末', field: 'subjectiveSubmitRecord', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(value, row, index){
                if(value['postTranslateTotal'] != null && value['postTranslateTotal'] != "undefined" && value['postTranslateUse'] != null && value['postTranslateUse'] != "undefined") {
                    return '<span class="label badge-primary">'+value['postTranslateTotal']+'</span>&nbsp;/&nbsp;<span class="label label-warning">'+value['postTranslateUse']+'</span>';
                } else {
                    return '-';
                }
            }},
            {title: '学前', field: 'subjectiveSubmitRecord', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(value, row, index){
                if(value['preWriteTotal'] != null && value['preWriteTotal'] != "undefined" && value['preWriteUse'] != null && value['preWriteUse'] != "undefined") {
                    return '<span class="label badge-primary">'+value['preWriteTotal']+'</span>&nbsp;/&nbsp;<span class="label label-warning">'+value['preWriteUse']+'</span>';
                } else {
                    return '-';
                }
            }},
            {title: '学中', field: 'subjectiveSubmitRecord', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(value, row, index){
                if(value['midWriteTotal'] != null && value['midWriteTotal'] != "undefined" && value['midWriteUse'] != null && value['midWriteUse'] != "undefined") {
                    return '<span class="label badge-primary">'+value['midWriteTotal']+'</span>&nbsp;/&nbsp;<span class="label label-warning">'+value['midWriteUse']+'</span>';
                } else {
                    return '-';
                }
            }},
            {title: '学末', field: 'subjectiveSubmitRecord', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(value, row, index){
                if(value['postWriteTotal'] != null && value['postWriteTotal'] != "undefined" && value['postWriteUse'] != null && value['postWriteUse'] != "undefined") {
                    return '<span class="label badge-primary">'+value['postWriteTotal']+'</span>&nbsp;/&nbsp;<span class="label label-warning">'+value['postWriteUse']+'</span>';
                } else {
                    return '-';
                }
            }}
        ]
    ];
};

/**
 * 检查是否选中
 */
ActivationCode.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ActivationCode.seItem = selected[0];
        return true;
    }
};


/**
 * 激活码导入
 */
ActivationCode.openImportActivationCode = function () {
    var index = layer.open({
        type: 2, title: '激活码导入', area: ['600px', '300px'], fix: false, maxmin: true,
        content: Feng.ctxPath + '/gift/activationCode/import'
    });
    this.layerIndex = index;
}

/**
 * 查询激活码管理列表
 */
ActivationCode.search = function () {
    var queryData = {};
    queryData['qCode'] = $("#qCode").val();
    queryData['qSectionCode'] = $("#qSectionCode").val();
    queryData['qPhone'] = $("#qPhone").val();
    queryData['qAddress'] = $("#qAddress").val();
    queryData['qIsActivated'] = $("#qIsActivated").val();
    queryData['qImportStartTime'] = $("#qImportStartTime").val();
    queryData['qImportEndTime'] = $("#qImportEndTime").val();
    queryData['qActivateStartTime'] = $("#qActivateStartTime").val();
    queryData['qActivateEndTime'] = $("#qActivateEndTime").val();
    ActivationCode.table.refresh({query: queryData});
};

ActivationCode.resetSearch = function () {
    $("#qCode").val("");
    $("#qSectionCode").val("");
    $("#qPhone").val("");
    $("#qAddress").val("");
    $("#qIsActivated").val("");
    $("#qImportStartTime").val("");
    $("#qImportEndTime").val("");
    $("#qActivateStartTime").val("");
    $("#qActivateEndTime").val("");
    ActivationCode.search();
}

$(function () {
    var defaultColunms = ActivationCode.initColumn();
    var table = new BSTable(ActivationCode.id, "/gift/activationCode/list", defaultColunms);
    table.setPaginationType("server");
    ActivationCode.table = table.init();
});
