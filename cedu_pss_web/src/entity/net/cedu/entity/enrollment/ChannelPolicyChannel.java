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

import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;

/**
 * 合作方与招生返款政策关系
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_r_channel_policy_channel")
public class ChannelPolicyChannel implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "channel_id")
	private int channelId; //合作方Id
	@Column(name = "policy_detail_id")
	private int policyDetailId; //政策明细Id
	@Column(name = "status")
	private int status; // 审批状态
	@Column(name = "delete_flag")
	private int deleteFlag; // 删除标记
	@Column(name = "creater_id")
	private int createrId; // 创建人
	@Column(name = "created_time")
	private Date createdTime; // 创建时间
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
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public int getPolicyDetailId() {
		return policyDetailId;
	}
	public void setPolicyDetailId(int policyDetailId) {
		this.policyDetailId = policyDetailId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public int getCreatorId() {
		return createrId;
	}
	public void setCreatorId(int createrId) {
		this.createrId = createrId;
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
	
	
	
	
}
