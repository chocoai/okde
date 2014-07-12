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
import net.cedu.entity.academy.Academy;

/**
 * 院校收费政策标准
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_e_policy_fee")
public class PolicyFee implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "code")
	private String code; // 编码
	@Column(name = "title")
	@SortChineseAnnotation(sort=true)
	private String title; // 标题
	@Column(name = "academy_id")
	private int academyId; // 院校ID
	@Column(name = "mode_id")
	private int modeId; // 学制Id
	@Column(name = "fee_subject_id")
	private int feeSubjectId; // 费用科目ID
	
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
	transient private String academyname; //院校名称
	@Transient
	transient private String modename;//学制名称
	@Transient
	transient private String feeSubjectname;//费用科目名称
	@Transient
	transient private String feestandardes;//收费标准集合显示
	
	@Transient
	transient private int indexcount; //在政策体里被审批过的数量
	
	@Transient
	transient private int policyFeeDetail; //收费政策Id

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

	public int getModeId() {
		return modeId;
	}

	public void setModeId(int modeId) {
		this.modeId = modeId;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
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

	public String getAcademyname() {
		return academyname;
	}

	public void setAcademyname(String academyname) {
		this.academyname = academyname;
	}

	public String getModename() {
		return modename;
	}

	public void setModename(String modename) {
		this.modename = modename;
	}

	public String getFeeSubjectname() {
		return feeSubjectname;
	}

	public void setFeeSubjectname(String feeSubjectname) {
		this.feeSubjectname = feeSubjectname;
	}

	public String getFeestandardes() {
		return feestandardes;
	}

	public void setFeestandardes(String feestandardes) {
		this.feestandardes = feestandardes;
	}

	public int getPolicyFeeDetail() {
		return policyFeeDetail;
	}

	public void setPolicyFeeDetail(int policyFeeDetail) {
		this.policyFeeDetail = policyFeeDetail;
	}

	public int getIndexcount() {
		return indexcount;
	}

	public void setIndexcount(int indexcount) {
		this.indexcount = indexcount;
	}	
	
}
