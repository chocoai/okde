package net.cedu.action.worklog;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.worklog.WorklogBiz;
import net.cedu.entity.worklog.Worklog;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID查询日报
 * @author Jack
 *
 */
public class ViewWorklogAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private WorklogBiz monthlogBiz;
	@Autowired
	private UserBiz userBiz;
	
	private Worklog worklog = new Worklog();
	
	private int id=0;
	
	public String execute()
	{
		try
		{
			worklog=monthlogBiz.getById(id);
			if(worklog!=null){
				worklog.setCreateUser(userBiz.findUserById(worklog.getCreateBy()));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Worklog getWorklog() {
		return worklog;
	}

	public void setId(int id) {
		this.id = id;
	}
}
