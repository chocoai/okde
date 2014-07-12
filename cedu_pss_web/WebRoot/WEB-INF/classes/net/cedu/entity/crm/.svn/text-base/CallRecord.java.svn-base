package net.cedu.entity.crm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 呼叫记录
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "tb_e_call_record")
public class CallRecord implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "student_id")
	private int studentId;// 学生ID

	@Column(name = "status_id")
	private int status_id;// 状态ID

	@Column(name = "indexs")
	private String index;// 索引

	@Column(name = "call_time")
	private Date callTime;// 呼叫时间

	@Column(name = "level")
	private int level;// 等级

	@Column(name = "content")
	private String content;// 跟进内容

	@Column(name = "delete_flag")
	private int deleteFlag;// 删除标记

	@Column(name = "creator_id")
	private int creatorId;// 创建人

	@Column(name = "created_time")
	private Date createdTime;// 创建时间

	@Column(name = "updater_id")
	private int updaterId;// 最后修改人

	@Column(name = "updated_time")
	private Date updatedTime;// 最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int statusId) {
		status_id = statusId;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

}
