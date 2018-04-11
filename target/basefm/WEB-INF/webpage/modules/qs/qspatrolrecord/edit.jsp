<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>巡视记录</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
    <style type="text/css">
         .a4{
              width: 100%;
	          height: 100%;
	          margin-left: auto;
	          margin-right: auto;
	          background-color: #fff;
	          padding-right: 30px;
	          padding-top: 10px;
	          padding-bottom: 40px;
	          padding-left: 50px;
            }
         .num{text-align: right;font-size: 12px;}
         .mainTitle{font-size:20px;text-align: center;}
         .formTitle{font-size:25px;font-weight:bold;text-align: center;}
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
           text-align:left;
         }
         .tdinput-center{
           border: none;
           width: 100%;
           height: 100%;
           padding: 0;
           margin: 0;
           text-align:center;
         }
         table.table {
	       width: 100%;
	       height: auto;
           font-family: verdana,arial,sans-serif;
           font-size:11px;
           color:#333333;
           border-width: 1px;
           border-color: #666666;
           text-align: center;
           border-collapse: collapse;
         }
         table.table tr {
           border-width: 1px;
           padding: 8px;
           border-style: solid;
           border-color: #666666;
           background-color: #dedede;
         }
         table.table td {
           font-size: 8px;
           color: #000000;
           border-width: 1px;
           padding: 8px;
           height: 100%;
           border-style: solid;
           border-color: #666666;
           background-color: #ffffff;
         }
         table.table td.width-20{width: 20%;}
         table.table td.width-30{width: 30%;}
         table.table td.padding-ms{padding-top:2rem ;padding-bottom: 2rem;}
         table.table td.padding-lg{padding-top:3rem ;padding-bottom: 3rem;}
    </style>
</head>
<body class="white-bg"  formid="qsPatrolRecordForm">
   <form:form id="qsPatrolRecordForm" modelAttribute="data" method="post">
		<form:hidden path="id"/>
		<form:hidden path="orgId" value="${param.treeId}"/>
		<div class="a4">
			<div>
				<p class="num">表号:TJ-1</p>
				<p id="titlename" class="mainTitle"><font color="#ccc" style="font-size:20px">主标题未设置 </font></p>
			    <p id="titlefu" class="mainTitle"><font color="#ccc" style="font-size:20px">副标题未设置</font></p>
			    <p id="titlemk" class="formTitle"><font color="#ccc" style="font-size:25px">表单名称未设置</font></p>
				<p class="num">编号&#58;
					<input class="showBottonBorder" name="number" datatype="*" nullmsg="请填写编号！" 
					value="${data.number}"/>
					<label class="Validform_checktip"></label>
				</p>
			</div>
			<table class="table">
				<tr>
				    <td>施工单位</td>
				    <td>
				        <form:input path="constructionUnit" class="showBottonBorder tdinput-center" name="constructionUnit"  value="${param.constructionUnitString}" readonly="readonly"/>
				    </td>
				    <td class="width-20">合同段</td>
				    <td class="width-30">
				    	<form:input path="contractNumber" class="showBottonBorder tdinput-center" style="text-align: center" name="contractNumber"  value="${param.contractNumberString}" readonly="readonly"/>
				    </td>
				</tr>
				<tr>
					<td>巡视人</td>
					<td>
						<input class="showBottonBorder tdinput-center" style="text-align: center" name="patrolBy" datatype="*" nullmsg="请填写巡视人！" 
						value="${data.patrolBy}"/>
						<label class="Validform_checktip"></label>
					</td>
					<td>巡视时间</td>
					<td>
						<form:input path="patrolDate" class="showBottonBorder tdinput-center form-control layer-date" name="patrolDate"  datatype="*" nullmsg="请选择日期！" 
						placeholder=" 年  月  日"  datefmt="yyyy-MM-dd" 
						readonly="true" style="background-color:#fff"
						onclick="laydate({istime: true, istoday:true, format: 'YYYY-MM-DD'})"/>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">巡视的范围</td>
					<td colspan="3">
						<textarea class="showBottonBorder tdinput" name="patrolRange" datatype="*" nullmsg="请填写巡视的范围！">${data.patrolRange}</textarea>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">主要施工情况</td>
					<td colspan="3">
						<textarea class="showBottonBorder tdinput" name="mainConstructionSituation">${data.mainConstructionSituation}
						</textarea>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">质量、安全、环保等问题</td>
					<td colspan="3">
						<textarea class="showBottonBorder tdinput" name="mainProblem" >${data.mainProblem}</textarea>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">发现的问题及处理意见</td>
					<td colspan="3">
						<textarea class="showBottonBorder tdinput" name="problemHandleOpinion">${data.problemHandleOpinion}</textarea>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">备注</td>
					<td colspan="3">
						<textarea class="showBottonBorder tdinput" name="remarks">${data.remarks}</textarea>
					</td>
				</tr>
				<tr>
					<td class="padding-lg">附件</td>
					<td colspan="3" style="text-align: left;">
						<input id="relateTable" type="hidden" value="qs_patrol_record"/>
						<input id="relateFeild" type="hidden" value="attachment"/>
			            <form:fileinput buttonText="选择附件" saveType="billId" multiple="true"
                             fileActionSettings="{showRemove: true,showUpload: false}"
                             isFormSubmit="true" uploadExtraFieldData="relateTable,relateFeild"
                             showUpload="false" showRemove="false" autoUpload="false"
                             fileInputWidth="100px"  fileInputHeight="100px"  path="attachment"
                             htmlEscape="false" class="form-control"/>
					</td>
				</tr>
			</table>
		</div>
		</form:form>
		<html:js name="bootstrap-fileinput" />
        <html:js name="simditor" />
	</body>
</html>
