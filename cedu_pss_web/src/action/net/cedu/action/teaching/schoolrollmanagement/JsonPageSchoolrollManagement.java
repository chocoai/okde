package net.cedu.action.teaching.schoolrollmanagement;

import net.cedu.action.BaseAction;
import net.cedu.biz.teaching.SchoolrollManagementBiz;
import net.cedu.entity.crm.Student;
import net.cedu.entity.teaching.SchoolrollManagement;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学籍分页
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonPageSchoolrollManagement extends BaseAction {
 
	private static final long serialVersionUID = -3193422798989006646L;
	@Autowired
	private SchoolrollManagementBiz schoolrollManagementBiz; //学籍业务层
	
	private SchoolrollManagement schoolrollmanagement; //学籍实体
	private Student student=new Student(); //学生实体
	
	private PageResult<Student> result=new PageResult<Student>(); //分页参数
	
	/**
	 * 分页集合
	 */
	@Action(value="page_schoolrollmanagement_list",results={@Result(name="success",type="json",params={"contentType","text/json"})})
	public String list()
	{
		try {
			result.setList(schoolrollManagementBiz.findScoolrollManagementPageListByCondition(student, result));
		} catch (Exception e) {
			 
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}
	@Action(value="page_shoolrollmanagement_count",results={@Result(name="success",type="json",params={"contentType","text/json"})})
	public String count()
	{
		try {
			result.setRecordCount(schoolrollManagementBiz.findScoolrollManagementPageCountByCondition(student, result));
		} catch (Exception e) {
			 
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}
	public SchoolrollManagement getSchoolrollmanagement() {
		return schoolrollmanagement;
	}
	public void setSchoolrollmanagement(SchoolrollManagement schoolrollmanagement) {
		this.schoolrollmanagement = schoolrollmanagement;
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
	
	
}
