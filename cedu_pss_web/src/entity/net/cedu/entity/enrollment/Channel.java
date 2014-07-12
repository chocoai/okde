package net.cedu.entity.enrollment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.hibernate.SortChineseAnnotation;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;

/**
 * 渠道
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_e_channel")
public class Channel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "code")
	private String code; // 编码
	@Column(name = "name")
	@SortChineseAnnotation(sort = true)
	private String name; // 名称
	@Column(name = "student_id")
	private int studentId; // 学员Id
	@Column(name = "types")
	private int type; //合作方类别
	@Column(name = "branch_id")
	private int branchId; // 学习中心ID
	@Column(name = "isall")
	private int isAll; //合作方类别
	@Column(name = "linkman")
	private String linkman; // 联系人
	@Column(name = "office_address")
	private String officeAddress; // 办公地址
	@Column(name = "sertificate_type")
	private int sertificateType; // 证件类型
	@Column(name = "sertificate_no")
	private String sertificateNo; // 证件号码
	@Column(name = "telephone")
	private String telephone; // 联系电话
	@Column(name = "account_bank")
	private String accountBank; // 开户行
	@Column(name = "account_name")
	private String accountName; // 开户名
	@Column(name = "account")
	private String account; // 账号
	@Column(name = "contract_no")
	private String contractNo; // 合同编号
	@Column(name = "status")
	private int status; // 合作方审核状态
	@Column(name = "is_using")
	private int isUsing; // 是否启用
	@Column(name = "audit_status")
	private int aduitStatus; // 审核状态
	@Column(name = "auditer")
	private int auditer; // 审核人
	@Column(name = "attachment_name")
	private String attachmentName;  //标题
	@Column(name = "attachment_url")
	private String attachmentUrl;  //附件路径
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
	transient private String statistics; //已审批/总数
	
	@Transient
	transient private String branchName; //学习中心
	
	@Transient
	transient private String channelTypeName; //合作方类别
	
	
	@Transient
	private ChannelPolicyDetail channelpolicydetail; //招生返款政策明细
	
	@Transient
	private ChannelPolicyChannel channelpolicychannel; //合作方与招生返款政策

	
	
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public int getSertificateType() {
		return sertificateType;
	}
	public void setSertificateType(int sertificateType) {
		this.sertificateType = sertificateType;
	}
	public String getSertificateNo() {
		return sertificateNo;
	}
	public void setSertificateNo(String sertificateNo) {
		this.sertificateNo = sertificateNo;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getChannelTypeName() {
		return channelTypeName;
	}
	public void setChannelTypeName(String channelTypeName) {
		this.channelTypeName = channelTypeName;
	}
	public int getAduitStatus() {
		return aduitStatus;
	}
	public void setAduitStatus(int aduitStatus) {
		this.aduitStatus = aduitStatus;
	}
	public int getAuditer() {
		return auditer;
	}
	public void setAuditer(int auditer) {
		this.auditer = auditer;
	}
	public int getIsAll() {
		return isAll;
	}
	public void setIsAll(int isAll) {
		this.isAll = isAll;
	}
	
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentUrl() {
		return attachmentUrl;
	}
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	public int getIsUsing() {
		return isUsing;
	}
	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
	}
	
	public ChannelPolicyDetail getChannelpolicydetail() {
		return channelpolicydetail;
	}
	public void setChannelpolicydetail(ChannelPolicyDetail channelpolicydetail) {
		this.channelpolicydetail = channelpolicydetail;
	}
	public ChannelPolicyChannel getChannelpolicychannel() {
		return channelpolicychannel;
	}
	public void setChannelpolicychannel(ChannelPolicyChannel channelpolicychannel) {
		this.channelpolicychannel = channelpolicychannel;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStatistics() {
		return statistics;
	}
	public void setStatistics(String statistics) {
		this.statistics = statistics;
	}
	
	
	
	
}
