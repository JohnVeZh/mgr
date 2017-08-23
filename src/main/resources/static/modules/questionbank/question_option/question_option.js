/**
 * 题目选项管理初始化
 */
var Question_option = {
    id: "Question_optionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    option:{}
};

/**
 * 初始化表格的列
 */
Question_option.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '问题id', field: 'itemId', visible: false, align: 'center', valign: 'middle'},
        {title: '选项名称', field: 'name', align: 'center', valign: 'middle'},
        {title: '选项内容', field: 'content',align: 'center', valign: 'middle'},
        {title: '是否为正确答案', field: 'isAnswer', align: 'center', valign: 'middle',formatter: function(value,row,index){
            if(value==1){
                return '是';

            }else{
                return '否';
            }

        }},
        {title: '创建时间', field: 'createDate',  visible: false,align: 'center', valign: 'middle'},
        {title: '修改时间', field: 'updateDate', visible: false, align: 'center', valign: 'middle'},
        {title: '修改人', field: 'updateBy', visible: false, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: false, align: 'center', valign: 'middle'},
        {title: '排序', field: 'sort', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Question_option.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    // var data =  $('#' + this.id).bootstrapTable('getData');
    // var arrayList=[];
    //    for(var i=0; i<data.length;i++){
    //        var option={'id':data[i].id,'isAnswer':data[i].isAnswer} ;
    //         arrayList[i]=option;
    //    }
    //     Question_option.option.name=arrayList;
    //  var optionDate= JSON.stringify(arrayList);
    // $("#opationId").val(optionDate);

    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Question_option.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加题目选项
 */
Question_option.openAddQuestion_option = function () {
    Question_option.assignOptions();
    var itemId=$("#itemId").val();
    var index = layer.open({
        type: 2,
        title: '添加题目选项',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question_option/question_option_add?itemId='+itemId
    });
    this.layerIndex = index;
};

/**
 * 打开查看题目选项详情
 */
Question_option.openQuestion_optionDetail = function () {
    Question_option.assignOptions();
    if (this.check()) {

        var idd=Question_option.seItem.id;
        var index = layer.open({
            type: 2,
            title: '选项详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question_option/question_option_update?question_optionId=' + idd
        });
        this.layerIndex = index;
    }
};

/**
 * 删除题目选项
 */
Question_option.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/question_option/delete", function (data) {
            Feng.success("删除成功!");
            Question_option.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询题目选项列表
 */
Question_option.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Question_option.table.refresh({query: queryData});
};

$(function () {
    var itemId=$("#itemId").val();
    var defaultColunms = Question_option.initColumn();
    var table = new BSTable(Question_option.id, "/question_option/list?itemId="+itemId, defaultColunms);
    table.setPaginationType("client");
    Question_option.table = table.init();
});

Question_option.assignOptions =function(){
    var data =  $('#' + this.id).bootstrapTable('getData');
    var arrayList=[];
    for(var i=0; i<data.length;i++){
        var option={'id':data[i].id,'isAnswer':data[i].isAnswer} ;
        arrayList[i]=option;
    }
    Question_option.option.name=arrayList;
    var optionDate= JSON.stringify(arrayList);
    $("#opationId").val(optionDate);
    return optionDate;
}
  
Question_option.refreshTable=function(){
    
}

