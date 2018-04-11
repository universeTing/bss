<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.user.title" /></title>
  <meta name="decorator" content="grid-select"/>
</head>
<body title="<spring:message code="sys.user.title" />">
	<grid:grid id="userGridId" multiselect="false" url="${adminPath}/sys/user/ajaxListUser">
		<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
		<grid:query name="orgId"  queryMode="hidden" />
	    <grid:column label="sys.user.realname"  name="realname"  query="true"  condition="like" width="60" />
	    <grid:column label="sys.user.username"  name="username"  query="true" condition="like"  width="60" />
	    <grid:column label="sys.user.organization.name"  name="orgName"/>
	    <grid:column label="sys.user.email"  name="email"  width="60"/>
	    <grid:column label="sys.user.phone"  name="phone"  query="true"  condition="like"  width="60"/>
		<grid:toolbar  function="search"/>
		<grid:toolbar  function="reset"/>
	</grid:grid>
</body>
</html>