package cn.jjxx.modules.qs.entity;

import java.util.Date;

import cn.jjxx.core.common.entity.AbstractEntity;
import cn.jjxx.modules.sys.entity.User;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

/**   
 * @Title: qs_aside_record
 * @Description: qs_aside_record
 * @author jjxx
 * @date 2018-03-28 14:11:51
 * @version V1.0   
 *
 */
@TableName("qs_aside_record")
@SuppressWarnings("serial")
public class QsAsideRecord extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
   
    /**创建者*/
    @TableField(value = "create_by",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    
    /**创建时间*/
    @TableField(value = "create_date",fill = FieldFill.INSERT)
	private Date createDate;
    /**更新者*/
    @TableField(value = "update_by",el="updateBy.id",fill = FieldFill.UPDATE)
	private User updateBy;
    /**创建人姓名*/
    @TableField(exist=false)
    private String createByName;
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
    /**xx工程项目标题*/
    @TableField(value = "project_title")
	private String projectTitle;
    /**副标题*/
    @TableField(value = "sub_title")
	private String subTitle;
    /**编号*/
    @TableField(value = "number")
	private String number;
    /**旁站人*/
    @TableField(value = "aside_man")
	private String asideMan;
    /**旁站时间*/
    @TableField(value = "aside_date")
	private Date asideDate;
    /**旁站项目*/
    @TableField(value = "aside_project")
	private String asideProject;
    /**实施过程简述*/
    @TableField(value = "process_description")
	private String processDescription;
    /**旁站工作情况*/
    @TableField(value = "aside_situation")
	private String asideSituation;
    /**主要数据录入*/
    @TableField(value = "main_data")
	private String mainData;
    /**发现问题及处理结果*/
    @TableField(value = "problem_handle_result")
	private String problemHandleResult;
    /**附件*/
    @TableField(value = "attachment")
	private String attachment;
    /**组织id*/
    @TableField(value = "org_id")
	private String orgId;
    /**预留的关联基础数据的表id号*/
    @TableField(value = "bid_id")
	private String bidId;
    /**施工单位*/
    @TableField(value = "construction_unit")
	private String constructionUnit;
    /**合同号*/
    @TableField(value = "contract_number")
	private String contractNumber;
    /**附件数*/
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
	 * 获取  createBy
	 *@return: String  创建者
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
	 *@return: String  更新者
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
	 *@return: String  xx工程项目名
	 */
	public String getProjectTitle(){
		return this.projectTitle;
	}

	/**
	 * 设置  projectTitle
	 *@param: projectTitle  xx工程项目名
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
	 * 获取  asideMan
	 *@return: String  旁站人
	 */
	public String getAsideMan(){
		return this.asideMan;
	}

	/**
	 * 设置  asideMan
	 *@param: asideMan  旁站人
	 */
	public void setAsideMan(String asideMan){
		this.asideMan = asideMan;
	}
	/**
	 * 获取  asideDate
	 *@return: String  旁站时间
	 */
	public Date getAsideDate(){
		return this.asideDate;
	}

	/**
	 * 设置  asideDate
	 *@param: asideDate  旁站时间
	 */
	public void setAsideDate(Date asideDate){
		this.asideDate = asideDate;
	}
	/**
	 * 获取  asideProject
	 *@return: String  旁站项目
	 */
	public String getAsideProject(){
		return this.asideProject;
	}

	/**
	 * 设置  asideProject
	 *@param: asideProject  旁站项目
	 */
	public void setAsideProject(String asideProject){
		this.asideProject = asideProject;
	}
	/**
	 * 获取  processDescription
	 *@return: String  实施过程简述
	 */
	public String getProcessDescription(){
		return this.processDescription;
	}

	/**
	 * 设置  processDescription
	 *@param: processDescription  实施过程简述
	 */
	public void setProcessDescription(String processDescription){
		this.processDescription = processDescription;
	}
	/**
	 * 获取  asideSituation
	 *@return: String  旁站工作情况
	 */
	public String getAsideSituation(){
		return this.asideSituation;
	}

	/**
	 * 设置  asideSituation
	 *@param: asideSituation  旁站工作情况
	 */
	public void setAsideSituation(String asideSituation){
		this.asideSituation = asideSituation;
	}
	/**
	 * 获取  mainData
	 *@return: String  主要数据录入
	 */
	public String getMainData(){
		return this.mainData;
	}

	/**
	 * 设置  mainData
	 *@param: mainData  主要数据录入
	 */
	public void setMainData(String mainData){
		this.mainData = mainData;
	}
	/**
	 * 获取  problemHandleResult
	 *@return: String  发现问题及处理结果
	 */
	public String getProblemHandleResult(){
		return this.problemHandleResult;
	}

	/**
	 * 设置  problemHandleResult
	 *@param: problemHandleResult  发现问题及处理结果
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
	 * 获取  bidId
	 *@return: String  预留的关联基础数据的表id号
	 */
	public String getBidId(){
		return this.bidId;
	}

	/**
	 * 设置  bidId
	 *@param: bidId  预留的关联基础数据的表id号
	 */
	public void setBidId(String bidId){
		this.bidId = bidId;
	}
	/**
	 * 获取  buildUnit
	 *@return: String  施工单位
	 */
	public String getConstructionUnit() {
			return constructionUnit;
		}
	/**
	 * 设置  constructionUnit
	 *@param: constructionUnit  设置施工单位
	 */
	public void setConstructionUnit(String constructionUnit) {
			this.constructionUnit = constructionUnit;
		}
	/**
	 * 获取  contractNumber
	 *@return: String  合同段
	 */
	public String getContractNumber() {
			return contractNumber;
	}
	/**
	 * 设置  contractNumber
	 *@param: contractNumber  设置合同段
	 */
	public void setContractNumber(String contractNumber) {
			this.contractNumber = contractNumber;
	}
	/**
	 * 获取  createByName
	 *@return: String  创建者姓名
	 */
	public String getCreateByName() {
			return createByName;
	}
	/**
	* 设置  createByName
	*@param: createByName  设置创建者姓名
	*/
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	/**
	 * 获取  attachementCount
	 *@return: String  附件个数
	 */
	public int getAttachmentCount() {
		return attachmentCount;
	}
	/**
	* 设置  attachementCount
	*@param: attachementCount  设置附件个数
	*/
	public void setAttachmentCount(int attachmentCount) {
		this.attachmentCount = attachmentCount;
	}

}
