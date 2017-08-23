/**
 * 初始化题目小题表详情对话框
 */
var Question_itemInfoDlg = {
    question_itemInfoData : {},
    validateFields: {
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
Question_itemInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
Question_itemInfoDlg.clearData = function() {
    this.question_itemInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_itemInfoDlg.set = function(key, val) {
    switch (key){
        case "content":
            this.question_itemInfoData[key] = (typeof value == "undefined") ? this.editor.$txt.html() : value;
            break;
        case  "analysis":
            this.question_itemInfoData[key] = (typeof value == "undefined") ? this.editor2.$txt.html() : value;
            break;
        default:
            this.question_itemInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            break;
    }

    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_itemInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Question_itemInfoDlg.close = function() {
    parent.layer.close(window.parent.Question.iteMeditLayerIndex);
}

/**
 * 收集数据
 */
Question_itemInfoDlg.collectData = function() {
    this.set('id').set('content').set('analysis').set('remarks').set('sort').set("questionId");
}

/**
 * 提交添加
 */
Question_itemInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    var questionId=$("#questionId").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_item/add", function(data){
        Feng.success("添加成功!");
        parent.layer.close(window.parent.Question.itemLayerIndex);
        window.parent.Question.openItem(questionId);
        Question_itemInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.question_itemInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
Question_itemInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_item/update", function(data){
        Feng.success("修改成功!");
        var questionId=$("#questionId").val();
        parent.layer.close(window.parent.Question.itemLayerIndex);
        window.parent.Question.openItem(questionId);
        Question_itemInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.question_itemInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("Form", Question_itemInfoDlg.validateFields);
    //初始化editor
    var translBookEditor =  new $wangEditor("content");
    Question_itemInfoDlg.editor =translBookEditor.init();
    //初始化editor
    var translBookEditor =  new $wangEditor("analysis");
    Question_itemInfoDlg.editor2 =translBookEditor.init();

});
