@/*
    select标签中各个参数的说明:
    leftWidth : 左边标签宽度 栅格值为1-12
    rightWidth ：右边标签宽度 栅格值为1-12
    name : select的名称
    id : select的id
    underline : 是否带分割线
    disabled: 是否禁用
@*/
<div class="form-group" id="${id}Group" style="vertical-align: middle">
    <label
            @if(isNotEmpty(leftWidth) && isNotEmpty(rightWidth)){
            class="col-sm-${leftWidth} control-label"
            @}else {
            class="col-sm-3 control-label"
            @}
            style="vertical-align: middle;"
     >${name}</label>
    <div
            @if(isNotEmpty(leftWidth) && isNotEmpty(rightWidth)){
            class="col-sm-${rightWidth} "
            @}else {
            class="col-sm-9 "
            @}
    >
        <select class="form-control" id="${id}" name="${id}"
            @if(isNotEmpty(disabled)){
                disabled="${disabled}"
            @}
            @if(isNotEmpty(changeFun)){
                onchange="${changeFun}"
            @}
            @if(isNotEmpty(style)){
                style="${style}"
            @}

        >
            ${tagBody!}
        </select>
        @if(isNotEmpty(hidden)){
            <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}">
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


