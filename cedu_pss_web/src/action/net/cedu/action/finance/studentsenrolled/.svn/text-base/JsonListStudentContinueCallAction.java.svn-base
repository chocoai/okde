package net.cedu.action.finance.studentsenrolled;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.crm.FollowUpBiz;
import net.cedu.biz.crm.OperationLogBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.crm.FollowUp;
import net.cedu.entity.crm.OperationLog;
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
public class JsonListStudentContinueCallAction extends BaseAction
{
	@Autowired
	private StudentBiz studentBiz;
	
	@Autowired
	private BaseDictBiz baseDictBiz;
	
	@Autowired
	private AcademyLevelBiz academylevelBiz;// 
	
	@Autowired
	private FollowUpBiz followUpBiz;
	
	@Autowired
	private OperationLogBiz operationLogBiz;
	
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	// 查询条件
	private Student student=new Student();
	//跟进内容
	FollowUp followUp = new FollowUp();
	
	//毕业学生参数
	private int studentId;//学生Id
	
	/**
	 * 查询学生数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_continue_call_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String cbStudentCount() throws Exception 
	{
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_XIU_XUE);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_TUI_XUE);
		//总部查询全部学生 不限制中心
		//student.setBranchId(0);
		result.setRecordCount(studentBiz.findStudentsPageCountByBaseConditions(student));
		return SUCCESS;
	}

	/**
	 * 查询学生列表集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_continue_call_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String lbStudentList() throws Exception
	{
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_XIU_XUE);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_TUI_XUE);
		//总部查询全部学生 不限制中心
		//student.setBranchId(0);
		result.setList(studentBiz.findStudentsPageListByBaseConditions(student, result));
		return SUCCESS;
	}
	
	@Action(value = "view_student_continue_call_ajax", results = { @Result(name = "success", type = "json", params = {
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
	@Action(value = "upate_student_continue_call_ajax", results = { @Result(name = "success", type = "json", params = {
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
		//手机
		newstu.setMobile(student.getMobile());
		//座机
		newstu.setPhone(student.getPhone());
		//性别
		newstu.setGender(student.getGender());
		//所在城市
		newstu.setLivingPlace(student.getLivingPlace());
		//msn
		newstu.setMsn(student.getMsn());
		//qq
		newstu.setQq(student.getQq());
		//email
		newstu.setEmail(student.getEmail());
		//单位信息
		newstu.setWorkUnitInfo(student.getWorkUnitInfo());
		//备注
		newstu.setRemark(student.getRemark());
		//学历
		newstu.setDegree(stu.getDegree());
		//邮编
		newstu.setZipcode(stu.getZipcode());
		//地址
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
		//学习中心
		newstu.setBranchId(student.getBranchId());
		//院校
		newstu.setAcademyId(student.getAcademyId());
		//批次
		newstu.setEnrollmentBatchId(student.getEnrollmentBatchId());
		//层次
		newstu.setLevelId(student.getLevelId());
		//专业
		newstu.setMajorId(student.getMajorId());
		//学生状态
		newstu.setStatus(student.getStatus());
		//是否呼叫     学习中心老生续读暂时取老生呼叫状态
		newstu.setCallStatus(student.getCallStatus());
		//数据来源
		newstu.setStudentDataSource(Constants.STU_SOURCE_CC);
		
		OperationLog operationLog = new OperationLog();
		//状态为已导入未分配，记录跟进时间
		if(student.getStatus() == Constants.STU_CALL_STATUS_YI_DAO_RU)
		{
			Date date = new Date();
			newstu.setLatestFollowUpDate(date);
			newstu.setFollowUpId(super.getUser().getUserId());
			// 学生状态预报名(标记呼叫中心的跟进内容)
			followUp.setStatusId(Constants.STU_CALL_STATUS_YU_BAO_MING);
			// 跟进人
			followUp.setCreatorId(super.getUser().getUserId());
			// 跟进时间
			followUp.setCreatedTime(date);
			// 操作日志
			operationLog = new OperationLog();
			operationLog.setContent(super
					.getText("crm.create.student.3", new Object[] {
							super.getUser().getFullName(), newstu.getName() }));// 内容
			operationLog.setIp(request.getRemoteHost());
			operationLog.setCreateId(super.getUser().getUserId());
			operationLog.setCreateTime(new Date());
		}
		
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
		if(newstu.getId()!=0)
		{
			if(student.getStatus() == Constants.STU_CALL_STATUS_YI_DAO_RU && followUp!=null && operationLog!=null)
			{
				followUp.setStudentId(newstu.getId());
				//增加跟进
				followUpBiz.addFollowUp(followUp);
				//增加操作日志
				operationLogBiz.addOperationLog(operationLog);
			}
		}
		
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

	public FollowUp getFollowUp() {
		return followUp;
	}

	public void setFollowUp(FollowUp followUp) {
		this.followUp = followUp;
	}
	
}
