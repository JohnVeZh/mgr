/**
 * 初始化大礼包试卷批改详情对话框
 */
var PaperCorrectInfoDlg = {
    editor:{},
    paperCorrectInfoData : {}
};


/**
 * 关闭此对话框
 */
PaperCorrectInfoDlg.close = function() {
    parent.layer.close(window.parent.PaperCorrect.layerIndex);
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
        for(var i=0;i<paper.ruleBeans.length;i++){
            for(var i=0;i<paper.ruleBeans.length;i++){
                if(paper.ruleBeans[i].ruleName=='切题性'){
                    $("#rules").append("<strong style='font-size: 14px;'>"+paper.ruleBeans[i].ruleName+"</strong>&nbsp;&nbsp;:&nbsp;&nbsp;<button type='button' class='btn btn-primary' style='width: 130px;'>"+PaperCorrectInfoDlg.getRuleLevel(paper.ruleBeans[i].level)+"</button><br><br>");
                }
            }
            for(var i=0;i<paper.ruleBeans.length;i++){
                if(paper.ruleBeans[i].ruleName=='结构'){
                    $("#rules").append("<strong style='font-size: 14px;'>"+paper.ruleBeans[i].ruleName+"</strong>&nbsp;&nbsp;:&nbsp;&nbsp;<button type='button' class='btn btn-primary' style='width: 130px;'>"+PaperCorrectInfoDlg.getRuleLevel(paper.ruleBeans[i].level)+"</button><br><br>");
                }
            }
            for(var i=0;i<paper.ruleBeans.length;i++){
                if(paper.ruleBeans[i].ruleName=='语法'){
                    $("#rules").append("<strong style='font-size: 14px;'>"+paper.ruleBeans[i].ruleName+"</strong>&nbsp;&nbsp;:&nbsp;&nbsp;<button type='button' class='btn btn-primary' style='width: 130px;'>"+PaperCorrectInfoDlg.getRuleLevel(paper.ruleBeans[i].level)+"</button><br><br>");
                }
            }
            for(var i=0;i<paper.ruleBeans.length;i++){
                if(paper.ruleBeans[i].ruleName=='格式'){
                    $("#rules").append("<strong style='font-size: 14px;'>"+paper.ruleBeans[i].ruleName+"</strong>&nbsp;&nbsp;:&nbsp;&nbsp;<button type='button' class='btn btn-primary' style='width: 130px;'>"+PaperCorrectInfoDlg.getRuleLevel(paper.ruleBeans[i].level)+"</button><br><br>");
                }
            }
            for(var i=0;i<paper.ruleBeans.length;i++){
                if(paper.ruleBeans[i].ruleName=='卷面'){
                    $("#rules").append("<strong style='font-size: 14px;'>"+paper.ruleBeans[i].ruleName+"</strong>&nbsp;&nbsp;:&nbsp;&nbsp;<button type='button' class='btn btn-primary' style='width: 130px;'>"+PaperCorrectInfoDlg.getRuleLevel(paper.ruleBeans[i].level)+"</button><br><br>");
                }
            }
        }

        //回显回复内容
        PaperCorrectInfoDlg.editor.$txt.html(paper.replyContent);



    },function(data){
        Feng.error("获取失败!" + data.responseJSON.message + "!");
    });
    ajax.start();

});
