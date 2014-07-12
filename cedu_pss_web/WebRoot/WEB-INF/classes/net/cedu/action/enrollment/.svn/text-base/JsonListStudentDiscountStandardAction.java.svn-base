package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.entity.enrollment.StudentDiscountPolicy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生优惠政策标准
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonListStudentDiscountStandardAction extends BaseAction
{
	// 分页结果
	private PageResult<StudentDiscountPolicy> result = new PageResult<StudentDiscountPolicy>();
	
	@Autowired
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;// 学生优惠政策标准业务接口	
	
	//查询条件
	private StudentDiscountPolicy studentDiscountPolicy;
	
	/**
	 * 获取学生优惠政策标准数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_discount_policy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentpolicyCount() throws Exception 
	{		
		result.setRecordCount(this.studentDiscountPolicyBiz.findStudentDiscountPolicyCountByDetails(studentDiscountPolicy, result));
		return SUCCESS;
	}
	
	/**
	 * 获取学生优惠政策标准列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_discount_policy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentpolicyList() throws Exception {
		result.setList(this.studentDiscountPolicyBiz.findStudentDiscountPolicyListByDetails(studentDiscountPolicy, result));
		return SUCCESS;
	}

	public PageResult<StudentDiscountPolicy> getResult() {
		return result;
	}

	public void setResult(PageResult<StudentDiscountPolicy> result) {
		this.result = result;
	}

	public StudentDiscountPolicy getStudentDiscountPolicy() {
		return studentDiscountPolicy;
	}

	public void setStudentDiscountPolicy(StudentDiscountPolicy studentDiscountPolicy) {
		this.studentDiscountPolicy = studentDiscountPolicy;
	}
	
	
}
