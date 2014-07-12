package net.cedu.action.worklog.audit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.biz.orgstructure.JobLevelBiz;
import net.cedu.biz.orgstructure.JurisdictionBiz;
import net.cedu.biz.worklog.WorklogBiz;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.admin.User;
import net.cedu.entity.orgstructure.Job;
import net.cedu.entity.orgstructure.JobLevel;
import net.cedu.entity.orgstructure.Jurisdiction;
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
	@Autowired
	private JobLevelBiz jobLevelBiz;
	@Autowired
	private JobBiz jobBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private JurisdictionBiz jurisdictionBiz;
	
	
	private Worklog worklog=new Worklog();
	
	private User user=new User();
	private Job job=new Job();
	private JobLevel jobLevel=new JobLevel();
	private Jurisdiction jurisdiction=new Jurisdiction();
	private Integer [] levels=null;
	
	public JsonPageListWorklogAction(){
		//super();
		
	}
	
	private String starttime,endtime;
	
	// 分页结果
	private PageResult<Worklog> result = new PageResult<Worklog>();
	
	@Action(value="page_list_worklog_audit", results={@Result(name = "success", type = "json", params={"contentType",  "text/json"} )})
	public String execute()throws Exception
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
		
		List<Worklog> worklogList=worklogBiz.findListByConditionAudit(result, worklog,starttime,endtime,departments);
		
		if(worklogList!=null){
			//当前用户信息
			try {
				user=userBiz.findUserById(super.getUser().getUserId());
				if(user!=null){
					 job=jobBiz.findJobById(user.getJobId());
					if(job!=null){
						 jobLevel=jobLevelBiz.findJobLevelById(job.getJobLevelId());
					}
					jurisdiction=jurisdictionBiz.findById(getUser().getUserId());
					levels=getChildLevelBySessionUser();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for (Worklog worklog : worklogList) {
				//判断是否已有评价
				List<Worklog> records=worklog.getRecords();
					//可以评分
					if(isPinFen(worklog.getCuJobLevel(),records)){
						worklog.setPingFen(true);
					}else{
						worklog.setPingFen(false);
					}
					//显示评价
					if(worklog.getRecords()!=null&&worklog.getRecords().size()!=0){
						worklog.setShowPingFen(true);
					}else{
						worklog.setShowPingFen(false);
					}
					
			}
		}
		
		result.setList(worklogList);
		return SUCCESS;
	}
	//判断是否可以评论
	private boolean isPinFen(int pl,List<Worklog> wList)throws Exception{
		
		//获取当前用户岗位级别
		int userJobLevel=9999;
		
		if(jobLevel!=null){
			userJobLevel=jobLevel.getLevels();
		}
		//获取当前人以下的岗位级别， 比当前人等级大的人不可以评论，值越小，等级越大
		//Integer [] levels=getChildLevelBySessionUser();
		List<Integer> levelList=new ArrayList<Integer>();
		if(levels!=null){
			levels[levels.length-1]=userJobLevel;
			for (Integer level : levels) {
				//过滤当前用户管辖的比自己岗位级别高的人
				if(level>=userJobLevel){
					if(wList!=null&&wList.size()!=0){
						boolean isfound=false;
						for (Worklog worklog : wList) {
							if(worklog.getCuJobLevel()<=level){
								isfound=true;
								continue;
							}
						}
						//存在说明自己已审批
						if(!isfound){
							if(!levelList.contains(level))
								levelList.add(level);
						}
					}else{
						if(level!=pl&&pl>userJobLevel&&pl>level){
							if(!levelList.contains(level))
								levelList.add(level);
						}
					}
					
				}
			}
		}
		
		//没有审批的岗位级别集合
		if(levelList.size()==0){
			return false;
		}else{
			if(levelList.size()==1&&levelList.get(0)==userJobLevel){
				return true;
			}
			
		}
		return false;
	}

	/**
	 * 
	 * @功能：通过当前登录人ID获取他以下的岗位级别数组
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-8 下午07:12:55
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @return
	 * @throws Exception 
	 */
	private Integer[] getChildLevelBySessionUser() throws Exception{
		Integer [] levels=null;
		String departmentIds=",";
		//查询所有关系部门IDs
		//Jurisdiction jurisdiction=jurisdictionBiz.findById(getUser().getUserId());
		if(jurisdiction!=null){
			if(jurisdiction.getDepartmentIds()!=null){
				String [] departmentIdArray=jurisdiction.getDepartmentIds().split("@");
				StringBuffer departmentIdsSB = new StringBuffer(",");
				for (String departmentId : departmentIdArray) {
					if(departmentId!=null&&!"".equals(departmentId)){
//						if(departmentIds.equals(",")){
//							departmentIds=departmentId;
//						}else{
//							departmentIds+=","+departmentId;
//						}
						if(departmentIdsSB.toString().equals(",")){
							departmentIdsSB = new StringBuffer(departmentId);
						}else{
							departmentIdsSB.append(","+departmentId);
						}
					}
				}
				departmentIds=departmentIdsSB.toString();
			}
		}
		if(departmentIds.equals(",")){
			departmentIds="0";
		}
		String jobIds=",";
		List<User> userList =userBiz.findUsersByDepartmentIds(departmentIds);
		if(userList!=null&&userList.size()!=0){
			StringBuffer jobIdsSB = new StringBuffer(",");
			for (User user : userList) {
//				if(jobIds.equals(",")){
//					jobIds=user.getJobId()+"";
//				}else{
//					jobIds+=","+user.getJobId();
//				}
				if(jobIdsSB.toString().equals(",")){
					jobIdsSB=new StringBuffer(user.getJobId()+"");
				}else{
					jobIdsSB.append(","+user.getJobId());
				}
			}
			jobIds=jobIdsSB.toString();
		}
		if(jobIds.equals(",")){
			jobIds="0";
		}
		
		String jobLevelIds=jobBiz.findJobLevelIdsByJobIds(jobIds);
		
		List<JobLevel> jobLevelList=jobLevelBiz.findJobLevelListByJobLevelIds(jobLevelIds);
		
		if(jobLevelList!=null&&jobLevelList.size()!=0){
			levels=new Integer[jobLevelList.size()+1];
			int i=0;
			for (JobLevel jobLevel : jobLevelList) {
				levels[i]=jobLevel.getLevels();
				i++;
				
			}
		}
		
		return levels;
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
