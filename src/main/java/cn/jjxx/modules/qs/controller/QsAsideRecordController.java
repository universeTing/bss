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

import org.apache.taglibs.standard.tag.common.fmt.FormatDateSupport;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import cn.jjxx.modules.qs.entity.QsAsideRecord;
import cn.jjxx.modules.qs.service.IQsAsideRecordService;

/**   
 * @Title: qs_aside_record
 * @Description: qs_aside_record
 * @author jjxx
 * @date 2018-03-28 14:11:51
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/qs/qsasiderecord")
@RequiresPathPermission("qs:qsasiderecord")
public class QsAsideRecordController extends BaseBeanController<QsAsideRecord> {

    @Autowired
    protected IQsAsideRecordService qsAsideRecordService;

    public QsAsideRecord get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return qsAsideRecordService.selectById(id);
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
        EntityWrapper<QsAsideRecord> entityWrapper = new EntityWrapper<QsAsideRecord>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        entityWrapper.eq("1", "1");
        entityWrapper.setTableAlias("t.");
        String orgId = String.valueOf(queryable.getValue("orgId"));
        if(StringUtils.isEmpty(orgId)||"null".equals(orgId)){
        	return ;
        }
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<QsAsideRecord> pagejson = new PageJson<QsAsideRecord>(qsAsideRecordService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") QsAsideRecord qsAsideRecord, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsAsideRecord, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        QsAsideRecord qsAsideRecord = get(id);
        model.addAttribute("data", qsAsideRecord);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") QsAsideRecord qsAsideRecord, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsAsideRecord, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(QsAsideRecord qsAsideRecord, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(qsAsideRecord, result)) {
            // 错误提示
            String errorMsg = errorMsg(result);
            if (!StringUtils.isEmpty(errorMsg)) {
                ajaxJson.fail(errorMsg);
            } else {
                ajaxJson.fail("保存失败");
            }
            return ajaxJson;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");//因为保存的格式为yyyy-mm-dd，转换时间格式为 yyyy年 mm月dd日,与前端显示的格式一样,否则保存不了
        try {
			qsAsideRecord.setAsideDate(sdf.parse(request.getParameter("asideDate")));//将前端获取到的值进行以上格式的转换
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
    
        try {
            if (StringUtils.isEmpty(qsAsideRecord.getId())) {
                qsAsideRecordService.insert(qsAsideRecord);
            } else {
                qsAsideRecordService.insertOrUpdate(qsAsideRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        ajaxJson.setData(qsAsideRecord);
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            qsAsideRecordService.deleteById(id);
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
            qsAsideRecordService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        QsAsideRecord qsAsideRecord = get(id);
        model.addAttribute("data", qsAsideRecord);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<QsAsideRecord> entityWrapper = new EntityWrapper<QsAsideRecord>(entityClass);
            valid = qsAsideRecordService.doValid(duplicateValid,entityWrapper);
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
