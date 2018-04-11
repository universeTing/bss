/*
 * public-add.js  @bgg  TM2017-12-22
 */
function onloadFun(){
	var winHeight = $("#page-wrapper").height()-100;
	
	try{
		$(".portlet-body,.portlet-body>div").css("height",winHeight+"px");
		$(".table-jqgrid").jqGrid("setGridHeight","auto");
		//$(".ui-jqgrid-view").css({"height":winHeight-150+"px","overflow-x":"hidden"});
		$(".ui-jqgrid-bdiv").css("overflow-x","hidden");
	}catch(e){}
}
function publicInit(){
	var _EXIDtime = 0;
	var winHeight = $(window).height();
	try{
		var times = null;
		clearInterval(times);
		var footerTop = $("#footer").offset().top;
		$(".cssanimations,html[lang=zh]").addClass("custom-scrollable");
		//$("#page-wrapper").height(winHeight);
		var wrap = $("#wrapper");
		wrap.css("height",winHeight-50+"px");
		wrap.find(".portlet-body").css("height",winHeight-91+"px");
		var w = wrap.find(".table-responsive");
		
		if(!w){
			times = setInterval(function(){
				_EXIDtime++;//防止死循环
				w = wrap.find(".table-responsive");
				w.css("height",winHeight-246+"px");
				if(w || _EXIDtime >= 50) clearInterval(times);
			},100);
		}
	}catch(e){}
	try{
		if($(".ibox-content")) $(".ibox-content").height(winHeight-115);
			
		if($('#cc')) $('#cc').layout('resize',{height:winHeight-50});
	}catch(e){}
}
$(function(){
	publicInit();
});
$(window).resize(function(){
	publicInit()
});
