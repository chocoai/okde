package net.cedu.action.finance.payment;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 历史缴费
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonPaymentHistoryAction extends BaseAction
{
	
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	
	@Autowired
	private StudentBiz studentBiz;//学生业务层接口
	private Student student=new Student();//学生实体
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校层次关系
	@Autowired
	private AcademyBiz academyBiz;//院校
	
	/**
	 * 获取学生数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_payment_history_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String applicationCount() throws Exception {
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(student.getLevelId()).getLevelId());
		}
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_TUI_XUE);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		//强制收费院校ID集合
		//student.setAcademyIdIds(academyBiz.findAcademyIdsByForceFeePolicyStatus(1));
		result.setRecordCount(this.studentBiz.findStudentsPageCount(student, result));
		return SUCCESS;
	}
	
	/**
	 * 获取学生列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_payment_history_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String applicationList() throws Exception {
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(student.getLevelId()).getLevelId());
		}
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_TUI_XUE);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		//强制收费院校ID集合
		//student.setAcademyIdIds(academyBiz.findAcademyIdsByForceFeePolicyStatus(1));
		
		result.setList(this.studentBiz.findStudentListsByParams(student, result));
		
		
		
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
