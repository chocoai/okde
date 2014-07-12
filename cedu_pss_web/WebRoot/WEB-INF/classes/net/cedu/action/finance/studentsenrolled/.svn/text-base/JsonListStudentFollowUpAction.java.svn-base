package net.cedu.action.finance.studentsenrolled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.StuStatusBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.StudentStatus;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生置为跟进中
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonListStudentFollowUpAction extends BaseAction
{
	@Autowired
	private StudentBiz studentBiz;
	
	@Autowired
	private FeePaymentDetailBiz fpdBiz;
	
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	// 查询条件
	private Student student=new Student();
	
	//学生置为跟进中参数
	private int studentId;//学生Id
	private boolean isfail=false;//返回参数
	private boolean isback=false;//返回参数
	
	//清空收费标准
	@Autowired
	private PendingFeePaymentBiz pfpBiz;//待缴费单_业务层接口
	
	
	/**
	 * 查询学生数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_follow_up_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String cbStudentCount() throws Exception 
	{
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_BI_YI);
		result.setRecordCount(studentBiz.findStudentsPageCountByBaseConditions(student));
		return SUCCESS;
	}

	/**
	 * 查询学生列表集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_follow_up_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String lbStudentList() throws Exception
	{
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_BI_YI);
		result.setList(studentBiz.findStudentsPageListByBaseConditions(student, result));
		return SUCCESS;
	}

	/**
	 * 学生置为跟进中
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upate_student_follow_up_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upStudentStatus() throws Exception
	{
		if(studentId>0)
		{
			//根据学生Id查询缴费单未作废的数量
			int count=this.fpdBiz.findFpdCountByStudentIdStatus(studentId);
			if(count==0)
			{
				Student stu=this.studentBiz.findStudentById(studentId);
				if(stu!=null)
				{
					stu.setStatus(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
					stu.setUpdaterId(super.getUser().getUserId());
					stu.setModifiedTime(new Date());
					this.studentBiz.updateStudentInfo(stu);
					isfail=true;
				}
			}
			else
			{
				isback=true;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 清空学生待缴费单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "del_student_pending_fee_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String delStudentPending() throws Exception
	{
		if(studentId>0)
		{
			isback=this.pfpBiz.deletePendingFeePaymentByStudentId(studentId);
		}
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

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	
}
