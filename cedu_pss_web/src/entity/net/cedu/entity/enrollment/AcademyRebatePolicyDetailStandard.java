package net.cedu.entity.enrollment;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 院校返款政策标准规则 
 */
@Entity
@Table(name="tb_e_academy_policy_rebates_detail_standard")
public class AcademyRebatePolicyDetailStandard implements Serializable
{
	private static final long serialVersionUID = 6715767027822117160L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="policy_id")
	private int policyId; //政策标准ID
	
	@Column(name="floors")
	private int floor; //人数上限
	
	@Column(name="ceils")
	private int ceil; //人数下限
	
	@Column(name="rebates_id")
	private int valueForm; //返款方式
	
	@Column(name="mondy_value")
	private BigDecimal value; //返款（金额、比率、学分），值：Constants.MONEY_FORM_RATIO / Constants.MONEY_FORM_AMOUNT / Constants.MONEY_FORM_SCORE
//	
//	@Column(name="delete_flag")
//	private int deleteFlag; //删除标记
//	
//	@Column(name="created_time")
//	private Date createdTime; //创建时间
//	
//	@Column(name="updater_id")
//	private int updaterId; //最后修改人
//	
//	@Column(name="updated_time")
//	private Date updatedTime; //最后修改人

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getCeil() {
		return ceil;
	}

	public void setCeil(int ceil) {
		this.ceil = ceil;
	}

	public int getValueForm() {
		return valueForm;
	}

	public void setValueForm(int valueForm) {
		this.valueForm = valueForm;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

//	public int getDeleteFlag() {
//		return deleteFlag;
//	}
//
//	public void setDeleteFlag(int deleteFlag) {
//		this.deleteFlag = deleteFlag;
//	}
//
//	public Date getCreatedTime() {
//		return createdTime;
//	}
//
//	public void setCreatedTime(Date createdTime) {
//		this.createdTime = createdTime;
//	}
//
//	public int getUpdaterId() {
//		return updaterId;
//	}
//
//	public void setUpdaterId(int updaterId) {
//		this.updaterId = updaterId;
//	}
//
//	public Date getUpdatedTime() {
//		return updatedTime;
//	}
//
//	public void setUpdatedTime(Date updatedTime) {
//		this.updatedTime = updatedTime;
//	}
}
