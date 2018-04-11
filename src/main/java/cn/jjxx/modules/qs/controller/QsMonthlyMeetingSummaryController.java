package cn.jjxx.modules.qs.controller;

import cn.jjxx.core.common.data.DuplicateValid;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.model.ValidJson;
import cn.jjxx.core.query.annotation.PageableDefaults;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.utils.QueryableConvertUtils;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import cn.jjxx.core.common.controller.BaseBeanController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import cn.jjxx.modules.qs.entity.QsMonthlyMeetingSummary;
import cn.jjxx.modules.qs.service.IQsMonthlyMeetingSummaryService;

/**   
 * @Title: 质量月例会会议纪要
 * @Description: 质量月例会会议纪要
 * @author jjxx
 * @date 2018-03-20 11:46:49
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/qs/qsmonthlymeetingsummary")
@RequiresPathPermission("qs:qsmonthlymeetingsummary")
public class QsMonthlyMeetingSummaryController extends BaseBeanController<QsMonthlyMeetingSummary> {

    @Autowired
    protected IQsMonthlyMeetingSummaryService qsMonthlyMeetingSummaryService;

    public QsMonthlyMeetingSummary get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return qsMonthlyMeetingSummaryService.selectById(id);//是传入xml的参数
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<QsMonthlyMeetingSummary> entityWrapper = new EntityWrapper<QsMonthlyMeetingSummary>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        String orgId = String.valueOf(queryable.getValue("orgId"));
        if(StringUtils.isEmpty(orgId)||"null".equals(orgId)){
        	return ;
        }
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        entityWrapper.setTableAlias("t.");
        entityWrapper.eq("del_flag", "0");
       
        PageJson<QsMonthlyMeetingSummary> pagejson = new PageJson<QsMonthlyMeetingSummary>(qsMonthlyMeetingSummaryService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
    	String name = request.getParameter("treeName");		  //获取组织名(前端通过路径传入参数)
    	//String id = request.getParameter("orgId");		  //获取组织Id(前端通过路径传入参数)
    	QsMonthlyMeetingSummary qsMonthlyMeetingSummary = new QsMonthlyMeetingSummary();
    	qsMonthlyMeetingSummary.setOrgName(name);	//设置实体的组织名属性值
        model.addAttribute("data",qsMonthlyMeetingSummary);
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") QsMonthlyMeetingSummary qsMonthlyMeetingSummary, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsMonthlyMeetingSummary, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        QsMonthlyMeetingSummary qsMonthlyMeetingSummary = get(id);
        model.addAttribute("data", qsMonthlyMeetingSummary);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") QsMonthlyMeetingSummary qsMonthlyMeetingSummary, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsMonthlyMeetingSummary, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(QsMonthlyMeetingSummary qsMonthlyMeetingSummary, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(qsMonthlyMeetingSummary, result)) {
            // 错误提示
            String errorMsg = errorMsg(result);
            if (!StringUtils.isEmpty(errorMsg)) {
                ajaxJson.fail(errorMsg);
            } else {
                ajaxJson.fail("保存失败");
            }
            return ajaxJson;
        }
        try {
            if (StringUtils.isEmpty(qsMonthlyMeetingSummary.getId())) {
                qsMonthlyMeetingSummaryService.insert(qsMonthlyMeetingSummary);
            } else {
                qsMonthlyMeetingSummaryService.insertOrUpdate(qsMonthlyMeetingSummary);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        ajaxJson.setData(qsMonthlyMeetingSummary);//上传附件一定要返回数据，要不然一直等待数据而无法关闭窗口
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            qsMonthlyMeetingSummaryService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            qsMonthlyMeetingSummaryService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        QsMonthlyMeetingSummary qsMonthlyMeetingSummary = get(id);
        model.addAttribute("data", qsMonthlyMeetingSummary);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<QsMonthlyMeetingSummary> entityWrapper = new EntityWrapper<QsMonthlyMeetingSummary>(entityClass);
            valid = qsMonthlyMeetingSummaryService.doValid(duplicateValid,entityWrapper);
            if (valid) {
                validJson.setStatus("y");
                validJson.setInfo("验证通过!");
            } else {
                validJson.setStatus("n");
                if (!StringUtils.isEmpty(duplicateValid.getErrorMsg())) {
                    validJson.setInfo(duplicateValid.getErrorMsg());
                } else {
                    validJson.setInfo("当前信息重复!");
                }
            }
        } catch (Exception e) {
            validJson.setStatus("n");
            validJson.setInfo("验证异常，请检查字段是否正确!");
        }
        return validJson;
    }
}
