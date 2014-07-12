package net.cedu.action.orgstructure.job.level;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JobLevelBiz;
import net.cedu.entity.orgstructure.JobLevel;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 修改岗位级别
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateJobLevelAction extends BaseAction implements ModelDriven<JobLevel>
{
	private static final long serialVersionUID = 5158621067783985998L;

	@Autowired
	private JobLevelBiz jobLevelBiz;
	
	private JobLevel jobLevel=new JobLevel();
	
	private boolean results=false;
	
	@Action(value = "update_job_level", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			jobLevelBiz.modify(jobLevel);
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

	public JobLevel getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(JobLevel jobLevel) {
		this.jobLevel = jobLevel;
	}

	public JobLevel getModel() {
		return jobLevel;
	}
}
