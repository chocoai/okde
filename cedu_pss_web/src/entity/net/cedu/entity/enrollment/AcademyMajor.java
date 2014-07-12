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

/**
 * 专业（院校批次）
 * @author hxj
 *
 */
@Entity
@Table(name="tb_e_academy_major")
public class AcademyMajor implements Serializable{
	
	private static final long serialVersionUID = 1516064366154219203L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name = "code")
	private String code;//编码
	
	@Column(name="academy_level_id")
	private int academyLevelId;//层次(招生批次)ID
	
	@Column(name="major_id")
	private int majorId;//专业(院校)ID
	
	@Column(name = "delete_flag")
	private int deleteFlag;//删除标记    0-未删除,1-已删除
	
	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间
	
	@Transient
	transient private AcademyLevel academyLevel;//层次(招生批次)实体
	
	@Transient
	transient private Major major;//专业(院校)实体
	
	
	@Transient
	transient private String majorName;//专业名称
	
	@Transient
	transient private String majorCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.replaceAll("[\\s]", "");
	}

	public int getAcademyLevelId() {
		return academyLevelId;
	}

	public void setAcademyLevelId(int academyLevelId) {
		this.academyLevelId = academyLevelId;
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

	public AcademyLevel getAcademyLevel() {
		return academyLevel;
	}

	public void setAcademyLevel(AcademyLevel academyLevel) {
		this.academyLevel = academyLevel;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

}
