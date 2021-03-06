/**
 * 初始化banner详情对话框
 */
var BannerInfoDlg = {
    bannerInfoData : {}
};

/**
 * 清除数据
 */
BannerInfoDlg.clearData = function() {
    this.bannerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BannerInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.bannerInfoData[key] = val;
    }else{
        this.bannerInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BannerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BannerInfoDlg.close = function() {
    parent.layer.close(window.parent.Banner.layerIndex);
}

/**
 * 收集数据
 */
BannerInfoDlg.collectData = function() {
    this.set('id');
    this.set('jumpType');
    var type = $('#jumpType option:selected') .val();
    if(type == 0){
    }else if(type == 1){
        this.set('content');
    }else if(type == 2){
        this.set('url');
    }else if(type == 3 ||type == 4 ||type == 5||type == 6){
        this.set('contentId');
    }
    this.set('moduleType');
    this.set('terminalType');
    this.set('sectionCode');
    this.set('title');
    this.set('img');
    this.set('sort');
}

/**
 * 提交添加
 */
BannerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/banner/add", function(data){
        Feng.success("添加成功!");
        window.parent.Banner.table.refresh();
        BannerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bannerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BannerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/banner/update", function(data){
        Feng.success("修改成功!");
        window.parent.Banner.table.refresh();
        BannerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bannerInfoData);
    ajax.start();
}

$(function() {
// 初始化图片上传
    var avatarUp = new $WebUpload("img");
    avatarUp.init();
    var editor = new $wangEditor("content");
    editor.init();
    initDispaly();
    contentDbClick();
    intUpdate();
    $("#jumpType").change();
});
function initDispaly() {
    $("#urlGroup").css("display","none");
    $("#contentTitleGroup").css("display","none");
    $("#contentGroup").css("display","none");
    $("#jumpType").on("change",function () {
        var type = $('#jumpType option:selected') .val();
        if(type == 0){
            $("#urlGroup").css("display","none");
            $("#contentTitleGroup").css("display","none");
            $("#contentGroup").css("display","none");
        }else if(type == 1){
            $("#urlGroup").css("display","none");
            $("#contentTitleGroup").css("display","none");
            $("#contentGroup").css("display","block");
        }else if(type == 2){
            $("#urlGroup").css("display","block");
            $("#contentTitleGroup").css("display","none");
            $("#contentGroup").css("display","none");
        }else if(type == 3 ||type == 4 ||type == 5||type == 6){
            $("#urlGroup").css("display","none");
            $("#contentTitleGroup").css("display","block");
            $("#contentGroup").css("display","none");
            if($("#isInit").val() != 1){
                $("#contentTitle").val("");
                $("#contentId").css("");
            }else{
                $("#isInit").val(2);
            }
        }
    })
}

function contentDbClick() {
    $("#contentTitle").on("dblclick",function () {
        var path = "";
        var type = $('#jumpType option:selected') .val();
        if(type == 3){
            path = "/banner/activity";
        }else if(type == 4){
            path = "/banner/new";
        }else if(type == 5){
            path = "/banner/book";
        }else if(type == 6){
            path = "/banner/network";
        }
        var index = layer.open({
            type: 2,
            title: 'banner详情',
            area: ['1000px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + path
        });
        this.layerIndex = index;
    })
}

function intUpdate() {
    if($("#jumpTypeInit").val()!= undefined ){
        $("#jumpType").val($("#jumpTypeInit").val());
    }
    if($("#moduleTypeInit").val()!= undefined ){
        $("#moduleType").val($("#moduleTypeInit").val());
    }
    if($("#terminalTypeInit").val()!= undefined ){
        $("#terminalType").val($("#terminalTypeInit").val());
    }
    if($("#sectionCodeInit").val()!= undefined ){
        $("#sectionCode").val($("#sectionCodeInit").val());
    }
}