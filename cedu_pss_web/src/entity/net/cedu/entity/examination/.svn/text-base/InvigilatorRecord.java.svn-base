package net.cedu.entity.examination;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_e_invigilator_record", catalog = "cedu_master")
public class InvigilatorRecord implements Serializable {

	/**
	 * 监考记录
	 */
	private static final long serialVersionUID = 7776690290198184615L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="invigilator_id")
	private Integer invigilatorId;
	
	@Column(name="academy_id")
	private Integer academyId;
	
	@Column(name="exam_area_id")
	private Integer examAreaId;
	
	@Column(name="content")
	private String content;
	
	@Column(name="fee")
	private int fee;
	
	@Column(name="feecount")
	private int feecount;
	
	@Column(name="status")
	private int status;
	
	@Column(name="check_time")
	private Date checkTime;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="creater_id")
	private Integer createrId;
	
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
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

	public Integer getInvigilatorId() {
		return invigilatorId;
	}

	public void setInvigilatorId(Integer invigilatorId) {
		this.invigilatorId = invigilatorId;
	}

	public Integer getAcademyId() {
		return academyId;
	}

	public void setAcademyId(Integer academyId) {
		this.academyId = academyId;
	}

	public Integer getExamAreaId() {
		return examAreaId;
	}

	public void setExamAreaId(Integer examAreaId) {
		this.examAreaId = examAreaId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getFeecount() {
		return feecount;
	}

	public void setFeecount(int feecount) {
		this.feecount = feecount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
