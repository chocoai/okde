package net.cedu.entity.admin.privilege;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户组权限
 * @author Jack
 *
 */
@Entity		   
@Table(name = "tb_p_r_ugroup_privilege")
public class GroupPrivilege implements Serializable
{
	private static final long serialVersionUID = 2938523904724669925L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="group_id")
	private int groupId; 					//用户组ID
	
	@Column(name="sub_system_ids")
	private String subSystemIds; 			//子系统ID集
	
	@Column(name="modular_ids")
	private String modularIds;				//模块ID集
	
	@Column(name="set_ids")
	private String setIds; 					//权限集ID集
	
	@Column(name="privilege_ids")
	private String privilegeIds;			//权限ID集

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getSubSystemIds() {
		return subSystemIds;
	}

	public void setSubSystemIds(String subSystemIds) {
		this.subSystemIds = subSystemIds;
	}

	public String getModularIds() {
		return modularIds;
	}

	public void setModularIds(String modularIds) {
		this.modularIds = modularIds;
	}

	public String getSetIds() {
		return setIds;
	}

	public void setSetIds(String setIds) {
		this.setIds = setIds;
	}

	public String getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String privilegeIds) {
		this.privilegeIds = privilegeIds;
	}	
}
