
//页面初始化时对form加验证
$(document).on('pageinit', function(){
    $('form.validate:last').validate();
});