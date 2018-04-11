<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>组织机构图</title>
    <meta name="decorator" content="form"/>
    <html:css name="syntaxhighlighter"/>
    <html:component name="bootstrap-fileinput"/>
</head>

<body class="white-bg" formid="qsOrgChartForm">
<div class="portlet-body">
    <input id="id" type="hidden" value="${param.id}"/>
    <html:display>

    </html:display>
    <form:fileinput saveType="billId" nested="false"
                    fileInputSetting="{showRemove : false, showBrowse : false, layoutTemplates : {actionDelete:''}}"
                    showUpload="false" showRemove="false" autoUpload="false"
                    path="infoid" buttonText="选择文件"/>
</div>
</body>
<!-- 全局js -->
<html:js name="iCheck,Validform,markdown,syntaxhighlighter"/>
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
<script type="text/javascript">

</script>
</html>