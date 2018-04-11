<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>${functionName}列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="${functionName}">
<grid:grid id="${entityName?uncap_first}GridId" async="true" treeGrid="true"  expandColumn="name"  url="${r'${adminPath}'}/${moduleName}/${entityName?lower_case}/ajaxTreeList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
<#list columns as column>
   <#if column.isList&&column.columnName!='id'>
    <grid:column label="<#if column.remarks??&&column.remarks!="">${column.remarks}<#else>${column.javaField}</#if>"  name="${column.javaField}" <#if column.isQuery> query="true" <#if column.queryModel??&&column.queryModel!=""> queryMode="${column.queryModel}"</#if> <#if column.queryType??&&column.queryType!=""> condition="${column.queryType}" </#if></#if><#if column.dictGroup??&&column.dictGroup!=""> dict="${column.dictGroup}"</#if>/>
   </#if>
</#list>
    <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete"/>
	<shiro:hasPermission name="${moduleName}:${entityName?lower_case}:create">
		<grid:toolbar function="create"/>
	</shiro:hasPermission>
	<shiro:hasPermission name="${moduleName}:${entityName?lower_case}:update">
		<grid:toolbar function="update"/>
	</shiro:hasPermission>
	<shiro:hasPermission name="${moduleName}:${entityName?lower_case}:delete">
		<grid:toolbar function="delete"/>
	</shiro:hasPermission>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>