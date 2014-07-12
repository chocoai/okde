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
@Results({
		@Result(name = "input", location = "/WEB-INF/content/crm/no_call_student.jsp"),
		@Result(name = "info", location = "/WEB-INF/content/crm/call_studentinfo.jsp") })
public class CallAction extends BaseAction {

	@Autowired
	private StudentBiz studentBiz;
	// 学生实体
	private Student student;

	private int studentId;

	private int ischecked;//是否跳转页面时验证身份证  return info的时候（点击跟进中学生后面的报名跳转过来的）
	
	private PageResult<Student> result = new PageResult<Student>();
	
	private int count;//呼叫人数

	@Override
	public String execute() throws Exception {
		try {
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
			student.setUserId(super.getUser().getUserId());
			student.setBranchId(super.getUser().getOrgId());
			// 呼叫条数
			count = studentBiz.findStudentsPageCount(student, result);
			if (count > 0) {

				if (result.getCurrentPageIndex() <= 0) {
					result.setCurrentPageIndex(count);
				}

				if (result.getCurrentPageIndex() >= count) {
					result.setCurrentPageIndex(1);
				}

				result.setRecordCount(count);
				
				//排序   呼叫中心的学生先进行呼叫 2012-02-08
				//result.setOrder("CASE studentDataSource WHEN "+Constants.STU_SOURCE_CC+" THEN 1 WHEN "+Constants.STU_SOURCE_LC+" THEN 2 WHEN "+Constants.STU_SOURCE_NP+" THEN 3 when "+Constants.STU_SOURCE_HS+" then 4 when 0 then 5 END");
				result.setOrder("CASE studentDataSource WHEN "+Constants.STU_SOURCE_CC+" THEN 1 WHEN "+Constants.STU_SOURCE_NP+" THEN 2 WHEN "+Constants.STU_SOURCE_LC+" THEN 3 when "+Constants.STU_SOURCE_HS+" then 4 when 0 then 5 END asc ,createDate desc");
				result.setSort("asc");
				
				List<Student> students = studentBiz.findStudentsPageList(
						student, result);
				student = students.get(0);
				if (student != null) {
					// 更改呼叫次数
					student.setCallStatus(1);
					student.setUserId(super.getUser().getUserId());
					student.setUpdaterId(super.getUser().getUserId());
					student.setModifiedTime(new Date());
					studentBiz.updateStudent(student, null, null);
				}
			} else {
				// 没有要呼叫的用户
				student = null;
				return INPUT;
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public int getIschecked() {
		return ischecked;
	}

	public void setIschecked(int ischecked) {
		this.ischecked = ischecked;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
