(function(){
	document.ztree = function(treeObj,url,configure){
		var menuId = window.localStorage? localStorage.getItem("nid"): Cookie.read("nid");
		var setting = {};
		if(typeof (configure) == "object" && Object.prototype.toString.call(configure).toLowerCase() == "[object object]" && !configure.length){
			setting = configure;
			defaultAsync.url = url;
			defaultAsync.otherParam = {"menuId":menuId};
			if(!setting.async)setting.async = defaultAsync;
			if(!setting.callback)setting.callback = defaultCallback;
			if(!setting.check)setting.check = defaultCheck;
			if(!setting.data)setting.data = defaultData;
			if(!setting.edit)setting.edit = defaultEdit;
			if(!setting.view)setting.view = defaultView;
		}else{
			defaultAsync.url = url;
			defaultAsync.otherParam = {"menuId":menuId};
			setting = {
				async: defaultAsync
			}
		}
		return initTree(treeObj,setting);
	}
	
	/**
	 * @descrption 默认的ztree的请求配置方式.<br>
	 */
	var defaultAsync = {
		enable: true,
		type: "post",
		dataFilter: filter,
		autoParam:["id=id"],
		otherParam:[]
	} 
	
	/**
	 * @descrption 默认的ztree的回调.<br>
	 */
	var defaultCallback = {}
	
	/**
	 * @descrption 默认的ztree的勾选事件.<br>
	 */
	var defaultCheck = {}
	
	/**
	 * @descrption 默认的ztree的勾选事件.<br>
	 */
	var defaultData = {}
	
	/**
	 * @descrption 默认的ztree的勾选事件.<br>
	 */
	var defaultEdit = {}
	
	/**
	 * @descrption 默认的ztree的勾选事件.<br>
	 */
	var defaultView = {}
	
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	
	/**
	 * @param treeObj 绑定的节点Id.<br>
	 * @param setting 参数配置.<br>
	 */
	function initTree(treeObj,setting){
		return $.fn.zTree.init($("#"+treeObj), setting);
	}
	
	if(!$.ztree){
		$.ztree = document.ztree;
	}
	if(!document.ztree){
		document.ztree = $.ztree;
	}
})();