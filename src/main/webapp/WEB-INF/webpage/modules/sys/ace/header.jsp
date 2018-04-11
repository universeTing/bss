<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="navbar-container ace-save-state" id="navbar-container">
	<!-- #section:basics/sidebar.mobile.toggle -->
	<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
		<span class="sr-only">Toggle sidebar</span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>
	</button>

	<!-- /section:basics/sidebar.mobile.toggle -->
	<div class="navbar-header pull-left">
		<!-- #section:basics/navbar.layout.brand -->
		<a class="navbar-brand" >
			<small>
				<!-- <i class="fa fa fa-fire"></i> -->
				<spring:message code="sys.site.title" arguments="${platformName}"/>
			</small>
		</a>

		<!-- /section:basics/navbar.layout.brand -->

		<!-- #section:basics/navbar.toggle -->

		<!-- /section:basics/navbar.toggle -->
	</div>
	<div class=" navbar-buttons navbar-header pull-left	">
		<ul class="nav ace-nav ace-top-nav">
			<li class="light-blue dropdown-modal ">
				<a data-toggle="dropdown" href="#" class="dropdown-toggle">
					首页
				</a>
			</li>
			<li class="light-blue dropdown-modal ">
				<a data-toggle="dropdown" href="#" class="dropdown-toggle">
					流程待办
				</a>
			</li>
			<li class="light-blue dropdown-modal ">
				<a href="#"  class="dropdown-toggle">
					应用中心<i class="ace-icon fa fa-caret-down"></i>
				</a>
				<ul class="dropdown-hover-nav">
					 <%@include file="top-menu.jsp"%>
				</ul>
			</li>
		</ul>
	</div>
	<!-- #section:basics/navbar.dropdown -->
	
	<div class="navbar-buttons navbar-header pull-right" role="navigation">
		<ul class="nav ace-nav">
			
			<!-- #section:basics/navbar.user_menu -->
			<li class="light-blue dropdown-modal ">
				<a data-toggle="dropdown" href="#" class="dropdown-toggle" title="当前用户">
					<img class="nav-user-photo" src="${fns:getUser().portrait}" onerror="this.src='${staticPath}/ace/img/users.png'" alt="${fns:getUser().realname}" />
					<span class="user-info">
						<!-- <small>欢迎您,</small> -->
						${fns:getUser().realname}
					</span>
					<i class="ace-icon fa fa-caret-down"></i>
				</a>

				<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					<li>
						<a class="J_menuItem" href="${adminPath}/sys/user/${fns:getUser().id}/avatar">
							<i class="ace-icon fa fa-user"></i>
							修改密码
						</a>
					</li>
					<li class="divider"></li>
                    <li>
                    	<a href="#" onclick="changeStyle('uadmin')"><i class="ace-icon fa  fa-wikipedia-w"></i>切换风格</a>
                    </li>
                    <!-- <li>
                    	<a href="#" onclick="changeStyle('inspinia')"><i class="ace-icon fa  fa-wikipedia-w"></i>INSPINIA主题</a>
                    </li> -->
					<li class="divider"></li>
					<li>
						<a href="${adminPath}/logout">
							<i class="ace-icon fa fa-power-off"></i>
							安全退出
						</a>
					</li>
				</ul>
			</li>

			<!-- /section:basics/navbar.user_menu -->
		</ul>		
	</div>
	<div class="navbar-buttons navbar-header pull-right" role="navigation">
		<ul class="nav ace-nav ace-top-nav">
			<li class="light-blue dropdown-modal ">
				<a data-toggle="dropdown" href="#" class="dropdown-toggle" onclick="openCutOrgWindow('${adminPath}/sys/user/getUserOrgs','切换组织')" title="点击切换组织">
					组织：${fns:getUser().orgName}
				</a>
			</li>
		</ul>
	</div>
	<!-- /section:basics/navbar.dropdown -->
</div><!-- /.navbar-container -->
<script>
function changeStyle(theme){
	   $.get('${adminPath}/theme/'+theme+'?url='+window.top.location.href,function(result){   window.location.reload();});
}
/*切换组织*/
function openCutOrgWindow(url,title){
		_index = top.layer.open({
		    type: 2,  
		    area: ['400px', '500px'],
		    title: title,
	        maxmin: false, //开启最大化最小化按钮
		    content: url,
		    btn: ['确定', '关闭'],
		    yes: function(index, layero){
		    	 var body = top.layer.getChildFrame('body', index);
		         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		         //文档地址
		         //http://www.layui.com/doc/modules/layer.html#use
		         iframeWin.contentWindow.doSubmit(
		         function(results) {
		        	 if(results.ret != 0) top.layer.alert(result.msg, {icon: 0, title:'警告'});
		        	 //判断逻辑并关闭
	       	         setTimeout(function(){top.layer.close(_index)}, 100);//延时0.1秒，对应360 7.1版本bug
		        	 window.location.reload();
		         });
				
			  }
		}); 
	}
</script>