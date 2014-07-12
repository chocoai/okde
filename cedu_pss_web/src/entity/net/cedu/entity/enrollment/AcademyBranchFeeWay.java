package net.cedu.entity.enrollment;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 院校中心缴费方式关系
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_r_academy_branch_fee_way")
public class AcademyBranchFeeWay implements Serializable
{
	private static final long serialVersionUID = -7676091159841857385L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  //主建ID
	private int id;
	
	@Column(name="batch_id")
	private int batchId; //招生批次ID
	
	@Column(name = "academy_id")
	private int academyId;  //院校ID

	@Column(name = "branch_id")
	private int branchId;  //学习中心ID
	
	@Column(name = "fee_way_id")
	private int feeWayId;  //缴费方式ID

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getFeeWayId() {
		return feeWayId;
	}

	public void setFeeWayId(int feeWayId) {
		this.feeWayId = feeWayId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
}
