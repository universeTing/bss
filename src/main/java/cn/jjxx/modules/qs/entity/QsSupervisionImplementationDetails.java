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
 * @Title: 监理实施细则
 * @Description: 监理实施细则
 * @author jjxx
 * @date 2018-03-07 13:17:34
 * @version V1.0   
 *
 */
@TableName("qs_supervision_implementation_details")
@SuppressWarnings("serial")
public class QsSupervisionImplementationDetails extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**创建者id*/
    @TableField(value = "create_by",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    /**创建者名称*/
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
    /**标题*/
    @TableField(value = "title")
	private String title;
    /**上传单位*/
    @TableField(value = "org_id")
	private String orgId;
    /**单位名称*/
    @TableField(exist=false)
    private String orgName;
    /**附件*/
    @TableField(value = "attachment")
	private String attachment;
	/**附件数*/
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
	 * 获取  createByName
	 *@return: createByName  创建者名称
	 */
	public String getCreateByName() {
		return createByName;
	}
	/**
	 * 设置  createByName
	 *@param: createByName  创建者
	 */
    public void setCreateByName(String createByName) {
		this.createByName = createByName;
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
	 * 获取  title
	 *@return: String  标题
	 */
	public String getTitle(){
		return this.title;
	}

	/**
	 * 设置  title
	 *@param: title  标题
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 * 获取  orgId
	 *@return: String  上传单位
	 */
	public String getOrgId(){
		return this.orgId;
	}
	/**
	 * 设置  orgName
	 *@param: orgName  组织名称
	 */
	 public void setOrgName(String orgName) {
			this.orgName = orgName;
	}
	 
 	/**
	 * 获取  orgName
	 *@return: orgName  获取组织名称
	 */ 
    public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置  orgId
	 *@param: orgId  上传单位
	 */
	public void setOrgId(String orgId){
		this.orgId = orgId;
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
	 *@return: int  附件数
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
	
}
