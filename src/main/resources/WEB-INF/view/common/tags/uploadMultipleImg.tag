@/*
    上传图片参数的说明:
    name : 名称
    id : 图片的id
@*/
<div class="form-group" id="${id}Group">
    <label for="${id}"
           @if(isNotEmpty(leftWidth) && isNotEmpty(rightWidth)){
           class="col-sm-${leftWidth} control-label"
           @}else {
           class="col-sm-3 control-label"
           @}
    >${name}</label>
    <div id="uploadImgContainer"
            @if(isNotEmpty(leftWidth) && isNotEmpty(rightWidth)){
            class="col-sm-${rightWidth} "
            @}else {
            class="col-sm-9 "
            @}
    >
        <input class="form-control" id="${id}Uploader" name="${id}"
               @if(isNotEmpty(type)){
               type="${type}"
               @}else{
               type="text"
               @}
               @if(isNotEmpty(readonly)){
               readonly="${readonly}"
               @}
               @if(isNotEmpty(clickFun)){
               onclick="${clickFun}"
               @}
               @if(isNotEmpty(style)){
               style="${style}"
               @}
               @if(isNotEmpty(disabled)){
               disabled="${disabled}"
               @}
               @if(isNotEmpty(placeholder)){
               placeholder="${placeholder}"
               @}
               accept="image/jpg,image/jpeg,image/png" multiple
        >
        <input class="form-control"  type="hidden" id="${id}"
               @if(isNotEmpty(value)){
               value="${value}"
               @}
        >
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}


