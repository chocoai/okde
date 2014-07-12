package net.cedu.entity.enrollment;

import java.io.Serializable;
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

import org.apache.struts2.json.annotations.JSON;

/**
 * 院校收费标准规则
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_e_fee_standard")
public class FeeStandard implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "policy_fee_detail_id")
	private int policyFeeDetailId; // 收费政策标准ID
	@Column(name = "fee_batch_id")
	private int feeBatchId; // 缴费次数
	@Column(name = "fee_batch_name")
	private String feeBatchName; //缴费次数名称
	@Column(name = "start_time")
	private Date startTime;//开始时间    
	@Column(name = "end_time")
	private Date endTime;//结束时间
	@Column(name = "charging_floor")
	private double chargingFloor; // 下限
	@Column(name = "charging_ceil")
	private double chargingCeil; // 上限
	
	@Column(name = "revised_credit")
	private int revisedCredit; // 应修学分
	@Column(name = "credit_fee")
	private double creditFee; // 每学分金额
	
	@Transient
	transient private String feeSubjectName;//费用科目名称
	
	@Transient
	transient private int policyFeeDetail;//收费政策Id
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPolicyFeeDetailId() {
		return policyFeeDetailId;
	}
	public void setPolicyFeeDetailId(int policyFeeDetailId) {
		this.policyFeeDetailId = policyFeeDetailId;
	}
	public int getFeeBatchId() {
		return feeBatchId;
	}
	public void setFeeBatchId(int feeBatchId) {
		this.feeBatchId = feeBatchId;
	}
	public double getChargingFloor() {
		return chargingFloor;
	}
	public void setChargingFloor(double chargingFloor) {
		this.chargingFloor = chargingFloor;
	}
	public double getChargingCeil() {
		return chargingCeil;
	}
	public void setChargingCeil(double chargingCeil) {
		this.chargingCeil = chargingCeil;
	}
	public int getRevisedCredit() {
		return revisedCredit;
	}
	public void setRevisedCredit(int revisedCredit) {
		this.revisedCredit = revisedCredit;
	}
	public double getCreditFee() {
		return creditFee;
	}
	public void setCreditFee(double creditFee) {
		this.creditFee = creditFee;
	}
	
	public String getFeeBatchName() {
		return feeBatchName;
	}
	public void setFeeBatchName(String feeBatchName) {
		this.feeBatchName = feeBatchName;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getFeeSubjectName() {
		return feeSubjectName;
	}
	public void setFeeSubjectName(String feeSubjectName) {
		this.feeSubjectName = feeSubjectName;
	}
	public int getPolicyFeeDetail() {
		return policyFeeDetail;
	}
	public void setPolicyFeeDetail(int policyFeeDetail) {
		this.policyFeeDetail = policyFeeDetail;
	}
	
	
}
