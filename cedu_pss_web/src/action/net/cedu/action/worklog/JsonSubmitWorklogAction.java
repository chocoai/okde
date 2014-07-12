package net.cedu.action.worklog;

import net.cedu.action.BaseAction;
import net.cedu.biz.worklog.WorklogBiz;
import net.cedu.entity.worklog.Worklog;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 日报提交审批
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonSubmitWorklogAction extends BaseAction
{
	private static final long serialVersionUID = 3251241713100596348L;

	@Autowired
	private WorklogBiz worklogBiz;
	
	private boolean results=false;
	private int id;
	
	@Action(value = "submit_worklog", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			Worklog worklog=worklogBiz.getById(id);
			worklog.setStatus(1);
			worklogBiz.update(worklog);
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
