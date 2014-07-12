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
 * 中心申购单
 * @author XFY
 *
 */
@Entity
@Table(name="tb_e_purchase_requisition")
public class PurchaseRequisition implements Serializable {
 
	private static final long serialVersionUID = -8132543734986952097L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;//主键id
	
	@Column(name="code")
	private String code;//申购单编号
	
	@Column(name="branch_id")
	private int branchId;//申购中心ID
	
	@Transient
	transient private String branchName; //申购中心名称
	
	@Column(name="academy_id")
	private int academyId;//院校ID
	
	@Column(name="amount")
	private double amount;//金额
	
	@Column(name="requisitioner")
	private int requisitioner;//申购人
	
	@Transient
	transient private String requisitionername; //申购人姓名
	
	@Column(name="requisition_time")
	private Date requisitiontime;//申购时间
	
	@Column(name="status")
	private int status; //状态
	
	@Transient
	transient private String statusname; //状态名称
	
	@Column(name="types")	
	private int types;//类别(学生申购、预估申购)
	
	@Column(name="order_id")	
	private int orderId;//订购单id
	
	
	@Column(name="address")
	private String address; //地址
	
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

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getRequisitioner() {
		return requisitioner;
	}

	public void setRequisitioner(int requisitioner) {
		this.requisitioner = requisitioner;
	}

	public Date getRequisitiontime() {
		return requisitiontime;
	}

	public void setRequisitiontime(Date requisitiontime) {
		this.requisitiontime = requisitiontime;
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

	public String getRequisitionername() {
		return requisitionername;
	}

	public void setRequisitionername(String requisitionername) {
		this.requisitionername = requisitionername;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(int updaterId) {
		this.updaterId = updaterId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

 
	
}
