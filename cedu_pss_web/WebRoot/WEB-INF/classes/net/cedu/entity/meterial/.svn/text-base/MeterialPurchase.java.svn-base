package net.cedu.entity.meterial;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 总部采购
 * 
 * @author YY
 * 
 */
@Entity
@Table(name = "tb_e_meterial_purchase", catalog = "cedu_master")
public class MeterialPurchase implements java.io.Serializable {
 
 
	private static final long serialVersionUID = -2499060006599331254L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id; // 主键id

	@Column(name = "code", length = 16)
	private String code; // 采购单编号

	@Column(name = "operators")
	private int operators; // 采购人
	
	@Transient
	transient private String operatorsname; //采购人名称

	@Column(name = "amount")
	private Double amount; // 采购总金额

	@Column(name = "purchase_time")
	private Date purchaseTime; // 采购时间

	@Column(name = "delete_flag")
	private int deleteFlag; // 删除标记

	@Column(name = "creator_id")
	private int creatorId; // 创建人

	@Column(name = "created_time")
	private Date createdTime=new Date(); // 创建时间

	@Column(name = "updater_id")
	private int updaterId; // 最后修改人

	@Column(name = "updated_time", length = 19)
	private Date updatedTime; // 最后修改时间

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

	public int getOperators() {
		return operators;
	}

	public void setOperators(int operators) {
		this.operators = operators;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
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

	public String getOperatorsname() {
		return operatorsname;
	}

	public void setOperatorsname(String operatorsname) {
		this.operatorsname = operatorsname;
	}
	
	

}