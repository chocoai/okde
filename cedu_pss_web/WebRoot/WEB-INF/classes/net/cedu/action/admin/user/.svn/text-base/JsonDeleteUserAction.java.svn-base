package net.cedu.action.admin.user;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 删除学习中心
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonDeleteUserAction extends BaseAction 
{
	private static final long serialVersionUID = -2159351755560754424L;
	
	@Autowired
	private UserBiz userBiz;
	
	private int id;
	private boolean results=false;
	
	@Action(value = "delete_user", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			userBiz.deleteConfigById(id);
			results=true;
		}
		catch(Exception e)
		{e.printStackTrace();}
		return SUCCESS;
	}

	public boolean getResults() {
		return results;
	}

	public void setId(int id) {
		this.id = id;
	}
}
