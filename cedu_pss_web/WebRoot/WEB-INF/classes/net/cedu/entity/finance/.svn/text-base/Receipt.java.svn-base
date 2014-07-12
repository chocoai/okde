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

import net.cedu.entity.crm.Student;


/**
 * 收据
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_receipt")
public class Receipt  implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  //主建ID
	private int id;
	
	@Column(name = "code")
	private String code;  //收据号
	
	@Column(name = "invoice_book_id")  //票本ID
	private int invoiceBookId;
	
	
	@Column(name = "fee_payment_id")  //缴费单ID
	private int feePaymentId;
	
	@Column(name = "student_id")  //学生ID
	private int studentId;
	 
	@Column(name = "status")  //状态
	private int status;
	 
	@Column(name = "is_canceled")  //是否核销
	private int isCanceled;
	   
	
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
	
	
	@Transient
	transient private String studentName;   //学生姓名
	
	@Transient
	transient private String certNo;   //学生证件号
	
	@Transient
	transient private String batchName;   //学生招生批次
	
	@Transient
	transient private String levelName;   //学生层次
	
	@Transient
	transient private String majorName;   //学生专业
	
    
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

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(int isCanceled) {
		this.isCanceled = isCanceled;
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

	public int getInvoiceBookId() {
		return invoiceBookId;
	}

	public void setInvoiceBookId(int invoiceBookId) {
		this.invoiceBookId = invoiceBookId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
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

	
	

	
}
