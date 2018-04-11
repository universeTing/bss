<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html lang="en" style="overflow: hidden;">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="Thu, 19 Nov 1900 08:52:00 GMT">
    
    <title><spring:message code="sys.site.title" arguments="${platformName}"/></title>
    <meta name="keywords" content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
    <meta name="description" content="<spring:message code="sys.site.description" arguments="${platformName}"/>">
     
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="${staticPath}/vendors/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
    <html:css  name="favicon,bootstrap,font-awesome,animate,pace,Validform"/>

    <!--Loading style-->
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/themes/style1/orange-blue.css" class="default-style">
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/themes/style1/orange-blue.css" id="theme-change" class="style-change color-change">
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/style-responsive.css"> 
    <!-- costom css  -->
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/index.css"> 
  
  	    
</head>

<body class="sidebar-default " >
    <div>
        <!--BEGIN BACK TO TOP--><a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
        <!--END BACK TO TOP-->
        <%@include file="../../../decorators/uadmin/topbar.jsp"%>
        <div id="wrapper">
            <%@include file="../../../decorators/uadmin/left.jsp"%>
            <!--BEGIN PAGE WRAPPER-->
            <div id="page-wrapper">
                <!--BEGIN CONTENT-->
                <div class="page-content" style="overflow: auto;">
                     <%@include file="../../../decorators/uadmin/main.jsp"%>
			    </div>
			    <!--END CONTENT-->
    </div>
    <!--BEGIN FOOTER-->
   <%--  <div id="footer">
        <div class="copyright"><spring:message code="sys.site.bottom.copyright" /></div>
    </div> --%>
    <!--END FOOTER-->
    <!--END PAGE WRAPPER-->
    </div>
    </div>
    <html:js  name="jquery,bootstrap,metisMenu,slimscroll,layer,pace,bootstrap-hover-dropdown,jquery-cookie,Validform,echarts,imgUtil"/>
    <script src="${staticPath}/uadmin/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${staticPath}/uadmin/js/jquery-ui.js"></script>
    <!--loading bootstrap js-->
    <script src="${staticPath}/uadmin/js/html5shiv.js"></script>
    <script src="${staticPath}/uadmin/js/respond.min.js"></script>
    <script src="${staticPath}/uadmin/js/jquery.menu.js"></script>

    <script src="${staticPath}/vendors/slimscroll/jquery.slimscroll.js"></script>
    <!--CORE JAVASCRIPT-->
    <script src="${staticPath}/uadmin/js/main.js"></script>
    <script>
     
		$(document).ready(function(){
			var list_theme = $('.dropdown-theme-setting > li > select#list_theme');
		    list_theme.on('change', function(){
		    	var theme=$(this).val();
		    	$.get('${adminPath}/theme/'+theme+'?url='+window.top.location.href,function(result){   
		    		//window.location.reload();
		    		window.top.location.href="${adminPath}";
		    	});
		    	//$.get('${adminPath}/theme/'+theme+'?url='+window.top.location.href,function(result){   window.location.reload();});
		    });
		});
		
		var changePasswordForm;
		function changePassword(){
			changePasswordForm.ajaxPost();
		}
		
		$(document).ready(function() {
			setTimeout(function(){imgLoadError('${staticPath}');},100);
			    changePasswordForm=$("#changePasswordForm").Validform({
				tiptype:function(msg,o,cssctl){
					if(!o.obj.is("form")){
						var objtip=o.obj.siblings(".Validform_checktip");
						cssctl(objtip,o.type);
						objtip.text(msg);
					}
				},beforeSubmit:function(curform){
					try{
						var beforeFunc=beforeSubmit;
						if(beforeFunc&&typeof(beforeFunc)=="function"){
							return beforeFunc(curform); 
						}
					}catch(err){
						
					}
					return true;	
				},callback:function(result){
					if(result.ret==0)
	              	{
	              	    top.layer.alert(result.msg, {icon: 0, title:'提示'});
	              	    //运行回调
	          	        callFunc(result);
	              	}else
	              	{
	              		top.layer.alert(result.msg, {icon: 0, title:'警告'});
	              	}
				}
			});
		});
		
	</script>
	<script type="text/javascript">
	
		function init() {
			var winW = $(".page-content").width();
			
			$(".page-content").height($(window).height()-50);
			var pageH = $(".page-content").height() - 50;
			$(".right").css({'width': winW - 415 + "px",'height':pageH+"px"});
			$(".bottom").height(pageH - 500);
			var myptions = {
				      width: $(".bottom").width()+"px",
				      height: pageH - 600+"px",
				      showLunarCalendar: false, //阴历
				      showHoliday: false, //休假
				      showFestival: false, //节日
				      showLunarFestival: false, //农历节日
				      showSolarTerm: false, //节气
			};
			var myCalendar = new SimpleCalendar('#echarts2',myptions);
		}
		function getMessage(){
			$.ajax({
				type:"POST",
				url:"${adminPath}/wbs/message/findMyMsg",
				success:function(data){
					data = data.data[0];
					$(".left>.tip .mess").html(data.num);
					if(data.num > 0) $(".left>.tip .mess").parent().attr("data-href","${adminPath}/wbs/taskmain");
				}
			});
		}
		$(function() {
			getMessage();
			init();
			$(".right .tabs-t>li").click(function(){
				$(".right .tabs-c>li").eq($(this).index()).addClass("active").siblings("li").removeClass("active");
				$(this).addClass("active").siblings("li").removeClass("active");
			});
			$(".left>.tip>.am-u-sm-4").click(function(){
				var url = $(this).attr("data-href");
				if(url) location.href=url;
			});
		});
		$(window).on("load resize",init);
		
		
	</script>
</body>

</html>