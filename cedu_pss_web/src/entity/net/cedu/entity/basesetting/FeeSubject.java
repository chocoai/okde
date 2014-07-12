package net.cedu.entity.basesetting;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 基础设置--费用科目
 * @author HXJ
 *
 */
@Entity
@Table(name = "tb_e_fee_subject")
public class FeeSubject implements Serializable{
	
	private static final long serialVersionUID = 1134788813982242809L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;//主键ID
	
	@Column(name = "code")
	private String code;//编码
	
	@Column(name = "name")
	private String name;//费用科目名称
	
	@Column(name = "batch_type")
	private int batchType;//批次类型 1-招生阶段 0-非招生阶段
	
	@Column(name="is_multiple_payment")
	private int isMultiplePayment;//是否多次缴费  0-单次  1-多次
	
	@Column(name = "delete_flag")
	private int deleteFlag;//删除标记    0-未删除,1-已删除
	
	@Column(name = "creator_id")
	private int creatorId;//创建人
	
	@Column(name = "created_time")
	private Date createdTime;//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间
	
	@Column(name="is_control")
	private int isControl;//是否受学分学年制控制
	
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

	public int getBatchType() {
		return batchType;
	}

	public void setBatchType(int batchType) {
		this.batchType = batchType;
	}

	public int getIsMultiplePayment() {
		return isMultiplePayment;
	}

	public void setIsMultiplePayment(int isMultiplePayment) {
		this.isMultiplePayment = isMultiplePayment;
	}

	public int getIsControl() {
		return isControl;
	}

	public void setIsControl(int isControl) {
		this.isControl = isControl;
	}
}
