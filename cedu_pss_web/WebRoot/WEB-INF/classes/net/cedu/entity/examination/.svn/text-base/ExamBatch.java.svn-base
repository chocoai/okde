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
 * ¿¼ÊÔÅú´Î
 * 
 */

@Entity
@Table(name= "tb_e_exam_batch", catalog = "cedu_master")
public class ExamBatch implements Serializable {
	/*
	 * ä¸»é”®ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  
	private Integer id;
	
	@Column(name="code")
	private String code;
	 @Column(name="name")
	 private String name;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
