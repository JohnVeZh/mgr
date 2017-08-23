/**
 * 题目管理初始化
 */
var Question = {
    id: "QuestionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    tree:null,
    paperId:null,
    structureId:null,
    contentType:null,
    type:1,
    listeningLayerIndex:-1,
    readingLayerIndex:-1,
    translationLayerIndex:-1,
    writingLayerIndex:-1
};

/**
 * 初始化表格的列
 */
Question.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};
Question.initListeningColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'questionEntity.id', visible: false, align: 'center', valign: 'middle'},
        {title: '题目名称', field: 'questionEntity.name', visible: true, align: 'center', valign: 'middle'},
        {title: '音频', field: 'audioUrl', visible: true, align: 'center', valign: 'middle',formatter: function(value,row,index){
            return '<audio style="width: 80px" controls="controls"><source src="'+value+'"></audio>';
        }},
        {title: '小题数量', field: 'questionEntity.questionNum', visible: true, align: 'center', valign: 'middle'}
    ];
};
Question.initReadingColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'questionEntity.id', visible: false, align: 'center', valign: 'middle'},
        {title: '题目名称', field: 'questionEntity.name', visible: true, align: 'center', valign: 'middle'},
        {title: '小题数量', field: 'questionEntity.questionNum', visible: true, align: 'center', valign: 'middle'}
    ];
};
Question.initTranslationColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'questionEntity.id', visible: false, align: 'center', valign: 'middle'},
        {title: '题目名称', field: 'questionEntity.name', visible: true, align: 'center', valign: 'middle'},
        {title: '小题数量', field: 'questionEntity.questionNum', visible: true, align: 'center', valign: 'middle'}
    ];
};
Question.initWritingColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'questionEntity.id', visible: false, align: 'center', valign: 'middle'},
        {title: '题目名称', field: 'questionEntity.name', visible: true, align: 'center', valign: 'middle'},
        {title: '小题数量', field: 'questionEntity.questionNum', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 检查是否选中
 */
Question.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Question.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加题目
 */
Question.openAddQuestion = function () {
    if (Question.structureId == undefined) {
        Feng.error("请选择结构!");
        return false;
    }
    var url = "";
    if (Question.type == 1) url = '/question_listening/question_listening_add.do?structureId=' + Question.structureId;
    if (Question.type == 2) url = '/question_reading/question_reading_add.do?structureId=' + Question.structureId;
    if (Question.type == 3) url = '/question_translation/question_translation_add.do?structureId=' + Question.structureId;
    if (Question.type == 4) url = '/question_writing/question_writing_add.do?structureId=' + Question.structureId;
    var index = layer.open({
        type: 2,
        title: '添加题目',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + url
    });
    if (Question.type == 1) this.listeningLayerIndex = index;
    if (Question.type == 2) this.readingLayerIndex = index;
    if (Question.type == 3) this.translationLayerIndex = index;
    if (Question.type == 4) this.writingLayerIndex = index;
};

/**
 * 打开查看题目详情
 */
Question.openQuestionDetail = function () {
    if (this.check()) {
        var url = "";
        if (Question.type == 1) url = '/question_listening/question_listening_update/' + Question.seItem.id;
        if (Question.type == 2) url = '/question_reading/question_reading_update/' + Question.seItem.id;
        if (Question.type == 3) url = '/question_translation/question_translation_update/' + Question.seItem.id;
        if (Question.type == 4) url = '/question_writing/question_writing_update/' + Question.seItem.id;
        var index = layer.open({
            type: 2,
            title: '题目详情',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + url
        });
        if (Question.type == 1) this.listeningLayerIndex = index;
        if (Question.type == 2) this.readingLayerIndex = index;
        if (Question.type == 3) this.translationLayerIndex = index;
        if (Question.type == 4) this.writingLayerIndex = index;
    }
};

//重写前人代码，用于添加成功后关闭窗口
Question.setcontent_type = function () {
    layer.close(Question.layerIndex);
    Question.table.refresh();
}

/**
 * 点击添加题目
 */
Question.openItem = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '小题详情',
            area: ['100%', '100%'], //宽高
            fix: true, //固定
            content: Feng.ctxPath + '/question_item?questionId=' + this.seItem.questionEntity.id
        });
        this.itemLayerIndex = index;
    }
};

Question.openAddQuestion_item = function (questionId) {
    var index = layer.open({
        type: 2,
        title: '添加题目小题表',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/question_item/question_item_add?questionId=' + questionId
    });
    this.iteMeditLayerIndex = index;
};
Question.openQuestion_itemDetail = function (id) {

    var index = layer.open({
        type: 2,
        title: '题目小题表详情',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/question_item/question_item_update/' + id
    });
    this.iteMeditLayerIndex = index;

};
Question.openOptionDetail=function(itemId){
    var index = layer.open({
        type: 2,
        title: '小题选项详情',
        area: ['100%', '100%'], //宽高
        fix: true, //固定
        content: Feng.ctxPath + '/question_option?itemId='+itemId
    });
    this.OptionDetailLayerIndex=index;
}

/**
 * 删除题目
 */
Question.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/question/delete", function (data) {
            Feng.success("删除成功!");
            Question.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questionId",this.seItem.questionEntity.id);
        ajax.start();
    }
};

Question.onClickCatalog = function (e, treeId, treeNode) {
    if (treeNode.level > 0) {
        Question.type = treeNode.getParentNode().id;

        Question.structureId = treeNode.id;
        Question.search();
    }
};

/**
 * 查询题目列表
 */
Question.search = function () {
    var condition = $("#condition").val();
    var url = "";
    Question.table.btInstance.bootstrapTable('destroy');
    if (Question.type == 1) {
        url = "/question_listening/queryList.do?structureId=" + Question.structureId + "&condition=" + condition;
        var defaultColunms = Question.initListeningColumn();
        var table = new BSTable(Question.id, url, defaultColunms);
        Question.table = table.init();
    }
    if (Question.type == 2) {
        url = "/question_reading/queryList.do?structureId=" + Question.structureId + "&condition=" + condition;
        var defaultColunms = Question.initReadingColumn();
        var table = new BSTable(Question.id, url, defaultColunms);
        Question.table = table.init();
    }
    if (Question.type == 3) {
        url = "/question_translation/queryList.do?structureId=" + Question.structureId + "&condition=" + condition;
        var defaultColunms = Question.initTranslationColumn();
        var table = new BSTable(Question.id, url, defaultColunms);
        Question.table = table.init();
    }
    if (Question.type == 4) {
        url = "/question_writing/queryList.do?structureId=" + Question.structureId + "&condition=" + condition;
        var defaultColunms = Question.initWritingColumn();
        var table = new BSTable(Question.id, url, defaultColunms);
        Question.table = table.init();
    }
};

Question.resetSearch = function () {
    $('#condition').val('');
    Question.search();
}

$(function () {

    Question.paperId = $('#paperId').val();
    Question.contentType = window.parent.Paper.seItem.contentType;

    var defaultColunms = Question.initColumn();
    var table = new BSTable(Question.id, "/", defaultColunms);
    Question.table = table.init();

    var ztree = new $ZTree("structureTree", "/paper/paperStructure/structureTree.do?paperId=" + Question.paperId + "&contentType=" + Question.contentType);
    ztree.bindOnClick(Question.onClickCatalog);
    Question.tree = ztree.init();
});
