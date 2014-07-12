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
import net.cedu.entity.basesetting.GlobalEnrollBatch;

/**
 * 院校<b>招生/学籍</b>批次
 */
@Entity
@Table(name="tb_e_academy_enroll_batch")
public class AcademyEnrollBatch implements Serializable
{

	private static final long serialVersionUID = -5493330161017067798L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
//	@Column(name="code")
//	private String code; //编码
	
	@Column(name="enrollment_name")
	@SortChineseAnnotation(sort = true)
	private String enrollmentName; //院校招生批次名称
	
	@Column(name="register_name")
	@SortChineseAnnotation(sort = true)
	private String registerName; //院校学籍批次名称
	
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Column(name="global_enroll_batch_id")
	private int globalEnrollBatchId; //全局招生批次ID
	
	@Column(name="is_enable")
	private int isEnable; //启用状态   0-停用 1-启用 2-结束状态
	
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
	transient private GlobalEnrollBatch globalEnrollBatch;//全局招生批次实体
	
	@Transient 
	transient private List<SubAcademyEnrollBatch> subAcademyEnrollBatchlist;//院校招生子批次list
	
	@Transient
	transient private List<AcademyLevel> academyLevelList;//层次（院校批次）
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	
//	public String getPeriod() {
//		return period;
//	}
//
//	public void setPeriod(String period) {
//		this.period = period;
//	}

	public int getGlobalEnrollBatchId() {
		return globalEnrollBatchId;
	}

	public void setGlobalEnrollBatchId(int globalEnrollBatchId) {
		this.globalEnrollBatchId = globalEnrollBatchId;
	}

	public int getIsEnable() {
		return isEnable;
	}

//	public Date getBeginDate() {
//		return beginDate;
//	}
//
//	public void setBeginDate(Date beginDate) {
//		this.beginDate = beginDate;
//	}
//
//	public Date getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
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

	public String getEnrollmentName() {
		return enrollmentName;
	}

	public void setEnrollmentName(String enrollmentName) {
		this.enrollmentName = enrollmentName.replaceAll("[\\s]", "");
	}

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName.replaceAll("[\\s]", "");
	}

	public GlobalEnrollBatch getGlobalEnrollBatch() {
		return globalEnrollBatch;
	}

	public void setGlobalEnrollBatch(GlobalEnrollBatch globalEnrollBatch) {
		this.globalEnrollBatch = globalEnrollBatch;
	}

	public List<SubAcademyEnrollBatch> getSubAcademyEnrollBatchlist() {
		return subAcademyEnrollBatchlist;
	}

	public void setSubAcademyEnrollBatchlist(
			List<SubAcademyEnrollBatch> subAcademyEnrollBatchlist) {
		this.subAcademyEnrollBatchlist = subAcademyEnrollBatchlist;
	}

	public List<AcademyLevel> getAcademyLevelList() {
		return academyLevelList;
	}

	public void setAcademyLevelList(List<AcademyLevel> academyLevelList) {
		this.academyLevelList = academyLevelList;
	}
	
}
