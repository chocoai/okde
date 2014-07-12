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
 * Ѳ������
 */
@Entity
@Table(name = "tb_e_exam_schedule", catalog = "cedu_master")
public class ExamSchedule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1042169681376121586L;

	/*
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  
	private Integer id;
	
	@Column(name="exam_batch_id")
	private Integer examBatchId;
	
	@Column(name="exam_area_id")
	private Integer examAreaId;
	
	@Column(name="inspector")
	private Integer inspector;
	
	@Column(name="start_time")
	private Date startTime;
	
	@Column(name="end_time")
	private Date endTime;
	
	@Column(name="planed_cost")
	private double planedCost;
	
	@Column(name="actual_cost")
	private double actualCost;
	
	@Column(name="inspector_mark")
	private Integer inspectorMark;
	
	@Column(name="file_path")
	private String filePath;
	
	@Column(name="exam_report_path")
	private String examReportPath;
	
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
	@Column(name="creator_id")
	private Integer creatorId;
	
	@Column(name="created_time")
	private Date createdTime;
     
	@Column(name="updater_id")
	private Integer updaterId;
	
	@Transient
	transient private String examAreaname; //考场
	
	@Transient
	transient private String examBatchname;//考试批次
	
	@Transient
	transient private String plan;
	
	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getExamAreaname() {
		return examAreaname;
	}

	public void setExamAreaname(String examAreaname) {
		this.examAreaname = examAreaname;
	}

	public String getExamBatchname() {
		return examBatchname;
	}

	public void setExamBatchname(String examBatchname) {
		this.examBatchname = examBatchname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExamBatchId() {
		return examBatchId;
	}

	public void setExamBatchId(Integer examBatchId) {
		this.examBatchId = examBatchId;
	}

	public Integer getExamAreaId() {
		return examAreaId;
	}

	public void setExamAreaId(Integer examAreaId) {
		this.examAreaId = examAreaId;
	}

	public Integer getInspector() {
		return inspector;
	}

	public void setInspector(Integer inspector) {
		this.inspector = inspector;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getPlanedCost() {
		return planedCost;
	}

	public void setPlanedCost(double planedCost) {
		this.planedCost = planedCost;
	}

	public double getActualCost() {
		return actualCost;
	}

	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}

	public Integer getInspectorMark() {
		return inspectorMark;
	}

	public void setInspectorMark(Integer inspectorMark) {
		this.inspectorMark = inspectorMark;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExamReportPath() {
		return examReportPath;
	}

	public void setExamReportPath(String examReportPath) {
		this.examReportPath = examReportPath;
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

	@Column(name="updated_time")
	private Date updatedTime;

}
