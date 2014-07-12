/**
 * 文件名：JsonInterfaceAction.java
 * 包名：net.cedu.action.worklog
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-1-5 上午10:22:37
 *
 */
package net.cedu.action.worklog;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.orgstructure.DepartmentBiz;
import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.biz.orgstructure.JurisdictionBiz;
import net.cedu.entity.admin.User;
import net.cedu.entity.orgstructure.Department;
import net.cedu.entity.orgstructure.Job;
import net.cedu.entity.orgstructure.Jurisdiction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonInterfaceAction extends BaseAction {
	
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private DepartmentBiz departmentBiz;
	@Autowired
	private JurisdictionBiz jurisdictionBiz;
	@Autowired
	private JobBiz jobBiz;
	@Autowired
	private UserBiz userBiz;
	//返回的结果
	private List<Map<String,Object>> treeList=new ArrayList<Map<String,Object>>();

	/**
	 * 
	 * @功能：通过当前用户查询组织Tree，以及部门，岗位，员工
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-5 上午10:24:25
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "org_tree", results = { @Result(name = "success", type = "json", params = {"contentType", "text/json", }) })
	public String orgTree() throws Exception {
		String departmentIds=",";
		//查询所有关系部门IDs
		Jurisdiction jurisdiction=jurisdictionBiz.findById(getUser().getUserId());
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
							departmentIdsSB=new StringBuffer(departmentId);
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
		//学习中心
//		Branch branchObject=branchBiz.findBranchById(super.getUser().getOrgId());
//		if(branchObject==null){
//			return SUCCESS;
//		}
		//学习中心下的部门
		//List<Department> departmentList=departmentBiz.findDepartmentsByUserIdAndBranchId(super.getUser().getUserId(), super.getUser().getOrgId());
		List<Department> departmentList=departmentBiz.findDepartmentsByDepartmentIds(departmentIds);
		//部门下的所有用户
//		List<User> userList=userBiz.findUsersByOrgId(branchObject.getId());
		
		
		List<User> userList =userBiz.findUsersByDepartmentIds(departmentIds);
		
		//查询当前用户所在的学习中心
		Map<String,Object> branch=new HashMap<String, Object>();
//		branch.put("branchId", branchObject.getId());
//		branch.put("branchName", branchObject.getName());
		branch.put("branchId", "0");
		branch.put("branchName", "全部");
		
		
		
		//通过当前用户以及该用户的学习中心ID查询部门
		List<Map<String,Object>> departments=new ArrayList<Map<String,Object>>();
		
		for (Department d : departmentList) {
			//通过部门ID查询用户
			List<Map<String,Object>> users=new ArrayList<Map<String,Object>>();
			Map<String,Object> department=new HashMap<String, Object>();
			department.put("departmentId", d.getId());
			department.put("departmentName", d.getName());
			for (User u : userList) {
				if(u!=null&&u.getDepartmentId()==d.getId()){
					Map<String,Object> user=new HashMap<String, Object>();
					user.put("userId", u.getId());
					//user.put("userName", PingYingHanZiUtil.getNameFirstZiMuToLowerCase(u.getFullName())+u.getFullName());
					Job job=jobBiz.findJobById(u.getJobId());
					if(job!=null){
						user.put("job_level_id",job.getJobLevelId());
					}
					else{
						user.put("job_level_id",0);
					}
					user.put("userName", u.getFullName());
					users.add(user);
				}
			}
			
			//按中文排序
			Collections.sort(users, new Comparator() {
				public int compare(Object obj0, Object obj1) {
					Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
					String name1 = ((Map<String,Object>) obj0).get("userName").toString();
					String name2 = ((Map<String,Object>) obj1).get("userName").toString();
					return cmp.compare(name1, name2);
				}
			});
			department.put("users", users);
			departments.add(department);
		}
		
		branch.put("departments", departments);
		
		treeList.add(branch);
		return SUCCESS;
	}

	public List<Map<String, Object>> getTreeList() {
		return treeList;
	}
	
}
