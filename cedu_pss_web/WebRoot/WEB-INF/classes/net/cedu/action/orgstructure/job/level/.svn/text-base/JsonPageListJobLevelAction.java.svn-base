package net.cedu.action.orgstructure.job.level;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JobLevelBiz;
import net.cedu.entity.orgstructure.JobLevel;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据条件查询岗位级别_分页
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonPageListJobLevelAction extends BaseAction
{
	private static final long serialVersionUID = -1813626596721638819L;	
	
	@Autowired
	private JobLevelBiz jobLevelBiz;
	
	private JobLevel jobLevel=new JobLevel();
	
	// 分页结果
	private PageResult<JobLevel> result = new PageResult<JobLevel>();
	
	@Action(value="page_list_job_level", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		result.setList(jobLevelBiz.findListByCondition(result, jobLevel));
		return SUCCESS;
	}

	public PageResult<JobLevel> getResult() {
		return result;
	}

	public void setModular(JobLevel jobLevel) {
		this.jobLevel = jobLevel;
	}
}
