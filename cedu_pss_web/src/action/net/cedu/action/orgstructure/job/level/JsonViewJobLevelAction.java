package net.cedu.action.orgstructure.job.level;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JobLevelBiz;
import net.cedu.entity.orgstructure.JobLevel;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID查询岗位级别
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewJobLevelAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private JobLevelBiz jobLevelBiz;
	
	private JobLevel jobLevel=null;
	
	private int id=0;
	
	@Action(value = "view_job_level", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			jobLevel=jobLevelBiz.findJobLevelById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public JobLevel getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(JobLevel jobLevel) {
		this.jobLevel = jobLevel;
	}



	public void setId(int id) {
		this.id = id;
	}
}
