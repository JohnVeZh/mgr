/**
 * 初始化试卷目录详情对话框
 */
var PaperCatalogInfoDlg = {
    paperCatalogInfoData : {},
    ztreeInstance: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        sectionCode: {
            validators: {
                notEmpty: {
                    message: '学段code不能为空'
                }
            }
        },
        // qrCode: {
        //     validators: {
        //         notEmpty: {
        //             message: '二维码不能为空'
        //         }
        //     }
        // },
        parentName: {
            validators: {
                notEmpty: {
                    message: '父类不能为空'
                }
            }
        },
        sort: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                },
                numeric:{
                    message: '内容为数值'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
PaperCatalogInfoDlg.clearData = function() {
    this.paperCatalogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperCatalogInfoDlg.set = function(key, val) {
    this.paperCatalogInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperCatalogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PaperCatalogInfoDlg.close = function() {
    parent.layer.close(window.parent.PaperCatalog.layerIndex);
}

/**
 * 收集数据
 */
PaperCatalogInfoDlg.collectData = function() {
    this.set('id').set('name').set('type').set('sectionCode').set('qrCode')
        .set('parentId').set('sort');
}

/**
 * 提交添加
 */
PaperCatalogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paperCatalog/add", function(data){
        Feng.success("添加成功!");
        window.parent.PaperCatalog.table.refresh();
        PaperCatalogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperCatalogInfoData);
    ajax.start();
}

/**
 * 验证数据是否为空
 */
PaperCatalogInfoDlg.validate = function () {

    if ($('#type').val() == '2' || $('#type').val() == '5'){
        var qrcode = $('#qrCode').val();
        if ( qrcode== undefined || qrcode == ""){
            Feng.error("请填写二维码!");
            return false;
        }
    }

    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}

/**
 * 提交修改
 */
PaperCatalogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paperCatalog/update", function(data){
        Feng.success("修改成功!");
        window.parent.PaperCatalog.table.refresh();
        PaperCatalogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperCatalogInfoData);
    ajax.start();
}

/**
 * 点击父级编号input框时
 */
PaperCatalogInfoDlg.onClickParent = function (e, treeId, treeNode) {
    if (treeNode.level < 3) {
        $("#parentName").attr("value", "顶级");
        $("#parentId").attr("value", "0");
    } else {
        $("#parentName").attr("value", PaperCatalogInfoDlg.ztreeInstance.getSelectedVal());
        $("#parentId").attr("value", treeNode.id);
    }
};

/**
 * 显示父级菜单选择的树
 */
PaperCatalogInfoDlg.showMenuSelectTree = function () {
    Feng.showInputTree("parentName", "pcodeTreeDiv", 15, 34);
};

$(function() {
    Feng.initValidator("Form", PaperCatalogInfoDlg.validateFields);

    var url = "/paper/paperCatalog/selectTreeList.do?type=" + $("#type").val();
    var ztree = new $ZTree("pcodeTree", url);
    ztree.bindOnClick(PaperCatalogInfoDlg.onClickParent);
    ztree.init();
    PaperCatalogInfoDlg.ztreeInstance = ztree;

    //初始化学段
    var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
        $.each(data,function(i,item){
            $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
        });
    }, function (data) {
        Feng.error("获取学段失败!" + data.responseJSON.message + "!");
    });
    ajax.start();

    //初始化下拉框
    if($("#typeValue").val() == undefined){
        $("#type").val(1);
    }else{
        $("#type").val($("#typeValue").val());
    }
    if ($("#sectionCodeValue").val() != undefined) {
        $("#sectionCode").val($("#sectionCodeValue").val());
    }

    $("#type").change(function(){
        url = "/paper/paperCatalog/selectTreeList.do?type=" + $("#type").val();
        var ztree = new $ZTree("pcodeTree", url);
        ztree.bindOnClick(PaperCatalogInfoDlg.onClickParent);
        ztree.init();
        PaperCatalogInfoDlg.ztreeInstance = ztree;
        $("#parentName").attr("value", "");
        $("#parentId").attr("value", "");
    });
});
