/**
 * 初始化词汇管理详情对话框
 */
var WordsInfoDlg = {
    wordsInfoData : {},
    selectWordCatalog:{},
    validateFields:{
        name: {
            validators: {
                notEmpty: {
                    message: '单词名称不能为空'
                }
            }
        },
        phonetic: {
            validators: {
                notEmpty: {
                    message: '单词音标不能为空'
                }
            }
        },
        paraphrase: {
            validators: {
                notEmpty: {
                    message: '释义不能为空'
                }
            }
        },
        sentence: {
            validators: {
                notEmpty: {
                    message: '例句不能为空'
                }
            }
        },
        reference: {
            validators: {
                notEmpty: {
                    message: '例句翻译不能为空'
                }
            }
        }
    }
};

WordsInfoDlg.showWordCatalogLayer = function () {
    var selectWordCatalog = layer.open({
        type: 2,
        title: '添加词汇管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wordsCatalog?isSelect=true'
    });
    this.selectWordCatalog = selectWordCatalog;
}

/**
 * 清除数据
 */
WordsInfoDlg.clearData = function() {
    this.wordsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WordsInfoDlg.set = function(key, val) {
     if(val!=undefined){
        this.wordsInfoData[key] = val;
    }else{
        this.wordsInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    }
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WordsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WordsInfoDlg.close = function() {
    parent.layer.close(window.parent.Words.layerIndex);
}

/**
 * 收集数据
 */
WordsInfoDlg.collectData = function() {
    this.set('id');
    this.set('catalogId');
    this.set('name');
    this.set('phonetic');
    this.set('pronunciationUrl');
    this.set('paraphrase');
    this.set('sentence');
    this.set('reference');
    this.set('referenceAudioUrl');
};

WordsInfoDlg.validate = function () {
    $('#wordsForm').data("bootstrapValidator").resetForm();
    $('#wordsForm').bootstrapValidator('validate');
    return $("#wordsForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
WordsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    var pronunciationUrl = $("#pronunciationUrl").val();
    if(pronunciationUrl === null || pronunciationUrl === undefined || pronunciationUrl === ''){
        //提示层
        layer.msg('请发音文件');
        return;
    }

    /*var referenceAudioUrl = $("#referenceAudioUrl").val();
    if(referenceAudioUrl === null || referenceAudioUrl === undefined || referenceAudioUrl === ''){
        //提示层
        layer.msg('请例句发音文件');
        return;
    }*/
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/words/add", function(data){
        Feng.success("添加成功!");
        window.parent.Words.table.refresh();
        WordsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wordsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WordsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    var pronunciationUrl = $("#pronunciationUrl").val();
    if(pronunciationUrl === null || pronunciationUrl === undefined || pronunciationUrl === ''){
        //提示层
        layer.msg('请发音文件');
        return;
    }

   /* var referenceAudioUrl = $("#referenceAudioUrl").val();
    if(referenceAudioUrl === null || referenceAudioUrl === undefined || referenceAudioUrl === ''){
        //提示层
        layer.msg('请例句发音文件');
        return;
    }*/
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/words/update", function(data){
        Feng.success("修改成功!");
        window.parent.Words.table.refresh();
        WordsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wordsInfoData);
    ajax.start();
}

$(function() {

    Feng.initValidator("wordsForm", WordsInfoDlg.validateFields);
    // 初始化上传
    var avatarUp = new $WeAudiobUpload("pronunciationUrl");
    avatarUp.init();

    // 初始化上传
    var avatarUp2 = new $WeAudiobUpload("referenceAudioUrl");
    avatarUp2.init();
});
