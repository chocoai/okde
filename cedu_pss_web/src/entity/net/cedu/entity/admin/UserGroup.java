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
 * 用户组
 * @author Jack
 *
 */
@Entity
@Table(name = "tb_p_e_user_group")
public class UserGroup implements Serializable 
{
	private static final long serialVersionUID = 7137580378299201419L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="name")
	private String name; 					//名称varchar(128)
	
	@Column(name="org_id")
	private int orgId;						//所属机构ID
	
	@Transient
	transient private Branch org;			//所属机构
	
	@Column(name="role_id")
	private int roleId;						//所属角色ID
	
	@Transient
	transient private Role role;			//所属角色

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public Branch getOrg() {
		return org;
	}

	public void setOrg(Branch org) {
		this.org = org;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}