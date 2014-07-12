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
 * 查询岗位级别数量_json
 * @author 谭必庆
 *
 */
@ParentPackage("json-default")
public class JsonPageCountJobLevelAction extends BaseAction 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private JobLevelBiz jobLevelBiz;
	
	private JobLevel jobLevel=new JobLevel();
	
	// 分页结果
	private PageResult<JobLevel> result = new PageResult<JobLevel>();
	
	@Action(value = "page_count_job_level", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(jobLevelBiz.findCountByConditionForHQL(jobLevel));
		return SUCCESS;
	}

	public PageResult<JobLevel> getResult() {
		return result;
	}

	public void setModular(JobLevel jobLevel) {
		this.jobLevel = jobLevel;
	}
}
