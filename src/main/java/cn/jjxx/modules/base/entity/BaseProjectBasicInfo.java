package cn.jjxx.modules.base.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jjxx.modules.sys.entity.User;
import java.util.Date;

/**   
 * @Title: base_project_basic_info
 * @Description: base_project_basic_info
 * @author jjxx
 * @date 2018-03-23 13:50:40
 * @version V1.0   
 *
 */
@TableName("base_project_basic_info")
@SuppressWarnings("serial")
public class BaseProjectBasicInfo extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**组织id*/
    @TableField(value = "org_id")
	private String orgId;
    /**项目编号*/
    @TableField(value = "project_number")
	private String projectNumber;
    /**项目全称*/
    @TableField(value = "project_full_name")
	private String projectFullName;
    /**项目简称*/
    @TableField(value = "project_short_name")
	private String projectShortName;
    /**业主单位*/
    @TableField(value = "owner_org")
	private String ownerOrg;
    /**起点名称*/
    @TableField(value = "start_point_name")
	private String startPointName;
    /**终点名称*/
    @TableField(value = "end_point_name")
	private String endPointName;
    /**起始桩号*/
    @TableField(value = "start_pile_no")
	private String startPileNo;
    /**终点桩号*/
    @TableField(value = "end_pile_no")
	private String endPileNo;
    /**计划开始日期*/
    @TableField(value = "start_plan_date")
	private Date startPlanDate;
    /**计划完工日期*/
    @TableField(value = "end_plan_date")
	private Date endPlanDate;
    /**实际开工日期*/
    @TableField(value = "start_actual_date")
	private Date startActualDate;
    /**实际结束日期*/
    @TableField(value = "end_actual_date")
	private Date endActualDate;
    /**法人代表*/
    @TableField(value = "legal_representative")
	private String legalRepresentative;
    /**总造价（万）*/
    @TableField(value = "total_cost")
	private String totalCost;
    /**建设性质*/
    @TableField(value = "construction_type")
	private String constructionType;
    /**投资方式*/
    @TableField(value = "investment_mode")
	private String investmentMode;
    /**主要设计单位*/
    @TableField(value = "main_design_unit")
	private String mainDesignUnit;
    /**单位造价*/
    @TableField(value = "unit_cost")
	private String unitCost;
    /**工程类别*/
    @TableField(value = "project_category")
	private String projectCategory;
    /**其他信息*/
    @TableField(value = "other_infor")
	private String otherInfor;
    /**项目简介*/
    @TableField(value = "project_introduction")
	private String projectIntroduction;
    /**技术等级*/
    @TableField(value = "technical_level")
	private String technicalLevel;
    /**路线长度*/
    @TableField(value = "route_length")
	private String routeLength;
    /**路基挖方*/
    @TableField(value = "roadbed_cut")
	private String roadbedCut;
    /**路基防护工程*/
    @TableField(value = "roadbed_protective_engineering")
	private String roadbedProtectiveEngineering;
    /**路基排水工程*/
    @TableField(value = "roadbed_drainage_works")
	private String roadbedDrainageWorks;
    /**路基宽度*/
    @TableField(value = "roadbed_width")
	private String roadbedWidth;
    /**路基填方*/
    @TableField(value = "roadbed_fill")
	private String roadbedFill;
    /**起讫桩号*/
    @TableField(value = "od_pile_no")
	private String odPileNo;
    /**特大桥*/
    @TableField(value = "grand_bridge")
	private String grandBridge;
    /**大桥*/
    @TableField(value = "great_bridge")
	private String greatBridge;
    /**中桥*/
    @TableField(value = "middle_bridge")
	private String middleBridge;
    /**小桥*/
    @TableField(value = "small_bridge")
	private String smallBridge;
    /**桥梁宽度*/
    @TableField(value = "bridge_width")
	private String bridgeWidth;
    /**设计载荷*/
    @TableField(value = "design_load")
	private String designLoad;
    /**设计车速*/
    @TableField(value = "design_speed")
	private String designSpeed;
    /**涵洞通道*/
    @TableField(value = "culvert_channel")
	private String culvertChannel;
    /**分离式立体交叉*/
    @TableField(value = "separate_grade_crossing")
	private String separateGradeCrossing;
    /**互通式立体交叉*/
    @TableField(value = "interchange")
	private String interchange;
    /**路面厚度*/
    @TableField(value = "pavement_thickness")
	private String pavementThickness;
    /**路面结构类型*/
    @TableField(value = "pavement_structure_type")
	private String pavementStructureType;
    /**水泥砼路面面积*/
    @TableField(value = "roadbed_cement_concrete_area")
	private String roadbedCementConcreteArea;
    /**沥青砼路面面积*/
    @TableField(value = "roadbed_asphalt_concrete_area")
	private String roadbedAsphaltConcreteArea;
    /**长隧道*/
    @TableField(value = "long_tunnel")
	private String longTunnel;
    /**中隧道*/
    @TableField(value = "middle_tunnel")
	private String middleTunnel;
    /**短隧道*/
    @TableField(value = "short_tunnel")
	private String shortTunnel;
    /**特长隧道*/
    @TableField(value = "super_long_tunnel")
	private String superLongTunnel;
    /**隧道宽度*/
    @TableField(value = "tunnel_width")
	private String tunnelWidth;
    /**其他*/
    @TableField(value = "other")
	private String other;
    /**创建者*/
    @TableField(value = "create_by",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    /**创建时间*/
    @TableField(value = "create_date",fill = FieldFill.INSERT)
	private Date createDate;
    /**更新者*/
    @TableField(value = "update_by",el="updateBy.id",fill = FieldFill.UPDATE)
	private User updateBy;
    /**更新时间*/
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
    @TableField(value = "del_flag")
	private String delFlag;
	/**启用标记（0：禁用；1：启用）*/
	@TableField(value = "use_status")
	private String useStatus;
	/**审核（0：保存；1：提交；2：打回；3：审核中；4：已审核）*/
	@TableField(value = "use_status")
	private String auditStatus;

	@TableField(exist = false)
	private String useType;
	
	/**
	 * 获取  id
	 *@return: String  主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  orgId
	 *@return: String  组织id
	 */
	public String getOrgId(){
		return this.orgId;
	}

	/**
	 * 设置  orgId
	 *@param: orgId  组织id
	 */
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
	/**
	 * 获取  projectNumber
	 *@return: String  项目编号
	 */
	public String getProjectNumber(){
		return this.projectNumber;
	}

	/**
	 * 设置  projectNumber
	 *@param: projectNumber  项目编号
	 */
	public void setProjectNumber(String projectNumber){
		this.projectNumber = projectNumber;
	}
	/**
	 * 获取  projectFullName
	 *@return: String  项目全称
	 */
	public String getProjectFullName(){
		return this.projectFullName;
	}

	/**
	 * 设置  projectFullName
	 *@param: projectFullName  项目全称
	 */
	public void setProjectFullName(String projectFullName){
		this.projectFullName = projectFullName;
	}
	/**
	 * 获取  projectShortName
	 *@return: String  项目简称
	 */
	public String getProjectShortName(){
		return this.projectShortName;
	}

	/**
	 * 设置  projectShortName
	 *@param: projectShortName  项目简称
	 */
	public void setProjectShortName(String projectShortName){
		this.projectShortName = projectShortName;
	}
	/**
	 * 获取  ownerOrg
	 *@return: String  业主单位
	 */
	public String getOwnerOrg(){
		return this.ownerOrg;
	}

	/**
	 * 设置  ownerOrg
	 *@param: ownerOrg  业主单位
	 */
	public void setOwnerOrg(String ownerOrg){
		this.ownerOrg = ownerOrg;
	}
	/**
	 * 获取  startPointName
	 *@return: String  起点名称
	 */
	public String getStartPointName(){
		return this.startPointName;
	}

	/**
	 * 设置  startPointName
	 *@param: startPointName  起点名称
	 */
	public void setStartPointName(String startPointName){
		this.startPointName = startPointName;
	}
	/**
	 * 获取  endPointName
	 *@return: String  终点名称
	 */
	public String getEndPointName(){
		return this.endPointName;
	}

	/**
	 * 设置  endPointName
	 *@param: endPointName  终点名称
	 */
	public void setEndPointName(String endPointName){
		this.endPointName = endPointName;
	}
	/**
	 * 获取  startPileNo
	 *@return: String  起始桩号
	 */
	public String getStartPileNo(){
		return this.startPileNo;
	}

	/**
	 * 设置  startPileNo
	 *@param: startPileNo  起始桩号
	 */
	public void setStartPileNo(String startPileNo){
		this.startPileNo = startPileNo;
	}
	/**
	 * 获取  endPileNo
	 *@return: String  终点桩号
	 */
	public String getEndPileNo(){
		return this.endPileNo;
	}

	/**
	 * 设置  endPileNo
	 *@param: endPileNo  终点桩号
	 */
	public void setEndPileNo(String endPileNo){
		this.endPileNo = endPileNo;
	}
	/**
	 * 获取  startPlanDate
	 *@return: String  计划开始日期
	 */
	public Date getStartPlanDate(){
		return this.startPlanDate;
	}

	/**
	 * 设置  startPlanDate
	 *@param: startPlanDate  计划开始日期
	 */
	public void setStartPlanDate(Date startPlanDate){
		this.startPlanDate = startPlanDate;
	}
	/**
	 * 获取  endPlanDate
	 *@return: String  计划完工日期
	 */
	public Date getEndPlanDate(){
		return this.endPlanDate;
	}

	/**
	 * 设置  endPlanDate
	 *@param: endPlanDate  计划完工日期
	 */
	public void setEndPlanDate(Date endPlanDate){
		this.endPlanDate = endPlanDate;
	}
	/**
	 * 获取  startActualDate
	 *@return: String  实际开工日期
	 */
	public Date getStartActualDate(){
		return this.startActualDate;
	}

	/**
	 * 设置  startActualDate
	 *@param: startActualDate  实际开工日期
	 */
	public void setStartActualDate(Date startActualDate){
		this.startActualDate = startActualDate;
	}
	/**
	 * 获取  endActualDate
	 *@return: String  实际结束日期
	 */
	public Date getEndActualDate(){
		return this.endActualDate;
	}

	/**
	 * 设置  endActualDate
	 *@param: endActualDate  实际结束日期
	 */
	public void setEndActualDate(Date endActualDate){
		this.endActualDate = endActualDate;
	}
	/**
	 * 获取  legalRepresentative
	 *@return: String  法人代表
	 */
	public String getLegalRepresentative(){
		return this.legalRepresentative;
	}

	/**
	 * 设置  legalRepresentative
	 *@param: legalRepresentative  法人代表
	 */
	public void setLegalRepresentative(String legalRepresentative){
		this.legalRepresentative = legalRepresentative;
	}
	/**
	 * 获取  totalCost
	 *@return: String  总造价（万）
	 */
	public String getTotalCost(){
		return this.totalCost;
	}

	/**
	 * 设置  totalCost
	 *@param: totalCost  总造价（万）
	 */
	public void setTotalCost(String totalCost){
		this.totalCost = totalCost;
	}
	/**
	 * 获取  constructionType
	 *@return: String  建设性质
	 */
	public String getConstructionType(){
		return this.constructionType;
	}

	/**
	 * 设置  constructionType
	 *@param: constructionType  建设性质
	 */
	public void setConstructionType(String constructionType){
		this.constructionType = constructionType;
	}
	/**
	 * 获取  investmentMode
	 *@return: String  投资方式
	 */
	public String getInvestmentMode(){
		return this.investmentMode;
	}

	/**
	 * 设置  investmentMode
	 *@param: investmentMode  投资方式
	 */
	public void setInvestmentMode(String investmentMode){
		this.investmentMode = investmentMode;
	}
	/**
	 * 获取  mainDesignUnit
	 *@return: String  主要设计单位
	 */
	public String getMainDesignUnit(){
		return this.mainDesignUnit;
	}

	/**
	 * 设置  mainDesignUnit
	 *@param: mainDesignUnit  主要设计单位
	 */
	public void setMainDesignUnit(String mainDesignUnit){
		this.mainDesignUnit = mainDesignUnit;
	}
	/**
	 * 获取  unitCost
	 *@return: String  单位造价
	 */
	public String getUnitCost(){
		return this.unitCost;
	}

	/**
	 * 设置  unitCost
	 *@param: unitCost  单位造价
	 */
	public void setUnitCost(String unitCost){
		this.unitCost = unitCost;
	}
	/**
	 * 获取  projectCategory
	 *@return: String  工程类别
	 */
	public String getProjectCategory(){
		return this.projectCategory;
	}

	/**
	 * 设置  projectCategory
	 *@param: projectCategory  工程类别
	 */
	public void setProjectCategory(String projectCategory){
		this.projectCategory = projectCategory;
	}
	/**
	 * 获取  otherInfor
	 *@return: String  其他信息
	 */
	public String getOtherInfor(){
		return this.otherInfor;
	}

	/**
	 * 设置  otherInfor
	 *@param: otherInfor  其他信息
	 */
	public void setOtherInfor(String otherInfor){
		this.otherInfor = otherInfor;
	}
	/**
	 * 获取  projectIntroduction
	 *@return: String  项目简介
	 */
	public String getProjectIntroduction(){
		return this.projectIntroduction;
	}

	/**
	 * 设置  projectIntroduction
	 *@param: projectIntroduction  项目简介
	 */
	public void setProjectIntroduction(String projectIntroduction){
		this.projectIntroduction = projectIntroduction;
	}
	/**
	 * 获取  technicalLevel
	 *@return: String  技术等级
	 */
	public String getTechnicalLevel(){
		return this.technicalLevel;
	}

	/**
	 * 设置  technicalLevel
	 *@param: technicalLevel  技术等级
	 */
	public void setTechnicalLevel(String technicalLevel){
		this.technicalLevel = technicalLevel;
	}
	/**
	 * 获取  routeLength
	 *@return: String  路线长度
	 */
	public String getRouteLength(){
		return this.routeLength;
	}

	/**
	 * 设置  routeLength
	 *@param: routeLength  路线长度
	 */
	public void setRouteLength(String routeLength){
		this.routeLength = routeLength;
	}
	/**
	 * 获取  roadbedCut
	 *@return: String  路基挖方
	 */
	public String getRoadbedCut(){
		return this.roadbedCut;
	}

	/**
	 * 设置  roadbedCut
	 *@param: roadbedCut  路基挖方
	 */
	public void setRoadbedCut(String roadbedCut){
		this.roadbedCut = roadbedCut;
	}
	/**
	 * 获取  roadbedProtectiveEngineering
	 *@return: String  路基防护工程
	 */
	public String getRoadbedProtectiveEngineering(){
		return this.roadbedProtectiveEngineering;
	}

	/**
	 * 设置  roadbedProtectiveEngineering
	 *@param: roadbedProtectiveEngineering  路基防护工程
	 */
	public void setRoadbedProtectiveEngineering(String roadbedProtectiveEngineering){
		this.roadbedProtectiveEngineering = roadbedProtectiveEngineering;
	}
	/**
	 * 获取  roadbedDrainageWorks
	 *@return: String  路基排水工程
	 */
	public String getRoadbedDrainageWorks(){
		return this.roadbedDrainageWorks;
	}

	/**
	 * 设置  roadbedDrainageWorks
	 *@param: roadbedDrainageWorks  路基排水工程
	 */
	public void setRoadbedDrainageWorks(String roadbedDrainageWorks){
		this.roadbedDrainageWorks = roadbedDrainageWorks;
	}
	/**
	 * 获取  roadbedWidth
	 *@return: String  路基宽度
	 */
	public String getRoadbedWidth(){
		return this.roadbedWidth;
	}

	/**
	 * 设置  roadbedWidth
	 *@param: roadbedWidth  路基宽度
	 */
	public void setRoadbedWidth(String roadbedWidth){
		this.roadbedWidth = roadbedWidth;
	}
	/**
	 * 获取  roadbedFill
	 *@return: String  路基填方
	 */
	public String getRoadbedFill(){
		return this.roadbedFill;
	}

	/**
	 * 设置  roadbedFill
	 *@param: roadbedFill  路基填方
	 */
	public void setRoadbedFill(String roadbedFill){
		this.roadbedFill = roadbedFill;
	}
	/**
	 * 获取  odPileNo
	 *@return: String  起讫桩号
	 */
	public String getOdPileNo(){
		return this.odPileNo;
	}

	/**
	 * 设置  odPileNo
	 *@param: odPileNo  起讫桩号
	 */
	public void setOdPileNo(String odPileNo){
		this.odPileNo = odPileNo;
	}
	/**
	 * 获取  grandBridge
	 *@return: String  特大桥
	 */
	public String getGrandBridge(){
		return this.grandBridge;
	}

	/**
	 * 设置  grandBridge
	 *@param: grandBridge  特大桥
	 */
	public void setGrandBridge(String grandBridge){
		this.grandBridge = grandBridge;
	}
	/**
	 * 获取  greatBridge
	 *@return: String  大桥
	 */
	public String getGreatBridge(){
		return this.greatBridge;
	}

	/**
	 * 设置  greatBridge
	 *@param: greatBridge  大桥
	 */
	public void setGreatBridge(String greatBridge){
		this.greatBridge = greatBridge;
	}
	/**
	 * 获取  middleBridge
	 *@return: String  中桥
	 */
	public String getMiddleBridge(){
		return this.middleBridge;
	}

	/**
	 * 设置  middleBridge
	 *@param: middleBridge  中桥
	 */
	public void setMiddleBridge(String middleBridge){
		this.middleBridge = middleBridge;
	}
	/**
	 * 获取  smallBridge
	 *@return: String  小桥
	 */
	public String getSmallBridge(){
		return this.smallBridge;
	}

	/**
	 * 设置  smallBridge
	 *@param: smallBridge  小桥
	 */
	public void setSmallBridge(String smallBridge){
		this.smallBridge = smallBridge;
	}
	/**
	 * 获取  bridgeWidth
	 *@return: String  桥梁宽度
	 */
	public String getBridgeWidth(){
		return this.bridgeWidth;
	}

	/**
	 * 设置  bridgeWidth
	 *@param: bridgeWidth  桥梁宽度
	 */
	public void setBridgeWidth(String bridgeWidth){
		this.bridgeWidth = bridgeWidth;
	}
	/**
	 * 获取  designLoad
	 *@return: String  设计载荷
	 */
	public String getDesignLoad(){
		return this.designLoad;
	}

	/**
	 * 设置  designLoad
	 *@param: designLoad  设计载荷
	 */
	public void setDesignLoad(String designLoad){
		this.designLoad = designLoad;
	}
	/**
	 * 获取  designSpeed
	 *@return: String  设计车速
	 */
	public String getDesignSpeed(){
		return this.designSpeed;
	}

	/**
	 * 设置  designSpeed
	 *@param: designSpeed  设计车速
	 */
	public void setDesignSpeed(String designSpeed){
		this.designSpeed = designSpeed;
	}
	/**
	 * 获取  culvertChannel
	 *@return: String  涵洞通道
	 */
	public String getCulvertChannel(){
		return this.culvertChannel;
	}

	/**
	 * 设置  culvertChannel
	 *@param: culvertChannel  涵洞通道
	 */
	public void setCulvertChannel(String culvertChannel){
		this.culvertChannel = culvertChannel;
	}
	/**
	 * 获取  separateGradeCrossing
	 *@return: String  分离式立体交叉
	 */
	public String getSeparateGradeCrossing(){
		return this.separateGradeCrossing;
	}

	/**
	 * 设置  separateGradeCrossing
	 *@param: separateGradeCrossing  分离式立体交叉
	 */
	public void setSeparateGradeCrossing(String separateGradeCrossing){
		this.separateGradeCrossing = separateGradeCrossing;
	}
	/**
	 * 获取  interchange
	 *@return: String  互通式立体交叉
	 */
	public String getInterchange(){
		return this.interchange;
	}

	/**
	 * 设置  interchange
	 *@param: interchange  互通式立体交叉
	 */
	public void setInterchange(String interchange){
		this.interchange = interchange;
	}
	/**
	 * 获取  pavementThickness
	 *@return: String  路面厚度
	 */
	public String getPavementThickness(){
		return this.pavementThickness;
	}

	/**
	 * 设置  pavementThickness
	 *@param: pavementThickness  路面厚度
	 */
	public void setPavementThickness(String pavementThickness){
		this.pavementThickness = pavementThickness;
	}
	/**
	 * 获取  pavementStructureType
	 *@return: String  路面结构类型
	 */
	public String getPavementStructureType(){
		return this.pavementStructureType;
	}

	/**
	 * 设置  pavementStructureType
	 *@param: pavementStructureType  路面结构类型
	 */
	public void setPavementStructureType(String pavementStructureType){
		this.pavementStructureType = pavementStructureType;
	}
	/**
	 * 获取  roadbedCementConcreteArea
	 *@return: String  水泥砼路面面积
	 */
	public String getRoadbedCementConcreteArea(){
		return this.roadbedCementConcreteArea;
	}

	/**
	 * 设置  roadbedCementConcreteArea
	 *@param: roadbedCementConcreteArea  水泥砼路面面积
	 */
	public void setRoadbedCementConcreteArea(String roadbedCementConcreteArea){
		this.roadbedCementConcreteArea = roadbedCementConcreteArea;
	}
	/**
	 * 获取  roadbedAsphaltConcreteArea
	 *@return: String  沥青砼路面面积
	 */
	public String getRoadbedAsphaltConcreteArea(){
		return this.roadbedAsphaltConcreteArea;
	}

	/**
	 * 设置  roadbedAsphaltConcreteArea
	 *@param: roadbedAsphaltConcreteArea  沥青砼路面面积
	 */
	public void setRoadbedAsphaltConcreteArea(String roadbedAsphaltConcreteArea){
		this.roadbedAsphaltConcreteArea = roadbedAsphaltConcreteArea;
	}
	/**
	 * 获取  longTunnel
	 *@return: String  长隧道
	 */
	public String getLongTunnel(){
		return this.longTunnel;
	}

	/**
	 * 设置  longTunnel
	 *@param: longTunnel  长隧道
	 */
	public void setLongTunnel(String longTunnel){
		this.longTunnel = longTunnel;
	}
	/**
	 * 获取  middleTunnel
	 *@return: String  中隧道
	 */
	public String getMiddleTunnel(){
		return this.middleTunnel;
	}

	/**
	 * 设置  middleTunnel
	 *@param: middleTunnel  中隧道
	 */
	public void setMiddleTunnel(String middleTunnel){
		this.middleTunnel = middleTunnel;
	}
	/**
	 * 获取  shortTunnel
	 *@return: String  短隧道
	 */
	public String getShortTunnel(){
		return this.shortTunnel;
	}

	/**
	 * 设置  shortTunnel
	 *@param: shortTunnel  短隧道
	 */
	public void setShortTunnel(String shortTunnel){
		this.shortTunnel = shortTunnel;
	}
	/**
	 * 获取  superLongTunnel
	 *@return: String  特长隧道
	 */
	public String getSuperLongTunnel(){
		return this.superLongTunnel;
	}

	/**
	 * 设置  superLongTunnel
	 *@param: superLongTunnel  特长隧道
	 */
	public void setSuperLongTunnel(String superLongTunnel){
		this.superLongTunnel = superLongTunnel;
	}
	/**
	 * 获取  tunnelWidth
	 *@return: String  隧道宽度
	 */
	public String getTunnelWidth(){
		return this.tunnelWidth;
	}

	/**
	 * 设置  tunnelWidth
	 *@param: tunnelWidth  隧道宽度
	 */
	public void setTunnelWidth(String tunnelWidth){
		this.tunnelWidth = tunnelWidth;
	}
	/**
	 * 获取  other
	 *@return: String  其他
	 */
	public String getOther(){
		return this.other;
	}

	/**
	 * 设置  other
	 *@param: other  其他
	 */
	public void setOther(String other){
		this.other = other;
	}
	/**
	 * 获取  createBy
	 *@return: User  创建者
	 */
	public User getCreateBy(){
		return this.createBy;
	}

	/**
	 * 设置  createBy
	 *@param: createBy  创建者
	 */
	public void setCreateBy(User createBy){
		this.createBy = createBy;
	}
	/**
	 * 获取  createDate
	 *@return: Date  创建时间
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  创建时间
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateBy
	 *@return: User  更新者
	 */
	public User getUpdateBy(){
		return this.updateBy;
	}

	/**
	 * 设置  updateBy
	 *@param: updateBy  更新者
	 */
	public void setUpdateBy(User updateBy){
		this.updateBy = updateBy;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  更新时间
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  更新时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 * 获取  delFlag
	 *@return: String  删除标记（0：正常；1：删除）
	 */
	public String getDelFlag(){
		return this.delFlag;
	}

	/**
	 * 设置  delFlag
	 *@param: delFlag  删除标记（0：正常；1：删除）
	 */
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}
}
