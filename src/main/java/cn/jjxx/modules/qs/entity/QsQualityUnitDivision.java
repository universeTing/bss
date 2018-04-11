package cn.jjxx.modules.qs.entity;

import cn.jjxx.core.common.entity.TreeEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jjxx.modules.sys.entity.User;

import java.math.BigDecimal;
import java.util.Date;

import org.framework.superutil.thirdparty.excel.Excel;


/**   
 * @Title: 质量单元划分
 * @Description: 质量单元划分
 * @author jjxx
 * @date 2018-03-14 12:06:30
 * @version V1.0   
 *
 */
@TableName("qs_quality_unit_division")
@SuppressWarnings("serial")
public class QsQualityUnitDivision extends TreeEntity<QsQualityUnitDivision> {
	
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
    /**单元编码*/
	@TableField(value = "code")
	@Excel(name="单元编码")
	private String code;
	/**单元名称*/
	@TableField(value = "name")
	@Excel(name="单元名称")
	private String name;
    /**组织ID*/
	@TableField(value = "org_id")
	private String orgId;
	/**单位*/
	@TableField(value = "unit")
	private String unit;
	/**单位名称*/
    @TableField(exist=false)
    @Excel(name="单位")
    private String unitName;
    /**数量*/
	@TableField(value = "number")
	@Excel(name="数量")
	private BigDecimal number;
    /**工程类型*/
	@TableField(value = "engineer_type")
	private String engineerType;
	/**工程类别名称*/
    @TableField(exist=false)
    @Excel(name="工程类别")
    private String engineerTypeName;
    /**节点类型*/
	@TableField(value = "node_point_type")
	private String nodePointType;   //nodeType 与 edit的验证产生冲突，将其重新命名为nodePointType
	/**节点类型名称*/
    @TableField(exist=false)
    @Excel(name="节点类型")
    private String nodeTypeName;
	/**上级编码*/
    @TableField(exist=false)
    @Excel(name="上级编码")
    private String parentCode;
    /**备注信息*/
	@TableField(value = "remarks")
	@Excel(name="说明")
	private String remarks;
    /**是否重点工程*/
	@TableField(value = "key_project")
	private Integer keyProject;
	/**是否重点工程(是、否)名称*/
    @TableField(exist=false)
    @Excel(name="是否重点工程(是、否)")
    private String keyProjectName;
    /**开工日期*/
	@TableField(value = "start_date")
	@Excel(name="开工日期(格式 YYYY/MM/DD)")
	private Date startDate;
    /**完工日期*/
	@TableField(value = "end_date")
	@Excel(name="完工日期(格式 YYYY/MM/DD)")
	private Date endDate;
	/**审核状态*/
	@TableField(value = "audit_status")
	private Integer auditStatus;
	/**审核者*/
	@TableField(value = "audit_by",el="auditBy.id",fill = FieldFill.UPDATE)
	private User auditBy;
    /**创建时间*/
	@TableField(value = "audit_date",fill = FieldFill.UPDATE)
	private Date auditDate;
	
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
	/**
	 * 获取  remarks
	 *@return: String  备注信息
	 */
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks  备注信息
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	/**
	 * 获取  code
	 *@return: String  单元编码
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * 设置  code
	 *@param: code  单元编码
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 获取  orgId
	 *@return: String  组织ID
	 */
	public String getOrgId(){
		return this.orgId;
	}

	/**
	 * 设置  orgId
	 *@param: orgId  组织ID
	 */
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
	/**
	 * 获取  engineerType
	 *@return: String  工程类型
	 */
	public String getEngineerType(){
		return this.engineerType;
	}

	/**
	 * 设置  engineerType
	 *@param: engineerType  工程类型
	 */
	public void setEngineerType(String engineerType){
		this.engineerType = engineerType;
	}
	/**
	 * 获取  nodeType
	 *@return: String  节点类型
	 */
	public String getNodePointType(){
		return this.nodePointType;
	}

	/**
	 * 设置  nodeType
	 *@param: nodeType  节点类型
	 */
	public void setNodePointType(String nodePointType){
		this.nodePointType = nodePointType;
	}
	/**
	 * 获取  keyProject
	 *@return: Integer  是否重点工程
	 */
	public Integer getKeyProject(){
		return this.keyProject;
	}

	/**
	 * 设置  keyProject
	 *@param: keyProject  是否重点工程
	 */
	public void setKeyProject(Integer keyProject){
		this.keyProject = keyProject;
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
	 * 获取  unit
	 *@return: String  单位
	 */
	public String getUnit(){
		return this.unit;
	}

	/**
	 * 设置  unit
	 *@param: unit  单位
	 */
	public void setUnit(String unit){
		this.unit = unit;
	}
	/**
	 * 获取  number
	 *@return: BigDecimal  数量
	 */
	public BigDecimal getNumber(){
		return this.number;
	}

	/**
	 * 设置  number
	 *@param: number  数量
	 */
	public void setNumber(BigDecimal number){
		this.number = number;
	}

	/**
	 * 获取  auditStatus
	 *@return: Integer  审核状态
	 */
	public Integer getAuditStatus() {
		return auditStatus;
	}

	/**
	 * 设置  auditStatus
	 *@param: auditStatus  审核状态
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * 获取  auditBy
	 *@return: User  审核者
	 */
	public User getAuditBy() {
		return auditBy;
	}

	/**
	 * 设置  auditBy
	 *@param: auditBy 审核者
	 */
	public void setAuditBy(User auditBy) {
		this.auditBy = auditBy;
	}

	/**
	 * 获取  auditDate
	 *@return: Date  审核时间
	 */
	public Date getAuditDate() {
		return auditDate;
	}

	/**
	 * 设置  auditDate
	 *@param: auditDate  审核时间
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	/**
	 * 获取  parentCode
	 *@return: String  上级编码
	 */
	public String getParentCode() {
		return parentCode;
	}
	
	/**
	 * 设置  parentCode
	 *@param: parentCode  上级编码
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getEngineerTypeName() {
		return engineerTypeName;
	}

	public void setEngineerTypeName(String engineerTypeName) {
		this.engineerTypeName = engineerTypeName;
	}

	public String getNodeTypeName() {
		return nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}

	public String getKeyProjectName() {
		return keyProjectName;
	}

	public void setKeyProjectName(String keyProjectName) {
		this.keyProjectName = keyProjectName;
	}
}