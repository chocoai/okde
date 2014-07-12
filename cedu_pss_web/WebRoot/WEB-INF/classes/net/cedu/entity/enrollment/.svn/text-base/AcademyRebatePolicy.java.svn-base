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
import net.cedu.entity.academy.Academy;

/**
 * 院校返款政策标准
 */
@Entity
@Table(name="tb_e_academy_policy_rebates")
public class AcademyRebatePolicy implements Serializable
{
	private static final long serialVersionUID = 231808776107045475L;
	
	/**
	 * 对应academyId属性，代表所有院校
	 */
	public static final int ACADEMY_ID_ALL = 0;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	private String code; //编码
	
	@Column(name="title")
	@SortChineseAnnotation(sort = true)
	private String title; //标题
	
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Transient
	private String academyName; //院校名称
	
	@Transient
	private transient Academy academy; //院校详细信息
	
//	@Column(name="policy_type")
//	private int valueForm; //返款方式
	
	@Column(name="fee_subject")
	private int feeSubjectId; //费用项目ID
	
	@Transient
	private transient String feeSubjectName; //费用项目名称

	@Column(name="delete_flag")
	private int deleteFlag; //删除标记

	@Column(name="creator_id")
	private int creatorId; //创建人
	
	@Column(name="created_time")
	private Date createdTime; //创建时间
	
	@Column(name="updater_id")
	private int updaterId; //最后修改人
	
	@Column(name="updated_time")
	private Date updatedTime; //最后修改人
	
	@Transient
	private transient List<AcademyRebatePolicyDetailStandard> standards; //标准规则
	
	@Transient
	transient private int indexcount; //在政策体里被审批过的数量
	
	public AcademyRebatePolicy() {
	}
	public AcademyRebatePolicy(List<AcademyRebatePolicyDetailStandard> standards) {
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
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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
	public int getFeeSubjectId() {
		return feeSubjectId;
	}
	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
//	public int getValueForm() {
//		return valueForm;
//	}
//	public void setValueForm(int valueForm) {
//		this.valueForm = valueForm;
//	}
	public String getAcademyName() {
		return academyName;
	}
	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}
	public List<AcademyRebatePolicyDetailStandard> getStandards() {
		return standards;
	}
	public void setStandards(List<AcademyRebatePolicyDetailStandard> standards) {
		this.standards = standards;
	}
	public String getFeeSubjectName() {
		return feeSubjectName;
	}
	public void setFeeSubjectName(String feeSubjectName) {
		this.feeSubjectName = feeSubjectName;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public int getIndexcount() {
		return indexcount;
	}
	public void setIndexcount(int indexcount) {
		this.indexcount = indexcount;
	}
	
}
