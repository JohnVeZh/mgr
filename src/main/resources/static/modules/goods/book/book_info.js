/**
 * 初始化图书详情对话框
 */
var BookInfoDlg = {
    editor:{},
    bookInfoData : {},
    zTreeInstance : null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '图书名称不能为空'
                }
            }
        },
        typeName: {
            validators: {
                notEmpty: {
                    message: '所属类型不能为空'
                }
            }
        },
        brief: {
            validators: {
                notEmpty: {
                    message: '图书简介不能为空'
                }
            }
        },
        presentPrice: {
            validators: {
                notEmpty: {
                    message: '图书现价不能为空'
                },
                regexp: {
                    regexp: /^(([1-9]\d*(\.\d{2}))|([1-9]\d*))$/,
                    message: '请输入大于0的价格，例如18.00'
                }
            }
        },
        originalPrice: {
            validators: {
                notEmpty: {
                    message: '图书原价不能为空'
                },
                regexp: {
                    regexp: /^(([1-9]\d*(\.\d{2}))|([1-9]\d*))$/,
                    message: '请输入大于0的价格，例如18.00'
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
BookInfoDlg.clearData = function() {
    this.bookInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookInfoDlg.set = function(key, val) {
    this.bookInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BookInfoDlg.close = function() {
    parent.layer.close(window.parent.Book.layerIndex);
}

/**
 * 点击类型ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
BookInfoDlg.onClickBook = function(e, treeId, treeNode) {
    $("#typeName").attr("value", BookInfoDlg.zTreeInstance.getSelectedVal());
    $("#typeId").attr("value", treeNode.id);
}



/**
 * 显示类型选择的树
 *
 * @returns
 */
BookInfoDlg.showBookSelectTree = function() {
    var typeName = $("#typeName");
    var typeNameOffset = $("#typeName").offset();
    $("#parentBookMenu").css({
        left : typeNameOffset.left + "px",
        top : typeNameOffset.top + typeName.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}


/**
 * 隐藏类型选择的树
 */
BookInfoDlg.hideBookSelectTree = function() {
    $("#parentBookMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}




/**
 * 收集数据
 */
BookInfoDlg.collectData = function() {
    // this.set('id');
    this.bookInfoData = {
        id:$("#id").val(),
        bookId:$("#bookId").val(),
        listImg: $("#listImg").val(),
        coverImg: $("#coverImg").val(),
        contentImgs:$("#contentImgs").val(),
        presss:$("#presss").val(),
        typeId:$("#typeId").val(),
        name:$("#name").val(),
        brief:$("#brief").val(),
        originalPrice:$("#originalPrice").val(),
        presentPrice:$("#presentPrice").val(),
        postage:$("#postage").val(),
        sectionCode:$("#sectionCode").val(),
        isShow:$("#isShow").val(),
        isPostage:$("#isPostage").val(),
        isPromotion:$("#isPromotion").val(),
        isSend:$("#isSend").val(),
        sort:$("#sort").val(),
        remarks:$("#remarks").val(),
        content: this.editor.$txt.html()
    };
}


BookInfoDlg.validate = function () {
    $('#bookInfoForm').data("bootstrapValidator").resetForm();
    $('#bookInfoForm').bootstrapValidator('validate');
    return $("#bookInfoForm").data('bootstrapValidator').isValid();
};



/**
 * select是否邮寄
 */
BookInfoDlg.changeIsSend= function(value) {
    if(value==1){
        $("#postage").attr("disabled",false);
    }else {
        $("#postage").attr("disabled",true);
    }
}




/**
 * 提交添加
 */
BookInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/book/add", function(data){
        Feng.success("添加成功!");
        window.parent.Book.table.refresh();
        BookInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BookInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goods/book/update", function(data){
        Feng.success("修改成功!");
        window.parent.Book.table.refresh();
        BookInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookInfoData);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id === "menuBtn" || event.target.id === "parentBookMenu" || $(
            event.target).parents("#parentBookMenu").length > 0)) {
        BookInfoDlg.hideBookSelectTree();
    }
}



$(function() {
    Feng.initValidator("bookInfoForm", BookInfoDlg.validateFields);

    //初始化类型树
    var ztree = new $ZTree("parentBookMenuTree", "/goods/bookType/tree");
    ztree.bindOnClick(BookInfoDlg.onClickBook);
    ztree.init();
    BookInfoDlg.zTreeInstance = ztree;

    // 初始化图片上传
    var listImg = new $WebUpload("listImg");
    listImg.init();
    var coverImg = new $WebUpload("coverImg");
    coverImg.init();
    var contentImgs = new $MyWebUpload("contentImgs");
    contentImgs.init();

    //初始化editor
    var bookEditor = new $wangEditor("content");
    BookInfoDlg.editor =bookEditor.init();

    //初始化下拉选项
    if($("#id").val()!=''){
        $("#sectionCode").val($("#sectionCodeValue").val());
        $("#isShow").val($("#isShowValue").val());
        $("#isPostage").val($("#isPostageValue").val());
        $("#isPromotion").val($("#isPromotionValue").val());

        var isSend=$("#isSendValue").val();
        $("#isSend").val(isSend);
        BookInfoDlg.changeIsSend(isSend)




        //回显详情多图
        var contentImgs = $("#contentImgsValue").val();
        $("#contentImgs").val(contentImgs);

        var imgs=null;
        if(contentImgs!=null && contentImgs!=''){
            imgs=contentImgs.split(",");
        }
        var id = 100001;
        var delFlag=false;
        for(var i=0;i<imgs.length;i++){
            if (!delFlag){
                $("#contentImgsPreId").empty();
                delFlag=true;
            }
            var img=$('<div><img id="' + id + '" width="100px" height="100px" name="contentImgs" value="'+imgs[i]+'" src="'+imgs[i]+'"> <span onclick="$MyWebUpload.removeImg(this.id)" class="cancle glyphicon glyphicon-remove" id="' + id + '"></span></div><br>');
            id++;
            $("#contentImgsPreId").append(img);

        }




    }

});
