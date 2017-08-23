/**
 * 初始化视频详情对话框
 */
var VideoInfoDlg = {
    videoInfoData : {},
    validateFields:{
        name: {
            validators: {
                notEmpty: {
                    message: '视频名称不能为空'
                }
            }
        },
        sort: {
            validators: {
                notEmpty: {
                    message: '视频排序不能为空'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '请输入正整数'
                }
            }
        },
        duration: {
            validators: {
                regexp: {
                    regexp: /^\d{2}:\d{2}:\d{2}$/,
                    message: '请输入 00:30:20 格式'
                }
            }
        }
    }

};

/**
 * 清除数据
 */
VideoInfoDlg.clearData = function() {
    this.videoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoInfoDlg.set = function(key, val) {
    this.videoInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * select是否直播
 */
VideoInfoDlg.changeType = function(value) {
    if(value==1){
        $("#size").attr("disabled",true);
        $("#videoCcId").attr("disabled",true);
        $("#liveRoomId").attr("disabled",false);
        $("#startTime").attr("disabled",false);
        $("#liveCcId").attr("disabled",false);
    }else {
        $("#size").attr("disabled",false);
        $("#videoCcId").attr("disabled",false);
        $("#liveRoomId").attr("disabled",true);
        $("#startTime").attr("disabled",true);
        $("#liveCcId").attr("disabled",true);
    }
}




/**
 * 关闭此对话框
 */
VideoInfoDlg.close = function() {
    parent.layer.close(window.parent.Video.layerIndex);
}

/**
 * 收集数据
 */
VideoInfoDlg.collectData = function() {
    this.videoInfoData = {
        id:$("#id").val(),
        networkCourseId:$("#networkCourseId").val(),
        catalogId:$("#catalogId").val(),

        coverImg: $("#coverImg").val(),
        type: $("#type").val(),
        sort: $("#sort").val(),
        remarks: $("#remarks").val(),
        teacherId: $(":radio:checked").val(),

        name: $("#name").val(),
        duration: $("#duration").val(),
        size: $("#size").val(),
        previewCcId: $("#previewCcId").val(),
        videoCcId: $("#videoCcId").val(),
        liveCcId: $("#liveCcId").val(),
        liveRoomId: $("#liveRoomId").val(),
        startTime: $("#startTime").val(),
        videoUrl: $("#videoUrl").val(),
    };
}

//验证
VideoInfoDlg.validate = function () {
    $('#videoInfoForm').data("bootstrapValidator").resetForm();
    $('#videoInfoForm').bootstrapValidator('validate');
    return $("#videoInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 提交添加
 */
VideoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/video/add", function(data){
        Feng.success("添加成功!");
        window.parent.Video.table.refresh();
        VideoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VideoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/video/update", function(data){
        Feng.success("修改成功!");
        window.parent.Video.table.refresh();
        VideoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoInfoData);
    ajax.start();
}


//显示老师列表
VideoInfoDlg.showTeachers=function () {
    var display=$("#teachers").css("display");
    if(display=='none'){
        $("#teachers").css("display","table");
    }else {
        $("#teachers").css("display","none");
    }
}




$(function() {
    //验证初始化
    Feng.initValidator("videoInfoForm", VideoInfoDlg.validateFields);

    // 初始化图片上传
    var avatarUp = new $WebUpload("coverImg");
    avatarUp.init();

    //获取教师列表
    var ajax = new $ax(Feng.ctxPath + "/goods/teacher/course/list", function(data){
        $("#teachers").append("<table class='table table-bordered table-hover' id='teacherTable'><tr>" +
            "<th>选择</th><th width='100px'>老师姓名</th><th  width='120px'>标签</th><th>介绍</th>"+
            "</tr></table>");
        for(var i=0;i<data.length;i++){
            $("#teacherTable").append("<tr id='aaa'><td><input type='radio' name='teacher'  class='teacher' style='width: 30px;' value="+data[i].id+"></td>" +
                "<td>"+data[i].name+"</td>"+
                "<td>"+data[i].tags+"</td>"+
                "<td>"+data[i].introduce+"</td>"+
                "</tr>");
        }
        $("#teacherTable").append("<tr><td colspan='11' align='center'><input class='btn btn-success' onclick='VideoInfoDlg.showTeachers()' type='button' value='确认'></td></tr>");
    });
    ajax.start();

    //表格点击选中单选框
    $("table tr").first().nextAll().click(function () {
        var ckstatus=$(this).find("input").prop("checked");
        if(ckstatus){
            $(this).find("input").prop("checked",false);
        }else{
            $(this).find("input").prop("checked",true);
        }

    });

    //单选框点击改变选中状态
    $(":radio").click(function () {
        var flag=$(this).prop("checked");
        if(flag){
            $(this).prop("checked",false);
        }else{
            $(this).prop("checked",true);
        }
    })


    if($("#id").val()!=''){
        //回显下拉框
        var type=$("#typeValue").val();
        $("#type").val(type);
        VideoInfoDlg.changeType(type);
        //回显单选框
        var teacherId=$("#teachersValue").val();
        $(":radio[value="+teacherId+"]").prop("checked",true);

    }

});
