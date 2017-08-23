var PaperStructureList = {
    id: "PaperStructureListTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 初始化表格列
 */
PaperStructureList.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        /*  {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},*/
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {
            title: '类型',
            visible: true,
            field: 'contentType',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {

                switch (row.contentType) {
                    case 1:
                        return "听力";
                        break;
                    case 2:
                        return "阅读";
                        break;
                    case 3:
                        return "翻译";
                        break;
                    case 4:
                        return "写作";
                        break;
                    default :
                        return "";
                        break;
                }
            }
        },
        {title: '级别', field: 'level', visible: true, align: 'center', valign: 'middle'}

    ];
};
PaperStructureList.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    console.log(selected);

    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        PaperStructureList.seItem = selected[0];
        return true;
    }
};

/**
 * 添加试卷结构
 */
PaperStructureList.addPaperStructure = function () {

    var index = layer.open({
        type: 2,
        title: '添加试卷结构',
        area: ['900px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/paper/paperStructure/toAddPaperStructure?paperId=' + $("#paperId").val()
    });
    this.layerIndex = index;

}

/**
 * 修改试卷结构
 */
PaperStructureList.editPaperStructure = function () {
    if (this.check()) {

        var arr = ["1","2","3","4"];

        if ($.inArray(PaperStructureList.seItem.id, arr) != -1) {
            Feng.info("该选项不能修改");
            return false;
        }
        var index = layer.open({
            type: 2,
            title: '修改试卷结构',
            area: ['900px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/paper/paperStructure/toDetailStructure/' + PaperStructureList.seItem.id
        });
        this.layerIndex = index;
    }
}

/**
 * 删除试卷结构
 */
PaperStructureList.deletePaperStructure = function () {
    var arr = ["1","2","3","4"];

    if ($.inArray(PaperStructureList.seItem.id, arr) != -1) {
        Feng.info("该选项不能删除");
        return false;
    }
    if (this.check()) {
        var checkAjax = new $ax(Feng.ctxPath + "/paper/paperStructure/check", function (data) {

            if (data == "childStruture") {
                Feng.error("请先删除子级结构!");
            } else if (data == "paper") {
                Feng.error("请先删除该结构下的试卷!");
            } else if (data == "success") {
                var ajax = new $ax(Feng.ctxPath + "/paper/paperStructure/delete", function (data) {
                    Feng.success("删除成功!");
                    PaperStructureList.table.refresh();
                }, function (data) {
                    Feng.success("删除失败!");
                });

                ajax.set("id", PaperStructureList.seItem.id);
                ajax.start();

            }

        }, function (data) {
            Feng.error("检查失败!");
        });
        checkAjax.set("id", PaperStructureList.seItem.id);
        checkAjax.start();
    }
}

$(function () {

    var defaultColunms = PaperStructureList.initColumn();

    var table = new BSTreeTable(PaperStructureList.id, "/paper/paperStructure/getStructureByPaper?paperId=" + $("#paperId").val(), defaultColunms);

    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.init();

    PaperStructureList.table = table;

})