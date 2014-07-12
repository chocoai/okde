package net.cedu.entity.enrollment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 院校<b>学籍</b>批次
 */
@Entity
@Table(name="tb_e_academy_register_batch")
public class AcademyRegisterBatch
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="code")
	private String code; //编码
	
	@Column(name="name")
	private String name; //批次名称
	
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Column(name="global_register_batch")
	private int globalRegisterBatch; //全局学籍批次ID
	
	@Column(name="start_time")
	private Date startTime; //开始时间
	
	@Column(name="end_time")
	private Date endTime; //结束时间
	
	@Column(name="is_enable")
	private int isEnable; //启用状态
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getGlobalRegisterBatch() {
		return globalRegisterBatch;
	}

	public void setGlobalRegisterBatch(int globalRegisterBatch) {
		this.globalRegisterBatch = globalRegisterBatch;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getIsEnable() {
		return isEnable;
	}

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
}
