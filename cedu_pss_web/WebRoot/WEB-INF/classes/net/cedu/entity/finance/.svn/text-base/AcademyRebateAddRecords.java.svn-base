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

/**
 * 院校返款追加记录
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_r_academy_rebate_add_records")
public class AcademyRebateAddRecords implements Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "pay_academy_cedu_id")
	private int payAcademyCeduId; // 本次院校返款单Id
	
	@Column(name = "pay_academy_cedu_add_id")
	private int payAcademyCeduAddId; // 追加的院校返款单Id
	
	@Column(name = "fee_payment_detail_id")
	private int feePaymentDetailId; // 缴费单明细Id
	
	@Column(name = "student_id")
	private int studentId; // 学生Id
	
//	@Column(name="rebate_id")
//	private int rebateId; //返款方式
//	
//	@Column(name="money_value")
//	private BigDecimal value; //金额/比例
	
	@Column(name="add_money")
	private BigDecimal addMoney; //追加金额

	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间

	@Column(name = "updater_id")
	private int updaterId; // 最后修改人

	@Column(name = "updated_time")
	private Date updatedTime; // 最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPayAcademyCeduId() {
		return payAcademyCeduId;
	}

	public void setPayAcademyCeduId(int payAcademyCeduId) {
		this.payAcademyCeduId = payAcademyCeduId;
	}

	public int getPayAcademyCeduAddId() {
		return payAcademyCeduAddId;
	}

	public void setPayAcademyCeduAddId(int payAcademyCeduAddId) {
		this.payAcademyCeduAddId = payAcademyCeduAddId;
	}

	public int getFeePaymentDetailId() {
		return feePaymentDetailId;
	}

	public void setFeePaymentDetailId(int feePaymentDetailId) {
		this.feePaymentDetailId = feePaymentDetailId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

//	public int getRebateId() {
//		return rebateId;
//	}
//
//	public void setRebateId(int rebateId) {
//		this.rebateId = rebateId;
//	}
//
//	public BigDecimal getValue() {
//		return value;
//	}
//
//	public void setValue(BigDecimal value) {
//		this.value = value;
//	}

	public BigDecimal getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(BigDecimal addMoney) {
		this.addMoney = addMoney;
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
	
	

}
