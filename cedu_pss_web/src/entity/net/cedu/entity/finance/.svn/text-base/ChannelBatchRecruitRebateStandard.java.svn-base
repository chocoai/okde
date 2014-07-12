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
 * 合作方返款时绑定每个批次第一次招生返款的政策标准
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_r_channel_batch_recruit_rebate_standard")
public class ChannelBatchRecruitRebateStandard implements Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "channel_id")
	private int channelId; // 合作方Id
	
	@Column(name = "academy_id")
	private int academyId; // 院校Id
	
	@Column(name = "global_batch_id",columnDefinition="int default '0'")
	private int globalBatchId; // 全局批次Id
	
	@Column(name = "batch_id")
	private int batchId; // 招生批次Id
	
	@Column(name = "channel_time_limit_id")
	private int channelTimeLimitId; // 招生返款期Id
	
	@Column(name = "channel_police_standard_id")
	private int channelPoliceStandardId; // 招生返款标准Id
	
	@Column(name = "rebate_count")
	private int rebateCount; // 招生返款总人数

	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getChannelTimeLimitId() {
		return channelTimeLimitId;
	}

	public void setChannelTimeLimitId(int channelTimeLimitId) {
		this.channelTimeLimitId = channelTimeLimitId;
	}

	public int getChannelPoliceStandardId() {
		return channelPoliceStandardId;
	}

	public void setChannelPoliceStandardId(int channelPoliceStandardId) {
		this.channelPoliceStandardId = channelPoliceStandardId;
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

	public int getRebateCount() {
		return rebateCount;
	}

	public void setRebateCount(int rebateCount) {
		this.rebateCount = rebateCount;
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
	
	
	
}
