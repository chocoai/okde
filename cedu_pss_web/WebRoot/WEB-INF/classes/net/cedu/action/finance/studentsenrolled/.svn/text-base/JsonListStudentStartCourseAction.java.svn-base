package net.cedu.action.finance.studentsenrolled;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生开课
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonListStudentStartCourseAction extends BaseAction
{
	@Autowired
	private StudentBiz studentBiz;
	
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	// 查询条件
	private Student student=new Student();
	
	//学生开课参数
	private int studentId;//学生Id
	private boolean isfail=false;//返回参数
	private int isStartCourse;//学生开课状态
	
	//学生批量开课参数	
	private int isStartcStatus;
	private String stuIds;//学生Ids
	
	
	/**
	 * 查询学生数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_start_course_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", 
			"includeProperties",
			"result.*,student.*"
	}) })
	public String cbStudentCount() throws Exception 
	{
		//开课状态显示
		if(student.getIsStartCourse()!=-1)
		{
			student.getParamsInt().put("isStartCourse", student.getIsStartCourse());
		}
		//过滤掉老生续读和社招
		student.getParamsString().put("removeEnrollmentSource", Constants.WEB_STU_SOURCE_DEFAULT+","+Constants.WEB_STU_LAO_SHENG_XU_DU);
		
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		result.setRecordCount(studentBiz.findStudentsPageCountByBaseConditions(student));
		return SUCCESS;
	}

	/**
	 * 查询学生列表集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_start_course_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,student.*"		
	}) })
	public String lbStudentList() throws Exception
	{
		//开课状态显示
		if(student.getIsStartCourse()!=-1)
		{
			student.getParamsInt().put("isStartCourse", student.getIsStartCourse());
		}
		//过滤掉老生续读和社招
		student.getParamsString().put("removeEnrollmentSource", Constants.WEB_STU_SOURCE_DEFAULT+","+Constants.WEB_STU_LAO_SHENG_XU_DU);
		
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		result.setList(studentBiz.findStudentsPageListByBaseConditions(student, result));
		return SUCCESS;
	}

	/**
	 * 学生开课
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upate_student_start_course_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upStudentStatus() throws Exception
	{
		Student stu=this.studentBiz.findStudentById(studentId);
		if(stu!=null)
		{
			stu.setIsStartCourse(isStartCourse);
			stu.setUpdaterId(super.getUser().getUserId());
			stu.setModifiedTime(new Date());
			this.studentBiz.updateStudentInfo(stu);
		}
		return SUCCESS;
	}
	
	/**
	 * 学生批量开课
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upate_batch_student_start_course_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upBatchStudentStatus() throws Exception
	{
		if(stuIds!=null && !stuIds.equals(""))
		{
			this.studentBiz.updateBatchStudentStartStatus(stuIds, isStartcStatus);
		}
		return SUCCESS;
	}
	
	public PageResult<Student> getResult() {
		return result;
	}

	public void setResult(PageResult<Student> result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public int getIsStartCourse() {
		return isStartCourse;
	}

	public void setIsStartCourse(int isStartCourse) {
		this.isStartCourse = isStartCourse;
	}

	public int getIsStartcStatus() {
		return isStartcStatus;
	}

	public void setIsStartcStatus(int isStartcStatus) {
		this.isStartcStatus = isStartcStatus;
	}

	public String getStuIds() {
		return stuIds;
	}

	public void setStuIds(String stuIds) {
		this.stuIds = stuIds;
	}
	
	
}

