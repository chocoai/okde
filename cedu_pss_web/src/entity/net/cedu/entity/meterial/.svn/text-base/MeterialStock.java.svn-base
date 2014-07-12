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
 * 物料库存
 * @author YY
 *  
 */
@Entity
@Table(name = "tb_e_meterial_stock", catalog = "cedu_master")
public class MeterialStock implements java.io.Serializable {
 
 
	private static final long serialVersionUID = 3341839337495655772L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id" )
	private int id;       //主键id
	
	@Column(name = "storeroom_id")
	private int storeroomId;   //库房id
	
	@Transient 
	transient private String storeroomname; //库房名称
	@Transient 
	transient private String storeroomweizhi; //库房名称
	
	@Column(name="meterial_id")
	private int meterialId;   //物料id
	
	@Transient 
	transient private String meterialname; //物料名称
	
	@Transient 
	transient private String meterialpirce; //物料价格
	
	@Column(name = "quantity")
	private int quantity;     //库存数量
	@Column(name = "amount")
	private Double amount;   //总价
	@Column(name = "delete_flag")
	private int deleteFlag;  //删除标记
	
	@Column(name = "creator_id")
	private int creatorId;   //创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间
	
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

	public int getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(int storeroomId) {
		this.storeroomId = storeroomId;
	}

	public String getStoreroomname() {
		return storeroomname;
	}

	public void setStoreroomname(String storeroomname) {
		this.storeroomname = storeroomname;
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

	public String getMeterialpirce() {
		return meterialpirce;
	}

	public void setMeterialpirce(String meterialpirce) {
		this.meterialpirce = meterialpirce;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public String getStoreroomweizhi() {
		return storeroomweizhi;
	}

	public void setStoreroomweizhi(String storeroomweizhi) {
		this.storeroomweizhi = storeroomweizhi;
	}

 

}