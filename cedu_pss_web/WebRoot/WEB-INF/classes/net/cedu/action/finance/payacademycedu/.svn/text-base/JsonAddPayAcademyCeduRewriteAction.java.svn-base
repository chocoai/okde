package net.cedu.action.finance.payacademycedu;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
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
public class JsonAddPayAcademyCeduRewriteAction extends BaseAction
{
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	//查询条件
	private int academyId;//院校Id
	private int batchId;//批次Id
	private int branchId;//学习中心Id
	private int feeSubjectId;//费用科目Id
	private String feepdIds;//已经选择的缴费单明细Ids字符串
	
	
	/**
	 * 获取适合返款的缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_academy_rebate_feepaymentdetail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCount() throws Exception 
	{
		FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
		feePaymentDetail.setFeeSubjectId(feeSubjectId);
		Student student=new Student();
		student.setAcademyId(academyId);
		student.setEnrollmentBatchId(batchId);
		student.setBranchId(branchId);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		result.setRecordCount(this.feePaymentDetailBiz.findFeePaymentDetailCountByPageForAcademyRebate(feePaymentDetail, student,feepdIds, result));
		return SUCCESS;
	}

	/**
	 * 获取适合返款的缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_academy_rebate_feepaymentdetail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyList() throws Exception 
	{	
		FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
		feePaymentDetail.setFeeSubjectId(feeSubjectId);
		Student student=new Student();
		student.setAcademyId(academyId);
		student.setEnrollmentBatchId(batchId);
		student.setBranchId(branchId);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		result.setList(this.feePaymentDetailBiz.findFeePaymentDetailListByPageForAcademyRebate(feePaymentDetail, student,feepdIds, result));
		return SUCCESS;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getFeepdIds() {
		return feepdIds;
	}

	public void setFeepdIds(String feepdIds) {
		this.feepdIds = feepdIds;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
	
}
