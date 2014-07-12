package net.cedu.action.worklog;

import net.cedu.action.BaseAction;
import net.cedu.biz.worklog.WorklogBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除岗位
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonDeleteWorklogAction extends BaseAction
{
	private static final long serialVersionUID = 3251241713100596348L;

	@Autowired
	private WorklogBiz worklogBiz;
	
	private boolean results=false;
	private int id;
	
	@Action(value = "delete_worklog", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			worklogBiz.deleteMonthWorklog(id);
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
