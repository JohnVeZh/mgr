/**
 * 题目管理初始化
 */
var Question = {
    id: "QuestionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    catalogId:0,
    type:0,
    pId:-1,
    tree:null
};

/**
 * 初始化表格的列
 */
Question.initColumn = function () {

    return [  {field: 'selectItem', radio: true},
    {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
    {title: '试卷图片', field: 'img', visible: true, align: 'center', valign: 'middle',width:'20%',formatter: function(value,row,index){
        return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
    }},
    {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
     {title: '内容类型', field: 'contentType', visible: false, align: 'center', valign: 'middle'},
    {title: '内容类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'},
    // {title: '二维码', field: 'qrCode', visible: true, align: 'center', valign: 'middle'},
    {title: '题目总数', field: 'questionNum', visible: true, align: 'center', valign: 'middle'},
     {title: '操作', field: '', visible: true, align: 'center', valign: 'middle' ,formatter: function(value,row,index){
            return '<a  id="'+row.id+'" name="'+row.name+'" value="'+row.contentType+'" onclick="Question.openPaperDetail(this)">试卷详情</a>';
        }}
    ];
};

Question.initColumnSubtitle = function () {

    return [  {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '试卷图片', field: 'img', visible: true, align: 'center', valign: 'middle',width:'20%',formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentType', visible: false, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '二维码', field: 'qrCode', visible: false, align: 'center', valign: 'middle'},
        // {title: '音频地址', field: 'audioUrl', visible: true, align: 'center', valign: 'middle',formatter: function(value,row,index){
        //     return '<audio style="width: 80px" controls="controls"><source src="'+value+'"></audio>';
        // }},
        {title: '字幕文件地址', field: 'subtitleUrl', visible: false, align: 'center', valign: 'middle'},
        // {title: '题目总数', field: 'questionNum', visible: true, align: 'center', valign: 'middle'},
        {title: '操作', field: '', visible: true, align: 'center', valign: 'middle' ,formatter: function(value,row,index){
            return '<a id='+row.id+' name='+row.sectionCode+'" value="'+row.contentType+'" onclick="Question.openVoice(this)">音频</a>&nbsp;&nbsp;'+
                '<a id='+row.id+' name='+row.sectionCode+'" value="'+row.contentType+'" onclick="Question.openVideo(this)">视频</a>';
        }}
    ];
};

Question.initColumnQRSubtitle = function () {

    return [  {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '试卷图片', field: 'img', visible: true, align: 'center', valign: 'middle',width:'20%',formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentType', visible: false, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '二维码', field: 'qrCode', visible: false, align: 'center', valign: 'middle'},
        {title: '学段', field: 'sectionCodeName', visible: true, align: 'center', valign: 'middle'},
        // {title: '音频', field: 'audioUrl', visible: true, align: 'center', valign: 'middle',formatter: function(value,row,index){
        //     return '<audio style="width: 80px" controls="controls"><source src="'+value+'"></audio>';
        // }},
        {title: '字幕文件地址', field: 'subtitleUrl', visible: false, align: 'center', valign: 'middle'},
        {title: '操作', field: '', visible: true, align: 'center', valign: 'middle' ,formatter: function(value,row,index){
            return '<a id='+row.id+' name='+row.sectionCode+'" value="'+row.contentType+'" onclick="Question.openVoice(this)">音频</a>&nbsp;&nbsp;'+
                '<a id='+row.id+' name='+row.sectionCode+'" value="'+row.contentType+'" onclick="Question.openVideo(this)">视频</a>';
        }}
    ];
};


Question.initColumnSpecial = function () {

    return [  {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '试卷图片', field: 'img', visible: true, align: 'center', valign: 'middle',width:'20%',formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentType', visible: false, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentTypeName', visible: false, align: 'center', valign: 'middle'},
        {title: '二维码', field: 'qrCode', visible: false, align: 'center', valign: 'middle'},
        {title: '题目总数', field: 'questionNum', visible: true, align: 'center', valign: 'middle'},
        {title: '操作', field: '', visible: true, align: 'center', valign: 'middle' ,formatter: function(value,row,index){
            return '<a id='+row.id+' name='+row.name+'"  value="'+row.contentType+'"onclick="Question.openPaperDetail(this)">试卷详情</a>';
        }}
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
 * 点击题目
 */
Question.openQuestion = function (data) {
    var type=$("#type").val();
    var paperId=$(data).attr("id");
    var sectionCode=$(data).attr("name");
    var contentType = $(data).attr("value");
    var index = layer.open({
        type: 2,
        title: '题目',
        area: ['99%', '99%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/CaptionListening/captionListeningManage?type='+type+"&paperId="+paperId+"&contentType="+contentType+
            "&sectionCode="+sectionCode
    });
    this.layerIndex = index;
};

/**
 * 打开查看题目详情
 */
Question.openQuestionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '题目详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question/question_update?type='+type+"&paperId="+paperId +"&contentType="+contentType
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看试卷详情
 */
Question.openPaperDetail = function (data) {
    var type1=$("#type").val();
    var id=$(data).attr("id");
    var paperName=$(data).attr("name");
    var contentType = $(data).attr("value");
    // if (this.check()) {
        var index = layer.open({
            type: 2,
            title: paperName+'试卷详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question/index2?id=' + id+"&type1="+type1+"&contentType="+contentType
        });
        this.layerIndex = index;
    // }
};

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
        ajax.set("questionId",this.seItem.id);
        ajax.start();
    }
};


Question.resetSearch = function () {
    $("#Question").val("");
    Question.catalogId = null;
    Question.search();
}

//音频
Question.openVoice=function(data){
    var type=$("#type").val();
    var paperId=$(data).attr("id");
    var index = layer.open({
        type: 2,
        title: '音频',
        area: ['99%', '99%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/CaptionListening/captionListeningManage?type='+type+"&paperId="+paperId
    });
    this.layerIndex = index;
}

//视频
Question.openVideo=function(data){
    var type=$("#type").val();
    var paperId=$(data).attr("id");
    var index = layer.open({
        type: 2,
        title: '视频',
        area: ['99%', '99%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/CaptionListeningVideo/captionListeningVideoManage?type='+type+"&paperId="+paperId
    });
    this.layerIndex = index;
}


$(function () {
    var type=$("#type").val();
    var defaultColunms="";
    var sectionCodes=$("#sectionCodes").val();//获取学段//

    // var buttonAdd='<button type="button" class="btn btn-primary " onclick="Question.openAddQuestion()" id="" title="">'
    //     +' <i class="fa fa-plus"></i>&nbsp;添加 </button> &nbsp;&nbsp; ';
    // var buttonUpdate='<button type="button" class="btn btn-primary button-margin" onclick="Question.openQuestionDetail()" id="" title=""> '+
    //     '<i class="fa fa-pencil"></i>&nbsp;修改 </button> &nbsp;&nbsp;';
    // var buttonDelete='<button type="button" class="btn btn-primary button-margin" onclick="Question.delete()" id="" title="">'
    //     +'<i class="fa fa-trash"></i>&nbsp;删除 </button> &nbsp;&nbsp;';
    var buttonListen ='<button type="button" class="btn btn-primary" onclick="Question.downloadListening()" id="">'
        +'<i class="fa fa-download"></i>&nbsp;听力模板</button> &nbsp;&nbsp;';
    var buttonRead  ='<button type="button" class="btn btn-primary" onclick="Question.downloadReading()" id="">'
        +'<i class="fa fa-download"></i>&nbsp;阅读模板</button>&nbsp;&nbsp;';
    var buttonTran ='<button type="button" class="btn btn-primary" onclick="Question.downloadTranslation()" id="">'
        +'<i class="fa fa-download"></i>&nbsp;翻译模板</button>&nbsp;&nbsp;';
    var buttonWrite ='<button type="button" class="btn btn-primary" onclick="Question.downloadWriting()" id="">'
        +'<i class="fa fa-download"></i>&nbsp;写作模板</button>&nbsp;&nbsp;';
    var buttonUpload ='<button type="button" class="btn btn-primary" onclick="Question.Upload()" id="">'
        +'<i class="fa fa-upload"></i>&nbsp;上传Excel</button>';

    var ss='<button type="button" class="btn btn-primary" style="width: 105px;height: 34px">'+
        '<label style="margin-top: -25px">'+
        ' <input type="file" id="UploadFile" name="myfile" style="visibility: hidden" onchange="Question.Upload()"/>'+
        ' <i class="fa fa-download"></i>&nbsp;上传Excel </label> </button>';


    switch (type){
        case '1':
            $("#PaperTableToolbar").append(buttonListen+buttonRead+buttonTran+buttonWrite+buttonUpload);
            defaultColunms = Question.initColumn();
            break;
        case '2' :
            $("#PaperTableToolbar").append(buttonListen+buttonUpload);
            defaultColunms = Question.initColumnSubtitle();
            $("#ImportTypeGroup").remove();
            break;
        case '3' :
            $("#PaperTableToolbar").append(buttonListen+buttonUpload);
            defaultColunms = Question.initColumn();
            $("#ImportTypeGroup").remove();
            break;
        case '4':
            $("#PaperTableToolbar").append(buttonListen+buttonRead+buttonTran+buttonWrite+buttonUpload);
            defaultColunms = Question.initColumnSpecial();
            break;
        case '5':
            // $("#PaperTableToolbar").append(buttonAdd+buttonUpdate+buttonDelete);
            defaultColunms = Question.initColumnQRSubtitle();
            $("#ImportTypeGroup").remove();
            break;
    }


    var queryParams={};
    queryParams["type"]=type;
    queryParams["sectionCodes"]=Question.pId;
    var table = new BSTable(Question.id, "/question/pageList?type="+type, defaultColunms);
    table.setPaginationType("client");
    table.setQueryParams(queryParams);
    Question.table = table.init();

    //树的加载
    var ztree = new $ZTree("paperCatalogTree", "/question/tree?type="+type+"&sectionCodes="+sectionCodes);
    ztree.bindOnClick(Question.onClickCatalog);
    Question.tree = ztree.init();
});




Question.onClickCatalog = function (e, treeId, treeNode) {
    if (treeNode.level > 0) {
    var id=treeNode.id;
    var pId=treeNode.pId;
        Question.catalogId = treeNode.id;
        Question.pId = treeNode.pId;
        Question.search();
    }
};

/**
 * 添加题目
 */
Question.addQuestion=function(){

}

Question.search = function () {
    var queryData = {};
    var name = $("#Question").val();
    queryData['type']=$("#type").val();
    if(name !=null && name !=""){
        queryData['queryName']=name;
    }
    if (Question.catalogId != '0') {
        queryData['catalogId']=Question.catalogId;
    }
    Question.table.refresh({query: queryData});
    Question.catalogId='0';
};



Question.downloadListening=function(){
    var type=$("#type").val();
    if(type =="1"){

        window.location.href = Feng.ctxPath + "/FileUoload/downloadFile?type=" + "1";

    }else if(type =="2"){
        window.location.href = Feng.ctxPath + "/FileUoload/downloadFile?type=" + "5";

    }else if(type =="3"){
        window.location.href = Feng.ctxPath + "/paper/template?type=" + "5";

    }else if(type =="4"){
        window.location.href = Feng.ctxPath + "/paper/template?type=" + "1";
    }
}

Question.downloadReading=function(){
    var type=$("#type").val();
    if(type =="1"){
        window.location.href = Feng.ctxPath + "/FileUoload/downloadFile?type=" + "2";

    }else if(type =="3"){
        window.location.href = Feng.ctxPath + "/paper/template?type=" + "2";

    }
}

Question.downloadTranslation=function(){

    var type=$("#type").val();
    if(type =="1"){
        window.location.href = Feng.ctxPath + "/FileUoload/downloadFile?type=" + "3";

    }else if(type =="3"){
        window.location.href = Feng.ctxPath + "/paper/template?type=" + "4";

    }
}

Question.downloadWriting=function(){

    var type=$("#type").val();
    if(type =="1"){
        window.location.href = Feng.ctxPath + "/FileUoload/downloadFile?type=" + "4";

    }else if(type =="3"){
        window.location.href = Feng.ctxPath + "/paper/template?type=" + "3";

    }
}

Question.Upload=function(){
    var contentType = $("#ImportType").val();
    var type=$("#type").val();
    if(type !="2" && type != "5" && type !='3'){
        if(contentType=="" || contentType==undefined){
            return  Feng.info("请选择导入类型");
        }
    }else {
        contentType="1";
    }
    if (this.check()) {
        var type=$("#type").val();
        //获取试卷id
        var paperId=Question.seItem.id;
        var paperContentType=  Question.seItem.contentType;
        var contentTypeName = Question.seItem.contentTypeName;
        if(paperContentType!="0"){
            if(contentType != paperContentType){
                return Feng.info("导入类型请选择"+contentTypeName);
            }
        }
        var index = layer.open({
            type: 2,
            title: '文件上传',
            area: ['300px', '220px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question/Upload?type=' + type+'&paperId='+paperId+"&contentType="+contentType
        });
        this.layerIndex = index;
    }

};



//var formData = new FormData($("#uploadForm")[0]);