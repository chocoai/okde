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
 * 总部订购单
 * @author XFY
 *
 */
@Entity
@Table(name="tb_e_cedu_book_order")
public class CeduBookOrder implements Serializable {
 
	private static final long serialVersionUID = 362932568748032314L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;//主键id
	
	@Column(name="code")
	private String code;//订购单编号
	
	@Column(name="supplier_id")
	private int supplierId;//书商ID
	
	@Transient
	transient private String suppilername; //书商名称;
	
	@Column(name="branch_id")
	private int branchId;//学习中心ID
	
	@Transient
	transient private String branchname; //学习中心名称;
	
	@Column(name="order_operator")
	private int orderOperator;//订购人
	
	@Transient
	transient private String orderoperatorname; //订购人名称
	
	@Column(name="amount")
	private double amount;//总金额
	
	@Column(name="order_time")
	private Date orderTime;//订购时间
	
	@Column(name="status")
	private int status;//状态
	
	@Transient
	transient private String statusname; //状态显示
	
	@Column(name="types")
	private int types;//类别(直订、代订)
	
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

	public int getOrderOperator() {
		return orderOperator;
	}

	public void setOrderOperator(int orderOperator) {
		this.orderOperator = orderOperator;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
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

 
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getSuppilername() {
		return suppilername;
	}

	public void setSuppilername(String suppilername) {
		this.suppilername = suppilername;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getOrderoperatorname() {
		return orderoperatorname;
	}

	public void setOrderoperatorname(String orderoperatorname) {
		this.orderoperatorname = orderoperatorname;
	}

	public int getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(int updaterId) {
		this.updaterId = updaterId;
	}
	
	
}
