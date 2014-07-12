package net.cedu.action.crm;

import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 呼叫（潜在学生）
 * 
 * @author yangdongdong
 * 
 */
@Results( {
		@Result(name = "input", location = "/WEB-INF/content/crm/no_call_student.jsp"),
		@Result(name = "info", location = "/WEB-INF/content/crm/call_studentinfo.jsp") })
public class StudentViewAction extends BaseAction {

	@Autowired
	private StudentBiz studentBiz;
	// 学生实体
	private Student student;

	private int studentId;

	private PageResult<Student> result = new PageResult<Student>();

	@Override
	public String execute() throws Exception {

		// 查询已的学生呼叫
		if (studentId != 0) {
			student = studentBiz.findStudentById(studentId);
			return "info";
		}

		result.setPageSize(1);
		if (student == null) {
			student = new Student();
		}
		// 查询已分配但没有呼叫的学生
		student.setStatus(Constants.STU_CALL_STATUS_YI_FENG_PEI);
		student.setCallStatus(0);
		student.setGender(-1);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		// 呼叫条数
		int count = studentBiz.findStudentsPageCount(student, result);
		if (count > 0) {

			if (result.getCurrentPageIndex() <= 0) {
				result.setCurrentPageIndex(count);
			}

			if (result.getCurrentPageIndex() >= count) {
				result.setCurrentPageIndex(1);
			}

			result.setRecordCount(count);
			List<Student> students = studentBiz.findStudentsPageList(student,
					result);
			student = students.get(0);
			if (student != null) {
				// 更改呼叫次数
				student.setCallStatus(1);
				student.setUpdaterId(super.getUser().getUserId());
				student.setModifiedTime(new Date());
				studentBiz.updateStudent(student, null, null);
			}
		} else {
			// 没有要呼叫的用户
			student = null;
			return INPUT;
		}
		return super.execute();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public PageResult<Student> getResult() {
		return result;
	}

	public void setResult(PageResult<Student> result) {
		this.result = result;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}
