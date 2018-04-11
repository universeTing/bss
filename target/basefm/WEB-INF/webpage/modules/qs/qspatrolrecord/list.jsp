<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>巡视记录列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js  name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="巡视记录">
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
        <grid:grid id="qsPatrolRecordGridId" url="${adminPath}/qs/qspatrolrecord/ajaxList" 
                   gridSetting="{loadComplete:onloadFun,ondblClickRow:dbClickRowFun}" sortname="createDate" sortorder="desc">
            <grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
            <grid:column label="编号" name="number" query="true" condition="like"/>
            <grid:column label="表号"  name="tableNumber" />
            <grid:column label="施工单位"  name="constructionUnit" />
            <grid:column label="合同段号"  name="contractNumber" />
            <grid:column label="巡视人"  name="patrolBy" />
            <grid:column label="巡视时间"  name="patrolDate" />
            <grid:column label="附件"  name="opt" formatter="button" width="120" align="center"/>
            <grid:column label="备注"  name="remarks" />
            <grid:column label="创建者"  name="createBy.realname" />
            <grid:column label="创建日期"  name="createDate"  formatter="date" dateformat="HH:mm:ss"/>
            <grid:column label="附件个数"  name="attachmentCount" width="100" hidden="true"/>
	        <grid:button exp="row.attachmentCount>0" title="查看附件" groupname="opt" function="attachmentLook" 
	                        outclass="btn-info" innerclass="fa-search" 
	                        url="${adminPath}/sys/common/attachmentLook"/>
            <grid:query name="orgId" queryMode="hidden" />
            <shiro:hasPermission name="qs:qspatrolrecord:create">
		       <grid:toolbar btnclass="btn-green"  icon="fa-plus"  onclick="toCreate()" title="新增"/>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qspatrolrecord:update">
	           <grid:toolbar btnclass="btn-yellow" icon="fa-plus"  onclick="toUpdate()" title="修改"/>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qspatrolrecord:delete">
		       <grid:toolbar function="delete"/>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qspatrolrecord:print">
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
     *@author Huangqiling
     *@date 2018-3-29
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
	  *@author Huangqiling
	  *@date 2018-3-29
	  */
	function onClick(event, treeId, treeNode, clickFlag) {
		 clearAllVal();
		 $("input[name='treeId']").val(treeNode.id);
		 $("input[name='orgId']").val(treeNode.id);
		 $("input[name='treeName']").val(treeNode.name);
		 $("input[name='treeOrgType']").val(treeNode.type);
	     search('qsPatrolRecordGridIdGrid');
	}
	
	/**
	  *@function 清空值
	  *@author Huangqiling
	  *@date 2018-3-29
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
	
	// 双击行查看数据
    function dbClickRowFun(rowid,iRow,iCol,e) {
		var url='${adminPath}/qs/qspatrolrecord/'+ rowid+'/update?load=detail';
    	openDialogDetail('查看',url,"800px","950px") ;
    }
	
	/**
	  *@function 加载样式
	  *@author Huangqiling
	  *@date 2018-3-29
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
	  *@author Huangqiling
	  *@date 2018-3-29
	  */
	function attachmentLook(title,url,gridId,id,width,height){
		rowDialogDetailRefresh(title,url,gridId,id,"auto","auto");
	}
	
	/**
	  *@function 新增-巡视记录
	  *@author Huangqiling
	  *@date 2018-3-29
	  */
	function toCreate(){
		var treeId = $("input[name='treeId']").val();
		var treeName = $("input[name='treeName']").val();
		var type = $("input[name='treeOrgType']").val();
		if(treeId!=""&&type.indexOf("5") >= 0){ //当树节点id为空的时候，弹出提示信息
			var queryParams = {queryFields : 'useStatus,contractNumber,constructionUnit,', 'query.orgId||eq' : treeId};
			var json = getAjaxList('${adminPath}/base/basebidsegmentbasicinfo/ajaxListNOPage', queryParams);
			if (json.results[0] == null){
				top.layer.alert('您所选的标段未录入标段基本信息！', {icon: 0, title:'提示'});
			}else{
				var checked = json.results[0].useStatus == 1;
				var contractNumber = json.results[0].contractNumber;
				var constructionUnit = json.results[0].constructionUnit;
		        if (checked) {
		        	var url = "${adminPath}/qs/qspatrolrecord/create?treeId="+treeId+"&contractNumberString="+contractNumber+"&constructionUnitString="+constructionUnit;
		        	create("新增-巡视记录",url,"qsPatrolRecordGridIdGrid","800px","950px");
		        } else {
		            top.layer.alert('您所选的标段的标段基本信息未被启用！', {icon: 0, title:'提示'});
		        }
			}
			
		}else{
			top.layer.alert("请选择标段类型的组织！", {icon: 0, title:'提示'});
		}			
	}
	/**
	  *@function 修改-巡视记录
	  *@author Huangqiling
	  *@date 2018-3-30
	  */
	function toUpdate(){
		var url = "${adminPath}/qs/qspatrolrecord/{id}/update";
		update("修改-巡视记录",url,"qsPatrolRecordGridIdGrid","800px","950px");
	}
</script>
</body>
</html>