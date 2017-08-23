/**
 * 初始化题目详情对话框
 */
var QuestionInfoDlg = {
    questionInfoData : {},
    ztreeInstance: null,
    validateFields: {
        type: {
            validators: {
                notEmpty: {
                    message: '题目类型'
                }
            }
        },
        hasItem: {
            validators: {
                notEmpty: {
                    message: '是否存在小题不能为空'
                }
            }
        },
        sectionCode: {
            validators: {
                notEmpty: {
                    message: '学段不能为空'
                }
            }
        }
    }
};

//验证
QuestionInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
QuestionInfoDlg.clearData = function() {
    this.questionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QuestionInfoDlg.set = function(key, val) {
    switch (key){
        case "content":
            this.questionInfoData[key] = (typeof value == "undefined") ? this.editor.$txt.html() : value;
            break;
        case "analysis":
            this.questionInfoData[key] = (typeof value == "undefined") ? this.editor1.$txt.html() : value;
            break;
        default:
            this.questionInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
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
QuestionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QuestionInfoDlg.close = function() {
    parent.layer.close(window.parent.Questionmanage.layerIndex);
}

/**
 * 收集数据
 */
QuestionInfoDlg.collectData = function() {
    this.set('id').set('name').set('type').set('content').set('questionNum').set('hasItem').set('sectionCode').set('remarks').set('analysis')
        .set("paperId").set("structureId").set("structureName").set("alias").set("sort");
}

/**
 * 提交添加
 */
QuestionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question/add", function(data){
        Feng.success("添加成功!");
        window.parent.Questionmanage.table.refresh();
        QuestionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QuestionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if(!this.validate()){
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question/update", function(data){
        Feng.success("修改成功!");
        window.parent.Questionmanage.table.refresh();
        QuestionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionInfoData);
    ajax.start();
}

//初始化学段
var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
    $.each(data,function(i,item){
        $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
    });
}, function (data) {
    Feng.error("获取学段失败!" + data.responseJSON.message + "!");
});
ajax.start();

$(function() {
    //初始化是否是菜单
    if($("#hasItemValue").val() == undefined){
        $("#hasItem").val(0);
    }else{
        $("#hasItem").val($("#ismenuValue").val());
    }
    if($("#typeValue").val() == undefined){
        $("#type").val(1);
    }else{
        $("#type").val($("#typeValue").val());
    }

    //初始化editor
    var translBookEditor =  new $wangEditor("content");
    QuestionInfoDlg.editor =translBookEditor.init();

    //初始化editor
    var translBookEditor1 =  new $wangEditor("analysis");
    QuestionInfoDlg.editor1 =translBookEditor1.init();

});


