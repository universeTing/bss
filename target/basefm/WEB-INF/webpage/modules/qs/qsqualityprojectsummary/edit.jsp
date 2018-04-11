<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>品质工程总结</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="qsQualityProjectSummaryForm">
    <form:form id="qsQualityProjectSummaryForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   		<tr>
					<td  class="width-15 active text-right">	
		              <label>标题</label>
		            </td>
					<td class="width-35">
						<form:input path="title" htmlEscape="false" class="form-control" dataType="*"  nullmgs="请输入标题！" placeholder="请输入标题！"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <font color="red">*</font><label>上传单位:</label>
		            </td>
					<td class="width-40">
						<form:input path="orgName" class="form-control" readonly="false" datatype="*" nullmsg="请选择上传单位！" />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>备注信息:</label>
		            </td>
					<td class="width-35">
						<form:input path="remarks" htmlEscape="false" class="form-control" dataType="*"  nullmgs="请输入标题！" placeholder="请输入 备注！"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>附件:</label>
		            </td>
					<td class="width-35">
						<input id="relateTable" type="hidden" value="qs_quality_project_summary"/>
						<input id="relateFeild" type="hidden" value="attachment"/>
						<form:fileinput buttonText="选择附件" saveType="billId" multiple="true"
						            fileActionSettings="{showRemove: true,showUpload: false}" 
						            isFormSubmit="true" uploadExtraFieldData="relateTable,relateFeild"
						            showUpload="false" showRemove="false" autoUpload="false" 
						            fileInputWidth="100px"  fileInputHeight="100px"  path="attachment" 
						            htmlEscape="false" class="form-control"/>	
					</td>
				</tr>
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
<!-- 全局js -->
<html:js name="iCheck,Validform,markdown,syntaxhighlighter"/>
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
</html>