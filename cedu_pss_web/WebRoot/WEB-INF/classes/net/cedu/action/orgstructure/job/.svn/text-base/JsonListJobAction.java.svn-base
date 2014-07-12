package net.cedu.action.orgstructure.job;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.entity.orgstructure.Job;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Json数据返回
 * @author Jack
 *
 */ 
@ParentPackage("json-default")
public class JsonListJobAction extends BaseAction implements ModelDriven<Job>
{
	private static final long serialVersionUID = -9007096419088908044L;
	
	@Autowired
	private JobBiz jobBiz;
	private List<Job> list=new ArrayList<Job>();
	
	private Job job=new Job();
	
	/**
	 * 获取学习中心集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="list_job", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"excludeProperties",
					"list.*.departmentId,"+
					"list.*.jobLevelId,"+
					"list.*.createBy,"+
					"list.*.createOn,"+
					"results,branch,result"
					} )})
	public String execute()throws Exception
	{
		list=jobBiz.findListByCondition(job);
		if(list!=null && list.size()>0)
		{
			Collections.sort(list, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Job) arg0).getName();
					String name2 = ((Job) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
		return SUCCESS;
	}

	public List<Job> getList() {
		return list;
	}

	public Job getModel() {
		return job;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}	
}
