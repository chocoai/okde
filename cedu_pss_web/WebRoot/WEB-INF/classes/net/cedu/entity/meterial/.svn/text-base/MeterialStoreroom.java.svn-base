package net.cedu.entity.meterial;

 
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 物料库房设置
 * @author YY
 *  
 */
@Entity
@Table(name="tb_e_meterial_storeroom"
    ,catalog="cedu_master"
)

public class MeterialStoreroom  implements java.io.Serializable {

	private static final long serialVersionUID = -4123762958499282043L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id" )
	private int id;       //主键id
	
	@Column(name = "code" )
     private String code;   //编号
	
	@Column(name = "name" )
     private String name;   //库房名称
	
	@Column(name = "position" )
     private int position;  //库房位置
	
	@Transient
	transient private String positionName; //库房位置名称
	
	@Column(name = "types" )
     private int types;  //库房类型
	
 	@Column(name = "delete_flag")
	private int deleteFlag;  //删除标记
	
	@Column(name = "creator_id")
	private int creatorId;   //创建人
	
	@Column(name = "created_time")
	private Date createdTime=new Date();//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;    //最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间

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

 
	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	




}