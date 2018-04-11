package cn.jjxx.modules.base.controller;

import cn.jjxx.core.common.data.DuplicateValid;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.model.ValidJson;
import cn.jjxx.core.query.annotation.PageableDefaults;
import cn.jjxx.core.query.data.Page;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.utils.QueryableConvertUtils;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.entity.Dict;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.entity.UserOrganization;
import cn.jjxx.modules.sys.service.IOrganizationService;
import cn.jjxx.modules.sys.utils.DictUtils;
import cn.jjxx.modules.sys.utils.UserUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.google.common.collect.Lists;
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
import java.util.*;

import cn.jjxx.modules.base.entity.BaseProjectBasicInfo;
import cn.jjxx.modules.base.service.IBaseProjectBasicInfoService;

/**   
 * @Title: base_project_basic_info
 * @Description: base_project_basic_info
 * @author jjxx
 * @date 2018-03-23 13:50:40
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/base/baseprojectbasicinfo")
@RequiresPathPermission("base:baseprojectbasicinfo")
public class BaseProjectBasicInfoController extends BaseBeanController<BaseProjectBasicInfo> {

    @Autowired
    protected IBaseProjectBasicInfoService baseProjectBasicInfoService;

    @Autowired
    protected IOrganizationService iOrganizationService;

    public BaseProjectBasicInfo get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return baseProjectBasicInfoService.selectById(id);
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
    @PageableDefaults(sort = "org.code=asc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<BaseProjectBasicInfo> entityWrapper = new EntityWrapper<BaseProjectBasicInfo>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        propertyPreFilterable.addQueryProperty("projectFullName");
        propertyPreFilterable.addQueryProperty("totalCost");
        propertyPreFilterable.addQueryProperty("ownerOrg");
        propertyPreFilterable.addQueryProperty("mainDesignUnit");
        propertyPreFilterable.addQueryProperty("useStatus");
        propertyPreFilterable.addQueryProperty("useType");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        entityWrapper.setTableAlias("t.");
        Page<BaseProjectBasicInfo> pageLists = baseProjectBasicInfoService.list(queryable,entityWrapper);
        List<BaseProjectBasicInfo> BaseProjectBasicInfoLists = pageLists.getContent();
        List<Dict> dicts = DictUtils.getDictList("useType");
        for (BaseProjectBasicInfo baseProjectBasicInfo : BaseProjectBasicInfoLists) {
            for (Dict dict : dicts) {
                if (baseProjectBasicInfo.getUseStatus().equals(dict.getValue())) {
                    baseProjectBasicInfo.setUseType(dict.getLabel());
                }
            }
        }
        PageJson<BaseProjectBasicInfo> pagejson = new PageJson<BaseProjectBasicInfo>(pageLists);
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    /**
     * 每个末级机构只能有一条数据
     * @param orgId 组织id
     * @param request
     * @param response
     * @return AjaxJson
     * @throws IOException
     */
    @RequestMapping(value = "checkOnly", method = { RequestMethod.GET })
    @ResponseBody
    private AjaxJson checkOnly(@RequestParam(required = true, value = "orgId") String orgId, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        AjaxJson ajaxJson = new AjaxJson();
        if (org.apache.commons.lang3.StringUtils.isBlank(orgId)) {
            ajaxJson.fail("请选择组织");
        } else {
            boolean flag = baseProjectBasicInfoService.checkOnlyByOrgId(orgId);
            if (flag) {
                ajaxJson.success("验证通过");
            } else {
                ajaxJson.fail("该组织下已存在数据");
            }
        }
        return ajaxJson;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
        String orgId = request.getParameter("treeId");
        String orgName = request.getParameter("treeName");
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        model.addAttribute("orgId", orgId);
        model.addAttribute("orgName", orgName);
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") BaseProjectBasicInfo baseProjectBasicInfo, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(baseProjectBasicInfo, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        BaseProjectBasicInfo baseProjectBasicInfo = get(id);
        model.addAttribute("data", baseProjectBasicInfo);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") BaseProjectBasicInfo baseProjectBasicInfo, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(baseProjectBasicInfo, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(BaseProjectBasicInfo baseProjectBasicInfo, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(baseProjectBasicInfo, result)) {
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
            if (StringUtils.isEmpty(baseProjectBasicInfo.getId())) {
                baseProjectBasicInfoService.insert(baseProjectBasicInfo);
            } else {
                BaseProjectBasicInfo old = get(baseProjectBasicInfo.getId());
                baseProjectBasicInfo.setCreateBy(old.getCreateBy());
                baseProjectBasicInfo.setCreateDate(old.getCreateDate());
                baseProjectBasicInfo.setUpdateBy(UserUtils.getUser());
                baseProjectBasicInfo.setUpdateDate(new Date());
                baseProjectBasicInfo.setDelFlag(old.getDelFlag());
                baseProjectBasicInfo.setUseStatus(old.getUseStatus());
                baseProjectBasicInfo.setAuditStatus(old.getAuditStatus());
                baseProjectBasicInfoService.updateAllColumnById(baseProjectBasicInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            baseProjectBasicInfoService.deleteById(id);
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
            List<String> idList = new ArrayList<String>(java.util.Arrays.asList(ids));
            // 如果已启用，不能被删除
            Iterator<String> iter = idList.iterator();
            while (iter.hasNext()) {
                String item = iter.next();
                BaseProjectBasicInfo baseProjectBasicInfo = baseProjectBasicInfoService.selectById(item);
                if (baseProjectBasicInfo.getUseStatus().equals("1")) {//启用标记（0：禁用；1：启用）
                    iter.remove();
                }
            }
            baseProjectBasicInfoService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "changeStatus", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson changeStatus(@RequestParam(value = "ids", required = true) String[] ids,
                                 @RequestParam(value = "status", required = false) Integer status) {
        //TODO: 若有引用时，不允许禁用
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("操作成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            List<BaseProjectBasicInfo> baseProjectBasicInfoList = Lists.newArrayList();
            for (String id : idList) {
                BaseProjectBasicInfo baseProjectBasicInfo = new BaseProjectBasicInfo();
                baseProjectBasicInfo.setId(id);
                if (status != null) {
                    baseProjectBasicInfo.setUseStatus(status.toString());
                }
                baseProjectBasicInfoList.add(baseProjectBasicInfo);
            }
            baseProjectBasicInfoService.insertOrUpdateBatch(baseProjectBasicInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("操作失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        BaseProjectBasicInfo baseProjectBasicInfo = get(id);
        model.addAttribute("data", baseProjectBasicInfo);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        String orgId = request.getParameter("orgId");
        try {
            EntityWrapper<BaseProjectBasicInfo> entityWrapper = new EntityWrapper<BaseProjectBasicInfo>(entityClass);
            entityWrapper.eq("orgId", orgId);
            valid = baseProjectBasicInfoService.doValid(duplicateValid,entityWrapper);
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
