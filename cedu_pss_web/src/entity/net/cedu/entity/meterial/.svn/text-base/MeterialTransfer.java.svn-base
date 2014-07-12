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
 * 物料移库
 * @author YY
 *  
 */
@Entity
@Table(name = "tb_e_meterial_transfer", catalog = "cedu_master")
public class MeterialTransfer implements java.io.Serializable {
 
 
	private static final long serialVersionUID = -1408176774871997547L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id" )
	private int id;       //主键id
	
	@Column(name = "from_id" )
	private int fromId;     //移出库房id
	
	@Transient
	transient private String fromname; //移除库房名称
	
	@Transient
	transient private String fromplace;  //移出库房位置
	
	@Column(name = "to_id" )
	private int toId;       //移入库房id
	
	@Transient
	transient private String toname; //移入库房名称
	
	@Transient
	transient private String toplace;  //移除库房位置
	
	@Column(name = "meterial_id" )
	private int meterialId;  //物料id
	
	@Transient
	transient private String meterialname; //物料名称
	
	@Transient
	transient private String meterialfen; //物料名称
	
	@Column(name = "quantity" )
	private int quantity;    //移库数量
	
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

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public String getToname() {
		return toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}

	public int getMeterialId() {
		return meterialId;
	}

	public void setMeterialId(int meterialId) {
		this.meterialId = meterialId;
	}

	public String getMeterialname() {
		return meterialname;
	}

	public void setMeterialname(String meterialname) {
		this.meterialname = meterialname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public String getFromplace() {
		return fromplace;
	}

	public void setFromplace(String fromplace) {
		this.fromplace = fromplace;
	}

	public String getToplace() {
		return toplace;
	}

	public void setToplace(String toplace) {
		this.toplace = toplace;
	}

	public String getMeterialfen() {
		return meterialfen;
	}

	public void setMeterialfen(String meterialfen) {
		this.meterialfen = meterialfen;
	}

 
	
	

}