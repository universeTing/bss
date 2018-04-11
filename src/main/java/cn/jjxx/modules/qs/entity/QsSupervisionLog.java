package cn.jjxx.modules.qs.entity;

import cn.jjxx.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jjxx.modules.sys.entity.User;
import java.util.Date;

/**   
 * @Title: 监理日志
 * @Description: 监理日志
 * @author jjxx
 * @date 2018-03-30 10:36:58
 * @version V1.0   
 *
 */
@TableName("qs_supervision_log")
@SuppressWarnings("serial")
public class QsSupervisionLog extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**创建者*/
    @TableField(value = "create_by",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    /**创建人姓名*/
    @TableField(exist=false)
    private String createByName;
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
    /**备注信息*/
    @TableField(value = "remarks")
	private String remarks;
    /**表号*/
    @TableField(value = "table_id")
	private String tableId;
    /**工程项目名*/
    @TableField(value = "project_title")
	private String projectTitle;
    /**副标题*/
    @TableField(value = "sub_title")
	private String subTitle;
    /**编号*/
    @TableField(value = "number")
	private String number;
    /**监理机构*/
    @TableField(value = "org_id")
	private String orgId;
    /**单位名称*/
    @TableField(exist=false)
    private String orgName;
    /**记录人*/
    @TableField(value = "recorder")
	private String recorder;
    /**记录日期*/
    @TableField(value = "date")
	private Date date;
    /**天气情况*/
    @TableField(value = "weather_conditions")
	private String weatherConditions;
    /**主要施工情况*/
    @TableField(value = "build_situation")
	private String buildSituation;
    /**监理主要工作*/
    @TableField(value = "supersion_work")
	private String supervisonWork;
    /**问题及处理情况*/
    @TableField(value = "problem_handle_result")
	private String problemHandleResult;
    /**附件*/
    @TableField(value = "attachment")
	private String attachment;
    /**附件数*/
    @TableField(exist=false)
    private int attachmentCount;
	/**审核人id*/
    @TableField(value = "audit_by")
	private String auditBy;
    /**审核状态*/
    @TableField(value = "audit_status")
	private String auditStatus;
    /**启用状态*/
    @TableField(value = "use_status")
	private String useStatus;
    /**审核日期*/
    @TableField(value = "audit_date")
	private Date auditDate;
	
	/**
	 * 获取  id
	 *@return: String  字段主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  字段主键
	 */
	public void setId(String id){
		this.id = id;
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
	 * 获取  createByName
	 *@return: String  创建者姓名
	 */
	public String getCreateByName() {
		return createByName;
	}
	/**
	 * 通过createBy来获取创建者的真实姓名
	 * 设置  createByName
	 *@param: createByName  创建者姓名
	 */
	public void setCreateByName() {
		this.createByName = createBy.getRealname();
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
	 * 获取  tableId
	 *@return: String  表号
	 */
	public String getTableId(){
		return this.tableId;
	}

	/**
	 * 设置  tableId
	 *@param: tableId  表号
	 */
	public void setTableId(String tableId){
		this.tableId = tableId;
	}
	/**
	 * 获取  projectTitle
	 *@return: String  工程项目名
	 */
	public String getProjectTitle(){
		return this.projectTitle;
	}

	/**
	 * 设置  projectTitle
	 *@param: projectTitle  工程项目名
	 */
	public void setProjectTitle(String projectTitle){
		this.projectTitle = projectTitle;
	}
	/**
	 * 获取  subTitle
	 *@return: String  副标题
	 */
	public String getSubTitle(){
		return this.subTitle;
	}

	/**
	 * 设置  subTitle
	 *@param: subTitle  副标题
	 */
	public void setSubTitle(String subTitle){
		this.subTitle = subTitle;
	}
	/**
	 * 获取  number
	 *@return: String  编号
	 */
	public String getNumber(){
		return this.number;
	}

	/**
	 * 设置  number
	 *@param: number  编号
	 */
	public void setNumber(String number){
		this.number = number;
	}
	/**
	 * 获取  orgId
	 *@return: String  监理机构
	 */
	public String getOrgId(){
		return this.orgId;
	}

	/**
	 * 设置  orgId
	 *@param: orgId  监理机构
	 */
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
	
 	/**
	 * 获取  orgName
	 *@return: orgName  获取组织名称
	 */ 
    public String getOrgName() {
		return orgName;
	}
    /**
	 * 设置  orgName
	 *@param: orgName  组织名称
	 */
	 public void setOrgName(String orgName) {
			this.orgName = orgName;
	}
	/**
	 * 获取  recorder
	 *@return: String  记录人
	 */
	public String getRecorder(){
		return this.recorder;
	}

	/**
	 * 设置  recorder
	 *@param: recorder  记录人
	 */
	public void setRecorder(String recorder){
		this.recorder = recorder;
	}
	/**
	 * 获取  date
	 *@return: Date  记录日期
	 */
	public Date getDate(){
		return this.date;
	}

	/**
	 * 设置  date
	 *@param: date  记录日期
	 */
	public void setDate(Date date){
		this.date = date;
	}
	/**
	 * 获取  weatherConditions
	 *@return: String  天气情况
	 */
	public String getWeatherConditions(){
		return this.weatherConditions;
	}

	/**
	 * 设置  weatherConditions
	 *@param: weatherConditions  天气情况
	 */
	public void setWeatherConditions(String weatherConditions){
		this.weatherConditions = weatherConditions;
	}
	/**
	 * 获取  buildSituation
	 *@return: String  主要施工情况
	 */
	public String getBuildSituation(){
		return this.buildSituation;
	}

	/**
	 * 设置  buildSituation
	 *@param: buildSituation  主要施工情况
	 */
	public void setBuildSituation(String buildSituation){
		this.buildSituation = buildSituation;
	}
	/**
	 * 获取  supervisonWork
	 *@return: String  监理主要工作
	 */
	public String getSupervisonWork(){
		return this.supervisonWork;
	}

	/**
	 * 设置  supervisonWork
	 *@param: supervisonWork  监理主要工作
	 */
	public void setSupervisonWork(String supervisonWork){
		this.supervisonWork = supervisonWork;
	}
	/**
	 * 获取  problemHandleResult
	 *@return: String  问题及处理情况
	 */
	public String getProblemHandleResult(){
		return this.problemHandleResult;
	}

	/**
	 * 设置  problemHandleResult
	 *@param: problemHandleResult  问题及处理情况
	 */
	public void setProblemHandleResult(String problemHandleResult){
		this.problemHandleResult = problemHandleResult;
	}
	/**
	 * 获取  attachment
	 *@return: String  附件
	 */
	public String getAttachment(){
		return this.attachment;
	}

	/**
	 * 设置  attachment
	 *@param: attachment  附件
	 */
	public void setAttachment(String attachment){
		this.attachment = attachment;
	}
	/**
	 * 获取  attachmentCount
	 *@return: String  附件数
	 */
	public int getAttachmentCount() {
		return attachmentCount;
	}
	/**
	 * 设置  attachmentCount
	 *@param: attachmentCount  附件数
	 */
	public void setAttachmentCount(int attachmentCount) {
		this.attachmentCount = attachmentCount;
	}

	/**
	 * 获取  reviewer
	 *@return: String  审核人id
	 */
	public String getAuditBy(){
		return this.auditBy;
	}

	/**
	 * 设置  reviewer
	 *@param: reviewer  审核人id
	 */
	public void setAuditBy(String auditBy){
		this.auditBy = auditBy;
	}
	/**
	 * 获取  auditState
	 *@return: String  审核状态
	 */
	public String getAuditStatus(){
		return this.auditStatus;
	}

	/**
	 * 设置  auditState
	 *@param: auditState  审核状态
	 */
	public void setAuditStatus(String auditStatus){
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取  useStatus
	 *@return: String  启用状态
	 */
	public String getUseStatus(){
		return this.useStatus;
	}

	/**
	 * 设置  useStatus
	 *@param: useStatus  启用状态
	 */
	public void setUseStatus(String useStatus){
		this.useStatus = useStatus;
	}
	/**
	 * 获取  auditDate
	 *@return: Date  审核日期
	 */
	public Date getAuditDate(){
		return this.auditDate;
	}

	/**
	 * 设置  auditDate
	 *@param: auditDate  审核日期
	 */
	public void setAuditDate(Date auditDate){
		this.auditDate = auditDate;
	}
	
}
