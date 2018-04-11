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
 * @Title: base_bid_segment_basic_info
 * @Description: base_bid_segment_basic_info
 * @author jjxx
 * @date 2018-03-27 13:43:43
 * @version V1.0   
 *
 */
@TableName("base_bid_segment_basic_info")
@SuppressWarnings("serial")
public class BaseBidSegmentBasicInfo extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**组织id*/
    @TableField(value = "org_id")
	private String orgId;
    /**标段编码*/
    @TableField(value = "bid_segment_number")
	private String bidSegmentNumber;
    /**标段类型*/
    @TableField(value = "bid_segment_type")
	private String bidSegmentType;
    /**标段名称*/
    @TableField(value = "bid_segment_full_name")
	private String bidSegmentFullName;
    /**标段简称*/
    @TableField(value = "bid_segment_short_name")
	private String bidSegmentShortName;
    /**起始桩号*/
    @TableField(value = "start_pile_no")
	private String startPileNo;
    /**终止桩号*/
    @TableField(value = "end_pile_no")
	private String endPileNo;
    /**标段长度*/
    @TableField(value = "bid_segment_length")
	private String bidSegmentLength;
    /**断链长度*/
    @TableField(value = "broken_chain_length")
	private String brokenChainLength;
    /**开工日期*/
    @TableField(value = "start_date")
	private Date startDate;
    /**完工日期*/
    @TableField(value = "end_date")
	private Date endDate;
    /**施工单位*/
    @TableField(value = "construction_unit")
	private String constructionUnit;
    /**监理单位*/
    @TableField(value = "supervision_unit")
	private String supervisionUnit;
    /**合同造价（万元）*/
    @TableField(value = "total_cost")
	private String totalCost;
    /**合同号*/
    @TableField(value = "contract_number")
	private String contractNumber;
    /**建设单位*/
    @TableField(value = "build_unit")
	private String buildUnit;
    /**其他基本信息*/
    @TableField(value = "other_base_info")
	private String otherBaseInfo;
    /**试验单位*/
    @TableField(value = "test_unit")
	private String testUnit;
    /**合同期限（月）*/
    @TableField(value = "contract_period")
	private String contractPeriod;
    /**简介*/
    @TableField(value = "introduction")
	private String introduction;
    /**路线长度*/
    @TableField(value = "route_length")
	private String routeLength;
    /**路基宽地*/
    @TableField(value = "subgrade_width")
	private String subgradeWidth;
    /**路面厚度*/
    @TableField(value = "pavement_thickness")
	private String pavementThickness;
    /**路面结构类型*/
    @TableField(value = "pavement_structure_type")
	private String pavementStructureType;
    /**桥梁宽度*/
    @TableField(value = "bridge_width")
	private String bridgeWidth;
    /**隧道宽度*/
    @TableField(value = "tunnel_width")
	private String tunnelWidth;
    /**路基挖方*/
    @TableField(value = "subgrade_excavation")
	private String subgradeExcavation;
    /**路基填方*/
    @TableField(value = "subgrade_fill")
	private String subgradeFill;
    /**路基防护工程*/
    @TableField(value = "subgrade_protection_engineering")
	private String subgradeProtectionEngineering;
    /**路基排水工程*/
    @TableField(value = "subgrade_drainage_project")
	private String subgradeDrainageProject;
    /**沥青砼路面面积*/
    @TableField(value = "roadbed_asphalt_concrete_area")
	private String roadbedAsphaltConcreteArea;
    /**水泥砼路面面积*/
    @TableField(value = "roadbed_cement_concrete_area")
	private String roadbedCementConcreteArea;
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
    /**涵洞通道*/
    @TableField(value = "culvert_channel")
	private String culvertChannel;
    /**特长隧道*/
    @TableField(value = "super_long_tunnel")
	private String superLongTunnel;
    /**长隧道*/
    @TableField(value = "long_tunnel")
	private String longTunnel;
    /**中隧道*/
    @TableField(value = "middle_tunnel")
	private String middleTunnel;
    /**短隧道*/
    @TableField(value = "short_tunnel")
	private String shortTunnel;
    /**分离式立体交叉*/
    @TableField(value = "separate_grade_crossing")
	private String separateGradeCrossing;
    /**互通式立体交叉*/
    @TableField(value = "interchange")
	private String interchange;
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
	 * 获取  bidSegmentNumber
	 *@return: String  标段编码
	 */
	public String getBidSegmentNumber(){
		return this.bidSegmentNumber;
	}

	/**
	 * 设置  bidSegmentNumber
	 *@param: bidSegmentNumber  标段编码
	 */
	public void setBidSegmentNumber(String bidSegmentNumber){
		this.bidSegmentNumber = bidSegmentNumber;
	}
	/**
	 * 获取  bidSegmentType
	 *@return: String  标段类型
	 */
	public String getBidSegmentType(){
		return this.bidSegmentType;
	}

	/**
	 * 设置  bidSegmentType
	 *@param: bidSegmentType  标段类型
	 */
	public void setBidSegmentType(String bidSegmentType){
		this.bidSegmentType = bidSegmentType;
	}
	/**
	 * 获取  bidSegmentFullName
	 *@return: String  标段名称
	 */
	public String getBidSegmentFullName(){
		return this.bidSegmentFullName;
	}

	/**
	 * 设置  bidSegmentFullName
	 *@param: bidSegmentFullName  标段名称
	 */
	public void setBidSegmentFullName(String bidSegmentFullName){
		this.bidSegmentFullName = bidSegmentFullName;
	}
	/**
	 * 获取  bidSegmentShortName
	 *@return: String  标段简称
	 */
	public String getBidSegmentShortName(){
		return this.bidSegmentShortName;
	}

	/**
	 * 设置  bidSegmentShortName
	 *@param: bidSegmentShortName  标段简称
	 */
	public void setBidSegmentShortName(String bidSegmentShortName){
		this.bidSegmentShortName = bidSegmentShortName;
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
	 *@return: String  终止桩号
	 */
	public String getEndPileNo(){
		return this.endPileNo;
	}

	/**
	 * 设置  endPileNo
	 *@param: endPileNo  终止桩号
	 */
	public void setEndPileNo(String endPileNo){
		this.endPileNo = endPileNo;
	}
	/**
	 * 获取  bidSegmentLength
	 *@return: String  标段长度
	 */
	public String getBidSegmentLength(){
		return this.bidSegmentLength;
	}

	/**
	 * 设置  bidSegmentLength
	 *@param: bidSegmentLength  标段长度
	 */
	public void setBidSegmentLength(String bidSegmentLength){
		this.bidSegmentLength = bidSegmentLength;
	}
	/**
	 * 获取  brokenChainLength
	 *@return: String  断链长度
	 */
	public String getBrokenChainLength(){
		return this.brokenChainLength;
	}

	/**
	 * 设置  brokenChainLength
	 *@param: brokenChainLength  断链长度
	 */
	public void setBrokenChainLength(String brokenChainLength){
		this.brokenChainLength = brokenChainLength;
	}
	/**
	 * 获取  startDate
	 *@return: Date  开工日期
	 */
	public Date getStartDate(){
		return this.startDate;
	}

	/**
	 * 设置  startDate
	 *@param: startDate  开工日期
	 */
	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}
	/**
	 * 获取  endDate
	 *@return: Date  完工日期
	 */
	public Date getEndDate(){
		return this.endDate;
	}

	/**
	 * 设置  endDate
	 *@param: endDate  完工日期
	 */
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	/**
	 * 获取  constructionUnit
	 *@return: String  施工单位
	 */
	public String getConstructionUnit(){
		return this.constructionUnit;
	}

	/**
	 * 设置  constructionUnit
	 *@param: constructionUnit  施工单位
	 */
	public void setConstructionUnit(String constructionUnit){
		this.constructionUnit = constructionUnit;
	}
	/**
	 * 获取  supervisionUnit
	 *@return: String  监理单位
	 */
	public String getSupervisionUnit(){
		return this.supervisionUnit;
	}

	/**
	 * 设置  supervisionUnit
	 *@param: supervisionUnit  监理单位
	 */
	public void setSupervisionUnit(String supervisionUnit){
		this.supervisionUnit = supervisionUnit;
	}
	/**
	 * 获取  totalCost
	 *@return: String  合同造价（万元）
	 */
	public String getTotalCost(){
		return this.totalCost;
	}

	/**
	 * 设置  totalCost
	 *@param: totalCost  合同造价（万元）
	 */
	public void setTotalCost(String totalCost){
		this.totalCost = totalCost;
	}
	/**
	 * 获取  contractNumber
	 *@return: String  合同号
	 */
	public String getContractNumber(){
		return this.contractNumber;
	}

	/**
	 * 设置  contractNumber
	 *@param: contractNumber  合同号
	 */
	public void setContractNumber(String contractNumber){
		this.contractNumber = contractNumber;
	}
	/**
	 * 获取  buildUnit
	 *@return: String  建设单位
	 */
	public String getBuildUnit(){
		return this.buildUnit;
	}

	/**
	 * 设置  buildUnit
	 *@param: buildUnit  建设单位
	 */
	public void setBuildUnit(String buildUnit){
		this.buildUnit = buildUnit;
	}
	/**
	 * 获取  otherBaseInfo
	 *@return: String  其他基本信息
	 */
	public String getOtherBaseInfo(){
		return this.otherBaseInfo;
	}

	/**
	 * 设置  otherBaseInfo
	 *@param: otherBaseInfo  其他基本信息
	 */
	public void setOtherBaseInfo(String otherBaseInfo){
		this.otherBaseInfo = otherBaseInfo;
	}
	/**
	 * 获取  testUnit
	 *@return: String  试验单位
	 */
	public String getTestUnit(){
		return this.testUnit;
	}

	/**
	 * 设置  testUnit
	 *@param: testUnit  试验单位
	 */
	public void setTestUnit(String testUnit){
		this.testUnit = testUnit;
	}
	/**
	 * 获取  contractPeriod
	 *@return: String  合同期限（月）
	 */
	public String getContractPeriod(){
		return this.contractPeriod;
	}

	/**
	 * 设置  contractPeriod
	 *@param: contractPeriod  合同期限（月）
	 */
	public void setContractPeriod(String contractPeriod){
		this.contractPeriod = contractPeriod;
	}
	/**
	 * 获取  introduction
	 *@return: String  简介
	 */
	public String getIntroduction(){
		return this.introduction;
	}

	/**
	 * 设置  introduction
	 *@param: introduction  简介
	 */
	public void setIntroduction(String introduction){
		this.introduction = introduction;
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
	 * 获取  subgradeWidth
	 *@return: String  路基宽地
	 */
	public String getSubgradeWidth(){
		return this.subgradeWidth;
	}

	/**
	 * 设置  subgradeWidth
	 *@param: subgradeWidth  路基宽地
	 */
	public void setSubgradeWidth(String subgradeWidth){
		this.subgradeWidth = subgradeWidth;
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
	 * 获取  subgradeExcavation
	 *@return: String  路基挖方
	 */
	public String getSubgradeExcavation(){
		return this.subgradeExcavation;
	}

	/**
	 * 设置  subgradeExcavation
	 *@param: subgradeExcavation  路基挖方
	 */
	public void setSubgradeExcavation(String subgradeExcavation){
		this.subgradeExcavation = subgradeExcavation;
	}
	/**
	 * 获取  subgradeFill
	 *@return: String  路基填方
	 */
	public String getSubgradeFill(){
		return this.subgradeFill;
	}

	/**
	 * 设置  subgradeFill
	 *@param: subgradeFill  路基填方
	 */
	public void setSubgradeFill(String subgradeFill){
		this.subgradeFill = subgradeFill;
	}
	/**
	 * 获取  subgradeProtectionEngineering
	 *@return: String  路基防护工程
	 */
	public String getSubgradeProtectionEngineering(){
		return this.subgradeProtectionEngineering;
	}

	/**
	 * 设置  subgradeProtectionEngineering
	 *@param: subgradeProtectionEngineering  路基防护工程
	 */
	public void setSubgradeProtectionEngineering(String subgradeProtectionEngineering){
		this.subgradeProtectionEngineering = subgradeProtectionEngineering;
	}
	/**
	 * 获取  subgradeDrainageProject
	 *@return: String  路基排水工程
	 */
	public String getSubgradeDrainageProject(){
		return this.subgradeDrainageProject;
	}

	/**
	 * 设置  subgradeDrainageProject
	 *@param: subgradeDrainageProject  路基排水工程
	 */
	public void setSubgradeDrainageProject(String subgradeDrainageProject){
		this.subgradeDrainageProject = subgradeDrainageProject;
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
	 *@return: String  创建时间
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
	 *@return: String  更新时间
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
}
