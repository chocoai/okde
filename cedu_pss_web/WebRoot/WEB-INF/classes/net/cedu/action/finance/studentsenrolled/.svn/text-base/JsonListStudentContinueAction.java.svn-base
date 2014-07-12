package net.cedu.action.finance.studentsenrolled;

import java.util.ArrayList;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生毕业
 * 
 */
@ParentPackage("json-default")
public class JsonListStudentContinueAction extends BaseAction
{
	@Autowired
	private StudentBiz studentBiz;
	
	@Autowired
	private BaseDictBiz baseDictBiz;
	
	@Autowired
	private AcademyLevelBiz academylevelBiz;// 
	
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	// 查询条件
	private Student student=new Student();
	
	//毕业学生参数
	private int studentId;//学生Id
	
	/**
	 * 查询学生数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_continue_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String cbStudentCount() throws Exception 
	{
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_XIU_XUE);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_TUI_XUE);
		result.setRecordCount(studentBiz.findStudentsPageCountByBaseConditions(student));
		return SUCCESS;
	}

	/**
	 * 查询学生列表集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_continue_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String lbStudentList() throws Exception
	{
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_XIU_XUE);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_TUI_XUE);
		result.setList(studentBiz.findStudentsPageListByBaseConditions(student, result));
		return SUCCESS;
	}
	
	@Action(value = "view_student_continue_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String viewStudent() throws Exception
	{
		Student stu=this.studentBiz.findStudentById(studentId);
		if(stu!=null)
		{
			student = stu;
		}
		return SUCCESS;
	}

	/**
	 * 老生续读
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upate_student_continue_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upStudentStatus() throws Exception
	{
		//老生
		Student stu=this.studentBiz.findStudentById(student.getId());
		//新生
		Student newstu = new Student();
		//基本信息
		newstu.setName(stu.getName());
		newstu.setCertType(stu.getCertType());
		newstu.setCertNo(stu.getCertNo());
		newstu.setMobile(stu.getMobile());
		newstu.setPhone(stu.getPhone());
		newstu.setDegree(stu.getDegree());
		newstu.setGender(stu.getGender());
		newstu.setEmail(stu.getEmail());
		newstu.setLivingPlace(stu.getLivingPlace());
		newstu.setMsn(stu.getMsn());
		newstu.setQq(stu.getQq());
		newstu.setZipcode(stu.getZipcode());
		newstu.setAddress(stu.getAddress());
		//合作方
		newstu.setEnrollmentSource(Constants.WEB_STU_LAO_SHENG_XU_DU);
		//老生续读无合作方
		newstu.setChannelId(0);
		//市场途径
		newstu.setEnrollmentWayName("老生续读");
		BaseDict bd=baseDictBiz.findBaseDictsByTypeAndName(Constants.BASEDICT_STYLE_ENROLLMENTWAY, newstu.getEnrollmentWayName());
		if(bd!=null)
		{
			newstu.setEnrollmentWay(bd.getId());
		}
		//意向
		newstu.setAcademyId(student.getAcademyId());
		newstu.setEnrollmentBatchId(student.getEnrollmentBatchId());
		newstu.setLevelId(student.getLevelId());
		newstu.setMajorId(student.getMajorId());
		//学生状态(已分配)
		newstu.setStatus(Constants.STU_CALL_STATUS_YI_FENG_PEI);
		//是否呼叫
		newstu.setCallStatus(1);
		//学习中心
		newstu.setBranchId(stu.getBranchId());
		//数据来源
		newstu.setStudentDataSource(Constants.STU_SOURCE_LC);
		//创建
		newstu.setCreateDate(new Date());
		newstu.setUserId(super.getUser().getUserId());
		//锁定姓名
		if(newstu.getName()!=null&&!newstu.getName().equals("")){
			newstu.setNameLockingStatus(1);
		}
		//锁定身份证
		if(newstu.getCertNo()!=null&&!newstu.getCertNo().equals("")){
			newstu.setCertNoLockingStatus(1);
		}
		
		// 层次转换
		if (newstu.getLevelId() != 0) {
			AcademyLevel academyLevel = academylevelBiz.findById(newstu
					.getLevelId());
			newstu.setLevelId(academyLevel.getLevelId());
		}
		//增加新生
		studentBiz.addStudentForLaoShengXuDu(newstu);
		//修改老生
		stu.setNewstuId(newstu.getId());
		stu.setUpdaterId(super.getUser().getUserId());
		stu.setModifiedTime(new Date());
		studentBiz.updateStudentInfo(stu);
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
	
}
