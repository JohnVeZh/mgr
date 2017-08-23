/**
 * 翻译题管理初始化
 */
var Question_translation = {
    id: "Question_translationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Question_translation.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
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
        {title: '题目id', field: 'questionId', visible: false, align: 'center', valign: 'middle'},
        {title: '参考范文', field: 'reference', visible: false, align: 'center', valign: 'middle'},
        {title: '视频讲解ccid', field: 'analysisCcId', visible: false, align: 'center', valign: 'middle'},
        {title: '是否存在小题', field: 'hasItem', align: 'center', valign: 'middle',formatter: function(value,row,index){
            if(value==1){ return '<a name='+row.questionId+' onclick="Question_translation.openItem(this)">小题详情</a>';}
            else { return '否';}

        }}
    ];
};

//点击查看小题详情
Question_translation.openItem=function(data){
    //获取题目id
    var questionId=$(data).attr("name");
    parent.Question.openItem(questionId);
}

/**
 * 检查是否选中
 */
Question_translation.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Question_translation.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加翻译题
 */
Question_translation.openAddQuestion_translation = function () {
    var structureId= $("#structureId").val();
    parent.Question.openAddQuestion_translation(structureId);
};

/**
 * 打开查看翻译题详情
 */
Question_translation.openQuestion_translationDetail = function () {
    if (this.check()) {
        parent.Question.openQuestion_translationDetail(Question_translation.seItem.id);
    }
};

/**
 * 删除翻译题
 */
Question_translation.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/question/delete", function (data) {
            Feng.success("删除成功!");
            Question_translation.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questionId",this.seItem.questionId);
        ajax.start();
    }
};

/**
 * 查询翻译题列表
 */
Question_translation.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Question_translation.table.refresh({query: queryData});
};

$(function () {
    //获取body标签 并把其的class去掉
    $(".gray-bg").attr("class","");
    var id=$("#structureId").val();
    var name=$("#contenName").val();
    var defaultColunms = Question_translation.initColumn();
    var table = new BSTable(Question_translation.id, "/question_translation/list?id="+id+"&name="+name, defaultColunms);
    table.setPaginationType("client");
    Question_translation.table = table.init();

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
