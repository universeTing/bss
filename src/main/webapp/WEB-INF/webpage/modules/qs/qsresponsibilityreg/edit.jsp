<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>质量责任登记表</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput"/>
    <html:css name="simditor"/>

    <style type="text/css">
        .from-title {
            text-align: center;
        }

        .form-control {
            width: 60%;
            display: inline;
        }
    </style>
</head>

<body class="white-bg" formid="qsResponsibilityRegForm" beforeSubmit="beforeSubmit">

<form:form id="qsResponsibilityRegForm" modelAttribute="data" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="orgId" value="${orgId}"/>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 from-title"><h3>公路建设项目参建单位工程安全责任登记表</h3></div>
        </div>
        <div class="row" style="margin-bottom: 10px;">

            <div class="col-sm-4">
                <label for="projectName" class="control-label"><font color="red">*</font>项目名称：</label>
                <form:input id="projectName" path="projectName" htmlEscape="false" class="form-control input-sm"
                            datatype="*" nullmsg="必填"/>
                <label class="Validform_checktip"></label>
            </div>
            <div class="col-sm-4">
                <label for="contractStageNumber" class="control-label"><font color="red">*</font>合同段号：</label>
                <form:input id="contractStageNumber" path="contractStageNumber" htmlEscape="false"
                            class="form-control input-sm" datatype="*" nullmsg="必填"/>
                <label
                    class="Validform_checktip"></label>
            </div>
            <div class="col-sm-4">
                <label for="contractStageNumber" class="control-label"><font color="red">*</font>签章：</label>
                <form:input id="contractStageNumber" path="signature" htmlEscape="false" class="form-control input-sm"
                            datatype="*" nullmsg="必填"/>
                <label class="Validform_checktip"></label>
            </div>

        </div>

        <div class="row">
            <table id="respRegDetailsTable" class="table table-bordered table-hover">
                <tr>
                    <td class="active text-right" style="width: 15%;" colspan="2">
                        <label for="orgName" class="control-label"><font color="red">*</font>单位名称</label>
                    </td>
                    <td style="width: 35%;" colspan="4">
                        <form:input id="orgName" path="orgName" class="form-control input-sm" datatype="*" nullmsg="必填！"/>
                        <label class="Validform_checktip"></label>
                    </td>
                    <td class="active text-right" style="width: 15%;" colspan="2">
                        <label for="workContent" class="control-label"><font color="red">*</font>承担工作内容</label>
                    </td>
                    <td style="width: 35%;" colspan="4">
                        <form:input id="workContent" path="workContent" class="form-control input-sm" datatype="*" nullmsg="必填！"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="active text-right" colspan="2">
                        <label for="qualification" class="control-label"><font color="red">*</font>资质等级及证书编号</label>
                    </td>
                    <td colspan="10">
                        <form:input id="qualification" path="qualification" class="form-control input-sm" datatype="*" nullmsg="必填！"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr class="active">
                    <td class="text-center" colspan="6">安全责任人及专职安全员</td>
                    <td class="text-center" colspan="2">在岗时间</td>
                    <td class="text-center" colspan="2">承担安全责任内容</td>
                    <td class="text-center">责任人签字</td>
                    <td class="text-center">操作</td>
                </tr>
                <%--详情表 begin--%>
                <c:set var="qsResponsibilityRegDetailsList" value="${requestScope.qsResponsibilityRegDetailsList}"/>
                <c:choose>
                    <c:when test="${qsResponsibilityRegDetailsList != null}">
                        <c:forEach items="${qsResponsibilityRegDetailsList}" var="list" varStatus="id">
                            <tr class="respRegDetailsTr_1">
                                <td rowspan="3" class="row_index">1</td>
                                <td class="text-right" colspan="1">
                                    <label for="name_1" class="control-label"><font color="red">*</font>姓名</label>
                                </td>
                                <td colspan="4"><%--姓名--%>
                                    <input id="name_1" name="details_name" value="${list.name}" class="form-control input-sm" datatype="*" nullmsg="必填！"/>
                                    <label class="Validform_checktip"></label>
                                </td>
                                <td rowspan="3" colspan="2" style="padding:0;"><%--在岗时间--%>
                                    <textarea id="onTheJobDate_1" name="details_onTheJobDate" class="form-control" maxlength="80" datatype="*" nullmsg="必填！" style="width:100%;height:100px;resize:none;">${list.onTheJobDate}</textarea>
                                    <label class="Validform_checktip"></label>
                                </td>
                                <td rowspan="3" colspan="2" style="padding:0;"><%--承担安全责任内容--%>
                                    <textarea id="workContent_1" name="details_workContent" class="form-control" maxlength="500" datatype="*" nullmsg="必填！" style="width:100%;height:100px;resize:none;">${list.workContent}</textarea>
                                    <label class="Validform_checktip"></label>
                                </td>
                                <td rowspan="3" style="padding:0;"><%--责任人签字--%>
                                    <textarea id="signature_1" name="details_signature" class="form-control" maxlength="50" datatype="*" nullmsg="必填！" style="width:100%;height:100px;resize:none;">${list.signature}</textarea>
                                    <label class="Validform_checktip"></label>
                                </td>
                                <td rowspan="3"><%--操作按钮--%>
                                    <button type="button" class="btn btn-primary btn-lg btn-block btn-sm" onclick="addRow(this);">+</button>
                                    <button type="button" class="btn btn-default btn-lg btn-block btn-sm" onclick="delRow(this);">-</button>
                                </td>
                            </tr>
                            <tr class="respRegDetailsTr_1">
                                <td class="text-right" colspan="1">
                                    <label for="idcard_1" bs-navbar="idcard_1" class="control-label"><font color="red">*</font>身份证号</label>
                                </td>
                                <td colspan="4"><%--身份证号--%>
                                    <input id="idcard_1" name="details_idcard" value="${list.idcard}" class="form-control input-sm" datatype="*" nullmsg="必填！"/>
                                    <label class="Validform_checktip"></label>
                                </td>
                            </tr>
                            <tr class="respRegDetailsTr_1">
                                <td class="text-right" colspan="1">
                                    <label for="qualification_1" class="control-label"><font color="red">*</font>职称及证书编号</label>
                                </td>
                                <td colspan="4"><%--职称及证书编号--%>
                                    <input id="qualification_1" name="details_qualification" value="${list.qualification}" class="form-control input-sm" datatype="*" nullmsg="必填！"/>
                                    <label class="Validform_checktip"></label>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr class="respRegDetailsTr_1">
                            <td rowspan="3" class="row_index">1</td>
                            <td class="text-right" colspan="1">
                                <label for="name_1" class="control-label"><font color="red">*</font>姓名</label>
                            </td>
                            <td colspan="4"><%--姓名--%>
                                <input id="name_1" name="details_name" class="form-control input-sm" datatype="*" nullmsg="必填！"/>
                                <label class="Validform_checktip"></label>
                            </td>
                            <td rowspan="3" colspan="2" style="padding:0;"><%--在岗时间--%>
                                <textarea id="onTheJobDate_1" name="details_onTheJobDate" class="form-control" maxlength="80" datatype="*" nullmsg="必填！" style="width:100%;height:100px;resize:none;"></textarea>
                                <label class="Validform_checktip"></label>
                            </td>
                            <td rowspan="3" colspan="2" style="padding:0;"><%--承担安全责任内容--%>
                                <textarea id="workContent_1" name="details_workContent" class="form-control" maxlength="500" datatype="*" nullmsg="必填！" style="width:100%;height:100px;resize:none;"></textarea>
                                <label class="Validform_checktip"></label>
                            </td>
                            <td rowspan="3" style="padding:0;"><%--责任人签字--%>
                                <textarea id="signature_1" name="details_signature" class="form-control" maxlength="50" datatype="*" nullmsg="必填！" style="width:100%;height:100px;resize:none;"></textarea>
                                <label class="Validform_checktip"></label>
                            </td>
                            <td rowspan="3"><%--操作按钮--%>
                                <button type="button" class="btn btn-primary btn-lg btn-block btn-sm" onclick="addRow(this);">+</button>
                                <button type="button" class="btn btn-default btn-lg btn-block btn-sm" onclick="delRow(this);">-</button>
                            </td>
                        </tr>
                        <tr class="respRegDetailsTr_1">
                            <td class="text-right" colspan="1">
                                <label for="idcard_1" bs-navbar="idcard_1" class="control-label"><font color="red">*</font>身份证号</label>
                            </td>
                            <td colspan="4"><%--身份证号--%>
                                <input id="idcard_1" name="details_idcard" class="form-control input-sm" datatype="*" nullmsg="必填！"/>
                                <label class="Validform_checktip"></label>
                            </td>
                        </tr>
                        <tr class="respRegDetailsTr_1">
                            <td class="text-right" colspan="1">
                                <label for="qualification_1" class="control-label"><font color="red">*</font>职称及证书编号</label>
                            </td>
                            <td colspan="4"><%--职称及证书编号--%>
                                <input id="qualification_1" name="details_qualification" class="form-control input-sm" datatype="*" nullmsg="必填！"/>
                                <label class="Validform_checktip"></label>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <%--详情表 end--%>
                <tr>
                    <td class="active text-right" colspan="2">
                        <label>备注</label>
                    </td>
                    <td colspan="10">
                        <form:textarea path="remarks" htmlEscape="false" class="form-control" style="width:100%;height:100px;resize:none;"/>
                        <label class="Validform_checktip"></label>
                    </td>
                </tr>
                <tr>
                    <td class="active text-right" colspan="2">
                        <label>附件</label>
                    </td>
                    <td colspan="10">
                        <input id="relateTable" type="hidden" value="qs_responsibility_reg"/>
                        <input id="relateFeild" type="hidden" value="attachment"/>
                        <form:fileinput buttonText="选择附件" saveType="billId" multiple="true"
                                        fileActionSettings="{showRemove: true,showUpload: false}"
                                        isFormSubmit="true" uploadExtraFieldData="relateTable,relateFeild"
                                        showUpload="false" showRemove="false" autoUpload="false"
                                        fileInputWidth="100px"  fileInputHeight="100px"  path="attachment"
                                        htmlEscape="false" class="form-control"/>
                    </td>
                </tr>
            </table>
        </div>

    </div>

</form:form>

<html:js name="bootstrap-fileinput"/>
<html:js name="simditor"/>
<!-- 全局js -->
<html:js name="iCheck,Validform,markdown,syntaxhighlighter"/>
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
<script>

    $(function(){
        rowIndexReset();
    });

    //详情的行号
    var row_index = undefined;
    //总行数
    var length = undefined;
    //总行号
    var rowLength = undefined;

    function addRow(obj) {
        //行号
        row_index = Number($(obj).parent().parent().find('td:eq(0)').text());
        var row1 = clearVal($($('.respRegDetailsTr_' + row_index)[0]).clone());
        var row2 = clearVal($($('.respRegDetailsTr_' + row_index)[1]).clone());
        var row3 = clearVal($($('.respRegDetailsTr_' + row_index)[2]).clone());
        $('.respRegDetailsTr_' + row_index + ':last').after(row3).after(row2).after(row1);
        //修改其他行序号
        rowIndexReset();
    }
    function delRow(obj) {
        //行号
        row_index = Number($(obj).parent().parent().find('td:eq(0)').text());
        //总行数
        length = $('#respRegDetailsTable tr[class^="respRegDetailsTr_"]').length;
        //总行号
        rowLength = length / 3;
        if (rowLength > 1) {
            $('.respRegDetailsTr_' + row_index).remove();
        } else {
            clearVal($('.respRegDetailsTr_' + rowLength));
        }
        rowIndexReset();
    }
    /**
     * 修改行号、id、name值
     * @author mali
     * @date 2018-03-22
     */
    function rowIndexReset() {
        //总行数
        length = $('#respRegDetailsTable tr[class^="respRegDetailsTr_"]').length;
        //总行号
        rowLength = length / 3;
        for (var i = 1, j = 1; i <= length, j <= rowLength; i++) {
            //修改序号
            $('.row_index:eq(' + (i-1) + ')').text(i);
            var row = $('#respRegDetailsTable tr[class^="respRegDetailsTr_"]:eq(' + (i-1) + ')');
            //修改tr的class，各属性
            row.attr('class', 'respRegDetailsTr_' + j);
            row.find('label[for^="name_"]').attr('for', 'name_' + j);
            row.find('input[id^="name_"]').attr('id', 'name_' + j);
            row.find('textarea[id^="onTheJobDate_"]').attr('id', 'onTheJobDate_' + j);
            row.find('textarea[id^="workContent_"]').attr('id', 'workContent_' + j);
            row.find('textarea[id^="signature_"]').attr('id', 'signature_' + j);

            row.find('label[for^="idcard_"]').attr('for', 'idcard_' + j);
            row.find('input[id^="idcard_"]').attr('id', 'idcard_' + j);

            row.find('label[for^="qualification_"]').attr('for', 'qualification_' + j);
            row.find('input[id^="qualification_"]').attr('id', 'qualification_' + j);
            if (i % 3 === 0) {
                j++;
            }
        }
    }

    /**
     * 清除所有input的值
     * @param {object} obj 需要拷贝的对象
     * @author mali
     * @date 2018-03-22
     * @return {object} obj 清除所有input的值之后的对象
     */
    function clearVal(obj) {
        obj.find(":input").each(function(i){
            if($(this).attr("type")!="button"){//不是button的
                $(this).val("");
            }
        });
        return obj;
    }


    $(document).ready(function () {
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            resizeGrid();
        });
    });
    $(function () {
        $(window).resize(function () {
            resizeGrid();
        });
    });

    function resizeGrid() {
        $("#qsResponsibilityRegDetailsGrid").setGridWidth($(window).width() - 60);
    }

    /**
     * 提交前的回调方法
     */
    function beforeSubmit(curform) {
        //这里最好还是使用JSON提交，控制器改变提交方法，并使用JSON方式来解析数
        //通过判断，如果有问题不提交
        var fromtable = $(".formsub").Validform();
        var validResult = fromtable.check(false);
        if (validResult) {//验证通过
            //总行数
            length = $('#respRegDetailsTable tr[class^="respRegDetailsTr_"]').length;
            //总行号
            rowLength = length / 3;
            var rowJson = [];
            for (var i = 1; i <= rowLength; i++) {
                var row1 = $($('.respRegDetailsTr_' + i)[0]);
                var row2 = $($('.respRegDetailsTr_' + i)[1]);
                var row3 = $($('.respRegDetailsTr_' + i)[2]);
                rowJson.push({
                    "name" : row1.find('#name_' + i).val(),
                    "onTheJobDate" : row1.find('#onTheJobDate_' + i).val(),
                    "workContent" : row1.find('#workContent_' + i).val(),
                    "signature" : row1.find('#signature_' + i).val(),
                    "idcard" : row2.find('#idcard_' + i).val(),
                    "qualification" : row3.find('#qualification_' + i).val()
                });
            }
            var respRegDetailsListJson = $('#qsResponsibilityRegDetailsListJson').val();
            if (respRegDetailsListJson === undefined || respRegDetailsListJson === '') {
                var rowInput = $('<input id="qsResponsibilityRegDetailsListJson" type="hidden" name="qsResponsibilityRegDetailsListJson" />');
                rowInput.attr('value', JSON.stringify(rowJson));
                // 附加到Form
                curform.append(rowInput);
            } else {
                $('#' + gridId + "ListJson").val(JSON.stringify(rowJson));
            }
        }
        return validResult;
    }

</script>
</body>
</html>