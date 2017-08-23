@/*
wangEditor参数的说明:
name : 名称不加editor后缀，方便表单提交
id : 控件的id
content:初始化内容
width:宽度
height:高度
leftWidth : 左边标签宽度 栅格值为1-12
rightWidth ：右边标签宽度 栅格值为1-12
@*/
<div class="form-group" id="${id}Group">
    <label for="${id}"
           @if(isNotEmpty(leftWidth) && isNotEmpty(rightWidth)){
           class="col-sm-${leftWidth} control-label"
           @}else {
           class="col-sm-3 control-label"
           @}
    >${name}</label>
    <div
            @if(isNotEmpty(leftWidth) && isNotEmpty(rightWidth)){
            class="col-sm-${rightWidth} "
            @}else {
            class="col-sm-9 "
            @}
    >
        <textarea  id="${id}editor"  class="wangEditor"
                   @if(isNotEmpty(width) && isNotEmpty(height)){
                        style="width:${width};height:${height}"
                   @}else if(isNotEmpty(width)){
                        style="width:${width}"
                    @}else if(isNotEmpty(height)){
                        style="height:${height}"
                    @}
 @if(isNotEmpty(content)){
        >${content}</textarea>
   @}else{
        ></textarea>
        @}
    </div>
    <input id="${id}" name="${id}" type="hidden"  >


</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}
