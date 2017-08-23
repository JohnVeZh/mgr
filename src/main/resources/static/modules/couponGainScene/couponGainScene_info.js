/**
 * 初始化优惠劵业务场景详情对话框
 */
var CouponGainSceneInfoDlg = {
    selectLayerIndex:null,
    validateFields: {
        type: {
            validators: {
                notEmpty: {
                    message: '场景类型不能为空'
                }
            }
        },
        contentName: {
            validators: {
                notEmpty: {
                    message: '场景名称不能为空'
                }
            }
        },
        productName: {
            validators: {
                notEmpty: {
                    message: '商品名称不能为空'
                }
            }
        }
    },
    couponGainSceneInfoData : {}
};

/**
 * 清除数据
 */
CouponGainSceneInfoDlg.clearData = function() {
    this.couponGainSceneInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponGainSceneInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.couponGainSceneInfoData[key] = val;
    }else{
        this.couponGainSceneInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponGainSceneInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CouponGainSceneInfoDlg.close = function() {
    parent.layer.close(window.parent.CouponGainScene.layerIndex);
}

/**
 * 收集数据
 */
CouponGainSceneInfoDlg.collectData = function() {
    this.set('id');
    this.set('ruleId');
    this.set('type');
    this.set('contentId');
    this.set('productId');
};

/**
 * 提交添加
 */
CouponGainSceneInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectAddData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponGainScene/add", function(data){
        Feng.success("添加成功!");
        window.parent.CouponGainScene.table.refresh();
        CouponGainSceneInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    },'json');
    ajax.setData(this.couponGainSceneInfoData);
    ajax.start();
};
CouponGainSceneInfoDlg.validate = function () {
    $('#couponGainSceneForm').data("bootstrapValidator").resetForm();
    $('#couponGainSceneForm').bootstrapValidator('validate');
    return $("#couponGainSceneForm").data('bootstrapValidator').isValid();
};
/**
 * 提交修改
 */
CouponGainSceneInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponGainScene/update", function(data){
        Feng.success("修改成功!");
        window.parent.CouponGainScene.table.refresh();
        CouponGainSceneInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.couponGainSceneInfoData);
    ajax.start();
};
CouponGainSceneInfoDlg.changeType = function () {
    var type = $("#type").val();
    $("#contentId").val('');
    $("#contentName").val('');
};

CouponGainSceneInfoDlg.selectProductLayer = function () {
    var openLayerUrl = "/couponGainScene/product/view?targetType=2";
    var selectLayer = layer.open({
        type: 2,
        title: '选择数据',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + openLayerUrl
    });
    this.selectLayerIndex = selectLayer;
    layer.full(selectLayer);
};

    CouponGainSceneInfoDlg.selectLayer = function (type) {
    var openLayerUrl = "";
    switch (type){
        case "1":{
            openLayerUrl = "/couponGainScene/product/view?targetType=1&isSelect=true";
            break;}
        case "2":{
            openLayerUrl ="/couponCode?isSelect=true";
            break;}
        case "3":{
            openLayerUrl = "/community/activity?isSelect=true";
            break;}
        case "4":{
            openLayerUrl = "/banner?isSelect=true";
            break;}
    }
    var selectLayer = layer.open({
        type: 2,
        title: '选择数据',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + openLayerUrl
    });
    this.selectLayerIndex = selectLayer;
    layer.full(selectLayer);
};
CouponGainSceneInfoDlg.selectContent = function () {
    var type = $("#type").val();
    CouponGainSceneInfoDlg.selectLayer(type);
};

$(function() {
    Feng.initValidator("couponGainSceneForm", CouponGainSceneInfoDlg.validateFields);
});
