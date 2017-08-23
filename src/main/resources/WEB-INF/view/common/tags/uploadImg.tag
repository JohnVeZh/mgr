@/*
    上传图片参数的说明:
    name : 名称
    id : 图片的id
@*/
<div class="form-group" id="${id}Group">
    <label class="col-sm-3 control-label head-scu-label">${name}</label>
    <div class="col-sm-5">
        <div id="${id}PreId">
            <div><img width="${width}" height="${height}"
                @if(isEmpty(url)){
                      src="${ctxPath}/static/img/tips.jpg">
                @}else{
                      src="${url}">
                @}
            </div>
        </div>
    </div>

    <div class="col-sm-4">
        <div class="head-scu-btn upload-btn" id="${id}BtnId">
            <i class="fa fa-upload"></i>&nbsp;上传
        </div>
    </div>
    <input type="hidden" id="${id}" value="${url!}"/>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


