<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>qs_aside_record</title>
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
<body class="white-bg"  formid="qsAsideRecordForm">
   <form:form id="qsAsideRecordForm" modelAttribute="data" method="post" class="form-horizontal">
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
				<div class="table-head-text">旁站记录</div>
				<p class="num">编号&#58;
					<input class="showBottonBorder" name="number" datatype="*" nullmsg="请填写编号！" value="${data.number}"
					ajaxurl="${adminPath}/qs/qsasiderecord/validate?orgId=${data.orgId}" 
					validErrorMsg="该编号已存在" /> 
					<label class="Validform_checktip"></label>
				</p>
			</div>
			<table class="table">
				<tr>
				    <td class="width-15">施工单位</td>
				    <td>
				        <form:input path="constructionUnit" class="showBottonBorder tdinput" name="constructionUnit"  value="${param.constructionUnitString}" readonly="readonly"/>
				    </td>
				    <td class="width-15">合同段</td>
				    <td class="width-30">
				    	<form:input path="contractNumber" class="showBottonBorder tdinput"  readonly="readonly"  style="text-align: center" name="contractNumber"  value="${param.contractNumberString}" />
				    </td>
				</tr>
				<tr>
					<td>旁站人</td>
					<td>
						<input class="showBottonBorder tdinput" name="asideMan" datatype="*" nullmsg="请填写旁站人！" 
						value="${data.asideMan}"/>
						<label class="Validform_checktip"></label>
					</td>
					<td>旁站时间</td>
					<td>
						<form:input path="asideDate" htmlEscape="false" class="form-control layer-date" datatype="*" name="asideDate" nullmsg="请选择日期！"
						placeholder=" 年 月 日" datefmt="yyyy年MM月dd日"  readonly="true" style="background-color:#fff" 
						onclick="laydate({istime: true, istoday:true, format: 'YYYY年MM月DD日'})"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">旁站项目</td>
					<td colspan="3">
						<form:textarea path="asideProject" htmlEscape="false" datatype="*" nullmsg="请输入旁站项目名称！" 
						class="form-control" style="height:100px;resize:none;"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">施工过程简述</td>
					<td colspan="3">
						<form:textarea class="form-control" path="processDescription" htmlEscape="false" style="height:100px;resize:none;"/>
						</textarea>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">旁站工作情况</td>
					<td colspan="3">
						<form:textarea class="form-control" path="asideSituation" htmlEscape="false" style="height:100px;resize:none;"/>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">主要数据记录</td>
					<td colspan="3">
						<form:textarea class="form-control" path="mainData" htmlEscape="false" style="height:100px;resize:none;"/>
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