package net.cedu.action.orgstructure.job;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.entity.orgstructure.Job;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询岗位数量_json
 * @author 谭必庆
 *
 */
@ParentPackage("json-default")
public class JsonPageCountJobAction extends BaseAction 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private JobBiz jobBiz;
	
	private Job job=new Job();
	
	// 分页结果
	private PageResult<Job> result = new PageResult<Job>();
	
	@Action(value = "page_count_job", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(jobBiz.findCountByConditionForHQL(job));
		return SUCCESS;
	}

	public PageResult<Job> getResult() {
		return result;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
}
