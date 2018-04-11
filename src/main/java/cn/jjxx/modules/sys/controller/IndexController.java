package cn.jjxx.modules.sys.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.utils.CookieUtils;
import cn.jjxx.core.utils.JeewebPropertiesUtil;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.oa.entity.OaNotification;
import cn.jjxx.modules.oa.service.IOaNotificationService;
import cn.jjxx.modules.sys.entity.Menu;
import cn.jjxx.modules.sys.utils.UserUtils;

@Controller
@RequestMapping("${admin.url.prefix}")
public class IndexController {
	@Autowired
	private IOaNotificationService oaNotificationService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		// 加载菜单
		model.addAttribute("menus", UserUtils.getMenuList());
		// 加载通知公告
		int oaNotificationCount = oaNotificationService
				.selectCount(new EntityWrapper<OaNotification>(OaNotification.class).eq("status", "1"));
		List<OaNotification> oaNotifications = oaNotificationService
				.selectList(new EntityWrapper<OaNotification>(OaNotification.class).eq("status", "1"));
		model.addAttribute("oaNotificationCount", oaNotificationCount);
		model.addAttribute("oaNotifications", oaNotifications);
		// 加载模版
		String theme = getTheme(request);
		return "modules/sys/index/index-" + theme;
	}
	
	/**
	 * 获取菜单json数据
	 * @author bgg
	 * @date 2018-2-23
	 */
	@RequestMapping(value="menuJson" ,method = RequestMethod.POST)
	public void menuJson(Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Menu> menus = UserUtils.getMenuList();
		StringUtils.printJson(response, menus);
	}

	/**
	 * 加载风格
	 * 
	 * @title: getTheme
	 * @description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @return
	 * @return: String
	 */
	private String getTheme(HttpServletRequest request) {
		// 默认风格
		String theme = JeewebPropertiesUtil.getConfig("admin.default.theme");
		if (StringUtils.isEmpty(theme)) {
			theme = "uadmin";
		}
		// cookies配置中的模版
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			if (cookie.getName().equalsIgnoreCase("theme")) {
				theme = cookie.getValue();
			}
		}
		return theme;
	}

	/**
	 * Coookie设置
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isNotBlank(theme)) {
			CookieUtils.setCookie(response, "theme", theme);
		} else {
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:" + request.getParameter("url");
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/main")
	public String main() {
		return "modules/sys/index/main";
	}

	@RequestMapping("/lock")
	public String lock() {
		return "lock";
	}

}
