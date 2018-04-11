<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>质量责任登记表列表</title>
    <meta name="decorator" content="list"/>
    <html:component name="bootstrap-treeview"/>
    <html:css name="iCheck,Validform,jquery-ztree,easy-ui"/>
    <html:js name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
    <style type="text/css">
        .row {
            margin: 0;
        }
    </style>
</head>
<body title="质量责任登记表">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <input type="hidden" id="treeId" name="treeId"/>
    <input type="hidden" id="treeName" name="treeName"/>
    <div data-options="region:'west',split:true" style="width:20%;">
        <div class="zTreeDemoBackground left">
            <ul id="treeObj" class="ztree"></ul>
        </div>
    </div>
    <div data-options="region:'center'">

        <grid:grid id="qsResponsibilityRegGridId" url="${adminPath}/qs/qsresponsibilityreg/ajaxList"
                   gridSetting="{loadComplete:onloadFun}"
                   sortname="createDate" sortorder="desc">
            <grid:column label="sys.common.key" hidden="true" name="id" width="100"/>
            <grid:column label="单位id" hidden="true" name="orgId"/>
            <grid:column label="项目名称" name="projectName" query="true" condition="like" width="100"/>
            <grid:column label="合同段号" name="contractStageNumber" query="true" condition="like" width="100"/>
            <grid:column label="签章" name="signature" width="100"/>
            <grid:column label="单位名称" name="orgName" width="100"/>
            <grid:column label="承担工作内容" name="workContent" width="100"/>
            <grid:column label="备注" name="remarks" width="100"/>
            <grid:column label="附件" name="opt" formatter="button" width="120"/>
            <grid:column label="创建者" name="createBy.realname" width="100"/>
            <grid:column label="创建时间" formatter="date" dateformat="yyyy-MM-dd" name="createDate" width="100"/>
            <grid:column label="更新者" name="updateBy.realname" width="100"/>
            <grid:column label="更新时间" formatter="date" dateformat="yyyy-MM-dd" name="updateDate" width="100"/>
            <grid:query name="orgId" queryMode="hidden"/>
            <grid:column label="附件个数"  name="attachmentCount" width="100" hidden="true"/>
            <grid:button exp="row.attachmentCount>0" title="查看附件" groupname="opt" function="attachmentLook"
                         outclass="btn-info" innerclass="fa-search"
                         url="${adminPath}/sys/common/attachmentLook"/>
            <grid:toolbar title="新增" btnclass="btn-green" icon="fa-plus" function="add"
                          url="${adminPath}/qs/qsresponsibilityreg/create"/>
            <grid:toolbar function="update" winwidth="1000px" winheight="800px"/>
            <grid:toolbar function="delete"/>
            <grid:toolbar title="打印" function="print"/>

            <grid:toolbar function="search"/>
            <grid:toolbar function="reset"/>
        </grid:grid>

    </div>
</div>

<script type="text/javascript">

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
        create("新增", url, "qsResponsibilityRegGridIdGrid","1000px","800px");
    }

    function print() {
        alert();
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
        search('qsResponsibilityRegGridIdGrid');
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