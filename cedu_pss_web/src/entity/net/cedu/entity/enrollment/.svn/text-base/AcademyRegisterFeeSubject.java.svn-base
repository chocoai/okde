package net.cedu.entity.enrollment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 院校-学籍批次-费用科目 关系表
 */
@Entity
@Table(name="tb_r_academy_register_feesubject")
public class AcademyRegisterFeeSubject implements Serializable
{
	private static final long serialVersionUID = 8445424983655164674L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Column(name="fee_subject")
	private int feeSubjectId; //费用科目
	
	@Column(name="register_batch")
	private int registerBatchId; //学籍批次
	
	@Column(name="creator_id")
	private int creatorId; //创建人
	
	@Column(name="created_time")
	private Date createdTime; //创建时间

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

	public int getRegisterBatchId() {
		return registerBatchId;
	}

	public void setRegisterBatchId(int registerBatchId) {
		this.registerBatchId = registerBatchId;
	}
}
