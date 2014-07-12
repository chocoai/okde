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
 * Ñ²¿¼¹µÍ¨¼ÇÂ¼
 */
@Entity
@Table(name = "tb_e_exam_communication_record" ,catalog="cedu_master")
public class ExamCommunicationRecord implements Serializable {
	
	/*
	 * Ö÷¼üID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="schedule_id")
	private Integer scheduleId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String  content;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="creater_id")
	private Integer createrId;
	
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
	@Column(name="updater_id")
	private Integer  updaterId;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
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
