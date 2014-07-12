package net.cedu.action.finance.payment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.finance.FeePayment;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缴费单所有操作
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonPaymentAction extends BaseAction {
	@Autowired
	private PaymentBiz paymentBiz;//缴费单业务层接口
	@Autowired
	private AcademyLevelBiz academylevelBiz;
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单业务层接口
	// 分页结果
	private PageResult<FeePayment> result = new PageResult<FeePayment>();

	// 查询条件
	private FeePayment feePayment;
	private Student student;

	/**
	 * 学生列表纪录数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "finance_payment_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String financePaymentCount() throws Exception {
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		// 查询数量
		result.setRecordCount(paymentBiz.findFeePaymentsPageCount(feePayment,
				student, result));
		return SUCCESS;
	}

	/**
	 * 查询学生列表集合通过条件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "finance_payment_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String financePaymentList() throws Exception {
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		// 查询集合
		result.setList(paymentBiz.findFeePaymentsPageList(feePayment, student,
				result));
		return SUCCESS;
	}

	/**
	 * 
	 * 更新缴费单状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_paymentl_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatePaymentStatus() throws Exception {
		if (feePayment != null) 
		{
			feePayment=this.feePaymentBiz.findFeePaymentById(feePayment.getId());
			if(feePayment != null && feePayment.getFeeWayId()!=0)
			{
				//缴费方式不同收费时的缴费单状态不同
				if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU)
				{
					feePayment.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);//缴费单状态(前期跟明细一样)
				}
				else if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU)
				{
					feePayment.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);//缴费单状态(前期跟明细一样)
				}
				else if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO)
				{
					feePayment.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);//缴费单状态(前期跟明细一样)
				}
				else if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU)
				{
					feePayment.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);//缴费单状态(前期跟明细一样)
				}
				else if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO)
				{
					feePayment.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);//缴费单状态(前期跟明细一样)
				}
			}
			paymentBiz.updateFeePaymentStatus(feePayment.getStatus(),
					feePayment.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 更新缴费单状态（缴费单作废）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_payment_invalid_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatePaymentInvalidStatus() throws Exception {
		if (feePayment != null) {
			paymentBiz.updateFeePaymentInvalidStatus(feePayment.getStatus(),
					feePayment.getId(),super.getUser().getUserId());
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 更新缴费单打印状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_paymentl_print_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatePaymentPrintStatus() throws Exception {
		if (feePayment != null) {
			paymentBiz.updateFeePaymentPrintStatus(feePayment.getIsPrint(),
					feePayment.getId());
		}
		return SUCCESS;
	}

	public PageResult<FeePayment> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePayment> result) {
		this.result = result;
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

}
