<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>每月较好质量照片管理</title>
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
<body title="每月较好质量照片管理">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <input type="hidden" id="treeId" name="treeId"/>
    <input type="hidden" id="treeName" name="treeName"/>
    <div data-options="region:'west',split:true" style="width:20%;">
        <div class="zTreeDemoBackground left">
            <ul id="treeObj" class="ztree"></ul>
        </div>
    </div>
    <div data-options="region:'center'">
        <grid:grid id="qsBestPictureOfMonthGridId" url="${adminPath}/qs/qsbestpictureofmonth/ajaxList"
                   gridSetting="{loadComplete:onloadFun}"
                   sortname="createDate" sortorder="desc">
            <grid:column label="sys.common.key" hidden="true" name="id" width="100"/>
            <grid:column label="日期" name="date" formatter="date" dateformat='yyyy-MM-dd'/>
            <grid:column label="标题" name="title"/>
            <grid:column label="备注" name="remarks"/>
            <grid:column label="附件" name="opt" formatter="button" width="100"/>
            <grid:column label="创建者" name="createBy.realname"/>
            <grid:column label="创建时间" name="createDate"/>
            <grid:query name="orgId" queryMode="hidden"/>
            <grid:column label="附件个数"  name="attachmentCount" width="100" hidden="false"/>
            <grid:button exp="row.attachmentCount>0" title="查看附件" groupname="opt" function="attachmentLook"
                         outclass="btn-info" innerclass="fa-search"
                         url="${adminPath}/sys/common/attachmentLook"/>
            <shiro:hasPermission name="qs:qsbestpictureofmonth:create">
                <%--<grid:toolbar function="create"/>--%>
                <grid:toolbar title="新增" btnclass="btn-green" icon="fa-plus" function="add"
                              url="${adminPath}/qs/qsbestpictureofmonth/create"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="qs:qsbestpictureofmonth:update">
                <grid:toolbar function="update"/>
            </shiro:hasPermission>
            <shiro:hasPermission name="qs:qsbestpictureofmonth:delete">
                <grid:toolbar function="delete"/>
            </shiro:hasPermission>

            <%--<grid:toolbar function="search"/>
            <grid:toolbar function="reset"/>--%>
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
        openLayer("新增", url, "qsBestPictureOfMonthGridIdGrid","800px","450px");
    }

    /**
     * 附件需要做必要验证
     * @param title
     * @param url
     * @param gridId
     * @param width
     * @param height
     */
    function openLayer(title,url,gridId,width,height) {
        top.layer.open({
            type: 2,
            area: [width, height],
            title: title,
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            btn: ['确定', '关闭'],
            yes: function(index, layero){
                var loadIndex = openLoad();
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();

                //文件必须上传
                var fileChecked = iframeWin.contentWindow.checkFileUpload();
                if(!fileChecked){//无附件上传，给出提示
                    closeLoad(loadIndex);
                    top.layer.alert('请上传附件', {icon: 0, title:'提示'});
                    return;
                }

                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.doSubmit(function(results){
                    //上传文件
                    var isFormSubmit = null;
                    try {
                        isFormSubmit = iframeWin.contentWindow.isFormSubmit();
                        if(isFormSubmit){
                            iframeWin.contentWindow.uploadFile(results.data.id,function(data){
                                tipInfo(results);
                                //判断逻辑并关闭
                                closeRefresh(index,gridId,loadIndex);
                            });
                        }
                    } catch (e) {
                    }
                    if(!isFormSubmit){
                        tipInfo(results);
                        closeRefresh(index,gridId,loadIndex);
                    }

                });
                closeLoad(loadIndex);
            },
            cancel: function(index){

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
        search('qsBestPictureOfMonthGridIdGrid');
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