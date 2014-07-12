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
 * ������Ϣ
 */

@Entity
@Table(name="tb_e_classroom",catalog="cedu_master")
public class ClassRoom implements Serializable {
	
	private static final long serialVersionUID = 2222097122506438859L;

	/*
	 * 
	 * ����ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="examroom_id")
	private Integer examroomId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="capacity")
	private Integer capacity;
	
	@Column(name="fee_type")
	private Integer feeType;
	
	@Column(name="fee_standard")
	private double feeStandard;
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
	@Column(name="creator_id")
	private Integer creatorId;
	
	@Column(name="created_time")
	private Date createTime;
	 
	@Column(name="updater_id")
	private Integer updaterId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getExamroomId() {
		return examroomId;
	}

	public void setExamroomId(Integer examroomId) {
		this.examroomId = examroomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public double getFeeStandard() {
		return feeStandard;
	}

	public void setFeeStandard(double feeStandard) {
		this.feeStandard = feeStandard;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	@Column(name="updated_time")
	private Date updatedTime;
	

}
