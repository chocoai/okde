package net.cedu.action.admin.branch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
/**
 * 删除学习中心
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonDeleteBranchAction extends BaseAction 
{
	private static final long serialVersionUID = -2159351755560754424L;
	
	@Autowired
	private BranchBiz branchBiz;
	
	private int id;
	private boolean results=false;
	
	@Action(value = "delete_branch", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			branchBiz.deleteConfigById(id);
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
