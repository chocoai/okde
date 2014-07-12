package net.cedu.action.admin.privilege.roleprivilege;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.RolePrivilegeBiz;
import net.cedu.entity.admin.privilege.RolePrivilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 角色权限范围
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateRolePrivilegeAction extends BaseAction implements ModelDriven<RolePrivilege>
{
	private static final long serialVersionUID = 4384055175835481869L;
	
	@Autowired
	private RolePrivilegeBiz rolePrivilegeBiz;
	
	private RolePrivilege rolePrivilege=new RolePrivilege();
	
	private boolean results=false;
	
	@Action(value = "update_role_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{		
			rolePrivilegeBiz.modify(rolePrivilege);
			results=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public RolePrivilege getModel() {
		return rolePrivilege;
	}

	public RolePrivilege getRolePrivilege() {
		return rolePrivilege;
	}

	public void setRolePrivilege(RolePrivilege rolePrivilege) {
		this.rolePrivilege = rolePrivilege;
	}

	public boolean getResults() {
		return results;
	}
}
