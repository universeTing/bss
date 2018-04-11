package cn.jjxx.modules.base.controller;

import cn.jjxx.core.common.data.DuplicateValid;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.model.ValidJson;
import cn.jjxx.core.query.annotation.PageableDefaults;
import cn.jjxx.core.query.data.*;
import cn.jjxx.core.query.utils.QueryableConvertUtils;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.entity.Dict;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.service.IOrganizationService;
import cn.jjxx.modules.sys.utils.DictUtils;
import cn.jjxx.modules.sys.utils.UserUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.jjxx.modules.base.entity.BaseBidSegmentBasicInfo;
import cn.jjxx.modules.base.service.IBaseBidSegmentBasicInfoService;

/**   
 * @Title: base_bid_segment_basic_info
 * @Description: base_bid_segment_basic_info
 * @author jjxx
 * @date 2018-03-27 13:43:43
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/base/basebidsegmentbasicinfo")
@RequiresPathPermission("base:basebidsegmentbasicinfo")
public class BaseBidSegmentBasicInfoController extends BaseBeanController<BaseBidSegmentBasicInfo> {

    @Autowired
    protected IBaseBidSegmentBasicInfoService baseBidSegmentBasicInfoService;

    public BaseBidSegmentBasicInfo get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return baseBidSegmentBasicInfoService.selectById(id);
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
        EntityWrapper<BaseBidSegmentBasicInfo> entityWrapper = new EntityWrapper<BaseBidSegmentBasicInfo>(entityClass);
        propertyPreFilterable.addQueryProperty("id");

        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<BaseBidSegmentBasicInfo> pagejson = new PageJson<BaseBidSegmentBasicInfo>(baseBidSegmentBasicInfoService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    /**
     * 每页行数设置为99999无分页
     * @param queryable
     * @param propertyPreFilterable
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "ajaxListNOPage", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "org.code=asc")
    private void ajaxListNOPage(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<BaseBidSegmentBasicInfo> entityWrapper = new EntityWrapper<BaseBidSegmentBasicInfo>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        Condition condition = queryable.getCondition();
        Pageable pageable = new PageRequest(1, 99999, queryable.getPageable().getSort());
        queryable.setPageable(pageable);
        //queryable.setCondition(condition);
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        entityWrapper.setTableAlias("t.");
        PageJson<BaseBidSegmentBasicInfo> pagejson = new PageJson<BaseBidSegmentBasicInfo>(baseBidSegmentBasicInfoService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
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
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") BaseBidSegmentBasicInfo baseBidSegmentBasicInfo, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(baseBidSegmentBasicInfo, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        BaseBidSegmentBasicInfo baseBidSegmentBasicInfo = get(id);
        model.addAttribute("data", baseBidSegmentBasicInfo);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") BaseBidSegmentBasicInfo baseBidSegmentBasicInfo, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(baseBidSegmentBasicInfo, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(BaseBidSegmentBasicInfo baseBidSegmentBasicInfo, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(baseBidSegmentBasicInfo, result)) {
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
            if (StringUtils.isEmpty(baseBidSegmentBasicInfo.getId())) {
                baseBidSegmentBasicInfoService.insert(baseBidSegmentBasicInfo);
            } else {
                BaseBidSegmentBasicInfo old = get(baseBidSegmentBasicInfo.getId());
                baseBidSegmentBasicInfo.setCreateBy(old.getCreateBy());
                baseBidSegmentBasicInfo.setCreateDate(old.getCreateDate());
                baseBidSegmentBasicInfo.setUpdateBy(UserUtils.getUser());
                baseBidSegmentBasicInfo.setUpdateDate(new Date());
                baseBidSegmentBasicInfo.setDelFlag(old.getDelFlag());
                baseBidSegmentBasicInfo.setUseStatus(old.getUseStatus());
                baseBidSegmentBasicInfo.setAuditStatus(old.getAuditStatus());
                baseBidSegmentBasicInfoService.updateAllColumnById(baseBidSegmentBasicInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        ajaxJson.setData(baseBidSegmentBasicInfo);
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            baseBidSegmentBasicInfoService.deleteById(id);
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
                BaseBidSegmentBasicInfo baseBidSegmentBasicInfo = baseBidSegmentBasicInfoService.selectById(item);
                if (baseBidSegmentBasicInfo.getUseStatus().equals("1")) {//启用标记（0：禁用；1：启用）
                    iter.remove();
                }
            }
            baseBidSegmentBasicInfoService.deleteBatchIds(idList);
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
            List<BaseBidSegmentBasicInfo> baseBidSegmentBasicInfoList = Lists.newArrayList();
            for (String id : idList) {
                BaseBidSegmentBasicInfo baseBidSegmentBasicInfo = new BaseBidSegmentBasicInfo();
                baseBidSegmentBasicInfo.setId(id);
                if (status != null) {
                    baseBidSegmentBasicInfo.setUseStatus(status.toString());
                }
                baseBidSegmentBasicInfoList.add(baseBidSegmentBasicInfo);
            }
            baseBidSegmentBasicInfoService.insertOrUpdateBatch(baseBidSegmentBasicInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("操作失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        BaseBidSegmentBasicInfo baseBidSegmentBasicInfo = get(id);
        model.addAttribute("data", baseBidSegmentBasicInfo);
        return display("edit");
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
            boolean checkedResult = baseBidSegmentBasicInfoService.checkOnlyByOrgId(orgId);
            if (checkedResult) {
                ajaxJson.success("验证通过");
            } else {
                ajaxJson.fail("该组织下已存在数据");
            }
        }
        return ajaxJson;
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<BaseBidSegmentBasicInfo> entityWrapper = new EntityWrapper<BaseBidSegmentBasicInfo>(entityClass);
            valid = baseBidSegmentBasicInfoService.doValid(duplicateValid,entityWrapper);
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
