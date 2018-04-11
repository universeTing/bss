<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>公司资料列表</title>
  <meta name="decorator" content="list"/>
  <html:css  name="iCheck,Validform,easy-ui"/>
  <html:js  name="iCheck,Validform,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="公司资料">
<grid:grid id="companyGridId" url="${adminPath}/base/company/ajaxList"
           gridSetting="{loadComplete:onloadFun,ondblClickRow : dbClickRowFun}" sortname="createDate" sortorder="desc">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="公司编号"  name="number" />
    <grid:column label="公司名称"  name="name"  query="true"  queryMode="input"  condition="eq" />
    <grid:column label="公司类型"  name="companyType"  query="true"  queryMode="select"  condition="eq"  dict="companyType"/>
    <grid:column label="公司简称"  name="simpleName" />
    <grid:column label="公司地址"  name="address" />
    <grid:column label="法人"  name="corporation" />
    <grid:column label="联系人"  name="contacts" />
    <grid:column label="联系电话"  name="contactsPhone" />
    <grid:column label="描述"  name="description" />
    <grid:column label="创建者"  name="creator" />
    <grid:column label="创建时间"  name="createDate"  formatter="date" dateformat='yyyy-MM-dd'/>
    <shiro:hasPermission name="base:company:create">
		<grid:toolbar function="create"/>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:company:update">
	    <grid:toolbar function="update"/>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:company:delete">
		<grid:toolbar function="delete"/>
	</shiro:hasPermission>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">
	// 双击行查看数据
    function dbClickRowFun(rowid,iRow,iCol,e) {
		var url='${adminPath}/base/company/'+ rowid+'/update?load=detail';
    	openDialogDetail('查看',url,"800px","450px") ;
    }
	
	
	/**
	  *@function 加载样式
	  *@author Huangqiling
	  *@date 2018-4-3
	  */
	function onloadFun(){
		var winHeight = $("#page-wrapper").height()-100;
		try{
			$(".portlet-body,.portlet-body>div").css("height",winHeight+"px");
			$(".ui-jqgrid-view").css("height",winHeight-150+"px")
		}catch(e){}
	}
	
	/**
	  *@function 附件查看
	  *@author Huangqiling
	  *@date 2018-4-3
	  */
	function attachmentLook(title,url,gridId,id,width,height){
		rowDialogDetailRefresh(title,url,gridId,id,"auto","auto");
	}
	
</script>
</body>
</html>