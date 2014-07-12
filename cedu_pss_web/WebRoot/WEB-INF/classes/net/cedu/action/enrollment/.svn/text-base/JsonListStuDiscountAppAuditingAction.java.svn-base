package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.DiscountApplicationBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.DiscountApplication;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生优惠审批
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonListStuDiscountAppAuditingAction extends BaseAction
{
	// 分页结果
	private PageResult<DiscountApplication> result = new PageResult<DiscountApplication>();
	
	@Autowired
	private DiscountApplicationBiz discountApplicationBiz;//优惠卷业务层接口
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校层次关系
	
	private DiscountApplication discountApplication=new DiscountApplication();//优惠卷实体
	private Student student=new Student();//学生实体
	
	
	/**
	 * 获取学生数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_discount_application_auditing_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String auditingCount() throws Exception {
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(student.getLevelId()).getLevelId());
		}
		student.setStartStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_XIU_XUE);
		result.setRecordCount(this.discountApplicationBiz.findDiscountApplicationCountByDetails(student, discountApplication, result));
		return SUCCESS;
	}
	
	/**
	 * 获取学生列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_discount_application_auditing_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String auditingList() throws Exception {
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(student.getLevelId()).getLevelId());
		}
		student.setStartStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_XIU_XUE);
		result.setList(this.discountApplicationBiz.findDiscountApplicationListByDetails(student, discountApplication, result));
		return SUCCESS;
	}

	public PageResult<DiscountApplication> getResult() {
		return result;
	}

	public void setResult(PageResult<DiscountApplication> result) {
		this.result = result;
	}

	public DiscountApplication getDiscountApplication() {
		return discountApplication;
	}

	public void setDiscountApplication(DiscountApplication discountApplication) {
		this.discountApplication = discountApplication;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
