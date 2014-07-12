package net.cedu.entity.book;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 总部派发单
 * @author YY
 *
 */
@Entity
@Table(name="tb_e_Headquarters_to_distribute")
public class HeadquartersToDistribute implements Serializable {
 
	private static final long serialVersionUID = -5253724590242875792L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;//主键id
	
	@Column(name="purchase_requisition_id")
	private int purchaseRequisitionId;//中心申购单明细ID
	
	@Column(name="code")
	private String code;//派发单编号
	
	@Column(name="branch_id")
	private int branchId;//受派发中心ID
	
	@Transient
	transient private String branchName; //受派发中心名称
 
	
	@Column(name="amount")
	private double amount;//金额
	
	@Column(name="distributeer")
	private int distributeer;//派发人
	
	@Transient
	transient private String distributename; //派发人姓名
	
	@Column(name="distribute_time")
	private Date distributetime;//派发时间
 
	@Column(name="delete_flag")
    private int deleteFlag; 	//删除标记
	
	@Column(name="creator_id")
    private int creatorId;     //创始人
	
	@Column(name="created_time")
    private Date createdTime;  //创建时间
	
	@Column(name="updater_id")
    private int updaterId;     //最后修改人
	
	@Column(name="updated_time")
    private Date updatedTime;  //最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaseRequisitionId() {
		return purchaseRequisitionId;
	}

	public void setPurchaseRequisitionId(int purchaseRequisitionId) {
		this.purchaseRequisitionId = purchaseRequisitionId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
 
	public int getDistributeer() {
		return distributeer;
	}

	public void setDistributeer(int distributeer) {
		this.distributeer = distributeer;
	}

	public String getDistributename() {
		return distributename;
	}

	public void setDistributename(String distributename) {
		this.distributename = distributename;
	}

	public Date getDistributetime() {
		return distributetime;
	}

	public void setDistributetime(Date distributetime) {
		this.distributetime = distributetime;
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

	
	
	
}
