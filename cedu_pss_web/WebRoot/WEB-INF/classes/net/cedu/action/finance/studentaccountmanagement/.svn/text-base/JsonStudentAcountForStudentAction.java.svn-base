package net.cedu.action.finance.studentaccountmanagement;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.StudentStatus;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生账户查询列表（查询条件为最基本的）
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonStudentAcountForStudentAction extends BaseAction
{
	
	@Autowired
	private StudentBiz studentBiz;
	
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	// 查询条件
	private Student student=new Student();
	
	/**
	 * 查询学生数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_acount_student_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String cbStudentCount() throws Exception 
	{
		// 查询数量
		student.setStartStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
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
	@Action(value = "list_student_acount_student_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String lbStudentList() throws Exception
	{
		// 查询集合
		student.setStartStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
		student.setEndStatusId(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		result.setList(studentBiz.findStudentsPageListByBaseConditions(student, result));
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
	
	
}
