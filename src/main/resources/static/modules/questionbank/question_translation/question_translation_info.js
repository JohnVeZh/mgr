/**
 * 初始化翻译题详情对话框
 */
var Question_translationInfoDlg = {
    question_translationInfoData : {},
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
                    message: '译文原文不能为空'
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
Question_translationInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
Question_translationInfoDlg.clearData = function() {
    this.question_translationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_translationInfoDlg.set = function(key, val) {
    if(key=="reference"){
        this.question_translationInfoData[key] = (typeof value == "undefined") ? this.editor.$txt.html() : value;
    }else if(key=="content"){
        this.question_translationInfoData[key] = (typeof value == "undefined") ? this.editor2.$txt.html() : value;
    }else {
        this.question_translationInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_translationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Question_translationInfoDlg.close = function() {
    parent.layer.close(window.parent.Question.translationLayerIndex);
}

/**
 * 收集数据
 */
Question_translationInfoDlg.collectData = function() {
    this.set('id').set('questionId').set('reference').set('analysisCcId').set("content").set("name")
        .set("hasItem").set("questionNum").set("remarks") .set("paperId").set("structureId")
        .set("sectionCode").set("type").set("sort");
}

/**
 * 提交添加
 */
Question_translationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_translation/add", function(data){
        Feng.success("添加成功!");
        window.parent.Question.setcontent_type();
        Question_translationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.question_translationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
Question_translationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_translation/update", function(data){
        Feng.success("修改成功!");
       window.parent.Question.setcontent_type();
        Question_translationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.question_translationInfoData);
    ajax.start();
}

$(function() {

    Feng.initValidator("Form", Question_translationInfoDlg.validateFields);

    //初始化editor
    var translBookEditor =  new $wangEditor("reference");
    Question_translationInfoDlg.editor =translBookEditor.init();

    //初始化editor
    var translBookEditor =  new $wangEditor("content");
    Question_translationInfoDlg.editor2 =translBookEditor.init();

    //加载下拉框
    if($("#hasItemValue").val() == undefined){
        $("#hasItem").val(0);
    }else{
        $("#hasItem").val($("#hasItemValue").val());
    }

});
