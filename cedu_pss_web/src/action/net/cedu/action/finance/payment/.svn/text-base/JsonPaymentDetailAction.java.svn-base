package net.cedu.action.finance.payment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缴费单明细所有操作
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonPaymentDetailAction extends BaseAction {
	@Autowired
	private PaymentBiz paymentBiz;
	@Autowired
	private AcademyLevelBiz academylevelBiz;
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();

	// 查询条件
	private FeePayment feePayment;
	private FeePaymentDetail feePaymentDetail;
	private Student student;

	/**
	 * 学生列表纪录数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "finance_paymentdetail_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String financePaymentDetailCount() throws Exception {
		// 查询数量
		
		if (student!=null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		
		result.setRecordCount(paymentBiz.findFeePaymentDetailsPageCount(
				feePaymentDetail, feePayment, student, result));
		return SUCCESS;
	}

	/**
	 * 查询学生列表集合通过条件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "finance_paymentdetail_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String financePaymentDetailList() throws Exception {
		if (student!=null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		// 查询集合
		result.setList(paymentBiz.findFeePaymentDetailsPageList(
				feePaymentDetail, feePayment, student, result));
		return SUCCESS;
	}

	/**
	 * 转正式缴费单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_paymentdetail_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatePaymentDetailStatus() throws Exception {
		if (feePaymentDetail!=null) {
			paymentBiz.updateFeePaymentDetailType(feePaymentDetail.getTypes(), feePaymentDetail.getId());
		}
		
		return SUCCESS;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}

}
