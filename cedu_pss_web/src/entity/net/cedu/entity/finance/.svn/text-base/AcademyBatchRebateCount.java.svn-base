package net.cedu.entity.finance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 院校返款时记录每次返款时每个批次的返款总人数
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_r_academy_batch_rebate_count")
public class AcademyBatchRebateCount implements Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "academy_id")
	private int academyId; // 院校Id
	
	@Column(name = "global_batch_id")
	private int globalBatchId; // 全局批次Id
	
	@Column(name = "batch_id")
	private int batchId; // 招生批次Id
	
	@Column(name = "fee_subject_id")
	private int feeSubjectId; // 费用科目Id
	
	@Column(name = "rebate_count")
	private int rebateCount; // 招生返款总人数
	
	@Column(name = "pay_academy_cedu_id")
	private int payAcademyCeduId; // 本次院校返款单Id    删除这个院校返款时级联删除这个不然追加金额时人数可能有问题
	
	@Column(name = "is_year_count")
	private int isYearCount; // 是否已经年度结算          年度院校返款后，招生人数将不再追加    0否  1是

	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间

	@Column(name = "updater_id")
	private int updaterId; // 最后修改人

	@Column(name = "updated_time")
	private Date updatedTime; // 最后修改时间

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

	public int getGlobalBatchId() {
		return globalBatchId;
	}

	public void setGlobalBatchId(int globalBatchId) {
		this.globalBatchId = globalBatchId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getRebateCount() {
		return rebateCount;
	}

	public void setRebateCount(int rebateCount) {
		this.rebateCount = rebateCount;
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

	public int getIsYearCount() {
		return isYearCount;
	}

	public void setIsYearCount(int isYearCount) {
		this.isYearCount = isYearCount;
	}

	public int getPayAcademyCeduId() {
		return payAcademyCeduId;
	}

	public void setPayAcademyCeduId(int payAcademyCeduId) {
		this.payAcademyCeduId = payAcademyCeduId;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
	
	

}
