package net.cedu.entity.enrollment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 招生返款政策标准规则
 * @author gaolei
 */
@Entity
@Table(name="tb_e_policy_channel_detail_standard")
public class ChannelPolicyDetailStandard implements Serializable
{
	
	/**
	 * @date 2011-08-05 11:14
	 */
	private static final long serialVersionUID = -3191242362971684630L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="policy_id")
	private int policyId; //政策标准ID
	
	@Column(name="enrollment_floor")
	private int enrollmentFloor; //招生人数下限
	
	@Column(name="enrollment_ceil")
	private int enrollmentCeil; //招生人数上限
	
	@Column(name="rebates_id")
	private int rebatesId; //返款方式
	
	@Column(name="money_value")
	private double value; //金额/比例
	
//	@Column(name="delete_flag")
//	private int deleteFlag; //删除标记
//	
//	@Column(name="creator_id")
//	private int creatorId; //创建者
//	
//	@Column(name="created_time")
//	private Date createdTime; //创建时间
//	
//	@Column(name="updater_id")
//	private int updaterId; //最后修改者
//	
//	@Column(name="updated_time")
//	private Date updatedTime; //最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public int getEnrollmentFloor() {
		return enrollmentFloor;
	}

	public void setEnrollmentFloor(int enrollmentFloor) {
		this.enrollmentFloor = enrollmentFloor;
	}

	public int getEnrollmentCeil() {
		return enrollmentCeil;
	}

	public void setEnrollmentCeil(int enrollmentCeil) {
		this.enrollmentCeil = enrollmentCeil;
	}

	public int getRebatesId() {
		return rebatesId;
	}

	public void setRebatesId(int rebatesId) {
		this.rebatesId = rebatesId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
