package net.cedu.entity.basesetting;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;

/**
 * 基础设置_招生返款时间限定（返款期、到账确认时间）
 * @author xiao
 *
 */
@Entity
@Table(name = "tb_e_channel_rebate_time_limit")
public class ChannelRebateTimeLimit implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;//主键ID
	
	@Column(name = "start_time")
	private Date startTime;//返款期_开始时间
	
	@Column(name = "end_time")
	private Date endTime;//返款期_结束时间     
	
	@Column(name = "rebate_name")
	private String rebateName;//招生返款批次名称
	
	@Column(name = "cedu_confirm_time")
	private Date ceduConfirmTime;//     到账确认时间
	
	@Column(name = "xue_money")
	private double xueMoney;//     学费下限
	
	@Column(name = "is_using")
	private int isUsing; // 是否启用
	
	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间
	
	@Transient
	transient private int count; //未汇款的招生返款单数量
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getRebateName() {
		return rebateName;
	}

	public void setRebateName(String rebateName) {
		this.rebateName = rebateName;
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

	@JSON(format = "yyyy-MM-dd")
	public Date getCeduConfirmTime() {
		return ceduConfirmTime;
	}

	public void setCeduConfirmTime(Date ceduConfirmTime) {
		this.ceduConfirmTime = ceduConfirmTime;
	}

	public int getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getXueMoney() {
		return xueMoney;
	}

	public void setXueMoney(double xueMoney) {
		this.xueMoney = xueMoney;
	}


	
}
