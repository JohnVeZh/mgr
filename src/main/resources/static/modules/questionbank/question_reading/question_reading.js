/**
 * 阅读题管理初始化
 */
var Question_reading = {
    id: "Question_readingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    questionId:null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Question_reading.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '题目id', field: 'questionId', visible: false, align: 'center', valign: 'middle'},
        {title: '题目名称', field: 'name', align: 'center', valign: 'middle'},
        {title: '题目类型', field: 'type', align: 'center', valign: 'middle',formatter: function(value,row,index){
            var typeName="";
            switch (value){
                case 1:
                    typeName="听力";
                    break;
                case 2:
                    typeName="阅读";
                    break;
                case 3:
                    typeName="翻译";
                    break;
                case 4:
                    typeName="写作";
                    break;

            }
            return typeName;
        }},
        {title: '译文', field: 'translation', align: 'center', valign: 'middle'},
        {title: '是否存在小题', field: 'hasItem', align: 'center', valign: 'middle',formatter: function(value,row,index){
            if(value==1){ return '<a name='+row.questionId+' onclick="Question_reading.openItem(this)">小题详情</a>';}
            else { return '否';}

        }}
    ];
};

/**
 * 检查是否选中
 */
Question_reading.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Question_reading.seItem = selected[0];
        return true;
    }
};

//点击查看小题详情
Question_reading.openItem=function(data){
    //获取题目id
    var questionId=$(data).attr("name");
    parent.Question.openItem(questionId);

}

/**
 * 点击添加阅读题
 */
Question_reading.openAddQuestion_reading = function () {
    var structureId= $("#structureId").val();
    parent.Question.openAddQuestion_reading(structureId);
};

/**
 * 打开查看阅读题详情
 */
Question_reading.openQuestion_readingDetail = function () {
    if (this.check()) {
        parent.Question.openQuestion_readingDetail(Question_reading.seItem.id);
    }
};

/**
 * 删除阅读题
 */
Question_reading.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/question/delete", function (data) {
            Feng.success("删除成功!");
            Question_reading.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questionId",this.seItem.questionId);
        ajax.start();
    }
};

/**
 * 查询阅读题列表
 */
Question_reading.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Question_reading.table.refresh({query: queryData});
};

$(function () {
    
    //获取body标签 并把其的class去掉
    $(".gray-bg").attr("class","");
    var id=$("#structureId").val();
    var name=$("#contenName").val();
    var defaultColunms = Question_reading.initColumn();
    var table = new BSTable(Question_reading.id, "/question_reading/list?id="+id+"&name="+name, defaultColunms);
    table.setPaginationType("client");
    Question_reading.table = table.init();

    //获取前期选中的试卷目录
    var structureId= $("#structureId").val();
    //检验该节点是否为叶子节点
    var url= Feng.ctxPath + "/question/getPaperStructureById?structureId="+structureId;
    $.ajax({
        url: url,
        type: 'POST',
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            if(returndata=="1"){

            }else {
                //隐藏添加按钮
                $("#add").css("display","none");
            }
        },
        error: function (returndata) {
            Feng.error("导入失败!" + returndata.message + "!");
        }
    });
});
