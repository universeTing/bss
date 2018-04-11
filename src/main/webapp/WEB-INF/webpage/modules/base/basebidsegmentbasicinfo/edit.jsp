<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>base_bid_segment_basic_info</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput"/>
    <html:css name="simditor"/>
    <html:css  name="iCheck,Validform"/>
    <html:js  name="iCheck,Validform"/>
</head>

<body class="white-bg" formid="baseBidSegmentBasicInfoForm">
<form:form id="baseBidSegmentBasicInfoForm" modelAttribute="data" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="orgId" value="${orgId}"/>
    <ul id="myTab" class="nav nav-pills nav-justified">
        <li class="active"><a href="#base" data-toggle="tab">基本资料</a></li>
        <li><a href="#synopsis" data-toggle="tab">标段简介</a></li>
        <li><a href="#maintech" data-toggle="tab">主要技术指标</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="base" style="padding: 20px;">

            <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer"
                   style="margin-bottom: 0;">
                <tbody>
                <tr>
                    <td class="width-15 active text-right">
                        <label><font color="red">*</font>标段编码:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="bidSegmentNumber" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/basebidsegmentbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label><font color="red">*</font>标段类型:</label>
                    </td>
                    <td class="width-35">
                        <%--<form:input path="bidSegmentType" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/basebidsegmentbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>--%>
                        <form:select path="bidSegmentType" class="width-35" ifChanged="checkTypeChange"
                                     dict="bidSegmentType" delimiter="&nbsp;&nbsp;"
                                     htmlEscape="false" defaultValue="0"
                                     cssClass="i-checks required" />
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label><font color="red">*</font>标段名称:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="bidSegmentFullName" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/basebidsegmentbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label><font color="red">*</font>标段简称:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="bidSegmentShortName" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/basebidsegmentbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>起始桩号:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="startPileNo" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>终止桩号:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="endPileNo" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>标段长度:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="bidSegmentLength" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>断链长度:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="brokenChainLength" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>开工日期:</label>
                    </td>
                    <td class="width-35">
                        <%--<form:input path="startDate" htmlEscape="false" class="form-control"/>--%>
                        <form:input path="startDate" datefmt="yyyy-MM-dd" htmlEscape="false" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="laydate({istime: false, format: 'YYYY-MM-DD'})"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>完工日期:</label>
                    </td>
                    <td class="width-35">
                        <%--<form:input path="endDate" htmlEscape="false" class="form-control"/>--%>
                        <form:input path="endDate" datefmt="yyyy-MM-dd" htmlEscape="false" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="laydate({istime: false, format: 'YYYY-MM-DD'})"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>施工单位:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="constructionUnit" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>监理单位:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="supervisionUnit" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>合同造价（万元）:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="totalCost" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label><font color="red">*</font>合同号:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="contractNumber" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/basebidsegmentbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>建设单位:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="buildUnit" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>其他基本信息:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="otherBaseInfo" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>试验单位:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="testUnit" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>合同期限（月）:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="contractPeriod" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
        <div class="tab-pane fade" id="synopsis" style="padding: 20px;">

            <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer"
                   style="margin-bottom: 0;">
                <tbody>
                <tr>
                    <td class="active text-center" style="width: 15%;">
                        <label>标段简介:</label>
                    </td>
                    <td class="">
                        <form:textarea path="introduction" htmlEscape="false" class="form-control"
                                       style="width:100%;height:500px;resize:none;"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
        <div class="tab-pane fade" id="maintech" style="padding: 20px;">

            <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer"
                   style="margin-bottom: 0;">
                <tbody>
                <tr>
                    <td class="width-15 active text-right">
                        <label>路线长度:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="routeLength" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>路基宽地:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="subgradeWidth" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>路面厚度:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="pavementThickness" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>路面结构类型:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="pavementStructureType" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>桥梁宽度:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="bridgeWidth" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>隧道宽度:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="tunnelWidth" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>路基挖方:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="subgradeExcavation" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>路基填方:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="subgradeFill" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>路基防护工程:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="subgradeProtectionEngineering" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>路基排水工程:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="subgradeDrainageProject" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>沥青砼路面面积:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="roadbedAsphaltConcreteArea" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>水泥砼路面面积:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="roadbedCementConcreteArea" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>特大桥:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="grandBridge" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>大桥:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="greatBridge" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>中桥:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="middleBridge" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>小桥:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="smallBridge" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>涵洞通道:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="culvertChannel" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>特长隧道:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="superLongTunnel" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>长隧道:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="longTunnel" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>中隧道:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="middleTunnel" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>短隧道:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="shortTunnel" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>分离式立体交叉:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="separateGradeCrossing" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active text-right">
                        <label>互通式立体交叉:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="interchange" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="width-15 active text-right">
                        <label>其他:</label>
                    </td>
                    <td class="width-35">
                        <form:input path="other" htmlEscape="false" class="form-control"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>
</form:form>
<html:js name="bootstrap-fileinput"/>
<html:js name="simditor"/>
<script type="text/javascript">
    /**
     * 当标段类型为“施工”时，“试验单位”不可选择。
     * 当标段类型为“监理”时，“施工单位”与“试验单位”不可选择。
     * 当标段类型为“试验”时，“施工单位”与“监理单位”不可选择。
     * 当标段类型为“其他”时，“监理单位”“试验单位”“施工单位”都为可用状态。
     * 若为不可用状态，则显示空文本。
     * 0	施工
     * 1	监理
     * 2	试验
     * 3	其他
     */
    $("#bidSegmentType").change(function(){
        var selected = Number($(this).val());
        checkTypeChange(selected);
    });
    /**
     * 页面加载完成之后触发事件(查看页面)
     * @param title
     * @param url
     * @param width
     * @param height
     * @param createName 创建人 （必填字段，全局变量参数，需从）
     *
     */
    $(function(){
        /**查看页面触发方法*/
        if(location.href.indexOf("load=detai")!=-1){
            //取消页面所有边框及背景颜色
            $(":input").attr("disabled","true");
            $(":input").css({"border":"none","background":"#fff"});
            $(".input-group-btn").remove();
        }
        // 默认为“施工”时，“试验单位”不可选择。
        var bidSegmentType = '${data.bidSegmentType}' === '' ? 0 : '${data.bidSegmentType}';
        checkTypeChange(Number(bidSegmentType));
    });
    
    function checkTypeChange(type) {
        /*$('#supervisionUnit').removeAttr("readonly").val('');
        $('#testUnit').removeAttr("readonly").val('');
        $('#constructionUnit').removeAttr("readonly").val('');*/
        switch (type) {
            case 0:
                $('#testUnit').val('').attr("readonly",true);//试验单位
                break;
            case 1:
                $('#constructionUnit').val('').attr("readonly",true);//施工单位
                $('#testUnit').val('').attr("readonly",true);//试验单位
                break;
            case 2:
                $('#constructionUnit').val('').attr("readonly",true);//施工单位
                $('#supervisionUnit').val('').attr("readonly",true);//监理单位
                break;
            case 3:
                $('#supervisionUnit').val('').attr("readonly",true);//监理单位
                $('#testUnit').val('').attr("readonly",true);//试验单位
                $('#constructionUnit').val('').attr("readonly",true);//施工单位
                break;
        }
    }
    

</script>
</body>
</html>