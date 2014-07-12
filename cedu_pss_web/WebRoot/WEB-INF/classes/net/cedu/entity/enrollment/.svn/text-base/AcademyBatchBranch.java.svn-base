package net.cedu.entity.enrollment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 院校授权的学习中心(关系表)
 */
@Entity
@Table(name="tb_r_academy_batch_branch")
public class AcademyBatchBranch implements Serializable
{
	private static final long serialVersionUID = -5308599405933447949L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="batch_id")
	private int batchId; //批次
	
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Column(name="global_id")
	private int globalBatchId; //全局招生批次ID
	
	@Column(name="branch_id")
	private int branchId; //学习中心ID
	
//	@Transient
//	private AcademyEnrollBatch enrollBatch;
//	@Transient
//	private Academy academy;
//	@Transient
//	private Branch branch;
	
	@Transient
	private transient String globalBatchName;

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

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setGlobalBatchId(int globalBatchId) {
		this.globalBatchId = globalBatchId;
	}

	public int getGlobalBatchId() {
		return globalBatchId;
	}

	public void setGlobalBatchName(String globalBatchName) {
		this.globalBatchName = globalBatchName;
	}

	public String getGlobalBatchName() {
		return globalBatchName;
	}
}
