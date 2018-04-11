package cn.jjxx.modules.qs.entity;

/**
 * 
 * @Title: QsUnitTree.java 
 * @Package cn.jjxx.modules.qs.entity; 
 * @Description: 质量单元划分实体类
 * @author Huangqiling
 * @email 543507214@qq.com 
 * @date 2018年3月15日 上午11:55:38
 * @version V1.0
 */
public class QsUnitTree {
	private String  id;
	private String  pid;//树上级节点
	private String  name;//树名称
	private String  type;//组织类型
	private String  level;//层级
	private boolean isQsUnit; //是否为质量单元划分
	private boolean isParent;//是否为父节点
	private String  iconSkin;//显示图标
	
	private String  icon;//自定义图标（图标路径）
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public boolean getIsQsUnit() {
		return isQsUnit;
	}
	public void setIsQsUnit(boolean isQsUnit) {
		this.isQsUnit = isQsUnit;
	}

	

	
}
