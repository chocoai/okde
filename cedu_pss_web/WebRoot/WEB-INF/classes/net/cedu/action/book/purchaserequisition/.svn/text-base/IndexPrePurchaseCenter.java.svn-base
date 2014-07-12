package net.cedu.action.book.purchaserequisition;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.crm.Student;
/**
 * 中心预申购
 * @author YY
 *
 */
public class IndexPrePurchaseCenter extends BaseAction {

	private static final long serialVersionUID = -8528984814876207562L;

	@Autowired
	private StudentBiz studentBiz;  //学生业务层
	
	private Student student;//学生实体 
	private List<Student> stulist=new ArrayList<Student>(); //学生集合
	
	@Autowired
	private AcademyBiz academyBiz; //院校业务层
 
	private List<Academy> academies=new ArrayList<Academy>(); //院校集合
 
	
	public String execute()
	{
		try {
			//查询所有院校
			academies=academyBiz.findAllAcademys(); 
			//查询所有学生
			stulist=studentBiz.findStudentByPrePurchaseCenter(student);
		} catch (Exception e) {			 
			e.printStackTrace();
		}
		
		return SUCCESS;
		
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStulist() {
		return stulist;
	}

	public void setStulist(List<Student> stulist) {
		this.stulist = stulist;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public void setAcademies(List<Academy> academies) {
		this.academies = academies;
	}

 
	
}
