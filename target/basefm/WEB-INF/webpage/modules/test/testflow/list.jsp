<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>测试流程demo列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="测试流程demo">
<grid:grid id="testFlowGridId" url="${adminPath}/test/testflow/ajaxList" >
	<grid:column label="sys.common.key" hidden="true" name="id" width="100"/>
	<grid:column label="名称"  name="name" width="100"/>
	<grid:column label="编号"  name="number" width="100"/>
	<grid:column label="状态"  name="status" width="100" dict="billStatus"/>
	<grid:column label="备注"  name="remarks" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	<grid:toolbar btnclass="btn-info"  icon="fa-download"  onclick="submit()" title="提交流程" />	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript" src="${staticPath}/modules/activiti/js/workflow.js"></script>
<script type="text/javascript" src="${staticPath}/common/js/window.js"></script>

<script type="text/javascript">

/**提交流程测试*/
function submit(){
	debugger
	var modelKey = null;
	var menuId = window.localStorage? localStorage.getItem("nid"): Cookie.read("nid");
	var url = '${adminPath}//workflow/actmenuorg/findModelKey';
	$.ajax({
		   type: 'POST',
		   url: url ,
		   data: {menuId:menuId} ,
		   dataType: 'text',
		   success: function(data){
			   submitBill(data);
		   } 
	});
	
}

function submitBill(modelKey){
	var rowsData = $("#testFlowGridIdGrid").jqGrid('getGridParam','selarrrow');
	var id = rowsData[0];
	var row = $("#testFlowGridIdGrid").jqGrid('getRowData',id);
	if(row.status!=0){
		layer.msg('已提交的数据不能重复提交', {icon: 2}); 
		return;
	}
	var formUrl = '${adminPath}/test/testflow/{id}/update';
	formUrl = formUrl.replace("{id}",id);
	var params = {
			basePath:'${adminPath}',
			billId:id,
			key:modelKey,
			formUrl:formUrl,
			tableName:'testFlow',
		}
 	submitFlow(params);
}

</script>
</body>
</html>