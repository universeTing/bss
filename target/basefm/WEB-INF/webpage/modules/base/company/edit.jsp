<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>公司资料</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="companyForm">
    <form:form id="companyForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>公司编号:</label>
		            </td>
					<td class="width-35">
						<form:input path="number" htmlEscape="false" class="form-control"  datatype="*1-50"  nullmsg="请填写公司编号" 
							ajaxurl="${adminPath}/base/company/validate"  />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>公司名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" class="form-control"  datatype="*1-50"  nullmsg="请填写公司名称" 
							ajaxurl="${adminPath}/base/company/validate" />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>公司类型:</label>
		            </td>
					<td class="width-35">
						<form:select path="companyType" htmlEscape="false" class="form-control"  dict="companyType"  datatype="*"  nullmsg="请选择公司类型"  />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>公司简称:</label>
		            </td>
					<td class="width-35">
						<form:input path="simpleName" htmlEscape="false" class="form-control"  ignore="true" datatype="*0-50"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>公司地址:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:input path="address" htmlEscape="false" class="form-control" ignore="true" datatype="*0-100"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>法人:</label>
		            </td>
					<td class="width-35">
						<form:input path="corporation" htmlEscape="false" class="form-control"  ignore="true" datatype="*0-20"    />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>联系人:</label>
		            </td>
					<td class="width-35">
						<form:input path="contacts" htmlEscape="false" class="form-control"  ignore="true" datatype="*0-20"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>联系电话:</label>
		            </td>
					<td class="width-35">
						<form:input path="contactsPhone" htmlEscape="false" class="form-control"  datatype="m" ignore="true" />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>电子邮箱:</label>
		            </td>
					<td class="width-35">
						<form:input path="email" htmlEscape="false" class="form-control"  datatype="e"  ignore="true"  />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>邮政编码:</label>
		            </td>
					<td class="width-35">
						<form:input path="postCode" htmlEscape="false" class="form-control"  datatype="p"   ignore="true"  />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>传真:</label>
		            </td>
					<td class="width-35">
						<form:input path="fax" htmlEscape="false" class="form-control"  ignore="true" datatype="*0-20"/>
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>工商号:</label>
		            </td>
					<td class="width-35">
						<form:input path="indComNumber" htmlEscape="false" class="form-control"   ignore="true" datatype="*0-50"  />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>税务号:</label>
		            </td>
					<td class="width-35">
						<form:input path="taxNumber" htmlEscape="false" class="form-control"   ignore="true" datatype="*0-50"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>描述:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="description" htmlEscape="false" class="form-control"  ignore="true" datatype="*0-255" />
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