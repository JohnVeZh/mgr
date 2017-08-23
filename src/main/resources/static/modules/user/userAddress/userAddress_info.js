/**
 * 初始化收货地址详情对话框
 */
var UserAddressInfoDlg = {
    userAddressInfoData : {}
};

/**
 * 清除数据
 */
UserAddressInfoDlg.clearData = function() {
    this.userAddressInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAddressInfoDlg.set = function(key, val) {
    if(val!=undefined){
        this.userAddressInfoData[key] = val;
    }else{
        this.userAddressInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAddressInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserAddressInfoDlg.close = function() {
    parent.layer.close(window.parent.UserAddress.layerIndex);
}

/**
 * 收集数据
 */
UserAddressInfoDlg.collectData = function() {

    var districtCn = $("#province option:selected").text()+$("#city option:selected").text()+$("#county option:selected").text();
    this.set('id');
    this.set('isDefault',0);
    this.set('districtCn',districtCn);
    this.set('receiver');
    this.set('userId');
    this.set('zipcode');
    this.set('mobile');
    this.set('provinceId',$("#province option:selected").val());
    this.set('cityId',$("#city option:selected").val());
    this.set('countyId',$("#county option:selected").val());
    this.set('detailAddress');
}

/**
 * 提交添加
 */
UserAddressInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userAddress/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserAddress.table.refresh();
        UserAddressInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userAddressInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserAddressInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userAddress/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserAddress.table.refresh();
        UserAddressInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userAddressInfoData);
    ajax.start();
}

$(function() {
    var $province = $("#province");
    var $city = $("#city");
    var $county = $("#county");
    $province.val($("#provinceId").val());
    $province.trigger("change");
    $city.val($("#cityId").val());
    $city.trigger("change");
    $county.val($("#countyId").val());
    $county.trigger("change");
});
