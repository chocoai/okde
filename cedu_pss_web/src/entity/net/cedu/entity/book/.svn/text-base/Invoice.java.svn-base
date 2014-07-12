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
 * 总部发货单
 * 
 * @作者： 杨阳
 * @作成时间：2012-2-22 下午05:12:44
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 */
@Entity
@Table(name = "tb_e_invoice")
public class Invoice implements Serializable {

	private static final long serialVersionUID = -702311745168946389L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;// 主键id

	@Column(name = "code")
	private String code;// 发货单编号

	@Column(name = "supplier_id")
	private int supplierId;// 书商ID
	@Transient
	transient private String suppliername; //书商名称
	
	@Column(name = "order_id")
	private int orderId;// 订购单id
	
	@Transient
	transient private String ordercode; //订购单编号
	
	@Column(name = "branch_id")
	private int branchId;// 学习中心ID
	@Transient
	transient private String branchname; //学习中心名称

	@Column(name = "invoice_operator")
	private int invoiceOperator;// 订购人

	@Column(name = "amount")
	private double amount;// 总金额

	@Column(name = "invoice_time")
	private Date invoiceTime;// 订购时间
	
	@Column(name = "delete_flag")
	private int deleteFlag; // 删除标记

	@Column(name = "creator_id")
	private int creatorId; // 创始人

	@Column(name = "created_time")
	private Date createdTime; // 创建时间

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getInvoiceOperator() {
		return invoiceOperator;
	}

	public void setInvoiceOperator(int invoiceOperator) {
		this.invoiceOperator = invoiceOperator;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getInvoiceTime() {
		return invoiceTime;
	}

	public void setInvoiceTime(Date invoiceTime) {
		this.invoiceTime = invoiceTime;
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

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	
}
