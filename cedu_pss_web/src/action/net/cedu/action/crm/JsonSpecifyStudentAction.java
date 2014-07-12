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
 * 指定学生
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonSpecifyStudentAction extends BaseAction {

	@Autowired
	private StudentBiz studentBiz;
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();

	// 查询条件
	private Student student;

	private String studentIds;// 学生ID集合
	// 用户ID
	private int userId;
	// 被转移用户
	private int shiftUserId;

	/**
	 * 分配学生给中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_distribution_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmDistributionStudent() throws Exception {
		// 分配学生
		studentBiz.addUserStudent(userId, studentIds);
		return SUCCESS;
	}

	/**
	 * 转移学生到同中心用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_shift_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmShiftStudent() throws Exception {
		// 转移学生
		studentBiz.shiftStudent(shiftUserId, studentIds);
		return SUCCESS;
	}

	/**
	 * 查询用户总数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_specify_student_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmSpecifyStudentCount() throws Exception {
		// 查询数量
		if (student == null) {
			student = new Student();
			student.setUserId(this.getUser().getUserId());
		}
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		result
				.setRecordCount(studentBiz.findStudentsPageCount(student,
						result));
		return SUCCESS;
	}

	/**
	 * 查询用户集合通过条件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_specify_studentk_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmSpecifyStudentkList() throws Exception {
		// 查询集合
		if (student == null) {
			student = new Student();
			student.setUserId(this.getUser().getUserId());
		}
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
//		result.setOrder("CASE studentDataSource WHEN "+Constants.STU_SOURCE_CC+" THEN 1 WHEN "+Constants.STU_SOURCE_LC+" THEN 2 WHEN "+Constants.STU_SOURCE_NP+" THEN 3 when "+Constants.STU_SOURCE_HS+" then 4 when 0 then 5 END");
//		result.setSort("asc");
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

	public String getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getShiftUserId() {
		return shiftUserId;
	}

	public void setShiftUserId(int shiftUserId) {
		this.shiftUserId = shiftUserId;
	}

}
