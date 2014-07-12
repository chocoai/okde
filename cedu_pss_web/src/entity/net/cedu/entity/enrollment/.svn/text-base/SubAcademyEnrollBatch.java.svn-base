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
 * 院校招生子批次
 * @author HXJ
 *
 */
@Entity
@Table(name="tb_e_sub_academy_enroll_batch")
public class SubAcademyEnrollBatch implements Serializable{

	private static final long serialVersionUID = -925299008036570594L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name = "sub_enrollment_name")
	private String subEnrollmentName;//院校招生子批次名称
	
	@Column(name = "academy_enroll_batch_id")
	private int academyEnrollBatchId;//院校招生批次ID
	
	@Column(name = "is_current")
	private int isCurrent;//是否当前子批次
	
	@Column(name = "begin_date")
	private Date beginDate;//开始日期
	
	@Column(name = "end_date")
	private Date endDate;//结束日期
	
	@Column(name = "delete_flag")
	private int deleteFlag;//删除标记    0-未删除,1-已删除
	
	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间
	
	@Transient   
	transient private AcademyEnrollBatch academyEnrollBatch;//院校招生批次

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSubEnrollmentName() {
		return subEnrollmentName;
	}

	public void setSubEnrollmentName(String subEnrollmentName) {
		this.subEnrollmentName = subEnrollmentName.replaceAll("[\\s]", "");
	}

	public int getAcademyEnrollBatchId() {
		return academyEnrollBatchId;
	}

	public void setAcademyEnrollBatchId(int academyEnrollBatchId) {
		this.academyEnrollBatchId = academyEnrollBatchId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public AcademyEnrollBatch getAcademyEnrollBatch() {
		return academyEnrollBatch;
	}

	public void setAcademyEnrollBatch(AcademyEnrollBatch academyEnrollBatch) {
		this.academyEnrollBatch = academyEnrollBatch;
	}

	public int getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(int isCurrent) {
		this.isCurrent = isCurrent;
	}
	
	
}
