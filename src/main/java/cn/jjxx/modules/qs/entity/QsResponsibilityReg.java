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
 * @Title: 质量责任登记表
 * @Description: 质量责任登记表
 * @author jjxx
 * @date 2018-03-21 10:35:46
 * @version V1.0   
 *
 */
@TableName("qs_responsibility_reg")
@SuppressWarnings("serial")
public class QsResponsibilityReg extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**单位id*/
    @TableField(value = "org_id")
	private String orgId;
    /**项目名称*/
    @TableField(value = "project_name")
	private String projectName;
    /**合同段号*/
    @TableField(value = "contract_stage_number")
	private String contractStageNumber;
    /**签章*/
    @TableField(value = "signature")
	private String signature;
    /**单位名称*/
    @TableField(value = "org_name")
	private String orgName;
    /**承担工作内容*/
    @TableField(value = "work_content")
	private String workContent;
    /**资质等级及证书编号*/
    @TableField(value = "qualification")
	private String qualification;
    /**备注*/
    @TableField(value = "remarks")
	private String remarks;
    /**附件*/
    @TableField(value = "attachment")
	private String attachment;
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

	/**附件个数*/
	@TableField(exist=false)
	private int attachmentCount;
	
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
	 *@return: String  单位id
	 */
	public String getOrgId(){
		return this.orgId;
	}

	/**
	 * 设置  orgId
	 *@param: orgId  单位id
	 */
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
	/**
	 * 获取  projectName
	 *@return: String  项目名称
	 */
	public String getProjectName(){
		return this.projectName;
	}

	/**
	 * 设置  projectName
	 *@param: projectName  项目名称
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}
	/**
	 * 获取  contractStageNumber
	 *@return: String  合同段号
	 */
	public String getContractStageNumber(){
		return this.contractStageNumber;
	}

	/**
	 * 设置  contractStageNumber
	 *@param: contractStageNumber  合同段号
	 */
	public void setContractStageNumber(String contractStageNumber){
		this.contractStageNumber = contractStageNumber;
	}
	/**
	 * 获取  signature
	 *@return: String  签章
	 */
	public String getSignature(){
		return this.signature;
	}

	/**
	 * 设置  signature
	 *@param: signature  签章
	 */
	public void setSignature(String signature){
		this.signature = signature;
	}
	/**
	 * 获取  orgName
	 *@return: String  单位名称
	 */
	public String getOrgName(){
		return this.orgName;
	}

	/**
	 * 设置  orgName
	 *@param: orgName  单位名称
	 */
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	/**
	 * 获取  workContent
	 *@return: String  承担工作内容
	 */
	public String getWorkContent(){
		return this.workContent;
	}

	/**
	 * 设置  workContent
	 *@param: workContent  承担工作内容
	 */
	public void setWorkContent(String workContent){
		this.workContent = workContent;
	}
	/**
	 * 获取  qualification
	 *@return: String  资质等级及证书编号
	 */
	public String getQualification() {
		return qualification;
	}
	/**
	 * 获取  qualification
	 *@return: String  资质等级及证书编号
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * 获取  remarks
	 *@return: String  备注
	 */
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks  备注
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
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

	public int getAttachmentCount() {
		return attachmentCount;
	}

	public void setAttachmentCount(int attachmentCount) {
		this.attachmentCount = attachmentCount;
	}
}