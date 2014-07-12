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

/**
 * 专业(院校)
 */
@Entity
@Table(name="tb_e_major")
public class Major implements Serializable
{
	private static final long serialVersionUID = 845786308120055682L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="code")
	private String code; //编码
	
	@Column(name="name")
	@SortChineseAnnotation(sort = true)
	private String name; //专业名称
	
	@Column(name="academy_id")
	private int academyId; //院校ID
	
	@Column(name="belong_base_major_id")
	private int belongBaseMajorId;//所属基础设置专业ID
	
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
	transient String basemajorname;//所属基础设置专业name
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.replaceAll("[\\s]", "");
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
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

	public int getBelongBaseMajorId() {
		return belongBaseMajorId;
	}

	public void setBelongBaseMajorId(int belongBaseMajorId) {
		this.belongBaseMajorId = belongBaseMajorId;
	}

	public String getBasemajorname() {
		return basemajorname;
	}

	public void setBasemajorname(String basemajorname) {
		this.basemajorname = basemajorname;
	}
	
}
