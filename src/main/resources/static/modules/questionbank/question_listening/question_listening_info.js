/**
 * 初始化听力题详情对话框
 */
var Question_listeningInfoDlg = {
    question_listeningInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '题目名称不能为空',
                    verify:false

                }
            }
        },
        hasItem: {
            validators: {
                notEmpty: {
                    message: '小题不能为空'
                }
            }
        },
        questionNum :{
            validators: {
                numeric:{
                    message: '内容为数值'

                }
            }
        },
        sort: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '请输入正整数'
                }
            }
        }
    }
};

//验证
Question_listeningInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
Question_listeningInfoDlg.clearData = function() {
    this.question_listeningInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_listeningInfoDlg.set = function(key, val) {
    if(key=="tapescripts"){ //content
        this.question_listeningInfoData[key] = (typeof value == "undefined") ? this.editor.$txt.html() : value;
    }else if(key=="translation") {
        this.question_listeningInfoData[key] = (typeof value == "undefined") ? this.editor1.$txt.html() : value;
    }else if(key=="content") {
        this.question_listeningInfoData[key] = (typeof value == "undefined") ? this.editor2.$txt.html() : value;
    }
    else {
        this.question_listeningInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_listeningInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Question_listeningInfoDlg.close = function() {
    parent.layer.close(window.parent.Question.listeningLayerIndex);
}

/**
 * 收集数据
 */
Question_listeningInfoDlg.collectData = function() {
    this.set('id').set("questionId").set("audioUrl").set("tapescripts").set("translation").set("subtitleUrl").set("type")
        .set("paperId").set("structureId").set("sectionCode").set("name").set("hasItem").set("remarks").set("content").set("sort");
}

/**
 * 提交添加
 */
Question_listeningInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //获取前期选中的试卷目录
    var structureId= parent.$("#structureId").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_listening/add", function(data){
        Feng.success("添加成功!");
        window.parent.Question.setcontent_type();
        Question_listeningInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    var data1=this.question_listeningInfoData;
    ajax.set(data1);
    ajax.start();
    //检验该节点是否为叶子节点
    // var url= Feng.ctxPath + "/question/getPaperStructureById?structureId="+structureId;
    // $.ajax({
    //     url: url,
    //     type: 'POST',
    //     async: false,
    //     cache: false,
    //     contentType: false,
    //     processData: false,
    //     success: function (returndata) {
    //         if(returndata=="1"){
    //
    //
    //         }else {
    //             Feng.success("请选择叶子节点！!");
    //         }
    //     },
    //     error: function (returndata) {
    //         Feng.error("导入失败!" + returndata.message + "!");
    //     }
    // });
}

/**
 * 提交修改
 */
Question_listeningInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_listening/update", function(data){
        Feng.success("修改成功!");
        window.parent.Question.setcontent_type();
        Question_listeningInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.question_listeningInfoData);
    ajax.start();
}

$(function() {

    Feng.initValidator("Form", Question_listeningInfoDlg.validateFields);
    //初始化editor
    var bookEditor = new $wangEditor("tapescripts");
    Question_listeningInfoDlg.editor =bookEditor.init();

    //初始化editor
    var translBookEditor =  new $wangEditor("translation");
    Question_listeningInfoDlg.editor1 =translBookEditor.init();

    //editor2
    var translBookEditor1 =  new $wangEditor("content");
    Question_listeningInfoDlg.editor2 =translBookEditor1.init();

    //加载下拉框
    if($("#hasItemValue").val() == undefined){
        $("#hasItem").val(0);
    }else{
        $("#hasItem").val($("#hasItemValue").val());
    }




});
