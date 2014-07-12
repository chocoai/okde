package net.cedu.entity.finance;

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

/**
 * （中心）汇款总部单
 * @author Sauntor
 *
 */
@Entity
@Table(name="tb_e_order_branch_cedu")
public class OrderBranchCedu
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="code")
	private String code; //编码
	
	@Column(name="remitter")
	private int remitterId; //汇款单位
	
	@Column(name="remittee")
	private int remitteeId; //收款单位
	
	@Column(name="remitter_account")
	private String remitterAccount; //汇款账号
	
	@Column(name="remittee_account")
	private String remitteeAccount; //收款单位账号
	
	@Column(name="amount")
	private BigDecimal amount; //金额
	
	@Column(name="note")
	private String note; //备注
	
	@Column(name="order_no")
	private String orderNo; //汇款单号
	
	@Column(name="remittance_date")
	@Temporal(TemporalType.DATE)
	private Date remittanceDate; //汇款日期
	
	@Column(name="img_url")
	private String imgUrl;
	
	@Column(name="status")
	private int status; //状态
	
	@Column(name="types")
	private int types; //回退状态       1.已提交    2.已回退
	
	@Column(name = "delete_flag")
	private int deleteFlag; // 删除标记
	@Column(name = "creator_id")
	private int creatorId; // 创建人
	@Column(name = "created_time")
	private Date createdTime; // 创建时间
	@Column(name = "updater_id")
	private int updaterId; // 最后修改人
	@Column(name = "updated_time")
	private Date updatedTime; // 最后修改时间
	
	
	@Transient
	private transient String remitterName;
	@Transient
	private transient String remitteeName;

	/********* Getter/Setter *********/
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
	
	public String getRemitterAccount() {
		return remitterAccount;
	}

	public void setRemitterAccount(String remitterAccount) {
		this.remitterAccount = remitterAccount;
	}

	public String getRemitteeAccount() {
		return remitteeAccount;
	}

	public void setRemitteeAccount(String remitteeAccount) {
		this.remitteeAccount = remitteeAccount;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getRemittanceDate() {
		return remittanceDate;
	}

	public void setRemittanceDate(Date remittanceDate) {
		this.remittanceDate = remittanceDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getRemitterId() {
		return remitterId;
	}

	public void setRemitterId(int remitterId) {
		this.remitterId = remitterId;
	}

	public int getRemitteeId() {
		return remitteeId;
	}

	public void setRemitteeId(int remitteeId) {
		this.remitteeId = remitteeId;
	}

	public String getRemitterName() {
		return remitterName;
	}

	public void setRemitterName(String remitterName) {
		this.remitterName = remitterName;
	}

	public String getRemitteeName() {
		return remitteeName;
	}

	public void setRemitteeName(String remitteeName) {
		this.remitteeName = remitteeName;
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

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}
	
}
