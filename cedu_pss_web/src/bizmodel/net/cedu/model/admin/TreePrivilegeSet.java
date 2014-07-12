package net.cedu.model.admin;

import java.io.Serializable;
import java.util.List;

import net.cedu.entity.admin.privilege.Privilege;
import net.cedu.entity.admin.privilege.PrivilegeSet;

/**
 * 权限集_权限集合
 * @author Jack
 *
 */
public class TreePrivilegeSet implements Serializable
{
	private static final long serialVersionUID = -1134294649267788221L;
	
	private PrivilegeSet privilegeSet;
	private List<Privilege> plist;
	
	public PrivilegeSet getPrivilegeSet() {
		return privilegeSet;
	}
	public void setPrivilegeSet(PrivilegeSet privilegeSet) {
		this.privilegeSet = privilegeSet;
	}
	public List<Privilege> getPlist() {
		return plist;
	}
	public void setPlist(List<Privilege> plist) {
		this.plist = plist;
	}
}
