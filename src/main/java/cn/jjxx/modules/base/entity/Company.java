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
 * @Title: 公司资料
 * @Description: 公司资料
 * @author jjxx
 * @date 2018-02-07 17:14:40
 * @version V1.0   
 *
 */
@TableName("base_company")
@SuppressWarnings("serial")
public class Company extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**公司编号*/
    @TableField(value = "number")
	private String number;
    /**公司名称*/
    @TableField(value = "name")
	private String name;
    /**公司类型*/
    @TableField(value = "company_type")
	private String companyType;
    /**公司简称*/
    @TableField(value = "simple_name")
	private String simpleName;
    /**公司地址*/
    @TableField(value = "address")
	private String address;
    /**法人*/
    @TableField(value = "corporation")
	private String corporation;
    /**联系人*/
    @TableField(value = "contacts")
	private String contacts;
    /**联系电话*/
    @TableField(value = "contacts_phone")
	private String contactsPhone;
    /**邮政编码*/
    @TableField(value = "post_code")
	private String postCode;
    /**电子邮箱*/
    @TableField(value = "email")
	private String email;
    /**传真*/
    @TableField(value = "fax")
	private String fax;
    /**工商号*/
    @TableField(value = "ind_com_number")
	private String indComNumber;
    /**税务号*/
    @TableField(value = "tax_number")
	private String taxNumber;
    /**描述*/
    @TableField(value = "description")
	private String description;
    /**组织Id*/
    @TableField(value = "org_id")
	private String orgId;
    /**状态（0：保存；1：提交）*/
    @TableField(value = "status")
	private Integer status;
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
    /**创建人*/
    @TableField(exist=false)
    private String creator;
	
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
	 * 获取  number
	 *@return: String  公司编号
	 */
	public String getNumber(){
		return this.number;
	}

	/**
	 * 设置  number
	 *@param: number  公司编号
	 */
	public void setNumber(String number){
		this.number = number;
	}
	/**
	 * 获取  name
	 *@return: String  公司名称
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  公司名称
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  companyType
	 *@return: String  公司类型
	 */
	public String getCompanyType(){
		return this.companyType;
	}

	/**
	 * 设置  companyType
	 *@param: companyType  公司类型
	 */
	public void setCompanyType(String companyType){
		this.companyType = companyType;
	}
	/**
	 * 获取  simpleName
	 *@return: String  公司简称
	 */
	public String getSimpleName(){
		return this.simpleName;
	}

	/**
	 * 设置  simpleName
	 *@param: simpleName  公司简称
	 */
	public void setSimpleName(String simpleName){
		this.simpleName = simpleName;
	}
	/**
	 * 获取  address
	 *@return: String  公司地址
	 */
	public String getAddress(){
		return this.address;
	}

	/**
	 * 设置  address
	 *@param: address  公司地址
	 */
	public void setAddress(String address){
		this.address = address;
	}
	/**
	 * 获取  corporation
	 *@return: String  法人
	 */
	public String getCorporation(){
		return this.corporation;
	}

	/**
	 * 设置  corporation
	 *@param: corporation  法人
	 */
	public void setCorporation(String corporation){
		this.corporation = corporation;
	}
	/**
	 * 获取  contacts
	 *@return: String  联系人
	 */
	public String getContacts(){
		return this.contacts;
	}

	/**
	 * 设置  contacts
	 *@param: contacts  联系人
	 */
	public void setContacts(String contacts){
		this.contacts = contacts;
	}
	/**
	 * 获取  contactsPhone
	 *@return: String  联系电话
	 */
	public String getContactsPhone(){
		return this.contactsPhone;
	}

	/**
	 * 设置  contactsPhone
	 *@param: contactsPhone  联系电话
	 */
	public void setContactsPhone(String contactsPhone){
		this.contactsPhone = contactsPhone;
	}
	/**
	 * 获取  postCode
	 *@return: String  邮政编码
	 */
	public String getPostCode(){
		return this.postCode;
	}

	/**
	 * 设置  postCode
	 *@param: postCode  邮政编码
	 */
	public void setPostCode(String postCode){
		this.postCode = postCode;
	}
	/**
	 * 获取  email
	 *@return: String  电子邮箱
	 */
	public String getEmail(){
		return this.email;
	}

	/**
	 * 设置  email
	 *@param: email  电子邮箱
	 */
	public void setEmail(String email){
		this.email = email;
	}
	/**
	 * 获取  fax
	 *@return: String  传真
	 */
	public String getFax(){
		return this.fax;
	}

	/**
	 * 设置  fax
	 *@param: fax  传真
	 */
	public void setFax(String fax){
		this.fax = fax;
	}
	/**
	 * 获取  indComNumber
	 *@return: String  工商号
	 */
	public String getIndComNumber(){
		return this.indComNumber;
	}

	/**
	 * 设置  indComNumber
	 *@param: indComNumber  工商号
	 */
	public void setIndComNumber(String indComNumber){
		this.indComNumber = indComNumber;
	}
	/**
	 * 获取  taxNumber
	 *@return: String  税务号
	 */
	public String getTaxNumber(){
		return this.taxNumber;
	}

	/**
	 * 设置  taxNumber
	 *@param: taxNumber  税务号
	 */
	public void setTaxNumber(String taxNumber){
		this.taxNumber = taxNumber;
	}
	/**
	 * 获取  description
	 *@return: String  描述
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * 设置  description
	 *@param: description  描述
	 */
	public void setDescription(String description){
		this.description = description;
	}
	/**
	 * 获取  orgId
	 *@return: String  组织Id
	 */
	public String getOrgId(){
		return this.orgId;
	}

	/**
	 * 设置  orgId
	 *@param: orgId  组织Id
	 */
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
	/**
	 * 获取  status
	 *@return: Integer  状态（0：保存；1：提交）
	 */
	public Integer getStatus(){
		return this.status;
	}

	/**
	 * 设置  status
	 *@param: status  状态（0：保存；1：提交）
	 */
	public void setStatus(Integer status){
		this.status = status;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
}
