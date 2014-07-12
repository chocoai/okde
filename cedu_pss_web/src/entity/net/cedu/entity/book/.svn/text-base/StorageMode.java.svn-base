package net.cedu.entity.book;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 入库方式
 * @author XFY
 *
 */
@Entity
@Table(name="tb_e_storage_mode")
public class StorageMode implements Serializable {
 
	private static final long serialVersionUID = -9157782868057615855L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;//主键id
	
	@Column(name="code")
	private String code;//编码
	
	@Column(name="name")
	private String name;//入库方式名称 
	
	@Column(name="delete_flag")
    private int deleteFlag; 	//删除标记
	
	@Column(name="creator_id")
    private int creatorId;     //创始人
	
	@Column(name="created_time")
    private Date createdTime;  //创建时间
	
	@Column(name="updater_id")
    private int updater_id;     //最后修改人
	
	@Column(name="updated_time")
    private Date updatedTime;  //最后修改时间

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

	public int getUpdater_id() {
		return updater_id;
	}

	public void setUpdater_id(int updaterId) {
		updater_id = updaterId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
