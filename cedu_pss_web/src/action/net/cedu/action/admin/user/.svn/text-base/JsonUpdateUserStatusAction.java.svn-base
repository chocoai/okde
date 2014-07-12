package net.cedu.action.admin.user;

import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.UserEnrollQuotaBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.User;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.UserEnrollQuota;

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
public class JsonUpdateUserStatusAction extends BaseAction implements ModelDriven<User>
{
	private static final long serialVersionUID = -9007096419088908044L;
	
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private UserEnrollQuotaBiz userEnrollQuotaBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private AcademyBiz academyBiz;
	
	private User users=new User();
	private boolean results=false;
	
	/**
	 * 获取学习中心集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="update_user_status", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"includeProperties","results"
					} )})
	public String execute()throws Exception
	{
		int status=users.getStatus();
		users=userBiz.findUserById(users.getId());
		if(users!=null && users.getId()>0)
		{
			// 如果用户要修改为 启用 则不用验证
			if(status == 0){
				users.setStatus(status);
				users.setUpdaterId(super.getUser().getUserId());
				users.setUpdatedTime(new Date());
				userBiz.modify(users);
				results=true;
			}
			else{
				// 院校项目经理
				Academy academy = new Academy();
				academy.setProjectManagerId(users.getId());
				List<Academy> academyList = academyBiz.findAcademyListByParams(academy,1);
				int academySum = academyList==null?0:academyList.size();
				// 学生跟进
				Student student = new Student();
				student.setUserId(users.getId());
				List<Student> studentList = studentBiz.findStudentListByParams(student,1);
				int studentSum = studentList==null?0:studentList.size();
				// 中心人员指标
				UserEnrollQuota userEnrollQuota = new UserEnrollQuota();
				userEnrollQuota.setUserId(users.getId());
				userEnrollQuota.setBranchId(users.getOrgId());
				List<UserEnrollQuota> userEnrollQuotaList = userEnrollQuotaBiz.findUserEnrollQuotaListByParams(userEnrollQuota,1);
				int quotaSum = userEnrollQuotaList==null?0:userEnrollQuotaList.size();
				// 如果数值均为0则可以禁用
				if(academySum==0&&quotaSum==0&&studentSum==0)
				{
					users.setStatus(status);
					users.setUpdaterId(super.getUser().getUserId());
					users.setUpdatedTime(new Date());
					userBiz.modify(users);
					results=true;
				}
			}
		}
		return SUCCESS;
	}

	public User getModel() {
		return users;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public boolean isResults() {
		return results;
	}	
}
