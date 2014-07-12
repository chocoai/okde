package net.cedu.entity.examination;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * 
 * 考试计划
 */

@Entity
@Table(name="tb_e_exam_plan",catalog = "cedu_master")
public class ExamPlan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6208846230985429809L;


	/*
	 * 主键Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Integer id;
	
	
	@Column(name="plan")
	private String plan;
	
	@Column(name="exam_batch_id")
	private Integer examBatchId;
	
	@Column(name="academy_id")
	private String academyId;
	
	@Column(name="exam_date")
	private Date examDate;
	
	@Column(name="exam_begin_time")
	private String examBeginTime;
	
	@Column(name="exam_end_time")
	private String examEndTime;
	
	@Column(name="exam_subject")
	private String examSubject;
	
	@Column(name="plan_count")
	private Integer planCount;
	
	
	
	@Column(name="branch_id")
	private Integer branchId;
	
	@Column(name="batch_id")
	private Integer batchId;
	
	@Column(name="level_id")
	private Integer levelId;
	
	@Column(name="major_id")
	private Integer majorId;
	
	@Column(name="invigilator_ids")
	private String invigilatorIds;
	
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
	
	@Column(name="creator_id")
	private  Integer creatorId;
	
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name="updater_id")
	private Integer updaterId;
	
	@Column(name="updated_time")
	private Date updatedTime;
	
	@Transient
	transient private String levelname; //层次名称
	
	@Transient
	transient private String majorname; //专业
	
	@Transient
	transient private String branchname; //学习中心
	
	@Transient
	transient private String batchname;//批次

	public String getBatchname() {
		return batchname;
	}

	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getMajorname() {
		return majorname;
	}

	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public Integer getExamBatchId() {
		return examBatchId;
	}

	public void setExamBatchId(Integer examBatchId) {
		this.examBatchId = examBatchId;
	}

	

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamBeginTime() {
		return examBeginTime;
	}

	public void setExamBeginTime(String examBeginTime) {
		this.examBeginTime = examBeginTime;
	}

	public String getExamEndTime() {
		return examEndTime;
	}

	public void setExamEndTime(String examEndTime) {
		this.examEndTime = examEndTime;
	}

	public String getExamSubject() {
		return examSubject;
	}

	public void setExamSubject(String examSubject) {
		this.examSubject = examSubject;
	}

	public Integer getPlanCount() {
		return planCount;
	}

	public void setPlanCount(Integer planCount) {
		this.planCount = planCount;
	}

	
	public String getAcademyId() {
		return academyId;
	}

	public void setAcademyId(String academyId) {
		this.academyId = academyId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public String getInvigilatorIds() {
		return invigilatorIds;
	}

	public void setInvigilatorIds(String invigilatorIds) {
		this.invigilatorIds = invigilatorIds;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	

}
