<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>质量月例会会议纪要</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="qsMonthlyMeetingSummaryForm">
    <form:form id="qsMonthlyMeetingSummaryForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-10 active text-right">	
		              <label><font color="red">*</font>会议议题:</label>
		            </td>
					<td class="width-40">
						<form:input path="title" htmlEscape="false" class="form-control" datatype="*" nullmsg="请输入标题！" placeholder="请输入议题" />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label><font color="red">*</font>会议时间:</label>
		            </td>
					<td class="width-40">
						<form:input path="time" htmlEscape="false" class="form-control layer-date" datatype="*" nullmsg="请选择会议日期"
						placeholder="yyyy-mm-dd" datefmt="yyyy-MM-dd"  readonly="true" style="background-color:#fff"
						onclick="laydate({istime: true, istoday:true, format: 'YYYY-MM-DD'})"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label>会议地点:</label>
		            </td>
					<td class="width-40">
						<form:input path="location" htmlEscape="false" class="form-control"  placeholder="请输入地点"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label>参会人员:</label>
		            </td>
					<td class="width-40">
						<form:input path="participants" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label>附件上传:</label>
		            </td>
					<td class="width-40" colspan="3">
						<input id="relateTable" type="hidden" value="qs_monthly_meeting_summary"/>
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
					<td  class="width-10 active text-right">	
		              <label>备注信息:</label>
		            </td>
					<td class="width-40">
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
<!-- 全局js -->
<html:js name="iCheck,Validform,markdown,syntaxhighlighter" />
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
</html>