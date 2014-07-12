package net.cedu.action.worklog;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.biz.orgstructure.JobLevelBiz;
import net.cedu.biz.worklog.WorklogBiz;
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
public class UpdateWorklogAction extends BaseAction implements ModelDriven<Worklog>
{
	private static final long serialVersionUID = -8638769045672917328L;

	@Autowired
	private WorklogBiz monthlogBiz;
	
	private Worklog worklog = new Worklog();
	
	@Action(results = {@Result(name="success",location="index_worklog",type="redirect")})
	public String execute() throws Exception
	{
		if(isGetRequest())
		{
			worklog=monthlogBiz.getById(worklog.getId());
			return INPUT;
		}
		Worklog old=monthlogBiz.getById(worklog.getId());
		old.setTitle(worklog.getTitle());
		old.setContent(worklog.getContent());
		monthlogBiz.update(old);
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
