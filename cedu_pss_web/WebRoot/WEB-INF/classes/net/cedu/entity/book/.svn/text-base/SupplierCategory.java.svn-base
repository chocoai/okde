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
 * 书商分类
 * @author XFY
 *
 */
@Entity
@Table(name="tb_e_supplier_category")
public class SupplierCategory implements Serializable {
 
	private static final long serialVersionUID = 3474687778862940741L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;           //主键id 
	
	@Column(name="code")
    private String code;      //编码
    
	@Column(name="name")
    private String name;     //书商分类名称
    
	@Column(name="delete_flag")
    private int deleteFlag;     //删除标记
    
	@Column(name="creator_id")
    private int creatorId;      //创建人
    
	@Column(name="created_time")
    private Date createdTime;   //创建时间
    
	@Column(name="updater_id")
    private int updaterId;     //最后修改人
    
	@Column(name="updated_time")
    private Date updatedTime;  //最后修改人

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
