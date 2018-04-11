package cn.jjxx.modules.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jjxx.core.common.controller.BaseTreeController;
import cn.jjxx.core.common.entity.ZtreeEntity;
import cn.jjxx.core.common.entity.tree.TreeSortUtil;
import cn.jjxx.core.common.service.ITreeCommonService;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.core.utils.MessageUtils;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.SpringContextHolder;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.Constants;
import cn.jjxx.modules.sys.entity.Menu;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.entity.User;
import cn.jjxx.modules.sys.entity.UserOrganization;
import cn.jjxx.modules.sys.service.IOrganizationService;
import cn.jjxx.modules.sys.service.IUserOrganizationService;
import cn.jjxx.modules.sys.utils.DictUtils;
import cn.jjxx.modules.sys.utils.UserUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

@Controller
@RequestMapping("${admin.url.prefix}/sys/organization")
@RequiresPathPermission("sys:organization")
public class OrganizationController extends BaseTreeController<Organization, String> {
	
	@Autowired
	private IUserOrganizationService userOrganizationService;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private ITreeCommonService<Organization, String> treeCommonService;
	
	
	public void preEdit(Organization entity, Model model, HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	public String showUpdate(Organization entity, Model model, HttpServletRequest request, HttpServletResponse response) {
		return "";
	}

	@RequestMapping(value = "createOrg", method = RequestMethod.GET)
	public String _showCreate(Model model, HttpServletRequest request, HttpServletResponse response) {
		preEdit(newModel(), model, request, response);
		String creteaView = showCreate(newModel(), model, request, response);
		if (!model.containsAttribute("data")) {
			model.addAttribute("data", newModel());
		}
		if (!StringUtils.isEmpty(creteaView)) {
			return creteaView;
		}
		return display("edit");
	}

	@RequestMapping(value = "createOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson create(Model model, @Valid @ModelAttribute("data") Organization entity, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		return doSave(entity, request, response, result);
	}

	
	@RequestMapping(value = "{id}/updateOrg", method = RequestMethod.GET)
	public String _showUpdate(@PathVariable("id") String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Organization entity = get(id);
		preEdit(entity, model, request, response);
		model.addAttribute("data", entity);
		if(entity.getOrgType()!=null&&entity.getOrgType().length()>0){
			model.addAttribute("orgTypeIds", Arrays.asList(entity.getOrgType().split(",")));
		}	
		String updateView = showUpdate(newModel(), model, request, response);
		if (!StringUtils.isEmpty(updateView)) {
			return updateView;
		}
		model.addAttribute("oldType", entity.getType());
		return display("edit");
	}

	@RequestMapping(value = "{id}/updateOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson update(Model model, @Valid @ModelAttribute("data") Organization entity, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		return doSave(entity, request, response, result);
	}
	
	@RequestMapping(value = "/saveOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson doSave(Organization entity, HttpServletRequest request, HttpServletResponse response,
			BindingResult result) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("info.save.success"));
		if (hasError(entity, result)) {
			// 错误提示
			String errorMsg = errorMsg(result);
			if (!StringUtils.isEmpty(errorMsg)) {
				ajaxJson.fail(errorMsg);
			} else {
				ajaxJson.fail(MessageUtils.getMessage("info.save.fail"));
			}
			return ajaxJson;
		}
		try {
			preEdit(entity, null, request, response);
			//判断管理单元不能添加到组织单元下面
			Organization parentOrg = get(entity.getParentId());
			if(parentOrg!=null){
				if(entity.getType()==Constants.MANAGER_CENTER&&parentOrg.getType()==Constants.ORG_CENTER){
					ajaxJson.fail(MessageUtils.getMessage("info.org.save.fail"));
					return ajaxJson;
				}				
			}
			if (ObjectUtils.isNullOrEmpty(entity.getId())) {
				//自定义生成code编码
				String code = createCode(entity);//暂时没有生效
				commonService.insert(entity);
			} else {
				Integer oldType = Integer.valueOf(request.getParameter("oldType"));//获取页面传来的原来类型值
				if(oldType!=entity.getType()){
					//判断如果下级包含管理单元则不能修改为组织单元			
					List<Organization> subOrgs = organizationService.findsubById(entity.getId());
					for (Organization org : subOrgs) {
						if(org.getType()==Constants.MANAGER_CENTER){
							ajaxJson.fail(MessageUtils.getMessage("info.org.save.level.fail"));
							return ajaxJson;
						}
					}
				}
				commonService.insertOrUpdate(entity);
			}
			afterSave(entity, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("info.save.fail.reason") + e.getMessage());
		}
		return ajaxJson;
	}
	
	/**
	 * 
	 * @Description: 根据保存实体，创建code编码信息 
	 * @param entity
	 * @return String .
	 * @author 周恺 
	 * @date 2017年11月30日 下午5:16:05
	 */
	private String createCode(Organization entity) {
		//判断是否根节点
		if(StringUtils.isEmpty(entity.getParentId())){
			//查出所有值
			//取出最大值
			//最大值+1
		}else{
			//找出父节点
			Organization porg = get(entity.getParentId());
			if(ObjectUtils.isNullOrEmpty(porg)){
			//查询父节点下一级所有节点
			//取出最大值
			//最大值+1
			}
		}		
		return null;
	}

	@RequestMapping(value = "batch/deleteOrg", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("info.del.success"));
		try {
			List<String> idList = java.util.Arrays.asList(ids);
			boolean haveFlag = false;	
			for(String id:idList){
				//判断组织是否有下级，有下级不能删除
				List<Organization>  orgs =  organizationService.selectList(new EntityWrapper<Organization>(Organization.class).eq("parentId", id));
				if(orgs!=null&&orgs.size()>0){
					haveFlag = true;
					ajaxJson.fail(MessageUtils.getMessage("info.org.del.level.fail"));
					return ajaxJson;
				}
				//判断组织下面是否有人员，有人员无法删除
				List<UserOrganization>  uorgs =  userOrganizationService.findUserByOrgId(id);
				if(uorgs!=null&&uorgs.size()>0){
					haveFlag = true;
					ajaxJson.fail(MessageUtils.getMessage("info.org.del.staff.fail"));
					return ajaxJson;
				}
			}
			if(!haveFlag){
				commonService.deleteBatchIds(idList);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("info.del.fail"));
		}
		return ajaxJson;
	}
	
	
	@RequestMapping(value = "{id}/deleteOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson delete(@PathVariable("id") String id) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success(MessageUtils.getMessage("info.del.success"));
		try {
			boolean haveFlag = false;
			//判断组织是否有下级，有下级不能删除
			List<Organization>  orgs =  organizationService.selectList(new EntityWrapper<Organization>(Organization.class).eq("parentId", id));
			if(orgs!=null&&orgs.size()>0){
				haveFlag = true;
				ajaxJson.fail(MessageUtils.getMessage("info.org.del.level.fail"));
				return ajaxJson;
			}
			//判断组织下面是否有人员，有人员无法删除
			List<UserOrganization>  uorgs =  userOrganizationService.findUserByOrgId(id);
			if(uorgs!=null&&uorgs.size()>0){
				haveFlag = true;
				ajaxJson.fail(MessageUtils.getMessage("info.org.del.staff.fail"));
				return ajaxJson;
			}
			if(!haveFlag){
				commonService.deleteById(id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(MessageUtils.getMessage("info.del.fail"));
		}
		return ajaxJson;
	}

	/**
	 * 根据页码和每页记录数，以及查询条件动态加载数据
	 * 只显示当前用户所属组织下的所有组织 管理员用户不受影响
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "ajaxOrgTreeList", method ={ RequestMethod.GET, RequestMethod.POST })
	private void ajaxTreeList(Queryable queryable,
			@RequestParam(value = "nodeid", required = false, defaultValue = "") String nodeid,
			@RequestParam(value = "async", required = false, defaultValue = "false") boolean async,
			HttpServletRequest request, HttpServletResponse response, PropertyPreFilterable propertyPreFilterable)
			throws IOException {
		EntityWrapper<Organization> entityWrapper = new EntityWrapper<Organization>(entityClass);
		entityWrapper.setTableAlias("t.");
		preAjaxList(queryable, entityWrapper, request, response);

		List<Organization> treeNodeList = null;
		if (!async) { // 非异步 查自己和子子孙孙
			treeNodeList = treeCommonService.selectTreeList(queryable, entityWrapper);
			TreeSortUtil.create().sort(treeNodeList).async(treeNodeList);
		} else { // 异步模式只查自己
			// queryable.addCondition("parentId", nodeid);
			if (ObjectUtils.isNullOrEmpty(nodeid)) {
				// 判断的应该是多个OR条件
				entityWrapper.isNull("parentId");
			} else {
				entityWrapper.eq("parentId", nodeid);
			}
			treeNodeList = treeCommonService.selectTreeList(queryable, entityWrapper);
			TreeSortUtil.create().sync(treeNodeList);
		}
		for (Organization organization : treeNodeList) {
			if(!StringUtils.isEmpty(organization.getOrgType())){//字典项转换
				String dictValues = DictUtils.getDictLabels(organization.getOrgType(),Constants.ORGTYPE_DIC,"");
				organization.setOrgTypeName(dictValues);
			}
		}
		propertyPreFilterable.addQueryProperty("id", "expanded", "hasChildren", "leaf", "loaded", "level", "parentId");
		SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
		PageJson<Organization> pagejson = new PageJson<Organization>(treeNodeList);
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response, content);
	}
	
	
	/**
	 * 
	 * @Description: 查询当前登录用户下的组织树 
	 * @param @param nodeid
	 * @param @param request
	 * @param @param response
	 * @param @param propertyPreFilterable
	 * @param @throws IOException .  
	 * @return void .
	 * @author 周恺 
	 * @date 2017年11月29日 下午6:03:24
	 */
	@RequestMapping(value = "orgTree", method ={ RequestMethod.GET, RequestMethod.POST })
	private void orgTree(@RequestParam(value = "nodeid", required = false) String nodeid,
			HttpServletRequest request, HttpServletResponse response, PropertyPreFilterable propertyPreFilterable)
			throws IOException {
		EntityWrapper<Organization> entityWrapper = new EntityWrapper<Organization>(entityClass);
		entityWrapper.setTableAlias("t.");
		entityWrapper.orderBy("t.code");
		if(UserUtils.getUser().getAdminType().equals(User.ADMIN_NORMAL)){
			entityWrapper.in("id", UserUtils.getOrgChilds(true, true));
		}
		List<Organization> treeNodeList = null;
			if (ObjectUtils.isNullOrEmpty(nodeid)) {
				if(UserUtils.getUser().getAdminType().equals(User.ADMIN_NORMAL)){//如果是普通用户执行下面
					entityWrapper.eq("id", UserUtils.getUser().getOrgId());		
				}else{
					entityWrapper.isNull("parentId");
				}
			} else {
//				if(UserUtils.getUser().getAdminType().equals(User.ADMIN_NORMAL)){//如果是查询子节点，只显示当前用户有权限的子节点
//					nodeid = getAuthOrgId(nodeid);
//				}
				entityWrapper.eq("parentId", nodeid);
			}
		
		treeNodeList = organizationService.selectWbsTreeList(entityWrapper);
		List<ZtreeEntity> ztreeList = getZtreeList(treeNodeList);
		String content = JSON.toJSONString(ztreeList);
		StringUtils.printJson(response, content);
	}
	
	/**
	 * @Description: 获取组织的tree .<br>
	 * @param request http响应.<br>
	 * @param response http请求.<br>
	 * @param queryable 查询条件封装.<br>
	 * @param wrapper 查询实体.<br>
	 * @param id 节点Id.<br>
	 * @param menuId 菜单Id.<br>   
	 * @author zcg .<br>
	 * @date 2018-3-30 下午6:46:08.<br>
	 */
	@RequestMapping(value = "findOrgTree", method ={ RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void findOrgTree(HttpServletRequest request, HttpServletResponse response,
			Queryable queryable,EntityWrapper<Organization> wrapper,String id,String menuId){
		List<Organization> orgList = getOrgTree(queryable, wrapper, id, menuId);
		List<ZtreeEntity> ztreeList = getZtreeList(orgList);
		String content = JSON.toJSONString(ztreeList);
		StringUtils.printJson(response, content);
	}
	
	/**
	 * @Description: 获取org组织树 .<br>
	 * @param queryable 查询条件封装.<br>
	 * @param wrapper 实体条件设置.<br>
	 * @param nodeId 节点Id.<br>
	 * @param menuId 菜单Id.<br>
	 * @return 返回组织集合.<br>   
	 * @author Administrator .<br>
	 * @date 2018-3-30 下午6:06:23.<br>
	 */
	public List<Organization> getOrgTree(Queryable queryable,EntityWrapper<Organization> wrapper,String nodeId,String menuId){
		User user = UserUtils.getUser();
		List<String> orgIds = getOrgIds(menuId);
		List<String> permisOrgIds = UserUtils.getOrgChilds(true, true);
		wrapper.setTableAlias("t.");
		wrapper.orderBy("code");
		orgIds.retainAll(permisOrgIds);
		if(user.getAdminType().equals(User.ADMIN_NORMAL)){
			//利用hashSet去掉重复的数据
			HashSet<String> set = new HashSet<String>();
			set.addAll(orgIds);
			orgIds.addAll(set);
			wrapper.in("id", orgIds);
		}
		if (ObjectUtils.isNullOrEmpty(nodeId)) {
			//如果是普通用户执行下面
			if(user.getAdminType().equals(User.ADMIN_NORMAL)){
				wrapper.eq("id", UserUtils.getUser().getOrgId());		
			}else{
				wrapper.isNull("parent_id");
			}
		} else {
			wrapper.eq("parent_id", nodeId);
		}
		return organizationService.selectTreeList(wrapper);
	}
	
	/**
	 * @Description: 根据菜单，获取组织包含的菜单类型 .<br>
	 * @param menuId 菜单Id.<br>
	 * @author zcg .<br>
	 * @date 2018-3-30 下午5:55:22.<br>
	 */
	private List<String> getOrgIds(String menuId){
		EntityWrapper<Organization> wrapper = new EntityWrapper<Organization>(entityClass);
		List<String> orgIds = new ArrayList<String>();
		List<Organization> orgList = new ArrayList<Organization>();
		if(StringUtils.isNotEmpty(menuId)){
			Menu menu = SpringContextHolder.getBean(MenuController.class).get(menuId);
			String type = menu.getType();
			type = "1,2,3,4,5,6";
			if(StringUtils.isNotEmpty(type)){
				List<String> str = Arrays.asList(type.split(","));
				wrapper.in("org_type",str);
				orgList = organizationService.selectList(wrapper);
				//通过类型得到上级Id
			}
		}
		//获取所包含的组织
		for(Organization org:orgList){
			String parentIds = org.getParentIds();
			if(StringUtils.isNotEmpty(parentIds)){
				List<String> ids = Arrays.asList(parentIds.split("/"));
				for(String str:ids){
					orgIds.add(str);
				}
			}
			orgIds.add(org.getId());
		}
		return orgIds;
	}
	
	/**
	 * @Description: 将集合组成list .<br>
	 * @param orgList 组织Id集合.<br>
	 * @return ztree的list集合.<br>   
	 * @author Administrator .<br>
	 * @date 2018-3-30 下午6:49:25.<br>
	 */
	private List<ZtreeEntity> getZtreeList(List<Organization> orgList){
		List<ZtreeEntity>  ztreeList = new ArrayList<ZtreeEntity>();
		for (Organization organization : orgList) {
			 ZtreeEntity  zt = new ZtreeEntity();
			 zt.setId(organization.getId());
			 zt.setTitle(organization.getName());
			 zt.setName(organization.getName());
			 zt.setPid(organization.getParentId());
			 zt.setType(organization.getOrgType());
			 zt.setIsParent(organization.isHasChildren());
			 ztreeList.add(zt);
		}
		return ztreeList;
	}
	
}
