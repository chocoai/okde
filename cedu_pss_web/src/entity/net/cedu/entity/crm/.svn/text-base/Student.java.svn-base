package net.cedu.entity.crm;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.file.excel.ExcelAnnotation;
import net.cedu.common.hibernate.SortChineseAnnotation;
import net.cedu.common.string.StringUtil;

import org.apache.struts2.json.annotations.JSON;

/**
 * 学生基础信息
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "tb_e_student")
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	@ExcelAnnotation(exportName = "姓名")
	@SortChineseAnnotation(sort = true)
	private String name;// 姓名

	@Column(name = "name_locking_status")
	private int nameLockingStatus;// 姓名锁定状态

	@Column(name = "stu_number")
	@ExcelAnnotation(exportName = "学号")
	private String number;// 学号

	@Column(name = "gender")
	@ExcelAnnotation(exportName = "性别")
	private int gender = 2;// 性别
	
	@Column(name = "living_place")
	@ExcelAnnotation(exportName = "所在城市")
	private String livingPlace;// 现居地
	
	@Column(name = "mobile")
	@ExcelAnnotation(exportName = "手机号码")
	private String mobile;// 手机号码
	
	@Column(name = "phone")
	@ExcelAnnotation(exportName = "座机号码")
	private String phone;// 座机

	@Column(name = "cert_type")
	@ExcelAnnotation(exportName = "证件类型")
	private int certType;// 证件类型

	@Column(name = "cert_no")
	@ExcelAnnotation(exportName = "证件号码")
	private String certNo;// 证件号码
	
	@Column(name = "cert_no_locking_status")
	private int certNoLockingStatus;// 证件号码锁定状态

	@Column(name = "degree")
	private int degree;// 学历
	
	@Column(name = "service_time")
	private String serviceTime;// 服务时间

	@Column(name = "status")
	@ExcelAnnotation(exportName = "学生状态")
	private int status;// 状态

	@Column(name = "call_status_id")
	private int callStatusId;// 呼叫等级ID

	@Column(name = "status_update_date")
	private Date statusUpdateDate;// 状态修改时间

	@Column(name = "prefer_academy")
	private String preferAcademy;// 意向院校

	@Column(name = "prefer_level")
	private int preferLevel;// 意向层次

	@Column(name = "prefer_major")
	private String preferMajor;// 意向专业

	@Column(name = "email")
	@ExcelAnnotation(exportName = "邮箱")
	private String email;// 邮箱

	@Column(name = "zipcode")
	@ExcelAnnotation(exportName = "邮政邮编")
	private String zipcode;// 邮编

	@Column(name = "address")
	@ExcelAnnotation(exportName = "地址")
	private String address;// 联系地址

	@Column(name = "enrollment_way")
	private int enrollmentWay;// 市场途径

	@Column(name = "enrollment_source")
	private int enrollmentSource;// 招生途径

	@Column(name = "student_data_source")
	private int studentDataSource;// 数据来源

	@Column(name = "channel_id")
	private int channelId;// 合作方

	@Column(name = "remark")
	private String remark;// 备注

	@Column(name = "work_unit_info")
	private String workUnitInfo;// 单位信息

	@Column(name = "qq")
	@ExcelAnnotation(exportName = "QQ")
	private String qq;// QQ

	@Column(name = "msn")
	@ExcelAnnotation(exportName = "MSN")
	private String msn;// MSN

	@Column(name = "branch_id")
	private int branchId;// 学习中心ID

	@Column(name = "academy_id")
	private int academyId;// 院校ID

	@Column(name = "register_id")
	private int batchId;// 学籍批次ID

	@Column(name = "enrollment_batch_id")
	private int enrollmentBatchId;// 招生批次ID

	@Column(name = "level_id")
	private int levelId;// 层次ID

	@Column(name = "major_id")
	private int majorId;// 专业ID

	@Column(name = "return_visit_time")
	private Date returnVisitTime;// 提示回访跟进

	@Column(name = "creator_id")
	private int creatorId;// 创建人

	@Column(name = "created_time")
	private Date createDate;// 创建时间

	@Column(name = "push_id")
	private int pushId;// 最后修改人

	@Column(name = "push_time")
	@ExcelAnnotation(exportName = "推送日期")
	private Date pushDate;// 推送日期

	@Column(name = "registration_time")
	private Date registrationTime;// 报名时间

	@Column(name = "updater_id")
	private int updaterId;// 最后修改人
	
	@Column(name = "updated_time")
	private Date modifiedTime;// 修改时间

	@Column(name = "follow_up_id")
	private int followUpId;// 最新跟进者ID

	@Column(name = "up_follow_up_id")
	private int upFollowUpId;// 上一跟进者ID

	@Column(name = "latest_follow_up_date")
	private Date latestFollowUpDate;// 最新跟进日期

	@Column(name = "follow_count")
	private int followCount;// 跟进次数

	@Column(name = "call_status")
	private int callStatus;// 是否呼叫过

	@Column(name = "user_id")
	private int userId;// 用户ID

	@Column(name = "has_account")
	private int hasAccount = 0; // 系统金额账户,0：未创建,1：已创建

	// 新加字段
	@Column(name = "is_exemption")
	@ExcelAnnotation(exportName = "是否免试生")
	private int isExemption; // 是否免试生

	@Column(name = "student_import_record_id")
	private int studentImportRecordId; // 导入纪录ID

	@Column(name = "last_monitor_result")
	private int lastMonitorResult; // 最新监控结果

	@Column(name = "is_force_fee")
	private int isForceFee; // 是否强制收费 0：强制 （数据库里默认） 1：不强制

	@Column(name = "not_apply_fee")
	private int notApplyFee; // 无报名费 0：缴纳报名费 1：不缴纳报名费

	@Column(name = "monitor_status")
	private int monitorStatus; // 学生监控状态

	@Column(name = "tuition_status")
	private int tuitionStatus; // 学生缴学费状态

	@Column(name = "is_start_course")
	private int isStartCourse; // 是否开课 0：未开课（数据库里默认） 1：已开课

	@Column(name = "admit_time")
	private Date admitTime;// 录取时间

	@Column(name = "newstu_id")
	private int newstuId;// 新生id

	@Column(name = "is_channel_type_checked")
	private int isChannelTypeChecked;// 招生途径复核0：未复核（数据库里默认） 1：已复核
	
	@Column(name = "graduate_time")
	private Date graduateTime;//颁发时间
	
	@Column(name = "is_channel_rebate")
	private int isChannelRebate; // 是否已经返款 0：未返款（数据库里默认） 1：已返款      暂时只控制老带新
	
	@Column(name = "channel_policy_standard_id")
	private int channelPolicyStandardId;   //合作方招生返款政策标准ID
	
	@Column(name = "channel_policy_standard_lock",columnDefinition="int default '0'")
	private int channelPolicyStandardLock;   //合作方招生返款政策标准是否锁定    0未锁定    1已锁定     
											//   只有当锁定后才不能被修改（每次返款时，最终汇款时，得去改变这个值）
	
	@Column(name = "global_batch")
	private int globalBatch;// 全局批次ID
	
	transient private String enrollmentSourceName1;// 招生途径
	
	@Transient
	@ExcelAnnotation(exportName = "招生批次")
	transient private String enrollmentBatchName;// 招生批次名称
	
	@Transient
	@ExcelAnnotation(exportName = "学籍批次")
	transient private String batchName;// 学籍批次名称
	
	@Transient
	transient private int diplomaStatus;//证书状态
	
	@Transient
	transient private String prePurchaseStatus;  //中心申购状态
	
	@Transient
	transient private String lastMonitorResultName; // 最新监控结果名称
	
	@Transient
	transient private String enrollmentSourceName; // 数据来源名称

	@Transient
	transient private String followUpName; // 当前跟进者
	
	@Transient
	transient private String upFollowUpName; // 上一跟进者

	@Transient
	@ExcelAnnotation(exportName = "报读院校")
	transient private String schoolName; // 院校名称

	@Transient
	transient private String academyenrollbatchName; // 院校批次

	@Transient
	@ExcelAnnotation(exportName = "层次")
	transient private String levelName; // 层次

	@Transient
	@ExcelAnnotation(exportName = "专业")
	transient private String majorName; // 专业
	
	@Transient
	transient private String statusIds;// 学生状态Ids

	@Transient
	transient private int startStatusId;// 学生起始状态ID
	
	@Transient
	transient private int endStatusId;// 学生结束状态ID

	@Transient
	transient private int pendingTestCounts;// 学生测试费代缴费单数量

	@Transient
	@ExcelAnnotation(exportName = "学习中心")
	transient private String branchName;// 学习中心名称

	@Transient
	@ExcelAnnotation(exportName = "民族")
	transient private int ethnicGroup;// 民族

	@Transient
	@ExcelAnnotation(exportName = "出生地")
	transient private String homeplace;// 出生地

	@Transient
	@ExcelAnnotation(exportName = "政治面貌")
	transient private int politicalStatus;// 政治面貌

	@Transient
	@ExcelAnnotation(exportName = "入学时间")
	transient private Date admissionTime;// 入学时间

	@Transient
	@ExcelAnnotation(exportName = "入学方式")
	transient private int admissionWay;// 入学方式

	@Transient
	@ExcelAnnotation(exportName = "失败原因")
	transient private String errorMessage;// 导入失败原因

	@Transient
	@ExcelAnnotation(exportName = "入学前最高学历")
	transient private String highestEducation;// 入学前最高学历
	
	// Excel序号
	transient private String serialNumber;// 序号

	@Transient
	transient private String enrollmentWayName;// 市场途径名称

	@Transient
	transient private String batchIds;// 批次Ids(多)

	@Transient
	transient private int glbtach;// 全局批次
	
	@Transient
	transient private int gllevel;// 全局层次
	
	@Transient
	transient private int glmajor;// 全局专业

	@Transient
	transient private String monitorStatusIds;// 监控ID集合

	@Transient
	transient private String channelName;// 合作方名称

	@Transient
	transient private String globalBatchName;// 全局批次名称

	@Transient
	transient private String academyIds;// 院校ID集合

	@Transient
	transient private Date startDate;// 开始时间
	
	@Transient
	transient private Date endDate;// 结束时间
	
	@Transient
	transient private Date pushStartDate;// 推送开始时间
	
	@Transient
	transient private Date pushEndDate;// 推送结束时间
	
	@Transient
	@ExcelAnnotation(exportName = "推送人")
	transient private String pushName;// 推送人

	@Transient
	transient private String studentDataSourceName;// 学生来源名称
	
	@Transient
	transient private String studentDataSourceIds;// 学生来源id集合
	
	@Transient
	transient private Date createStartDate;// 创建开始时间
	
	@Transient
	transient private Date createEndDate;// 创建结束时间
	
	@Transient
	transient private int isEmphasisVerification;// 是否重点核查
	
	@Transient
	transient private String noLastMonitorResults;// 不包括的监控结果
	
	@Transient
	transient private int rebateFpdCount;// 学生招生返款单数量
	
	@Transient
	transient private Date followUpTimeBegin;// 跟进时间（始）（全部跟进记录）
	
	@Transient
	transient private Date followUpTimeEnd;// 跟进时间（终）（全部跟进记录）
	
	@Transient
	transient private Date returnVisitTimeBegin;// 提示回访跟进（始）
	
	@Transient
	transient private Date returnVisitTimeEnd;// 提示回访跟进（终）
	
	@Transient
	transient private int returnVisitTimeType;// 提示回访跟进类型
	
	@Transient
	transient private Map<String,String> paramsString=new HashMap<String, String>();
	
	@Transient
	transient private Map<String,Integer> paramsInt=new HashMap<String, Integer>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return StringUtil.toTrim(name);
	}

	public void setName(String name) {
		this.name = StringUtil.toTrim(name);
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getLivingPlace() {
		return StringUtil.toTrim(livingPlace);
	}

	public void setLivingPlace(String livingPlace) {
		this.livingPlace = StringUtil.toTrim(livingPlace);
	}

	public String getMobile() {
		return StringUtil.toTrim(mobile);
	}

	public void setMobile(String mobile) {
		this.mobile = StringUtil.toTrim(mobile);
	}

	public String getPhone() {
		return StringUtil.toTrim(phone);
	}

	public void setPhone(String phone) {
		this.phone = StringUtil.toTrim(phone);
	}

	public int getCertType() {
		return certType;
	}

	public void setCertType(int certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return StringUtil.toTrim(certNo);
	}

	public void setCertNo(String certNo) {
		this.certNo = StringUtil.toTrim(certNo);
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}

	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public String getPreferAcademy() {
		return StringUtil.toTrim(preferAcademy);
	}

	public void setPreferAcademy(String preferAcademy) {
		this.preferAcademy = StringUtil.toTrim(preferAcademy);
	}

	public int getPreferLevel() {
		return preferLevel;
	}

	public void setPreferLevel(int preferLevel) {
		this.preferLevel = preferLevel;
	}

	public String getPreferMajor() {
		return StringUtil.toTrim(preferMajor);
	}

	public void setPreferMajor(String preferMajor) {
		this.preferMajor = StringUtil.toTrim(preferMajor);
	}

	public String getEmail() {
		return StringUtil.toTrim(email);
	}

	public void setEmail(String email) {
		this.email = StringUtil.toTrim(email);
	}

	public String getZipcode() {
		return StringUtil.toTrim(zipcode);
	}

	public void setZipcode(String zipcode) {
		this.zipcode = StringUtil.toTrim(zipcode);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getEnrollmentWay() {
		return enrollmentWay;
	}

	public void setEnrollmentWay(int enrollmentWay) {
		this.enrollmentWay = enrollmentWay;
	}

	public int getEnrollmentSource() {
		return enrollmentSource;
	}

	public void setEnrollmentSource(int enrollmentSource) {
		this.enrollmentSource = enrollmentSource;
	}

	public int getStudentDataSource() {
		return studentDataSource;
	}

	public void setStudentDataSource(int studentDataSource) {
		this.studentDataSource = studentDataSource;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQq() {
		return StringUtil.toTrim(qq);
	}

	public void setQq(String qq) {
		this.qq = StringUtil.toTrim(qq);
	}

	public String getMsn() {
		return StringUtil.toTrim(msn);
	}

	public void setMsn(String msn) {
		this.msn = StringUtil.toTrim(msn);
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getEnrollmentBatchId() {
		return enrollmentBatchId;
	}

	public void setEnrollmentBatchId(int enrollmentBatchId) {
		this.enrollmentBatchId = enrollmentBatchId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public Date getReturnVisitTime() {
		return returnVisitTime;
	}

	public void setReturnVisitTime(Date returnVisitTime) {
		this.returnVisitTime = returnVisitTime;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	@JSON(format = "yyyy-MM-dd HH:mm")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(int updaterId) {
		this.updaterId = updaterId;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getNumber() {
		return StringUtil.toTrim(number);
	}

	public void setNumber(String number) {
		this.number = StringUtil.toTrim(number);
	}

	public String getAcademyenrollbatchName() {
		return academyenrollbatchName;
	}

	public void setAcademyenrollbatchName(String academyenrollbatchName) {
		this.academyenrollbatchName = academyenrollbatchName;
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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	public int getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(int followUpId) {
		this.followUpId = followUpId;
	}

	public String getFollowUpName() {
		return followUpName;
	}

	public void setFollowUpName(String followUpName) {
		this.followUpName = followUpName;
	}

	public int getUpFollowUpId() {
		return upFollowUpId;
	}

	public void setUpFollowUpId(int upFollowUpId) {
		this.upFollowUpId = upFollowUpId;
	}

	public String getUpFollowUpName() {
		return upFollowUpName;
	}

	public void setUpFollowUpName(String upFollowUpName) {
		this.upFollowUpName = upFollowUpName;
	}

	public int getFollowCount() {
		return followCount;
	}

	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	public int getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(int callStatus) {
		this.callStatus = callStatus;
	}

	public int getCallStatusId() {
		return callStatusId;
	}

	public void setCallStatusId(int callStatusId) {
		this.callStatusId = callStatusId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatusIds() {
		return statusIds;
	}

	public void setStatusIds(String statusIds) {
		this.statusIds = statusIds;
	}

	public int getHasAccount() {
		return hasAccount;
	}

	public void setHasAccount(int hasAccount) {
		this.hasAccount = hasAccount;
	}

	public int getLastMonitorResult() {
		return lastMonitorResult;
	}

	public void setLastMonitorResult(int lastMonitorResult) {
		this.lastMonitorResult = lastMonitorResult;
	}

	public String getLastMonitorResultName() {
		return lastMonitorResultName;
	}

	public void setLastMonitorResultName(String lastMonitorResultName) {
		this.lastMonitorResultName = lastMonitorResultName;
	}

	public String getWorkUnitInfo() {
		return workUnitInfo;
	}

	public void setWorkUnitInfo(String workUnitInfo) {
		this.workUnitInfo = workUnitInfo;
	}

	public int getStartStatusId() {
		return startStatusId;
	}

	public void setStartStatusId(int startStatusId) {
		this.startStatusId = startStatusId;
	}

	public int getEndStatusId() {
		return endStatusId;
	}

	public void setEndStatusId(int endStatusId) {
		this.endStatusId = endStatusId;
	}

	public int getIsExemption() {
		return isExemption;
	}

	public void setIsExemption(int isExemption) {
		this.isExemption = isExemption;
	}

	public int getPendingTestCounts() {
		return pendingTestCounts;
	}

	public void setPendingTestCounts(int pendingTestCounts) {
		this.pendingTestCounts = pendingTestCounts;
	}

	public int getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup(int ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getHomeplace() {
		return homeplace;
	}

	public void setHomeplace(String homeplace) {
		this.homeplace = homeplace;
	}

	public int getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(int politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public Date getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(Date admissionTime) {
		this.admissionTime = admissionTime;
	}

	public int getAdmissionWay() {
		return admissionWay;
	}

	public void setAdmissionWay(int admissionWay) {
		this.admissionWay = admissionWay;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getEnrollmentBatchName() {
		return enrollmentBatchName;
	}

	public void setEnrollmentBatchName(String enrollmentBatchName) {
		this.enrollmentBatchName = enrollmentBatchName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public int getStudentImportRecordId() {
		return studentImportRecordId;
	}

	public void setStudentImportRecordId(int studentImportRecordId) {
		this.studentImportRecordId = studentImportRecordId;
	}

	public int getGlobalBatch() {
		return globalBatch;
	}

	public void setGlobalBatch(int globalBatch) {
		this.globalBatch = globalBatch;
	}

	public String getEnrollmentWayName() {
		return enrollmentWayName;
	}

	public void setEnrollmentWayName(String enrollmentWayName) {
		this.enrollmentWayName = enrollmentWayName;
	}

	public int getIsForceFee() {
		return isForceFee;
	}

	public void setIsForceFee(int isForceFee) {
		this.isForceFee = isForceFee;
	}

	public int getNotApplyFee() {
		return notApplyFee;
	}

	public void setNotApplyFee(int notApplyFee) {
		this.notApplyFee = notApplyFee;
	}

	public String getEnrollmentSourceName() {
		return enrollmentSourceName;
	}

	public void setEnrollmentSourceName(String enrollmentSourceName) {
		this.enrollmentSourceName = enrollmentSourceName;
	}

	public int getMonitorStatus() {
		return monitorStatus;
	}

	public void setMonitorStatus(int monitorStatus) {
		this.monitorStatus = monitorStatus;
	}

	public int getTuitionStatus() {
		return tuitionStatus;
	}

	public void setTuitionStatus(int tuitionStatus) {
		this.tuitionStatus = tuitionStatus;
	}

	public String getBatchIds() {
		return batchIds;
	}

	public void setBatchIds(String batchIds) {
		this.batchIds = batchIds;
	}

	public int getGlbtach() {
		return glbtach;
	}

	public void setGlbtach(int glbtach) {
		this.glbtach = glbtach;
	}

	public int getGllevel() {
		return gllevel;
	}

	public void setGllevel(int gllevel) {
		this.gllevel = gllevel;
	}

	public int getGlmajor() {
		return glmajor;
	}

	public void setGlmajor(int glmajor) {
		this.glmajor = glmajor;
	}

	public String getMonitorStatusIds() {
		return monitorStatusIds;
	}

	public void setMonitorStatusIds(String monitorStatusIds) {
		this.monitorStatusIds = monitorStatusIds;
	}

	public int getIsStartCourse() {
		return isStartCourse;
	}

	public void setIsStartCourse(int isStartCourse) {
		this.isStartCourse = isStartCourse;
	}

	public Date getAdmitTime() {
		return admitTime;
	}

	public void setAdmitTime(Date admitTime) {
		this.admitTime = admitTime;
	}

	public int getNameLockingStatus() {
		return nameLockingStatus;
	}

	public void setNameLockingStatus(int nameLockingStatus) {
		this.nameLockingStatus = nameLockingStatus;
	}

	public int getCertNoLockingStatus() {
		return certNoLockingStatus;
	}

	public void setCertNoLockingStatus(int certNoLockingStatus) {
		this.certNoLockingStatus = certNoLockingStatus;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getGlobalBatchName() {
		return globalBatchName;
	}

	public void setGlobalBatchName(String globalBatchName) {
		this.globalBatchName = globalBatchName;
	}

	public int getNewstuId() {
		return newstuId;
	}

	public void setNewstuId(int newstuId) {
		this.newstuId = newstuId;
	}

	public String getAcademyIds() {
		return academyIds;
	}

	public void setAcademyIds(String academyIds) {
		this.academyIds = academyIds;
	}

	@JSON(format = "yyyy-MM-dd HH:mm")
	public Date getLatestFollowUpDate() {
		return latestFollowUpDate;
	}

	public void setLatestFollowUpDate(Date latestFollowUpDate) {
		this.latestFollowUpDate = latestFollowUpDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEnrollmentSourceName1() {
		return enrollmentSourceName1;
	}

	public void setEnrollmentSourceName1(String enrollmentSourceName1) {
		this.enrollmentSourceName1 = enrollmentSourceName1;
	}

	public String getStudentDataSourceName() {
		return studentDataSourceName;
	}

	public void setStudentDataSourceName(String studentDataSourceName) {
		this.studentDataSourceName = studentDataSourceName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getIsChannelTypeChecked() {
		return isChannelTypeChecked;
	}

	public void setIsChannelTypeChecked(int isChannelTypeChecked) {
		this.isChannelTypeChecked = isChannelTypeChecked;
	}

	public int getPushId() {
		return pushId;
	}

	public void setPushId(int pushId) {
		this.pushId = pushId;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	public Date getPushStartDate() {
		return pushStartDate;
	}

	public void setPushStartDate(Date pushStartDate) {
		this.pushStartDate = pushStartDate;
	}

	public Date getPushEndDate() {
		return pushEndDate;
	}

	public void setPushEndDate(Date pushEndDate) {
		this.pushEndDate = pushEndDate;
	}

	public String getPushName() {
		return pushName;
	}

	public void setPushName(String pushName) {
		this.pushName = pushName;
	}

	public String getPrePurchaseStatus() {
		return prePurchaseStatus;
	}

	public void setPrePurchaseStatus(String prePurchaseStatus) {
		this.prePurchaseStatus = prePurchaseStatus;
	}

	public Date getGraduateTime() {
		return graduateTime;
	}

	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
	}

	public int getDiplomaStatus() {
		return diplomaStatus;
	}

	public void setDiplomaStatus(int diplomaStatus) {
		this.diplomaStatus = diplomaStatus;
	}

	public String getStudentDataSourceIds() {
		return studentDataSourceIds;
	}

	public void setStudentDataSourceIds(String studentDataSourceIds) {
		this.studentDataSourceIds = studentDataSourceIds;
	}

	public Date getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(Date createStartDate) {
		this.createStartDate = createStartDate;
	}

	public Date getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}

	public int getIsChannelRebate() {
		return isChannelRebate;
	}

	public void setIsChannelRebate(int isChannelRebate) {
		this.isChannelRebate = isChannelRebate;
	}

	public int getIsEmphasisVerification() {
		return isEmphasisVerification;
	}

	public void setIsEmphasisVerification(int isEmphasisVerification) {
		this.isEmphasisVerification = isEmphasisVerification;
	}

	public int getChannelPolicyStandardId() {
		return channelPolicyStandardId;
	}

	public void setChannelPolicyStandardId(int channelPolicyStandardId) {
		this.channelPolicyStandardId = channelPolicyStandardId;
	}

	public int getChannelPolicyStandardLock() {
		return channelPolicyStandardLock;
	}

	public void setChannelPolicyStandardLock(int channelPolicyStandardLock) {
		this.channelPolicyStandardLock = channelPolicyStandardLock;
	}

	public String getNoLastMonitorResults() {
		return noLastMonitorResults;
	}

	public void setNoLastMonitorResults(String noLastMonitorResults) {
		this.noLastMonitorResults = noLastMonitorResults;
	}

	public int getRebateFpdCount() {
		return rebateFpdCount;
	}

	public void setRebateFpdCount(int rebateFpdCount) {
		this.rebateFpdCount = rebateFpdCount;
	}

	public Map<String, String> getParamsString()
	{
		if(paramsString==null)
		{
			paramsString=new HashMap<String, String>();
		}
		return paramsString;
	}

	public void setParamsString(Map<String, String> paramsString) {
		this.paramsString = paramsString;
	}

	public Map<String, Integer> getParamsInt() {
		if(paramsInt==null)
		{
			paramsInt=new HashMap<String, Integer>();
		}
		return paramsInt;
	}

	public void setParamsInt(Map<String, Integer> paramsInt) {
		this.paramsInt = paramsInt;
	}

	public Date getFollowUpTimeBegin() {
		return followUpTimeBegin;
	}

	public void setFollowUpTimeBegin(Date followUpTimeBegin) {
		this.followUpTimeBegin = followUpTimeBegin;
	}

	public Date getFollowUpTimeEnd() {
		return followUpTimeEnd;
	}

	public void setFollowUpTimeEnd(Date followUpTimeEnd) {
		this.followUpTimeEnd = followUpTimeEnd;
	}

	public Date getReturnVisitTimeBegin() {
		return returnVisitTimeBegin;
	}

	public void setReturnVisitTimeBegin(Date returnVisitTimeBegin) {
		this.returnVisitTimeBegin = returnVisitTimeBegin;
	}

	public Date getReturnVisitTimeEnd() {
		return returnVisitTimeEnd;
	}

	public void setReturnVisitTimeEnd(Date returnVisitTimeEnd) {
		this.returnVisitTimeEnd = returnVisitTimeEnd;
	}

	public int getReturnVisitTimeType() {
		return returnVisitTimeType;
	}

	public void setReturnVisitTimeType(int returnVisitTimeType) {
		this.returnVisitTimeType = returnVisitTimeType;
	}

	
}
