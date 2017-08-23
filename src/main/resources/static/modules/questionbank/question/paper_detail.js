/**
 * 题目管理初始化
 */
var Question = {
    id: "QuestionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    catalogid:0,
    questionId:0,
    type:0,
    OptionItemLayerIndex:"",
    OptionDetailLayerIndex:"",
    listeningLayerIndex:"",
    readingLayerIndex:"",
    translationLayerIndex:"",
    writingLayerIndex:"",
    itemLayerIndex:"",
    iteMeditLayerIndex:"",
    tree:null
};


Question.resetSearch = function () {
    $("#condition").val("");
    Question.questionId = null;
    Question.search();
}


$(function () {
    //树的加载
   var id=$("#paperId").val();
   var type=$("#type").val();
   var contentType = $("#contentType").val();
    var ztree = new $ZTree("paperCatalogTree", "/question/paperStructureTree?id="+id+"&type="+type+"&contentType="+contentType);
    ztree.bindOnClick(Question.onClickCatalog);
    Question.tree = ztree.init();
});




Question.onClickCatalog = function (e, treeId, treeNode) {
    if (treeNode.level > 0) {
        var structureId=treeNode.id;
        $("#structureId").val(structureId);
        Question.structureId = structureId;
        Question.setcontent_type();
        // Question.search();
    }
};


Question.setcontent_type = function () {
    var id=Question.structureId;
    if (id !='' && id !=undefined) {
        var ajax = new $ax(Feng.ctxPath + "/question/getType", function (data) {
            var contentType=data.contentType;
            if(contentType=="1"){
                $("#myIframe").attr("src", Feng.ctxPath+"/question_listening/index2?id="+id+"&name="+data.name);
            }else if(contentType=="2"){
                $("#myIframe").attr("src", Feng.ctxPath+"/question_reading/index2?id="+id+"&name="+data.name);
            }
            else if(contentType=="3"){
                $("#myIframe").attr("src", Feng.ctxPath+"/question_translation/index2?id="+id+"&name="+data.name);
            }
            else if(contentType=="4"){
                $("#myIframe").attr("src", Feng.ctxPath+"/question_writing/index2?id="+id+"&name="+data.name);
            }

        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",id);
        ajax.start();
    }
};

//点击查看小题详情
Question.openItem=function(questionId){
    var index = layer.open({
        type: 2,
        title: '小题详情',
        area: ['96%', '96%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question_item?questionId='+questionId
    });
    this.itemLayerIndex=index;
}


//选项详情
Question.openOptionDetail=function(itemId){
    var index = layer.open({
        type: 2,
        title: '小题选项详情',
        area: ['96%', '96%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question_option?itemId='+itemId
    });
    this.OptionDetailLayerIndex=index;
}

/**
 * 点击添加听力题
 */
Question.openAddQuestion_listening = function (structureId) {
    var index = layer.open({
        type: 2,
        title: '添加听力题',
        area: ['96%', '96%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question_listening/question_listening_add?structureId='+structureId
    });
    this.listeningLayerIndex = index;
};

/**
 * 打开查看听力题详情
 */
Question.openQuestion_listeningDetail = function (id) {

        var index = layer.open({
            type: 2,
            title: '听力题详情',
            area: ['96%', '96%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question_listening/question_listening_update/' + id
        });
        this.listeningLayerIndex = index;

};

/**
 * 点击添加阅读题
 */
Question.openAddQuestion_reading = function (structureId) {
    var index = layer.open({
        type: 2,
        title: '添加阅读题',
        area: ['99%', '99%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question_reading/question_reading_add?structureId='+structureId
    });
    this.readingLayerIndex = index;
};

/**
 * 打开查看阅读题详情
 */
Question.openQuestion_readingDetail = function (id) {
        var index = layer.open({
            type: 2,
            title: '阅读题详情',
            area: ['96%', '96%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question_reading/question_reading_update/' + id
        });
        this.readingLayerIndex = index;
};

/**
 * 点击添加翻译题
 */
Question.openAddQuestion_translation = function (structureId) {
    var index = layer.open({
        type: 2,
        title: '添加翻译题',
        area: ['96%', '96%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question_translation/question_translation_add?structureId='+structureId
    });
    this.translationLayerIndex = index;
};

/**
 * 打开查看翻译题详情
 */
Question.openQuestion_translationDetail = function (id) {
        var index = layer.open({
            type: 2,
            title: '翻译题详情',
            area: ['96%', '96%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question_translation/question_translation_update/' +id
        });
        this.translationLayerIndex = index;

};

/**
 * 点击添加写作题
 */
Question.openAddQuestion_writing = function (structureId) {
    var index = layer.open({
        type: 2,
        title: '添加写作题',
        area: ['96%', '96%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question_writing/question_writing_add?structureId='+structureId
    });
    this.writingLayerIndex = index;
};

/**
 * 打开查看写作题详情
 */
Question.openQuestion_writingDetail = function (id) {
        var index = layer.open({
            type: 2,
            title: '写作题详情',
            area: ['96%', '96%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question_writing/question_writing_update/' + id
        });
        this.writingLayerIndex = index;

};

/**
 * 点击添加题目小题表
 */
Question.openAddQuestion_item = function (questionId) {
    var index = layer.open({
        type: 2,
        title: '添加题目小题表',
        area: ['96%', '96%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question_item/question_item_add?questionId='+questionId
    });
    this.iteMeditLayerIndex = index;
};

/**
 * 打开查看题目小题表详情
 */
Question.openQuestion_itemDetail = function (id) {

        var index = layer.open({
            type: 2,
            title: '题目小题表详情',
            area: ['96%', '96%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question_item/question_item_update/' + id
        });
        this.iteMeditLayerIndex = index;

};

