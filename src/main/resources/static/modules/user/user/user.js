/**
 * 用户管理初始化
 */
var User = {
    id: "UserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
User.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '头像', field: 'headerImg', visible: true, align: 'center', valign: 'middle',formatter:imgFormatter},
        {title: '昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
        {title: '注册时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
        {title: '注册渠道', field: 'regType', visible: true, align: 'center', valign: 'middle',formatter:regFormatter},
        {title: '状态', field: 'userStatus', visible: true, align: 'center', valign: 'middle',formatter:statusFormatter},
        {title: '真实姓名', field: 'realName', visible: false, align: 'center', valign: 'middle'},
        {title: '生日', field: 'birthday', visible: false, align: 'center', valign: 'middle'},
        {title: '性别', field: 'sex', visible: false, align: 'center', valign: 'middle',formatter:sexFormatter},
        {title: '学段', field: 'sectionCode', visible: false, align: 'center', valign: 'middle',formatter:sectionFormatter},
        {title: '入学年份', field: 'enrollYear', visible: false, align: 'center', valign: 'middle'},
        {title: '最后一次登录时间', field: 'updateDate', visible: false, align: 'center', valign: 'middle'},
        {title: '操作', field: 'caozuo', visible: true, align: 'center', valign: 'middle',formatter:editFormatter}
    ];
};
/**
 * 检查是否选中
 */
User.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        User.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户
 */
User.openAddUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/user/user_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户详情
 */
User.openUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户详情',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/user/user_update/' + User.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
User.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/user/delete", function (data) {
            Feng.success("删除成功!");
            User.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户列表
 */
User.search = function () {
    var queryData = {};
    queryData['nickname'] = $("#nickname").val();
    queryData['phone'] = $("#phone").val();
    queryData['regType'] = $("#regType").val();
    User.table.refresh({query: queryData});
};


/**
 * 打开收货地址页面
 */
User.openUserAddress = function (id) {
        var index = layer.open({
            type: 2,
            title: '用户收货地址',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userAddress?userId='+id
        });
        this.layerIndex = index;
        layer.full(index);
};

/**
 * 打开生词本
 */
User.openNewWords = function (id) {
    var index = layer.open({
        type: 2,
        title: '用户生词本',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userAddress?userId='+id
    });
    this.layerIndex = index;
};
/**
 *
 * @param id
 */
User.updateStatus = function (id,userStatus) {
    $.ajax({
        type: "post",
        url: "/user/updateStatus",
        data: {id:id, userStatus:userStatus},
        dataType: "json",
        success: function(data){
            User.table.refresh();
        },
        error:function (data) {
            layer.msg("操作失败！请稍后再试！");
        }
    });
}





$(function () {
    var defaultColunms = User.initColumn();
    var table = new BSTable(User.id, "/user/list", defaultColunms);
    table.setPaginationType("server");//client
    User.table = table.init();
});





//数据格式化
function imgFormatter(value,row,index) {
    if(value != ""){
        return "<img src='"+value+"' width='50px' height='50px'>"
    }
}
function statusFormatter(value,row,index) {
    var status = "";
    switch(value)
    {
        case 0: status = '<font color="#2E8B57" style="font-weight:bold;">正常</font>'; break;
        case 1: status = '<font color="#A9A9A9" style="font-weight:bold;">禁用</font>'; break;
        default: status = "";
    }
    return status;
}
function sexFormatter(value,row,index) {
    var sex = "";
    switch(value)
    {
        case 0: sex = "保密"; break;
        case 1: sex = "男"; break;
        case 2: sex = "女"; break;
        default: sex = "";
    }
    return sex;
}
function sectionFormatter(value,row,index) {
    var section = "";
    switch(value)
    {
        case 1: section = "CET4"; break;
        case 2: section = "CET6"; break;
        case 3: section = "考研英语"; break;
        case 4: section = "英语专业"; break;
        case 5: section = "高中英语"; break;
        case 6: section = "初中英语"; break;
        case 7: section = "小学英语"; break;
        default: section = "";
    }
    return section;
}
function regFormatter(value,row,index) {
    var reg = "";
    switch(value)
    {
        case 0: reg = '<font color="#000000" style="font-weight:bold;">老用户</font>'; break;
        case 1: reg = '<font color="#006400" style="font-weight:bold;">Android</font>'; break;
        case 2: reg = '<font color="#FF0000" style="font-weight:bold;">IOS</font>'; break;
        case 3: reg = '<font color="#006699" style="font-weight:bold;">M站</font>'; break;
        case 4: reg = '<font color="#0000FF" style="font-weight:bold;">PC站</font>'; break;
        case 5: reg = '<font color="#FF7F50" style="font-weight:bold;">知米</font>'; break;
        case 6: reg = '<font color="#008080" style="font-weight:bold;">管理员添加</font>'; break;
        default: reg = "";
    }
    return reg;
}

function editFormatter(value,row,index) {
    var menu = "";
    if($("#addressPermiss").val()){
         menu += ' <a onclick="User.openUserAddress(\''+row.id+'\')" title="收货地址"><span class="label label-default">收货地址</span></a>';
    }
    if($("#updatePermiss").val()) {
        if(row.userStatus == 0){
            menu += ' <a onclick="User.updateStatus(\''+row.id+'\',\'1\')" title="禁用"><span class="label label-default">禁用</span></a>';
        }else if(row.userStatus == 1){
            menu += ' <a onclick="User.updateStatus(\''+row.id+'\',\'0\')" title="启用"><span class="label label-success">启用</span></a>';
        }

    }
    return menu;
}
