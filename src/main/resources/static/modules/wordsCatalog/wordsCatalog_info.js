/**
 * 初始化词汇栏目管理详情对话框
 */
var WordsCatalogInfoDlg = {
    wordsCatalogInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '栏目名称不能为空'
                }
            }
        },
        level: {
            validators: {
                notEmpty: {
                    message: '栏目层级不能为空'
                }
            }
        },
        parentCatalogId: {
            validators: {
                notEmpty: {
                    message: '父级目录不能为空'
                }
            }
        },
        isLeaf: {
            validators: {
                notEmpty: {
                    message: '叶子节点不能为空'
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: '栏目类型不能为空'
                }
            }
        },
        sectionCode: {
            validators: {
                notEmpty: {
                    message: '栏目学段不能为空'
                }
            }
        },
        iconUrl: {
            validators: {
                notEmpty: {
                    message: '栏目icon路径地址不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
WordsCatalogInfoDlg.clearData = function() {
    this.wordsCatalogInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WordsCatalogInfoDlg.set = function(key, value) {
     if(value!=undefined){
        this.wordsCatalogInfoData[key] = value;
    }else{
        this.wordsCatalogInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WordsCatalogInfoDlg.get = function(key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
WordsCatalogInfoDlg.close = function() {
    parent.layer.close(window.parent.WordsCatalog.layerIndex);
};

/**
 * 收集数据
 */
WordsCatalogInfoDlg.collectData = function() {
    this.set('id');
    this.set('name');
    this.set('code');
    this.set('level');
    this.set('isLeaf');
    this.set('type');
    this.set('sectionCode');
    this.set('parentId',$("#parentCatalogId").val());
    this.set('iconUrl');
};

WordsCatalogInfoDlg.validate = function () {
    $('#wordsCatalogForm').data("bootstrapValidator").resetForm();
    $('#wordsCatalogForm').bootstrapValidator('validate');
    return $("#wordsCatalogForm").data('bootstrapValidator').isValid();
};

WordsCatalogInfoDlg.showWordCatalogLayer = function() {
    var selectWordCatalog = layer.open({
        type: 2,
        title: '添加词汇管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wordsCatalog?isSelect=true'
    });
    this.selectWordCatalog = selectWordCatalog;
};
/**
 * 提交添加
 */
WordsCatalogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wordsCatalog/add", function(data){
        Feng.success("添加成功!");
        window.parent.WordsCatalog.table.refresh();
        WordsCatalogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wordsCatalogInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
WordsCatalogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wordsCatalog/update", function(data){
        Feng.success("修改成功!");
        window.parent.WordsCatalog.table.refresh();
        WordsCatalogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wordsCatalogInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("wordsCatalogForm", WordsCatalogInfoDlg.validateFields);
});
