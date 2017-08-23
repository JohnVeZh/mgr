/**
 * 初始化优惠码详情对话框
 */
var CouponCodeInfoDlg = {
    couponCodeInfoData : {},
    validateFields: {
        code:{
            validators: {
                notEmpty: {
                    message: '优惠码不能为空'
                }
            }
        },
        codeNum: {
            validators: {
                notEmpty: {
                    message: '优惠码数量不能为空'
                }
            }
        },
        usedNum: {
            validators: {
                notEmpty: {
                    message: '已兑换次数不能为空'
                }
            }
        },
        maxNum: {
            validators: {
                notEmpty: {
                    message: '最大可兑换次数不能为空'
                }
            }
        },
        effectDate: {
            validators: {
                notEmpty: {
                    message: '生效时间不能为空'
                }
            }
        },
        invalidDate: {
            validators: {
                notEmpty: {
                    message: '失效时间不能为空'
                }
            }
        },
        status: {
            validators: {
                notEmpty: {
                    message: '规则状态不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
CouponCodeInfoDlg.clearData = function() {
    this.couponCodeInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponCodeInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.couponCodeInfoData[key] = val;
    }else{
        this.couponCodeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponCodeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CouponCodeInfoDlg.close = function() {
    parent.layer.close(window.parent.CouponCode.layerIndex);
}

/**
 * 收集数据
 */
CouponCodeInfoDlg.collectData = function() {
    this.set('id');
    this.set('code');
    this.set('usedNum',0);
    this.set('maxNum');
    this.set('effectDate');
    this.set('invalidDate');
    this.set('status');

}
CouponCodeInfoDlg.validate = function () {
    $('#couponCodeForm').data("bootstrapValidator").resetForm();
    $('#couponCodeForm').bootstrapValidator('validate');
    return $("#couponCodeForm").data('bootstrapValidator').isValid();
};
/**
 * 提交添加
 */
CouponCodeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponCode/add", function(data){
        Feng.success("添加成功!");
        window.parent.CouponCode.table.refresh();
        CouponCodeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.couponCodeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CouponCodeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponCode/update", function(data){
        Feng.success("修改成功!");
        window.parent.CouponCode.table.refresh();
        CouponCodeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    },'json');
    ajax.setData(this.couponCodeInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("couponCodeForm", CouponCodeInfoDlg.validateFields);
});
