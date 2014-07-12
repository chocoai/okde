package net.cedu.entity.finance;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 应收院校款
 * 
 * @author Sauntor
 *
 */
@Entity
@Table(name="tb_e_planed_academy_bill")
public class PlanedAcademyBill
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Column(name="branch_id")
	private int branchId; //学习中心Id
	
	@Column(name="academy_rebate_id")
	private int academyRebateId; //院校返款单ID
	
	@Column(name="received_way")
	private int receivedWay; //收款类型
	
	@Column(name="amount")
	private BigDecimal amount; //应收金额
	
	@Column(name="note")
	private String note; //备注
	
	@Column(name="attachment")
	private String attachmentPath; //附件
	
	@Column(name="is_rebate")
	private int isRebate; //是否返款
	
	@Column(name="delete_flag")
	private int deleteFlag; //删除标记
	
	@Column(name="creator_id")
	private int creatorId; //创建人
	
	@Column(name="created_time")
	private Date createdTime; //创建时间
	
	@Column(name="updater_id")
	private int updaterId; //更新人
	
	@Column(name="updated_time")
	private Date updatedTime; //最后更新时间
	
	@Transient
	private transient String academyName;
	@Transient
	private transient String receivedWayName;
	
	@Transient
	private transient String branchName;
	
///////////////////// Getter/Setter /////////////////////////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAcademyId() {
		return academyId;
	}
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	public int getReceivedWay() {
		return receivedWay;
	}
	public void setReceivedWay(int receivedWay) {
		this.receivedWay = receivedWay;
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
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public int getIsRebate() {
		return isRebate;
	}
	public void setIsRebate(int isRebate) {
		this.isRebate = isRebate;
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
	public String getAcademyName() {
		return academyName;
	}
	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}
	public String getReceivedWayName() {
		return receivedWayName;
	}
	public void setReceivedWayName(String receivedWayName) {
		this.receivedWayName = receivedWayName;
	}
	public int getAcademyRebateId() {
		return academyRebateId;
	}
	public void setAcademyRebateId(int academyRebateId) {
		this.academyRebateId = academyRebateId;
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
	
	
}
