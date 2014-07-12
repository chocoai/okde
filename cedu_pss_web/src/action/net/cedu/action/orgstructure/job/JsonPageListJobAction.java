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
 * 根据条件查询岗位_分页
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonPageListJobAction extends BaseAction
{
	private static final long serialVersionUID = -1813626596721638819L;	
	
	@Autowired
	private JobBiz jobBiz;
	
	private Job job=new Job();
	
	// 分页结果
	private PageResult<Job> result = new PageResult<Job>();
	
	@Action(value="page_list_job", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		result.setList(jobBiz.findListByCondition(result, job));
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
