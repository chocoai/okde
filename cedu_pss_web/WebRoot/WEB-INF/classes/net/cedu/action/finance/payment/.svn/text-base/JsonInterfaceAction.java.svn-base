package net.cedu.action.finance.payment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.enrollment.AcademyBranchFeeWayBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyBranchFeeWay;
import net.cedu.entity.finance.FeePayment;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缴费单外部接口
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonInterfaceAction extends BaseAction {
	@Autowired
	private AcademyBranchFeeWayBiz academyBranchFeeWayBiz;

	// 缴费方式集合
	private List<AcademyBranchFeeWay> academyBranchFeeWays = new ArrayList<AcademyBranchFeeWay>();

	// 查询条件
	private Student student;
	private FeePayment feePayment;
	/**
	 * 缴费方式
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "finance_payment_way_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String financePaymentWayList() throws Exception {
		if (student != null) {
			if (student.getBranchId() != 0
					&& student.getEnrollmentBatchId() != 0) {
				academyBranchFeeWays = academyBranchFeeWayBiz
						.findByBatchAndBranch(student.getEnrollmentBatchId(),
								student.getBranchId());
			}
		}
		return SUCCESS;
	}

	public List<AcademyBranchFeeWay> getAcademyBranchFeeWays() {
		return academyBranchFeeWays;
	}

	public void setAcademyBranchFeeWays(
			List<AcademyBranchFeeWay> academyBranchFeeWays) {
		this.academyBranchFeeWays = academyBranchFeeWays;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	

}
