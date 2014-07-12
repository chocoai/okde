package net.cedu.entity.finance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;

/**
 * 发票管理
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_invoice_management")
public class InvoiceManagement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	// 主建ID
	private int id;

	@Column(name = "invoice_code")
	private String invoiceCode; // 发票号

	@Column(name = "issued_time")
	private Date issuedTime; // 开票日期

	@Column(name = "branch_id")
	// 中心ID
	private int branchId;

	@Column(name = "student_id")
	// 学生ID
	private int studentId;

	@Column(name = "fee_payment_code")
	// 缴费单Code
	private String feePaymentCode;

	@Column(name = "fee_payment_detail_id")
	// 缴费单明细ID
	private String feePaymentDetailId;

	@Column(name = "is_post")
	// 状态 是否发送
	private int isPost;

	@Column(name = "is_sign")
	// 状态 是否签收
	private int isSign;
	@Column(name = "registration_invoice_type")
	private int registrationInvoiceType; // 1.总部登记 2.学习中心自己登记

	@Column(name = "attachment_name")
	// 附件名
	private String attachmentName;

	@Column(name = "attachment_path")
	// 附件路径
	private String attachmentPath;

	@Column(name = "delete_flag")
	private int deleteFlag; // 删除标记

	@Column(name = "creator_id")
	private int creatorId; // 创建人

	@Column(name = "created_time")
	private Date createdTime; // 创建时间

	@Column(name = "updater_id")
	private int updaterId; // 最后修改人

	@Column(name = "updated_time")
	private Date updatedTime; // 最后修改时间

	@Transient
	transient private String branchName; // 学习中心

	@Transient
	transient private String studentName; // 学生姓名

	@Transient
	transient private String academyenrollbatchName; // 院校批次

	@Transient
	transient private String levelName; // 层次

	@Transient
	transient private String majorName; // 专业

	@Transient
	transient private double amountPaied; // 总金额
	
	@Transient
	transient private String academyName; // 院校

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFeePaymentCode() {
		return feePaymentCode;
	}

	public void setFeePaymentCode(String feePaymentCode) {
		this.feePaymentCode = feePaymentCode;
	}

	public String getFeePaymentDetailId() {
		return feePaymentDetailId;
	}

	public void setFeePaymentDetailId(String feePaymentDetailId) {
		this.feePaymentDetailId = feePaymentDetailId;
	}

	public int getIsPost() {
		return isPost;
	}

	public void setIsPost(int isPost) {
		this.isPost = isPost;
	}

	public int getIsSign() {
		return isSign;
	}

	public void setIsSign(int isSign) {
		this.isSign = isSign;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public double getAmountPaied() {
		return amountPaied;
	}

	public void setAmountPaied(double amountPaied) {
		this.amountPaied = amountPaied;
	}

	public int getRegistrationInvoiceType() {
		return registrationInvoiceType;
	}

	public void setRegistrationInvoiceType(int registrationInvoiceType) {
		this.registrationInvoiceType = registrationInvoiceType;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

}
