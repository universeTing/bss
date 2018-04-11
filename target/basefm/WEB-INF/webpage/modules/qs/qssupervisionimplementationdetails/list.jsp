<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>监理实施细则列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js   name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="监理实施细则">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <input type="hidden" id="treeId" name="treeId"/>
    <input type="hidden" id="treeName" name="treeName"/>
	<div data-options="region:'west',split:true" style="width:20%;">
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<div data-options="region:'center'">
		<grid:grid id="qsSupervisionImplementationDetailsGridId" url="${adminPath}/qs/qssupervisionimplementationdetails/ajaxList"
					gridSetting="{loadComplete:onloadFun,ondblClickRow : dbClickRowFun}" sortname="createDate" sortorder="desc">
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
		    <grid:column label="标题"  name="title"  query="true" condition="like" width="100"/>
		    <grid:column label="上传单位"  name="orgName" width="100"/>
		    <grid:column label="备注"  name="remarks" width="100"/>
		    <grid:column label="附件"  name="opt" formatter="button" width="60"/>
		    <grid:column label="创建人"  name="createByName" width="100"/>
		    <grid:column label="创建日期"  name="createDate" width="100" formatter="date" dateformat='yyyy-MM-dd'/>
		    <grid:query name="orgId" queryMode="hidden" />
		    <grid:column label="附件个数"  name="attachmentCount" width="100" hidden="true"/>
		    <grid:button exp="row.attachmentCount>0" title="查看附件"  groupname="opt" function="attachmentLook" 
		                        outclass="btn-info" innerclass="fa-search" 
		                        url="${adminPath}/sys/common/attachmentLook"/>
		    <shiro:hasPermission name="qs:qssupervisionimplementationdetails:toCreate">
		   		<grid:toolbar btnclass="btn-green"  icon="fa-plus"  onclick="toCreate()" title="新增"/>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="qs:qssupervisionimplementationdetails:update">
				<grid:toolbar function="update"/>
			</shiro:hasPermission>
			<shiro:hasPermission name="qs:qssupervisionimplementationdetails:delete">
				<grid:toolbar function="delete"/>
			</shiro:hasPermission>
			<grid:toolbar function="search"/>
			<grid:toolbar function="reset"/>
		</grid:grid>
	</div>
</div>
<script type="text/javascript">
   /**
     *@function 显示组织树
     *@author Wangyuanting
     *@date 2018-3-7
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
	  *@date 2018-3-7
	  */
	function onClick(event, treeId, treeNode, clickFlag) {
		 clearAllVal();
		 $("input[name='treeId']").val(treeNode.id);
		 $("input[name='orgId']").val(treeNode.id);
		 $("input[name='treeName']").val(treeNode.name);
	     search('qsSupervisionImplementationDetailsGridIdGrid');
	}
	
	/**
	  *@function 清空值
	  *@author Wangyuanting
	  *@date 2018-3-7
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
	  *@function 加载样式
	  *@author Wangyuanting
	  *@date 2018-3-7
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
	  *@date 2018-3-7
	  */
	function attachmentLook(title,url,gridId,id,width,height){
		rowDialogDetailRefresh(title,url,gridId,id,"auto","auto");
	}
	
	/**
	*@function 双击查看数据
	*@author  Wangyuanting
	*@date 2018-4-2
	*/
	function dbClickRowFun(rowid,iRow,icol,e){
		var url = '${adminPath}/qs/qssupervisionimplementationdetails/' + rowid + '/update';
		openDialogDetail('查看',url,"700px","500px");
	}
	
	/**
	  *@function 新增-实施细则
	  *@author Wangyuanting
	  *@date 2018-3-7
	  */
	function toCreate(){
		var treeId = $("input[name='treeId']").val();
		var treeName = $("input[name='treeName']").val();
		//var url = "${adminPath}/qs/qssupervisionimplementationdetails/create?treeId="+treeId+"&treeName="+treeName;
		var url = "${adminPath}/qs/qssupervisionimplementationdetails/create?orgId="+treeId+"&treeName="+treeName;
		if(treeId!=""){  //当树节点id为空的时候，弹出提示信息
			create("新增-实施细则",url,"qsSupervisionImplementationDetailsGridIdGrid","800px","450px");
		}else{
			top.layer.alert("请选择组织单位！", {icon: 0, title:'提示'});
		}			
	}
</script>

</body>
</html>