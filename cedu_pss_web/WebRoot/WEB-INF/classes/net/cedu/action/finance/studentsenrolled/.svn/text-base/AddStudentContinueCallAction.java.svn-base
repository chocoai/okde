package net.cedu.action.finance.studentsenrolled;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.entity.crm.Student;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 老生续读（总部呼叫中心）
 * 
 * @author dongminghao
 * 
 */
public class AddStudentContinueCallAction extends BaseAction {

	// 电话
	private String phone;
	// 学生实体
	private List<Student> students;
	@Autowired
	private StudentBiz studentBiz;
	// 学生
	private Student student;
	// 学生ID
	private int studentId;

	@Override
	public String execute() throws Exception {
		if (phone != null && !"".equals(phone)) {
			// 通过手机号码查询用户信息，如果存在，返回实体，如果不存在把手机号码返回到页面
			students = studentBiz.findStudentsByPhone(phone);
			if (students != null) {
				for (Student s : students) {
					if (studentId == s.getId()) {
						student = s;
//						students.remove(student);
						return SUCCESS;
					}

				}

				if (student == null) {
					student=students.get(0);
//					students.remove(student);
				}
			}

		} else {
			if (studentId != 0) {
				student = studentBiz.findStudentById(studentId);
			}
		}
		//锁定招生途径，合作方，市场途径
		//request.setAttribute(arg0, arg1);
		return super.execute();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
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

}