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
 * 院校-招生批次-费用科目 关系表
 */
@Entity
@Table(name="tb_r_academy_batch_feesubject")
public class AcademyBatchFeeSubject implements Serializable
{
	private static final long serialVersionUID = 7605645508854076344L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Column(name="fee_subject")
	private int feeSubjectId; //费用科目
	
	@Column(name="batch_id")
	private int batchId; //招生批次
	
	@Column(name="stage")
	private int stage; //阶段（招生／非招生）
	
	@Column(name="creator_id")
	private int creatorId; //创建人
	
	@Column(name="created_time")
	private Date createdTime; //创建时间
	
	@Transient
	private transient String feeSubjectName; //费用项目名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public String getFeeSubjectName() {
		return feeSubjectName;
	}

	public void setFeeSubjectName(String feeSubjectName) {
		this.feeSubjectName = feeSubjectName;
	}
}
