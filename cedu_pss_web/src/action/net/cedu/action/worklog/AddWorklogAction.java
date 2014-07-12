package net.cedu.action.worklog;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.biz.orgstructure.JobLevelBiz;
import net.cedu.biz.worklog.WorklogBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.admin.User;
import net.cedu.entity.orgstructure.Job;
import net.cedu.entity.orgstructure.JobLevel;
import net.cedu.entity.worklog.Worklog;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 添加日报
 * @author Jack
 *
 */
public class AddWorklogAction extends BaseAction implements ModelDriven<Worklog>
{
	private static final long serialVersionUID = -8638769045672917328L;

	@Autowired
	private WorklogBiz monthlogBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private JobBiz jobBiz;
	@Autowired
	private JobLevelBiz jobLevelBiz;
	
	private Worklog worklog = new Worklog();
	
	@Action(results = {@Result(name="success",location="index_worklog",type="redirect")})
	public String execute() throws Exception
	{
		if(isGetRequest())
		{
			return INPUT;
		}
		
		if(worklog.getCreateOn()!=null){
			if(monthlogBiz.findWorklogByParams(getUser().getUserId(), DateUtil.getDateStr(worklog.getCreateOn(), "yyyy-MM-dd"))){
				request.setAttribute("ERROR", "DATE");
				return INPUT;
			}
		}
		
		User user=userBiz.findUserById(getUser().getUserId());
		worklog.setCreateBy(user.getId());
		worklog.setCuDepartmentId(user.getDepartmentId());
		Job job=jobBiz.findJobById(user.getJobId());
		JobLevel jobLevel=jobLevelBiz.findJobLevelById(job.getJobLevelId());
		worklog.setCuJobLevel(jobLevel.getLevels());
		monthlogBiz.createNew(worklog);
		return SUCCESS;
	}

	public Worklog getModel() {
		return worklog;
	}

	public Worklog getWorklog() {
		return worklog;
	}

	public void setWorklog(Worklog worklog) {
		this.worklog = worklog;
	}
}
