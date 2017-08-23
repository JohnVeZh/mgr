/**
 * 词汇管理管理初始化
 */
var Words = {
    id: "WordsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

//例句
// private String sentence;
// //例句翻译
// private String reference;
// //例句发音
// private String referenceAudioUrl;
/**
 *
 * 初始化表格的列
 */
Words.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '目录id', field: 'catalogId', visible: false, align: 'center', valign: 'middle'},
        {title: '目录', field: 'catalogName',  align: 'center', valign: 'middle'},
        {title: '单词名称', field: 'name',  align: 'center', valign: 'middle'},
        {title: '音标', field: 'phonetic',  align: 'center', valign: 'middle'},
       /* {title: '发音文件地址', field: 'pronunciationUrl', width:250, align: 'center', valign: 'middle',formatter : function (e,column,index) {
                return '<audio src="'+column.pronunciationUrl+'">您的浏览器不支持播放。</audio>';
        }},*/
        {title: '释义', field: 'paraphrase',  align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Words.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Words.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加词汇管理
 */
Words.openAddWords = function () {
    var index = layer.open({
        type: 2,
        title: '添加词汇管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/words/words_add'
    });
    this.layerIndex = index;
    layer.full(index);
};

/**
 * 打开查看词汇管理详情
 */
Words.openWordsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '词汇管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/words/words_update/' + Words.seItem.id
        });
        this.layerIndex = index;
        layer.full(index);
    }
};

/**
 * 删除词汇管理
 */
Words.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/words/delete", function (data) {
            Feng.success("删除成功!");
            Words.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wordsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询词汇管理列表
 */
Words.search = function () {
    var queryData = {};
    queryData['nameLike'] = $("#name").val();
    Words.table.refresh({query: queryData});
};

Words.resetSearch = function () {
    var queryData = {};
    $("#name").val('');
};


$(function () {
    var defaultColunms = Words.initColumn();
    var table = new BSTable(Words.id, "/words/list", defaultColunms);
    Words.table = table.init();
});
