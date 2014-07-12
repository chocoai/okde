package net.cedu.entity.finance;


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

import net.cedu.entity.crm.Student;

import org.apache.struts2.json.annotations.JSON;


/**
 * 学生账户金额变动管理
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_student_account_amount_management")
public class StudentAccountAmountManagement  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  //主建ID
	private int id;
	
	@Column(name = "code")
	private String code;  //账户编号
	
	@Column(name = "account_id")  //学生账户ID
	private int accountId;
	
	@Column(name = "fee_subject")  //费用科目ID
	private int feeSubject;
	
	@Column(name = "fee_payment_id")  //缴费单ID
	private int feePaymentId;
	
	@Column(name = "fee_payment_detail_id")  //缴费单明细ID
	private int feePaymentDetailId;
	
	@Column(name = "account_money")
	private BigDecimal accountMoney;  //总缴费金额
	
	@Column(name = "types")  //状态
	private int types;
	
	@Column(name = "description")  //描述
	private String description;

	@Column(name = "delete_flag")
	private int deleteFlag;  //删除标记
	
	@Column(name = "creator_id")
	private int creatorId;  //创建人
	
	@Column(name = "created_time")
	private Date createdTime;  //创建时间
	
	@Transient
	transient private String studentName; // 学生姓名

	@Transient
	transient private String schoolName; // 院校名称

	@Transient
	transient private String academyenrollbatchName; // 院校批次

	@Transient
	transient private String levelName; // 层次

	@Transient
	transient private String majorName; // 专业
	
	@Transient
	private transient String branchName; // 学习中心名称

	@Transient
	transient private String feeSubjectName;   //费用科目名称
	
	@Transient
	transient private int starttypes;   //开始状态
	
	@Transient
	transient private int endtypes;   //结束状态
	
	@Transient
	transient private Student student; // 学生信息
	
	@Transient
	transient private FeePayment feePayment; // 缴费单信息
	
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
		this.code = code;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public BigDecimal getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(BigDecimal accountMoney) {
		this.accountMoney = accountMoney;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getFeeSubject() {
		return feeSubject;
	}

	public void setFeeSubject(int feeSubject) {
		this.feeSubject = feeSubject;
	}

	public String getFeeSubjectName() {
		return feeSubjectName;
	}

	public void setFeeSubjectName(String feeSubjectName) {
		this.feeSubjectName = feeSubjectName;
	}

	public int getStarttypes() {
		return starttypes;
	}

	public void setStarttypes(int starttypes) {
		this.starttypes = starttypes;
	}

	public int getEndtypes() {
		return endtypes;
	}

	public void setEndtypes(int endtypes) {
		this.endtypes = endtypes;
	}

	public int getFeePaymentDetailId() {
		return feePaymentDetailId;
	}

	public void setFeePaymentDetailId(int feePaymentDetailId) {
		this.feePaymentDetailId = feePaymentDetailId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}
	
	

}
