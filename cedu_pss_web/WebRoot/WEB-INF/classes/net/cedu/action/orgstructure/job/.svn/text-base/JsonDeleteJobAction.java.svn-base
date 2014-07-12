package net.cedu.action.orgstructure.job;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JobBiz;

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
public class JsonDeleteJobAction extends BaseAction
{
	private static final long serialVersionUID = 3251241713100596348L;

	@Autowired
	private JobBiz jobBiz;
	
	private boolean results=false;
	private int id;
	
	@Action(value = "delete_job", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			jobBiz.deleteConfigById(id);
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
