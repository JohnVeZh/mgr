/**
 * 初始化分析建议详情对话框
 */
var SpecialSuggestionInfoDlg = {
    specialSuggestionInfoData : {},
    analysisEditor:null,
    suggestionEditor:null,
    validateFields: {
        start: {
            validators: {
                notEmpty: {
                    message: '下限分数不能为空'
                },
                numeric:{
                    message: '内容为数值'
                }
            }
        },
        end: {
            validators: {
                notEmpty: {
                    message: '上限分数不能为空'
                },
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
                numeric:{
                    message: '内容为数值'
                }
            }
        },
        analysis: {
            validators: {
                notEmpty: {
                    message: '分析不能为空'
                }
            }
        },
        suggestion: {
            validators: {
                notEmpty: {
                    message: '建议不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
SpecialSuggestionInfoDlg.clearData = function() {
    this.specialSuggestionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialSuggestionInfoDlg.set = function(key, val) {
    this.specialSuggestionInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialSuggestionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SpecialSuggestionInfoDlg.close = function() {
    parent.layer.close(window.parent.SpecialSuggestion.layerIndex);
}

/**
 * 收集数据
 */
SpecialSuggestionInfoDlg.collectData = function() {
    this.set('id').set('type').set('start').set('sort').set('sectionCode')
        .set('end').set('analysis').set('suggestion');
    // //编辑器值获取
    // var analysis = this.analysisEditor.$txt.html();
    // var suggestion = this.suggestionEditor.$txt.html();
    // SpecialSuggestionInfoDlg.specialSuggestionInfoData["analysis"] = analysis;
    // SpecialSuggestionInfoDlg.specialSuggestionInfoData["suggestion"] = suggestion;
}

/**
 * 验证数据是否为空
 */
SpecialSuggestionInfoDlg.validate = function () {

    // var analysis = this.analysisEditor.$txt.html();
    // if (analysis == undefined || analysis == '<p><br></p>') {
    //     Feng.info("请编辑分析内容！");
    //     return false;
    // }
    // var suggestion = this.suggestionEditor.$txt.html();
    // if (suggestion == undefined || suggestion == '<p><br></p>') {
    //     Feng.info("请编辑建议内容！");
    //     return false;
    // }

    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
SpecialSuggestionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/specialSuggestion/add", function(data){
        Feng.success("添加成功!");
        window.parent.SpecialSuggestion.table.refresh();
        SpecialSuggestionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialSuggestionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SpecialSuggestionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/specialSuggestion/update", function(data){
        Feng.success("修改成功!");
        window.parent.SpecialSuggestion.table.refresh();
        SpecialSuggestionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialSuggestionInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("Form", SpecialSuggestionInfoDlg.validateFields);

    //初始化学段
    var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
        $.each(data,function(i,item){
            $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
        });
    }, function (data) {
        Feng.error("获取学段失败!" + data.responseJSON.message + "!");
    });
    ajax.start();

    //初始化下拉框
    if ($("#sectionCodeValue").val() != undefined) {
        $("#sectionCode").val($("#sectionCodeValue").val());
    }
    if ($("#typeValue").val() != undefined) {
        $("#type").val($("#typeValue").val());
    }

    // //初始化编辑器
    // var analysisEditor = new $wangEditor("analysis");
    // SpecialSuggestionInfoDlg.analysisEditor = analysisEditor.init();
    // var suggestionEditor = new $wangEditor("suggestion");
    // SpecialSuggestionInfoDlg.suggestionEditor = suggestionEditor.init();
});
