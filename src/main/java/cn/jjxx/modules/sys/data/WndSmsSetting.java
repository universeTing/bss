package cn.jjxx.modules.sys.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.jjxx.core.utils.PropertiesUtil;

public class WndSmsSetting  implements Serializable{

	private static final long serialVersionUID = -913691426202979533L;
	
	public static final String PROPERTIES_PATH = "sms.properties";
	private String accountUrl;
	private String account;
	private String pws;
	
	public void load() {
		load(PROPERTIES_PATH);
	}

	public void load(String propertiesPath) {
		PropertiesUtil propertiesUtil = new PropertiesUtil(propertiesPath);
		this.accountUrl = propertiesUtil.getString("sms.wnd.base.serverurl");
		this.account = propertiesUtil.getString("sms.wnd.account");
		this.pws = propertiesUtil.getString("sms.wnd.psw");
	}
	
	public void set() {
		set(PROPERTIES_PATH);
	}

	public void set(String propertiesPath) {
		PropertiesUtil propertiesUtil = new PropertiesUtil(propertiesPath);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("sms.wnd.base.serverurl", accountUrl);
		dataMap.put("sms.wnd.account", account);
		dataMap.put("sms.wnd.psw", pws);
		propertiesUtil.set(dataMap);
	}

	public String getAccountUrl() {
		return accountUrl;
	}

	public void setAccountUrl(String accountUrl) {
		this.accountUrl = accountUrl;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPws() {
		return pws;
	}

	public void setPws(String pws) {
		this.pws = pws;
	}

	
}
