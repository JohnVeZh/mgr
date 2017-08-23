/**
 * 题目报错管理初始化
 */
var QuestionError = {
    id: "QuestionErrorTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 * id, user_id, section_code, paper_id, question_id, content, is_solved, type, create_date
 */
QuestionError.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
        {title: '模块', field: 'type', visible: true, align: 'center', valign: 'middle',formatter:typeFormatter},
        {title: '题目类型', field: 'questionType', visible: true, align: 'center', valign: 'middle',formatter:questionTypeFormatter},
        {title: '学段', field: 'sectionCode', visible: true, align: 'center', valign: 'middle',formatter:sectionFormatter},
        {title: '分类', field: 'structureName', visible: true, align: 'center', valign: 'middle'},
        {title: '题目', field: 'questionName', visible: true, align: 'center', valign: 'middle'},
        {title: '错误内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
        {title: '是否已解决', field: 'isSolved', visible: true, align: 'center', valign: 'middle',formatter:solvedFormatter}
    ];
};

/**
 * 检查是否选中
 */
QuestionError.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QuestionError.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加题目报错
 */
QuestionError.openAddQuestionError = function () {
    var index = layer.open({
        type: 2,
        title: '添加题目报错',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/questionError/questionError_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看题目报错详情
 */
QuestionError.openQuestionErrorDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '题目报错详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/questionError/questionError_update/' + QuestionError.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除题目报错
 */
QuestionError.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/questionError/delete", function (data) {
            Feng.success("删除成功!");
            QuestionError.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questionErrorId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询题目报错列表
 */
QuestionError.search = function () {
    var queryData = {};
    queryData['content'] = $("#content").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    queryData['questionType'] = $("#questionType").val();
    queryData['isSolved'] = $("#isSolved").val();
    QuestionError.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = QuestionError.initColumn();
    var table = new BSTable(QuestionError.id, "/questionError/list", defaultColunms);
    table.setPaginationType("client");
    QuestionError.table = table.init();
});


function questionTypeFormatter (value,row,index) {
    var type = "";
    switch (value){
        case 1:
            type="听力";
            break;
        case 2:
            type="阅读";
            break;
        case 3:
            type="翻译";
            break;
        case 4:
            type="写作";
            break;
    }
    return type;
}
function typeFormatter(value,row,index) {
    var type = "";
    switch (value){
        case 1:
            type="全真考场";
        break;
        case 2:
            type="字幕听力";
            break;
        case 3:
            type="简系列";
            break;
        case 4:
            type="专项练习";
            break;
    }
    return type;
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
function solvedFormatter(value,row,index) {
    if(value == 0){
        return '<span class="label label-success">待解决</span>';
    }else if(value == 1){
        return '<span class="label label-primary">已解决</span>';
    }else{
        return '<span class="label label-danger">未识别状态</span>';
    }
}