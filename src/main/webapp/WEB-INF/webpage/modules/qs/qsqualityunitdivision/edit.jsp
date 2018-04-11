<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>质量单元划分</title>
    <meta name="decorator" content="form"/> 
    <html:css name="bootstrap-fileinput" />
    <html:js name="bootstrap-fileinput" />
    <html:css name="simditor" />
    <html:js name="simditor" />
    <html:css  name="iCheck,Validform"/>
    <html:js  name="iCheck,Validform"/>
</head>

<body class="white-bg"  formid="qsQualityUnitDivisionForm">
    <form:form id="qsQualityUnitDivisionForm"  modelAttribute="data"  method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="parentId"/>
		<form:hidden path="orgId"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
			  <tr>
		         <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>单元编码:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="code" class="form-control " datatype="*" nullmsg="请输入单元编码！" htmlEscape="false" 
		             ajaxurl="${adminPath}/qs/qsqualityunitdivision/validate?orgId=${data.orgId}" validErrorMsg="该编码已存在" />
		             <label class="Validform_checktip"></label>
		         </td>
		          <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>单元名称:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="name" class="form-control " datatype="*" nullmsg="请输入单元名称！" htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>工程类型:</label>
		            </td>
					<td class="width-35">
						<form:select path="engineerType" htmlEscape="false" class="form-control" 
						 dict="projecttype" delimiter="&nbsp;&nbsp;" defaultValue="1"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>节点类型:</label>
		            </td>
					<td class="width-35">
						<form:select path="nodePointType" htmlEscape="false" class="form-control"
						 dict="nodetype" delimiter="&nbsp;&nbsp;" defaultValue="1"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>是否重点工程:</label>
		            </td>
					<td class="width-35" colspan="3">
		             <form:radiobuttons path="keyProject" class="width-35"
								 dict="keyProject" delimiter="&nbsp;&nbsp;"
								 htmlEscape="false" defaultValue="0"
								 cssClass="i-checks required" />
		              <label class="Validform_checktip"></label>
		         </td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>开工日期:</label>
		            </td>
					<td class="width-35">
						<form:input path="startDate" htmlEscape="false" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"       />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>完工日期:</label>
		            </td>
					<td class="width-35">
						<form:input path="endDate" htmlEscape="false" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"       />
						<label class="Validform_checktip"></label>
					</td>
				</tr> 
				<tr>
					<td  class="width-15 active text-right">	
		              <label>单位:</label>
		            </td>
					<td class="width-35">
						<form:select path="unit" htmlEscape="false" class="form-control"   
						 dict="unitType" delimiter="&nbsp;&nbsp;" defaultValue="null"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>数量:</label>
		            </td>
					<td class="width-35">
						<form:input path="number" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>备注信息:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control "/>
						<label class="Validform_checktip"></label>
					</td>
		  		</tr>
		   </tbody>
		</table>   
	</form:form>
</body>
</html>