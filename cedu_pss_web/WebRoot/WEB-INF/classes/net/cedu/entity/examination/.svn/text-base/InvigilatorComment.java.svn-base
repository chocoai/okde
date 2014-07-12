package net.cedu.entity.examination;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * 
 * �࿼��ʦ����
 */

@Entity
@Table(name="tb_e_invigilator_comment",catalog="cedu_master")
public class InvigilatorComment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7683274259569423674L;

	/*
	 * ����ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="schedule_id")
	private Integer scheduleId;
	
	@Column(name="invigilator_id")
	private Integer invigilatorId;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="score")
	private Integer score;
	
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
	@Column(name="creator_id")
	private Integer creatorId;
	
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name="updater_id")
	private Integer updaterId;
	
	@Column(name="updated_time")
	private Date updatedTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getInvigilatorId() {
		return invigilatorId;
	}

	public void setInvigilatorId(Integer invigilatorId) {
		this.invigilatorId = invigilatorId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	

}