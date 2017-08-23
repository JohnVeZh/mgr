/**
 * 学习方案管理初始化
 */
var PlanConfig = {
    id: "PlanConfigTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PlanConfig.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '学段', field: 'sectionCode', align: 'center', valign: 'middle', sortable:true,formatter: function (value,row,index) {
            switch (value) {
                case 1:
                    return '四级';
                case 2:
                    return '六级';
                default:
                    break;
            }
        }},
        {title: '阶段', field: 'period', align: 'center', valign: 'middle', sortable:true,formatter: function (value,row,index) {
            switch (value) {
                case 1:
                    return '学前';
                case 2:
                    return '学中';
                case 3:
                    return '学末';
                default:
                    break;
            }
        }},
        {title: '展示名称', field: 'title', align: 'center', valign: 'middle', sortable:true},
        {title: '起始分数', field: 'scoreStart', align: 'center', valign: 'middle', sortable:true},
        {title: '结束分数', field: 'scoreEnd', align: 'center', valign: 'middle', sortable:true},
        {title: '排序', field: 'sort', align: 'center', valign: 'middle', sortable:true},
        {title: '操作', field: '', align: 'center', valign: 'middle', sortable: true,formatter: function(value,row,index){
            var htmls='<a href="javascript:void(0)" onclick="PlanConfig.bindNetworkCourse(\''+row.id+'\')">已绑定的网课</a>';
            return htmls;
        }}
    ];
};

PlanConfig.bindNetworkCourse = function(configId){
    var index = layer.open({
        type: 2,
        title: '绑定网课列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/gift/planNetworkCourse/'+configId
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 检查是否选中
 */
PlanConfig.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PlanConfig.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加学习方案
 */
PlanConfig.openAddPlanConfig = function () {
    var index = layer.open({
        type: 2,
        title: '添加学习方案',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/gift/planConfig/planConfig_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看学习方案详情
 */
PlanConfig.openPlanConfigDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '学习方案详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/gift/planConfig/planConfig_update/' + PlanConfig.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除学习方案
 */
PlanConfig.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/gift/planConfig/delete", function (data) {
            Feng.success("删除成功!");
            PlanConfig.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("planConfigId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询学习方案列表
 */
PlanConfig.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['sectionCode'] = $("#sectionCode").val();
    queryData['period'] = $("#period").val();
    PlanConfig.table.refresh({query: queryData});
};

/**
 * 清空查询条件
 */
PlanConfig.resetSearch = function () {
    $("#title").val("");
    $("#sectionCode").val("");
    $("#period").val("");
    PlanConfig.search();
}

$(function () {
    var defaultColunms = PlanConfig.initColumn();
    var table = new BSTable(PlanConfig.id, "/gift/planConfig/list", defaultColunms);
    table.setPaginationType("server");
    PlanConfig.table = table.init();
});
