package cn.jjxx.modules.sys.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.jjxx.core.common.controller.BaseTreeController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.entity.Menu;

@Controller
@RequestMapping("${admin.url.prefix}/sys/menu")
@RequiresPathPermission("sys:menu")
public class MenuController extends BaseTreeController<Menu, String> {

	@RequestMapping(value = "{id}/updateMenu", method = RequestMethod.GET)
	public String _showUpdate(@PathVariable("id") String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Menu menu = get(id);
		if(StringUtils.isNotEmpty(menu.getType())){
			List<String> list = Arrays.asList(menu.getType().split(","));
			model.addAttribute("typeIds", list);
		}
		model.addAttribute("data", menu);
		return display("edit");
	}
}
