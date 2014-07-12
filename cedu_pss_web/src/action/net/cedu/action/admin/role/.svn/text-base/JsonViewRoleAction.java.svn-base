package net.cedu.action.admin.role;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.RoleBiz;
import net.cedu.entity.admin.Role;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID查询角色
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewRoleAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private RoleBiz roleBiz;
	
	private Role role=new Role();
	
	private int id=0;
	
	@Action(value = "view_role", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			role=roleBiz.findRoleById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Role getRole() {
		return role;
	}

	public void setId(int id) {
		this.id = id;
	}
}
