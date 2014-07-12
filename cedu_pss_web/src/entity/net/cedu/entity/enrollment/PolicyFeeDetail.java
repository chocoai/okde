package net.cedu.entity.enrollment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 院校收费政策
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_e_policy_fee_detail")
public class PolicyFeeDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "code")
	private String code;//编码
	@Column(name = "academy_id")
	private int academyId; // 院校Id
	@Column(name = "policy_fee_id")
	private int policyFeeId; // 收费政策标准Id
	@Column(name = "batch_id")
	private int batchId; // 招生批次Id
	@Column(name = "branch_id")
	private int branchId; // 学习中心Id
	@Column(name = "level_id")
	private int levelId; // 层次Id
	@Column(name = "major_id")
	private int majorId; // 专业Id
	@Column(name = "fee_subject_id")
	private int feeSubjectId; // 费用项目ID
	
	@Column(name = "audit_status")
	private int aduitStatus; // 审核状态
	@Column(name = "auditer")
	private int auditer; // 审核人
	@Column(name = "audit_date")
	private Date auditDate; // 审核日期
	@Column(name = "is_using")
	private int isUsing; // 是否启用
	
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
	transient private String academyname;//院校
	@Transient
	transient private String batchname;//学籍批次实体
	@Transient
	transient private String levelname;//层次实体
	@Transient
	transient private String majorname;//专业
	@Transient
	transient private String feeSubjectname;//费用项目实体
	@Transient
	transient private String branchname;//学习中心
	@Transient
	transient private String feestandardes;//收费标准
	
	
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
	public int getAcademyId() {
		return academyId;
	}
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	public int getPolicyFeeId() {
		return policyFeeId;
	}
	public void setPolicyFeeId(int policyFeeId) {
		this.policyFeeId = policyFeeId;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	public int getAduitStatus() {
		return aduitStatus;
	}
	public void setAduitStatus(int aduitStatus) {
		this.aduitStatus = aduitStatus;
	}
	public int getAuditer() {
		return auditer;
	}
	public void setAuditer(int auditer) {
		this.auditer = auditer;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public int getIsUsing() {
		return isUsing;
	}
	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
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
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public String getAcademyname() {
		return academyname;
	}
	public void setAcademyname(String academyname) {
		this.academyname = academyname;
	}
	public String getBatchname() {
		return batchname;
	}
	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}
	public String getLevelname() {
		return levelname;
	}
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
	public String getMajorname() {
		return majorname;
	}
	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}
	public String getFeeSubjectname() {
		return feeSubjectname;
	}
	public void setFeeSubjectname(String feeSubjectname) {
		this.feeSubjectname = feeSubjectname;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getFeestandardes() {
		return feestandardes;
	}
	public void setFeestandardes(String feestandardes) {
		this.feestandardes = feestandardes;
	}
	public int getFeeSubjectId() {
		return feeSubjectId;
	}
	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
	
}
