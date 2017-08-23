/**
 * 初始化试卷组详情对话框
 */
var PaperGroupInfoDlg = {
    paperGroupInfoData : {},
    tree:null,
    table:null,
    selectionIds:[],  //保存选中ids
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        catalogName: {
            validators: {
                notEmpty: {
                    message: '目录不能为空'
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
        },
        img: {
            validators: {
                notEmpty: {
                    message: '图片不能为空'
                }
            }
        }
    }
};

/**
 * 初始化表格的列
 */
PaperGroupInfoDlg.initPaperColumn = function () {
    return [
        {field: 'selectItem', checkbox: true,formatter:checkedFormatter},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '试卷图片', field: 'img', visible: true, align: 'center', valign: 'middle',width:'20%',formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '内容类型', field: 'contentTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '题目总数', field: 'questionNum', visible: true, align: 'center', valign: 'middle'}
    ];
};

function checkedFormatter(value, row, index) {
    if (PaperGroupInfoDlg.selectionIds.length > 0) {
        return {
            checked : $.inArray(row.id, PaperGroupInfoDlg.selectionIds) != -1
        };
    }
}

/**
 * 清除数据
 */
PaperGroupInfoDlg.clearData = function() {
    this.paperGroupInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperGroupInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.paperGroupInfoData[key] = val;
    }else{
        this.paperGroupInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperGroupInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PaperGroupInfoDlg.close = function() {
    parent.layer.close(window.parent.PaperGroup.layerIndex);
}

/**
 * 收集数据
 */
PaperGroupInfoDlg.collectData = function() {
    this.set('id').set('name').set('img').set('sort').set('catalogId').set('paperIds',PaperGroupInfoDlg.selectionIds.join(','));
}

/**
 * 验证数据是否为空
 */
PaperGroupInfoDlg.validate = function () {
    if (PaperGroupInfoDlg.selectionIds.length == 0) {
        Feng.error("请选择试卷!");
        return false;
    }
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
PaperGroupInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paperGroup/add", function(data){
        Feng.success("添加成功!");
        window.parent.PaperGroup.table.refresh();
        PaperGroupInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperGroupInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PaperGroupInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paperGroup/update", function(data){
        Feng.success("修改成功!");
        window.parent.PaperGroup.table.refresh();
        PaperGroupInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperGroupInfoData);
    ajax.start();
}

/**
 * 点击父级编号input框时
 */
PaperGroupInfoDlg.onClickParent = function (e, treeId, treeNode) {
    if (treeNode.level > 2) {
        $("#catalogName").attr("value", PaperGroupInfoDlg.tree.getSelectedVal());
        $("#catalogId").attr("value", treeNode.id);
    }
    //刷新列表
    // PaperGroupInfoDlg.resetTable();
};

/**
 * 显示父级菜单选择的树
 */
PaperGroupInfoDlg.showMenuSelectTree = function () {
    Feng.showInputTree("catalogName", "pcodeTreeDiv", 15, 34);
};

/**
 * 选中事件操作数组
 */
PaperGroupInfoDlg.union = function(array,ids){
    $.each(ids, function (i, id) {
        if($.inArray(id,array)==-1){
            array[array.length] = id;
        }
    });
    return array;
};

/**
 * 取消选中事件操作数组
 */
PaperGroupInfoDlg.difference = function(array,ids){
    $.each(ids, function (i, id) {
        var index = $.inArray(id,array);
        if(index!=-1){
            array.splice(index, 1);
        }
    });
    return array;
};

/**
 * 表格处理
 */
PaperGroupInfoDlg.tableResponseHandler = function (res) {
    $.each(res.rows, function (i, row) {
        row.selectItem = $.inArray(row.id, PaperGroupInfoDlg.selectionIds) != -1;  //判断当前行的数据id是否存在与选中的数组，存在则将多选框状态变为true
    });
    return res;
}

/**
 * 查询列表
 */
PaperGroupInfoDlg.searchPaper = function () {
    var params = {};
    var queryData = {};
    queryData['queryName'] = $("#paperName").val();
    // queryData['catalogId'] = $('#catalogId').val();
    params['url'] = Feng.ctxPath + "/paper/list";
    params['query'] = queryData;
    PaperGroupInfoDlg.table.btInstance.bootstrapTable('refresh',params);
};

/**
 * 重置
 */
PaperGroupInfoDlg.resetSearch = function () {
    $('#paperName').val('');
    PaperGroupInfoDlg.searchPaper();
};

/**
 * 刷新试卷列表
 */
PaperGroupInfoDlg.resetTable = function (catalogId) {
    PaperGroupInfoDlg.selectionIds = [];
    PaperGroupInfoDlg.searchPaper();
}

$(function() {
    Feng.initValidator("Form", PaperGroupInfoDlg.validateFields);
    var url = "/paper/paperCatalog/selectTreeList.do?type=2,5";
    var ztree = new $ZTree("pcodeTree", url);
    ztree.bindOnClick(PaperGroupInfoDlg.onClickParent);
    ztree.init();
    PaperGroupInfoDlg.tree = ztree;

    // 初始化图片上传
    var avatarUp = new $WebUpload("img");
    avatarUp.init();
    if ($('#imgValue').val() != undefined) {
        $('img').attr("src",$('#imgValue').val());
    }

    var paperIds = $('#paperIds').val();
    if (paperIds != undefined) {
        PaperGroupInfoDlg.selectionIds = paperIds.split(',');
    }

    //初始化列表
    var defaultColunms = PaperGroupInfoDlg.initPaperColumn();
    // var url = "/paper/list?catalogId=" + $('#catalogId').val() + "&paperIds=" + PaperGroupInfoDlg.selectionIds.join(',');
    var url = "/paper/list?catalogType=25&paperIds=" + PaperGroupInfoDlg.selectionIds.join(',');
    var table = new BSTable("PaperTable", url , defaultColunms);
    table.responseHandler = PaperGroupInfoDlg.tableResponseHandler;
    PaperGroupInfoDlg.table = table.init();

    var _ = {"union":PaperGroupInfoDlg.union,"difference":PaperGroupInfoDlg.difference};
    //绑定选中事件、取消事件、全部选中、全部取消
    PaperGroupInfoDlg.table.btInstance.on('check.bs.table check-all.bs.table uncheck.bs.table uncheck-all.bs.table', function (e, rows) {
        var ids = $.map(!$.isArray(rows) ? [rows] : rows, function (row) {
            return row.id;
        });
        func = $.inArray(e.type, ['check', 'check-all']) > -1 ? 'union' : 'difference';
        PaperGroupInfoDlg.selectionIds = _[func](PaperGroupInfoDlg.selectionIds, ids);
    });

});
