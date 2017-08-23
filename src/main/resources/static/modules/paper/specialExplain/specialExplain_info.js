/**
 * 初始化专项讲解详情对话框
 */
var SpecialExplainInfoDlg = {
    specialExplainInfoData : {},
    paperIndex:0,
    paperTable:null,
    editor:null,
    bookTable:null,
    networkCourseTable:null,
    newsTable:null,
    contentId:null,
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
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
 * 初始化图书表格的列
 */
SpecialExplainInfoDlg.initBookTableColumn = function () {
    return [
        {field: 'selectItem', radio: true,formatter:checkedFormatter},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '图书名称', field: 'name', align: 'center', valign: 'middle'},
        // {title: '原价', field: 'originalPrice', align: 'center', valign: 'middle'},
        // {title: '现价', field: 'presentPrice', align: 'center', valign: 'middle'},
        // {title: '邮费', field: 'postage', align: 'center', valign: 'middle'},
        {title: '类型', field: 'bookTypeName', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createDate', align: 'center', valign: 'middle'},
        {title: '创建人', field: 'createBy', align: 'center', valign: 'middle'}
    ];
};

/**
 * 初始化网课表格的列
 */
SpecialExplainInfoDlg.initNetworkCourseColumn = function () {
    return [
        {field: 'selectItem', radio: true,formatter:checkedFormatter},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        // {title: '封面图片', field: 'coverImg', align: 'center', valign: 'middle',formatter: function(value,row,index){
        //     return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        // }},
        {title: '网课名称', field: 'name', align: 'center', valign: 'middle'},
        {title: '网课级别类型', field: 'sectionType', align: 'center', valign: 'middle'},
        // {title: '现价', field: 'presentPrice', align: 'center', valign: 'middle'},
        // {title: '实际销量', field: 'saleNum', align: 'center', valign: 'middle'},
        {title: '网课形式', field: 'isLive', align: 'center', valign: 'middle', formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label label-success">录播</span>';
                case 1:
                    return '<span class="label label-primary">直播</span>';
                default:
                    break;
            }
        }},
        {title: '网课状态', field: 'status', align: 'center', valign: 'middle', formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label badge-default">待发布</span>';
                case 1:
                    return '<span class="label label-info">发布中</span>';
                case 2:
                    return '<span class="label label-success">预约中</span>';
                case 3:
                    return '<span class="label label-primary">开售中</span>';
                case 4:
                    return '<span class="label label-warning">已停售</span>';
                case 5:
                    return '<span class="label label-danger">已下架</span>';
                default:
                    break;
            }
        }},
        {title: '开始时间', field: 'startTime', align: 'center', valign: 'middle'},
        {title: '结束时间', field: 'endTime', align: 'center', valign: 'middle'}
    ];
};

/**
 * 初始化资讯表格的列
 */
SpecialExplainInfoDlg.initNewsColumn = function () {
    return [
        {field: 'selectItem', radio: true,formatter:checkedFormatter},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title',  align: 'center', valign: 'middle'},
        {title: '副标题', field: 'subtitle', align: 'center', valign: 'middle'},
        {title: '作者', field: 'author',  align: 'center', valign: 'middle'},
        {title: '阅读数', field: 'readNum',  align: 'center', valign: 'middle'},
        {title: '发布', field: 'isShow',  align: 'center', valign: 'middle' , formatter : function (e,column,index) {
            var htmlElement = '';
            if(column.isShow == 1){
                htmlElement += '<span class="label label-primary">已发布</span>&nbsp;&nbsp;';
            }else{
                htmlElement += '<span class="label label-default">草稿箱</span>';
            }
            return htmlElement;
        }}
    ];
};

function checkedFormatter(value, row, index) {
    if ($("#contentId").val() != undefined && row.id == $("#contentId").val()) {
        return {
            checked : true//设置选中
        };
    }
}

/**
 * 清除数据
 */
SpecialExplainInfoDlg.clearData = function() {
    this.specialExplainInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialExplainInfoDlg.set = function(key, val) {
    this.specialExplainInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialExplainInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SpecialExplainInfoDlg.close = function() {
    parent.layer.close(window.parent.SpecialExplain.layerIndex);
}

/**
 * 收集数据
 */
SpecialExplainInfoDlg.collectData = function() {
    this.set('id').set('title').set('sort').set('contentType')
        .set('url').set('remarks').set('type').set('sectionCode');
    //编辑器值获取
    if (this.editor != undefined) {
        var html = this.editor.$txt.html();
        SpecialExplainInfoDlg.specialExplainInfoData["content"] = html;
    }
    SpecialExplainInfoDlg.specialExplainInfoData["contentId"] = SpecialExplainInfoDlg.contentId;
}

/**
 * 验证数据是否为空
 */
SpecialExplainInfoDlg.validate = function () {
    SpecialExplainInfoDlg.contentId = null;
    //根据内容类型验证
    var contentType = $('#contentType').val();
    if (contentType == 1) {
        var html = this.editor.$txt.html();
        if (html == undefined || html == '<p><br></p>') {
            Feng.info("请编辑内容！");
            return false;
        }
    }
    if (contentType == 2) {
        if ($('#url').val() == undefined) {
            Feng.info("请填写外部链接地址！");
            return false;
        }
    }
    if (contentType == 3) {
        var selected = $('#NewsTable').bootstrapTable('getSelections');
        if(selected.length == 0){
            Feng.info("请先选中表格中的某一记录！");
            return false;
        }else {
            SpecialExplainInfoDlg.contentId = selected[0].id;
        }
    }
    if (contentType == 4) {
        var selected = $('#BookTable').bootstrapTable('getSelections');
        if(selected.length == 0){
            Feng.info("请先选中表格中的某一记录！");
            return false;
        }else {
            SpecialExplainInfoDlg.contentId = selected[0].id;
        }
    }
    if (contentType == 5) {
        var selected = $('#NetworkCourseTable').bootstrapTable('getSelections');
        if(selected.length == 0){
            Feng.info("请先选中表格中的某一记录！");
            return false;
        }else {
            SpecialExplainInfoDlg.contentId = selected[0].id;
        }
    }

    $('#Form').data("bootstrapValidator").resetForm();
    $('#Form').bootstrapValidator('validate');
    return $("#Form").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
SpecialExplainInfoDlg.addSubmit = function() {

    this.clearData();

    if (!this.validate()) {
        return;
    }

    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/specialExplain/add", function(data){
        Feng.success("添加成功!");
        window.parent.SpecialExplain.table.refresh();
        SpecialExplainInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialExplainInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SpecialExplainInfoDlg.editSubmit = function() {

    this.clearData();

    if (!this.validate()) {
        return;
    }

    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/specialExplain/update", function(data){
        Feng.success("修改成功!");
        window.parent.SpecialExplain.table.refresh();
        SpecialExplainInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.specialExplainInfoData);
    ajax.start();
}

/**
 * 查询图书列表
 */
SpecialExplainInfoDlg.searchBook = function () {
    var params = {};
    var queryData = {};
    queryData['condition'] = $("#bookName").val();
    params['url'] = Feng.ctxPath + "/goods/book/list";
    params['query'] = queryData;
    SpecialExplainInfoDlg.bookTable.btInstance.bootstrapTable('refresh',params);
};

/**
 * 查询网课列表
 */
SpecialExplainInfoDlg.searchNetworkCourse = function () {
    var params = {};
    var queryData = {};
    queryData['condition'] = $("#networkCourseName").val();
    params['url'] = Feng.ctxPath + "/goods/networkCourse/list";
    params['query'] = queryData;
    SpecialExplainInfoDlg.networkCourseTable.btInstance.bootstrapTable('refresh',params);
};

/**
 * 查询资讯列表
 */
SpecialExplainInfoDlg.searchNews = function () {
    var params = {};
    var queryData = {};
    queryData['condition'] = $("#newsName").val();
    params['url'] = Feng.ctxPath + "/community/news/list";
    params['query'] = queryData;
    SpecialExplainInfoDlg.newsTable.btInstance.bootstrapTable('refresh',params);
};

/**
 * 重置
 */
SpecialExplainInfoDlg.resetSearch = function () {
    var contentType = $("#contentType").val();
    if (contentType == 3) {
        $('#newsName').val('');
        SpecialExplainInfoDlg.searchNews();
    }
    if (contentType == 4) {
        $('#bookName').val('');
        SpecialExplainInfoDlg.searchBook();
    }
    if (contentType == 5) {
        $('#networkCourseName').val('');
        SpecialExplainInfoDlg.searchNetworkCourse();
    }
};

function hideDiv(contentType){
    $('#contentDiv').hide();
    $('#urlDiv').hide();
    $('#newsDiv').hide();
    $('#bookDiv').hide();
    $('#networkCourseDiv').hide();
}

function showDiv(contentType){

    if (contentType == 1) {
        if (SpecialExplainInfoDlg.editor == undefined) {
            var editor = new $wangEditor("content");
            SpecialExplainInfoDlg.editor = editor.init();
        }
        $('#contentDiv').show();
    }
    if (contentType == 2) $('#urlDiv').show();

    var productId = $("#contentId").val() == undefined ? '' : $("#contentId").val();

    if (contentType == 3) {
        if (SpecialExplainInfoDlg.newsTable == undefined) {
            var defaultColunms = SpecialExplainInfoDlg.initNewsColumn();
            var table = new BSTable("NewsTable", "/community/news/list?id=" + productId, defaultColunms);
            SpecialExplainInfoDlg.newsTable = table.init();
        }
        $('#newsDiv').show();
    }
    if (contentType == 4) {
        if (SpecialExplainInfoDlg.bookTable == undefined) {
            var defaultColunms = SpecialExplainInfoDlg.initBookTableColumn();
            var table = new BSTable("BookTable", "/goods/book/list?productId=" + productId, defaultColunms);
            SpecialExplainInfoDlg.bookTable = table.init();
        }
        $('#bookDiv').show();
    }
    if (contentType == 5) {
        if (SpecialExplainInfoDlg.networkCourseTable == undefined) {
            var defaultColunms = SpecialExplainInfoDlg.initNetworkCourseColumn();
            var table = new BSTable("NetworkCourseTable", "/goods/networkCourse/list?productId=" + productId, defaultColunms);
            SpecialExplainInfoDlg.networkCourseTable = table.init();
        }
        $('#networkCourseDiv').show();
    }
}

$(function() {

    Feng.initValidator("Form", SpecialExplainInfoDlg.validateFields);

    // //初始化学段
    // var ajax = new $ax(Feng.ctxPath + "/section/list", function (data) {
    //     $.each(data,function(i,item){
    //         $("#sectionCode").append("<option value='" + item.code + "'>" + item.name + "</option>");
    //     });
    // }, function (data) {
    //     Feng.error("获取学段失败!" + data.responseJSON.message + "!");
    // });
    // ajax.start();

    //初始化下拉框
    if ($("#sectionCodeValue").val() != undefined) {
        $("#sectionCode").val($("#sectionCodeValue").val());
    }
    if ($("#typeValue").val() != undefined) {
        $("#type").val($("#typeValue").val());
    }
    if ($("#contentTypeValue").val() != undefined) {
        $("#contentType").val($("#contentTypeValue").val());
    }

    hideDiv($("#contentType").val());
    showDiv($("#contentType").val());

    //内容类型改变事件
    $("#contentType").change(function(){
        var contentType = $("#contentType").val();
        hideDiv(contentType);
        showDiv(contentType);
    });

});
