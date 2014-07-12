package net.cedu.entity.enrollment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import net.cedu.entity.basesetting.Level;

/**
 * 院校返款政策明细
 */
@Entity
@Table(name="tb_e_academy_policy_rebates_detail")
public class AcademyRebatePolicyDetail implements Serializable
{
	private static final long serialVersionUID = -40776587921627194L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="code")
	private String code; //编码
	
	@Column(name="academy_id")
	private int academyId;
	
	@Transient
	private String academyName;
	
	@Column(name="batch_id")
	private int batchId; //批次(根据政策类型来确定是招生/学籍批次)

	@Column(name="branch_id")
	private int branchId; //学习中心
	
	@Column(name="level_id")
	private int levelId; //批次
	
	@Column(name="major_id")
	private int majorId; //专业
	
	@Column(name="policy_rebates_id")
	private int policyId; //政策标准ID
	
	@Column(name="fee_subject_id")
	private int feeSubjectId; //费用项目
	
	@Column(name="is_using")
	private int enable; //是否启用
	
	@Column(name="audit_status")
	private int auditStatus; //审核状态
	
	@Column(name="auditer")
	private int auditerId; //审核人
	
	@Column(name="audit_date")
	@Temporal(TemporalType.DATE)
	private Date auditDate; //审核日期
	
	@Column(name="delete_flag")
	private int deleteFlag; //删除标记
	
	@Column(name="creator_id")
	private int creatorId; //创建人
	
	@Column(name="created_time")
	private Date createdTime; //创建时间
	
	@Column(name="updater_id")
	private int updaterId; //最后修改人
	
	@Column(name="updated_time")
	private Date updatedTime; //最后修改时间
	
	@Transient
	private transient String branchName; //学习中名称
	@Transient
	private transient String feeSubjectName; //费用项目名称
	@Transient
	private transient String majorName; //专业名称
	@Transient
	private transient AcademyRebatePolicy policy;
	@Transient
	private transient Level level;
	@Transient
	private transient String batchName;
	@Transient
	private transient String levelName;
	@Transient
	private transient AcademyEnrollBatch batch;
	@Transient
	private transient List<AcademyRebatePolicyDetailStandard> standards;

	/********************** Getter/Setter **********************/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public AcademyRebatePolicy getPolicy() {
		return policy;
	}

	public void setPolicy(AcademyRebatePolicy policy) {
		this.policy = policy;
	}
	
	public int getEnable() {
		return enable;
	}

	public List<AcademyRebatePolicyDetailStandard> getStandards() {
		return standards;
	}

	public void setStandards(List<AcademyRebatePolicyDetailStandard> standards) {
		this.standards = standards;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getAuditerId() {
		return auditerId;
	}

	public void setAuditerId(int auditerId) {
		this.auditerId = auditerId;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
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

	public AcademyEnrollBatch getBatch() {
		return batch;
	}

	public void setBatch(AcademyEnrollBatch batch) {
		this.batch = batch;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFeeSubjectName() {
		return feeSubjectName;
	}

	public void setFeeSubjectName(String feeSubjectName) {
		this.feeSubjectName = feeSubjectName;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchName() {
		return branchName;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelName() {
		return levelName;
	}
}
