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

    <label for="${id}"

    >${name}</label>
    <div

    >
        <textarea  id="${id}editor" name="${id}" class="wangEditor"
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

@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}
