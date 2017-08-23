/**
 * 听力题管理初始化
 */
var Question_listening = {
    id: "Question_listeningTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Question_listening.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '题目id', field: 'questionId', visible: false, align: 'center', valign: 'middle'},//
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
        {title: '音频', field: 'audioUrl',  align: 'center', valign: 'middle',formatter: function(value,row,index){
            return '<audio style="width: 80px" controls="controls"><source src="'+value+'"></audio>';
        }},
        {title: '听力原文', field: 'tapescripts',  visible: false, align: 'center', valign: 'middle'},
        {title: '译文', field: 'translation', visible: false, align: 'center', valign: 'middle'},
        {title: '字幕文件地址', field: 'subtitleUrl', visible: false, align: 'center', valign: 'middle'},
        {title: '是否存在小题', field: 'hasItem', align: 'center', valign: 'middle',formatter: function(value,row,index){
            if(value==1){ return '<a name='+row.questionId+' onclick="Question_listening.openItem(this)">小题详情</a>';}
            else { return '否';}

        }}
    ];
};

//点击查看小题详情
Question_listening.openItem=function(data){
    //获取题目id
    var questionId=$(data).attr("name");
    //父类弹出框
    parent.Question.openItem(questionId)
    // var index = layer.open({
    //     type: 2,
    //     title: '小题详情',
    //     area: ['100%', '100%'], //宽高
    //     fix: false, //不固定
    //     maxmin: true,
    //     content: Feng.ctxPath + '/question_item?questionId='+questionId
    // });
}

/**
 * 检查是否选中
 */
Question_listening.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Question_listening.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加听力题
 */
Question_listening.openAddQuestion_listening = function () {
    var structureId= $("#structureId").val();
    parent.Question.openAddQuestion_listening(structureId);
};

/**
 * 打开查看听力题详情
 */Question_listening.openQuestion_listeningDetail = function () {
    if (this.check()) {
        parent.Question.openQuestion_listeningDetail(Question_listening.seItem.id);
    }

};


/**
 * 删除听力题
 */
Question_listening.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/question/delete", function (data) {
            Feng.success("删除成功!");
            Question_listening.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questionId",this.seItem.questionId);
        ajax.start();
    }
};

/**
 * 查询听力题列表
 */
Question_listening.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Question_listening.table.refresh({query: queryData});
};

$(function () {
    //获取body标签 并把其的class去掉
    $(".gray-bg").attr("class","");
    var id=$("#structureId").val();
    var name=$("#contenName").val();
    var defaultColunms = Question_listening.initColumn();
    var table = new BSTable(Question_listening.id, "/question_listening/list?id="+id+"&name="+name, defaultColunms);
    table.setPaginationType("client");
    Question_listening.table = table.init();

    //获取前期选中的试卷目录
    var structureId= $("#structureId").val();
    //检验该节点是否为叶子节点(判断该节点下是否有题目)
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
            Feng.error( returndata.message + "!");
        }
    });
});
