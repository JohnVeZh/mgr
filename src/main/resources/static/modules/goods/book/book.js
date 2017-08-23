/**
 * 图书管理初始化
 */
var Book = {
    id: "BookTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    zTreeInstance : null,
    layerIndex: -1

};

/**
 * 初始化表格的列
 */
Book.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '图书名称', field: 'name',width:'20%', align: 'center', valign: 'middle', sortable: true},
        {title: '列表图片', field: 'listImg', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '封面图片', field: 'coverImg', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<img width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" src="' + value + '">';
        }},
        {title: '原价', field: 'originalPrice', align: 'center', valign: 'middle', sortable: true},
        {title: '现价', field: 'presentPrice', align: 'center', valign: 'middle', sortable: true},
        {title: '邮费', field: 'postage', align: 'center', valign: 'middle', sortable: true},
        {title: '类型', field: 'bookTypeName', align: 'center', valign: 'middle', sortable: true},
        {title: '上架', field: 'isShow', align: 'center', valign: 'middle', sortable: true, formatter: function(value, row, index){
            switch(value) {
                case 0:
                    return '<span class="label label-danger">否</span>';
                case 1:
                    return '<span class="label label-primary">是</span>';
                default:
                    break;
            }
        }},
        {title: '操作', field: '', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            return '<a href="javascript:void(0)" onclick="Book.updateIsShow(\''+row.id+'\')">上架状态</a>';
        }}
    ];
};

/**
 * 去往修改上架状态页面
 */
Book.updateIsShow = function (id) {
    var index = layer.open({
        type: 2,
        title: '修改上架状态',
        area: ['380px', '260px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/book/book_updateIsShow/'+id
    });
    this.layerIndex = index;
};

/**
 * 检查是否选中
 */
Book.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Book.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加图书
 */
Book.openAddBook = function () {
    var index = layer.open({
        type: 2,
        title: '添加图书',
        area: ['1130px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goods/book/book_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看图书详情
 */
Book.openBookDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '图书详情',
            area: ['1130px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goods/book/book_update/' + Book.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除图书
 */
Book.delete = function () {
    if (this.check()) {
        var operation=function () {
            var ajax = new $ax(Feng.ctxPath + "/goods/book/delete", function (data) {
                Feng.success("删除成功!");
                Book.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id",Book.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否刪除该图书?", operation);
    }
};

/**
 * 重置搜索条件
 */
Book.resetSearch = function () {
    $("#condition").val("");
    $("#endDate").val("");
    $("#startDate").val("");
    $("#sectionCode").val("");
    $("#typeId").val("");
    $("#typeName").attr("value", '');
    Book.search();
};

/**
 * 查询图书列表
 */
Book.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['endDate'] = $("#endDate").val();
    queryData['startDate'] = $("#startDate").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    queryData['typeId'] = $("#typeId").val();
    // queryData['typeName'] = $("#typeName").val();
    Book.table.refresh({query: queryData});
};



/**
 * 点击类型ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
Book.onClickBook = function(e, treeId, treeNode) {
    $("#typeName").attr("value", Book.zTreeInstance.getSelectedVal());
    $("#typeId").attr("value", treeNode.id);
}



/**
 * 显示类型选择的树
 *
 * @returns
 */
Book.showBookSelectTree = function() {
    var typeName = $("#typeName");
    var typeNameOffset = $("#typeName").offset();
    $("#parentBookMenu").css({
        left : typeNameOffset.left + "px",
        /* top : typeNameOffset.top + typeName.outerHeight() + "px"*/
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}


/**
 * 隐藏类型选择的树
 */
Book.hideBookSelectTree = function() {
    $("#parentBookMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}

function onBodyDown(event) {
    if (!(event.target.id === "menuBtn" || event.target.id === "parentBookMenu" || $(
            event.target).parents("#parentBookMenu").length > 0)) {
        Book.hideBookSelectTree();
    }
}


$(function () {
    //初始化类型树
    var ztree = new $ZTree("parentBookMenuTree", "/goods/bookType/tree");
    ztree.bindOnClick(Book.onClickBook);
    ztree.init();
    Book.zTreeInstance = ztree;


    var defaultColunms = Book.initColumn();
    var table = new BSTable(Book.id, "/goods/book/list", defaultColunms);
    table.setPaginationType("server");
    Book.table = table.init();
});
