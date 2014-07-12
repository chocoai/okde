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
 * 领用单明细
 * 
 * @author YY
 */
@Entity
@Table(name = "tb_e_mur_detail", catalog = "cedu_master")
public class MurDetail implements java.io.Serializable {
 
	private static final long serialVersionUID = -1198409871424218138L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id; // 主键id

	@Column(name = "application_id")
	private int applicationId; // 领用单id

	@Column(name = "meterial_id")
	private int meterialId; // 物料id
	
	@Transient
	transient private String meterialname; //物料名称
	

	@Column(name = "price")
	private Double price; // 单价

	@Column(name = "amount")
	private int amount; // 数量

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

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
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

	 
	
	

}