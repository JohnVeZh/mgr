/**
 * 初始化网课详情对话框
 */
var NetworkCourseInfoDlg = {
    editor:{},
    networkCourseInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '课程名称不能为空'
                }
            }
        },
        liveRoomId: {
            validators: {
                notEmpty: {
                    message: '直播间ID不能为空'
                }
            }
        },
        limitNumber: {
            validators: {
                notEmpty: {
                    message: '限购人数不能为空'
                }
            }
        },
        catalogNumber: {
            validators: {
                notEmpty: {
                    message: '课程时数不能为空'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '请输入正整数'
                }
            }
        },
        sort: {
            validators: {
                notEmpty: {
                    message: '课程排序不能为空'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '请输入正整数'
                }
            }
        },
        brief: {
            validators: {
                notEmpty: {
                    message: '课程简介不能为空'
                }
            }
        },
        presentPrice: {
            validators: {
                notEmpty: {
                    message: '课程现价不能为空'
                },
                regexp: {
                    regexp: /^(([0-9]\d*(\.\d{2}))|([1-9]\d*))$/,
                    message: '请输入大于等于0的价格，例如18.00'
                }
            }
        },
        originalPrice: {
            validators: {
                notEmpty: {
                    message: '课程原价不能为空'
                },
                regexp: {
                    regexp: /^(([0-9]\d*(\.\d{2}))|([1-9]\d*))$/,
                    message: '请输入大于等于0的价格，例如18.00'
                }
            }
        },
        content: {
            validators: {
                notEmpty: {
                    message: '课程详情不能为空'
                }
            }
        }

    }
};

/**
 * 清除数据
 */
NetworkCourseInfoDlg.clearData = function() {
    this.networkCourseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NetworkCourseInfoDlg.set = function(key, val) {
    this.networkCourseInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NetworkCourseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NetworkCourseInfoDlg.close = function() {
    parent.layer.close(window.parent.NetworkCourse.layerIndex);
}

/**
 * select是否直播
 */
NetworkCourseInfoDlg.changeIsLive = function(value) {
    if(value==1){
        $("#liveRoomId").attr("disabled",false);
        $("#startTime").attr("disabled",false);
        $("#endTime").attr("disabled",false);
        $("#limitNumber").attr("disabled",false);
        $("#reserveStartTime").attr("disabled",false);
    }else {
        $("#liveRoomId").attr("disabled",true);
        $("#startTime").attr("disabled",true);
        $("#endTime").attr("disabled",true);
        $("#limitNumber").attr("disabled",true);
        $("#reserveStartTime").attr("disabled",true);
    }
}


/**
 * select是否免费
 */
NetworkCourseInfoDlg.changeIsLimitFree = function(value) {
    if(value==1){
        $("#limitStartTime").attr("disabled",false);
        $("#limitEndTime").attr("disabled",false);
    }else {
        $("#limitStartTime").attr("disabled",true);
        $("#limitEndTime").attr("disabled",true);
    }
}

/**
 * select是否邮寄
 */
NetworkCourseInfoDlg.changeIsSend = function(value) {
    if(value==1){
        $("#postage").attr("disabled",false);
    }else {
        $("#postage").attr("disabled",true);
    }
}

/**
 * 收集数据
 */
NetworkCourseInfoDlg.collectData = function() {
    // this.set('id');
    //获取选中教师
    var teachers="";
    $(".teacher:checked").each(function(){
        teachers+=$(this).val()+" ";
    })
    Feng.success()
    this.networkCourseInfoData = {
        id:$("#id").val(),
        productId:$("#productId").val(),
        // bookId:$("#bookId").val(),
        listImg: $("#listImg").val(),
        coverImg: $("#coverImg").val(),
        contentImgs:$("#contentImgs").val(),
        sectionCode:$("#sectionCode").val(),
        courseType:$("#courseType").val(),
        isLive:$("#isLive").val(),
        isFree:$("#isFree").val(),
        isLimitFree:$("#isLimitFree").val(),
        isFamousTeacher:$("#isFamousTeacher").val(),
        isShow:$("#isShow").val(),
        isPromotion:$("#isPromotion").val(),
        isPostage:$("#isPostage").val(),
        isSend:$("#isSend").val(),
        hasCatalog:$("#hasCatalog").val(),
        teacherNames:teachers,

        name:$("#name").val(),
        brief:$("#brief").val(),
        previewCcId:$("#previewCcId").val(),
        liveRoomId:$("#liveRoomId").val(),
        originalPrice:$("#originalPrice").val(),
        presentPrice:$("#presentPrice").val(),
        postage:$("#postage").val(),
        limitNumber:$("#limitNumber").val(),
        catalogNumber:$("#catalogNumber").val(),
        sort:$("#sort").val(),
        qqGroupNo:$("#qqGroupNo").val(),
        remarks:$("#remarks").val(),
        reserveStartTime:$("#reserveStartTime").val(),
        shelfOnTime:$("#shelfOnTime").val(),
        shelfOffTime:$("#shelfOffTime").val(),
        startTime:$("#startTime").val(),
        endTime:$("#endTime").val(),
        saleStartTime:$("#saleStartTime").val(),
        saleEndTime:$("#saleEndTime").val(),
        limitStartTime:$("#limitStartTime").val(),
        limitEndTime:$("#limitEndTime").val(),

        content: this.editor.$txt.html()
    };
}


NetworkCourseInfoDlg.validate = function () {
    $('#networkCourseInfoForm').data("bootstrapValidator").resetForm();
    $('#networkCourseInfoForm').bootstrapValidator('validate');
    return $("#networkCourseInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
NetworkCourseInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/networkCourse/add", function(data){
        Feng.success("添加成功!");
        window.parent.NetworkCourse.table.refresh();
        NetworkCourseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.networkCourseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NetworkCourseInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/networkCourse/update", function(data){
        Feng.success("修改成功!");
        window.parent.NetworkCourse.table.refresh();
        NetworkCourseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.networkCourseInfoData);
    ajax.start();
}




//显示老师列表
NetworkCourseInfoDlg.showTeachers=function () {
    var display=$("#teachers").css("display");
    if(display=='none'){
        $("#teachers").css("display","table");
    }else {
        $("#teachers").css("display","none");
    }
}



$(function() {

    Feng.initValidator("networkCourseInfoForm", NetworkCourseInfoDlg.validateFields);

    // 初始化图片上传
    var listImg = new $WebUpload("listImg");
    listImg.init();
    var coverImg = new $WebUpload("coverImg");
    coverImg.init();
    var contentImgs = new $MyWebUpload("contentImgs");
    contentImgs.init();


    //获取教师列表
    var ajax = new $ax(Feng.ctxPath + "/goods/teacher/course/list", function(data){
        $("#teachers").append("<table class='table table-bordered table-hover' id='teacherTable'><tr>" +
                                "<th>选择</th><th width='100px'>老师姓名</th><th  width='120px'>标签</th><th>介绍</th>"+
                                "</tr></table>");
        for(var i=0;i<data.length;i++){
            $("#teacherTable").append("<tr id='aaa'><td><input type='checkbox'  class='teacher' style='width: 30px;' value="+data[i].name+"></td>" +
                                        "<td>"+data[i].name+"</td>"+
                                        "<td>"+data[i].tags+"</td>"+
                                        "<td>"+data[i].introduce+"</td>"+
                                        "</tr>");
        }
        $("#teacherTable").append("<tr><td colspan='11' align='center'><input class='btn btn-success' onclick='NetworkCourseInfoDlg.showTeachers()' type='button' value='确认'></td></tr>");
    });
    ajax.start();

    //表格点击选中复选框
    $("table tr").first().nextAll().click(function () {
        var ckstatus=$(this).find("input").prop("checked");
        if(ckstatus){
            $(this).find("input").prop("checked",false);
        }else{
            $(this).find("input").prop("checked",true);
        }

    });

    //复选框选框点击改变选中状态
    $(".teacher").click(function () {
        var flag=$(this).prop("checked");
        if(flag){
            $(this).prop("checked",false);
        }else{
            $(this).prop("checked",true);
        }
    })


    //初始化editor
    var networkCourseEditor = new $wangEditor("content");
    NetworkCourseInfoDlg.editor =networkCourseEditor.init();



    if($("#id").val()!=''){
        //回显下拉框
        $("#sectionCode").val($("#sectionCodeValue").val());
        $("#courseType").val($("#courseTypeValue").val());
        $("#isFamousTeacher").val($("#isFamousTeacherValue").val());
        $("#isShow").val($("#isShowValue").val());
        $("#isFree").val($("#isFreeValue").val());
        $("#isPromotion").val($("#isPromotionValue").val());
        $("#isPostage").val($("#isPostageValue").val());
        $("#hasCatalog").val($("#hasCatalogValue").val());

        var isLive=$("#isLiveValue").val();
        $("#isLive").val(isLive);
        NetworkCourseInfoDlg.changeIsLive(isLive);
        var isSend=$("#isSendValue").val();
        $("#isSend").val(isSend);
        NetworkCourseInfoDlg.changeIsSend(isSend)
        var isLimitFree=$("#isLimitFreeValue").val();
        $("#isLimitFree").val(isLimitFree);
        NetworkCourseInfoDlg.changeIsLimitFree(isLimitFree);



        //回显详情多图
        var contentImgs = $("#contentImgsValue").val();
        $("#contentImgs").val(contentImgs);
        var imgs=null;
        if(contentImgs!=null && contentImgs!=''){
            imgs=contentImgs.split(",");
            var fid = 1000001;
            var delFlag=false;
            for(var i=0;i<imgs.length;i++){
                if (!delFlag){
                    $("#contentImgsPreId").empty();
                    delFlag=true;
                }
                var img=$('<div><img id="' + fid + '" width="100px" height="100px" name="contentImgs" value="'+imgs[i]+'" src="'+imgs[i]+'"> <span onclick="$MyWebUpload.removeImg(this.id)" class="cancle glyphicon glyphicon-remove" id="' + fid + '"></span></div><br>');
                fid++;
                $("#contentImgsPreId").append(img);

            }
        }


        //回显复选框
        var teacherName=$("#teachersValue").val();
        var teacherNames=teacherName.split(" ");
        for(var i=0;i<teacherNames.length;i++){
            // $(".teacher[value="+teacherNames[i]+"]").attr("checked",true);
            $(".teacher").each(function(){
                if($(this).attr("value")==teacherNames[i]){
                    $(this).attr("checked",true);
                }
            })
        }

        //上架时间变化
        $("#shelfOnTime").onchange(function () {
            var stime=$("#shelfOnTime").val();
            Feng.success("stime="+stime);
        })

    }

});
