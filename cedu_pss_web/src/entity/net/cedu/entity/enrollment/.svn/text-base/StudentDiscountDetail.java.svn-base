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

import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;

/**
 * 学生优惠政策
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_e_student_discount_detail")
public class StudentDiscountDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "code")
	private String code; //编码
	@Column(name = "academy_id")
	private int academyId; // 院校Id
	@Column(name = "discount_policy_id")
	private String discountPolicyId; // 政策标准Id
	@Column(name = "batch_id")
	private int batchId; // 批次(单)	
	@Column(name = "branch_id")
	private int branchId; // 学习中心(单)
	@Column(name = "level_id")
	private int levelId; // 层次(单)
	@Column(name = "major_id")
	private int majorId; // 专业(单)
	@Column(name = "fee_subject_id")
	private int feeSubjectId; // 费用项目ID(单选)
	@Column(name = "discount_type")
	private int discountType; // 类别      1:报名前政策   2：报名后政策
	@Column(name = "channel_type")
	private int channelType; // 合作方类别Id (招生途径)
	@Column(name = "channel_id")
	private int channelId; // 合作方Id
	@Column(name = "fee_count_id")
	private int feeCountId; // 缴费次数Id
	//新加字段2012-01-15
	@Column(name = "enrollment_way")
	private int enrollmentWay;// 市场途径
	@Column(name = "student_data_source")
	private int studentDataSource;// 数据来源
	
	@Column(name = "audit_status")
	private int aduitStatus; // 审核状态
	@Column(name = "auditer")
	private int auditer; // 审核人
	@Column(name = "audit_date")
	private Date auditDate; // 审核时间
	@Column(name = "is_using")
	private int isUsing; // 是否启用
	
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
	transient private String academyname;//院校名称
	@Transient
	transient private String batchname;//批次名称
	@Transient
	transient private String levelname;//层次名称
	@Transient
	transient private String majorname;//专业
	@Transient
	transient private String feesubjectname;//费用项名称
	@Transient
	transient private String branchname;//学习中心
	@Transient
	transient private String channeltypename;//合作方类别名称
	@Transient
	transient private String channelname;//合作方名称
	@Transient
	transient private String discountstandard;//优惠标准
	
	
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
	public int getAcademyId() {
		return academyId;
	}
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	
	public String getDiscountPolicyId() {
		return discountPolicyId;
	}
	public void setDiscountPolicyId(String discountPolicyId) {
		this.discountPolicyId = discountPolicyId;
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
	
	public int getFeeSubjectId() {
		return feeSubjectId;
	}
	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
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
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public int getIsUsing() {
		return isUsing;
	}
	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
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
	public String getBatchname() {
		return batchname;
	}
	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}
	public String getLevelname() {
		return levelname;
	}
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
	
	public String getFeesubjectname() {
		return feesubjectname;
	}
	public void setFeesubjectname(String feesubjectname) {
		this.feesubjectname = feesubjectname;
	}
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	public int getDiscountType() {
		return discountType;
	}
	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}
	public String getMajorname() {
		return majorname;
	}
	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public int getChannelType() {
		return channelType;
	}
	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public int getFeeCountId() {
		return feeCountId;
	}
	public void setFeeCountId(int feeCountId) {
		this.feeCountId = feeCountId;
	}
	public String getChanneltypename() {
		return channeltypename;
	}
	public void setChanneltypename(String channeltypename) {
		this.channeltypename = channeltypename;
	}
	public String getChannelname() {
		return channelname;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	public String getAcademyname() {
		return academyname;
	}
	public void setAcademyname(String academyname) {
		this.academyname = academyname;
	}
	public String getDiscountstandard() {
		return discountstandard;
	}
	public void setDiscountstandard(String discountstandard) {
		this.discountstandard = discountstandard;
	}
	public int getEnrollmentWay() {
		return enrollmentWay;
	}
	public void setEnrollmentWay(int enrollmentWay) {
		this.enrollmentWay = enrollmentWay;
	}
	public int getStudentDataSource() {
		return studentDataSource;
	}
	public void setStudentDataSource(int studentDataSource) {
		this.studentDataSource = studentDataSource;
	}
	
	
	
	
}
