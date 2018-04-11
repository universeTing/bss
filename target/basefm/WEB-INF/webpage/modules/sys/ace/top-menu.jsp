<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="index" value="0"/>
<c:forEach items="${menus}" var="menu">
     <c:if test="${menu.parentId == null && menu.isshow eq '1'}">
     	<c:set var="index" value="${index+1 }"/>
	    <li><a  href=" #" class="${menu.parent }" onclick="menuChange(${index},'${menu.menuIcon}','${menu.name}')"><i class="menu-icon fa ${menu.menuIcon}" style="color:#${index+3}${index+2}${index+6}999"></i>${menu.name}</a></li>
   </c:if>
</c:forEach>