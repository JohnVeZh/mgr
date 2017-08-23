@/*
    时间查询条件标签的参数说明:

    name : 查询条件的名称
    id : 查询内容的input框id
    isTime : 日期是否带有小时和分钟(true/false)
    pattern : 日期的正则表达式(例如:"YYYY-MM-DD")
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
    <input type="text" class="form-control layer-date"  name="${id}"
           @if(isNotEmpty(placeholder)){
           placeholder="${placeholder}"
           @}
           @if(isNotEmpty(value)){
           value="${value,'yyyy-MM-dd HH:mm:ss'}"
           @}
           onclick="laydate({istime: ${isTime}, format: '${pattern}'})" id="${id}"/>
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}