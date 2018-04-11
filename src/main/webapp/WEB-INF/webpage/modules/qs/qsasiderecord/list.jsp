<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>旁站记录列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js   name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="旁站记录">
<!-- 自定义js -->
<script src="${staticPath}/base/js/tools.js?v=1.0.0"></script>
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <input type="hidden" id="treeId" name="treeId"/>
    <input type="hidden" id="treeName" name="treeName"/>
    <input type="hidden" id="treeOrgType" name="treeOrgType"/>
	<div data-options="region:'west',split:true" style="width:20%;">
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<div data-options="region:'center'">
		<grid:grid id="qsAsideRecordGridId" url="${adminPath}/qs/qsasiderecord/ajaxList" sortname="createDate" sortorder="desc"
		 		   gridSetting="{loadComplete:onloadFun,ondblClickRow : dbClickRowFun}" >
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
		    <grid:column label="编号"  name="number"  width="100"/>
		    <grid:column label="合同段号" name="contractNumber" width="100"/>
		    <grid:column label="施工单位" name="constructionUnit" width="101"/>
		    <grid:column label="项目名称"  name="projectTitle" query="true"  queryMode="input"  condition="like" width="100"/>
		    <grid:column label="旁站人"  name="asideMan" width="100"/>
		    <grid:column label="旁站时间"  name="asideDate" formatter="date" dateformat="HH:mm:ss" width="100"/>
		    <grid:column label="附件"  name="opt" formatter="button" width="100"/>
		    <grid:column label="附件个数"  name="attachmentCount" width="100" hidden="true"/>
		    <grid:button exp="row.attachmentCount>0" title="查看附件"  groupname="opt" function="attachmentLook" 
		                        outclass="btn-info" innerclass="fa-search" 
		                   url="${adminPath}/sys/common/attachmentLook" />
		     <grid:query name="orgId" queryMode="hidden" />
		    <grid:column label="备注"  name="remarks" width="100"/>
		    <grid:column label="创建人" name="createByName" width="100"/>
		    <grid:column label="创建日期" name="createDate" formatter="date" dateformat="HH:mm:ss" width="100"/>
		    
		    <shiro:hasPermission name="qs:qsasiderecord:create">
				<grid:toolbar btnclass="btn-green" icon="fa-plus"  onclick="toCreate()" title="新增"/>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="qs:qsasiderecord:delete">
				<grid:toolbar function="delete"/>
			</shiro:hasPermission>
			<shiro:hasPermission name="qs:qsasiderecord:toUpdate">
				<grid:toolbar btnclass="btn-yellow" icon="fa-plus"  onclick="toUpdate()" title="修改"/>
			</shiro:hasPermission>
			<shiro:hasPermission name="qs:qsasiderecord:print">
				<grid:toolbar btnclass="btn-green" icon="fa-print"  onclick="print()" title="打印"/>
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
     *@date 2018-3-28
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
	  *@date 2018-3-28
	  */
	function onClick(event, treeId, treeNode, clickFlag) {
		 clearAllVal();
		 $("input[name='treeId']").val(treeNode.id);
		 $("input[name='orgId']").val(treeNode.id);
		 $("input[name='treeName']").val(treeNode.name);
		 $("input[name='treeOrgType']").val(treeNode.type);
	     search('qsAsideRecordGridIdGrid');
	}
	
	/**
	  *@function 清空值
	  *@author Wangyuanting
	  *@date 2018-3-28
	  */
	function clearAllVal(){
		 $("input[name='treeId']").val("");
		 $("input[name='orgId']").val("");
		 $("input[name='treeName']").val("");
		 $("input[name='treeOrgType']").val("");
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
		var url = '${adminPath}/qs/qsasiderecord/' + rowid + '/update';
		openDialogDetail('查看',url,"1000px","900px");
	}
	
	/**
	  *@function 加载样式
	  *@author Wangyuanting
	  *@date 2018-3-28
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
	  *@date 2018-3-28
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
		var url = '${adminPath}/qs/qsasiderecord/' + rowid + '/update';
		openDialogDetail('查看',url,"1000px","900px");
	}
	
	
	/**
	  *@function 新增-旁站记录
	  *@author Wangyuanting
	  *@date 2018-3-28
	  */
	function toCreate(){
		var treeId = $("input[name='treeId']").val();
		var treeName = $("input[name='treeName']").val();
		var type = $("input[name='treeOrgType']").val();
		if(treeId!=""&&type.indexOf("5") >= 0){ //当树节点id为空的时候，弹出提示信息并且组织树的类型大于等于0时不能新增数据
			var queryParams = {queryFields : 'useStatus,contractNumber,constructionUnit,', 'query.orgId||eq' : treeId};
		    //请求获取合同号和施工单位的url路径
			var json = getAjaxList('${adminPath}/base/basebidsegmentbasicinfo/ajaxListNOPage', queryParams);
			if (json.results[0] == null){
				top.layer.alert('您所选的标段未录入标段基本信息！', {icon: 0, title:'提示'});
			}else{
				var checked = json.results[0].useStatus == 1;//获取检查该标段的合同数据是否启用。
				var contractNumber = json.results[0].contractNumber;
				var constructionUnit = json.results[0].constructionUnit;
				//获取标段基本信息id
				var bidId = json.results[0].id;
		        if (checked) {
		        	//打开新增页面
		        	var url = "${adminPath}/qs/qsasiderecord/create?orgId="+treeId+"&contractNumberString="+contractNumber+"&constructionUnitString="+constructionUnit+"&bidId="+bidId;
		        	create("新增-旁站记录",url,"qsAsideRecordGridIdGrid","1000px","950px");
		        } else {
		            top.layer.alert('您所选的标段的标段基本信息未被启用！', {icon: 0, title:'提示'});
		        }
			}
		}else{
			top.layer.alert("请选择标段类型的组织！", {icon: 0, title:'提示'});
		}			
	}
	

	/**
	  *@function 修改-旁站记录  加大弹窗的大小
	  *@author Wangyuanting
	  *@date 2018-3-29
	  */
	function toUpdate(){
		var url = "${adminPath}/qs/qsasiderecord/{id}/update";
		update("修改-旁站记录",url,"qsAsideRecordGridIdGrid","1000px","900px");
	}
	
</script>
</body>
</html>