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
 * 添加岗位级别_Json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddJobLevelAction extends BaseAction implements ModelDriven<JobLevel>
{
	private static final long serialVersionUID = -3724410920328060484L;

	@Autowired
	private JobLevelBiz jobLevelBiz;
	
	private JobLevel jobLevel=new JobLevel();
	
	private boolean results=false;
	
	@Action(value = "add_job_level", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			results=jobLevelBiz.addNew(jobLevel);
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
