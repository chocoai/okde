package net.cedu.entity.finance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.file.excel.ExcelAnnotation;
import net.cedu.entity.crm.Student;

import org.apache.struts2.json.annotations.JSON;

/**
 * 缴费单
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_fee_payment")
public class FeePayment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	// 主建ID
	private int id;

	@Column(name = "student_id")
	// 学生ID
	private int studentId;
	
	//全局批次
	@Column(name = "common_batch")
	private int commonBatch;

	
	@Column(name = "code")
	private String code; // 缴费单号

	@Column(name = "pos_serial_no")
	private String posSerialNo; // POS流水号

	@Column(name = "bar_code")
	private String barCode; // 条形码

	@Column(name = "fee_way_id")
	private int feeWayId; // 缴费方式ID

	@Column(name = "discount_amount")
	private double discountAmount;// 总优惠金额
	
	@Column(name = "fee_payment")
	private double feePayment; // 实缴金额(总金额减去充值金额      包括优惠金额和使用的充值金额)
	
	@Column(name = "recharge_amount")
	private double rechargeAmount; // 充值金额
	
	@Column(name = "total_amount")
	private double totalAmount; // 总金额

	@Column(name = "is_print")
	private int isPrint; // 是否打印

	@Column(name = "status")
	private int status;	//状态
	
	@Column(name = "pament_type")
	private int pamentType;	//缴费单类型
	
	@Column(name = "note")
	private String note;	//备注

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
	transient private Student student; // 学生信息

	@Transient
	transient private String feePaymentDetailCode; // 缴费单明细Code

	@Transient
	transient private double amountPaied; // 实收金额

	@Transient
	transient private String feeSubject; // 费用项目

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
	transient private String invoiceCode; // 发票号
	
	@Transient
	private transient int startStatusId;//状态开始Id
	
	@Transient
	private transient int endStatusId;//状态结束Id
	
	@Transient
	transient private List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>(); // 缴费单明细List

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

	public String getPosSerialNo() {
		return posSerialNo;
	}

	public void setPosSerialNo(String posSerialNo) {
		this.posSerialNo = posSerialNo;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public int getFeeWayId() {
		return feeWayId;
	}

	public void setFeeWayId(int feeWayId) {
		this.feeWayId = feeWayId;
	}

	public double getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(double feePayment) {
		this.feePayment = feePayment;
	}

	public int getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(int isPrint) {
		this.isPrint = isPrint;
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

	@JSON(format = "yyyy-MM-dd")
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public double getAmountPaied() {
		return amountPaied;
	}

	public void setAmountPaied(double amountPaied) {
		this.amountPaied = amountPaied;
	}

	public String getFeeSubject() {
		return feeSubject;
	}

	public void setFeeSubject(String feeSubject) {
		this.feeSubject = feeSubject;
	}

	public String getFeePaymentDetailCode() {
		return feePaymentDetailCode;
	}

	public void setFeePaymentDetailCode(String feePaymentDetailCode) {
		this.feePaymentDetailCode = feePaymentDetailCode;
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

	public int getPamentType() {
		return pamentType;
	}

	public void setPamentType(int pamentType) {
		this.pamentType = pamentType;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getCommonBatch() {
		return commonBatch;
	}

	public void setCommonBatch(int commonBatch) {
		this.commonBatch = commonBatch;
	}

	public double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<FeePaymentDetail> getFeePaymentDetailList() {
		return feePaymentDetailList;
	}

	public void setFeePaymentDetailList(List<FeePaymentDetail> feePaymentDetailList) {
		this.feePaymentDetailList = feePaymentDetailList;
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

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
}
