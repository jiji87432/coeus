$(function(){
	$(".form-time-control").each(function(){
	    var oTime = new TimeInit();
	    oTime.Init($(this));
	 });
})
var TimeInit = function () {
	var oTimeInit = new Object();
	oTimeInit.Init = function (timeObj) {
		$(timeObj).datetimepicker({
			format: 'yyyy-mm-dd hh:ii',
			language: 'zh-CN',
			autoclose:true
        });
	}
	return oTimeInit;
}