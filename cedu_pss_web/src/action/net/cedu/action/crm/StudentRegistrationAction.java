package net.cedu.action.crm;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.crm.Student;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生报名（潜在学生）
 * 
 * @author yangdongdong
 * 
 */
@Results({
	@Result(name = "input",type="redirect", location = "call?studentId=${id}&ischecked=1")})
public class StudentRegistrationAction extends BaseAction {
	@Autowired
	public StudentBiz studentBiz;

	public Student student;

	public int id;
	
	private String feedate;//缴费时间

	@Override
	public String execute() throws Exception {
		if (id != 0) {
			student = studentBiz.findStudentById(id);
			if(null==student.getCertNo() || student.getCertNo().trim().equals(""))
			{
				return INPUT;
			}
			
		}
		feedate=DateUtil.getNowTimestamp("yyyy-MM-dd").toString().substring(0,10);
		return super.execute();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeedate() {
		return feedate;
	}

	public void setFeedate(String feedate) {
		this.feedate = feedate;
	}

}
