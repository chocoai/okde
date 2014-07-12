package net.cedu.action.finance.academyrebatereview;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 院校返款单新增(可以返款的缴费单明细ajax)
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonAddAcademyRebateReviewShowAction extends BaseAction
{
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	//查询条件
	Student student=new Student();
	FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
	
	private String feepdIds;//已经选择的缴费单明细Ids字符串
	
	
	//************统计学习中心***************//
	private String fpdIds;//根据缴费单明细ids字符冲统计其所在的学习中心集合
	List<Branch> branchList=new ArrayList<Branch>();//学习中心列表
	
	
	/**
	 * 获取适合返款的缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_academy_rebate_review_fpd_show_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCount() throws Exception 
	{		
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
		
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		result.setRecordCount(this.feePaymentDetailBiz.findFeePaymentDetailCountByPageForAcademyRebate(feePaymentDetail, student,feepdIds, result));
		return SUCCESS;
	}

	/**
	 * 获取适合返款的缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_academy_rebate_review_fpd_show_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyList() throws Exception 
	{	
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
		
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		result.setList(this.feePaymentDetailBiz.findFeePaymentDetailListByPageForAcademyRebate(feePaymentDetail, student,feepdIds, result));
		return SUCCESS;
	}
	
	/**
	 * 根据缴费单明细Ids获取学习中心列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_list_branch_by_fpdIds_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchList() throws Exception 
	{	
		branchList=this.feePaymentDetailBiz.findBranchListByFpdIds(fpdIds);
		return SUCCESS;
	}

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}

	public String getFeepdIds() {
		return feepdIds;
	}

	public void setFeepdIds(String feepdIds) {
		this.feepdIds = feepdIds;
	}

	public String getFpdIds() {
		return fpdIds;
	}

	public void setFpdIds(String fpdIds) {
		this.fpdIds = fpdIds;
	}

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}

	
}
