/**
 * 上传图片组件扩展
 * @param settings
 * @constructor
 */

var FileInputCustom = function (config, inputSettings) {
    this.init(config, inputSettings);
};
FileInputCustom.prototype.defaults = {
    language: "zh",
    uploadAsync: true,
    allowedFileExtensions: ['jpg', 'png', 'zip','mp3','ogg','wav'],
    uploadUrl: Feng.ctxPath + '/system/upload/multi',
    autoReplace: true,
    overwriteInitial: true,
    showUploadedThumbs: false,
    validateInitialCount: true,
    maxFileCount: 10,
    useDefaultName: false

};
FileInputCustom.prototype.init = function (config, inputSettings) {
    if (!inputSettings) {
        inputSettings = {};
    }
    this.settings = config;
    this.settings.uploadedUrls = [];
    var $inputFile = config.$inputFile;//fileInput控件
    if (inputSettings.dataUrls) {
        var initImages = inputSettings.dataUrls.split(",");
        var initialPreview = [];
        var initialPreviewConfig = [];
        if (inputSettings.dataUrls.length > 0) {
            $.each(initImages, function (i, obj) {
                this.settings.uploadedUrls.push(obj);
                var part = obj.split(".");
                console.info(part[part.length-1]);
                if(part[part.length-1] == "mp3" ||  part[part.length-1] == "ogg"||  part[part.length-1] == "wav"){
                    initialPreview.push("<audio  controls='controls' style='height:160px' src='" + obj + "'> </audio>");
                }if(part[part.length-1] == "jpg" ||  part[part.length-1] == "png"||  part[part.length-1] == "zip"){
                    initialPreview.push("<img style='height:160px' src='" + obj + "'>");
                }
                var config = {
                    width: "120px",
                    url: Feng.ctxPath + "/system/delete",
                    key: i,
                    extra: obj
                };
                initialPreviewConfig.push(config);
            }.bind(this))
        }
        inputSettings.initialPreview = initialPreview;
        inputSettings.initialPreviewConfig = initialPreviewConfig;
    }
    this.inputFileSettings = $.extend({}, this.defaults, inputSettings);
    if (this.inputFileSettings.maxFileCount > 1) {
        this.inputFileSettings.autoReplace = false;
        this.inputFileSettings.overwriteInitial = false;
    }
    if (this.inputFileSettings.useDefaultName) {
        this.inputFileSettings.uploadUrl = this.inputFileSettings.uploadUrl + "?useDefaultName=true"
    }
    //初始化上传图片控件
    $inputFile.fileinput(this.inputFileSettings);
    $inputFile.on('fileuploaded', function (event, data, id, index) {
        this.settings.uploadedUrls.push(data.response.url);
        $("#" + id).attr("dataUrl", data.response.url);
        this.setUploadUrls();
    }.bind(this));

    $inputFile.on('filecleared', function (event) {
        this.settings.uploadedUrls.length = 0;
    }.bind(this));
    $inputFile.on('filepreupload', function (event, data, previewId, index) {
        if (this.inputFileSettings.maxFileCount == 1) {
            this.settings.uploadedUrls.length = 0;
        } else {
            if (this.inputFileSettings.maxFileCount <= this.settings.uploadedUrls.length) {
                this.settings.uploadedUrls.remove(this.settings.uploadedUrls.length - 1);
            }
        }
    }.bind(this));
    $inputFile.on('filedeleted', function (event, key, jqXHR, data) {
        this.settings.uploadedUrls.removevalue(data);
        this.setUploadUrls();
    }.bind(this));
    $inputFile.on('filesuccessremove', function (event, id) {
        this.settings.uploadedUrls.remove($("#" + id).attr("dataUrl"));
    }.bind(this));
};
/**
 * 设置上传的url地址到input中
 */
FileInputCustom.prototype.setUploadUrls = function () {
    var urls = "";
    $.each(this.settings.uploadedUrls, function (index, obj) {
        if (index > 0) {
            urls += ',' + obj;
        } else {
            urls += obj;
        }
    });
    this.settings.$resUrl.val(urls);

    return urls;
}
FileInputCustom.prototype.checkUrls = function () {
    var urls = this.setUploadUrls();
    return urls && urls.length > 0;
};
Array.prototype.removevalue = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
