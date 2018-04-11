<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>组织机构图列表</title>
    <meta name="decorator" content="list"/>
    <html:component name="bootstrap-treeview"/>
    <html:css name="iCheck,Validform,jquery-ztree,easy-ui"/>
    <html:js name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
    <style type="text/css">.row {
        margin: 0;
    }</style>
</head>
<body title="组织机构图管理">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <input type="hidden" id="treeId" name="treeId"/>
    <input type="hidden" id="treeName" name="treeName"/>
    <div data-options="region:'west',split:true" style="width:20%;">
        <div class="zTreeDemoBackground left">
            <ul id="treeObj" class="ztree"></ul>
        </div>
    </div>
    <div data-options="region:'center'">
        <grid:grid id="qsOrgChartGridId" url=""
                   gridSetting="{loadComplete:onloadFun,ondblClickRow : dbClickRowFun}"
                   sortname="createDate" sortorder="desc">
            <grid:column label="sys.common.key" hidden="true" name="id" width="100"/>
            <grid:column label="图片名称" name="chartName" query="true" condition="like" width="100"/>
            <grid:column label="上传单位" name="orgName"/>
            <grid:column label="备注" name="remarks" width="100"/>
            <grid:column label="附件" name="opt" formatter="button" width="100"/>
            <grid:column label="创建人" name="createBy.realname" width="100"/>
            <grid:column label="创建日期" name="createDate" width="100" formatter="date" dateformat='yyyy-MM-dd'/>
            <grid:query name="orgId" queryMode="hidden"/>
            <grid:column label="附件个数" name="attachmentCount" width="100" hidden="true"/>
            <grid:button exp="row.attachmentCount>0" title="查看附件" groupname="opt" function="attachmentLook"
                         outclass="btn-info" innerclass="fa-search"
                         url="${adminPath}/sys/common/attachmentLook"/>
            <shiro:hasPermission name="qs:qsorgchart:create">
            <grid:toolbar title="新增" btnclass="btn-green" icon="fa-plus" function="add"
                          url="${adminPath}/qs/qsorgchart/create"/>
            </shiro:hasPermission>
            <%--<grid:button groupname="opt" function="delete"/>
            <grid:toolbar function="create"/>--%>
            <shiro:hasPermission name="qs:qsorgchart:update">
            <grid:toolbar function="update" url="${adminPath}/qs/qsorgchart/{id}/update"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="qs:qsorgchart:delete">
            <grid:toolbar function="delete"/>
            </shiro:hasPermission>
            <grid:toolbar function="search"/>
            <grid:toolbar function="reset"/>
        </grid:grid>
    </div>
</div>

<script type="text/javascript">

    // 双击行查看数据
    function dbClickRowFun(rowid,iRow,iCol,e) {
        //seeDeatil(rowid);
        var url = '${adminPath}/qs/qsorgchart/' + rowid + '/update';
        openDialogDetail('查看',url, "800px","450px");
    }

    /**
     * 查看
     */
    function seeDeatil(id) {
        var url = '${adminPath}/qs/qsorgchart/' + id + '/update?load=detail';
        top.layer.open({
            type : 2,
            area : [ '100%', '100%' ],
            title : '查看',
            maxmin : true, // 开启最大化最小化按钮
            content : url,
            btn : [ '关闭' ],
            cancel : function(index) {
            }
        });
    }

    /**
     * 新增按钮
     **/
    function add(title, url, gridid, width, height) {
        var treeId = $("input[name='treeId']").val();
        var treeName = $("input[name='treeName']").val();
        if (treeId === '') {
            top.layer.alert('请选择组织!', {icon: 0, title: '警告'});
            return;
        }
        url += "?treeId=" + treeId + "&treeName=" + treeName;
        create("新增", url, "qsOrgChartGridIdGrid","800px","450px");
    }

    var treeObj;
    var setting = {
        async: {
            enable: true,
            url: "${adminPath}/sys/organization/orgTree",
            autoParam: ["id=nodeid"],
            dataFilter: filter
        },
        callback: {
            onClick: onClick
        }
    };

    // 左侧树过滤
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i = 0, l = childNodes.length; i < l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }

    // 左侧树点击事件
    function onClick(event, treeId, treeNode, clickFlag) {
        clearAllVal();
        $("input[name='treeId']").val(treeNode.id);
        $("input[name='orgId']").val(treeNode.id);
        $("input[name='treeName']").val(treeNode.name);

        var setting = {datatype:'json',url:"${adminPath}/qs/qsorgchart/ajaxList"};
        $("#qsOrgChartGridIdGrid").jqGrid('setGridParam',setting);
        search('qsOrgChartGridIdGrid');
    }

    /**
     *@function 清空值
     *@author Huangqiling
     *@date 2018-3-1
     */
    function clearAllVal() {
        $("input[name='treeId']").val("");
        $("input[name='orgId']").val("");
        $("input[name='treeName']").val("");
    }

    // 初始化左侧树
    $(document).ready(function () {
        treeObj = $.fn.zTree.init($("#treeObj"), setting);
    });

    function onloadFun() {
        var winHeight = $("#page-wrapper").height() - 100;

        try {
            $(".portlet-body,.portlet-body>div").css("height", winHeight + "px");
            $(".ui-jqgrid-view").css("height", winHeight - 150 + "px")
        } catch (e) {
        }
    }

    /**
     *@function 附件查看
     *@author Huangqiling
     *@date 2018-3-1
     */
    function attachmentLook(title,url,gridId,id,width,height){
        rowDialogDetailRefresh(title,url,gridId,id,"auto","auto");
    }

</script>

</body>
</html>