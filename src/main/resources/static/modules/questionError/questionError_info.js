/**
 * 初始化题目报错详情对话框
 */
var QuestionErrorInfoDlg = {
    questionErrorInfoData : {}
};

/**
 * 清除数据
 */
QuestionErrorInfoDlg.clearData = function() {
    this.questionErrorInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QuestionErrorInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.questionErrorInfoData[key] = val;
    }else{
        this.questionErrorInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QuestionErrorInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QuestionErrorInfoDlg.close = function() {
    parent.layer.close(window.parent.QuestionError.layerIndex);
}

/**
 * 收集数据
 */
QuestionErrorInfoDlg.collectData = function() {
    this.set('id');
    this.set('isSolved',1);
}

/**
 * 提交添加
 */
QuestionErrorInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/questionError/add", function(data){
        Feng.success("添加成功!");
        window.parent.QuestionError.table.refresh();
        QuestionErrorInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionErrorInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QuestionErrorInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/questionError/update", function(data){
        Feng.success("修改成功!");
        window.parent.QuestionError.table.refresh();
        QuestionErrorInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionErrorInfoData);
    ajax.start();
}

$(function() {
    var solved =  $("#isSolved").val();
    var sectionCode =  $("#sectionCode").val();
    var questionTypeValue =  $("#questionType").val();
    if(solved == 0){
        $("#solvedValue").html("待解决");
    }else if(solved == 1){
        $("#solvedValue").html("已解决");
    }
   var sectionValue = "";
    switch(sectionCode)
    {
        case '1': sectionValue = "CET4";break;
        case '2': sectionValue = "CET6"; break;
        case '3': sectionValue = "考研英语"; break;
        case '4': sectionValue = "英语专业"; break;
        case '5': sectionValue = "高中英语"; break;
        case '6': sectionValue = "初中英语"; break;
        case '7': sectionValue = "小学英语"; break;
        default: sectionValue = "";
    }
    $("#sectionValue").html(sectionValue);
   var type = "";
    switch (questionTypeValue){
        case '1':
            type="听力";
            break;
        case '2':
            type="阅读";
            break;
        case '3':
            type="翻译";
            break;
        case '4':
            type="写作";
            break;
    }

    $("#questionTypeValue").html(type);
});
