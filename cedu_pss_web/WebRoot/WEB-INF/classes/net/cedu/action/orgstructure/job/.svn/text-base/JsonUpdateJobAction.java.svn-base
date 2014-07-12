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
 * 修改岗位
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateJobAction extends BaseAction implements ModelDriven<Job>
{
	private static final long serialVersionUID = 5158621067783985998L;

	@Autowired
	private JobBiz jobBiz;
	
	private Job job=new Job();
	
	private boolean results=false;
	
	@Action(value = "update_job", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			jobBiz.modify(job);
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
