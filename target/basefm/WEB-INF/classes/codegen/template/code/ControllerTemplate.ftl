package ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.controller;

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
import ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.entity.${entityName?cap_first};
import ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.service.I${entityName?cap_first}Service;

/**   
 * @Title: ${functionName}
 * @Description: ${functionDesc}
 * @author ${functionAuthor}
 * @date ${time}
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${r'${admin.url.prefix}'}/${moduleName}/${entityName?lower_case}")
@RequiresPathPermission("${moduleName}:${entityName?lower_case}")
public class ${entityName?cap_first}Controller extends BaseBeanController<${entityName?cap_first}> {

    @Autowired
    protected I${entityName?cap_first}Service ${entityName?uncap_first}Service;

	/**
	 * @Description: 根据获取${functionName}的实体数据 .<br>
	 * @param id 主键Id.<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    public ${entityName?cap_first} get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return ${entityName?uncap_first}Service.selectById(id);
        } else {
            return newModel();
        }
    }

	/**
	 * @Description: 跳转至${functionName}的列表界面 .<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

	/**
	 * @Description: ${functionName}的列表界面获取列表结果 .<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<${entityName?cap_first}> entityWrapper = new EntityWrapper<${entityName?cap_first}>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<${entityName?cap_first}> pagejson = new PageJson<${entityName?cap_first}>(${entityName?uncap_first}Service.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

	/**
	 * @Description: 跳转至${functionName}的添加界面 .<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        return display("edit");
    }
	
	/**
	 * @Description: 新增${functionName}的数据 .<br>
	 * @param ${entityName?uncap_first} ${functionName}实体类.<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") ${entityName?cap_first} ${entityName?uncap_first}, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(${entityName?uncap_first}, request, response, result);
    }

	/**
	 * @Description: 跳转至${functionName}的更新界面 .<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        ${entityName?cap_first} ${entityName?uncap_first} = get(id);
        model.addAttribute("data", ${entityName?uncap_first});
        return display("edit");
    }

	/**
	 * @Description: 修改${functionName}的数据 .<br>
	 * @param ${entityName?uncap_first} ${functionName}实体类.<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") ${entityName?cap_first} ${entityName?uncap_first}, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(${entityName?uncap_first}, request, response, result);
    }

	/**
	 * @Description: 执行保存${functionName}的数据操作 .<br>
	 * @param ${entityName?uncap_first} ${functionName}实体类.<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(${entityName?cap_first} ${entityName?uncap_first}, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(${entityName?uncap_first}, result)) {
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
            if (StringUtils.isEmpty(${entityName?uncap_first}.getId())) {
                ${entityName?uncap_first}Service.insert(${entityName?uncap_first});
            } else {
                ${entityName?uncap_first}Service.insertOrUpdate(${entityName?uncap_first});
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        return ajaxJson;
    }

	/**
	 * @Description: 单条删除${functionName} .<br>
	 * @param id 单据Id.<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            ${entityName?uncap_first}Service.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

	/**
	 * @Description: 执行保存${functionName}的数据操作 .<br>
	 * @param ids 单据Id集合.<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            ${entityName?uncap_first}Service.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

	/**
	 * @Description: 跳转至${functionName}的查看界面 .<br>
	 * @param ids 单据Id.<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        ${entityName?cap_first} ${entityName?uncap_first} = get(id);
        model.addAttribute("data", ${entityName?uncap_first});
        return display("edit");
    }

	/**
	 * @Description: 验证${functionName}功能数据的唯一性 .<br>
	 * @param duplicateValid 校验实体类.<br>
	 * @author ${functionAuthor} .<br>
	 * @date ${time}.<br>
	 */
    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<${entityName?cap_first}> entityWrapper = new EntityWrapper<${entityName?cap_first}>(entityClass);
            valid = ${entityName?uncap_first}Service.doValid(duplicateValid,entityWrapper);
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
