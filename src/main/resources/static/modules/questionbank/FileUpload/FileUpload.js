var FileUpload = {
    id: "FileUplod",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    catalogid:0,
    type:0,
};

FileUpload.checkFileExt = function(file){
    var flag = false; //状态
    var arr = ["xls"];
    //取出上传文件的扩展名
    var index = file.lastIndexOf(".");
    var ext = file.substr(index+1).toLowerCase();
    //循环比较
    for(var i=0;i<arr.length;i++)
    {
        if(ext == arr[i])
        {
            flag = true; //一旦找到合适的，立即退出循环
            break;
        }
    }
    //条件判断
    if(flag)
    {
        return true;
    }else
    {
        Feng.error("导入的文件类型不是.xls的类型");
        return false;
    }
}


FileUpload.Upload=function(){

    var type=$("#type").val();
    var paperId=$("#paperId").val();
    var contentType=$("#contentType").val();
    if(type ==null || type==undefined || type==""){
        return Feng.error("试卷类型不能为空");
    }
    if(paperId ==null || paperId ==undefined ||  paperId==""){
        return Feng.error("导入的试卷类型不能为空");
    }
    if(contentType == null || contentType == undefined || contentType==""){

    }

    var fileObj = document.getElementById("UploadFile").files[0]; // js 获取文件对象
    var formData = new FormData();
    formData.append("type", type);
    formData.append("paperId", paperId);
    formData.append("contentType", contentType);
    formData.append("file", fileObj); // 文件对象

    if( !this.checkFileExt($("#UploadFile").val())){
        return;
    }

    if(type=="1" || type =="2"){
        var url=Feng.ctxPath + "/FileUoload/upload";
        FileUpload.FileUpload(url,formData,type);

    }else if(type=="3" || type =="4"){
        var url=Feng.ctxPath + "/paper/upload/"+paperId;
        FileUpload.FileUpload(url,formData,type);
    }
}

FileUpload.FileUpload=function(url,formData,type){
    $.ajax({
        url: url,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            if(type=="1" || type =="2"){
                if(returndata.code=="0"){
                    Feng.success("导入成功");
                    FileUpload.close();
                }else {
                    Feng.error(returndata.message);
                }

            }else {
                Feng.success("导入成功!");
                FileUpload.close();
            }

        },
        error: function (returndata) {
            Feng.error("导入失败!" + returndata.message + "!");
        }
    });
}

FileUpload.close=function (){
    parent.layer.close(window.parent.Question.layerIndex);
}