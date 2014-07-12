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
 * 添加角色_Json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddRoleAction extends BaseAction implements ModelDriven<Role>
{
	private static final long serialVersionUID = -3724410920328060484L;

	@Autowired
	private RoleBiz roleBiz;
	
	private Role role=new Role();
	
	private boolean results=false;
	
	@Action(value = "add_role", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			results=roleBiz.createNew(role);
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getModel() {
		return role;
	}
}
