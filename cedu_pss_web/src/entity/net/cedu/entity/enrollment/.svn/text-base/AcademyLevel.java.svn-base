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

import net.cedu.entity.basesetting.Level;

/**
 * 层次（院校批次）
 * @author hxj
 *
 */
@Entity
@Table(name="tb_e_academy_level")
public class AcademyLevel implements Serializable{

	private static final long serialVersionUID = 9129771922746674767L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name = "code")
	private String code;//编码
	
	@Column(name = "level_id")
	private int levelId;//层次ID(基础数据)
	
	@Column(name = "academy_enroll_batch_id")
	private int academyEnrollBatchId;//院校招生批次ID
	
	@Column(name = "delete_flag")
	private int deleteFlag;//删除标记    0-未删除,1-已删除
	
	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间

	@Transient  
	transient private Level level;//基础数据层次实体
	
	@Transient  
	transient private AcademyEnrollBatch academyEnrollBatch;//院校招生批次
	
	@Transient
	transient private String academyMajorNames;//显示一组专业（院校批次）
	
	@Transient
	transient private String levelName;//层次名称
	
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
		this.code = code.replaceAll("[\\s]", "");
	}


	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	

	public int getAcademyEnrollBatchId() {
		return academyEnrollBatchId;
	}

	public void setAcademyEnrollBatchId(int academyEnrollBatchId) {
		this.academyEnrollBatchId = academyEnrollBatchId;
	}

	public AcademyEnrollBatch getAcademyEnrollBatch() {
		return academyEnrollBatch;
	}

	public void setAcademyEnrollBatch(AcademyEnrollBatch academyEnrollBatch) {
		this.academyEnrollBatch = academyEnrollBatch;
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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getAcademyMajorNames() {
		return academyMajorNames;
	}

	public void setAcademyMajorNames(String academyMajorNames) {
		this.academyMajorNames = academyMajorNames;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


}
