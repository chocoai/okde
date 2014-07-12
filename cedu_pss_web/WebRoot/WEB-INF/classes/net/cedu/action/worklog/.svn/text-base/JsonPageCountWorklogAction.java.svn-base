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
 * 查询岗位数量_json
 * @author 谭必庆
 *
 */
@ParentPackage("json-default")
public class JsonPageCountWorklogAction extends BaseAction 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private WorklogBiz worklogBiz;
	
	private Worklog worklog=new Worklog();
	
	private String starttime,endtime;
	
	
	// 分页结果
	private PageResult<Worklog> result = new PageResult<Worklog>();
	
	@Action(value = "page_count_worklog_person", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		if(worklog==null){
			worklog=new Worklog();
		}
		worklog.setCreateBy(getUser().getUserId());
		result.setRecordCount(worklogBiz.findCountByConditionForHQL(worklog,starttime,endtime));
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
