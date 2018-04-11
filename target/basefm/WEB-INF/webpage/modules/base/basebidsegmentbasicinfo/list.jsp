<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>标段基本信息</title>
    <meta name="decorator" content="list"/>
    <html:component name="bootstrap-treeview"/>
    <html:css name="iCheck,Validform,jquery-ztree,easy-ui"/>
    <html:js name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
    <style type="text/css">.row {
        margin: 0;
    }</style>
</head>
<body title="标段基本信息">
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <input type="hidden" id="treeId" name="treeId"/>
    <input type="hidden" id="treeName" name="treeName"/>
    <div data-options="region:'west',split:true" style="width:20%;">
        <div class="zTreeDemoBackground left">
            <ul id="treeObj" class="ztree"></ul>
        </div>
    </div>
    <div data-options="region:'center'">
        <grid:grid id="baseBidSegmentBasicInfoGridId" url=""
                   pageable="false"
                   gridSetting="{loadComplete:onloadFun,ondblClickRow : dbClickRowFun}"
                   sortname="org.code" sortorder="asc">
            <grid:column label="sys.common.key" hidden="true" name="id" width="100"/>
            <grid:column label="标段编码" name="bidSegmentNumber"/>
            <grid:column label="标段名称" name="bidSegmentFullName"/>
            <grid:column label="标段类型" name="bidSegmentType" dict="bidSegmentType"/>
            <grid:column label="合同号" name="contractNumber"/>
            <grid:column label="施工单位" name="constructionUnit"/>
            <grid:column label="建设单位" name="buildUnit"/>
            <grid:column label="监理单位" name="supervisionUnit"/>
            <grid:column label="起始桩号" name="startPileNo"/>
            <grid:column label="终止桩号" name="endPileNo"/>
            <grid:column label="状态" name="useStatus" dict="useType"/>
            <grid:query name="orgId" queryMode="hidden"/>
            <shiro:hasPermission name="base:basebidsegmentbasicinfo:create">
            <grid:toolbar title="新增" btnclass="btn-green" icon="fa-plus" function="add"
                          url="${adminPath}/base/basebidsegmentbasicinfo/create"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="base:basebidsegmentbasicinfo:update">
            <grid:toolbar title="修改" btnclass="btn-success" icon="fa-plus" onclick="updateDetail('修改','${adminPath}/base/basebidsegmentbasicinfo/{id}/update','baseBidSegmentBasicInfoGridIdGrid','100%','100%')"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="base:basebidsegmentbasicinfo:enable">
            <grid:toolbar title="启用" btnclass="btn-pink" icon="fa-plus" onclick="updateStatus('启用', '${adminPath}/base/basebidsegmentbasicinfo/changeStatus', 'baseBidSegmentBasicInfoGridIdGrid', 1)"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="base:basebidsegmentbasicinfo:disable">
            <grid:toolbar title="禁用" btnclass="btn-warning" icon="fa-plus" onclick="updateStatus('禁用', '${adminPath}/base/basebidsegmentbasicinfo/changeStatus', 'baseBidSegmentBasicInfoGridIdGrid', 0)"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="base:basebidsegmentbasicinfo:delete">
            <grid:toolbar title="删除" btnclass="btn-danger" icon="fa-plus" onclick="deleteBatch('删除','${adminPath}/base/basebidsegmentbasicinfo/batch/delete','baseBidSegmentBasicInfoGridIdGrid')"/>
            </shiro:hasPermission>
            <%--<grid:toolbar function="delete"/>

            <grid:toolbar function="search"/>
            <grid:toolbar function="reset"/>--%>
        </grid:grid>
    </div>
</div>
<script src="${staticPath}/base/js/tools.js?v=1.0.0"></script>
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
        $.ajax({
            type: 'GET',
            url: '${adminPath}/base/basebidsegmentbasicinfo/checkOnly?orgId=' + treeId,
            dataType : 'json',
            data: {},
            success: function (msg) {
                if (msg.ret === 0) {
                    create("新增", url, "baseBidSegmentBasicInfoGridIdGrid", "100%", "100%");
                } else {
                    tipInfo(msg);
                }
            },
            complete:function(){
            }
        });
    }

    /**
     * 更新
     */
    function updateDetail(title, url, gridId, width, height) {
        var rowsData = $("#" + gridId).jqGrid('getGridParam', 'selarrrow');
        var multiselect = $("#" + gridId).jqGrid('getGridParam', 'multiselect');
        var rowData = $("#" + gridId).jqGrid('getGridParam', 'selrow');
        if (!multiselect) {
            if (rowData) {
                rowsData[0] = rowData;
            }
        }
        if (!rowsData || rowsData.length == 0) {
            top.layer.alert('请至少选择一条数据!', {icon: 0, title: '警告'});
            return;
        }
        if (rowsData.length > 1) {
            top.layer.alert('只能选择一条数据!', {icon: 0, title: '警告'});
            return;
        }

        var id = rowsData[0];
        var queryParams = {queryFields : 'useStatus,', 'query.id||eq' : id};
        var json = getAjaxList('${adminPath}/base/basebidsegmentbasicinfo/ajaxListNOPage', queryParams);
        //var checked = checkUseStatus('${adminPath}/base/basebidsegmentbasicinfo/ajaxListNOPage', queryParams);
        var checked = json.results[0].useStatus == 0;
        if (checked) {
            update(title, url, gridId, width, height)
        } else {
            top.layer.alert('该记录已启用', {icon: 0, title:'提示'});
        }

    }

    /**
     * 删除
     */
    function deleteBatch(title,url,gridId) {
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
            layer.confirm("只能删除未启用的数据，您确定要删除这些信息么?请谨慎操作！", {
                title:title+"提示",
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
        }else
        {
            top.layer.alert('请选择需要删除的数据!', {icon: 0, title:'警告'});
            return;
        }
    }

    // 双击行查看数据
    function dbClickRowFun(rowid,iRow,iCol,e) {
        //seeDeatil(rowid);
        var url = '${adminPath}/base/basebidsegmentbasicinfo/' + rowid + '/update';
        openDialogDetail('查看',url, "100%","100%");
    }

    /**
     * 查看
     */
    function seeDeatil(id) {
        var url = '${adminPath}/base/basebidsegmentbasicinfo/' + id + '/update?load=detail';
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

        var setting = {datatype:'json',url:"${adminPath}/base/basebidsegmentbasicinfo/ajaxListNOPage"};
        $("#baseBidSegmentBasicInfoGridIdGrid").jqGrid('setGridParam',setting);
        search('baseBidSegmentBasicInfoGridIdGrid');
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


</script>
</body>
</html>