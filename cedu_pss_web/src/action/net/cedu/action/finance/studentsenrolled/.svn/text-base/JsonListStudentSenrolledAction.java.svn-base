package net.cedu.action.finance.studentsenrolled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.StuStatusBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.StudentStatus;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生录取
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonListStudentSenrolledAction extends BaseAction
{
	@Autowired
	private StudentBiz studentBiz;
	
	@Autowired
	private StuStatusBiz stuStatusBiz;// 学生状态_业务层接口
	private List<StudentStatus> stuStatusList = new ArrayList<StudentStatus>();// 学生状态集合
	private int startId;
	private int endId;
	
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	// 查询条件
	private Student student=new Student();
	
	//录取学生参数
	private int studentId;//学生Id
	private boolean isfail=false;//返回参数
	
	//批量录取学生参数
	private String studentIds;//学生ids字符串
	private boolean isback=false;//返回参数
	
	/**
	 * 查询学生数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_senrolled_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String cbStudentCount() throws Exception 
	{
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		result.setRecordCount(studentBiz.findStudentsPageCountByBaseConditions(student));
		return SUCCESS;
	}

	/**
	 * 查询学生列表集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_senrolled_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String lbStudentList() throws Exception
	{
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		result.setList(studentBiz.findStudentsPageListByBaseConditions(student, result));
		return SUCCESS;
	}

	/**
	 * 录取学生
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upate_student_senrolled_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upStudentStatus() throws Exception
	{
		Student stu=this.studentBiz.findStudentById(studentId);
		if(stu!=null)
		{
//			if(stu.getTuitionStatus()==Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI)
//			{
//				isfail=true;
//			}
//			else
//			{
				stu.setStatus(Constants.STU_CALL_STATUS_YI_LU_QU);
				stu.setAdmitTime(new Date());
				stu.setUpdaterId(super.getUser().getUserId());
				stu.setModifiedTime(new Date());
				this.studentBiz.updateStudentInfo(stu);
//			}
		}
		return SUCCESS;
	}
	
	/**
	 * 批量录取学生
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upate_batch_student_senrolled_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upBatchStudentStatus() throws Exception
	{
		if(studentIds!=null)
		{
			this.studentBiz.updateStudentSenrolled(studentIds, Constants.STU_CALL_STATUS_YI_LU_QU);
		}
		else
		{
			isback=true;
		}
		return SUCCESS;
	}
	
	/**
	 * 学生状态集合(录取的学生状态和已毕业的学生状态) 
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_student_senrolled_status_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"stuStatusList.*,startId,endId"
	}) })
	public String stuStatusList() throws Exception {
		stuStatusList = this.stuStatusBiz.findStatusNamesByStartIdAndEndId(startId, endId);
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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public List<StudentStatus> getStuStatusList() {
		return stuStatusList;
	}

	public void setStuStatusList(List<StudentStatus> stuStatusList) {
		this.stuStatusList = stuStatusList;
	}

	public int getStartId() {
		return startId;
	}

	public void setStartId(int startId) {
		this.startId = startId;
	}

	public int getEndId() {
		return endId;
	}

	public void setEndId(int endId) {
		this.endId = endId;
	}

	public String getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	
}
