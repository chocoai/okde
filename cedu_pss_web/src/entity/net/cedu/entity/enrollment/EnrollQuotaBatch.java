package net.cedu.entity.enrollment;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 批次总指标
 * @author gaolei
 */
@Entity
@Table(name="tb_e_enroll_quota_batch")
public class EnrollQuotaBatch implements Serializable
{
	private static final long serialVersionUID = -5308599405933447949L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; //主键ID
	
	@Column(name="quota_id")
	private int quotaId; //年度总指标Id
	
	@Column(name="batch_id")
	private int batchId; // 批次Id
	
	@Column(name="target")
	private int target; // 指标
	
	
	
	@Column(name="types")
	private int types; // 状态 0:指标未设定  1:指标已设定
	
	@Column(name="delete_flag")
	private int deleteFlag; //删除标记
	
	
	@Column(name="creator_id")
	private int creatorId; //创建人
	
	@Column(name="created_time")
	private Date createdTime; //创建时间
	
	@Column(name="updater_id")
	private int updaterId; //最后修改人
	
	@Column(name="updated_time")
	private Date updatedTime; //最后修改时间

	@Transient
	transient private String batchName;     //全局批次名称
	
	@Transient
	transient private int enrollYear;     //年度
	
	@Transient
	transient private int enrollQuota;     //年度总指标
	
	@Transient
	transient private double registNumber;     //已报名数
	
	@Transient
	transient private double feePaymentNumber; //已缴费数
	
	@Transient
	transient private double admitNumber;      //已录取数
	
	
	//录取缴费比例
	@Transient
	transient private Object admitfeePaymentProportion;
	
	//指标缴费比例
	@Transient
	transient private Object targetfeePaymentProportion;
	
	//报名录取比例 	
	@Transient
	transient private Object registadmitProportion;
	
	//指标录取比例 	
	@Transient
	transient private Object targetadmitProportion;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(int quotaId) {
		this.quotaId = quotaId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
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

	public double getRegistNumber() {
		return registNumber;
	}

	public void setRegistNumber(double registNumber) {
		this.registNumber = registNumber;
	}

	public double getFeePaymentNumber() {
		return feePaymentNumber;
	}

	public void setFeePaymentNumber(double feePaymentNumber) {
		this.feePaymentNumber = feePaymentNumber;
	}

	public double getAdmitNumber() {
		return admitNumber;
	}

	public void setAdmitNumber(double admitNumber) {
		this.admitNumber = admitNumber;
	}

	
	public Object getAdmitfeePaymentProportion() {
		if(feePaymentNumber!=0 && admitNumber!=0)
		{
			DecimalFormat df = new DecimalFormat("0.00");
			return df.format((feePaymentNumber/admitNumber)*100)+"%";
		}
		return "0.00%";
	}

	public void setAdmitfeePaymentProportion(Object admitfeePaymentProportion) {
		this.admitfeePaymentProportion = admitfeePaymentProportion;
	}

	public Object getTargetfeePaymentProportion() {
		if(feePaymentNumber!=0 && target!=0)
		{
			DecimalFormat df = new DecimalFormat("0.00");
			return df.format((feePaymentNumber/target)*100)+"%";
		}
		return "0.00%";
		
	}

	public void setTargetfeePaymentProportion(Object targetfeePaymentProportion) {
		this.targetfeePaymentProportion = targetfeePaymentProportion;
	}
	
	public Object getRegistadmitProportion() {
		if(admitNumber!=0 && registNumber!=0)
		{
			DecimalFormat df = new DecimalFormat("0.00");
			return df.format((admitNumber/registNumber)*100)+"%";
		}
		return "0.00%";
	}

	public void setRegistadmitProportion(Object registadmitProportion) {
		this.registadmitProportion = registadmitProportion;
	}

	public Object getTargetadmitProportion() {
		if(admitNumber!=0 && target!=0)
		{
			DecimalFormat df = new DecimalFormat("0.00");
			return df.format((admitNumber/target)*100)+"%";
		}
		return "0.00%";
	}

	public void setTargetadmitProportion(Object targetadmitProportion) {
		this.targetadmitProportion = targetadmitProportion;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getEnrollYear() {
		return enrollYear;
	}

	public void setEnrollYear(int enrollYear) {
		this.enrollYear = enrollYear;
	}

	public int getEnrollQuota() {
		return enrollQuota;
	}

	public void setEnrollQuota(int enrollQuota) {
		this.enrollQuota = enrollQuota;
	}

	

}
