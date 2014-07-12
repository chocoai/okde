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

/**
 * 待缴费单
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_e_pending_fee_payment")
public class PendingFeePayment implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	// 主建ID

	@Column(name = "code")
	private String code; // 待缴费单号
	
	@Column(name = "student_id")
	private int studentId;	// 学生ID

	@Column(name = "policy_fee_detail_id")// 收费政策ID
	private int policyFeeDetailId;
	
	@Column(name = "fee_standard_id")
	private int feeStandardId; //收费政策标准明细Id
	
	@Column(name = "status")
	private int status; //状态 ：未缴、已缴未完、已缴已完
	
	@Column(name = "amount_paid")
	private BigDecimal amountPaid; //已缴金额
	
	@Column(name = "amount_surplus")
	private BigDecimal amountSurplus; //剩余金额
	
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
	
	//新加字段
	@Column(name = "fee_subject_id")
	private int feeSubjectId; // 费用科目Id
	
	@Column(name = "fee_batch_id")
	private int feeBatchId; // 缴费次数
	
	@Column(name = "money_to_pay")
	private BigDecimal moneyToPay;//应缴金额
	
	@Column(name = "charging_ceil")
	private BigDecimal chargingCeil; // 上限金额
	
	@Column(name = "start_time")
	private Date startTime;// 收费标准开始时间    
	
	@Column(name = "end_time")
	private Date endTime;//收费标准结束时间
	
	@Transient
	transient private String feeSubjectName;//费用科目名称
	
	@Transient
	transient private String modeName;//学制名称

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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getFeeStandardId() {
		return feeStandardId;
	}

	public void setFeeStandardId(int feeStandardId) {
		this.feeStandardId = feeStandardId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public BigDecimal getAmountSurplus() {
		return amountSurplus;
	}

	public void setAmountSurplus(BigDecimal amountSurplus) {
		this.amountSurplus = amountSurplus;
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

	public String getFeeSubjectName() {
		return feeSubjectName;
	}

	public void setFeeSubjectName(String feeSubjectName) {
		this.feeSubjectName = feeSubjectName;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public int getPolicyFeeDetailId() {
		return policyFeeDetailId;
	}

	public void setPolicyFeeDetailId(int policyFeeDetailId) {
		this.policyFeeDetailId = policyFeeDetailId;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public int getFeeBatchId() {
		return feeBatchId;
	}

	public void setFeeBatchId(int feeBatchId) {
		this.feeBatchId = feeBatchId;
	}

	public BigDecimal getMoneyToPay() {
		return moneyToPay;
	}

	public void setMoneyToPay(BigDecimal moneyToPay) {
		this.moneyToPay = moneyToPay;
	}

	public BigDecimal getChargingCeil() {
		return chargingCeil;
	}

	public void setChargingCeil(BigDecimal chargingCeil) {
		this.chargingCeil = chargingCeil;
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
	
	
}
