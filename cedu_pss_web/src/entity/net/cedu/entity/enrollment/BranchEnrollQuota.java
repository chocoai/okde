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
import javax.persistence.Transient;

import net.cedu.entity.academy.Academy;

/**
 * 学习中心指标
 * @author gaolei
 */
@Entity
@Table(name="tb_e_branch_enroll_quota")
public class BranchEnrollQuota implements Serializable
{
	private static final long serialVersionUID = -5308599405933447949L;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; //主键ID
	
	@Column(name="batch_id")
	private int batchId; // 批次Id
	
	@Column(name="branch_id")
	private int branchId; // 学习中心Id
	
	@Column(name="target")
	private int target; // 指标
	
	
	
	
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
	transient private String batchName;     //全局批次名称
	
	@Transient
	transient private String branchName;     //学习中心名称
	
	@Transient
	transient private String quotaYear;     //年度
	
	@Transient
	transient private String enrollQuota;     //年度总指标

	@Transient
	transient private List<Academy> academylst;     //院校集合
	
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

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
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

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getQuotaYear() {
		return quotaYear;
	}

	public void setQuotaYear(String quotaYear) {
		this.quotaYear = quotaYear;
	}

	public String getEnrollQuota() {
		return enrollQuota;
	}

	public void setEnrollQuota(String enrollQuota) {
		this.enrollQuota = enrollQuota;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public List<Academy> getAcademylst() {
		return academylst;
	}

	public void setAcademylst(List<Academy> academylst) {
		this.academylst = academylst;
	}
	
	
}
