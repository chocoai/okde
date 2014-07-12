package net.cedu.entity.basesetting;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 基础设置--全局招生批次
 * @author HXJ
 *
 */
@Entity
@Table(name = "tb_e_global_enroll_batch")
public class GlobalEnrollBatch implements Serializable{

	private static final long serialVersionUID = 1224680900386045132L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;//主键ID
	
	@Column(name = "enroll_batch")
	private String batch;//全局招生批次号
	
	@Column(name = "title")
	private String title;//标题
	
	//@Column(name="using_status")
	//private int usingStatus;//启用状况  0-未启用,1-已启用
	
	@Column(name="belong_year")
	private int belongYear;//所属年份
	
	@Column(name = "delete_flag")
	private int deleteFlag;//删除标记  0-未删除,1-已删除
	
	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch.replaceAll("[\\s]", "");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.replaceAll("[\\s]", "");
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

//	public int getUsingStatus() {
//		return usingStatus;
//	}
//
//	public void setUsingStatus(int usingStatus) {
//		this.usingStatus = usingStatus;
//	}

	public int getBelongYear() {
		return belongYear;
	}

	public void setBelongYear(int belongYear) {
		this.belongYear = belongYear;
	}

}
