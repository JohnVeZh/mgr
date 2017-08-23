/**
 * 初始化课程目录详情对话框
 */
var CatalogInfoDlg = {
    catalogInfoData : {},
    validateFields:{
        name: {
            validators: {
                notEmpty: {
                    message: '目录名称不能为空'
                }
            }
        },
        sort: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '请输入正整数'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
CatalogInfoDlg.clearData = function() {
    this.catalogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CatalogInfoDlg.set = function(key, val) {
    this.catalogInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CatalogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CatalogInfoDlg.close = function() {
    parent.layer.close(window.parent.Catalog.layerIndex);
}

/**
 * 收集数据
 */
CatalogInfoDlg.collectData = function() {
    this.set('id').set("sort").set("name").set("remarks").set("networkCourseId");
}

//验证
CatalogInfoDlg.validate = function () {
    $('#catalogInfoForm').data("bootstrapValidator").resetForm();
    $('#catalogInfoForm').bootstrapValidator('validate');
    return $("#catalogInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 提交添加
 */
CatalogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/catalog/add", function(data){
        Feng.success("添加成功!");
        window.parent.Catalog.table.refresh();
        CatalogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.catalogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CatalogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/catalog/update", function(data){
        Feng.success("修改成功!");
        window.parent.Catalog.table.refresh();
        CatalogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.catalogInfoData);
    ajax.start();
}

$(function() {
    //验证初始化
    Feng.initValidator("catalogInfoForm", CatalogInfoDlg.validateFields);

});
