package cn.jjxx.modules.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jjxx.core.common.controller.BaseCRUDController;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.modules.sys.entity.Dict;
import cn.jjxx.modules.sys.entity.DictGroup;
import cn.jjxx.modules.sys.service.IDictGroupService;
import cn.jjxx.modules.sys.service.IDictService;

@Controller
@RequestMapping("${admin.url.prefix}/sys/dict")
@RequiresPathPermission("sys:user")
public class DictController extends BaseCRUDController<Dict, String> {
	@Autowired
	private IDictGroupService dictGroupService;
	@Autowired
	private IDictService dictService;

	@Override
	public void preList(Model model, HttpServletRequest request, HttpServletResponse response) {
		String gid = request.getParameter("gid");
		DictGroup group = dictGroupService.selectById(gid);
		model.addAttribute("group", group);
	}

	@Override
	public void preAjaxList(Queryable queryable, EntityWrapper<Dict> entityWrapper, HttpServletRequest request,
			HttpServletResponse response) {
		String gid = request.getParameter("gid");
		queryable.addCondition("gid", gid);
	}

	@Override
	public void preEdit(Dict entity, Model model, HttpServletRequest request, HttpServletResponse response) {
		String gid = request.getParameter("gid");
		model.addAttribute("gid", gid);
	}
	
	@RequestMapping(value = "selectByGroup", method = RequestMethod.POST)
	@ResponseBody
	public List<Dict> selectByGroup(HttpServletRequest request,String gid){
		EntityWrapper<Dict> wrapper = new EntityWrapper<Dict>();
		EntityWrapper<DictGroup> gwrapper = new EntityWrapper<DictGroup>();
		gwrapper.eq("code", gid);
		DictGroup group = dictGroupService.selectOne(gwrapper);
		wrapper.eq("gid", group.getId());
		return dictService.selectList(wrapper);
	}
}
