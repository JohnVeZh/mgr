@/*
    表单中input框标签中各个参数的说明:
    leftWidth : 左边标签宽度 栅格值为1-12
    rightWidth ：右边标签宽度 栅格值为1-12
    id : input框id
    name : input框名称
    readonly : readonly属性
    clickFun : 点击事件的方法名
    style : 附加的css属性
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
        <textarea class="form-control" id="${id}" name="${id}"
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
      @if(isNotEmpty(content)){
        >${content}</textarea>
        @}else{
        ></textarea>
        @}


    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


