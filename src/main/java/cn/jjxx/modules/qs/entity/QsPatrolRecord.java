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
 * @Title: 巡视记录
 * @Description: 巡视记录
 * @author jjxx
 * @date 2018-03-29 15:40:34
 * @version V1.0   
 *
 */
@TableName("qs_patrol_record")
@SuppressWarnings("serial")
public class QsPatrolRecord extends AbstractEntity<String> {

    /**字段主键*/
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
    /**更新时间*/
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
    @TableField(value = "del_flag")
	private String delFlag;
    /**备注信息*/
    @TableField(value = "remarks")
	private String remarks;
    /**编号*/
    @TableField(value = "number")
	private String number;
    /**关联报表标题ID*/
    @TableField(value = "title_table_id")
	private String tableTitleId;
    /**表号*/
    @TableField(value = "table_number")
	private String tableNumber;
    /**表单主标题*/
    @TableField(value = "table_main_title")
	private String tableMainTitle;
    /**表单副标题*/
    @TableField(value = "table_sub_title")
	private String tableSubTitle;
    /**表单名称*/
    @TableField(value = "table_name")
	private String tableName;
    /**组织ID*/
    @TableField(value = "org_id")
	private String orgId;
    /**施工单位*/
    @TableField(value = "construction_unit")
	private String constructionUnit;
    /**合同号*/
    @TableField(value = "contract_number")
	private String contractNumber;
    /**巡视人*/
    @TableField(value = "patrol_by")
	private String patrolBy;
    /**巡视时间*/
    @TableField(value = "patrol_date")
	private Date patrolDate;
    /**巡视范围*/
    @TableField(value = "patrol_range")
	private String patrolRange;
    /**主要施工情况*/
    @TableField(value = "main_construction_situation")
	private String mainConstructionSituation;
    /**主要问题*/
    @TableField(value = "mainProblem")
	private String mainProblem;
    /**问题及处理意见*/
    @TableField(value = "problem_handle_opinion")
	private String problemHandleOpinion;
    /**附件*/
    @TableField(value = "attachment")
	private String attachment;
    /**附件个数*/
    @TableField(exist=false)
    private int attachmentCount;
	
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
	 * 获取  tableTitleId
	 *@return: String  关联报表标题ID
	 */
	public String getTableTitleId(){
		return this.tableTitleId;
	}

	/**
	 * 设置  tableTitleId
	 *@param: tableTitleId  关联报表标题ID
	 */
	public void setTableTitleId(String tableTitleId){
		this.tableTitleId = tableTitleId;
	}
	/**
	 * 获取  tableNumber
	 *@return: String  表号
	 */
	public String getTableNumber(){
		return this.tableNumber;
	}

	/**
	 * 设置  tableNumber
	 *@param: tableNumber  表号
	 */
	public void setTableNumber(String tableNumber){
		this.tableNumber = tableNumber;
	}
	/**
	 * 获取  tableMainTitle
	 *@return: String  表单主标题
	 */
	public String getTableMainTitle(){
		return this.tableMainTitle;
	}

	/**
	 * 设置  tableMainTitle
	 *@param: tableMainTitle  表单主标题
	 */
	public void setTableMainTitle(String tableMainTitle){
		this.tableMainTitle = tableMainTitle;
	}
	/**
	 * 获取  tableSubTitle
	 *@return: String  表单副标题
	 */
	public String getTableSubTitle(){
		return this.tableSubTitle;
	}

	/**
	 * 设置  tableSubTitle
	 *@param: tableSubTitle  表单副标题
	 */
	public void setTableSubTitle(String tableSubTitle){
		this.tableSubTitle = tableSubTitle;
	}
	/**
	 * 获取  tableName
	 *@return: String  表单名称
	 */
	public String getTableName(){
		return this.tableName;
	}

	/**
	 * 设置  tableName
	 *@param: tableName  表单名称
	 */
	public void setTableName(String tableName){
		this.tableName = tableName;
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
	 * 获取  patrolBy
	 *@return: String  巡视人
	 */
	public String getPatrolBy(){
		return this.patrolBy;
	}

	/**
	 * 设置  patrolBy
	 *@param: patrolBy  巡视人
	 */
	public void setPatrolBy(String patrolBy){
		this.patrolBy = patrolBy;
	}
	/**
	 * 获取  patrolDate
	 *@return: Date  巡视时间
	 */
	public Date getPatrolDate(){
		return this.patrolDate;
	}

	/**
	 * 设置  patrolDate
	 *@param: patrolDate  巡视时间
	 */
	public void setPatrolDate(Date patrolDate){
		this.patrolDate = patrolDate;
	}
	/**
	 * 获取  patrolRange
	 *@return: String  巡视范围
	 */
	public String getPatrolRange(){
		return this.patrolRange;
	}

	/**
	 * 设置  patrolRange
	 *@param: patrolRange  巡视范围
	 */
	public void setPatrolRange(String patrolRange){
		this.patrolRange = patrolRange;
	}
	/**
	 * 获取  mainConstructionSituation
	 *@return: String  主要施工情况
	 */
	public String getMainConstructionSituation(){
		return this.mainConstructionSituation;
	}

	/**
	 * 设置  mainConstructionSituation
	 *@param: mainConstructionSituation  主要施工情况
	 */
	public void setMainConstructionSituation(String mainConstructionSituation){
		this.mainConstructionSituation = mainConstructionSituation;
	}
	/**
	 * 获取  mainProblem
	 *@return: String  主要问题
	 */
	public String getMainProblem(){
		return this.mainProblem;
	}

	/**
	 * 设置  mainProblem
	 *@param: mainProblem  主要问题
	 */
	public void setMainProblem(String mainProblem){
		this.mainProblem = mainProblem;
	}
	/**
	 * 获取  problemHandleOpinion
	 *@return: String  问题及处理意见
	 */
	public String getProblemHandleOpinion(){
		return this.problemHandleOpinion;
	}

	/**
	 * 设置  problemHandleOpinion
	 *@param: problemHandleOpinion  问题及处理意见
	 */
	public void setProblemHandleOpinion(String problemHandleOpinion){
		this.problemHandleOpinion = problemHandleOpinion;
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
	 *@return: String  附件个数
	 */
	public int getAttachmentCount(){
		return this.attachmentCount;
	}

	/**
	 * 设置  attachment
	 *@param: attachment  附件
	 */
	public void setAttachmentCount(int attachmentCount){
		this.attachmentCount = attachmentCount;
	}
	
}
