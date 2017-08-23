/**
 * 初始化学习方案绑定网课列表详情对话框
 */
var PlanNetworkCourseInfoDlg = {
    planNetworkCourseInfoData : {},
    validateFields:{
        networkCourseId:{
            validators:{
                notEmpty:{
                    message:'网课不能为空'
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
        }
    }
};

/**
 * 清除数据
 */
PlanNetworkCourseInfoDlg.clearData = function() {
    this.planNetworkCourseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlanNetworkCourseInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.planNetworkCourseInfoData[key] = val;
    }else{
        this.planNetworkCourseInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlanNetworkCourseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

//显示网课列表
PlanNetworkCourseInfoDlg.showNetworkCourse=function () {
    var display=$("#networkCoursetable").css("display");
    if(display=='none'){
        $("#networkCoursetable").css("display","table");
    }else {
        $("#networkCoursetable").css("display","none");
    }
}

/**
 * 关闭此对话框
 */
PlanNetworkCourseInfoDlg.close = function() {
    parent.layer.close(window.parent.PlanNetworkCourse.layerIndex);
}

/**
 * 收集数据
 */
PlanNetworkCourseInfoDlg.collectData = function() {
    this.set('planId').set('networkCourseId').set('sort').set('id');
}

/**
 * 数据校验
 */
PlanNetworkCourseInfoDlg.validate = function (){
    $("#planNetworkCourseForm").data("bootstrapValidator").resetForm();
    $("#planNetworkCourseForm").bootstrapValidator("validate");
    return $("#planNetworkCourseForm").data("bootstrapValidator").isValid();
}

/**
 * 提交添加
 */
PlanNetworkCourseInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (this.planNetworkCourseInfoData.networkCourseId=="") {
        $("#tip2").css("display","");
        $("#tip2").css("color","#a94442");
        $("#tip1").css("color","#ed5565");
        //提交前数据进行校验
        if (!this.validate()) {
            return;
        }
        return;
    }

    //提交前数据进行校验
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/gift/planNetworkCourse/add", function(data){
        Feng.success("添加成功!");
        window.parent.PlanNetworkCourse.table.refresh();
        PlanNetworkCourseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.planNetworkCourseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PlanNetworkCourseInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (this.planNetworkCourseInfoData.networkCourseId=="") {
        alert("请选择网课")
        return;
    }

    //提交前数据进行校验
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/gift/planNetworkCourse/update", function(data){
        Feng.success("修改成功!");
        window.parent.PlanNetworkCourse.table.refresh();
        PlanNetworkCourseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.planNetworkCourseInfoData);
    ajax.start();
}

$(function() {

    Feng.initValidator("planNetworkCourseForm",PlanNetworkCourseInfoDlg.validateFields);
});
