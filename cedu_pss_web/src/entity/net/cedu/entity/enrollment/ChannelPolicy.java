package net.cedu.entity.enrollment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.hibernate.SortChineseAnnotation;

/**
 * 招生返款政策标准
 */
@Entity
@Table(name="tb_e_policy_channel")
public class ChannelPolicy implements Serializable
{
	private static final long serialVersionUID = 2648708914151354503L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="code")
	private String code; //编码
	
	@Column(name="title")
	@SortChineseAnnotation(sort = true)
	private String title; //名称
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Column(name="branch_id")
	private String branchIds; // 学习中心ID 例如_1_2_
	
//	@Column(name="types")
//	private int type; //类型（招生/非招生）
	
	@Column(name="fee_subject_id")
	private int feeSubjectId; //费用项目
	
	@Column(name="channel_type_id")
	private int channelTypeId; //合作方类别ID
	
	@Column(name="requires_audit")
	private int requiresAudit; //是否需要审批
	
	@Column(name="delete_flag")
	private int deleteFlag; //删除标记
	
	@Column(name="creator_id")
	private int creatorId; //创建人
	
	@Column(name="created_time")
	private Date createdTime; //创建时间
	
	@Column(name="updater_id")
	private int updaterId; //最后修改人
	
	@Column(name="updated_time")
	private Date updatedTime; //最后修改时间
	
	@Transient
	private transient String academyName;
	@Transient
	private transient String branchName;
	@Transient
	transient private int indexcount; //在政策体里被审批过的数量
	
	public String getFeeSubjectName() {
		return feeSubjectName;
	}

	public void setFeeSubjectName(String feeSubjectName) {
		this.feeSubjectName = feeSubjectName;
	}

	public String getChannelTypeName() {
		return channelTypeName;
	}

	public void setChannelTypeName(String channelTypeName) {
		this.channelTypeName = channelTypeName;
	}

	@Transient
	private transient String feeSubjectName;
	
	@Transient
	private transient String channelTypeName;
	
	@Transient
	private transient List<ChannelPolicyDetailStandard> standards;

	public ChannelPolicy() {
	}

	public ChannelPolicy(List<ChannelPolicyDetailStandard> standards) {
		this.standards = standards;
	}

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
	public List<ChannelPolicyDetailStandard> getStandards() {
		return standards;
	}
	public void setStandards(List<ChannelPolicyDetailStandard> standards) {
		this.standards = standards;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public int getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(int channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public int getRequiresAudit() {
		return requiresAudit;
	}

	public void setRequiresAudit(int requiresAudit) {
		this.requiresAudit = requiresAudit;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	

	

	public String getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getIndexcount() {
		return indexcount;
	}

	public void setIndexcount(int indexcount) {
		this.indexcount = indexcount;
	}
	
}
