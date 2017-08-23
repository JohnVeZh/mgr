/**
 * wangEditor插件的封装
 */
(function() {

    var $wangEditor = function(initId) {
        this.id = initId+"editor";
        this.idPram = initId;
    };

    $wangEditor.prototype = {
        /**
         * 初始化editor的设置
         */
        init : function() {
            //七牛参数
            var tokenUrl = "/qiniu/getUpToken";
            var bucket = "http://res.xhiw.com.cn/";
            var editor = new wangEditor(this.id);
            var idPram = this.idPram;
            editor.config.customUpload = true;  // 设置自定义上传的开关
            editor.config.customUploadInit = function () {
                var editor = this;
                // 触发选择文件的按钮的id
                var btnId = editor.customUploadBtnId;
                // 触发选择文件的按钮的父容器的id
                var containerId = editor.customUploadContainerId;

                // 创建上传对象
                var uploader = Qiniu.uploader({
                    runtimes: 'html5,flash,html4',    //上传模式,依次退化
                    browse_button: btnId,       //上传选择的点选按钮，**必需**
                    uptoken_url: tokenUrl,
                    // uptoken : 'NEg0gY6JM0N-UUeGrfEz3wvX6NJFRiAXwQ-SHdjS:JM9n1L-1k8tqS6887y2DpWCpJyc=:eyJzY29wZSI6InNwYXJrIiwiZGVhZGxpbmUiOjE1MDAzOTEyNTl9',//若未指定uptoken_url,则必须指定 uptoken ,uptoken由其他程序生成
                    unique_names: true,// 默认 false，key为文件名。若开启该选项，SDK会为每个文件自动生成key（文件名）
                    // save_key: true,// 默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK在前端将不对key进行任何处理
                    domain: bucket, //bucket 域名，下载资源时用到，**必需**
                    container: containerId,
                    max_file_size: '100mb',
                    flash_swf_url: Feng.ctxPath+ '/static/js/plugins/qiNiu/Moxie.swf',
                    filters: {
                        mime_types: [
                            { title: "图片文件", extensions: "jpg,gif,png,bmp" }
                        ]
                    },
                    max_retries: 3,
                    dragdrop: true,
                    drop_element: 'editor-container',
                    chunk_size: '4mb',
                    auto_start: true,
                    init: {
                        'FilesAdded': function(up, files) {
                            plupload.each(files, function(file) {
                                console.log('on FilesAdded');// 文件添加进队列后,处理相关的事情
                            });
                        },
                        'BeforeUpload': function(up, file) {
                            // 每个文件上传前,处理相关的事情
                            console.log('on BeforeUpload');
                        },
                        'UploadProgress': function(up, file) {
                            // 显示进度条
                            editor.showUploadProgress(file.percent);
                        },
                        'FileUploaded': function(up, file, info) {
                            // 每个文件上传成功后,处理相关的事情
                            // 其中 info 是文件上传成功后，服务端返回的json，形式如
                            // {
                            //    "hash": "Fh8xVqod2MQ1mocfI4S4KpRL6D98",
                            //    "key": "gogopher.jpg"
                            //  }
                            console.log(info);
                            var domain = up.getOption('domain');
                            var res = $.parseJSON(info);
                            var sourceLink = domain + res.key; //获取上传成功后的文件的Url
                            console.log(sourceLink);
                            // 插入图片到editor
                            editor.command(null, 'insertHtml', '<img src="' + sourceLink + '" style="max-width:100%;"/>')
                        },
                        'Error': function(up, err, errTip) {
                            //上传出错时,处理相关的事情
                            console.log('on Error');
                        },
                        'UploadComplete': function() {
                            //队列文件处理完毕后,处理相关的事情
                            console.log('on UploadComplete');
                            // 隐藏进度条
                            editor.hideUploadProgress();
                        }
                        // Key 函数如果有需要自行配置，无特殊需要请注释
                        //,
                        // 'Key': function(up, file) {
                        //     // 若想在前端对每个文件的key进行个性化处理，可以配置该函数
                        //     // 该配置必须要在 unique_names: false , save_key: false 时才生效
                        //     var key = "";
                        //     // do something with key here
                        //     return key
                        // }
                    }
                });
            };
            editor.onchange = function () {
                // 编辑区域内容变化时，实时打印出当前内容
                $("#"+idPram).val(this.$txt.html());
            };
            editor.create();
            $("#"+idPram).val(editor.$txt.html());
            return editor;
        }
    };
    window.$wangEditor = $wangEditor;

}());

