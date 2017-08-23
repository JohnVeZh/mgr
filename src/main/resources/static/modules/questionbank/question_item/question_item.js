/**
 * 题目小题表管理初始化
 */
var Question_item = {
    id: "Question_itemTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

    /**
     * 初始化表格的列
     */
    Question_item.initColumn = function () {
        return [
            {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '题目id', field: 'questionId', visible: false, align: 'center', valign: 'middle'},
            {title: '题目名称', field: 'name',  align: 'center', valign: 'middle'},
            {title: '题目类型', field: 'typeName',  align: 'center', valign: 'middle'},
            {title: '小题内容', field: 'content',  align: 'center', valign: 'middle'},
            // {title: '解析', field: 'analysis',  align: 'center', valign: 'middle'},
            // {title: '创建时间', field: 'createDate',  align: 'center', valign: 'middle'},
            // {title: '创建人', field: 'createBy',  align: 'center', valign: 'middle'},
            // {title: '修改时间', field: 'updateBy',  align: 'center', valign: 'middle'},
            {title: '备注', field: 'remarks',  align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', visible: false, align: 'center', valign: 'middle'},
            {title: '选项详情', field: '',  align: 'center', valign: 'middle',formatter: function(value,row,index){
                return '<a name='+row.id+' onclick="Question_item.openOptionDetail(this)">选项详情</a>';
            }}
        ];
    };

    //选项详情
    Question_item.openOptionDetail=function(data){
        var itemId=$(data).attr("name");
        parent.Question.openOptionDetail(itemId);

    }

    /**
     * 检查是否选中
     */
    Question_item.check = function () {
        var selected = $('#' + this.id).bootstrapTable('getSelections');
        if(selected.length == 0){
            Feng.info("请先选中表格中的某一记录！");
            return false;
        }else{
            Question_item.seItem = selected[0];
            return true;
        }
    };



    /**
     * 点击添加题目小题表
     */
    Question_item.openAddQuestion_item = function () {
        var questionId=$("#questionId").val();
        parent.Question.openAddQuestion_item(questionId);
    };

    /**
     * 打开查看题目小题表详情
     */
    Question_item.openQuestion_itemDetail = function () {
        if (this.check()) {
            parent.Question.openQuestion_itemDetail(Question_item.seItem.id);
        }

    };

    /**
     * 跳转到相对用的页面
     */
    Question_item.delete = function () {

        if (this.check()) {
            var ajax = new $ax(Feng.ctxPath + "/question_item/delete", function (data) {
                Feng.success("删除成功!");
                Question_item.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id",this.seItem.id);
            ajax.set("questionId",this.seItem.questionId);
            ajax.start();
        }
    };

    /**
     * 查询题目小题表列表
     */
    Question_item.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        Question_item.table.refresh({query: queryData});
    };

    $(function () {
        var questionId=$("#questionId").val();
        var defaultColunms = Question_item.initColumn();
        var table = new BSTable(Question_item.id, "/question_item/list?questionId="+questionId, defaultColunms);
        table.setPaginationType("client");
    Question_item.table = table.init();
});



Question_item.resetSearch = function () {
    $("#condition").val("");
    Question.catalogid = null;
    Question.search();
}

Question_item.search = function () {
    var queryData = {};
    var name = $("#condition").val();
    queryData['id'] = Question_item.catalogid;
    // alert(queryData.name);
    Question_item.table.refresh({query: queryData});
};

