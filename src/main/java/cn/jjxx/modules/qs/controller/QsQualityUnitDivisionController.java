package cn.jjxx.modules.qs.controller;

import cn.jjxx.core.common.entity.tree.BootstrapTreeHelper;
import cn.jjxx.core.common.entity.tree.BootstrapTreeNode;
import cn.jjxx.core.common.entity.tree.TreeSortUtil;
import cn.jjxx.core.common.data.DuplicateValid;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.model.ValidJson;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.QueryPropertyPreFilter;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.framework.customutil.ListSortUtil;
import org.framework.customutil.zTreeIconUtil;
import org.framework.superutil.thirdparty.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.jjxx.core.common.controller.BaseBeanController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import cn.jjxx.modules.common.bean.UploadExcel;
import cn.jjxx.modules.qs.entity.QsQualityUnitDivision;
import cn.jjxx.modules.qs.entity.QsUnitTree;
import cn.jjxx.modules.qs.service.IQsQualityUnitDivisionService;
import cn.jjxx.modules.sys.Constants;
import cn.jjxx.modules.sys.entity.Dict;
import cn.jjxx.modules.sys.entity.DictGroup;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.service.IDictGroupService;
import cn.jjxx.modules.sys.service.IDictService;
import cn.jjxx.modules.sys.service.IOrganizationService;
import cn.jjxx.modules.sys.utils.UserUtils;

/**   
 * @Title: 质量单元划分
 * @Description: 质量单元划分
 * @author jjxx
 * @date 2018-03-14 12:06:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/qs/qsqualityunitdivision")
@RequiresPathPermission("qs:qsqualityunitdivision")
public class QsQualityUnitDivisionController extends BaseBeanController<QsQualityUnitDivision> {

    @Autowired
    protected IQsQualityUnitDivisionService qsQualityUnitDivisionService;
    @Autowired
	private IOrganizationService organizationService;
    @Autowired
	private IDictGroupService dictGroupService;
	@Autowired
	private IDictService dictService;

    public QsQualityUnitDivision get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return qsQualityUnitDivisionService.selectById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

	/**
	 * 根据页码和每页记录数，以及查询条件动态加载数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public void treeData(Queryable queryable,
			@RequestParam(value = "nodeid", required = false, defaultValue = "") String nodeid,
			@RequestParam(value = "async", required = false, defaultValue = "false") boolean async,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		EntityWrapper<QsQualityUnitDivision> entityWrapper = new EntityWrapper<QsQualityUnitDivision>(entityClass);
		entityWrapper.setTableAlias("t.");
		List<QsQualityUnitDivision> treeNodeList = null;
		if (!async) { // 非异步 查自己和子子孙孙
			treeNodeList = qsQualityUnitDivisionService.selectTreeList(queryable, entityWrapper);
			TreeSortUtil.create().sort(treeNodeList).async(treeNodeList);
		} else { // 异步模式只查自己
			// queryable.addCondition("parentId", nodeid);
			if (ObjectUtils.isNullOrEmpty(nodeid)) {
				// 判断的应该是多个OR条件
				entityWrapper.isNull("parentId");
			} else {
				entityWrapper.eq("parentId", nodeid);
			}
			treeNodeList = qsQualityUnitDivisionService.selectTreeList(queryable, entityWrapper);
			TreeSortUtil.create().sync(treeNodeList);
		}
		PropertyPreFilterable propertyPreFilterable = new QueryPropertyPreFilter();
		propertyPreFilterable.addQueryProperty("id", "name", "expanded", "hasChildren", "leaf", "loaded", "level",
				"parentId");
		SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
		PageJson<QsQualityUnitDivision> pagejson = new PageJson<QsQualityUnitDivision>(treeNodeList);
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response, content);
	}

	/**
	 * 根据页码和每页记录数，以及查询条件动态加载数据
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "ajaxTreeList", method ={ RequestMethod.GET, RequestMethod.POST })
	private void ajaxTreeList(Queryable queryable,
			@RequestParam(value = "nodeid", required = false, defaultValue = "") String nodeid,
			@RequestParam(value = "async", required = false, defaultValue = "false") boolean async,
			HttpServletRequest request, HttpServletResponse response, PropertyPreFilterable propertyPreFilterable)
			throws IOException {
		EntityWrapper<QsQualityUnitDivision> entityWrapper = new EntityWrapper<QsQualityUnitDivision>(entityClass);
		String open = String.valueOf(queryable.getValue("open"));
		entityWrapper.setTableAlias("t.");
		entityWrapper.orderBy("code", true);
		List<QsQualityUnitDivision> treeNodeList = null;
		if("true".equals(open)){ //用于显示底层
			String nodeId = String.valueOf(queryable.getValue("treePId"));
			String isQsUnit = String.valueOf(queryable.getValue("treeIsQsUnit"));
			if("false".equals(isQsUnit)){ //选择节点为组织节点时
				entityWrapper.eq("orgId", nodeId);
			}else{ //选择节点为质量单元节点时
				entityWrapper.eq("id", nodeId); 
				entityWrapper.or();
				entityWrapper.like("parentIds", nodeId);
			}
			treeNodeList = qsQualityUnitDivisionService.selectTreeList(queryable, entityWrapper);
			List<QsQualityUnitDivision> resultList = new ArrayList<QsQualityUnitDivision>();
			for(QsQualityUnitDivision entity:treeNodeList){
				entity.setExpanded(true);
				resultList.add(entity);
			}
			TreeSortUtil.create().sort(treeNodeList).async(resultList);
			propertyPreFilterable.addQueryProperty("id", "expanded", "hasChildren", "leaf", "loaded", "level", "parentId");
			SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
			PageJson<QsQualityUnitDivision> pagejson = new PageJson<QsQualityUnitDivision>(resultList);
			String content = JSON.toJSONString(pagejson, filter);
			StringUtils.printJson(response, content);
		}else{
			if (!async) { // 非异步 查自己和子子孙孙
				treeNodeList = qsQualityUnitDivisionService.selectTreeList(queryable, entityWrapper);
				TreeSortUtil.create().sort(treeNodeList).async(treeNodeList);
			} else { // 异步模式只查自己
				String queryPid = String.valueOf(queryable.getValue("parentId"));
				String orgId = String.valueOf(queryable.getValue("orgId")) ;
				if(StringUtils.isEmpty(orgId)||"null".equals(orgId)&&"null".equals(queryPid)){
					if(ObjectUtils.isNullOrEmpty(nodeid)){			
						entityWrapper.isNull("parentId");
						entityWrapper.eq("orgId", orgId);
					}else{
						entityWrapper.eq("parentId", nodeid);
					}		
				}else{
					if(ObjectUtils.isNullOrEmpty(nodeid)){
						if(ObjectUtils.isNullOrEmpty(queryPid)||"null".equals(queryPid)){
							entityWrapper.isNull("parentId");
							entityWrapper.eq("orgId", orgId);
						}else{
							//得到本级节点
							entityWrapper.eq("id", queryPid); //满足点击质量单元得到本级节点  by hql 2018-1-18
						}
					}else{
						queryable.removeCondition("parentId");
						entityWrapper.eq("parentId", nodeid);
					}			
				}
				treeNodeList = qsQualityUnitDivisionService.selectTreeList(queryable, entityWrapper);
				TreeSortUtil.create().sync(treeNodeList);
			}
			propertyPreFilterable.addQueryProperty("id", "expanded", "hasChildren", "leaf", "loaded", "level", "parentId");
			SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
			PageJson<QsQualityUnitDivision> pagejson = new PageJson<QsQualityUnitDivision>(treeNodeList);
			String content = JSON.toJSONString(pagejson, filter);
			StringUtils.printJson(response, content);
		}
	}
	
	/**
	 * 根据页码和每页记录数，以及查询条件动态加载数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "bootstrapTreeData")
	private void bootstrapTreeData(Queryable queryable,
			@RequestParam(value = "nodeid", required = false, defaultValue = "") String nodeid, HttpServletRequest request,
			HttpServletResponse response, PropertyPreFilterable propertyPreFilterable) throws IOException {
		EntityWrapper<QsQualityUnitDivision> entityWrapper = new EntityWrapper<QsQualityUnitDivision>(entityClass);
		entityWrapper.setTableAlias("t.");
		List<QsQualityUnitDivision> treeNodeList = qsQualityUnitDivisionService.selectTreeList(queryable, entityWrapper);
		List<BootstrapTreeNode> bootstrapTreeNodes = BootstrapTreeHelper.create().sort(treeNodeList);
		propertyPreFilterable.addQueryProperty("text", "href", "tags", "nodes");
		SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
		String content = JSON.toJSONString(bootstrapTreeNodes, filter);
		StringUtils.printJson(response, content);
	}

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
    	String id = request.getParameter("treePId");
    	String isQsUnit = request.getParameter("treeIsQsUnit");
        if (!model.containsAttribute("data")) {
        	QsQualityUnitDivision qsQualityUnitDivision = newModel();
        	if("true".equals(isQsUnit)&&!StringUtils.isEmpty(id)){
        		//如果是质量单元划分树节点则查询当前的质量单元对象，并给先传入的data对象赋值
        		QsQualityUnitDivision qud = get(id);
        		if(!ObjectUtils.isNullOrEmpty(qud)){
        			qsQualityUnitDivision.setOrgId(qud.getOrgId());
        			if("true".equals(isQsUnit)){
        				qsQualityUnitDivision.setParentId(qud.getId());
        			}else{
        				qsQualityUnitDivision.setOrgId(qud.getOrgId());
        			}			
        		}
        	}else if("false".equals(isQsUnit)&&!StringUtils.isEmpty(id)){
        		if("true".equals(isQsUnit)){
        			qsQualityUnitDivision.setParentId(id);
    			}else{
    				qsQualityUnitDivision.setOrgId(id);
    			}
        		qsQualityUnitDivision.setOrgId(id);
        	}
            model.addAttribute("data", qsQualityUnitDivision);
        }
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") QsQualityUnitDivision qsQualityUnitDivision, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsQualityUnitDivision, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        QsQualityUnitDivision qsQualityUnitDivision = get(id);
        model.addAttribute("data", qsQualityUnitDivision);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") QsQualityUnitDivision qsQualityUnitDivision, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(qsQualityUnitDivision, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(QsQualityUnitDivision qsQualityUnitDivision, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(qsQualityUnitDivision, result)) {
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
            if (StringUtils.isEmpty(qsQualityUnitDivision.getId())) {
                qsQualityUnitDivisionService.insert(qsQualityUnitDivision);
            } else {
                qsQualityUnitDivisionService.insertOrUpdate(qsQualityUnitDivision);
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
            qsQualityUnitDivisionService.deleteById(id);
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
            qsQualityUnitDivisionService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }
    
    /**
     * 
     * @Title: deleteData   
     * @Description: 批量删除
     * @param ids 选中的ids
     * @author Huangqiling  
     * @date 2018-3-19 上午10:24:27
     * @return AjaxJson
     * @throws
     */
    @RequestMapping(value = "batch/deleteData", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchDeleteData(@RequestParam(value = "ids", required = false) String[] ids,Queryable queryable) {
        AjaxJson ajaxJson = new AjaxJson();
        EntityWrapper<QsQualityUnitDivision> entityWrapper = new EntityWrapper<QsQualityUnitDivision>(entityClass);
		entityWrapper.setTableAlias("t.");
		entityWrapper.orderBy("code", true);
        ajaxJson.success("删除成功");
        try {
            List<String> idList = new ArrayList<String>();
            for (int i = 0; i < ids.length; i++) {
            	entityWrapper.eq("id", ids[i]); 
				entityWrapper.or();
				entityWrapper.like("parentIds", ids[i]);
				List<QsQualityUnitDivision> resultList = qsQualityUnitDivisionService.selectTreeList(queryable, entityWrapper);
				for (int j = 0; j < resultList.size(); j++) {
					QsQualityUnitDivision unit = resultList.get(j);
					if(unit.getAuditStatus()!=0){
						ajaxJson.fail("您选择的数据已审核或者其子级包含非保存状态数据，不能删除！");
						return ajaxJson;
					}else{
						idList.add(unit.getId());
					}
				}
			}
            qsQualityUnitDivisionService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }
    /**
     * 
     * @Title: batchOperate   
     * @Description: 提交、审核、反审核操作
     * @param ids 选中的ids
     * @author Huangqiling  
     * @date 2018-3-19 上午10:24:27
     * @return AjaxJson
     * @throws
     */
    @RequestMapping(value = "batch/operate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchOperate(@RequestParam(value = "ids", required = false) String[] ids,HttpServletRequest request,
            HttpServletResponse response) {
        AjaxJson ajaxJson = new AjaxJson();
        String statusString = request.getParameter("status");
        String title = "";
        int status = 0;
        try {
        	status = Integer.parseInt(statusString);
		} catch (Exception e) {
			// TODO: handle exception
			status = 0;
		}
        if("1".equals(statusString)){
        	title = "提交";
        }else if("4".equals(statusString)){
        	title = "审核";
        }else if("0".equals(statusString)){
        	title = "反审核";
        }
        ajaxJson.success(title+"成功");
        try {
        	List<QsQualityUnitDivision> resultList = new ArrayList<QsQualityUnitDivision>();
        	for (String id : ids) {
        		QsQualityUnitDivision qsQualityUnitDivision = get(id);
        		qsQualityUnitDivision.setAuditStatus(status);
        		if(status == 4){
        			User currentUser = UserUtils.getUser();
        			qsQualityUnitDivision.setAuditDate(new Date());
        			qsQualityUnitDivision.setAuditBy(currentUser);
        		}else{
        			qsQualityUnitDivision.setAuditDate(null);
        			qsQualityUnitDivision.setAuditBy(null);
        		}
	         	resultList.add(qsQualityUnitDivision);
          	}
        	qsQualityUnitDivisionService.insertOrUpdateBatch(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail(title+"失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        QsQualityUnitDivision qsQualityUnitDivision = get(id);
        model.addAttribute("data", qsQualityUnitDivision);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        String orgId = request.getParameter("orgId");
        try {
            EntityWrapper<QsQualityUnitDivision> entityWrapper = new EntityWrapper<QsQualityUnitDivision>(entityClass);
            entityWrapper.eq("orgId", orgId);
            valid = qsQualityUnitDivisionService.doValid(duplicateValid,entityWrapper);
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
   	 * 
   	 * @Description: 异步查询组织与质量单元组合成的树
   	 * @param nodeid 树节点ID
   	 * @param isQsUnit 是否是质量单元
   	 * @param type 组织类型
   	 * @param onlyOrg 仅仅查询组织 true表示是 false表示否
   	 * @param request
   	 * @param response
   	 * @param propertyPreFilterable
   	 * @param @throws IOException .  
   	 * @return void .
   	 * @author Huangqiling 
   	 * @date 2018年3月15日 上午13:51:30
   	 */
   	@RequestMapping(value = "qsUnitTree", method ={ RequestMethod.GET, RequestMethod.POST })
   	private void qsUnitTree(@RequestParam(value = "nodeid", required = false) String nodeid,
   			@RequestParam(value = "isQsUnit", required = false,defaultValue="false") boolean isQsUnit,
   			@RequestParam(value = "isParent", required = false) boolean isParent,
   			@RequestParam(value = "type", required = false) String type,
   			HttpServletRequest request, HttpServletResponse response, PropertyPreFilterable propertyPreFilterable)
   			throws IOException {
   		List<QsUnitTree>  qsUnitTreeList = getQsUnitTreeList(nodeid, isQsUnit, isParent, type);
   		String content = JSON.toJSONString(qsUnitTreeList);
   		StringUtils.printJson(response, content);
   	}
   	
   	/**
   	 * @Description: 查找质量单元 .
   	 * @param nodeid 节点Id.  
   	 * @param isQsUnit 是否是质量单元节点.
   	 * @param isParent 是否是父节点.
   	 * @param type 节点类型.   
   	 * @param propertyPreFilterable .
   	 * @return AjaxJson .
   	 * @author Huangqiling .
   	 * @date 2018-3-15 下午13:53:13.
   	 */
   	public List<QsUnitTree> getQsUnitTreeList(String nodeid,boolean isQsUnit,boolean isParent,String type){
   		List<QsUnitTree>  qsUnitTreeList = new ArrayList<QsUnitTree>();
   		if(isQsUnit){
   			//查询质量单元分类树
   			addQsUnitTree(qsUnitTreeList,nodeid,false);
   		}else{
   			//查询组织机构树
   			addOrgTree(qsUnitTreeList,nodeid,isQsUnit,type,isParent);
   		}
   		return qsUnitTreeList;
   	}
   	
   	/**
   	 * 
   	 * @Description: 异步查询组织与质量单元管理组合成的树
   	 * @param nodeid 树节点ID
   	 * @param isDrawingGroup 是否是质量单元树
   	 * @param type 组织类型
   	 * @param onlyOrg 仅仅查询组织 true表示是 false表示否
   	 * @param request
   	 * @param response
   	 * @param propertyPreFilterable
   	 * @param @throws IOException .  
   	 * @return void .
   	 * @author Huangqiling 
   	 * @date 2018-3-15 下午13:53:13
   	 */
   	@RequestMapping(value = "qsUnitTreeByNodeId", method ={ RequestMethod.GET, RequestMethod.POST })
   	private void qsUnitTreeByNodeId(@RequestParam(value = "nodeid", required = false) String nodeid,
   			@RequestParam(value = "isQsUnit", required = false,defaultValue="false") boolean isQsUnit,
   			@RequestParam(value = "isParent", required = false) boolean isParent,
   			@RequestParam(value = "type", required = false) String type,
   			HttpServletRequest request, HttpServletResponse response, PropertyPreFilterable propertyPreFilterable)
   			throws IOException {
   		List<QsUnitTree>  qsUnitTreeList = new ArrayList<QsUnitTree>();
   		String orgId = request.getParameter("orgId");
   		if(StringUtils.isNotEmpty(nodeid)){
   			addQsUnitTree(qsUnitTreeList,nodeid,false);
   		}else{
   			addQsUnitTree(qsUnitTreeList,orgId,true);
   		}
   		String content = JSON.toJSONString(qsUnitTreeList);
   		StringUtils.printJson(response, content);
   	}
   	
   	
   	/**
   	 * 
   	 * @Description: 根据传入的节点查询质量单元树 
   	 * @param @param qsUnitTreeList
   	 * @param @param nodeid页面传过来的树ID
   	 * @return void .
   	 * @author Huangqiling
   	 * @date 2018-3-15 下午13:53:13
   	 */
   	private void addQsUnitTree(List<QsUnitTree> qsUnitTreeList, String nodeid,boolean isOrg) {
   		EntityWrapper<QsQualityUnitDivision> treeWrapper = new EntityWrapper<QsQualityUnitDivision>(QsQualityUnitDivision.class);
   		treeWrapper.setTableAlias("t.");
   		treeWrapper.orderBy("code", true);
   		if(isOrg){
   			treeWrapper.eq("orgId", nodeid);
   			treeWrapper.isNull("parentId");
   		}else{
   			treeWrapper.eq("parentId", nodeid);
   		}	
   		 List<QsQualityUnitDivision> qsQualityUnitDivisionList = qsQualityUnitDivisionService.selectTreeList(treeWrapper);
   			for (QsQualityUnitDivision qsQualityUnitDivision : qsQualityUnitDivisionList) {
   				QsUnitTree  groupTree = new QsUnitTree();
   				groupTree.setId(qsQualityUnitDivision.getId());
   				groupTree.setName(qsQualityUnitDivision.getName());   
   				groupTree.setPid(qsQualityUnitDivision.getParentId());
   				groupTree.setIsQsUnit(true);
   				groupTree.setIconSkin(zTreeIconUtil.WBS_PARENT_ICON);
   				groupTree.setIsParent(qsQualityUnitDivision.isHasChildren());			
   				qsUnitTreeList.add(groupTree);
   			}
   		
   	}
   	
   	
   	/**
   	 * 
   	 * @Description: 根据条件查询机构树，以及机构关联的下级质量单元树 
   	 * @param @param qsUnitTreeList
   	 * @param @param nodeid页面传的树ID
   	 * @param isQsUnit 是否是图纸分类树
   	 * @param @param type .组织类型 5代表是标段
   	 * @return void .
   	 * @author Huangqiling
   	 * @param isParent 是否是父节点
   	 * @param onlyOrg  是否只查组织
   	 * @date 2017年11月24日 上午10:07:32
   	 */
   	private void addOrgTree(List<QsUnitTree> qsUnitTreeList, String nodeid,
   			boolean isQsUnit, String type, boolean isParent) {
   		EntityWrapper<Organization> entityWrapper = new EntityWrapper<Organization>(Organization.class);
   		entityWrapper.setTableAlias("t.");
   		List<Organization> treeNodeList = null;		
   		if (ObjectUtils.isNullOrEmpty(nodeid)) {
   			if(UserUtils.getUser().getAdminType().equals(User.ADMIN_NORMAL)){//如果是普通用户执行下面
   				entityWrapper.eq("id", UserUtils.getUser().getOrgId());		
   			}else{
   				entityWrapper.isNull("parentId");
   			}
   		} else {
   			entityWrapper.eq("parentId", nodeid);
   			if(!StringUtils.isEmpty(type)&&type.contains(Constants.FIAG_DIC_VALUE)){
   				//如果当前节点是标段类型则增加下级的标段类型
   				addQsUnitTree(qsUnitTreeList,nodeid,true);
   			}
   		}
   		treeNodeList = organizationService.selectWbsTreeList(entityWrapper);
   		for (Organization organization : treeNodeList) {
   			QsUnitTree  tree = new QsUnitTree();
   			tree.setId(organization.getId());
   			tree.setName(organization.getName());
   			tree.setPid(organization.getParentId());
   			tree.setIsQsUnit(false);
   			tree.setType(organization.getOrgType());
   			if(!organization.isHasChildren()&&!StringUtils.isEmpty(organization.getOrgType())
   					 &&organization.getOrgType().contains(Constants.FIAG_DIC_VALUE)){
   				//如果为标段类型的组织判断是否有质量单元节点
   				EntityWrapper<QsQualityUnitDivision> treeWrapper = new EntityWrapper<QsQualityUnitDivision>(QsQualityUnitDivision.class);
   				treeWrapper.eq("orgId", organization.getId());
   				List<QsQualityUnitDivision> qsQualityUnitDivisionList = qsQualityUnitDivisionService.selectList(treeWrapper);
   				if(qsQualityUnitDivisionList!=null&&qsQualityUnitDivisionList.size()>0){
   					tree.setIsParent(true);
   				}
   			}else{
   				tree.setIsParent(organization.isHasChildren());
   			}
   			if(!StringUtils.isEmpty(organization.getOrgType())
   					 &&organization.getOrgType().contains(Constants.FIAG_DIC_VALUE)){
   				tree.setIconSkin(zTreeIconUtil.BD_ORG_ICON);
   			}else{
   				tree.setIconSkin(zTreeIconUtil.ORG_PARENT_ICON);
   			}
   			qsUnitTreeList.add(tree);
   		}
   	}
   	
   	/**
   	 * 
   	 * @Title: exportExcelModel   
   	 * @Description:Excel模板导出    
   	 * @param request 请求 response 响应
   	 * @author Huangqiling  
   	 * @date 2018-3-19 上午10:26:40
   	 * @return void
   	 * @throws null
   	 */
    @RequestMapping(value = "exportExcelModel", method = RequestMethod.GET)
    public void exportExcelModel(HttpServletRequest request, HttpServletResponse response) {
    	ExcelUtils.setIsExportTemplate(true);
    	ExcelUtils.setFirstTitle("质量单元划分模板");
    	ExcelUtils.setSecTitle("导出人："+UserUtils.getUser().getRealname());
    	ExcelUtils.downloadXlsx(response, null, QsQualityUnitDivision.class, null, "质量单元划分模板");
    }
    
    /**
   	 * 
   	 * @Title: exportExcel   
   	 * @Description:Excel导出    
   	 * @param request 请求 response 响应
   	 * @author Huangqiling  
   	 * @date 2018-3-19 上午11:30:40
   	 * @return void
   	 * @throws null
   	 */
    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    public void exportExcel(Queryable queryable,HttpServletRequest request, HttpServletResponse response) {
    	String treeNodeId = request.getParameter("treePId");
    	String isQsUnit = request.getParameter("treeIsQsUnit");
    	EntityWrapper<QsQualityUnitDivision> entityWrapper = new EntityWrapper<QsQualityUnitDivision>(entityClass);
		entityWrapper.setTableAlias("t.");
		entityWrapper.orderBy("code", true);
		List<QsQualityUnitDivision> treeNodeList = null;
		if("true".equals(isQsUnit)){ //树节点为质量单元划分节点时，查询该节点以及以下所有节点
			entityWrapper.eq("id", treeNodeId);
			entityWrapper.or();
			entityWrapper.eq("parentId", treeNodeId);
		}else{ //树节点为组织标段时，查询该组织下所有节点
			entityWrapper.eq("orgId", treeNodeId);	
		}
		treeNodeList = qsQualityUnitDivisionService.selectTreeList(queryable, entityWrapper);
		
		//得到单位字典值
		List<Dict> unitTypeList = selectByGroup("unitType");
		//得到工程类别字典值
		List<Dict> engineerTypeList = selectByGroup("projecttype");
		//得到节点类型字典值
		List<Dict> nodeTypeList = selectByGroup("nodetype");
		//得到是否重点工程字典值
		List<Dict> keyProjectList = selectByGroup("keyProject");
		
		List<QsQualityUnitDivision> exportList = new ArrayList<QsQualityUnitDivision>();
		for (int i = 0; i < treeNodeList.size(); i++) {
			QsQualityUnitDivision entity = treeNodeList.get(i);
			for (int j = 0; j < unitTypeList.size(); j++) {
				try {
					if(entity.getUnit().equals(unitTypeList.get(j).getValue())){
						entity.setUnitName(unitTypeList.get(j).getLabel());
					}
				} catch (Exception e) {
					// TODO: handle exception
					entity.setUnitName(null);
				}
				
			}
			for (int j = 0; j < engineerTypeList.size(); j++) {
				if(entity.getEngineerType().equals(engineerTypeList.get(j).getValue())){
					entity.setEngineerTypeName(engineerTypeList.get(j).getLabel());
				}
			}
			for (int j = 0; j < nodeTypeList.size(); j++) {
				if(entity.getNodePointType().equals(nodeTypeList.get(j).getValue())){
					entity.setNodeTypeName(nodeTypeList.get(j).getLabel());
				}
			}
			for (int j = 0; j < keyProjectList.size(); j++) {
				if((entity.getKeyProject()+"").equals(keyProjectList.get(j).getValue())){
					entity.setKeyProjectName(keyProjectList.get(j).getLabel());
				}
			}
			exportList.add(entity);
		}
    	ExcelUtils.setIsExportTemplate(false);
    	ExcelUtils.setFirstTitle("质量单元划分");
    	ExcelUtils.setSecTitle("导出人："+UserUtils.getUser().getRealname());
    	ExcelUtils.downloadXlsx(response, exportList, QsQualityUnitDivision.class, null, "质量单元划分");
    }
    
    
    /**
     * @Title: selectByGroup   
     * @Description:根据字典组编码获取字典组包含的字典
     * @param groupCode 字典组编码
     * @author Huangqiling  
     * @date 2018-3-19 下午4:30:44
     * @return List<Dict>
     * @throws null
     */
	public List<Dict> selectByGroup(String groupCode){
		EntityWrapper<DictGroup> dgwrapper = new EntityWrapper<DictGroup>();
		EntityWrapper<Dict> wrapper = new EntityWrapper<Dict>();
		dgwrapper.eq("code", groupCode);
		DictGroup dg = dictGroupService.selectOne(dgwrapper);
		if(dg != null){
			wrapper.eq("gid", dg.getId());
		}
		return dictService.selectList(wrapper);
	}
	
	/**
	 * 
	 * @Title: excelUpload   
	 * @Description: Excel导入 
	 * @param model 模型实体类
     * @param excel excel参数实体类 
     * @param request http请求
     * @param response http响应
	 * @author Huangqiling  
	 * @date 2018-3-20 上午10:03:31
	 * @return AjaxJson
	 * @throws null
	 */
    @RequestMapping(value = "excelUpload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson excelUpload(Model model,UploadExcel excel,HttpServletRequest request, HttpServletResponse response) {
    	AjaxJson j = new AjaxJson();
    	response.setContentType("text/plain");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				try {
					MultipartFile file = multiRequest.getFile(ite.next());
					CommonsMultipartFile cFile = (CommonsMultipartFile) file;
			        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
			        InputStream inputStream = fileItem.getInputStream();
			        //得到Excel导入的质量单元列表
					List<QsQualityUnitDivision> orginalImportlist = new ExcelUtils<QsQualityUnitDivision>(new QsQualityUnitDivision()).readFromFile(null, inputStream);
					
					//去掉无用的Excel导入的质量单元  单元编码为空去掉该条质量单元划分,去掉空行的部分
					List<QsQualityUnitDivision> importlist = new ArrayList<QsQualityUnitDivision>();
					for(QsQualityUnitDivision unit:orginalImportlist){
						if(StringUtils.isNotEmpty(unit.getCode())){
							importlist.add(unit);
						}
					}
					ListSortUtil<QsQualityUnitDivision> sortList = new ListSortUtil<QsQualityUnitDivision>();
					sortList.sort(importlist, "code", "asc");
					//查询数据库，得到当前组织的所有质量单元记录
					List<QsQualityUnitDivision> existQsUnitList = qsQualityUnitDivisionService.selectByOrgId(excel.getNodeId());
					//对比两个质量单元的数据，将没有的数据copy到有的里面
					List<QsQualityUnitDivision> notInQsUnitInfos = new ArrayList<QsQualityUnitDivision>();
					//循环list，并创建主键Id,重复的直接取已有的数据的Id
					copyQsUnitInfoToNew(importlist, existQsUnitList, notInQsUnitInfos,excel.getNodeId());
					//TODO 如果数据库不存在这些质量单元,根据编号，获取上级质量单元信息，设置上级路径
					if(notInQsUnitInfos.size()>0){
						//转换最终符合格式的质量单元导入数据
						List<QsQualityUnitDivision> importQsUnitMainList = new ArrayList<QsQualityUnitDivision>();
						copyInsertQsUnit(notInQsUnitInfos, existQsUnitList,importQsUnitMainList);
						//TODO 保存到数据库
						qsQualityUnitDivisionService.saveQsUnitExcels(importQsUnitMainList);
					}
					j.setMsg("文件上传成功");
					break;
				} catch (Exception e) {
					e.printStackTrace();
					j.setMsg("文件上传失败!");
					break;
				}
			}
		}
    	return j;
    }
    
    /**
     * @Description: 对导入的excel检测，检测导入的质量单元划分是否在数据库存在,将检测结果存到对应的两个集合里面 
     * @param importlist 导入的excel的数据集合
     * @param existQsUnitList 已存在的质量单元划分的集合
     * @param notInQsUnitInfos 用于存放不存在的质量单元划分的集合  
     * @author Huangqiling
     * @date 2018-3-20 上午14:10:05
     */
    private void copyQsUnitInfoToNew(List<QsQualityUnitDivision> importlist,List<QsQualityUnitDivision> existQsUnitList,List<QsQualityUnitDivision> notInQsUnitInfos,String orgId){
    	for(QsQualityUnitDivision unit:importlist){
    		String id = checkQsUnitExist(unit.getCode(), existQsUnitList);
    		if(!StringUtils.isNotEmpty(id)){
    			QsQualityUnitDivision entity = new QsQualityUnitDivision();
    			entity.setOrgId(orgId);
    			entity.setCode(unit.getCode());
    			entity.setName(unit.getName());
    			entity.setNumber(unit.getNumber());
    			entity.setRemarks(unit.getRemarks());
    			User currentUser = UserUtils.getUser();
    			entity.setCreateDate(new Date());
    			entity.setCreateBy(currentUser);
    			entity.setStartDate(unit.getStartDate());
    			entity.setEndDate(unit.getEndDate());
    			//得到单位字典值
    			List<Dict> unitTypeList = selectByGroup("unitType");
    			//得到工程类别字典值
    			List<Dict> engineerTypeList = selectByGroup("projecttype");
    			//得到节点类型字典值
    			List<Dict> nodeTypeList = selectByGroup("nodetype");
    			//得到是否重点工程字典值
    			List<Dict> keyProjectList = selectByGroup("keyProject");
    			
    			for (int j = 0; j < unitTypeList.size(); j++) {
					if(unit.getUnitName().equals(unitTypeList.get(j).getLabel())){
						entity.setUnit(unitTypeList.get(j).getValue());
					}
				}
				for (int j = 0; j < engineerTypeList.size(); j++) {
					if(unit.getEngineerTypeName().equals(engineerTypeList.get(j).getLabel())){
						entity.setEngineerType(engineerTypeList.get(j).getValue());
					}
				}
				for (int j = 0; j < nodeTypeList.size(); j++) {
					if(unit.getNodeTypeName().equals(nodeTypeList.get(j).getLabel())){
						entity.setNodePointType(nodeTypeList.get(j).getValue());
					}
				}
				for (int j = 0; j < keyProjectList.size(); j++) {
					try {
						if(unit.getKeyProjectName().equals(keyProjectList.get(j).getLabel())){
							entity.setKeyProject(Integer.parseInt(keyProjectList.get(j).getValue()));
						}
					} catch (Exception e) {
						// TODO: handle exception
						entity.setKeyProject(0);
					}
				}	
    			notInQsUnitInfos.add(entity);
    		}
    	}
    }
    
    /**
     * @Description: 检测质量单元节点是否存在于数据库 
     * @param code 质量单元的编码
     * @param existQsUnitList 数据库已存在的质量单元集合   
     * @return String 返回检测结果（质量单元的Id,没有则返回null） 
     * @author Huangqiling 
     * @date 2018-3-20 上午14:22:05
     */
    private String checkQsUnitExist(String code,List<QsQualityUnitDivision> existQsUnitList){
    	for(QsQualityUnitDivision unit:existQsUnitList){
    		if(unit.getCode().equals(code)){
    			return unit.getId();
    		}
    	}
    	return null;
    }
    
    /**
     * @Description: 从待导入或已存在的质量单元划分里面获取上级Id
     * @param code 本级编码
     * @param importList 临时导入的质量单元划分集合
     * @param existQsUnitList 数据库已存在的质量单元划分集合 
     * @return String 返回上级Id,null,空字符
     * @author Huangqiling
     * @date 2018-3-20 下午2:56:15
     */
    private String getParentId(String code,List<QsQualityUnitDivision> importList,List<QsQualityUnitDivision> existQsUnitList){
    	if(StringUtils.isEmpty(code)){
    		return "";
    	}
    	if(!code.contains(".")){
    		return null;
    	}
    	String parentCode = code.substring(0, code.lastIndexOf("."));
    	for(QsQualityUnitDivision unit:importList){
    		if(unit.getCode().equals(parentCode)){
    			return unit.getId();
    		}
    	}
    	for(QsQualityUnitDivision unit:existQsUnitList){
    		if(unit.getCode().equals(parentCode)){
    			return unit.getId();
    		}
    	}
    	return "";
    }
    
    /**
     * @Description: 将数据库没有的质量单元数据导入到数据库
     * @param notInQsUnitInfos 没有的质量单元集合
     * @param existQsUnitList 数据库存在的质量单元集合
     * @param importQsUnitMainList 接受需要导入的质量单元集合 
     * @author Huangqiling
     * @date 2018-3-21 下午2:30:55
     */
    private void copyInsertQsUnit(List<QsQualityUnitDivision> notInQsUnitInfos,List<QsQualityUnitDivision> existQsUnitList,List<QsQualityUnitDivision> importQsUnitMainList){
    	//1.实体集合去重
    	HashSet<QsQualityUnitDivision> set = new HashSet<QsQualityUnitDivision>(notInQsUnitInfos);
    	notInQsUnitInfos.clear();
    	notInQsUnitInfos.addAll(set);
    	//2.设置主键Id
    	List<QsQualityUnitDivision> tempQsUnitList = new ArrayList<QsQualityUnitDivision>();
    	for(QsQualityUnitDivision unit:notInQsUnitInfos){
    		String id = StringUtils.randomUUID();
    		unit.setId(id);
			tempQsUnitList.add(unit);
    	}
    	//3.集合排序,检测质量单元划分是否有上级,并设置上级Id
    	ListSortUtil<QsQualityUnitDivision> sortList = new ListSortUtil<QsQualityUnitDivision>();
    	List<QsQualityUnitDivision> tempList = new ArrayList<QsQualityUnitDivision>();
		sortList.sort(tempQsUnitList, "code", "asc");
		List<QsQualityUnitDivision> checkQsUnitList = tempQsUnitList;
    	for(QsQualityUnitDivision unit:tempQsUnitList){
    		String pId = getParentId(unit.getCode(), checkQsUnitList, existQsUnitList);
    		if(StringUtils.isNull(pId)||StringUtils.isNotEmpty(pId)){
    			unit.setParentId(pId);
    		}
    		tempList.add(unit);
    	}
    	//4.集合排序,循环设置长编码
    	ListSortUtil<QsQualityUnitDivision> newSortList = new ListSortUtil<QsQualityUnitDivision>();
    	newSortList.sort(tempList, "code", "asc");
    	existQsUnitList.addAll(tempList);
    	for(QsQualityUnitDivision impQsUnit:tempList){
    		String parentIds = getIdsPath(null,impQsUnit, existQsUnitList);
    		impQsUnit.setParentIds(parentIds);
    		importQsUnitMainList.add(impQsUnit);
    	}
    }
    
    /**
     * @Description: 生成树的路径，生成parentIds
     * @param unit 需要生成parentIds的质量单元
     * @param list 已经存在的质量单元集合
     * @return String 路径parentIds
     * @author Huangqiling
     * @date 2018-3-21 下午2:50:55
     */
    private String getIdsPath(String path,QsQualityUnitDivision unit ,List<QsQualityUnitDivision> list){
    	String pId = null;
    	if(StringUtils.isNotEmpty(unit.getParentId())){
    		pId = unit.getParentId();
    	}
    	QsQualityUnitDivision parentQsUnit = null;
    	while(StringUtils.isNotEmpty(pId)){
    		parentQsUnit = getParentQsUnit(list, pId);
    		if(!ObjectUtils.isNullOrEmpty(parentQsUnit)){
    			if(StringUtils.isNull(path)){
    				path = parentQsUnit.getId()+parentQsUnit.getSeparator();
    			}else{
    				path = parentQsUnit.getId()+parentQsUnit.getSeparator()+path;
    			}
    			if(StringUtils.isNotEmpty(parentQsUnit.getParentId())){
    				pId = parentQsUnit.getParentId();
    			}else{
    				pId = null;
    			}
    		}else{
    			pId = null;
    		}
    	}
		return path;
    }
    
    /**
     * @Description: 根据质量单元的ID，获取质量单元实体
     * @param unitList 质量单元的实体集合.<br>
     * @param pId 上级Id
     * @return QsQualityUnitDivision
     * @author Huangqiling
     * @date 2018-3-21 下午2:50:55
     */
    private QsQualityUnitDivision getParentQsUnit(List<QsQualityUnitDivision> unitList,String pId){
    	for(QsQualityUnitDivision unit:unitList){
    		if(unit.getId().equals(pId)){
    			return unit;
    		}
    	}
    	return null;
    }
}
