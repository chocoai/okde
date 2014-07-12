package net.cedu.action.admin.user.group;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserGroupBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除模块
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonDeleteUserGroupAction extends BaseAction
{
	private static final long serialVersionUID = 3251241713100596348L;

	@Autowired
	private UserGroupBiz userGroupBiz;
	
	private boolean results=false;
	private int id;
	
	@Action(value = "delete_user_group", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			userGroupBiz.deleteConfigById(id);
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
