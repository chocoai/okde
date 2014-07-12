package net.cedu.action.orgstructure.job;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.entity.orgstructure.Job;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加岗位_Json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddJobAction extends BaseAction implements ModelDriven<Job>
{
	private static final long serialVersionUID = -3724410920328060484L;

	@Autowired
	private JobBiz jobBiz;
	
	private Job job=new Job();
	
	private boolean results=false;
	
	@Action(value = "add_job", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			job.setCreateBy(getUser().getUserId());
			results=jobBiz.addNew(job);
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Job getModel() {
		return job;
	}	
}
