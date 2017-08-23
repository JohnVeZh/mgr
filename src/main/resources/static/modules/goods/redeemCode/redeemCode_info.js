/**
 * 初始化网课兑换码详情对话框
 */
var RedeemCodeInfoDlg = {
    editor:{},
    redeemCodeInfoData : {},
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
                    message:'生成数量不能为空'
                },
                regexp:{
                    regexp:/^[0-9]+$/,
                    message:'请输入正整数'
                }
            }
        },
        startTime:{
            validators:{
                notEmpty:{
                    message:'必填'
                }
            }
        },
        endTime:{
            validators:{
                notEmpty:{
                    message:'必填'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
RedeemCodeInfoDlg.clearData = function() {
    this.redeemCodeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RedeemCodeInfoDlg.set = function(key, val) {
    this.redeemCodeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RedeemCodeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RedeemCodeInfoDlg.close = function() {
    parent.layer.close(window.parent.RedeemCode.layerIndex);
}

/**
 * 收集数据
 */
RedeemCodeInfoDlg.collectData = function() {
    // this.set('id');
    this.redeemCodeInfoData = {
        networkCourseId:$("#networkCourseId").val(),
        sort:$("#sort").val(),
        isExport:$("#isExport").val(),
        startTime:$("#startTime").val(),
        endTime:$("#endTime").val()
    };
}

/**
 * 导出Excel
 */
RedeemCodeInfoDlg.openExportExcel = function () {
    var ncId = $("#networkCourseId").val();
    var startDate = $("#startTime").val();
    var endDate = $("#endTime").val();
    var number = $("#sort").val();
    var isExport = $("#isExport").val();

    $("#ncId").val(ncId);
    $("#startDate").val(startDate);
    $("#endDate").val(endDate);
    $("#number").val(number);
    $("#export").val(isExport);
    excelForm.action = Feng.ctxPath + '/goods/redeemCode/add';
    excelForm.submit();
}


RedeemCodeInfoDlg.validate = function () {
    $('#redeemCodeInfoForm').data("bootstrapValidator").resetForm();
    $('#redeemCodeInfoForm').bootstrapValidator('validate');
    return $("#redeemCodeInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 提交添加
 */
RedeemCodeInfoDlg.addSubmit = function() {
    if (!this.validate()) {
        return;
    }
    RedeemCodeInfoDlg.openExportExcel();
}

/**
 * 修改可用状态
 */
RedeemCodeInfoDlg.editIsEnable = function() {

    this.clearData();
    this.redeemCodeInfoData={
        id:$("#id").val(),
        isEnable:$("#isEnable").val()
    };


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/redeemCode/update", function(data){
        Feng.success("修改成功!");
        window.parent.RedeemCode.table.refresh();
        RedeemCodeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.redeemCodeInfoData);
    ajax.start();
}

/**
 * select回显
 */
$(function() {
    Feng.initValidator("redeemCodeInfoForm", RedeemCodeInfoDlg.validateFields);

    var ajax = new $ax(Feng.ctxPath + "/goods/redeemCode/select", function(data){
        $.each(data,function (i) {
            $("#networkCourseId").append("<option value=" + this.id + ">" + this.name + "</option>>");
        })
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    $("#isEnable").val($("#isEnableValue").val());




});
