<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>qs_best_picture_of_month</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput"/>
    <html:css name="simditor"/>
</head>

<body class="white-bg" formid="qsBestPictureOfMonthForm">
<form:form id="qsBestPictureOfMonthForm" modelAttribute="data" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="orgId" value="${orgId}"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active text-right">
                <label><font color="red">*</font>日期:</label>
            </td>
            <td class="width-35">
                <form:input path="date" datefmt="yyyy-MM-dd" htmlEscape="false" class="form-control layer-date"
                            datatype="*"
                            placeholder="YYYY-MM-DD" onclick="laydate({istime: false, format: 'YYYY-MM-DD'})"/>
                <label class="Validform_checktip"></label>
            </td>
        </tr>
        <tr>
            <td class="width-15 active text-right">
                <label><font color="red">*</font>标题:</label>
            </td>
            <td class="width-35">
                <form:input path="title" htmlEscape="false" class="form-control"/>
                <label class="Validform_checktip"></label>
            </td>
        </tr>
        <tr>
            <td class="width-15 active text-right">
                <label>备注:</label>
            </td>
            <td class="width-35">
                <form:textarea path="remarks" htmlEscape="false" class="form-control" style="height:100px;resize:none;"/>
                <label class="Validform_checktip"></label>
            </td>
        </tr>
        <tr>
            <td class="width-15 active text-right">
                <label><font color="red">*</font>附件:</label>
            </td>
            <td class="width-35">
                <input id="relateTable" type="hidden" value="qs_best_picture_of_month"/>
                <input id="relateFeild" type="hidden" value="attachment"/>
                <form:fileinput buttonText="选择附件" saveType="billId" multiple="true"
                                fileActionSettings="{showRemove: true,showUpload: false}"
                                isFormSubmit="true" uploadExtraFieldData="relateTable,relateFeild"
                                showUpload="false" showRemove="false" autoUpload="false"
                                fileInputWidth="100px" fileInputHeight="100px" path="attachment"
                                htmlEscape="false" class="form-control"/>
            </td>
        </tr>
        </tbody>
    </table>
</form:form>
<html:js name="bootstrap-fileinput"/>
<html:js name="simditor"/>

<script type="text/javascript">
    function checkFileUpload() {
        var files = $('#attachmentFile').fileinput('getFileStack');
        if(files.length === 0) {//无附件上传
            return false;
        } else {
            return true;
        }
    }
</script>

</body>
</html>