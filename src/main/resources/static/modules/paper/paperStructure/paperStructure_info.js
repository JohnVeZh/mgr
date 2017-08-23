/**
 * 初始化试卷树形结构详情对话框
 */
var PaperStructureInfoDlg = {
    paperStructureInfoData: {},
    tree: null,
    editor: null,
    layerIndex: -1,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        contentType: {
            validators: {
                notEmpty: {
                    message: '题目类型不能为空'
                }
            }
        },
        alias: {
            validators: {
                notEmpty: {
                    message: '别名不能为空'
                }
            }
        },
        sort: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                }, numeric: {
                    message: '内容为数值'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
PaperStructureInfoDlg.clearData = function () {
    this.paperStructureInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperStructureInfoDlg.set = function (key, val) {
    this.paperStructureInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperStructureInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PaperStructureInfoDlg.close = function () {
    parent.layer.close(window.parent.PaperStructureList.layerIndex);
}

/**
 * 验证数据是否为空
 */
PaperStructureInfoDlg.validate = function () {
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}

/**
 * 收集数据
 */
PaperStructureInfoDlg.collectData = function () {
    this.set('id').set('paperId').set('name').set('remark').set('contentType').set('alias').set('sort').set('parentId');
    if (this.editor != undefined) {
        var html = this.editor.$txt.html();
        PaperStructureInfoDlg.paperStructureInfoData["content"] = html;
    }
}

/**
 * 提交添加
 */
PaperStructureInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    console.log(this.paperStructureInfoData);

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paperStructure/add", function (data) {
        Feng.success("添加成功!");
        window.parent.PaperStructureList.table.refresh();
        PaperStructureInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperStructureInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PaperStructureInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paperStructure/update", function (data) {
        Feng.success("修改成功!");
        window.parent.PaperStructureList.table.refresh();
        PaperStructureInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperStructureInfoData);
    ajax.start();
}

PaperStructureInfoDlg.onClickParent = function (e, treeId, treeNode) {
    $("#parentName").attr("value", PaperStructureInfoDlg.tree.getSelectedVal());
    $("#parentId").attr("value", treeNode.id);
};


PaperStructureInfoDlg.showMenuSelectTree = function () {
    Feng.showInputTree("parentName", "pcodeTreeDiv", 15, 34);
}


$(function () {

    Feng.initValidator("Form", PaperStructureInfoDlg.validateFields);

    var ztree = new $ZTree("pcodeTree", "/paper/paperStructure/getParentStructure?paperId=" + $("#paperId").val());
    ztree.bindOnClick(PaperStructureInfoDlg.onClickParent);
    ztree.init();

    PaperStructureInfoDlg.tree = ztree;

    var editor = new $wangEditor("content");
    PaperStructureInfoDlg.editor = editor.init();
});
