package net.cedu.entity.enrollment;

import java.io.Serializable;
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

import org.apache.struts2.json.annotations.JSON;

/**
 * 学生优惠政策标准
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_e_student_discount_policy")
public class StudentDiscountPolicy implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "code")
	private String code; // 编码
	@Column(name = "title")
	private String title; // 标题
	@Column(name = "academy_id")
	private int academyId; // 院校ID
	
	@Column(name = "fee_subject_id")
	private int feeSubjectId; // 费用项目Id
	@Column(name = "fee_payment_id")
	private int feePaymentId; // 缴费次数Id
	@Column(name = "discount_way")
	private int discountWayId; // 优惠方式
	@Column(name = "target_object")
	private int targetObjectId; // 优惠主体
	@Column(name = "activity_begin_date")
	private Date activityBeginDate; // 活动起始日期
	@Column(name = "activity_end_date")
	private Date activityEndDate; // 活动结束日期
	@Column(name = "use_begin_date")
	private Date useBeginDate; // 使用起始日期
	@Column(name = "use_end_date")
	private Date useEndDate; // 使用结束日期
	@Column(name = "money")
	private BigDecimal money; // 金额/比例
	@Column(name = "mutable")
	private int mutable; // 渐变或者固定     1.固定   2.渐变
	@Column(name = "delta")
	private double delta; // 渐变差量
	@Column(name = "is_application_needed")
	private int isApplicationNeeded; // 是否需要申请    1.是    2.否	
	
	@Column(name = "is_shared")
	private int isShared; // 是否院校与弘成共同承担    1.是    0.否	
	
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
	transient private String feesubjectname;//费用项名称
	@Transient
	transient private String academyname;//院校名称
	
	@Transient
	transient private String discountstandard;//优惠标准
	
	@Transient
	transient private String targetObjectName;//优惠主体名称
	
	@Transient
	transient private int indexcount; //优惠卷被使用的数量
	
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAcademyId() {
		return academyId;
	}
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	
	public int getFeeSubjectId() {
		return feeSubjectId;
	}
	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
	public int getFeePaymentId() {
		return feePaymentId;
	}
	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}
	public int getDiscountWayId() {
		return discountWayId;
	}
	public void setDiscountWayId(int discountWayId) {
		this.discountWayId = discountWayId;
	}
	public int getTargetObjectId() {
		return targetObjectId;
	}
	public void setTargetObjectId(int targetObjectId) {
		this.targetObjectId = targetObjectId;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getActivityBeginDate() {
		return activityBeginDate;
	}
	public void setActivityBeginDate(Date activityBeginDate) {
		this.activityBeginDate = activityBeginDate;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getActivityEndDate() {
		return activityEndDate;
	}
	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getUseBeginDate() {
		return useBeginDate;
	}
	public void setUseBeginDate(Date useBeginDate) {
		this.useBeginDate = useBeginDate;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getUseEndDate() {
		return useEndDate;
	}
	public void setUseEndDate(Date useEndDate) {
		this.useEndDate = useEndDate;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public int getMutable() {
		return mutable;
	}
	public void setMutable(int mutable) {
		this.mutable = mutable;
	}
	public double getDelta() {
		return delta;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}
	public int getIsApplicationNeeded() {
		return isApplicationNeeded;
	}
	public void setIsApplicationNeeded(int isApplicationNeeded) {
		this.isApplicationNeeded = isApplicationNeeded;
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
	public String getFeesubjectname() {
		return feesubjectname;
	}
	public void setFeesubjectname(String feesubjectname) {
		this.feesubjectname = feesubjectname;
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
	public int getIsShared() {
		return isShared;
	}
	public void setIsShared(int isShared) {
		this.isShared = isShared;
	}
	public String getTargetObjectName() {
		return targetObjectName;
	}
	public void setTargetObjectName(String targetObjectName) {
		this.targetObjectName = targetObjectName;
	}
	public int getIndexcount() {
		return indexcount;
	}
	public void setIndexcount(int indexcount) {
		this.indexcount = indexcount;
	}
	
	
}
