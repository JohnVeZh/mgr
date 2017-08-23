/**
 * 初始化阅读题详情对话框
 */
var Question_readingInfoDlg = {
    question_readingInfoData : {},
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

/**
 * 清除数据
 */
Question_readingInfoDlg.clearData = function() {
    this.question_readingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_readingInfoDlg.set = function(key, val) {
    if(key=="translation"){
        this.question_readingInfoData[key] = (typeof value == "undefined") ? this.editor.$txt.html() : value;
    }else if(key=="content"){
        this.question_readingInfoData[key] = (typeof value == "undefined") ? this.editor2.$txt.html() : value;
    }
    else {
        this.question_readingInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_readingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Question_readingInfoDlg.close = function() {
    parent.layer.close(window.parent.Question.readingLayerIndex);
}

/**
 * 收集数据
 */
Question_readingInfoDlg.collectData = function() {
    this.set('id').set('questionId').set('translation').set("content").set("name").set("hasItem").set("questionNum").set("remarks")
        .set("paperId").set("structureId").set("sectionCode").set("type").set("sort");

}

/**
 * 验证数据是否为空
 */
Question_readingInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
Question_readingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_reading/add", function(data){
        Feng.success("添加成功!");
        window.parent.Question.setcontent_type();
        Question_readingInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.question_readingInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
Question_readingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if( !this.validate()){
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_reading/update", function(data){
        Feng.success("修改成功!");
        window.parent.Question.setcontent_type();
        Question_readingInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.question_readingInfoData);
    ajax.start();
}

$(function() {

    Feng.initValidator("Form", Question_readingInfoDlg.validateFields);

    //初始化editor
    var translBookEditor =  new $wangEditor("translation");
    Question_readingInfoDlg.editor =translBookEditor.init();

    //初始化editor
    var translBookEditor =  new $wangEditor("content");
    Question_readingInfoDlg.editor2 =translBookEditor.init();

    //加载下拉框
    if($("#hasItemValue").val() == undefined){
        $("#hasItem").val(0);
    }else{
        $("#hasItem").val($("#hasItemValue").val());
    }

});
