/**
 * 检查数组长度
 * @param {array} arrays 待检查数组
 * @param {number} choice 最大长度
 */
function checkArraysLength(arrays, choice) {
    if (!arrays || arrays.length == 0) {
        top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
        return false;
    }
    if (!choice) {
        return true;
    } else {
        if (arrays.length > choice) {
            top.layer.alert('只能选择' + toZhDigit(choice) + '条数据!', {icon: 0, title:'警告'});
            return false;
        } else {
            return true;
        }
    }
}

/**
 * 阿拉伯数字转中文数字,
 * 如果传入数字时则最多处理到21位，超过21位js会自动将数字表示成科学计数法，导致精度丢失和处理出错
 * 传入数字字符串则没有限制
 * @param {number|string} digit
 */
function toZhDigit(digit) {
    digit = typeof digit === 'number' ? String(digit) : digit;
    const zh = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
    const unit = ['千', '百', '十', ''];
    const quot = ['万', '亿', '兆', '京', '垓', '秭', '穰', '沟', '涧', '正', '载', '极', '恒河沙', '阿僧祗', '那由他', '不可思议', '无量', '大数'];

    var breakLen = Math.ceil(digit.length / 4);
    var notBreakSegment = digit.length % 4 || 4;
    var segment;
    var zeroFlag = [], allZeroFlag = [];
    var result = '';

    while (breakLen > 0) {
        if (!result) { // 第一次执行
            segment = digit.slice(0, notBreakSegment);
            var segmentLen = segment.length;
            for (var i = 0; i < segmentLen; i++) {
                if (segment[i] != 0) {
                    if (zeroFlag.length > 0) {
                        result += '零' + zh[segment[i]] + unit[4 - segmentLen + i];
                        // 判断是否需要加上 quot 单位
                        if (i === segmentLen - 1 && breakLen > 1) {
                            result += quot[breakLen - 2];
                        }
                        zeroFlag.length = 0;
                    } else {
                        result += zh[segment[i]] + unit[4 - segmentLen + i];
                        if (i === segmentLen - 1 && breakLen > 1) {
                            result += quot[breakLen - 2];
                        }
                    }
                } else {
                    // 处理为 0 的情形
                    if (segmentLen == 1) {
                        result += zh[segment[i]];
                        break;
                    }
                    zeroFlag.push(segment[i]);
                    continue;
                }
            }
        } else {
            segment = digit.slice(notBreakSegment, notBreakSegment + 4);
            notBreakSegment += 4;

            for (var j = 0; j < segment.length; j++) {
                if (segment[j] != 0) {
                    if (zeroFlag.length > 0) {
                        // 第一次执行zeroFlag长度不为0，说明上一个分区最后有0待处理
                        if (j === 0) {
                            result += quot[breakLen - 1] + zh[segment[j]] + unit[j];
                        } else {
                            result += '零' + zh[segment[j]] + unit[j];
                        }
                        zeroFlag.length = 0;
                    } else {
                        result += zh[segment[j]] + unit[j];
                    }
                    // 判断是否需要加上 quot 单位
                    if (j === segment.length - 1 && breakLen > 1) {
                        result += quot[breakLen - 2];
                    }
                } else {
                    // 第一次执行如果zeroFlag长度不为0, 且上一划分不全为0
                    if (j === 0 && zeroFlag.length > 0 && allZeroFlag.length === 0) {
                        result += quot[breakLen - 1];
                        zeroFlag.length = 0;
                        zeroFlag.push(segment[j]);
                    } else if (allZeroFlag.length > 0) {
                        // 执行到最后
                        if (breakLen == 1) {
                            result += '';
                        } else {
                            zeroFlag.length = 0;
                        }
                    } else {
                        zeroFlag.push(segment[j]);
                    }

                    if (j === segment.length - 1 && zeroFlag.length === 4 && breakLen !== 1) {
                        // 如果执行到末尾
                        if (breakLen === 1) {
                            allZeroFlag.length = 0;
                            zeroFlag.length = 0;
                            result += quot[breakLen - 1];
                        } else {
                            allZeroFlag.push(segment[j]);
                        }
                    }
                    continue;
                }
            }


            --breakLen;
        }

        return result;
    }
}

/**
 * 根据URL、查询条件获取列表信息
 * @param {string} url 格式：'${adminPath}/base/basebidsegmentbasicinfo/ajaxList'
 * @param {json} queryParams 格式：{'page.size' : pageSize, 'page.pn' : pagePn, queryFields : 'id,name,', 'query.orgId||eq' : $('#treeId').val()}
 * 可选参数：`page.size`、`page.pn`
 * 必要参数：返回字段queryFields（结尾要有逗号），查询条件`quyer.orgId||eq`
 * 查询条件参考cn.jjxx.core.query.data.Condition.Operator
 * @return {json} 列表信息，默认是limit 1,10
 */
function getAjaxList(url, queryParams) {
    var json = '';
    $.ajax({
        async : false,
        url : url,
        type : 'get',
        data : queryParams,
        cache : false,
        success : function(data) {
            json = data;
        }
    });
    return json;
}

/**
 * 单表停用启用
 * @param opt '停用'/'启用'
 * @param url controller地址
 * @param gridId 表格id
 * @param status 停用启用状态
 */
function updateStatus(opt, url, gridId, status) {
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
        layer.confirm("您确定要" + opt + "这些信息么?请谨慎操作！", {
            title: opt + "提示",
            icon: 3,
            btn: ['确定', '取消'] //按钮
        }, function () {
            //确定
            for ( var i = 0; i < rows.length; i++) {
                ids.push(rows[i]);
            }
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
                        refreshTable(gridId);
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
        top.layer.alert('请选择需要' + opt + '的数据!', {icon: 0, title: '警告'});
    }
}