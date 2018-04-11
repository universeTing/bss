<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>质量单元划分列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js  name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="质量单元划分">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <input type="hidden" id="treePId" name="treePId"/>
	<input type="hidden" id="treeIsQsUnit" name="treeIsQsUnit"/>
	<input type="hidden" id="treeOrgType" name="treeOrgType"/>
	<input type="hidden" id="treeOrgName" name="treeOrgName"/>
	<div data-options="region:'west',split:true" style="width:20%;">
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<div data-options="region:'center'">
	    <grid:grid id="qsQualityUnitDivisionGridId" async="true" treeGrid="true"  expandColumn="name" multiselect="true"
	               url="${adminPath}/qs/qsqualityunitdivision/ajaxTreeList" gridSetting="{loadComplete:onloadFun}">
	        <grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
            <grid:column label="编码"  name="code"  query="true" condition="like"/>
            <grid:column label="名称"  name="name" width="200" query="true" condition="like"/>
            <grid:column label="单位"  name="unit" dict="unitType" />
            <grid:column label="数量"  name="number" />
            <grid:column label="工程类型"  name="engineerType" dict="projecttype"/>
            <grid:column label="节点类型"  name="nodePointType" dict="nodetype"/>
            <grid:column label="上级编码"  name="parentCode" />
            <grid:column label="是否重点工程"  name="keyProject" dict="keyProject"/>
            <grid:column label="开工日期"  name="startDate" />
            <grid:column label="完工日期"  name="endDate" />
            <grid:column label="状态"  name="auditStatus" dict="auditType"/>
            <grid:column label="审核人"  name="auditBy.realname" />
            <grid:column label="审核日期"  name="auditDate" formatter="date" dateformat='yyyy-MM-dd' />
            <grid:column label="备注信息"  name="remarks" />
            <grid:column label="子节点个数"  name="hasChildren" hidden="true"/>
            <grid:query name="orgId"  condition="eq"   queryMode="hidden" />
	        <grid:query name="parentId"  condition="eq"   queryMode="hidden" />
	        <grid:query name="open"  condition="eq"   queryMode="hidden" />
	        <grid:query name="treePId"  condition="eq"   queryMode="hidden" />
	        <grid:query name="treeIsQsUnit"  condition="eq"   queryMode="hidden" />
	        <shiro:hasPermission name="qs:qsqualityunitdivision:create">
		       <grid:toolbar btnclass="btn-green"  icon="fa-plus"  onclick="toCreate()" title="新增"/>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qsqualityunitdivision:update">
	           <grid:toolbar btnclass="btn-success"  icon="fa-file-text-o"  onclick="toUpdate()" title="修改"/>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qsqualityunitdivision:delete">
		       <grid:toolbar btnclass="btn-danger"  icon="fa-trash-o"  onclick="deleteData()" title="删除" />
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qsqualityunitdivision:submit">
		       <grid:toolbar btnclass="btn-grey"  icon="fa-sign-in"  onclick="submit()" title="提交" />
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qsqualityunitdivision:audit">
		       <grid:toolbar btnclass="btn-info"  icon="fa-sign-in"  onclick="audit()" title="审核" />
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qsqualityunitdivision:antiaudit">
		       <grid:toolbar btnclass="btn-grey"  icon="fa-sign-in"  onclick="antiAudit()" title="反审核" />
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qsqualityunitdivision:importexcel">
		       <grid:toolbar btnclass="btn-info"  icon="fa-sign-in"  onclick="importExcel()" title="Excel导入" />
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qsqualityunitdivision:exportexcel">
		       <grid:toolbar btnclass="btn-grey"  icon="fa-sign-in"  onclick="exportExcel()" title="Excel导出" />
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qsqualityunitdivision:exportexcelmodel">
		       <grid:toolbar btnclass="btn-info"  icon="fa-sign-in"  onclick="exportExcelModel()" title="模板下载" />
	        </shiro:hasPermission>
	        <shiro:hasPermission name="qs:qsqualityunitdivision:showall">
		       <grid:toolbar btnclass="btn-grey"  icon="fa-sign-in"  onclick="showAll()" title="显示底层" />
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
     *@date 2018-3-15
     */
	var treeObj;
	var setting = {
			async: {
				enable: true,
				url:"${adminPath}/qs/qsqualityunitdivision/qsUnitTree",
				autoParam:["id=nodeid", "type=type", "isQsUnit=isQsUnit","isParent=isParent"],
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
	  *@date 2018-3-15
	  */
	function onClick(event, treeId, treeNode, clickFlag) {
		clearAllVal();
		$("input[name='open']").val(false);
		$("input[name='treePId']").val(treeNode.id);
		$("input[name='treeIsQsUnit']").val(treeNode.isQsUnit);
		$("input[name='treeOrgType']").val(treeNode.type);
		$("input[name='treeOrgName']").val(treeNode.name);
		if(treeNode.isQsUnit){
			$("input[name='parentId']").val(treeNode.id);
			search('qsQualityUnitDivisionGridIdGrid');
		}else{
			$("input[name='orgId']").val(treeNode.id);
			search('qsQualityUnitDivisionGridIdGrid');
		}
	}
	
	/**
	  *@function 清空值
	  *@author Huangqiling
	  *@date 2018-3-15
	  */
	function clearAllVal(){
		$("input[name='parentId']").val("");
		$("input[name='orgId']").val("");
		$("input[name='treePId']").val("");
		$("input[name='treeIsQsUnit']").val("");
		$("input[name='treeOrgType']").val("");
		$("input[name='treeOrgName']").val("");
	}
	
	$(document).ready(function(){
		treeObj=$.fn.zTree.init($("#treeObj"), setting);
	});
	
	
	/**
	  *@function 加载样式
	  *@author Huangqiling
	  *@date 2018-3-14
	  */
	function onloadFun(){
		var winHeight = $("#page-wrapper").height()-100;
		try{
			$(".portlet-body,.portlet-body>div").css("height",winHeight+"px");
			$(".ui-jqgrid-view").css("height",winHeight-150+"px")
		}catch(e){}
	}
	
	/**
	  *@function 新增-质量单元划分
	  *@author Huangqiling
	  *@date 2018-3-14
	  */
	function toCreate(){
		var treePId = $("input[name='treePId']").val();
		var type = $("input[name='treeOrgType']").val();
		var treeIsQsUnit = $("input[name='treeIsQsUnit']").val();
		var treeOrgName = $("input[name='treeOrgName']").val();
		var url = "${adminPath}/qs/qsqualityunitdivision/create?treePId="+treePId+"&treeIsQsUnit="+treeIsQsUnit;
		if(type.indexOf("5") >= 0||treeIsQsUnit=="true"){
			openQsUnitDialog("新增",url,"qsQualityUnitDivisionGridIdGrid","800px","450px");
		}else{
			top.layer.alert("请选择标段类型的组织或者选择单元划分节点，方可增加下级！", {icon: 0, title:'提示'});
		}			
	}
	
	/**
	  *@function 修改-质量单元划分
	  *@author Huangqiling
	  *@date 2018-3-16
	  */
	function toUpdate(){
		var rowsData = $("#qsQualityUnitDivisionGridIdGrid").jqGrid('getGridParam','selarrrow');
		var multiselect=$("#qsQualityUnitDivisionGridIdGrid").jqGrid('getGridParam','multiselect');
		var rowData= $("#qsQualityUnitDivisionGridIdGrid").jqGrid('getGridParam','selrow');
		if(!multiselect)
		{
			if(rowData)
			{
				  rowsData[0]=rowData;
			}
		}
	    if (!rowsData || rowsData.length==0) {
		    top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
			return; 
		}
	    if (rowsData.length>1) {
	    	top.layer.alert('只能选择一条数据!', {icon: 0, title:'警告'});
			return;
		}
	    
	    var id = rowsData[0];
	    var url = "${adminPath}/qs/qsqualityunitdivision/{id}/update";
	    url=url.replace("{id}",id);
	    openQsUnitDialog("修改",url,"qsQualityUnitDivisionGridIdGrid","800px","450px");
	}
	
	/**
	 *@function 打开图纸分组修改框，并刷新当前修改的树节点
	 *@author Huangqiling
	 *@date 2017-12-2
	 */
	function openQsUnitDialog(title,url,gridId,width,height){
		width = width?width:'800px';
		height = height?height:'500px';
		if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
			width='auto';
			height='auto';
		}else{//如果是PC端，根据用户设置的width和height显示。
		
		}
		top.layer.open({
		    type: 2,  
		    area: [width, height],
		    title: title,
	        maxmin: true, //开启最大化最小化按钮
		    content: url ,
		    btn: ['确定', '关闭'],
		    yes: function(index, layero){
		    	 var body = top.layer.getChildFrame('body', index);
		         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		         iframeWin.contentWindow.doSubmit(function(results){
		        	 //上传文件
		        	 var isFormSubmit = null;
		        	 try {
		        		 iframeWin.contentWindow.isFormSubmit();
			        	 if(isFormSubmit){
			        		 iframeWin.contentWindow.uploadFile(results.data.id);
			        	 }
					 } catch (e) {
					 }
		        	 
		        	 //判断逻辑并关闭
	       	         setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
	       	         //刷新表单
		        	 refreshTable(gridId);
		        	 //刷新当前树节点
		        	 refreshCurrentNode()
		        	 //刷新当前树节点的子节点
		        	 refreshCurrentChildNode();
		         });
				
			  },
			  cancel: function(index){ 
				  
			  }
		}); 	
		
	}
	
	/** 
     * @function 刷新当前节点
     * @author Huangqiling
     * @date 2018-3-16
     */  
    function refreshCurrentNode() {  
        /*根据 treeId 获取 zTree 对象*/  
        var zTree = $.fn.zTree.getZTreeObj("treeObj");  
        var type = "refresh";  
        var silent = true;  
        /*获取 zTree 当前被选中的节点数据集合*/  
        var nodes = zTree.getSelectedNodes(); 
        
        /*刷新当前节点父节点*/
        var parentNode = zTree.getNodeByTId(nodes[0].parentTId); 
        /*选中指定节点*/   
        zTree.reAsyncChildNodes(parentNode, type, silent);
    }  
	
    /** 
     * @function 刷新当前节点的下级节点
     * @author Huangqiling
     * @date 2018-3-16
     */  
    function refreshCurrentChildNode() {  
        /*根据 treeId 获取 zTree 对象*/  
        var zTree = $.fn.zTree.getZTreeObj("treeObj");  
        var type = "refresh";  
        var silent = true;  
        /*获取 zTree 当前被选中的节点数据集合*/  
        var nodes = zTree.getSelectedNodes();    
        /*强行异步加载父节点的子节点。[setting.async.enable = true 时有效]*/  
        nodes[0].isParent = true;//把属性变成true，让这个节点被认为是根节点
        zTree.reAsyncChildNodes(nodes[0], type, silent); 
        /*展开树节点*/
        zTree.expandNode(nodes[0], true, false, false);
    }  
    
    /**
	  *@function 提交
	  *@author Huangqiling
	  *@date 2018-3-16
	  */
	function submit(){
		operate("qsQualityUnitDivisionGridIdGrid","提交",1);
	}
    
	/**
	  *@function 审核
	  *@author Huangqiling
	  *@date 2018-3-16
	  */
	function audit(){
		operate("qsQualityUnitDivisionGridIdGrid","审核",4);
	}
	
	/**
	  *@function 反审核
	  *@author Huangqiling
	  *@date 2018-3-16
	  */
	function antiAudit(){
		operate("qsQualityUnitDivisionGridIdGrid","反审核",0);
	}
	
	/**
	  *@function 提交、审核、反审核操作
	  *@author Huangqiling
	  *@date 2018-3-16
	  */
	function operate(gridId,operateType,status) {  
		var url = "${adminPath}/qs/qsqualityunitdivision/batch/operate";
		var ids = [];
		var rows =$("#"+gridId).jqGrid('getGridParam','selarrrow');
		var rowData= $("#"+gridId).jqGrid('getGridParam','selrow');
		var multiselect=$("#"+gridId).jqGrid('getGridParam','multiselect');
		if(!multiselect)
		{
			if(rowData)
			{
				rows[0]=rowData;
			}
		}
	    if (rows.length > 0) {
	    	layer.confirm("您确定要"+operateType+"这些数据么?", {
	   		  title:"提示",
	   		  icon:3,
	   		  btn: ['确定','取消'] //按钮
	   		}, function(){
	   		    //确定
	   			for ( var i = 0; i < rows.length; i++) {
	           		ids.push(rows[i]);
	   			}
	   			$.ajax({
	   				url : url,
	   				type : 'post',
	   				data : {
	   					ids : ids.join(','),
	   					status:status
	   				},
	   				cache : false,
	   				success : function(d) {
	   					if (d.ret==0) {
	   						var msg = d.msg;
	   					    layer.msg(msg, {icon:1});
	   					    //刷新表单
	   			            refreshTable(gridId);
	   					}else{
	   						var msg = d.msg;
	   					    layer.msg(msg, {icon: 3,shade:0.3});
	   					}
	   				}
	   			});
	   		}, function(){
	   		  //取消
	   		});
			return;
		}else{
		    top.layer.alert("请选择需要"+operateType+"的数据!", {icon: 0, title:'警告'});
		    return;
		}
    } 
	
	/**
	  *@function Excel模板导出
	  *@author Huangqiling
	  *@date 2018-3-19
	  */
    function exportExcelModel(){
    	 var url = '${adminPath}/qs/qsqualityunitdivision/exportExcelModel';
    	 window.open(url);
    }
	
    /**
	  *@function Excel导出
	  *@author Huangqiling
	  *@date 2018-3-19
	  */
    function exportExcel(){
    	var treePId = $("input[name='treePId']").val();
		var type = $("input[name='treeOrgType']").val();
		var treeIsQsUnit = $("input[name='treeIsQsUnit']").val();
 		if(type.indexOf("5") >= 0||treeIsQsUnit=="true"){
			var url = '${adminPath}/qs/qsqualityunitdivision/exportExcel?treePId='+treePId+'&treeIsQsUnit='+treeIsQsUnit;
	     	window.open(url);
		}else{
			top.layer.alert("请选择标段类型的组织或者选择单元划分节点进行导出！", {icon: 0, title:'提示'});
		}	
    }
    
    /**
	  *@function Excel导入
	  *@author Huangqiling
	  *@date 2018-3-20
	  */
    function importExcel(){
    	var treePId = $("input[name='treePId']").val();
		var type = $("input[name='treeOrgType']").val();
		var treeIsQsUnit = $("input[name='treeIsQsUnit']").val();
		var controllerName = '${adminPath}/qs/qsqualityunitdivision/excelUpload';
		var url = '${adminPath}/sys/common/excelUpload?nodeId='+treePId+'&controllerName='+controllerName;
		if(type.indexOf("5") >= 0&&treeIsQsUnit!="true"){
			uploadExcel('导入excel',url,"qsQualityUnitDivisionGridIdGrid");
		}else{
			 top.layer.alert("请选择标段类型的组织,方可导入！", {icon: 0, title:'提示'});
		}
	 }
    
    /**
	  *@function 显示底层
	  *@author Huangqiling
	  *@date 2018-3-20
	  */
    function showAll(){
    	var treePId = $("input[name='treePId']").val();
		var type = $("input[name='treeOrgType']").val();
		var treeIsQsUnit = $("input[name='treeIsQsUnit']").val();
		if(type.indexOf("5") >= 0||treeIsQsUnit=="true"){
			$("input[name='open']").val(true);
			//刷新表单
       	    refreshTable("qsQualityUnitDivisionGridIdGrid");
		}else{
			top.layer.alert("请选择标段类型的组织或者选择单元划分节点进行底层显示！", {icon: 0, title:'提示'});
		}
	 }
    
    
    /**
	  *@function 删除
	  *@author Huangqiling
	  *@date 2018-3-26
	  */
	function deleteData() {  
    	debugger;
		var url = "${adminPath}/qs/qsqualityunitdivision/batch/deleteData";
		var ids = [];
		var rows =$("#qsQualityUnitDivisionGridIdGrid").jqGrid('getGridParam','selarrrow');
		var rowData= $("#qsQualityUnitDivisionGridIdGrid").jqGrid('getGridParam','selrow');
		var multiselect=$("#qsQualityUnitDivisionGridIdGrid").jqGrid('getGridParam','multiselect');
		if(!multiselect)
		{
			if(rowData)
			{
				rows[0]=rowData;
			}
		}
	    if (rows.length > 0) {
	    	layer.confirm("删除当前数据该数据的子级也会被删除,您确定要删除这些数据么?", {
	   		  title:"提示",
	   		  icon:3,
	   		  btn: ['确定','取消'] //按钮
	   		}, function(){
	   		    //确定
	   			for ( var i = 0; i < rows.length; i++) {
	           		ids.push(rows[i]);
	   			}
	   			$.ajax({
	   				url : url,
	   				type : 'post',
	   				data : {
	   					ids : ids.join(',')
	   				},
	   				cache : false,
	   				success : function(d) {
	   					if (d.ret==0) {
	   						var msg = d.msg;
	   					    layer.msg(msg, {icon:1});
	   					    //刷新表单
	   			            refreshTable("#qsQualityUnitDivisionGridIdGrid");
	   					}else{
	   						var msg = d.msg;
	   					    layer.msg(msg, {icon: 3,shade:0.3});
	   					}
	   				}
	   			});
	   		}, function(){
	   		  //取消
	   		});
			return;
		}else{
		    top.layer.alert("请选择需要删除的数据!", {icon: 0, title:'警告'});
		    return;
		}
   } 
</script>
</body>
</html>