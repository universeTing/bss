<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>base_project_basic_info</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput"/>
    <html:css name="simditor"/>
</head>

<body class="white-bg" formid="baseProjectBasicInfoForm">
<div class="container-fluid" style="width: 80%;">
    <form:form id="baseProjectBasicInfoForm" modelAttribute="data" method="post" class="form-horizontal">
        <form:hidden path="id"/>
        <form:hidden path="orgId" value="${orgId}"/>
        <ul id="myTab" class="nav nav-pills nav-justified">
            <li class="active"><a href="#base" data-toggle="tab">基本资料</a></li>
            <li><a href="#synopsis" data-toggle="tab">项目简介</a></li>
            <li><a href="#maintech" data-toggle="tab">主要技术指标</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="base" style="padding: 20px;">

                <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer"
                       style="margin-bottom: 0;">
                    <tbody>
                    <tr>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>项目编号:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="projectNumber" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/baseprojectbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>项目全称:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="projectFullName" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/baseprojectbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>项目简称:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="projectShortName" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/baseprojectbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>业主单位:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="ownerOrg" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/baseprojectbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>起点名称:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="startPointName" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/baseprojectbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>终点名称:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="endPointName" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/baseprojectbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>起始桩号:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="startPileNo" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/baseprojectbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>终点桩号:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="endPileNo" htmlEscape="false" class="form-control" datatype="*" ajaxurl="${adminPath}/base/baseprojectbasicinfo/validate?orgId=${orgId}" validErrorMsg="重复"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>计划开始日期:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="startPlanDate" datefmt="yyyy-MM-dd" htmlEscape="false" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="laydate({format: 'YYYY-MM-DD', istime: false})"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>计划完工日期:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="endPlanDate" datefmt="yyyy-MM-dd" htmlEscape="false" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="laydate({format: 'YYYY-MM-DD', istime: false})"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>实际开工日期:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="startActualDate" datefmt="yyyy-MM-dd" htmlEscape="false" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="laydate({format: 'YYYY-MM-DD', istime: false})"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>实际结束日期:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="endActualDate" datefmt="yyyy-MM-dd" htmlEscape="false" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="laydate({format: 'YYYY-MM-DD', istime: false})"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>法人代表:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="legalRepresentative" htmlEscape="false" class="form-control"
                                        datatype="*"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>总造价（万）:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="totalCost" htmlEscape="false" class="form-control" datatype="*"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>建设性质:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="constructionType" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>投资方式:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="investmentMode" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label><font color="red">*</font>主要设计单位:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="mainDesignUnit" htmlEscape="false" class="form-control" datatype="*"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>单位造价:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="unitCost" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>工程类别:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="projectCategory" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>其他信息:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="otherInfor" htmlEscape="false" class="form-control"/>
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
                            <label>项目简介:</label>
                        </td>
                        <td class="">
                            <form:textarea path="projectIntroduction" htmlEscape="false" class="form-control"
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
                            <label>技术等级:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="technicalLevel" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>路线长度:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="routeLength" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>路基挖方:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="roadbedCut" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>路基防护工程:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="roadbedProtectiveEngineering" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>路基排水工程:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="roadbedDrainageWorks" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>路基宽度:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="roadbedWidth" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>路基填方:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="roadbedFill" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>起讫桩号:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="odPileNo" htmlEscape="false" class="form-control"/>
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
                            <label>桥梁宽度:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="bridgeWidth" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>设计载荷:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="designLoad" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>设计车速:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="designSpeed" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>涵洞通道:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="culvertChannel" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>分离式立体交叉:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="separateGradeCrossing" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>互通式立体交叉:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="interchange" htmlEscape="false" class="form-control"/>
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
                            <label>水泥砼路面面积:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="roadbedCementConcreteArea" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>沥青砼路面面积:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="roadbedAsphaltConcreteArea" htmlEscape="false" class="form-control"/>
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
                            <label>特长隧道:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="superLongTunnel" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-15 active text-right">
                            <label>隧道宽度:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="tunnelWidth" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                        <td class="width-15 active text-right">
                            <label>其他:</label>
                        </td>
                        <td class="width-35">
                            <form:input path="other" htmlEscape="false" class="form-control"/>
                            <label class="Validform_checktip"></label>
                        </td>
                    </tbody>
                </table>

            </div>
        </div>
    </form:form>
</div>
<html:js name="bootstrap-fileinput"/>
<html:js name="simditor"/>
<!-- 全局js -->
<html:js name="iCheck,Validform,markdown,syntaxhighlighter"/>
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
<script type="text/javascript">
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
            //追加显示创建人及创建时间
            /*createName = createName==undefined?"":createName;
            if(createDate==undefined){
                createDate = "";
            }else{
                createDate = getTaskTime(createDate);
            }
            var str =  '<div style="margin-top: -28px;">';
            str += '<div style="float: left;margin-left: 9px;">创建人：'+createName+'</div>';
            str += '<div style="float: right;margin-right: 9px;">创建时间：'+createDate+'</div>';
            str += '</div>';
            $("body").append($(str));*/

        }
    });
</script>
</body>
</html>