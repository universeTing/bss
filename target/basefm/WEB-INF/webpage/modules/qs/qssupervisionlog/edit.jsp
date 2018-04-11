<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>监理日志</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
    <link rel="stylesheet" type="text/css" href="${staticPath}/base/css/table.style.css" />
    <style type="text/css">
         .showBottonBorder{
            border: none;
            border-bottom: 1px solid black;
            outline-style: none;
            text-align: center;
         }
         .tdinput{
           border: none;
           width: 100%;
           height: 100%;
           padding: 0;
           margin: 0;
         }
         
         .tableId{
            width: 100px;
         }
         .table tr td {
		    border-width: 1px;
		    padding: 0px;
		    border-style: solid;
		    border-color: #666666;
		    background-color: #ffffff;
		}
		.a4{
		   width:900px;
		}
        .table-head-text{
        	color:#000;
        }
        .showTopLine{
         	border: none;
            border-top: 1px solid black;
        }
    </style>
</head>

<body class="white-bg"  formid="qsSupervisionLogForm">
    <form:form id="qsSupervisionLogForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="a4">
			<div class="table-head">
				<p class="num">表号&#58;<input class="showBottonBorder tableId" name="tableId"  value="${data.tableId}"/> </p>
				<p>
					<input class="showBottonBorder" name="projectTitle" datatype="*"  nullmsg="请填写工程项目名！" 
					value="${data.projectTitle}"/>工程项目 
					<label class="Validform_checktip"></label>
				</p>
				<p>
					<input class="showBottonBorder" name="subTitle" datatype="*"  nullmsg="请填写工程项目副标题！" 
					 value="${data.subTitle}"/>
					 <label class="Validform_checktip"></label>
				</p>
				<div class="table-head-text">监理日志</div>
				<p class="num">编号&#58;
					<input class="showBottonBorder" name="number" datatype="*" nullmsg="请填写编号！" value="${data.number}"
					ajaxurl="${adminPath}/qs/qssupervisionlog/validate?orgId=${data.orgId}" 
					validErrorMsg="该编号已存在" /> 
					<label class="Validform_checktip"></label>
				</p>
			</div>
			<table class="table">
				<tr>
					<td class="width-15">监理机构</td>
					<td colspan="3"> 
						<form:input path="orgName" class="form-control" readonly="true" datatype="*" nullmsg="请选择上传单位！" />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				    <td class="width-15">记录人</td>
				    <td class="width-30">
				        <input class="showBottonBorder tdinput" name="recorder"  value="${data.recorder}" datatype="*" nullmsg="请填写记录人！"/>
				        <label class="Validform_checktip"></label>
				    </td>
				    <td class="width-15">日期</td>
				    <td class="width-30">
				    	<form:input path="date" htmlEscape="false" class="form-control layer-date" datatype="*" name="date" nullmsg="请选择日期！"
						placeholder="         年         月         日" datefmt="yyyy年MM月dd日"  readonly="true" style="background-color:#fff" 
						onclick="laydate({istime: true, istoday:true, format: 'YYYY年MM月DD日'})"/>
						<label class="Validform_checktip"></label>
				    </td>
				</tr>
				<tr>
					<td>审核人</td>
					<td>
						<input class="showBottonBorder tdinput" name="auditBy" value="${data.auditBy}" readonly="true" />
					</td>
					<td>天气情况</td>
					<td>
						<input class="showBottonBorder tdinput" name="weatherConditions" value="${data.weatherConditions}"/>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">主要施工情况</td>
					<td colspan="3">
						<form:textarea path="buildSituation" htmlEscape="false"	class="form-control" style="height:100px;resize:none;"/>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">监理主要工作</td>
					<td colspan="3">
						<form:textarea class="form-control" path="supervisonWork" htmlEscape="false" style="height:100px;resize:none;"/>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">发现的问题及处理结果</td>
					<td colspan="3">
						<form:textarea class="form-control" path="problemHandleResult" htmlEscape="false" style="height:100px;resize:none;"/>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">附件</td>
					<td colspan="3">
						<input id="relateTable" type="hidden" value="qs_aside_record"/>
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
					<td class="padding-lg">备注</td>
					<td colspan="3">
						<form:textarea class="form-control" path="remarks" htmlEscape="false" style="height:100px;resize:none;"/>
					</td>
				</tr>
			</table>
		</div> 
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
</html>