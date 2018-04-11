<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>品质工程制度制定</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="qsQualityFormulationForm">
    <form:form id="qsQualityFormulationForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orgId" value="${orgId}"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		        <tr>
					<td  class="width-10 active text-right">	
		              <font color="red">*</font><label>标题:</label>
		            </td>
					<td class="width-40">
						<form:input path="title" class="form-control"  datatype="*" nullmsg="请输入标题！" ajaxurl="${adminPath}/qs/qsqualityformulation/validate?orgId=${orgId}" validErrorMsg="该标题已存在"  />
						<label class="Validform_checktip"></label>
					</td>
		  		</tr>
		  		<tr>
					<td  class="width-10 active text-right">	
		              <font color="red">*</font><label>上传单位:</label>
		            </td>
					<td class="width-40">
						<form:input path="orgName" class="form-control" readonly="true" datatype="*" nullmsg="请选择上传单位！"  value="${orgName}" />
						<label class="Validform_checktip"></label>
					</td>
		  		</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label>备注信息:</label>
		            </td>
					<td class="width-40">
						<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control "/>
						<label class="Validform_checktip"></label>
					</td>
		  		</tr>
		  		<tr>
					<td  class="width-10 active text-right">	
		              <label>附件上传:</label>
		            </td>
					<td class="width-40" colspan="3">
						<input id="relateTable" type="hidden" value="qs_quality_formulation"/>
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
<html:js name="iCheck,Validform,markdown,syntaxhighlighter" />
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
</html>