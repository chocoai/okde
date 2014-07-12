package net.cedu.action.enrollment.enrollmonitorapplication;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
@ParentPackage("json-default")
public class JsonUpdateEnrollMonitorApplicationAction extends BaseAction{
	
	private static final long serialVersionUID = 2521726686668221249L;

	@Autowired
	private StudentBiz stubiz;
	
	private Student student;//学生信息实体
	private boolean updaterltbool=true;//判断添加操作是否成功
	
	/**
	 * 修改
	 */
	@Action(value="update_enroll_monitor_application_stuinfo",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		try {
			int userid = super.getUser().getUserId();
			Student stu = new Student();
			if (student != null) {
				stu = stubiz.findStudentById(student.getId());
				stu.setLivingPlace(student.getLivingPlace());
				stu.setMsn(student.getMsn());
				stu.setQq(student.getQq());
				stu.setEmail(student.getEmail());
				stu.setRemark(student.getRemark());
				stu.setWorkUnitInfo(student.getWorkUnitInfo());
				stu.setMonitorStatus(Constants.STU_MONITOR_STATUS_JIAN_KONG_ZAI_SHENG_QIN);
				stu.setUpdaterId(userid);
				stu.setMobile(student.getMobile());
				stu.setModifiedTime(new Date());
				
				stubiz.updateStudentInfo(stu);
			}else{
				updaterltbool = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbool = false;
		}
		return SUCCESS;
	}
	//---------------------------------------get and set methods----------------------------------------
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public boolean isUpdaterltbool() {
		return updaterltbool;
	}
	public void setUpdaterltbool(boolean updaterltbool) {
		this.updaterltbool = updaterltbool;
	}
	
	
}
