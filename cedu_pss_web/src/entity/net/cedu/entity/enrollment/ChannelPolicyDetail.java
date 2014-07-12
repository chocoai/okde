package net.cedu.entity.enrollment;

import java.io.Serializable;
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

import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;

/**
 * 招生返款政策明细
 */
@Entity
@Table(name="tb_e_policy_channel_detail")
public class ChannelPolicyDetail implements Serializable
{
	
	/**
	 * @date 2011-08-05 11:14
	 */
	private static final long serialVersionUID = -3191242362971684630L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="code")
	private String code; //编码
	
	@Column(name="policy_channel_id")
	private int policyId; //政策ID
	
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Column(name="batch_id")
	private int batchId; //院校招生批次
	
	@Column(name="level_id")
	private int levelId; //层次
	
	@Column(name="major_id")
	private int majorId; //专业
	
	@Column(name="branch_id")
	private int branchId; //学习中心
	
	@Column(name="channel_id")
	private int channelId; //合作方ID
	
	@Column(name="fee_subject_id")
	private int feeSubjectId; //费用项目
	
	@Column(name="is_approval")
	private int isApprival; //是否需要审批
	
	@Column(name="audit_status")
	private int auditStatus; //审核状态
	
	@Column(name="auditer")
	private int auditer; //审核人
	
	@Column(name="audit_date")
	@Temporal(TemporalType.DATE)
	private Date auditDate; //审核日期
	
	@Column(name="is_using")
	private int enable; //是否启用
	
	@Column(name="orders")
	private int order; //使用优先顺序(序号小的先使用,从1开始,可不联系)
	
	@Column(name="delete_flag")
	private int deleteFlag; //删除标记
	
	@Column(name="creator_id")
	private int creatorId; //创建者
	
	@Column(name="created_time")
	private Date createdTime; //创建时间
	
	@Column(name="updater_id")
	private int updaterId; //最后修改者
	
	@Column(name="updated_time")
	private Date updatedTime; //最后修改时间
	
	@Transient
	transient private Academy academy;   //院校
	@Transient
	private transient String academyName;
	
	@Transient
	transient private ChannelPolicy channelpolicy;   //政策
	
//	@Transient
//	transient private ChannelPolicyDetailStandard channelpolicydetailstandard;   //政策标准规则
	
	@Transient
	transient private AcademyEnrollBatch academyenrollbatch;  //院校批次
	
	@Transient
	transient private Level level;  //层次
	@Transient
	private transient String levelName;
	@Transient
	transient private Major major;  //专业
	@Transient
	private transient String majorName;
	@Transient
	transient private EnrollmentSource enrollmentsource;  //招生途径
	@Transient
	private transient AcademyEnrollBatch batch;
	@Transient
	private transient String batchName;
	@Transient
	private transient String feeSubjectName;
	@Transient
	private transient FeeSubject feeSubject;
	@Transient
	private transient Channel channel;
	@Transient
	private transient String channelName;
	
	@Transient
	private transient ChannelPolicyDetailStandard matchedStandard;
	
	//////////////////////// Getter/Setter /////////////////////////////////
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getAuditer() {
		return auditer;
	}

	public void setAuditer(int auditer) {
		this.auditer = auditer;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
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

	public int getIsApprival() {
		return isApprival;
	}

	public void setIsApprival(int isApprival) {
		this.isApprival = isApprival;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}


	public AcademyEnrollBatch getAcademyenrollbatch() {
		return academyenrollbatch;
	}

	public void setAcademyenrollbatch(AcademyEnrollBatch academyenrollbatch) {
		this.academyenrollbatch = academyenrollbatch;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public ChannelPolicy getChannelpolicy() {
		return channelpolicy;
	}

	public void setChannelpolicy(ChannelPolicy channelpolicy) {
		this.channelpolicy = channelpolicy;
	}

	public EnrollmentSource getEnrollmentsource() {
		return enrollmentsource;
	}

	public void setEnrollmentsource(EnrollmentSource enrollmentsource) {
		this.enrollmentsource = enrollmentsource;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	public AcademyEnrollBatch getBatch() {
		return batch;
	}

	public void setBatch(AcademyEnrollBatch batch) {
		this.batch = batch;
	}
	
	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getFeeSubjectName() {
		return feeSubjectName;
	}

	public void setFeeSubjectName(String feeSubjectName) {
		this.feeSubjectName = feeSubjectName;
	}

	public FeeSubject getFeeSubject() {
		return feeSubject;
	}

	public void setFeeSubject(FeeSubject feeSubject) {
		this.feeSubject = feeSubject;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public ChannelPolicyDetailStandard getMatchedStandard() {
		return matchedStandard;
	}

	public void setMatchedStandard(ChannelPolicyDetailStandard matchedStandard) {
		this.matchedStandard = matchedStandard;
	}
}
