@/*
    上传音频参数的说明:
    name : 名称
    id : 图片的id
@*/
<div class="form-group" id="${id}Group">
    <label class="col-sm-3 control-label head-scu-label">${name}</label>
    <div class="col-sm-5">
        <div id="${id}PreId">
            <div><audio controls="controls"><source
                @if(isEmpty(url)){
                      src="">
                @}else{
                      src="${url}">
                @}
                不支持音频
            </audio>
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


