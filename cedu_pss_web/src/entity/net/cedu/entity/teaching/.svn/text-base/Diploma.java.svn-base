package net.cedu.entity.teaching;

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
 * 毕业证书
 * @author wangmingjie
 *
 */
@Entity
@Table(name = "tb_e_diploma")
public class Diploma implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  //主建ID
	private int id;
	
	@Column(name = "academy_id")
	private int academyId;  //院校ID
	
	@Column(name = "student_id")
	private int studentId;  //学生ID
	
	@Column(name = "student_status")
	private int studentStatus;  //学生状态
	
	@Column(name = "admission_time")
	private Date admissionTime;  //入学时间
	
	@Column(name = "graduation")
	private Date graduation;  //毕业时间
	
	@Column(name = "status")
	private int status;  //证书状态
	
	@Column(name = "delete_flag")
	private int deleteFlag;  //删除标记
	
	@Column(name = "creator_id")
	private int creatorId;  //创建人
	
	@Column(name = "created_time")
	private Date createdTime;  //创建时间
	
	@Column(name = "updater_id")
	private int updaterId;  //最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;  //最后修改时间
	
	@Column(name = "diploma_url")
	private String diplomaUrl; // 图片引用路径
	
	//下面这些暂时没有
	@Transient
	transient private String number;// 学号
	@Transient
	transient private String studentName;//学生姓名
	@Transient
	transient private int gender;//性别
	@Transient
	transient private String academyName;//院校名称
	@Transient
	transient private String batchName;//招生批次名称
	@Transient
	transient private String levelName;//层次名称
	@Transient
	transient private String majorName;//专业名称
	
	@Transient
	transient private Date  beginSchoolTime;//颁发的开始时间
	@Transient
	transient private Date  endSchoolTime;//颁发的结束时间
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(int studentStatus) {
		this.studentStatus = studentStatus;
	}

	public Date getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(Date admissionTime) {
		this.admissionTime = admissionTime;
	}

	public Date getGraduation() {
		return graduation;
	}

	public void setGraduation(Date graduation) {
		this.graduation = graduation;
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

	public String getDiplomaUrl() {
		return diplomaUrl;
	}

	public void setDiplomaUrl(String diplomaUrl) {
		this.diplomaUrl = diplomaUrl;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Date getBeginSchoolTime() {
		return beginSchoolTime;
	}

	public void setBeginSchoolTime(Date beginSchoolTime) {
		this.beginSchoolTime = beginSchoolTime;
	}

	public Date getEndSchoolTime() {
		return endSchoolTime;
	}

	public void setEndSchoolTime(Date endSchoolTime) {
		this.endSchoolTime = endSchoolTime;
	}


	
}
