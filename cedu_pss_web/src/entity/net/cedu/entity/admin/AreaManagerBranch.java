package net.cedu.entity.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 区域经理与学习中心关系
 * @author gaolei
 *
 */
@Entity
@Table(name = "tb_r_area_manager_branch")
public class AreaManagerBranch implements Serializable {
	private static final long serialVersionUID = 3950863883401035406L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name = "branch_id")  					//学习中心ID
	private int branchId;
	
	@Column(name = "manager_id")  					//区域经理ID
	private int managerId;
	
	
	@Transient
	transient private String branchName;            //学习中心名称


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBranchId() {
		return branchId;
	}


	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	
}
