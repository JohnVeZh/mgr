/**
 * 初始化题目选项详情对话框
 */
var Question_optionInfoDlg = {
    question_optionInfoData : {},
    options:{},
    validateFields: {
        itemId: {
            validators: {
                notEmpty: {
                    message: '小题不能为空'
                }
            }
        },
        isAnswer: {
            validators: {
                notEmpty: {
                    message: '是否正确答案不能为空'
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
Question_optionInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
Question_optionInfoDlg.clearData = function() {
    this.question_optionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_optionInfoDlg.set = function(key, val) {
    this.question_optionInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Question_optionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Question_optionInfoDlg.close = function() {
    parent.layer.close(window.parent.Question_option.layerIndex);
}

/**
 * 收集数据
 */
Question_optionInfoDlg.collectData = function() {
    this.set('id').set('content').set('name').set('isAnswer').set('remarks').set('sort').set("itemId");
}

/**
 * 提交添加
 */
Question_optionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return
    }

    //检验正确答案是是唯一的
    var isAnswer = this.question_optionInfoData.isAnswer;
    var j=0;
    if(isAnswer==1){
        j++;
    }
    var options = JSON.parse( parent.$("#opationId").val());
    for(var i=0 ;i< options.length ;i++){
        isAnswer=options[i].isAnswer;
        if(isAnswer==1){
            j++;
        }
    }
    if(j != 1){
        return  Feng.error("选项有且只有一个正确答案！");
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_option/add", function(data){
        Feng.success("添加成功!");
        window.parent.Question_option.table.refresh();
        Question_optionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.question_optionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
Question_optionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //检验正确答案是是唯一的
    var isAnswer;
    var id=this.question_optionInfoData.id;
    var j=0;
    var options = JSON.parse( parent.$("#opationId").val());
    for(var i=0 ;i< options.length ;i++){
        if(options[i].id==id){
            isAnswer=this.question_optionInfoData.isAnswer;
        }else {
            isAnswer=options[i].isAnswer;
        }
        if(isAnswer==1){
            j++;
        }
    }
    if(j != 1){
        return  Feng.error("选项有且只有一个正确答案！");
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question_option/update", function(data){
        Feng.success("修改成功!");
        window.parent.Question_option.table.refresh();
        Question_optionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.question_optionInfoData);
    ajax.start();
};

$(function() {

    //给下拉菜单赋值
    var isAnswer=$("#isAnswerValue").val();
    $("#isAnswer").val(isAnswer);
    var name=$("#nameValue").val();
    $("#name").val(name);
    Feng.initValidator("Form", Question_optionInfoDlg.validateFields);

});



