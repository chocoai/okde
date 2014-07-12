package net.cedu.entity.enrollment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 区域经理与学习中心关系
 */
@Entity
@Table(name="tb_r_area_manager_branch")
public class EnrollmentAreaManager implements Serializable
{
	private static final long serialVersionUID = -4336114666868368519L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="manager_id")
	private int managerId; //区域经理（用户ID）
	
	@Column(name="branch_id")
	private int branchId; //学习中心
	
	// Default constructor
	public EnrollmentAreaManager() {
	}
	
	// Full fielded constructor
	public EnrollmentAreaManager(int id, int managerId, int branchId) {
		super();
		this.id = id;
		this.managerId = managerId;
		this.branchId = branchId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
}
