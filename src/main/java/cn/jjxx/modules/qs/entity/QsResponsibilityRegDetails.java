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
 * @Title: 质量责任登记表详情
 * @Description: 质量责任登记表详情
 * @author jjxx
 * @date 2018-03-21 10:35:46
 * @version V1.0   
 *
 */
@TableName("qs_responsibility_reg_details")
@SuppressWarnings("serial")
public class QsResponsibilityRegDetails extends AbstractEntity<String> {

    /**主键*/
	@TableId(value = "id", type = IdType.UUID)
	private String id;
    /**质量责任登记表id*/
    @TableField(value = "qs_responsibility_reg_id",el="qsResponsibilityReg.id")
	private QsResponsibilityReg qsResponsibilityReg;
    /**姓名*/
    @TableField(value = "name")
	private String name;
    /**身份证号*/
    @TableField(value = "idcard")
	private String idcard;
    /**职称及证书编号*/
    @TableField(value = "qualification")
	private String qualification;
    /**在岗时间*/
    @TableField(value = "on_the_job_date")
	private String onTheJobDate;
    /**承担安全责任内容*/
    @TableField(value = "work_content")
	private String workContent;
    /**责任人签字*/
    @TableField(value = "signature")
	private String signature;
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
	 * 获取  qsResponsibilityReg
	 *@return: QsResponsibilityReg  质量责任登记表id
	 */
	public QsResponsibilityReg getQsResponsibilityReg(){
		return this.qsResponsibilityReg;
	}

	/**
	 * 设置  qsResponsibilityReg
	 *@param: qsResponsibilityReg  质量责任登记表id
	 */
	public void setQsResponsibilityReg(QsResponsibilityReg qsResponsibilityReg){
		this.qsResponsibilityReg = qsResponsibilityReg;
	}
	/**
	 * 获取  name
	 *@return: String  姓名
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  姓名
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  idcard
	 *@return: String  身份证号
	 */
	public String getIdcard(){
		return this.idcard;
	}

	/**
	 * 设置  idcard
	 *@param: idcard  身份证号
	 */
	public void setIdcard(String idcard){
		this.idcard = idcard;
	}
	/**
	 * 获取  qualification
	 *@return: String  职称及证书编号
	 */
	public String getQualification(){
		return this.qualification;
	}

	/**
	 * 设置  qualification
	 *@param: qualification  职称及证书编号
	 */
	public void setQualification(String qualification){
		this.qualification = qualification;
	}
	/**
	 * 获取  onTheJobDate
	 *@return: String  在岗时间
	 */
	public String getOnTheJobDate(){
		return this.onTheJobDate;
	}

	/**
	 * 设置  onTheJobDate
	 *@param: onTheJobDate  在岗时间
	 */
	public void setOnTheJobDate(String onTheJobDate){
		this.onTheJobDate = onTheJobDate;
	}
	/**
	 * 获取  workContent
	 *@return: String  承担安全责任内容
	 */
	public String getWorkContent(){
		return this.workContent;
	}

	/**
	 * 设置  workContent
	 *@param: workContent  承担安全责任内容
	 */
	public void setWorkContent(String workContent){
		this.workContent = workContent;
	}
	/**
	 * 获取  signature
	 *@return: String  责任人签字
	 */
	public String getSignature(){
		return this.signature;
	}

	/**
	 * 设置  signature
	 *@param: signature  责任人签字
	 */
	public void setSignature(String signature){
		this.signature = signature;
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
	
}
