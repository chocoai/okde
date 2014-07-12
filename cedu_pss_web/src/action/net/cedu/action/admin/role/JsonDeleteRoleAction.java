package net.cedu.action.admin.role;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.RoleBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除角色
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonDeleteRoleAction extends BaseAction
{
	private static final long serialVersionUID = 3251241713100596348L;

	@Autowired
	private RoleBiz roleBiz;
	
	private boolean results=false;
	private int id;
	
	@Action(value = "delete_role", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			roleBiz.deleteConfigById(id);
			results=true;
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

	public void setId(int id) {
		this.id = id;
	}	
}
