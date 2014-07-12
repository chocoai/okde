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

/**
 * 总部替院校垫付退费
 * @author xiao
 *
 */
@Entity
@Table(name="tb_e_refund_cedu_academy")
public class RefundCeduAcademy implements Serializable
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="academy_id")
	private int academyId; //院校Id
	
	@Column(name="student_id") 
	private int studentId; //学生Id
	
	@Column(name="refund_payment_id") 
	private int refundPaymentId; //退费单Id
	
	@Column(name="refund_payment_detail_id") 
	private int refundPaymentDetailId; //退费单明细Id
	
	@Column(name="amount") 
	private BigDecimal amount; //金额
	
	@Column(name="status") 
	private int status; //状态
	
	@Column(name="types") 
	private int types; //类型     
	
	@Column(name="note") 
	private String note; //备注
	
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
	transient private FeePayment feePayment;//退费单
	
	@Transient
	transient private Student student;//学生
	
	@Transient
	transient private double amountPaied;//缴费金额(来自缴费单明细)
	
	@Transient
	transient private double rechargeAmount;//充值金额(来自缴费单明细)
	
	@Transient
	transient private double refundAmount;//退费金额(来自退费单明细)
	
	@Transient
	transient private double totalAmount;//总金额(缴费金额+充值金额+退费金额)

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

	public int getRefundPaymentId() {
		return refundPaymentId;
	}

	public void setRefundPaymentId(int refundPaymentId) {
		this.refundPaymentId = refundPaymentId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public int getRefundPaymentDetailId() {
		return refundPaymentDetailId;
	}

	public void setRefundPaymentDetailId(int refundPaymentDetailId) {
		this.refundPaymentDetailId = refundPaymentDetailId;
	}

	public double getAmountPaied() {
		return amountPaied;
	}

	public void setAmountPaied(double amountPaied) {
		this.amountPaied = amountPaied;
	}

	public double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
