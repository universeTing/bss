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
import cn.jjxx.modules.qs.entity.QsQualityProjectSummary;
import cn.jjxx.modules.qs.entity.QsSupervisionImplementationDetails;
import cn.jjxx.modules.qs.service.IQsQualityProjectSummaryService;

/**   
 * @Title: qs_quality_project_summary
 * @Description: qs_quality_project_summary
 * @author jjxx
 * @date 2018-04-03 11:20:01
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/qs/qsqualityprojectsummary")
@RequiresPathPermission("qs:qsqualityprojectsummary")
public class QsQualityProjectSummaryController extends BaseBeanController<QsQualityProjectSummary> {

    @Autowired
    protected IQsQualityProjectSummaryService qsQualityProjectSummaryService;

	/**
	 * @Description: 根据获取qs_quality_project_summary的实体数据 .<br>
	 * @param id 主键Id.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    public QsQualityProjectSummary get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return qsQualityProjectSummaryService.selectById(id);
        } else {
            return newModel();
        }
    }

	/**
	 * @Description: 跳转至qs_quality_project_summary的列表界面 .<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

	/**
	 * @Description: qs_quality_project_summary的列表界面获取列表结果 .<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<QsQualityProjectSummary> entityWrapper = new EntityWrapper<QsQualityProjectSummary>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        entityWrapper.eq("1", 1);
        entityWrapper.setTableAlias("t.");
        String orgId = String.valueOf(queryable.getValue("orgId"));
        if(StringUtils.isEmpty(orgId)||"null".equals(orgId)){
        	return ;
        }
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<QsQualityProjectSummary> pagejson = new PageJson<QsQualityProjectSummary>(qsQualityProjectSummaryService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

	/**
	 * @Description: 跳转至qs_quality_project_summary的添加界面 .<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
    	String name = request.getParameter("treeName");		  //获取组织名(前端通过路径传入参数)
    	String id = request.getParameter("orgId");		  //获取组织Id(前端通过路径传入参数)
    	QsQualityProjectSummary qsQualityProjectSummary=new QsQualityProjectSummary();
    	qsQualityProjectSummary.setOrgName(name);	//设置实体的组织名属性值
    	qsQualityProjectSummary.setOrgId(id); //传入组织ID,方便做标题的唯一性验证 by huangqiling 2018-3-13
        model.addAttribute("data",qsQualityProjectSummary);//将实体qsQualityProjectSummary绑定到data属性
        return display("edit");
    }
	
	/**
	 * @Description: 新增qs_quality_project_summary的数据 .<br>
	 * @param qsQualityProjectSummary qs_quality_project_summary实体类.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") QsQualityProjectSummary qsQualityProjectSummary, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsQualityProjectSummary, request, response, result);
    }

	/**
	 * @Description: 跳转至qs_quality_project_summary的更新界面 .<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        QsQualityProjectSummary qsQualityProjectSummary = get(id);
        model.addAttribute("data", qsQualityProjectSummary);
        return display("edit");
    }

	/**
	 * @Description: 修改qs_quality_project_summary的数据 .<br>
	 * @param qsQualityProjectSummary qs_quality_project_summary实体类.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") QsQualityProjectSummary qsQualityProjectSummary, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsQualityProjectSummary, request, response, result);
    }

	/**
	 * @Description: 执行保存qs_quality_project_summary的数据操作 .<br>
	 * @param qsQualityProjectSummary qs_quality_project_summary实体类.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(QsQualityProjectSummary qsQualityProjectSummary, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(qsQualityProjectSummary, result)) {
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
            if (StringUtils.isEmpty(qsQualityProjectSummary.getId())) {
                qsQualityProjectSummaryService.insert(qsQualityProjectSummary);
            } else {
                qsQualityProjectSummaryService.insertOrUpdate(qsQualityProjectSummary);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        ajaxJson.setData(qsQualityProjectSummary);
        return ajaxJson;
    }

	/**
	 * @Description: 单条删除qs_quality_project_summary .<br>
	 * @param id 单据Id.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            qsQualityProjectSummaryService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

	/**
	 * @Description: 执行保存qs_quality_project_summary的数据操作 .<br>
	 * @param ids 单据Id集合.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            qsQualityProjectSummaryService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

	/**
	 * @Description: 跳转至qs_quality_project_summary的查看界面 .<br>
	 * @param ids 单据Id.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        QsQualityProjectSummary qsQualityProjectSummary = get(id);
        model.addAttribute("data", qsQualityProjectSummary);
        return display("edit");
    }

	/**
	 * @Description: 验证qs_quality_project_summary功能数据的唯一性 .<br>
	 * @param duplicateValid 校验实体类.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 11:20:01.<br>
	 */
    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<QsQualityProjectSummary> entityWrapper = new EntityWrapper<QsQualityProjectSummary>(entityClass);
            valid = qsQualityProjectSummaryService.doValid(duplicateValid,entityWrapper);
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
