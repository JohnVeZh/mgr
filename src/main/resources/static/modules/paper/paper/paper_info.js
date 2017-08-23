/**
 * 初始化试卷详情对话框
 */
var PaperInfoDlg = {
    paperInfoData : {},
    tree:null,
    GroupTable:null,
    selectionIds:[],
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
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
        }
    }
};

/**
 * 初始化表格的列
 */
PaperInfoDlg.initGroupColumn = function () {
    return [
        {field: 'selectItem', checkbox: true,formatter:checkedFormatter},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '组图片', field: 'img', visible: true, align: 'center', valign: 'middle',width:'20%',formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '组名称', field: 'name', visible: true, align: 'center', valign: 'middle'}
    ];
};

function checkedFormatter(value, row, index) {
    if (PaperInfoDlg.selectionIds.length > 0) {
        return {
            checked : $.inArray(row.id, PaperInfoDlg.selectionIds) != -1
        };
    }
}

/**
 * 清除数据
 */
PaperInfoDlg.clearData = function() {
    this.paperInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperInfoDlg.set = function(key, val) {
    this.paperInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PaperInfoDlg.close = function() {
    parent.layer.close(window.parent.Paper.layerIndex);
}

/**
 * 收集数据
 */
PaperInfoDlg.collectData = function() {
    this.set('id').set('catalogId').set('name').set('code').set('qrCode')
        .set('contentType').set('sort').set('img');

    var type = $("#type").val();
    if (type == 2 || type == 5) {//字幕听力、扫码字幕听力
        this.paperInfoData['groupIds'] = PaperInfoDlg.selectionIds.join(',');
    }
}

/**
 * 验证数据是否为空
 */
PaperInfoDlg.validate = function () {
    if (PaperInfoDlg.selectionIds.length == 0) {
        Feng.error("请选择试卷组!");
        return false;
    }
    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
PaperInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/add", function(data){
        Feng.success("添加成功!");
        window.parent.Paper.table.refresh();
        PaperInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PaperInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/update", function(data){
        Feng.success("修改成功!");
        window.parent.Paper.table.refresh();
        PaperInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperInfoData);
    ajax.start();
}

/**
 * 点击父级编号input框时
 */
PaperInfoDlg.onClickParent = function (e, treeId, treeNode) {
    if (treeNode.level > 2) {
        $("#catalogName").attr("value", PaperInfoDlg.tree.getSelectedVal());
        $("#catalogId").attr("value", treeNode.id);
    }
};

/**
 * 显示父级菜单选择的树
 */
PaperInfoDlg.showMenuSelectTree = function () {
    Feng.showInputTree("catalogName", "pcodeTreeDiv", 15, 34);
};

/**
 * 选中事件操作数组
 */
PaperInfoDlg.union = function(array,ids){
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
PaperInfoDlg.difference = function(array,ids){
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
PaperInfoDlg.tableResponseHandler = function (res) {
    $.each(res.rows, function (i, row) {
        row.selectItem = $.inArray(row.id, PaperInfoDlg.selectionIds) != -1;  //判断当前行的数据id是否存在与选中的数组，存在则将多选框状态变为true
    });
    return res;
}

/**
 * 查询列表
 */
PaperInfoDlg.searchGroup = function () {
    var params = {};
    var queryData = {};
    queryData['condition'] = $("#groupName").val();
    queryData['catalogType'] = $("#type").val();
    params['url'] = Feng.ctxPath + "/paper/paperGroup/list";
    params['query'] = queryData;
    PaperInfoDlg.GroupTable.btInstance.bootstrapTable('refresh',params);
};

/**
 * 重置
 */
PaperInfoDlg.resetGroupSearch = function () {
    $('#groupName').val('');
    PaperInfoDlg.selectionIds = [];
    PaperInfoDlg.searchGroup();
};

function showDiv() {
    var type = $("#type").val();

    if (type == 1) {//全真考场
        $("#catalogName").attr("disabled",false);
        $("#contentType").val('0').attr("disabled",true);
        $("#groupDiv").hide();
        initCatalogTree(type);
    } else if (type == 2 || type == 5) {//字幕听力、扫码字幕听力
        $("#catalogName").attr("disabled",true);
        if (PaperInfoDlg.GroupTable == undefined) {
            var defaultColunms = PaperInfoDlg.initGroupColumn();
            var groupIds = PaperInfoDlg.selectionIds.length == 0 ? "" : PaperInfoDlg.selectionIds.join(',');
            var url = "/paper/paperGroup/list?groupIds=" + groupIds + "&catalogType=" + type;
            if ($('#id').val() != undefined && $('#id').val() != "")
                url = "/paper/paperGroup/list?groupIds=" + groupIds;
            var table = new BSTable("GroupTable", url, defaultColunms);
            table.responseHandler = PaperInfoDlg.tableResponseHandler;
            PaperInfoDlg.GroupTable = table.init();
            var _ = {"union":PaperInfoDlg.union,"difference":PaperInfoDlg.difference};
            //绑定选中事件、取消事件、全部选中、全部取消
            PaperInfoDlg.GroupTable.btInstance.on('check.bs.table check-all.bs.table uncheck.bs.table uncheck-all.bs.table', function (e, rows) {
                var ids = $.map(!$.isArray(rows) ? [rows] : rows, function (row) {
                    return row.id;
                });
                func = $.inArray(e.type, ['check', 'check-all']) > -1 ? 'union' : 'difference';
                PaperInfoDlg.selectionIds = _[func](PaperInfoDlg.selectionIds, ids);
            });
        } else {
            PaperInfoDlg.selectionIds = [];
            PaperInfoDlg.resetGroupSearch();
        }
        $("#groupDiv").show();
        $("#contentType").val('1').attr("disabled",true);
    } else if (type == 3) {//简系列
        $("#catalogName").attr("disabled",false);
        $("#contentType").val('1').attr("disabled",true);
        $("#groupDiv").hide();
        initCatalogTree(type);
    } else if (type == 4) {//专项练习
        $("#catalogName").attr("disabled",false);
        $("#contentType").val('1').attr("disabled",false);
        $("#groupDiv").hide();
        initCatalogTree(type);
    }
}

/**
 * 初始化目录
 * @param type
 */
function initCatalogTree(type) {
    var url = "/paper/paperCatalog/selectTreeList.do?type=" + type;
    var ztree = new $ZTree("pcodeTree", url);
    ztree.bindOnClick(PaperInfoDlg.onClickParent);
    ztree.init();
    PaperInfoDlg.tree = ztree;
}

$(function() {

    Feng.initValidator("Form", PaperInfoDlg.validateFields);

    if ($("#contentTypeValue").val() != undefined) {
        $("#contentType").val($("#contentTypeValue").val());
    }

    // 初始化图片上传
    var avatarUp = new $WebUpload("img");
    avatarUp.init();

    if ($('#imgValue').val() != undefined) {
        $('img').attr("src",$('#imgValue').val());
    }
    //初始化下拉框
    if($("#typeValue").val() == undefined){
        $("#type").val(1);
    }else{
        $("#type").val($("#typeValue").val());
    }
    var groupIds = $('#groupIds').val();
    if (groupIds != undefined) {
        PaperInfoDlg.selectionIds = groupIds.split(',');
    }

    //根据类型显示样式
    showDiv();

    $("#type").change(function(){
        $("#catalogName").attr("value", "");
        $("#catalogId").attr("value", "");
        showDiv();
    });

    $("#contentType").change(function(){
        if ($("#type").val() == 4 && $(this).val() == 0) {
            $("#contentType").val(1);
            Feng.error("专项练习试卷,内容类型不可选择不区分!");
        }
    });

});
