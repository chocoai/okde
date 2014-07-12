package net.cedu.entity.enrollment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 优惠卷(优惠申请)
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_e_discount_application")
public class DiscountApplication implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "student_id")
	private int studentId; // 学生ID
	@Column(name = "code")
	private String code; // 优惠卷编号
	@Column(name = "policy_standard_id")
	private int policyStandardId; // 优惠政策标准ID
	@Column(name = "note")
	private String note; // 备注
	@Column(name = "audit_status")
	private int auditStatus; // 审核状态
	@Column(name = "usage_flag")
	private int usageFlag; // 使用状态
	@Column(name = "use_datetime")
	private Date useDatetime;//使用时间
	@Column(name = "start_time")
	private Date startTime;//有效时间：开始时间
	@Column(name = "end_time")
	private Date endTime;//有效时间：结束时间
	@Column(name = "discount_way")
	private int discountWay;//缴费方式
	@Column(name = "money")
	private BigDecimal money;//金额比例
	@Column(name="create_time")
	private Date createTime; //创建时间
	@Column(name = "auditee")
	private int auditee; // 审核方：0;总部，1中心
	
	
	@Transient
	transient private String feeSubjectName;//费用科目名称
	@Transient
	transient private String discountstandard;//优惠标准
	@Transient
	transient private String studentName;//学生姓名
	@Transient
	transient private String branchName;//学习中心名称
	@Transient
	transient private String academyName;//院校名称
	@Transient
	transient private String batchName;//招生批次名称
	@Transient
	transient private String levelName;//层次名称
	@Transient
	transient private String majorName;//专业名称
	@Transient
	transient private int gender;//性别
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public int getUsageFlag() {
		return usageFlag;
	}
	public void setUsageFlag(int usageFlag) {
		this.usageFlag = usageFlag;
	}
	public Date getUseDatetime() {
		return useDatetime;
	}
	public void setUseDatetime(Date useDatetime) {
		this.useDatetime = useDatetime;
	}
	public int getPolicyStandardId() {
		return policyStandardId;
	}
	public void setPolicyStandardId(int policyStandardId) {
		this.policyStandardId = policyStandardId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDiscountstandard() {
		return discountstandard;
	}
	public void setDiscountstandard(String discountstandard) {
		this.discountstandard = discountstandard;
	}
	public String getFeeSubjectName() {
		return feeSubjectName;
	}
	public void setFeeSubjectName(String feeSubjectName) {
		this.feeSubjectName = feeSubjectName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getDiscountWay() {
		return discountWay;
	}
	public void setDiscountWay(int discountWay) {
		this.discountWay = discountWay;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getAuditee() {
		return auditee;
	}
	public void setAuditee(int auditee) {
		this.auditee = auditee;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	
	
}
