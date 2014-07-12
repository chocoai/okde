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

/**
 * 招生指标_总指标
 * @author gaolei
 */
@Entity
@Table(name="tb_e_enroll_quota")
public class EnrollQuota implements Serializable
{
	private static final long serialVersionUID = -5308599405933447949L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; //主键ID
	
	@Column(name="enroll_year")
	private int enrollYear; //年度
	
	@Column(name="quota")
	private int quota; //招生总指标
	

	
	@Column(name="types")
	private int types; //分配状态
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
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

	public int getEnrollYear() {
		return enrollYear;
	}

	public void setEnrollYear(int enrollYear) {
		this.enrollYear = enrollYear;
	}

	
	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}
	
	
	
}
