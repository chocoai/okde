package net.cedu.action.crm;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.entity.crm.Student;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改学生信息
 * 
 * @author xiao
 * 
 */
public class UpdateStudentChanneltypeCheckedAction extends BaseAction 
{
	
	@Autowired
	private StudentBiz studentBiz;
	// 学生
	private Student student;
	// 学生ID
	private int studentId;

	@Override
	public String execute() throws Exception
	{		
		if (studentId != 0)
		{
			student = studentBiz.findStudentById(studentId);
		}
		
		return super.execute();
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