<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>质量培训教育</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="qsQualityTrainingEducationForm">
    <form:form id="qsQualityTrainingEducationForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
			   <tr>
				   <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>标题:</label>
			       </td>
					<td class="width-35">
						<form:input path="title" htmlEscape="false" class="form-control"   dataType="*" nullmgs="请输入标题！" placeholder="请输入标题"   />
						<label class="Validform_checktip"></label>
					</td>
			   </tr>
			   <tr>
					<td  class="width-10 active text-right">	
		              <label>附件上传:</label>
		            </td>
					<td class="width-40" colspan="3">
						<input id="relateTable" type="hidden" value="qs_quality_training_education"/>
						<input id="relateFeild" type="hidden" value="attachment"/>
						<form:fileinput buttonText="选择附件" saveType="billId" multiple="true"
						            fileActionSettings="{showRemove: true,showUpload: false}" 
						            isFormSubmit="true" uploadExtraFieldData="relateTable,relateFeild"
						            showUpload="false" showRemove="false" autoUpload="false" 
						            fileInputWidth="100px"  fileInputHeight="100px"  path="attachment" 
						            htmlEscape="false" class="form-control"/>	 
					 </td>
				</tr>
		        <tr>
					<td  class="width-15 active text-right">	
		              <label>备注信息:</label>
		            </td>
					<td class="width-35">
						<form:textarea path="remarks" rows="4" maxlength="200" htmlEscape="false" class="form-control"   placeholder="请输入备注"   />
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
</html>