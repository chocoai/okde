package net.cedu.entity.crm;

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
 * 学生信息导入
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "tb_e_student_import_record")
public class StudentImportRecord implements Serializable {

	@Transient
	transient public final static int IMPORT_TYPE_MANAGER = 1;// 总部
	@Transient
	transient public final static int IMPORT_TYPE_Branch = 2;// 学习中心
	
	@Transient
	transient public final static int IMPORT_TYPE_MANAGER_CC = 3;// 总部(呼叫中心)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;// 数据名称
	@Column(name = "student_sttaus")
	private int studentSttaus;// 导入的学生状态

	@Column(name = "planed_student_count")
	private int planedStudentCount;// 计划人数

	@Column(name = "complete_ratio")
	private double completeRatio;// 完成度

	@Column(name = "is_enable")
	private int isEnable;// 是否启用

	@Column(name = "first_call_time")
	private Date firstCallTime;// 首呼开始时间

	@Column(name = "assignment_type")
	private int assignmentType;// 分配类型
	@Column(name = "source_id")
	private int sourceId;// 数据来源ID

	@Column(name = "org_id")
	private int orgId;// 学习中心ID

	@Column(name = "people_type")
	private String peopleType;// 人群类型

	@Column(name = "enrollment_source_id")
	private int enrollmentSourceId;// 招生途径ID

	@Column(name = "enrollment_way_id")
	private int enrollmentWayId;// 市场途径ID

	@Column(name = "channel_id")
	private int channelId;// 渠道ID

	@Column(name = "uploaded_file")
	private String uploadedFile;// 上传文件

	@Column(name = "note")
	private String note;// 备注

	@Column(name = "error_count")
	private int errorCount;// 导入失败条数
	@Column(name = "success_count")
	private int successCount;// 成功条数
	@Column(name = "error_log")
	private String errorLog;// 导入失败条数的日志

	@Column(name = "fenpei_log")
	private String fenpeiLog;// 自动分配日志

	@Column(name = "creator_id")
	private int creatorId;// 创建人

	@Column(name = "created_date")
	private Date createdDate;// 创建时间

	@Column(name = "updated_id")
	private int updatedId;// 最后修改人

	@Column(name = "updated_time")
	private Date updatedTime;// 最后修改时间
	@Column(name = "status")
	private int status;
	@Column(name = "type")
	private int type;// 1,历史数据2,中心新增数据3,呼叫中心
	@Transient
	transient private String enrollmentWayName;// 市场途径名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPlanedStudentCount() {
		return planedStudentCount;
	}

	public void setPlanedStudentCount(int planedStudentCount) {
		this.planedStudentCount = planedStudentCount;
	}

	public double getCompleteRatio() {
		return completeRatio;
	}

	public void setCompleteRatio(double completeRatio) {
		this.completeRatio = completeRatio;
	}

	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	public Date getFirstCallTime() {
		return firstCallTime;
	}

	public void setFirstCallTime(Date firstCallTime) {
		this.firstCallTime = firstCallTime;
	}

	public int getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(int assignmentType) {
		this.assignmentType = assignmentType;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}

	public int getEnrollmentSourceId() {
		return enrollmentSourceId;
	}

	public void setEnrollmentSourceId(int enrollmentSourceId) {
		this.enrollmentSourceId = enrollmentSourceId;
	}

	public int getEnrollmentWayId() {
		return enrollmentWayId;
	}

	public void setEnrollmentWayId(int enrollmentWayId) {
		this.enrollmentWayId = enrollmentWayId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(String uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedId() {
		return updatedId;
	}

	public void setUpdatedId(int updatedId) {
		this.updatedId = updatedId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}

	public String getFenpeiLog() {
		return fenpeiLog;
	}

	public void setFenpeiLog(String fenpeiLog) {
		this.fenpeiLog = fenpeiLog;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getStudentSttaus() {
		return studentSttaus;
	}

	public void setStudentSttaus(int studentSttaus) {
		this.studentSttaus = studentSttaus;
	}

	public String getEnrollmentWayName() {
		return enrollmentWayName;
	}

	public void setEnrollmentWayName(String enrollmentWayName) {
		this.enrollmentWayName = enrollmentWayName;
	}

}
