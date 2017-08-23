/**
 * 初始化学习方案详情对话框
 */
var PlanConfigInfoDlg = {
    editor:{},
    planConfigInfoData : {},
    validateFields:{
        title:{
            validators:{
                notEmpty:{
                    message:'展示名称不能为空'
                },
                regexp:{
                    regexp:/^[0-9]+$/,
                    message:'请输入正整数'
                }
            }
        },
        sort:{
            validators:{
                notEmpty:{
                    message:'排序不能为空'
                },
                regexp:{
                    regexp:/^[0-9]+$/,
                    message:'请输入正整数'
                }
            }
        },
        scoreStart:{
            validators:{
                notEmpty:{
                    message:'起始分数不能为空'
                },
                regexp:{
                    regexp:/^[0-9]+$/,
                    message:"请输入正整数"
                }
            }
        },
        scoreEnd:{
            validators:{
                notEmpty:{
                    message:'起始分数不能为空'
                },
                regexp:{
                    regexp:/^[0-9]+$/,
                    message:"请输入正整数"
                }
            }
        },
        comment:{
            validators:{
                notEmpty:{
                    message:'评语不能为空'
                }
            }
        },
        content:{
            validators:{
                notEmpty:{
                    message:'内容不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
PlanConfigInfoDlg.clearData = function() {
    this.planConfigInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlanConfigInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.planConfigInfoData[key] = val;
    }else{
        this.planConfigInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlanConfigInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PlanConfigInfoDlg.close = function() {
    parent.layer.close(window.parent.PlanConfig.layerIndex);
}

/**
 * 收集数据
 */
PlanConfigInfoDlg.collectData = function() {
    this.set('id').set('sectionCode').set('title').set('scoreStart').set('period').set('sort').set('scoreEnd').set('comment');
    if (this.editor != undefined) {
        var html = this.editor.$txt.html();
        PlanConfigInfoDlg.planConfigInfoData["content"] = html;
    }
}

/**
 * 数据校验
 */
PlanConfigInfoDlg.validate = function (){
    $("#planConfigForm").data("bootstrapValidator").resetForm();
    $("#planConfigForm").bootstrapValidator("validate");
    return $("#planConfigForm").data("bootstrapValidator").isValid();
}

/**
 * 提交添加
 */
PlanConfigInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交前数据进行校验
    if(!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/gift/planConfig/add", function(data){
        Feng.success("添加成功!");
        window.parent.PlanConfig.table.refresh();
        PlanConfigInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.planConfigInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PlanConfigInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/gift/planConfig/update", function(data){
        Feng.success("修改成功!");
        window.parent.PlanConfig.table.refresh();
        PlanConfigInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.planConfigInfoData);
    ajax.start();
}

$(function() {

    Feng.initValidator("planConfigForm",PlanConfigInfoDlg.validateFields);

    //初始化editor
    var planConfigEditor = new $wangEditor("content");
    PlanConfigInfoDlg.editor = planConfigEditor.init();

});
