package net.cedu.action.worklog;

import net.cedu.action.BaseAction;
import net.cedu.biz.worklog.WorklogBiz;
import net.cedu.entity.worklog.Worklog;
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
public class JsonPageListWorklogAction extends BaseAction
{
	private static final long serialVersionUID = -1813626596721638819L;	
	
	@Autowired
	private WorklogBiz worklogBiz;
	
	private Worklog worklog=new Worklog();
	
	private String starttime,endtime;
	
	// 分页结果
	private PageResult<Worklog> result = new PageResult<Worklog>();
	
	@Action(value="page_list_worklog_person", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		if(worklog==null){
			worklog=new Worklog();
		}
		worklog.setCreateBy(getUser().getUserId());
		result.setList(worklogBiz.findListByCondition(result, worklog,starttime,endtime));
		return SUCCESS;
	}

	public PageResult<Worklog> getResult() {
		return result;
	}

	public Worklog getWorklog() {
		return worklog;
	}

	public void setWorklog(Worklog worklog) {
		this.worklog = worklog;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
}
