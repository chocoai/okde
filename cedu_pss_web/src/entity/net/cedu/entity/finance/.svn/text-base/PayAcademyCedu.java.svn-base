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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import net.cedu.common.Constants;

import org.apache.struts2.json.annotations.JSON;

/**
 * 院校返款
 * 
 * @author Sauntor
 *
 */
@Entity
@Table(name="tb_e_pay_academy_cedu")
public class PayAcademyCedu implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="remittance_no")
	private String remittanceNo; //返款单号
	
	@Column(name="remitter")
	private int remitterId; //返款院校
	
	@Column(name="amount") //应返款金额
	private BigDecimal amount;
	
	@Column(name="amount_paied")
	private BigDecimal amountPaied; //实返款总金额
	
	@Column(name="adjust_paied")
	private BigDecimal adjustPaied; //调整金额
	
	@Column(name="add_paied")
	private BigDecimal addPaied; //追加金额
	
	@Column(name="payment_amount") //缴费单明细返款金额
	private BigDecimal paymentAmount;
	
	@Column(name="academy_amount") //应收学校款返款金额
	private BigDecimal academyAmount;
	
	@Column(name="refund_amount") //退费抵销金额
	private BigDecimal refundAmount;
	
	@Column(name="note") //备注
	private String note;
	
	@Column(name="is_year_count") //是否年度返款
	private int isYearCount;  //1.院校返款   2.年度院校返款
	
	@Column(name="current_year") //年度返款的具体年数
	private int currentYear;  
	
	@Column(name="remittance_date")
	@Temporal(TemporalType.DATE)
	private Date remittanceDate; //汇款日期

	@Column(name="status")
	private int status; //状态  Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN＝10表示fankuan成功
	
	@Column(name="types")
	private int types; //回退状态       1.已提交    2.已回退
	
	@Column(name="roll_back_reason")
	private String rollBackReason; //回退原因
	
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
	private transient String remitterName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemittanceNo() {
		return remittanceNo;
	}

	public void setRemittanceNo(String remittanceNo) {
		this.remittanceNo = remittanceNo;
	}

	public int getRemitterId() {
		return remitterId;
	}

	public void setRemitterId(int remitterId) {
		this.remitterId = remitterId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@JSON(format=Constants.DATE_FORMAT)
	public Date getRemittanceDate() {
		return remittanceDate;
	}

	public void setRemittanceDate(Date remittanceTime) {
		this.remittanceDate = remittanceTime;
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

	public String getRemitterName() {
		return remitterName;
	}

	public void setRemitterName(String remitterName) {
		this.remitterName = remitterName;
	}

	public BigDecimal getAcademyAmount() {
		return academyAmount;
	}

	public void setAcademyAmount(BigDecimal academyAmount) {
		this.academyAmount = academyAmount;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public BigDecimal getAmountPaied() {
		return amountPaied;
	}

	public void setAmountPaied(BigDecimal amountPaied) {
		this.amountPaied = amountPaied;
	}

	public BigDecimal getAdjustPaied() {
		return adjustPaied;
	}

	public void setAdjustPaied(BigDecimal adjustPaied) {
		this.adjustPaied = adjustPaied;
	}

	public String getRollBackReason() {
		return rollBackReason;
	}

	public void setRollBackReason(String rollBackReason) {
		this.rollBackReason = rollBackReason;
	}

	public BigDecimal getAddPaied() {
		return addPaied;
	}

	public void setAddPaied(BigDecimal addPaied) {
		this.addPaied = addPaied;
	}

	public int getIsYearCount() {
		return isYearCount;
	}

	public void setIsYearCount(int isYearCount) {
		this.isYearCount = isYearCount;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	
	
}
