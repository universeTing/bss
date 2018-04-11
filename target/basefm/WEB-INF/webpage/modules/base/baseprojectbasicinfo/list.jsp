<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>项目基本信息</title>
    <meta name="decorator" content="list"/>
    <html:component name="bootstrap-treeview"/>
    <html:css name="iCheck,Validform,jquery-ztree,easy-ui,layui"/>
    <html:js name="iCheck,Validform,jquery-ztree,easy-ui,public-js,layui"/>
    <style type="text/css">
        .row {
            margin: 0;
        }
        .padding15 {
            padding: 15px;
        }
        .lfpadding15 {
            padding: 0 15px;
        }
        .basic-info-box {
            border: 1px solid #eee;
        }
        .basic-info-box.active,
        .basic-info-box:hover {
            border-color: #00B2F8;box-shadow: 1px 1px 8px #00B2F8;
            position: relative;
        }
        .btn-more{background-color: #eee;width: 100%;height: 35px;line-height: 35px; display: block;}
        .basic-info-box.active:after{
            content: "";
            width: 20px;
            height: 20px;
            position: absolute;
            top: 10px;
            right: 15px;
            background: url("${staticPath}/base/img/yes.png") no-repeat center;
            -webkit-background-size: 100%;
            background-size: 100%;
        }
        .red-font-color {
            color : red;
        }
        .green-font-color {
            color : green;
        }
    </style>
</head>
<body title="项目基本信息">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <input type="hidden" id="treeId" name="treeId"/>
    <input type="hidden" id="treeName" name="treeName"/>
    <div data-options="region:'west',split:true" style="width:20%;">
        <div class="zTreeDemoBackground left">
            <ul id="treeObj" class="ztree"></ul>
        </div>
    </div>
    <div data-options="region:'center'">
        <div class="container-fluid" style="">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <!-- 查询条件 -->
                    <%--<div class="row">
                        <div id="qsResponsibilityRegGridIdGridQuery" class="col-sm-12">
                            <div class="form-inline">
                                <div class="form-group">
                                    <label class="control-label">搜索字段名：</label>
                                    <input  type="text" placeholder="搜索字段名"   name='chartName' condition='like' class='form-control'/>
                                </div>
                                <div class="form-group">
                                    <input type="hidden" placeholder=""   name='orgId' condition='eq' class='form-control'/>
                                </div>
                            </div>
                            <br/>
                        </div>
                    </div>--%>
                    <!-- 工具栏 -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pull-left">
                                <shiro:hasPermission name="base:baseprojectbasicinfo:create">
                                <button   class="btn btn-sm btn-green"
                                          onclick="add('新增','${adminPath}/base/baseprojectbasicinfo/create','baseProjectBasicInfoGridIdGrid','100%','100%')"
                                ><i class="fa fa-plus"></i> 新增</button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="base:baseprojectbasicinfo:update">
                                <button   class="btn btn-sm btn-success"
                                          onclick="updateDetail('修改','${adminPath}/base/baseprojectbasicinfo/{id}/update','baseProjectBasicInfoGridIdGrid','100%','100%')"
                                ><i class="fa fa-file-text-o"></i> 修改</button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="base:baseprojectbasicinfo:enable">
                                <button   class="btn btn-sm btn-pink"
                                          onclick="enable('启用','${adminPath}/base/baseprojectbasicinfo/changeStatus', 1)"
                                ><i class="fa fa-file-text-o"></i>启用</button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="base:baseprojectbasicinfo:disable">
                                <button   class="btn btn-sm btn-warning"
                                          onclick="enable('禁用','${adminPath}/base/baseprojectbasicinfo/changeStatus', 0)"
                                ><i class="fa fa-file-text-o"></i>禁用</button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="base:baseprojectbasicinfo:delete">
                                <button   class="btn btn-sm btn-danger"
                                          onclick="deleteBatch('删除','${adminPath}/base/baseprojectbasicinfo/batch/delete','baseProjectBasicInfoGridIdGrid')"
                                ><i class="fa fa-trash-o"></i> 删除</button>
                                </shiro:hasPermission>
                            </div>
                            <%--<div class="pull-right">
                                <button class="btn btn-sm btn-info" onclick="search('baseProjectBasicInfoGridIdGrid')"><i class="fa fa-search"></i> 搜索</button>
                                <button class="btn btn-sm btn-warning" onclick="reset('baseProjectBasicInfoGridIdGrid')"><i class="fa fa-refresh"></i> 重置</button>
                            </div>--%>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix basic-info-list" style="margin-top: 20px; min-height: 540px;">

            </div>

            <!-- 分页 -->
            <div class="row clearfix text-center">
                <div class="col-md-12">
                    <nav aria-label="Page navigation">
                        <ul class="pagination basic-info-page">

                        </ul>
                    </nav>
                </div>
            </div>

        </div>

    </div>
</div>
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
<script src="${staticPath}/base/js/tools.js?v=1.0.0"></script>
<script type="text/javascript">

    var queryFields = 'projectFullName,totalCost,ownerOrg,mainDesignUnit';
    var currentPage = 1;// 当前页
    var pageS = 6;// 每页行数

    $(function () {
        //getData(1, 6);
    });

    /**
     * 获取卡片数据
     * @param {number} pagePn 第几页
     * @param {number} pageSize 每页多少行
     */
    function getData(pagePn, pageSize) {
        currentPage = pagePn;
        pageS = pageSize;
        var queryParams = {'page.size' : pageSize, 'page.pn' : pagePn, 'query.orgId||eq' : $('#treeId').val()};
        $.ajax({
            type: 'GET',
            url: '${adminPath}/base/baseprojectbasicinfo/ajaxList',
            dataType : 'json',
            data: queryParams,
            success: function (msg) {
                renderData(msg.results);
                renderPage(msg);
            },
            complete:function(){
                // 绑定选中事件
                $(".padding15").on("click",function(){
                    if(!$(this).children(".basic-info-box").hasClass("active")) {
                        $(this).children(".basic-info-box").addClass("active");
                    }else{
                        $(this).children(".basic-info-box").removeClass("active");
                    }
                });
                // 绑定双击事件
                $(".padding15").on("dblclick",function(){
                    dbClickRowFun($(this).attr('info-id'));
                });
            }
        });
    }

    /**
     * 渲染卡片列表数据
     * @param {object} data ajaxList获取到的数据
     */
    function renderData(data) {
        $('.basic-info-list').empty();
        for (var i = 0; i < data.length; i++) {
            var column = $('<div></div>').attr('class', 'col-md-4 column padding15').attr('info-id', data[i].id);
            var div = $('<div></div>').attr('class', 'basic-info-box');
            div.append($('<h3 class="projectFullName"></h3>').attr('class', 'text-center').text((data[i].projectFullName === undefined ? '' : data[i].projectFullName)));//项目全称
            var totalCost = (data[i].totalCost === undefined ? '' : data[i].totalCost);
            div.append($('<p class="totalCost"></p>').attr('class', 'text-center').text('总造价（万）：' + totalCost));
            div.append($('<p class="ownerOrg"></p>').attr('class', 'text-justify lfpadding15').text('业主单位：' + (data[i].ownerOrg === undefined ? '' : data[i].ownerOrg)));
            div.append($('<p class="mainDesignUnit"></p>').attr('class', 'text-justify lfpadding15').text('设计单位：' + (data[i].mainDesignUnit === undefined ? '' : data[i].mainDesignUnit)));
            div.append($('<p class="useStatus"></p>').attr('class', (data[i].useStatus === '0') ? 'red-font-color text-justify lfpadding15' : 'green-font-color text-justify lfpadding15')
                .attr('useStatus', (data[i].useStatus === undefined ? '' : data[i].useStatus))
                .text('状态：' + (data[i].useType === undefined ? '' : data[i].useType)));
            div.append($('<a></a>').attr('class', 'text-center btn-more').attr('href', 'javascript:void(0);')
                .attr('onclick', 'seeinfo(\'' + data[i].id + '\');')
                .text('项目详情'));
            div.appendTo(column);
            column.appendTo('.basic-info-list');
        }
    }

    /**
     * 渲染分页数据
     * @param {object} data ajaxList获取到的数据
     */
    function renderPage(data) {
        $('.basic-info-page').empty();
        var page = '';// 分页html
        var start = 1;// 上一页
        var end = 1;// 下一页
        page += '<li><a href="javascript:void(0);" aria-label="Previous" onclick="getData(' + start + ', 6);"><span aria-hidden="true">&laquo;</span></a></li>';
        if (data.totalPages === 0) {
            page += '<li><a href="javascript:void(0);" onclick="getData(1, 6);">1</a></li>';
        } else {
            // 只显示5个页数，分别是当前页-+2
            start = data.page > 0 ? (((data.page - 2) > 0) ? (data.page - 2) : 1) : 1;
            end = data.page > 0 ? (((data.page + 2) > data.totalPages) ? data.totalPages : (data.page + 2)) : 1;
            for (var i = start; i <= end; i++ && i < 5) {
                page += '<li' + (i === data.page ? ' class="active"' : '') + '><a href="javascript:void(0);" onclick="getData(' + i + ', 6);">' + i + '</a></li>';
            }
        }
        page += '<li><a href="javascript:void(0);" aria-label="Next" onclick="getData(' + end + ', 6);"><span aria-hidden="true">&raquo;</span></a></li>';

        $('.basic-info-page').append(page);
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
        $.ajax({
            type: 'GET',
            url: '${adminPath}/base/baseprojectbasicinfo/checkOnly?orgId=' + treeId,
            dataType : 'json',
            data: {},
            success: function (msg) {
                if (msg.ret === 0) {
                    openLayer(title, url, gridid, width, height);
                } else {
                    tipInfo(msg);
                }
            },
            complete:function(){
            }
        });
    }

    /**
     * 启用
     */
    function enable(title, url, status) {
        var ids = getCardId();
        var checked = checkArraysLength(ids, 1);
        if (checked) {
            editStatus(title, url, ids, status);
        }
    }

    function editStatus(opt, url, ids, status) {
        if (ids.length > 0) {
            layer.confirm("您确定要" + opt + "这些信息么?请谨慎操作！", {
                title: opt + "提示",
                icon: 3,
                btn: ['确定', '取消'] //按钮
            }, function () {
                //确定
                $.ajax({
                    url: url,
                    type: 'post',
                    data: {
                        ids: ids.join(','),
                        status: status
                    },
                    cache: false,
                    success: function (d) {
                        if (d.ret == 0) {
                            var msg = d.msg;
                            layer.msg(msg, {icon: 1});
                            //刷新表单
                            getData(currentPage, pageS);
                        } else {
                            var msg = d.msg;
                            layer.msg(msg, {icon: 3, shade: 0.3});
                        }
                    }
                });
            }, function () {
                //取消
            });
        } else {
            top.layer.alert('请选择需要删除的数据!', {icon: 0, title: '警告'});
        }
    }

    /**
     * 更新
     */
    function updateDetail(title, url, gridid, width, height) {
        var ids = [];
        $('.padding15>.active').each(function (i, j) {
            ids.push($(this).parent().attr('info-id'));
        });
        if (!ids || ids.length==0) {
            top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
            return;
        }
        if (ids.length>1) {
            top.layer.alert('只能选择一条数据!', {icon: 0, title:'警告'});
            return;
        }
        var id = ids[0];
        var queryParams = {'query.id||eq' : id};
        var json = getAjaxList('${adminPath}/base/baseprojectbasicinfo/ajaxList', queryParams);
        //var checked = checkUseStatus('${adminPath}/base/baseprojectbasicinfo/ajaxList', queryParams);
        var checked = json.results[0].useStatus == 0;
        if (checked) {
            url = url.replace("{id}", id);
            openLayer(title, url, gridid, '100%', '100%');
        } else {
            top.layer.alert('该记录已启用', {icon: 0, title:'提示'});
        }

    }

    // 双击行查看数据
    function dbClickRowFun(rowid) {
        seeDeatil('修改','${adminPath}/base/baseprojectbasicinfo/{id}/update?load=detail', rowid);
    }

    /**
     * 查看
     */
    function seeDeatil(title, url, id) {
        /*var ids = [];
        $('.padding15>.active').each(function (i, j) {
            ids.push($(this).parent().attr('info-id'));
        });
        if (!ids || ids.length==0) {
            top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
            return;
        }
        if (ids.length>1) {
            top.layer.alert('只能选择一条数据!', {icon: 0, title:'警告'});
            return;
        }
        var id = ids[0];*/
        //url = '${adminPath}/base/baseprojectbasicinfo/' + id + '/update?load=detail';
        url = url.replace("{id}", id);
        top.layer.open({
            type : 2,
            area : [ '100%', '100%' ],
            title : title,
            maxmin : true, // 开启最大化最小化按钮
            content : url,
            btn : [ '关闭' ],
            cancel : function(index) {
            }
        });
    }

    /**
     * 查看
     * @param {string} id
     */
    function seeinfo(id) {
        var url = '${adminPath}/base/baseprojectbasicinfo/' + id + '/update?load=detail';
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
     * 删除
     */
    function deleteBatch(title,url,gridId) {
        var ids = [];
        $('.padding15>.active').each(function (i, j) {
            ids.push($(this).parent().attr('info-id'));
        });
        if (!ids || ids.length==0) {
            top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
        } else {
            layer.confirm("只能删除未启用的数据，您确定要删除这些信息么?请谨慎操作！", {
                title:title+"提示",
                icon:3,
                btn: ['确定','取消'] //按钮
            }, function(){
                //确定
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
                            getData(1, pageS);
                        }else{
                            var msg = d.msg;
                            layer.msg(msg, {icon: 3,shade:0.3});
                        }
                    }
                });
            }, function(){
                //取消
            });
        }
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
        //search('qsResponsibilityRegGridIdGrid');
        getData(1, 6);
    }

    /**
     *搜索
     * @param gridId
     */
    function searchBasicInfo(gridId) {
        var queryParams = {};
        var queryFields=$('#queryFields').val();
        queryParams['queryFields'] = queryFields;
        //普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var val = $(this).val();
            if (queryParams[$(this).attr('name')]) {
                val = queryParams[$(this).attr('name')] + "," + $(this).val();
            }
            queryParams[$(this).attr('name')] = val;
        });

        // 普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var condition = $(this).attr('condition');
            if (!condition) {
                condition = "";
            }
            var key = "query." + $(this).attr('name') + "||" + condition;
            queryParams[key] = queryParams[$(this).attr('name')];
        });
        //刷新
        //传入查询条件参数
        $("#"+gridId).jqGrid('setGridParam',{
            datatype:'json',
            postData:queryParams, //发送数据
            page:1
        }).trigger("reloadGrid"); //重新载入
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
     * 打开layer，回调函数是getData
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
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.doSubmit(function(results){

                    tipInfo(results);
                    //判断逻辑并关闭
                    setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
                    //刷新表单
                    getData(currentPage, pageS);

                });

            },
            cancel: function(index){

            }
        });
    }

    /**
     * 获取卡片id，即baseprojectbasicinfo的id
     * @return {array} ids，id数组
     */
    function getCardId() {
        var ids = [];
        $('.padding15>.active').each(function (i, j) {
            ids.push($(this).parent().attr('info-id'));
        });

        return ids;
    }


</script>

</body>
</html>