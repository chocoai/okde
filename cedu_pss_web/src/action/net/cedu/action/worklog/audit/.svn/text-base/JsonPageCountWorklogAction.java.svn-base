package net.cedu.action.worklog.audit;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JurisdictionBiz;
import net.cedu.biz.worklog.WorklogBiz;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.orgstructure.Jurisdiction;
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
	@Autowired
	private JurisdictionBiz jurisdictionBiz;
	
	private Worklog worklog=new Worklog();
	
	private String starttime,endtime;
	
	
	// 分页结果
	private PageResult<Worklog> result = new PageResult<Worklog>();
	
	@Action(value = "page_count_worklog_audit", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		if(worklog==null){
			worklog=new Worklog();
		}
		String departments="0";
		//worklog.setCreateBy(getUser().getUserId());
		if(worklog.getCuDepartmentId()!=0){
			departments=worklog.getCuDepartmentId()+"";
		}else{
			Jurisdiction j=jurisdictionBiz.findById(getUser().getUserId());
			
			if(null!=j)
			{
				departments=StringUtil.strToList(j.getDepartmentIds(),"@").toString();
				departments=departments.replace("[","");
				departments=departments.replace("]","");
				//System.out.println(departments);
			}
		}
		
		result.setRecordCount(worklogBiz.findCountByConditionForHQLAudit(worklog,starttime,endtime,departments));
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
