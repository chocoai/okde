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
 * 采购单明细
 * 
 * @author YY
 * 
 */
@Entity
@Table(name = "tb_e_meterial_purchase_detail", catalog = "cedu_master")
public class MeterialPurchaseDetail implements java.io.Serializable {
 
	private static final long serialVersionUID = -723793902907216599L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id; // 主键id

	@Column(name = "purchase_id")
	private int purchaseId; // 采购单id

	@Column(name = "meterial_id")
	private int meterialId; // 物料id
	
	@Transient
	transient private String meterialname; //物料名称

	@Column(name = "price", precision = 32, scale = 3)
	private Double price; // 单价

	@Column(name = "total")
	private int total; // 数量

	@Column(name = "delete_flag")
	private int deleteFlag; // 删除标记

	@Column(name = "creator_id")
	private int creatorId; // 创建人

	@Column(name = "created_time")
	private Date createdTime=new Date();// 创建时间

	@Column(name = "updater_id")
	private int updaterId; // 最后修改人

	@Column(name = "updated_time")
	private Date updatedTime;// 最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 

	public Integer getMeterialId() {
		return meterialId;
	}

	public void setMeterialId(Integer meterialId) {
		this.meterialId = meterialId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
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

	public String getMeterialname() {
		return meterialname;
	}

	public void setMeterialname(String meterialname) {
		this.meterialname = meterialname;
	}

	 

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public void setMeterialId(int meterialId) {
		this.meterialId = meterialId;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}