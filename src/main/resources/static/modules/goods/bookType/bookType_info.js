/**
 * 初始化图书类型详情对话框
 */
var BookTypeInfoDlg = {
    bookTypeInfoData : {},
    zTreeInstance : null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '图书类型名称不能为空'
                }
            }
        },
        parentName: {
            validators: {
                notEmpty: {
                    message: '父级类型不能为空'
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
BookTypeInfoDlg.clearData = function() {
    this.bookTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookTypeInfoDlg.set = function(key, val) {
    this.bookTypeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BookTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.BookType.layerIndex);
}

/**
 * 点击类型ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
BookTypeInfoDlg.onClickBookType = function(e, treeId, treeNode) {
    $("#parentName").attr("value", BookTypeInfoDlg.zTreeInstance.getSelectedVal());
    $("#parentId").attr("value", treeNode.id);
}



/**
 * 显示类型选择的树
 *
 * @returns
 */
BookTypeInfoDlg.showBookTypeSelectTree = function() {
    var parentName = $("#parentName");
    var parentNameOffset = $("#parentName").offset();
    $("#parentBookTypeMenu").css({
        left : parentNameOffset.left + "px",
        top : parentNameOffset.top + parentName.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}


/**
 * 隐藏类型选择的树
 */
BookTypeInfoDlg.hideBookTypeSelectTree = function() {
    $("#parentBookTypeMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}




/**
 * 收集数据
 */
BookTypeInfoDlg.collectData = function() {
    this.set('id').set("name").set("sort").set("remarks").set("parentId");
}


/**
 * 表单验证
 */
BookTypeInfoDlg.validate = function () {
    $('#bookTypeInfoForm').data("bootstrapValidator").resetForm();
    $('#bookTypeInfoForm').bootstrapValidator('validate');
    return $("#bookTypeInfoForm").data('bootstrapValidator').isValid();
}




/**
 * 提交添加
 */
BookTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/bookType/add", function(data){
        Feng.success("添加成功!");
        window.parent.BookType.table.refresh();
        BookTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BookTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/bookType/update", function(data){
        Feng.success("修改成功!");
        window.parent.BookType.table.refresh();
        BookTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookTypeInfoData);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id === "menuBtn" || event.target.id === "parentBookTypeMenu" || $(
            event.target).parents("#parentBookTypeMenu").length > 0)) {
        BookTypeInfoDlg.hideBookTypeSelectTree();
    }
}


$(function() {
    Feng.initValidator("bookTypeInfoForm", BookTypeInfoDlg.validateFields);

    var ztree = new $ZTree("parentBookTypeMenuTree", "/goods/bookType/tree");
    ztree.bindOnClick(BookTypeInfoDlg.onClickBookType);
    ztree.init();
    BookTypeInfoDlg.zTreeInstance = ztree;
});
