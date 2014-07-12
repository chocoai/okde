package net.cedu.entity.finance;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;




/**
 * 邮寄包
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_postal_parcel")
public class PostalParcel  implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  //主建ID
	private int id;
	
	@Column(name = "post_serial_no")
	private String postSerialNo;  //邮寄单号
	
	@Column(name = "code")
	private String code;  //邮包号
	
	@Column(name = "forwarder")
	private String forwarder;  //货运公司
	
	@Column(name = "branch_id")  //中心ID
	private int branchId;
	
	@Column(name = "invoice_ids")
	private String invoiceIds;  //发票ID
	
	@Column(name = "status")  //状态  0,未配送  1,已配送，未签收  2,已配送已签收
	private int status;
	   
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
	transient private String branchName;   //学习中心名称
	
	@Transient
	transient private int invoiceNumber;   //发票数量
	
	@Transient
	transient private int stuSignNumber;   //学生已签领数
	
	@Transient
	transient private int stuSignNoNumber;   //学生未签领数
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostSerialNo() {
		return postSerialNo;
	}

	public void setPostSerialNo(String postSerialNo) {
		this.postSerialNo = postSerialNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getForwarder() {
		return forwarder;
	}

	public void setForwarder(String forwarder) {
		this.forwarder = forwarder;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getInvoiceIds() {
		return invoiceIds;
	}

	public void setInvoiceIds(String invoiceIds) {
		this.invoiceIds = invoiceIds;
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
	@JSON(format="yyyy-MM-dd")
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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public int getStuSignNumber() {
		return stuSignNumber;
	}

	public void setStuSignNumber(int stuSignNumber) {
		this.stuSignNumber = stuSignNumber;
	}

	public int getStuSignNoNumber() {
		return stuSignNoNumber;
	}

	public void setStuSignNoNumber(int stuSignNoNumber) {
		this.stuSignNoNumber = stuSignNoNumber;
	}


	
	
	

}
