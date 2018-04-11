<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>监理日志列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js   name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="监理日志">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <input type="hidden" id="treeId" name="treeId"/>
    <input type="hidden" id="treeName" name="treeName"/>
	<div data-options="region:'west',split:true" style="width:20%;">
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<div data-options="region:'center'">
		<grid:grid id="qsSupervisionLogGridId" url="${adminPath}/qs/qssupervisionlog/ajaxList" sortname="createDate" sortorder="desc"
					gridSetting="{loadComplete:onloadFun,ondblClickRow : dbClickRowFun}"> 
			<grid:column label="sys.common.key" hidden="true" name="id" width="100"/>
			<grid:query name="orgId" queryMode="hidden" />
		    <grid:column label="编号"  name="number"  query="true"  queryMode="input"  condition="eq" />
		    <grid:column label="表号"  name="tableId" />
		    <grid:column label="监理机构"  name="orgName" />
		    <grid:column label="记录人"  name="recorder" />
		    <grid:column label="记录日期"  name="date" formatter="date" dateformat="HH:mm:ss"/>
			<grid:column label="审核人"   name="auditBy"/>
		    <grid:column label="审核日期"  name="auditDate" formatter="auditDate"/>
		    <grid:column label="附件"  name="opt" formatter="button" width="100"/>
		    <grid:column label="附件个数"  name="attachmentCount" width="100" hidden="true"/>
		    <grid:button exp="row.attachmentCount>0" title="查看附件"  groupname="opt" function="attachmentLook" 
		                        outclass="btn-info" innerclass="fa-search" 
		                   url="${adminPath}/sys/common/attachmentLook" />
		    <grid:column label="备注信息"  name="remarks" />
		    <grid:column label="创建人"  name="createByName" />
		    <grid:column label="创建日期"  name="createDate" formatter="date" dateformat="HH:mm:ss"/>
		    <grid:column label="审核状态"  name="auditStatus" dict="auditType"/>
		    
		    <shiro:hasPermission name="qs:qssupervisionlog:toCreate">
				<grid:toolbar btnclass="btn-green" icon="fa-plus"  onclick="toCreate()" title="创建"/>
			</shiro:hasPermission>
			<shiro:hasPermission name="qs:qssupervisionlog:toUpdate">
				<grid:toolbar btnclass="btn-success" icon="fa fa-file-text-o"  onclick="toUpdate()" title="修改"/>
			</shiro:hasPermission>
			<shiro:hasPermission name="qs:qssupervisionlog:toDelete">
				<grid:toolbar btnclass="btn-danger" icon="fa-trash-o"  function="toDelete" title="删除"/>
			</shiro:hasPermission>
			<shiro:hasPermission name="qs:qssupervisionlog:toPrint">
				<grid:toolbar btnclass="btn-blue" icon="fa-print"  onclick="toPrint()" title="打印"/>
			</shiro:hasPermission>
			<shiro:hasPermission name="qs:qssupervisionlog:changeAuditstatus">
				<grid:toolbar btnclass="btn-pink " icon="fa-plus"   title="提交"  
			              function="changeAuditstatus('${adminPath}/qs/qssupervisionlog/changeAuditStatus','3','qsSupervisionLogGridIdGrid')"/>
			</shiro:hasPermission>
			<shiro:hasPermission name="qs:qssupervisionlog:changeAuditstatus">
				<grid:toolbar btnclass="btn-violet  " icon="fa fa-file-text-o"  title="审核"
			              function="changeAuditstatus('${adminPath}/qs/qssupervisionlog/changeAuditStatus','4','qsSupervisionLogGridIdGrid')"/>
			</shiro:hasPermission>
			<shiro:hasPermission name="qs:qssupervisionlog:changeAuditstatus">
				<grid:toolbar btnclass="btn-yellow " icon="fa fa-file-text-o"   title="反审核"
			              function="changeAuditstatus('${adminPath}/qs/qssupervisionlog/changeAuditStatus','0','qsSupervisionLogGridIdGrid')"/>
			</shiro:hasPermission>
			<grid:toolbar function="search"/>
			<grid:toolbar function="reset"/>
		</grid:grid>
	</div>
</div>
<script type="text/javascript">


/**@function 改变审核的状态
 * @author Wangyuanting
 * @date 2018-4-2
 * @param url    提交路径
 * @param value  要改变的状态值
 * @param gridId 列表id
 * 
 */
function changeAuditstatus(url,value,gridId){
	var rowsData = $("#"+gridId).jqGrid('getGridParam','selarrrow');
	var multiselect=$("#"+gridId).jqGrid('getGridParam','multiselect');
	var rowData= $("#"+gridId).jqGrid('getGridParam','selrow');
	var msg = "";
	if(!multiselect){
		if(rowData){
			rowsData[0]=rowData;
		}
	}
	if (!rowsData || rowsData.length==0) {
		top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
		return; 
	}
	var ids = [];
	if(value==3){//提交
		for(var i=0;i<rowsData.length;i++){
			//获取当前行对象
			var rowObject = $("#"+gridId).jqGrid('getRowData',rowsData[i]);
			if(rowObject.auditStatus==0){
				ids.push(rowsData[i]);
			}
		}
	    if(ids.length==0){
	    	top.layer.alert('数据只能在【保存】状态下提交!', {icon: 0, title:'警告'});
			return;
	    }
		msg="您确定要提交该数据吗？";
	}else if(value==4){//审核
		for(var i=0;i<rowsData.length;i++){
			//获取当前行对象
			var rowObject = $("#"+gridId).jqGrid('getRowData',rowsData[i]);
			if(rowObject.auditStatus==3){
				ids.push(rowsData[i]);
			}
		}
		if(ids.length==0){
			top.layer.alert('数据只能在【审核中】状态下审核!', {icon: 0, title:'警告'});
			return;
		}
		msg="您确定要审核该数据吗？";
		
	}else if(value==0){//反审核
		for(var i=0;i<rowsData.length;i++){
			//获取当前行对象
			var rowObject = $("#"+gridId).jqGrid('getRowData',rowsData[i]);
			if(rowObject.auditStatus==4){
				ids.push(rowsData[i]);
			}
		}
		if(ids.length==0){
			top.layer.alert('数据只能在【已审核】状态下审核!', {icon: 0, title:'警告'});
			return;
		}
		msg="您确定要审核该数据吗？";
	}
	var id = rowsData[0];
	swal({
		title: "提示",
		text: msg+"请谨慎操作！",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "确定",
		closeOnConfirm: false,
		cancelButtonText: "取消",
	}, function () {
		$.ajax({
			url : url,
			type : 'post',
			data : {ids : ids.join(','),"value":value},
			cache : false,
			success : function(data) {
				if (data.ret==0) {
					var msg = data.msg;
					swal("提示！", msg, "success");
					//刷新表单
					refreshTable(gridId);
				}else{
					var msg = data.msg;
					swal("提示！", msg, "error");
				}
			}
		});
	});
}

   /**
     *@function 显示组织树
     *@author Wangyuanting
     *@date 2018-3-30
     */
	var treeObj;
	var setting = {
			async: {
				enable: true,
				url:"${adminPath}/sys/organization/orgTree",
				autoParam:["id=nodeid"],
				dataFilter: filter
			},
			callback: {
				onClick: onClick
			}
		};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	
	/**
	  *@function 树节点的点击事件
	  *@author Wangyuanting
	  *@date 2018-3-30
	  */
	function onClick(event, treeId, treeNode, clickFlag) {
		 clearAllVal();
		 $("input[name='treeId']").val(treeNode.id);
		 $("input[name='orgId']").val(treeNode.id);
		 $("input[name='treeName']").val(treeNode.name);
	     search('qsSupervisionLogGridIdGrid');
	}
	
	/**
	  *@function 清空值
	  *@author Wangyuanting
	  *@date 2018-3-30
	  */
	function clearAllVal(){
		 $("input[name='treeId']").val("");
		 $("input[name='orgId']").val("");
		 $("input[name='treeName']").val("");
	}
	
	$(document).ready(function(){
		treeObj=$.fn.zTree.init($("#treeObj"), setting);
	});
	
	/**
	*@function 双击查看数据
	*@author  Wangyuanting
	*@date 2018-4-2
	*/
	function dbClickRowFun(rowid,iRow,icol,e){
		var url = '${adminPath}/qs/qssupervisionlog/' + rowid + '/update';
		openDialogDetail('查看',url,"1000px","900px");
	}
	
	/**
	  *@function 加载样式
	  *@author Wangyuanting
	  *@date 2018-3-30
	  */
	function onloadFun(){
		var winHeight = $("#page-wrapper").height()-100;
		try{
			$(".portlet-body,.portlet-body>div").css("height",winHeight+"px");
			$(".ui-jqgrid-view").css("height",winHeight-150+"px")
		}catch(e){}
	}
	
	/**
	  *@function 附件查看
	  *@author Wangyuanting
	  *@date 2018-3-30
	  */
	function attachmentLook(title,url,gridId,id,width,height){
		rowDialogDetailRefresh(title,url,gridId,id,"auto","auto");
	}
	
	/**
	  *@function 新增-监理日志
	  *@author Wangyuanting
	  *@date 2018-3-30
	  */
	function toCreate(){
		var treeId = $("input[name='treeId']").val();
		var treeName = $("input[name='treeName']").val();
		var url = "${adminPath}/qs/qssupervisionlog/create?orgId="+treeId+"&treeName="+treeName;
		if(treeId!=""){  //当树节点id为空的时候，弹出提示信息
			debugger;
			create("新增-监理日志",url,"qsSupervisionLogGridIdGrid","1000px","900px");
		}else{
			top.layer.alert("请选择组织单位！", {icon: 0, title:'提示'});
		}			
	}
	/**
	  *@function 修改-监理日志  加大弹窗的大小
	  *@author Wangyuanting
	  *@date 2018-3-30
	  */
	function toUpdate(){
		var url = "${adminPath}/qs/qssupervisionlog/{id}/update";
		update("修改-监理日志",url,"qsSupervisionLogGridIdGrid","1000px","900px");
	}
	
	/**
	  *@function 批量删除数据    进行判断已经审核的数据是不能删除的
	  *@author Wangyuanting
	  *@date 2018-4-2
	  */
	function toDelete(title,url,gridId){
		var ids = [];
		var rows =$("#"+gridId).jqGrid('getGridParam','selarrrow');//获取当前选中的行数
		var rowData= $("#"+gridId).jqGrid('getGridParam','selrow');//获取选中行的对象
		var multiselect=$("#"+gridId).jqGrid('getGridParam','multiselect');//获取选中的状态
	    if (rows.length > 0) {
	    	for(var i=0; i<rows.length;i++){
	    		//获取当前行的对象
	    		var rowData = $("#"+gridId).jqGrid('getRowData',rows[i]);
	    		if(rowData.auditStatus==4){
	    			top.layer.alert('已审核数据不能删除!', {icon: 0, title:'警告'});
	    			return;
	    		}
	    	}
	    	//调用删除方法    删除功能放在for外面是当选中的数据只要有一条数据被审核了则选中的数据都不能删除
			batchDelete(title,url,gridId);
	    	
	    }else{
		    top.layer.alert('请选择需要删除的数据!', {icon: 0, title:'警告'});
		    return;
		}
	}
	
	/**格式化时间*/
	function qsSupervisionLogGridIdAuditDateFormatter(value,option,row){
		if(value=="1970-01-01 00:00:00" || value==null){
			return "";
		}
		debugger;
		return value.substring(0,10);
	}
</script>
</body>
</html>