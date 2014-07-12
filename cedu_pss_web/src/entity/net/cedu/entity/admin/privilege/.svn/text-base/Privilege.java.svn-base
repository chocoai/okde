package net.cedu.entity.admin.privilege;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 权限
 */
@Entity
@Table(name = "tb_p_e_privilege")
public class Privilege implements Serializable
{
	private static final long serialVersionUID = 5444704397151673398L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="name")
	private String name; 					//权限名称varchar(64)
	
	@Column(name="set_id")
	private int setId;						//权限集ID
	
	@Transient
	transient private PrivilegeSet privilegeSet;	//权限集
	
	@Column(name="orders")
	private int orders=0;						//排序
	
	@Column(name="full_path")
	private String fullPath;				//权限子项全路径(Text)
	
	@Column(name="is_show")
	private int isShow;						//是否显示

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

	public int getSetId() {
		return setId;
	}

	public void setSetId(int setId) {
		this.setId = setId;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public PrivilegeSet getPrivilegeSet() {
		return privilegeSet;
	}

	public void setPrivilegeSet(PrivilegeSet privilegeSet) {
		this.privilegeSet = privilegeSet;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
}
