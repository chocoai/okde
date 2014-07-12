package net.cedu.action.orgstructure.job;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.entity.orgstructure.Job;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID查询岗位
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewJobAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private JobBiz jobBiz;
	
	private Job job=null;
	
	private int id=0;
	
	@Action(value = "view_job", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			job=jobBiz.findJobById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}



	public void setId(int id) {
		this.id = id;
	}
}
