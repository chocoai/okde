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
 * 权限子项
 * @author Jack
 *
 */
@Entity
@Table(name = "tb_p_e_subprivilege")
public class SubPrivilege implements Serializable 
{
	private static final long serialVersionUID = 9034085236402657805L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="name")
	private String name; 					//权限子项名varchar(64)
	
	@Column(name="privilege_id")
	private int privilegeId;				//权限ID
	
	@Transient
	transient private Privilege privilege;	//权限
	
	@Column(name="full_path")
	private String fullPath;				//权限子项全路径(Text)
	
	@Column(name="code")
	private String code;					//权限子项代码(256)
	
	@Column(name="is_show")
	private int isShow;						//是否显示
	
	@Column(name="is_popup")
	private int isPopUp;					//是否弹出
	
	@Column(name="orders")
	private int orders=0;						//排序

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

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getIsPopUp() {
		return isPopUp;
	}

	public void setIsPopUp(int isPopUp) {
		this.isPopUp = isPopUp;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
}
