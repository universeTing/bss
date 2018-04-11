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
import cn.jjxx.modules.qs.entity.QsQualityFormulation;
import cn.jjxx.modules.qs.service.IQsQualityFormulationService;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.service.IOrganizationService;

/**   
 * @Title: 品质工程制度制定详细需求描述
 * @Description: 品质工程制度制定详细需求描述
 * @author jjxx
 * @date 2018-04-03 09:03:57
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/qs/qsqualityformulation")
@RequiresPathPermission("qs:qsqualityformulation")
public class QsQualityFormulationController extends BaseBeanController<QsQualityFormulation> {

    @Autowired
    protected IQsQualityFormulationService qsQualityFormulationService;
    
    @Autowired
	private IOrganizationService organizationService;

	/**
	 * @Description: 根据获取品质工程制度制定详细需求描述的实体数据 .<br>
	 * @param id 主键Id.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    public QsQualityFormulation get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return qsQualityFormulationService.selectById(id);
        } else {
            return newModel();
        }
    }

	/**
	 * @Description: 跳转至品质工程制度制定详细需求描述的列表界面 .<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

	/**
	 * @Description: 品质工程制度制定详细需求描述的列表界面获取列表结果 .<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<QsQualityFormulation> entityWrapper = new EntityWrapper<QsQualityFormulation>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        String orgId = String.valueOf(queryable.getValue("orgId"));
        if(StringUtils.isEmpty(orgId)||"null".equals(orgId)){
        	return ;
        }
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        entityWrapper.setTableAlias("t.");
        PageJson<QsQualityFormulation> pagejson = new PageJson<QsQualityFormulation>(qsQualityFormulationService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

	/**
	 * @Description: 跳转至品质工程制度制定详细需求描述的添加界面 .<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
    	String id = request.getParameter("treeId");
    	String name = request.getParameter("treeName");
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        model.addAttribute("orgId",id);
        model.addAttribute("orgName",name);
        return display("edit");
    }
	
	/**
	 * @Description: 新增品质工程制度制定详细需求描述的数据 .<br>
	 * @param qsQualityFormulation 品质工程制度制定详细需求描述实体类.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") QsQualityFormulation qsQualityFormulation, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsQualityFormulation, request, response, result);
    }

	/**
	 * @Description: 跳转至品质工程制度制定详细需求描述的更新界面 .<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        QsQualityFormulation qsQualityFormulation = get(id);
        model.addAttribute("data", qsQualityFormulation);
        Organization org = organizationService.selectById(qsQualityFormulation.getOrgId());
        String name = "";
        if(org!=null){
        	name = org.getName();
        }
        model.addAttribute("orgName",name);
        model.addAttribute("orgId",qsQualityFormulation.getOrgId());
        return display("edit");
    }

	/**
	 * @Description: 修改品质工程制度制定详细需求描述的数据 .<br>
	 * @param qsQualityFormulation 品质工程制度制定详细需求描述实体类.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") QsQualityFormulation qsQualityFormulation, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsQualityFormulation, request, response, result);
    }

	/**
	 * @Description: 执行保存品质工程制度制定详细需求描述的数据操作 .<br>
	 * @param qsQualityFormulation 品质工程制度制定详细需求描述实体类.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(QsQualityFormulation qsQualityFormulation, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(qsQualityFormulation, result)) {
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
            if (StringUtils.isEmpty(qsQualityFormulation.getId())) {
                qsQualityFormulationService.insert(qsQualityFormulation);
            } else {
                qsQualityFormulationService.insertOrUpdate(qsQualityFormulation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        ajaxJson.setData(qsQualityFormulation);
        return ajaxJson;
    }

	/**
	 * @Description: 单条删除品质工程制度制定详细需求描述 .<br>
	 * @param id 单据Id.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            qsQualityFormulationService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

	/**
	 * @Description: 执行保存品质工程制度制定详细需求描述的数据操作 .<br>
	 * @param ids 单据Id集合.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            qsQualityFormulationService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

	/**
	 * @Description: 跳转至品质工程制度制定详细需求描述的查看界面 .<br>
	 * @param ids 单据Id.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        QsQualityFormulation qsQualityFormulation = get(id);
        model.addAttribute("data", qsQualityFormulation);
        return display("edit");
    }

	/**
	 * @Description: 验证品质工程制度制定详细需求描述功能数据的唯一性 .<br>
	 * @param duplicateValid 校验实体类.<br>
	 * @author jjxx .<br>
	 * @date 2018-04-03 09:03:57.<br>
	 */
    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        String orgId = request.getParameter("orgId");
        try {
            EntityWrapper<QsQualityFormulation> entityWrapper = new EntityWrapper<QsQualityFormulation>(entityClass);
            entityWrapper.eq("orgId", orgId);
            valid = qsQualityFormulationService.doValid(duplicateValid,entityWrapper);
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
