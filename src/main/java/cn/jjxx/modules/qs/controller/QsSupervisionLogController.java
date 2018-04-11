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

import org.apache.commons.collections.iterators.AbstractListIteratorDecorator;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.jjxx.modules.qs.entity.QsSupervisionImplementationDetails;
import cn.jjxx.modules.qs.entity.QsSupervisionLog;
import cn.jjxx.modules.qs.service.IQsSupervisionLogService;
import cn.jjxx.modules.sys.utils.UserUtils;

/**   
 * @Title: 监理日志
 * @Description: 监理日志
 * @author jjxx
 * @date 2018-03-30 10:36:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/qs/qssupervisionlog")
@RequiresPathPermission("qs:qssupervisionlog")
public class QsSupervisionLogController extends BaseBeanController<QsSupervisionLog> {

    @Autowired
    protected IQsSupervisionLogService qsSupervisionLogService;

    public QsSupervisionLog get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return qsSupervisionLogService.selectById(id);
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
        EntityWrapper<QsSupervisionLog> entityWrapper = new EntityWrapper<QsSupervisionLog>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        entityWrapper.setTableAlias("t.");
        entityWrapper.eq("del_flag", "0");
        String orgId = String.valueOf(queryable.getValue("orgId"));
        if(StringUtils.isEmpty(orgId)||"null".equals(orgId)){
        	return ;
        }
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<QsSupervisionLog> pagejson = new PageJson<QsSupervisionLog>(qsSupervisionLogService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
    	String name = request.getParameter("treeName");		  //获取组织名(前端通过路径传入参数)
    	String id = request.getParameter("orgId");		  //获取组织Id(前端通过路径传入参数)
        QsSupervisionLog qsSupervisionLog = new QsSupervisionLog();
        qsSupervisionLog.setOrgName(name);	//设置实体的组织名属性值
        qsSupervisionLog.setOrgId(id); //传入组织ID,方便做标题的唯一性验证 by huangqiling 2018-3-13
        model.addAttribute("data",qsSupervisionLog);
        return display("edit");
    	
    	/*if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        return display("edit");*/
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") QsSupervisionLog qsSupervisionLog, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsSupervisionLog, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        QsSupervisionLog qsSupervisionLog = get(id);
        model.addAttribute("data", qsSupervisionLog);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") QsSupervisionLog qsSupervisionLog, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsSupervisionLog, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(QsSupervisionLog qsSupervisionLog, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(qsSupervisionLog, result)) {
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
        	qsSupervisionLog.setDate(sdf.parse(request.getParameter("date")));//将前端获取到的值进行以上格式的转换
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        
        try {
            if (StringUtils.isEmpty(qsSupervisionLog.getId())) {
                qsSupervisionLogService.insert(qsSupervisionLog);
            } else {
                qsSupervisionLogService.insertOrUpdate(qsSupervisionLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        ajaxJson.setData(qsSupervisionLog);
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            qsSupervisionLogService.deleteById(id);
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
            qsSupervisionLogService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        QsSupervisionLog qsSupervisionLog = get(id);
        model.addAttribute("data", qsSupervisionLog);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<QsSupervisionLog> entityWrapper = new EntityWrapper<QsSupervisionLog>(entityClass);
            valid = qsSupervisionLogService.doValid(duplicateValid,entityWrapper);
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
    
    /**
     * 提交、审核、反审核
     * @param request
     * @param response
     * @param id          当前行数据id
     * @param value       要改变的状态值
     * @return            AjaxJson
     * @throws ParseException 
     * @author wangyuanting
     * @date 2018-4-2
     */
    @RequestMapping(value = "changeAuditStatus", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson changeAuditStatus(HttpServletRequest request, HttpServletResponse response,
			                           @RequestParam(value = "ids", required = false) String[] ids, String value) throws ParseException {
		AjaxJson ajaxJson = new AjaxJson();
		List<QsSupervisionLog> updateList = qsSupervisionLogService.selectBatchIds(Arrays.asList(ids));
	    if(Integer.parseInt(value)==3){//提交操作
	        for(QsSupervisionLog enityLog : updateList){
		    	enityLog.setAuditStatus(value);
	        }
			ajaxJson.setMsg("数据提交成功！");
		}else if(Integer.parseInt(value)==4){//审核操作
			for(QsSupervisionLog enityLog : updateList){
				enityLog.setAuditStatus(value);
		    	enityLog.setAuditBy(UserUtils.getUser().getRealname());
		    	enityLog.setAuditDate(new Date());
	        }
			ajaxJson.setMsg("数据审核成功！");
		}else if(Integer.parseInt(value)==0){//反审核操作
			for(QsSupervisionLog enityLog : updateList){
				enityLog.setAuditStatus(value);
		    	enityLog.setAuditBy("");
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	enityLog.setAuditDate(sdf.parse("1970-01-01"));
	        }
			ajaxJson.setMsg("数据反审核成功！");
		}
		qsSupervisionLogService.updateBatchById(updateList);
		return ajaxJson;
	}

    
}
