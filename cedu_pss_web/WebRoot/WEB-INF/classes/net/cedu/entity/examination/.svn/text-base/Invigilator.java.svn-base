package net.cedu.entity.examination;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbEInvigilator entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_e_invigilator", catalog = "cedu_master")
public class Invigilator implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6249611969957046646L;

	private Integer id;                     //主键ID
	
	private String code;					//编号
	
	private String name;					//姓名
	
	private Integer gender;					//性别
	
	private String photo;					//照片
	
	private String telephone;				//电话
	
	private String mobile;					//手机
	
	private String email;					//邮箱
	
	private Integer certType;				//证件类型
	
	private String certNo;					//证件号码
	
	private Integer degree;					//最高学历
	
	private String workUnit;				//工作单位
	
	private Integer feeType;				//收费方式
	
	private Double feeStandard;				//收费标准
	
	private String invigilationExperience;	//监考经历
	
	private String note;					//备注
	
	private Integer status;					//状态
	
	private Integer deleteFlag;				//删除标记
	
	private Integer creatorId;				//创建人
		
	private Date createdTime;				//创建时间
	
	private Integer updaterId;				//最后修改人
	
	private Date updatedTime;				//最后修改时间
	

	@Transient
	transient private String creatorname;
	
	
	

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 16)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "gender")
	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Column(name = "photo", length = 128)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "telephone", length = 32)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "mobile", length = 16)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "cert_type")
	public Integer getCertType() {
		return this.certType;
	}

	public void setCertType(Integer certType) {
		this.certType = certType;
	}

	@Column(name = "cert_no", length = 32)
	public String getCertNo() {
		return this.certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	@Column(name = "degree")
	public Integer getDegree() {
		return this.degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	@Column(name = "work_unit", length = 128)
	public String getWorkUnit() {
		return this.workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	@Column(name = "fee_type")
	public Integer getFeeType() {
		return this.feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	@Column(name = "fee_standard", precision = 10)
	public Double getFeeStandard() {
		return this.feeStandard;
	}

	public void setFeeStandard(Double feeStandard) {
		this.feeStandard = feeStandard;
	}

	@Column(name = "invigilation_experience", length = 65535)
	public String getInvigilationExperience() {
		return this.invigilationExperience;
	}

	public void setInvigilationExperience(String invigilationExperience) {
		this.invigilationExperience = invigilationExperience;
	}

	@Column(name = "note", length = 65535)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "creator_id")
	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "created_time", length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "updater_id")
	public Integer getUpdaterId() {
		return this.updaterId;
	}

	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}

	@Column(name = "updated_time", length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}