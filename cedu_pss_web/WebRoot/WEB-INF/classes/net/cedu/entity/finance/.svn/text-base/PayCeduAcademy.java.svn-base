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

import net.cedu.common.Constants;

import org.apache.struts2.json.annotations.JSON;

/**
 * 院校打款单
 * 
 * @author Sauntor
 *
 */
@Entity
@Table(name="tb_e_pay_cedu_academy")
public class PayCeduAcademy
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="code")
	private String code; //编号
	
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
	
	@Column(name="remittance_no")
	private String remittanceNo; //汇款单号
	
	@Column(name="remittance_date")
	@Temporal(TemporalType.DATE)
	private Date remittanceDate; //汇款日期
	
	@Column(name="uploaded_bill")
	private String uploadedBillPath; //上传单据
	
	@Column(name="status")
	private int status; //状态
	
	@Column(name="types")
	private int types; //回退状态       1.已提交    2.已回退
	
	@Column(name="pay_academy_cedu_id")
	private int payAcademyCeduId; //返款单ID
	
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
	
	@Transient
	private double moneyToCedu; //应返款金额
	
	@Transient
	private transient int ceduId;//总部Id
	
	//////////////////// Getter/Setter ////////////////////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRemittanceNo() {
		return remittanceNo;
	}
	public void setRemittanceNo(String remittanceNo) {
		this.remittanceNo = remittanceNo;
	}
	@JSON(format=Constants.DATE_FORMAT)
	public Date getRemittanceDate() {
		return remittanceDate;
	}
	public void setRemittanceDate(Date remittanceDate) {
		this.remittanceDate = remittanceDate;
	}
	public String getUploadedBillPath() {
		return uploadedBillPath;
	}
	public void setUploadedBillPath(String uploadedBillPath) {
		this.uploadedBillPath = uploadedBillPath;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public double getMoneyToCedu() {
		return moneyToCedu;
	}
	public void setMoneyToCedu(double moneyToCedu) {
		this.moneyToCedu = moneyToCedu;
	}
	public int getPayAcademyCeduId() {
		return payAcademyCeduId;
	}
	public void setPayAcademyCeduId(int payAcademyCeduId) {
		this.payAcademyCeduId = payAcademyCeduId;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getTypes() {
		return types;
	}
	public void setTypes(int types) {
		this.types = types;
	}
	public int getCeduId() {
		return ceduId;
	}
	public void setCeduId(int ceduId) {
		this.ceduId = ceduId;
	}
	
}
