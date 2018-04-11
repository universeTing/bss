<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html lang="en" style="overflow:hidden;">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title><spring:message code="sys.site.title" arguments="${platformName}"/></title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- 导入CSS -->
		<html:css  name="favicon,bootstrap,font-awesome,ace-theme"/>
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default ace-save-state">
			 <%@include file="../ace/header.jsp"%>
		</div>
		<!-- /section:basics/navbar.layout -->
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
				
			</script>
			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar  responsive  ace-save-state">
				 <%@include file="../ace/left.jsp"%>
			</div>
			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
					
					
						<!-- <div class="ace-settings-container" id="ace-settings-container">
							<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
								<i class="ace-icon fa fa-cog bigger-130"></i>
							</div>

							<div class="ace-settings-box clearfix" id="ace-settings-box">
								<div class="pull-left width-50">
									<div class="ace-settings-item">
										<div class="pull-left">
											<select id="skin-colorpicker" class="hide">
												<option data-skin="no-skin" value="#438EB9">#438EB9</option>
												<option data-skin="skin-1" value="#222A2D">#222A2D</option>
												<option data-skin="skin-2" value="#C6487E">#C6487E</option>
												<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
											</select>
										</div>
										<span>&nbsp; Choose Skin</span>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-navbar" autocomplete="off" />
										<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-sidebar" autocomplete="off" />
										<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-breadcrumbs" autocomplete="off" />
										<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" autocomplete="off" />
										<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-add-container" autocomplete="off" />
										<label class="lbl" for="ace-settings-add-container">
											Inside
											<b>.container</b>
										</label>
									</div>

								</div>

								<div class="pull-left width-50">
									#section:basics/sidebar.options
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" autocomplete="off" />
										<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" autocomplete="off" />
										<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" autocomplete="off" />
										<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
									</div>

								</div>
							</div>
						</div> -->
						
						<div class="row content-tabs">
			                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
			                </button>
			                <nav class="page-tabs J_menuTabs">
			                    <div class="page-tabs-content">
			                        <a href="javascript:;" class="active J_menuTab" data-id="${adminPath}/main">首页</a>
			                    </div>
			                </nav>
			                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
			                </button>
			                <div class="btn-group roll-nav roll-right">
			                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>
			
			                    </button>
			                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
			                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
			                        </li>
			                        <li class="divider"></li>
			                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
			                        </li>
			                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
			                        </li>
			                    </ul>
			                </div>
			                <%-- <a href="${adminPath}/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a> --%>
			            </div>
			            <div class="row J_mainContent" id="content-main">
			                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${adminPath}/main" frameborder="0" data-id="${adminPath}/main" seamless></iframe>
			            </div>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<%-- <div class="footer">
				<div class="footer-inner">
					<!-- #section:basics/footer -->
					<div class="footer-content">
						<span class="bigger-120">
							 <spring:message code="sys.site.bottom.copyright" />
						</span>
					</div>

					<!-- /section:basics/footer -->
				</div>
			</div> --%>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		<!-- 全局js -->
		<html:js  name="jquery,bootstrap,metisMenu,slimscroll,layer,ace-theme"/>

		
		<script type="text/javascript" src="${staticPath}/common/js/contabs.js"></script>
		<script type="text/javascript">
			var firstName = null;
			var firstIcon = null;
			var menus = new Array();
			$(function(){
				$.ajax({
					type:"POST",
					url:"${adminPath}/menuJson",
					async:false,
					success:function(data){
						for(var i=0;i<data.length;i++){
							var tep ='';
							if(data[i].parentId == null && data[i].isshow == 1){
								if(!firstName){
									firstName = data[i].name;
									firstIcon = data[i].menuIcon;
								}
								for(var j =0;j<data.length;j++){
									if(data[j].parentId == data[i].id && data[j].isshow == 1){
										if(data[j].url != ''){
											tep += '<li><a class="J_menuItem" data-menuid="'+data[j].id+'" href="${adminPath}/'+data[j].url+'"><i class="menu-icon fa '+data[j].menuIcon+'"></i><span class="menu-text">'+data[j].name+'</span></a>';
										}else{
											tep += '<li><a href="#" class="dropdown-toggle"><i class="menu-icon fa '+data[j].menuIcon+'"></i><span class="menu-text">'+data[j].name+'</span><b class="arrow fa fa-angle-down"></b></a>';
										}
										tep +='<ul class="submenu">';
										for(var k =0;k<data.length;k++){
											if(data[k].parentId == data[j].id && data[k].isshow == 1){
												if(data[k].url != ''){
													tep += '<li><a class="J_menuItem" data-menuid="'+data[k].id+'" href="${adminPath}/'+data[k].url+'"><i class="menu-icon fa '+data[k].menuIcon+'"></i><span class="menu-text">'+data[k].name+'</span></a>';
												}else{
													tep += '<li><a href="#" class="dropdown-toggle"><i class="menu-icon fa '+data[k].menuIcon+'"></i><span class="menu-text">'+data[k].name+'</span><b class="arrow fa fa-angle-down"></b></a>';
												}
												tep +='<ul class="submenu">';
												for(var y =0;y<data.length;y++){
													if(data[y].parentId == data[k].id && data[y].isshow == 1){
														if(data[y].url != ''){
															tep += '<li><a class="J_menuItem" data-menuid="'+data[y].id+'" href="${adminPath}/'+data[y].url+'"><i class="menu-icon fa '+data[y].menuIcon+'"></i><span class="menu-text">'+data[y].name+'</span></a>';
														}else{
															tep += '<li><a href="#" class="dropdown-toggle"><i class="menu-icon fa '+data[y].menuIcon+'"></i><span class="menu-text">'+data[y].name+'</span><b class="arrow fa fa-angle-down"></b></a>';
														}
														
													}
												}
												tep +='</ul></li>';
											}
										}
										tep +='</ul></li>';
									}
									//TODO
								}
								menus.push(tep);
							}
						}
						
					},
					complete:function(d){
						$("#nav-list-ul").html(menus[0]);
						$("#sidebar-shortcuts").html('<i class="ace-icon fa '+firstIcon+'"></i>'+firstName);
						$('.J_menuItem').on('click', menuItem);
					}
				});
			});
			
		</script>
	</body>
</html>