package net.cedu.entity.academy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;

import net.cedu.common.hibernate.SortChineseAnnotation;
import net.cedu.entity.admin.User;

/**
 * 院校
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_academy")
public class Academy implements Serializable,Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	// 主建ID
	private int id;

	@Column(name = "code")
	private String code; // 编码

	@Column(name = "name")
	@SortChineseAnnotation(sort = true)
	private String name; // 名称

	@Column(name = "picture_url")
	private String pictureUrl; // 图片引用路径

	@Column(name = "short_name")
	private String shortName; // 简称

	@Column(name = "founded_year")
	private String foundedYear; // 成立年度

	@Column(name = "telephone")
	private String telephone; // 院校联系电话

	@Column(name = "website")
	private String website; // 网址

	@Column(name = "scale")
	private int scale; // 规模

	@Column(name = "introduction")
	private String introduction; // 简介

	@Column(name = "audit_status")
	private int auditStatus; // 审核状态

	@Column(name = "using_status")
	private int usingStatus; // 启用状况

	@Column(name = "contract_endtime")
	private Date contractEndtime; // 合同截至日期

	@Column(name = "auditer")
	private int auditer; // 审核人

	@Column(name = "project_manager_id")
	private int projectManagerId; // 项目经理ID

	@Transient
	transient private String projectManagerName; // 项目经理姓名

	@Temporal(TemporalType.DATE)
	@Column(name = "audited_date")
	private Date auditedDate; // 审核日期

	@Column(name = "interface_setting_status")
	private int interfaceSettingStatus; // 接口设置状态

	@Column(name = "address")
	private String address; // 院校所在地

	@Column(name = "account")
	private String account; // 账号

	@Column(name = "rebates_feesubject")
	private String rebatesFeesubject; // 返款费用科目

	@Column(name = "is_force_fee_policy")
	private int isForceFeePolicy; // 是否强制执行收费政策

	@Column(name = "delete_flag")
	private int deleteFlag; // 删除标记

	@Column(name = "creator_id")
	private int creatorId; // 创建人

	@Column(name = "created_time")
	private Date createdTime; // 创建时间

	@Column(name = "updater_id")
	private int updaterId; // 最后修改人

	@Column(name = "updated_time")
	private Date updatedTime; // 最后修改时间

	@Transient
	transient private int target; // 指标

	@Transient
	transient private int expectTarget; // 期望指标
	@Transient
	transient private int complete; // 完成数

	//克隆
	public Academy clone()throws CloneNotSupportedException
	{
		return (Academy)super.clone();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFoundedYear() {
		return foundedYear;
	}

	public void setFoundedYear(String foundedYear) {
		this.foundedYear = foundedYear;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getUsingStatus() {
		return usingStatus;
	}

	public void setUsingStatus(int usingStatus) {
		this.usingStatus = usingStatus;
	}
	@JSON(format = "yyyy-MM-dd")
	public Date getContractEndtime() {
		return contractEndtime;
	}

	public void setContractEndtime(Date contractEndtime) {
		this.contractEndtime = contractEndtime;
	}

	public int getAuditer() {
		return auditer;
	}

	public void setAuditer(int auditer) {
		this.auditer = auditer;
	}

	public Date getAuditedDate() {
		return auditedDate;
	}

	public void setAuditedDate(Date auditedDate) {
		this.auditedDate = auditedDate;
	}

	public int getInterfaceSettingStatus() {
		return interfaceSettingStatus;
	}

	public void setInterfaceSettingStatus(int interfaceSettingStatus) {
		this.interfaceSettingStatus = interfaceSettingStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getIsForceFeePolicy() {
		return isForceFeePolicy;
	}

	public void setIsForceFeePolicy(int isForceFeePolicy) {
		this.isForceFeePolicy = isForceFeePolicy;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(int updaterId) {
		this.updaterId = updaterId;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRebatesFeesubject() {
		return rebatesFeesubject;
	}

	public void setRebatesFeesubject(String rebatesFeesubject) {
		this.rebatesFeesubject = rebatesFeesubject;
	}

	public String getProjectManagerName() {
		return projectManagerName;
	}

	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}

	public int getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(int projectManagerId) {
		this.projectManagerId = projectManagerId;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	public int getExpectTarget() {
		return expectTarget;
	}

	public void setExpectTarget(int expectTarget) {
		this.expectTarget = expectTarget;
	}


}
