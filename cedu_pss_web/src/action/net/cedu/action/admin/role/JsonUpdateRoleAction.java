package net.cedu.action.admin.role;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.RoleBiz;
import net.cedu.entity.admin.Role;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 修改角色
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateRoleAction extends BaseAction implements ModelDriven<Role>
{
	private static final long serialVersionUID = 5158621067783985998L;

	@Autowired
	private RoleBiz roleBiz;
	
	private Role role=new Role();
	
	private boolean results=false;
	
	@Action(value = "update_role", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			results=roleBiz.modify(role);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public boolean getResults() {
		return results;
	}

	public Role getModel() {
		return role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	
}
