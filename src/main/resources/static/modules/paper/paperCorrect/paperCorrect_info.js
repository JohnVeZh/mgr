/**
 * 初始化大礼包试卷批改详情对话框
 */
var PaperCorrectInfoDlg = {
    editor:{},
    paperCorrectInfoData : {}
};

/**
 * 清除数据
 */
PaperCorrectInfoDlg.clearData = function() {
    this.paperCorrectInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperCorrectInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.paperCorrectInfoData[key] = val;
    }else{
        this.paperCorrectInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperCorrectInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PaperCorrectInfoDlg.close = function() {
    parent.layer.close(window.parent.PaperCorrect.layerIndex);
}

/**
 * 收集数据
 */
PaperCorrectInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
PaperCorrectInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paperCorrect/add", function(data){
        Feng.success("添加成功!");
        window.parent.PaperCorrect.table.refresh();
        PaperCorrectInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperCorrectInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PaperCorrectInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paperCorrect/update", function(data){
        Feng.success("修改成功!");
        window.parent.PaperCorrect.table.refresh();
        PaperCorrectInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperCorrectInfoData);
    ajax.start();
}

//根据学段和试卷类型获取标题
PaperCorrectInfoDlg.getTitle = function(sectionCode,questionType) {
    if(sectionCode=='1'){
        switch(questionType) {
            case 3:
                return '四级翻译';
            case 4:
                return '四级写作';
            default:
                break;
        }
    }else if(sectionCode=='2'){
        switch(questionType) {
            case 3:
                return '六级翻译';
            case 4:
                return '六级写作';
            default:
                break;
        }
    }
}

//根据学段和试卷类型获取标题
PaperCorrectInfoDlg.getRuleLevel = function(level) {

        switch(level) {
            case 1:
                return '差';
            case 2:
                return '中';
            case 3:
                return '好';
            default:
                break;
        }

}


$(function() {

    //初始化editor
    var PaperEditor = new $wangEditor("content");
    PaperCorrectInfoDlg.editor =PaperEditor.init();

    //初始化试卷详情数据
    var answerId=$("#answerId").val();
    var ajax = new $ax(Feng.ctxPath + "/paper/paperCorrect/detail/"+answerId, function(paper){
        //回显标题
        var title=PaperCorrectInfoDlg.getTitle(paper.sectionCode,paper.questionType);
        $("#title").append(title);

        //回显图片
        $("#userAnswer").prop("src",paper.userAnswer);

        //回显规则
        var ruleAjax = new $ax(Feng.ctxPath + "/paper/paperCorrect/rules", function(rules){


        },function(data){
            Feng.error("获取规则失败!" + data.responseJSON.message + "!");
        });
        ruleAjax.set({"sectionCode":paper.sectionCode,"questionType":paper.questionType});
        ruleAjax.start();


        //回显回复内容
        PaperCorrectInfoDlg.editor.$txt.html(paper.replyContent);



    },function(data){
        Feng.error("获取失败!" + data.responseJSON.message + "!");
    });
    ajax.start();

});
