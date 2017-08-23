/**
 * 初始化题目详情对话框
 */
var Questionmanage = {
    id: "QuestionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1

};



/**
 * 关闭此对话框
 */
Questionmanage.close = function() {
    parent.layer.close(window.parent.Question.layerIndex);
}

/**
 * 收集数据
 */
Questionmanage.collectData = function() {
    this.set('id').set('name').set('type').set('content').set('questionNum').set('hasItem').set('sectionCode').set('remarks').set('analysis')
        .set("paperId").set("structureId");
}


// //初始化学段
// var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
//     $.each(data,function(i,item){
//         $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
//     });
// }, function (data) {
//     Feng.error("获取学段失败!" + data.responseJSON.message + "!");
// });
// ajax.start();

/**
 * 初始化表格的列
 */
Questionmanage.initColumn = function () {

    return [  {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '题目名称', field: 'name', visible: true, align: 'center', valign: 'middle',width:'20%'},
        {title: '题目类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
        {title: '是否存在小题', field: 'has_item', visible: false, align: 'center', valign: 'middle'},
        {title: '题目数量', field: 'questionNum', visible: true, align: 'center', valign: 'middle'},
        {title: '学段code', field: 'sectionCode', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'}

    ];
};

$(function () {
   var paperId= $("#paperId").val();
    var defaultColunms = Questionmanage.initColumn();
    var table = new BSTable(Questionmanage.id, "/question/list?paperId="+paperId, defaultColunms);
    table.setPaginationType("client");
    Questionmanage.table = table.init();


});



/**
 * 打开查看题目详情
 */
Questionmanage.openQuestionDetail = function () {
    if (this.check()) {
        var id=Questionmanage.seItem.id;
        var index = layer.open({
            type: 2,
            title: '题目详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question/question_update?id='+id
        });
        this.layerIndex = index;
    }
};

/**
 * 检查是否选中
 */
Questionmanage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Questionmanage.seItem = selected[0];
        return true;
    }
};

/**
 * 删除听力题
 */
Questionmanage.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/question/delete", function (data) {
            Feng.success("删除成功!");
            Questionmanage.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 点击添加题目
 */
Questionmanage.openAddQuestion = function () {
    var type=$("#type").val();
    var paperId=$("#paperId").val();
    var sectionCode=$("#sectionCode").val();
    var contentType = $("#contentType").val();
    var index = layer.open({
        type: 2,
        title: '添加题目',
        area: ['96%', '96%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question/question_add?type='+type+"&paperId="+paperId+"&contentType="+contentType+
        "&sectionCode="+sectionCode
    });
    this.layerIndex = index;
};


