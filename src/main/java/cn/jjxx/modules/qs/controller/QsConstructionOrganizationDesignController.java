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
import cn.jjxx.modules.qs.entity.QsConstructionOrganizationDesign;
import cn.jjxx.modules.qs.service.IQsConstructionOrganizationDesignService;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.service.IOrganizationService;

/**   
 * @Title: 施工组织设计
 * @Description: 施工组织设计
 * @author jjxx
 * @date 2018-03-07 11:03:57
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/qs/qsconstructionorganizationdesign")
@RequiresPathPermission("qs:qsconstructionorganizationdesign")
public class QsConstructionOrganizationDesignController extends BaseBeanController<QsConstructionOrganizationDesign> {

    @Autowired
    protected IQsConstructionOrganizationDesignService qsConstructionOrganizationDesignService;
    
    @Autowired
	private IOrganizationService organizationService;

    public QsConstructionOrganizationDesign get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return qsConstructionOrganizationDesignService.selectById(id);
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
        EntityWrapper<QsConstructionOrganizationDesign> entityWrapper = new EntityWrapper<QsConstructionOrganizationDesign>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        String orgId = String.valueOf(queryable.getValue("orgId"));
        if(StringUtils.isEmpty(orgId)||"null".equals(orgId)){
        	return ;
        }
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        entityWrapper.setTableAlias("t.");
        PageJson<QsConstructionOrganizationDesign> pagejson = new PageJson<QsConstructionOrganizationDesign>(qsConstructionOrganizationDesignService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

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

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") QsConstructionOrganizationDesign qsConstructionOrganizationDesign, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsConstructionOrganizationDesign, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        QsConstructionOrganizationDesign qsConstructionOrganizationDesign = get(id);
        Organization org = organizationService.selectById(qsConstructionOrganizationDesign.getOrgId());
        String name = "";
        if(org!=null){
        	name = org.getName();
        }
        model.addAttribute("data", qsConstructionOrganizationDesign);
        model.addAttribute("orgName",name);
        model.addAttribute("orgId",qsConstructionOrganizationDesign.getOrgId());
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") QsConstructionOrganizationDesign qsConstructionOrganizationDesign, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsConstructionOrganizationDesign, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(QsConstructionOrganizationDesign qsConstructionOrganizationDesign, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(qsConstructionOrganizationDesign, result)) {
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
            if (StringUtils.isEmpty(qsConstructionOrganizationDesign.getId())) {
                qsConstructionOrganizationDesignService.insert(qsConstructionOrganizationDesign);
            } else {
                qsConstructionOrganizationDesignService.insertOrUpdate(qsConstructionOrganizationDesign);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        ajaxJson.setData(qsConstructionOrganizationDesign);
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            qsConstructionOrganizationDesignService.deleteById(id);
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
            qsConstructionOrganizationDesignService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        QsConstructionOrganizationDesign qsConstructionOrganizationDesign = get(id);
        model.addAttribute("data", qsConstructionOrganizationDesign);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        String orgId = request.getParameter("orgId");
        try {
            EntityWrapper<QsConstructionOrganizationDesign> entityWrapper = new EntityWrapper<QsConstructionOrganizationDesign>(entityClass);
            entityWrapper.eq("orgId", orgId);
            valid = qsConstructionOrganizationDesignService.doValid(duplicateValid,entityWrapper);
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
