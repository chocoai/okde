package net.cedu.action.crm;

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
 * 学生未分配列表Action
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonStudentWeiFenPeiAction extends BaseAction
{
	@Autowired
	private StudentBiz studentBiz;
	
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	// 查询条件
	private Student student;
	
	/**
	 * 未分配学生列表纪录数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "weifenpei_student_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String wfStudentCount() throws Exception {
		// 查询数量
		
		student.setBranchId(super.getUser().getOrgId());
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		result.setRecordCount(studentBiz.findStudentsPageCount(student, result));
		return SUCCESS;
	}

	/**
	 * 未分配学生列表集合(带特殊分页)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "weifenpei_student_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String wfStudentList() throws Exception {
		// 查询集合
		
		student.setBranchId(super.getUser().getOrgId());
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		//result.setOrder("CASE studentDataSource WHEN "+Constants.STU_SOURCE_CC+" THEN 1 WHEN "+Constants.STU_SOURCE_LC+" THEN 2 WHEN "+Constants.STU_SOURCE_NP+" THEN 3 when "+Constants.STU_SOURCE_HS+" then 4 when 0 then 5 END");
		//result.setSort("asc");createDate
		result.setOrder("CASE studentDataSource WHEN "+Constants.STU_SOURCE_CC+" THEN 1 WHEN "+Constants.STU_SOURCE_NP+" THEN 2 WHEN "+Constants.STU_SOURCE_LC+" THEN 3 when "+Constants.STU_SOURCE_HS+" then 4 when 0 then 5 END asc ,createDate desc");
		result.setSort("");
		result.setList(studentBiz.findStudentsPageList(student, result));
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
