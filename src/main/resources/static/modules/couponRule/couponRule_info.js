/**
 * 初始化优惠劵规则详情对话框
 */
var CouponRuleInfoDlg = {
    couponRuleInfoData : {},
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
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
        minMoney: {
            validators: {
                notEmpty: {
                    message: '最低使用金额不能为空'
                }
            }
        },
        discountMoney: {
            validators: {
                notEmpty: {
                    message: '折扣金额不能为空'
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
CouponRuleInfoDlg.clearData = function() {
    this.couponRuleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponRuleInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.couponRuleInfoData[key] = val;
    }else{
        this.couponRuleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponRuleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CouponRuleInfoDlg.close = function() {
    parent.layer.close(window.parent.CouponRule.layerIndex);
}

/**
 * 收集数据
 */
CouponRuleInfoDlg.collectData = function() {
    this.set('id');
    this.set('title');
    this.set('effectDate');
    this.set('invalidDate');
    this.set('minMoney');
    this.set('discountMoney');
    this.set('status');
};

CouponRuleInfoDlg.validate = function () {
    $('#couponRuleForm').data("bootstrapValidator").resetForm();
    $('#couponRuleForm').bootstrapValidator('validate');
    return $("#couponRuleForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
CouponRuleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponRule/add", function(data){
        Feng.success("添加成功!");
        window.parent.CouponRule.table.refresh();
        CouponRuleInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    },'json');
    ajax.setData(this.couponRuleInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CouponRuleInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponRule/update", function(data){
        Feng.success("修改成功!");
        window.parent.CouponRule.table.refresh();
        CouponRuleInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    },"json");
    ajax.setData(this.couponRuleInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("couponRuleForm", CouponRuleInfoDlg.validateFields);
});
